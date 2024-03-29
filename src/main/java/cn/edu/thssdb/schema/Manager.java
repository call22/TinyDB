package cn.edu.thssdb.schema;
import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.server.ThssDB;
import cn.edu.thssdb.exception.*;

import cn.edu.thssdb.utils.Global;
import org.apache.thrift.TException;
import cn.edu.thssdb.utils.LogManager;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
//  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  String currentDB;

  private static final String SCHEMA_FILE="schema";
  private static final String DEFAULT_DBNAME="TEST";


  /**
   * 从schema文件恢复总的数据库信息（若无文件则新建默认数据库TEST）
   * 从userInfo.info文件中恢复当前用户信息
   */
  public Manager(){     // 删除IOException,改为try, catch
    databases = new HashMap<>();

    String filename=Global.dirPath+SCHEMA_FILE;
    try {
      if (!new File(filename).exists()) {
        File DBS = new File(Global.dirPath);
        DBS.mkdirs();
        File schema = new File(filename);
        //schema.mkdirs();
        FileOutputStream fos = new FileOutputStream(filename);
        DataOutputStream dos = new DataOutputStream(fos);
        dos.writeInt(0);
      }
      if(new File(filename).exists()) {

        FileInputStream fis = new FileInputStream(filename);
        DataInputStream dis = new DataInputStream(fis);
        int databaseNum = dis.readInt();
        for (int i = 0; i < databaseNum; i++) {
          int dbNamelength = dis.readInt();
          byte[] dbname = new byte[dbNamelength];
          dis.read(dbname);

          Database db = new Database(new String(dbname));
          databases.put(db.getName(), db);
          if(i==0){
            currentDB=db.getName();
          }
        }
        dis.close();
        fis.close();

      }

      if (databases.isEmpty()) {
        Database defaultdb = new Database(DEFAULT_DBNAME);
        databases.put(DEFAULT_DBNAME, defaultdb);
        currentDB = DEFAULT_DBNAME;
        updateSchema();
      }
      for(Database db:databases.values()){
        String fileDir=Global.dirPath+db.getName();

        String logname = fileDir+"/"+db.getName() + ".log";
        File logFile=new File(logname);
        if(logFile.exists()&&logFile.length()>0){
          LogManager.recover(this,db);
        }
      }
      persist();
    }catch (IOException e){
      System.err.println(e.getMessage());
    }
  }

  /**
   * 获取当前数据库名字
   */
  public Database getCurrentDB(){return databases.get(currentDB);}

  /**
   * 当前包含的所有数据库
   * '''将ArrayList改为LinkedList,相对速度快点'''
   */
  public LinkedList<Database> getDatabases(){
    LinkedList<Database> result= new LinkedList<>();
    for(Database db:databases.values()){
      result.add(db);
    }
    return result;
  }

  /**
   * 按照数据库名称获取db, 没有则返回null
   * @param databaseName 想获取的数据库名称*/
  public Database selectDatabase(String databaseName){
    if(databases.containsKey(databaseName)){
      return databases.get(databaseName);
    }
    return null;
  }

  /**
   * 创建新数据库
   * @param dbname 新的数据库名
   * @throws IOException 将新表信息写入文件时出错
   * @throws DuplicateKeyException 此名字的数据库已存在
   */
  public void createDatabaseIfNotExists(String dbname) throws IOException {
    if(!databases.containsKey(dbname)){
      Database newdb=new Database(dbname);
      databases.put(dbname,newdb);
      updateSchema();

    }else{
      throw new DuplicateKeyException();
    }

  }

  /**
   * 删除数据库
   * @param dbName 待删除的数据库名
   * @throws IOException 更新信息文件时出错
   * @throws KeyNotExistException 此名字的数据库不存在
   */
  public void deleteDatabase(String dbName) throws IOException {
    if(databases.containsKey(dbName)){
//      Database deletedDB=databases.get(dbName);
//      deletedDB.dropSelf();

      databases.remove(dbName);
      if(dbName.equals(currentDB)){
        if(databases.size()>0){
          currentDB=getDatabases().get(0).getName();

        }else{
          Database defaultdb=new Database(DEFAULT_DBNAME);
          databases.put(DEFAULT_DBNAME,defaultdb);
          currentDB=DEFAULT_DBNAME;
//          updateSchema();
        }
      }
      updateSchema();
    }else {
      throw new KeyNotExistException();
    }
  }

  /**
   * 切换数据库
   * @param dbName 切换到的数据库名
   * @throws KeyNotExistException 此名字的数据库不存在
   */
  public void switchDatabase(String dbName) throws IOException {
    if(databases.containsKey(dbName)){
      databases.get(currentDB).quit();
      currentDB=dbName;
    }else{
      throw new KeyNotExistException();
    }
  }
  public void switchTempDatabase(String dbName)  {
    if(databases.containsKey(dbName)){
      currentDB=dbName;
    }else{
      throw new KeyNotExistException();
    }
  }
  /**
   * 更新schema文件中的数据库信息
   * @throws KeyNotExistException 此名字的数据库不存在
   */
  private void updateSchema() throws IOException {
    String filename=Global.dirPath+SCHEMA_FILE;

    FileOutputStream outputStream = new FileOutputStream(filename);
    DataOutputStream out = new DataOutputStream(outputStream);
    out.writeInt(databases.size());
    for (Database dataBase : databases.values()) {
      out.writeInt(dataBase.getName().length());
      out.write(dataBase.getName().getBytes());
    }
    out.close();
    outputStream.close();
  }

  /**
   * 离开schema
   * 序列化用户信息*/
  public void quit() throws IOException {
    for(Database d:databases.values()){
      d.quit();
    }
    updateSchema();
    persist();
  }
  /**
   * 持久化信息
   * 内存中保有什么信息？
   * */

  public void persist() throws IOException {
    // 先删除该删除的文件夹
    ArrayList<String> dbnames=new ArrayList<>();
    for(Database db :databases.values()){
      dbnames.add(db.getName());
    }
    String dirPath=Global.dirPath;
    File file = new File(dirPath);
    File[] files = file.listFiles();
    for(File f :files){
      if(!f.isFile() && !dbnames.contains(f.getName())){
        deleteDir(f.getPath());
      }
    }
    // 将每个数据库及其包含的表持久化
    for(Database db :databases.values()){
      db.persist();
      LogManager.deleteLogs(db);
    }

  }

  /**
   * 迭代删除文件夹
   * @param dirPath 文件夹路径
   */
  public static void deleteDir(String dirPath) {
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


  /**
   * 多个用户连接时使用, 获取新的用户
   */
  public static Manager getInstance() {
    return Manager.ManagerHolder.INSTANCE;
  }

  private static class ManagerHolder{
    private static final Manager INSTANCE = new Manager();
    private ManagerHolder() {

    }
  }
}
