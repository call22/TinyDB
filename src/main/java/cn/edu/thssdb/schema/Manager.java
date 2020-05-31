package cn.edu.thssdb.schema;
import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.server.ThssDB;
import cn.edu.thssdb.exception.*;

import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
  public static Long id = 0L;
  /**存储用户信息的hash
   * 存储当前连接情况*/
  private static HashMap<String, String> userInfo;
  private static ArrayList<Long> sessionIds;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
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
      FileInputStream fis = new FileInputStream(filename);
      DataInputStream dis = new DataInputStream(fis);
      int databaseNum = dis.readInt();
      for (int i = 0; i < databaseNum; i++) {
        int dbNamelength = dis.readInt();
        byte[] dbname = new byte[dbNamelength];
        dis.read(dbname);

        Database db = new Database(new String(dbname));
        databases.put(db.getName(), db);
        if (i == 0) {
          currentDB = db.getName();
        }
      }
      if (databases.isEmpty()) {
        Database defaultdb = new Database(DEFAULT_DBNAME);
        databases.put(DEFAULT_DBNAME, defaultdb);
        currentDB = DEFAULT_DBNAME;
        updateSchema();
      }
      // 反序列化
      FileInputStream fileInputStream = new FileInputStream(Global.infoPath);
      ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
      userInfo = (HashMap<String, String>) objectInputStream.readObject();
      if(userInfo == null){
        userInfo = new HashMap<String, String>();
        userInfo.put("user", "password");
      }
      sessionIds = new ArrayList<>();
    }catch (IOException | ClassNotFoundException e){
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
      //updateSchema();

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
      Database deletedDB=databases.get(dbName);
      deletedDB.dropSelf();

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
      //updateSchema();
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
    try {
      FileOutputStream outputStream = new FileOutputStream(Global.infoPath);
      ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
      objectOutputStream.writeObject(userInfo);
      objectOutputStream.close();
    }catch (FileNotFoundException e){
      throw new IOException(e.getMessage());
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

  /**
   * 检查username, password是否正确*/
  public boolean checkInfo(String username, String password){
    return userInfo.containsKey(username) && userInfo.get(username).equals(password);
  }

  public boolean checkSessionId(long id){
    return sessionIds.contains(id);
  }

  public void setSessionIds(long id) {
    sessionIds.add(id);
  }

  public void dropSessionIds(long id){
    sessionIds.remove(id);
  }
}
