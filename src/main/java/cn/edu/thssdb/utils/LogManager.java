package cn.edu.thssdb.utils;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.parser.SQLErrorListener;
import cn.edu.thssdb.parser.SQLLexer;
import cn.edu.thssdb.parser.SQLParser;
import cn.edu.thssdb.query.statement.Statement;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import static org.junit.Assert.*;


import java.io.*;
import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LogManager {
  private static Boolean isTransaction = false;   // 事务开始时true，事务commit时false
  public static ArrayList<ReentrantReadWriteLock.WriteLock> writeLocks;
  public static ArrayList<String> logList;
  private String logFileName;

  public static Boolean getIsTransaction() {
    return isTransaction;
  }

  public static void setIsTransaction(Boolean isTransaction) {
    LogManager.isTransaction = isTransaction;
  }

  /**
   * 若当前是事务状态，则插入排它锁至list
   * @param writeLock table的排它锁
   */
  public static void addWritelock(ReentrantReadWriteLock.WriteLock writeLock) {
    if (isTransaction) {
      writeLock.lock();
      writeLocks.add(writeLock);
    }
  }

  /**
   * 在table执行写操作时，若发现异常
   * 且当前状态是事务状态，则删除最近插入的排它锁
   */
  public static void removeWritelock() {
    if (isTransaction) {
      ReentrantReadWriteLock.WriteLock writeLock =
              writeLocks.remove(writeLocks.size()-1);
      writeLock.unlock();
    }
  }
//  public static byte[] insertRowToByte(long loc,byte[] rowcontent){
//
//
//  }

  /**
   * 写一条log
   */
  public static void writeLog(Manager manager) throws IOException {

    String dbname=manager.getCurrentDB().getName();
    String logPath=Global.dirPath +dbname+"/"+dbname+".log";
    if(!new File(Global.dirPath +dbname).exists()){
      File f=new File(Global.dirPath +dbname);
      f.mkdirs();
    }
    if(!new File(logPath).exists()){
      new File(logPath);
    }
    FileWriter toLog=new FileWriter(logPath,true);
//    toLog.write("begin transaction"+System.getProperty("line.separator"));

    for(String logrecord : logList){
      toLog.write(logrecord+System.getProperty("line.separator"));
    }
//    toLog.write("commit"+System.getProperty("line.separator"));

    toLog.flush();
    toLog.close();

  }
  public static void writeSingleLog(Manager manager,String log) throws IOException {

    String dbname=manager.getCurrentDB().getName();
    String logPath=Global.dirPath +dbname+"/"+dbname+".log";
    if(!new File(Global.dirPath +dbname).exists()){
      File f=new File(Global.dirPath +dbname);
      f.mkdirs();
    }

    if(!new File(logPath).exists()){
      new File(logPath);
    }
    FileWriter toLog=new FileWriter(logPath,true);
    toLog.write(log+System.getProperty("line.separator"));
    toLog.flush();
    toLog.close();

  }


  public static void recover(Manager manager,Database db) throws IOException {
    ArrayList<String> logList=readLog(db);

    for(String log:logList){
      String[] strings = log.split(" ");

      if(strings[0].equals("insert") || strings[0].equals("delete") || strings[0].equals("update") ||(strings[0].equals("create") && strings[1].equals("table"))||(strings[0].equals("drop") && strings[1].equals("table"))){
        redo(manager,db,log);
      }

    }
  }

  /**
   * 读一条log
   */
  public static ArrayList<String> readLog(Database db) throws IOException {
    String dbname=db.getName();
    String logPath=Global.dirPath +dbname+"/"+dbname+".log";
    ArrayList<String> statements=new ArrayList<>();

    if(new File(logPath).exists()){
      FileReader fromLog=new FileReader(logPath);
      BufferedReader br=new BufferedReader(fromLog);
      String line="";
      while ((line=br.readLine())!=null) {
        statements.add(line);
      }
      br.close();
      fromLog.close();
    }

    int i=statements.size()-1;
    boolean deleteLog=false;
    while(i>=0){
      String[] strings = statements.get(i).split(" ");
      if(strings[0]=="commit"){
        break;
      }else if(strings[0]=="begin"){
        deleteLog=true;
        break;
      }
      i--;

    }
    if(deleteLog){
      for(int k=i;k<statements.size();k++){
        statements.remove(k);
      }
    }
    return statements;
  }

  public static void redo(Manager manager, Database db, String statement)  {

    manager.switchTempDatabase(db.getName());
    Listener listener = getListenerFromStatement(statement);

    ArrayList<Statement> statementArrayList = listener.getStatements();

    for (Statement s : statementArrayList){
      assertTrue(s.isValid());    // 必须判断正确
      try{
        System.out.println(s.execute(manager).toString());
      }catch (RuntimeException e){
        System.out.println(e.getMessage());
      }
    }
  }



  protected static Listener getListenerFromStatement(String statement) throws SyntaxErrorException {
//    CharStream in = CharStreams.fromFileName(fileName);
    CharStream in = CharStreams.fromString(statement);
    SQLLexer lexer = new SQLLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    SQLParser parser = new SQLParser(tokens);
    parser.removeErrorListeners();      // 去除默认的错误监听器
    parser.addErrorListener(new SQLErrorListener());    // 添加定制的错误监听器
    ParseTree tree;
    try{
      tree = parser.parse();
    } catch (RuntimeException e){
      throw new SyntaxErrorException(e.getMessage());
    }
    Listener listener = new Listener();
    ParseTreeWalker.DEFAULT.walk(listener, tree);
    return listener;
  }

  /**
   * 清空log
   */
  public static void deleteLogs(Database db) throws IOException {

    String logPath=Global.dirPath +db.getName()+"/"+db.getName()+".log";
    FileWriter toLog=new FileWriter(logPath);
    toLog.write("");
    toLog.flush();
    toLog.close();


  }

  /**
   * 查看log文件是否有log内容
   * @return true: 有 false: 空
   */
  public Boolean checkHasLog() {
    return false;
  }

  /**
   * 恢复创建database,删除这个新创建的文件夹
   */
  public void undoCreateDB(String name){
    String filpath = Global.dirPath+"/"+name;
    deleteDir(filpath);
  }
  /**
   * 恢复创建table,删除这个新创建的文件夹
   */
  public void undoCreateTable(String dbname,String tablename){
    String tablePath=Global.dirPath+dbname+"/"+tablename;
    deleteDir(tablePath);
  }
  /**
   * 恢复删除database,无需任何操作
   */
  public void undoDeleteDb(String dbname,String tablename){
  }
  /**
   * 恢复删除table，无需操作
   */
  public void undoDeleteTable(String dbname,String tablename){
  }
  /**
   * 恢复插入行，收回空间（修改freeptr）
   */
  public void undoInsertRow(String dbname,String tablename,long ptr){
  }
  /**
   * 恢复删除行,在freelistPtr重新写入
   */
  public void undoDeleteRow(String dbname,String tablename,byte[] rowContent){
  }
  /**
   * 恢复修改行,从index获得原位置，写入原内容
   */
  public void undoUpdateRow(String dbname,String tablename,byte[] rowContent){
  }

  /**
   * 迭代删除文件夹
   * @param dirPath 文件夹路径
   */
  public static void deleteDir(String dirPath)
  {
    File file = new File(dirPath);
    if(file.isFile()) {
      file.delete();
    }else {
      File[] files = file.listFiles();
      if(files == null) {
        file.delete();
      }else {
        for (int i = 0; i < files.length; i++) {
          deleteDir(files[i].getAbsolutePath());
        }
        file.delete();
      }
    }
  }


}
