package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.type.ColumnType;
import cn.edu.thssdb.utils.Global;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Database {
  private static final String SCHEMA_EXTENSION = ".meta";

  private String name;
  private HashMap<String, Table> tables;
  ReentrantReadWriteLock lock;

  /**
   * 和getDatabases格式变为一致: Table[] ==> LinkedList<Table>*/
  public LinkedList<Table> getTables(){
    return new LinkedList<>(tables.values());
  }

  public String getName(){return name;}

  /**
   * 根据数据库名称，创建、或从.meta文件恢复数据库
   *
   * @param name 待创建的数据库的名字
   * @throws IOException 读取文件失败
   */

  public Database(String name) throws IOException {
    this.name = name;
    this.tables = new HashMap<>();
    this.lock = new ReentrantReadWriteLock();
    recover();
  }

  /**
   * 将数据库信息（名字|表数量|表信息）持久化到.meta文件中
   *
   * @throws IOException 写入文件失败
   */

  public void persist() throws IOException {
    String fileDir=Global.dirPath+name;

    if(!new File(fileDir).exists()){
      File FileDir=new File(fileDir);
      FileDir.mkdirs();
    }

    String filename = Global.dirPath+name+"/"+name + SCHEMA_EXTENSION;
    if(!new File(filename).exists()){
      new File(filename);
    }
    FileOutputStream fos = new FileOutputStream(filename);
    DataOutputStream dos = new DataOutputStream(fos);
    dos.writeInt(tables.size());
    for(Table table : tables.values()){
      dos.writeInt(toSchemaBytes(table).length);
      dos.write(toSchemaBytes(table));
    }
    fos.close();
    dos.close();
    for (Table t :tables.values()){
      t.serializeIndex();
    }
  }

  /**
   * 将某个表的信息（表名|列信息）转化成可写入.meta文件的二进制格式
   *
   * @param table 待转化的表
   * @return 转化得的二进制串
   */

  private byte[] toSchemaBytes(Table table) {
    ByteBuffer buffer = ByteBuffer.allocate(1024);
    // buffer.put("TABLE".getBytes()); // Magic Number
    buffer.putInt(table.getTableName().length());
    buffer.put(table.getTableName().getBytes());
    // buffer.putInt(uniqueID);
    buffer.putInt(table.getColumns().size());
    for (Column column : table.getColumns()) {
      buffer.putInt(column.getType().ordinal());
      buffer.putInt(column.getName().length());
      buffer.put(column.getName().getBytes());
      buffer.putInt(column.isPrimary()?1:0);
      buffer.putInt(column.getNull()?1:0);

      buffer.putInt(column.getMaxLength());
    }
    return buffer.array();
  }

  /**
   *  将数据库信息 (名字|表数量) 转化为字符串*/
  @Override
  public String toString(){
    return this.name + " | " + this.tables.size();
  }

  /**
   * 创建新的表
   *
   * @param name 新的表名
   * @param columns 新的表所包含的列
   * @return
   * @throws IOException 将新表信息写入文件时出错
   */

  public void create(String name, Column[] columns) throws IOException {
    if(!tables.containsKey(name)){
      Table newTabel= new Table(this.name,name,columns);
      tables.put(newTabel.getTableName(),newTabel);
//      persist();

    }else{
      throw new DuplicateKeyException();

    }
  }

  /**
   * 删除某个表
   *
   * @param tableName 待删除的表名
   * @return
   * @throws IOException 更新信息写入文件时出错
   */

  public void drop(String tableName) throws IOException {
    if(tables.containsKey(tableName)){
//      Table table=tables.get(tableName);
//      table.drop();

      tables.remove(tableName);
//      persist();

    }else{
      throw new KeyNotExistException();
    }
  }

  /**
   * 删除tablename中的column
   *
   * @param tablename 待修改的表名
   * @param column 待删除的列
   * @return
   * @throws IOException 将新表信息写入文件时出错
   */
//  public void alterTableDrop(String tablename,String column) throws IOException {
//    //存在此table
//    Table t=tables.get(tablename);
//    if(t==null){
//      throw new KeyNotExistException();
//    }
//
//    t.alterDrop(column);
//    //persist();
//
//
//  }

  /**
   * 向tablename添加column
   *
   * @param tablename 待修改的表名
   * @param column 待添加的列
   * @param
   * @return
   * @throws IOException 将新表信息写入文件时出错
   */
//  public void alterTableAdd(String tablename,Column column) throws IOException {
//    Table t=tables.get(tablename);
//    //存在此table
//    if(t==null){
//      throw new KeyNotExistException();
//    }
//    t.alterADD(column);
//    persist();
//  }

  /**
   * 从数据库中选择表单
   * @param name 表单名称*/
  public Table selectTable(String name) {
    // TODO
    return tables.get(name);
  }

  /**
   * 从.meta恢复数据库信息
   *
   * @throws IOException 读文件时出错
   */
  private void recover() throws IOException {
    String fileDir=Global.dirPath+name;
//    if(!new File(fileDir).exists()){
//      File FileDir=new File(fileDir);
//      FileDir.mkdirs();
//    }
    String filename = fileDir+"/"+name + SCHEMA_EXTENSION;
    if(new File(filename).exists()){
      FileInputStream fis = new FileInputStream(filename);
      DataInputStream dis = new DataInputStream(fis);
      int tableNum = dis.readInt();
      for(int i=0;i<tableNum;i++){
        int tableSchemaLength=dis.readInt();
        byte[] tableSchema = new byte[tableSchemaLength];
        dis.read(tableSchema);
        Table t= TableFromSchema(tableSchema);
        tables.put(t.getTableName(),t);
      }
      dis.close();
      fis.close();

    }
//    else{
//      File metaFile = new File(filename);
//      persist();
//    }
  }

  /**
   * 从.meta文件中读取到的二进制串恢复表信息
   *
   * @param tableSchema 包含了表信息的二进制串
   * @return 恢复得到的表
   * @throws IOException 写入文件时出错
   */
  private Table TableFromSchema(byte[] tableSchema) throws IOException {
    ByteBuffer buffer = ByteBuffer.wrap(tableSchema);
    int tableNameSize = buffer.getInt();
    byte[] byteName = new byte[tableNameSize];
    buffer.get(byteName);
    String tableName = new String(byteName);
    // int uniqueID = buffer.getInt();
    int columnSize = buffer.getInt();
    Column[] columns = new Column[columnSize];
    for (int i = 0; i < columnSize; i++) {
      ColumnType type=ColumnType.INT;

      int t=buffer.getInt();
      switch (t){
        case 0:
          type=ColumnType.INT;
          break;
        case 1:
          type=ColumnType.LONG;
          break;
        case 2:
          type=ColumnType.FLOAT;
          break;
        case 3:
          type=ColumnType.DOUBLE;
          break;
        case 4:
          type=ColumnType.STRING;
          break;

      }
      int nameSize = buffer.getInt();
      byte[] columnName = new byte[nameSize];
      buffer.get(columnName);
      boolean primary = buffer.getInt()==1;
      boolean notnull= buffer.getInt() == 1;
      int maxlength = buffer.getInt();
      columns[i] = new Column(new String(columnName),type,primary,notnull,maxlength);
    }

    Table resultT=new Table(name,tableName,columns);
    return resultT;
  }

  /**
   * 退出该数据库
   *
   * @return
   * @throws IOException 将数据库信息写入文件时出错
   */
  public void quit() throws IOException {
//    for(Table t:tables.values()){
//      t.getRAF().close();;
//      t.serialize();
//    }
    persist();
  }

  /**
   * 删除数据库（自身），包括删除包含的表、及相关的文件
   *
   * @return
   * @throws IOException 删除文件时出错
   */
//  public void dropSelf() throws IOException {
//    for(Table t :tables.values()){
//      t.drop();
//    }
//    String filpath = Global.dirPath+"/"+name;
//    String metaFile=filpath+"/"+name+SCHEMA_EXTENSION;
//    File deletedMeta=new File(metaFile);
//    deletedMeta.delete();
//
//    File deletedFile= new File(filpath);
//    deletedFile.delete();
//  }

}
