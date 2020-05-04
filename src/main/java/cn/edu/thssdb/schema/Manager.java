package cn.edu.thssdb.schema;
import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.server.ThssDB;
import cn.edu.thssdb.exception.*;

import cn.edu.thssdb.exception.*;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Manager {
  private HashMap<String, Database> databases;
  private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
  String currentDB;
  private static final String SCHEMA_FILE="schema";
  private static final String DEFAULT_DBNAME="TEST";


  /**
   * 从schema文件恢复总的数据库信息（若无文件则新建默认数据库TEST）
   * @throws IOException 读文件时出错
   */
  public Manager() throws IOException {
    databases = new HashMap<>();
    if(!new File(SCHEMA_FILE).exists()){
      File schema = new File(SCHEMA_FILE);
      FileOutputStream fos= new FileOutputStream(SCHEMA_FILE);
      DataOutputStream dos = new DataOutputStream(fos);
      dos.writeInt(0);
    }
    FileInputStream fis = new FileInputStream(SCHEMA_FILE);
    if(fis!=null){
      DataInputStream dis = new DataInputStream(fis);
      int databaseNum= dis.readInt();
      for(int i=0;i<databaseNum;i++){
        int dbNamelength=dis.readInt();
        byte[] dbname=new byte[dbNamelength];
        dis.read(dbname);

        Database db = new Database(new String(dbname));
        databases.put(db.getName(),db);
        if(i==0){
          currentDB=db.getName();
        }
      }
      if(databases.isEmpty()){
        Database defaultdb=new Database(DEFAULT_DBNAME);
        databases.put(DEFAULT_DBNAME,defaultdb);
        currentDB=DEFAULT_DBNAME;
        updateSchema();
      }
    }
  }

  /**
   * 获取当前数据库名字
   */
  public Database getCurrentDB(){return databases.get(currentDB);}

  /**
   * 当前包含的所有数据库
   */
  public ArrayList<Database> getDatabases(){
    ArrayList<Database> result= new ArrayList<>();
    for(Database db:databases.values()){
      result.add(db);
    }
    return result;

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
      Database deletedDB=databases.get(dbName);
      deletedDB.dropSelf();

      databases.remove(dbName);
      if(dbName==currentDB){
        if(databases.size()>0){
          currentDB=getDatabases().get(0).getName();

        }else{
          Database defaultdb=new Database(DEFAULT_DBNAME);
          databases.put(DEFAULT_DBNAME,defaultdb);
          currentDB=DEFAULT_DBNAME;
          updateSchema();
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
  public void switchDatabase(String dbName) {
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
    FileOutputStream outputStream = new FileOutputStream(SCHEMA_FILE);
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
   * 以下模板暂时没搞懂什么作用...有exception报错..先注释掉了...
   */
//  public static Manager getInstance() {
//    return Manager.ManagerHolder.INSTANCE;
//  }

//  private static class ManagerHolder {
//    private static final Manager INSTANCE = new Manager();
//    private ManagerHolder() {
//
//    }
//  }
}
