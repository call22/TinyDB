package cn.edu.thssdb.schema;

import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.type.ByteManager;
import cn.edu.thssdb.type.ColumnType;
import cn.edu.thssdb.utils.Global;
import com.sun.org.apache.bcel.internal.generic.NEW;
import javafx.util.Pair;

import javax.management.openmbean.KeyAlreadyExistsException;
import java.io.*;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  ReentrantReadWriteLock lock;
  private String databaseName;
  private String tableName;
  private ArrayList<Column> columns;
  private BPlusTree<Entry, Long> index;
  private int primaryIndex = 0;
  private ArrayList<Integer> primaryIndexList;

  private static final String DATA_EXTENSION = ".data";   // 数据文件后缀名
  private static final String INDEX_EXTENSION = ".idx";   // 索引文件后缀名
  private RandomAccessFile dataFile;                      // 数据文件
  private long freeListPtr = -1;                          // 当前空闲列表区域指针
  private int uniqueID = 0;                               // 默认的主键值（自增） - 无主键时
  private boolean hasPrimaryKey = false;                  // 是否含有主键
  private boolean isMultiPrimaryKey = false;              // 是否是多主键

  /**
   * 创建表单
   * @param databaseName 所属数据库
   * @param tableName 表单名称
   * @param columns 属性list*/
  public Table(String databaseName, String tableName, Column[] columns) throws IOException {
    this.databaseName = databaseName;
    this.tableName = tableName.toUpperCase();
    this.columns = new ArrayList<>(Arrays.asList(columns));
    primaryIndexList = new ArrayList<>();
    java.lang.String tablePath= Global.dirPath+databaseName+"/"+tableName;
    if(!new File(tablePath).exists()){
      File Tabledir=new File(tablePath);
      Tabledir.mkdirs();
    }


    // 遍历判断是否含有主键及个数
    int primaryKeyCount = 0;
    int i = 0;
    for (Column column : columns) {
      if(column.isPrimary()) {
        primaryKeyCount++;
        primaryIndexList.add(i);
      }
      i++;
    }
    if (primaryKeyCount > 0) {
      hasPrimaryKey = true;
      if (primaryKeyCount > 1) {
        isMultiPrimaryKey = true;
      }
    }

    // 若无主键 或 多主键 需要创建master列，便于索引
    if (!hasPrimaryKey || isMultiPrimaryKey) {
      Column tmpPrimaryColumn = new Column("IDX", ColumnType.INT, 1, true, -1);
      this.columns.add(0, tmpPrimaryColumn);
    } else {
      primaryIndex = primaryIndexList.get(0);   //单主键时的索引
    }

    // 创建或读取数据文件
    String dataFileName = databaseName + "_" + tableName + DATA_EXTENSION;
    String filePath= Global.dirPath+databaseName+"/"+tableName+"/"+dataFileName;

    dataFile = new RandomAccessFile(filePath, "rw");
    // 初始化空闲列表和uniqueID - 若文件为空，就写入初始值 | 若非空，就读取
    if (dataFile.length() > Long.BYTES + Integer.BYTES) {
      readDataFileHeader();
    }
    else {
      writeDataFileHeader();
    }

    // 构建索引树，若文件存有数据，则反序列化读取
    index = new BPlusTree<>();
    String indexFileName = databaseName + "_" + tableName + "_" + this.columns.get(primaryIndex).getName() +INDEX_EXTENSION;

    RandomAccessFile indexFile = new RandomAccessFile(tablePath+"/"+indexFileName, "rw");
    if (indexFile.length() > 0) {
      try {
        deserialize(tablePath+"/"+indexFileName);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    indexFile.close();
  }
  public RandomAccessFile getRAF(){return dataFile;}
  public ArrayList<Column> getColumns() {
    return columns;
  }
  public String getTableName(){
    return tableName;
  }

  private void recover() {
    // TODO
  }

  /**
   * 插入一行数据
   *
   * @param row 待插入的行
   * @throws IOException 插入错误
   */
  public void insert(Row row) throws IOException {
    // 判断NOT_NULL约束
    ArrayList<Entry> entries = row.getEntries();
    int i = 0;
    for (Column column : columns) {
      if(column.getNull() == 1 && entries.get(i) == null) { // 不能是null约束
        throw new IOException("can not satify NULL constraint");
      }
      i++;
    }

    // 若无主键 或 多主键 在IDX列插入索引值
    if (!hasPrimaryKey) {
      row.getEntries().add(0, new Entry(uniqueID));
      uniqueID++;
    }
    else if (isMultiPrimaryKey) {
      // 插入多键时，使用hashCode唯一映射
      StringBuffer primaryKeyStr = new StringBuffer();
      for (int primaryIndex : primaryIndexList) {
        primaryKeyStr.append(row.getEntries().get(primaryIndex).toString());
      }
      row.getEntries().add(0, new Entry(primaryKeyStr.toString().hashCode()));
    }

    // 判断主键是否重复
    if (index.contains(row.getEntries().get(primaryIndex))) {
      throw new IOException("Key already exists");
    }


    // 写入单行数据, 若空闲列表非空，插入至空闲位置，否则插入至文件末尾
    if(freeListPtr == -1) {
      dataFile.seek((dataFile.length()));
    }
    else {
      dataFile.seek(freeListPtr);
      long nextPtr = dataFile.readLong();
      dataFile.seek((freeListPtr));
      freeListPtr = nextPtr;
    }
    Long indexStartPtr = dataFile.getFilePointer();
    ByteManager byteManager = new ByteManager();
    byte[] bytes = new byte[columns.size() + byteManager.columnsByteSize(columns)];
    Arrays.fill(bytes, (byte) 0);
    ByteBuffer buf = ByteBuffer.wrap(bytes);
    int j = 0;
    for (Entry entry : entries) {
      buf.put( (byte) (entry==null||entry.value == null ? 1 : 0) );
      buf.put(byteManager.entryToBytes(entry, columns.get(j)));
      j++;
    }
    dataFile.write(bytes);

    // 更新索引
    index.put(entries.get(primaryIndex), indexStartPtr);
    // 更新文件头
    writeDataFileHeader();
  }

  /**
   * 查询一行数据
   *
   * @param entry 待查询的主键entry
   * @return 查询到的Row元组
   * @throws IOException
   */
  public Row search(Entry entry) throws IOException{
    Long position = index.get(entry);
    // 定位到文件指定位置
    dataFile.seek(position);
    // 读取原生bytes
    ByteManager byteManager = new ByteManager();
    byte[] bytes = new byte[columns.size() + byteManager.columnsByteSize(columns)];
    dataFile.read(bytes);
    // 从bytes转换为row
    ByteBuffer buf = ByteBuffer.wrap(bytes);
    Entry[] entries = new Entry[columns.size()];
    int i = 0;
    for (Column column : columns) {
      // 获取是否为空信息 与 entry的byte信息
      byte isNull = buf.get();
      byte[] entryByteValue = new byte[byteManager.getColumnTypeByteSize(column)];
      buf.get(entryByteValue);
      if (isNull == 1) {
        entries[i] = null;
      }
      else {
        entries[i] = byteManager.bytesToEntry(entryByteValue, column);
      }
      i++;
    }
    return new Row(entries);
  }

  /**
   * 删除一行数据
   *
   * @param row 待删除的一行数据
   * @throws IOException
   */
  public void delete(Row row) throws IOException {
    Entry primaryEntry = row.getEntries().get(primaryIndex);
    Long position = index.get(primaryEntry);
    // 先删除索引
    index.remove(primaryEntry);
    // 把数据文件覆盖为空闲列表指针
    dataFile.seek(position);
    dataFile.writeLong(freeListPtr);
    freeListPtr = position;
    // 修改文件头
    writeDataFileHeader();
  }

  /**
   * 更新一行数据
   *
   * @param row 新的元组
   * @throws IOException
   */
  public void update(Row row) throws IOException{
    delete(row);
    insert(row);
  }

  /**
   * 序列化索引树
   *
   * @throws IOException
   */
  public void serialize() throws IOException {
    String indexFileName = databaseName + "_" + tableName + "_" + this.columns.get(primaryIndex).getName() +INDEX_EXTENSION;
    String idxfilePath= Global.dirPath+databaseName+"/"+tableName+"/"+indexFileName;

    RandomAccessFile indexFile = new RandomAccessFile(idxfilePath, "rw");
    FileOutputStream fileOut = new FileOutputStream(idxfilePath);
    ObjectOutputStream oos = new ObjectOutputStream(fileOut);
    ArrayList<Pair<Entry, Long>> leafArrayList = new ArrayList<>();
    for (Pair<Entry, Long> leaf : index) {
      leafArrayList.add(leaf);
    }
    oos.writeObject(leafArrayList);
    oos.close();
    fileOut.close();
    indexFile.close();
  }

  /**
   * 删除这个表对应的文件
   *
   * @throws IOException
   */
  public void drop() throws IOException {
    dataFile.close();;
    String tablePath=Global.dirPath+databaseName+"/"+tableName;
    File tableDir=new File(tablePath);
    String dataFileName = databaseName + "_" + tableName + DATA_EXTENSION;
    String datafilePath=tablePath+"/"+dataFileName;

    File dFile= new File(datafilePath);
    dFile.delete();
    String idxFileName = databaseName + "_" + tableName + "_" + this.columns.get(primaryIndex).getName() +INDEX_EXTENSION;
    String idxfilePath= tablePath+"/"+idxFileName;

    File idxFile= new File( idxfilePath);
    idxFile.delete();
    tableDir.delete();

  }

  /**
   * 反序列化索引树
   *
   * @param indexFileName 索引树所在文件名
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void deserialize(String indexFileName) throws IOException, ClassNotFoundException {
    // 从文件读取所有叶子节点
    FileInputStream fileIn = new FileInputStream(indexFileName);
    ObjectInputStream ois = new ObjectInputStream(fileIn);
    ArrayList<Pair<Entry, Long>> leafArrayList = (ArrayList<Pair<Entry, Long>>) ois.readObject();
    // 恢复索引树
    for (Pair<Entry, Long> leaf : leafArrayList) {
      index.put(leaf.getKey(), leaf.getValue());
    }
    ois.close();
    fileIn.close();
  }

  /**
   * 读取数据文件头的 空闲列表指针 和 当前uniqueID信息
   *
   * @throws IOException
   */
  private void readDataFileHeader() throws IOException {
    dataFile.seek(0);
    freeListPtr = dataFile.readLong();
    uniqueID = dataFile.readInt();
  }

  /**
   * 写入数据文件头的 空闲列表指针 和 当前uniqueID信息
   *
   * @throws IOException
   */
  private void writeDataFileHeader() throws IOException {
    dataFile.seek(0);
    dataFile.writeLong(freeListPtr);
    dataFile.writeInt(uniqueID);
  }

  /**
   * 表迭代器，遍历索引树的索引值，读取文件中row的数据
   */
  private class TableIterator implements Iterator<Row> {
    private final Iterator<Pair<Entry, Long>> iterator;
    private Table table;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
      this.table = table;
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      try {
        return table.search(iterator.next().getKey());
      } catch (IOException e) {
        e.printStackTrace();
        System.exit(0);
      }
      return null;
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }


  /**
   * 添加列
   *
   * @param column 添加的列
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void alterADD(Column column) throws IOException {
    //已存在这一列
    if(columns.contains(column)){
      throw new KeyAlreadyExistsException();
    }
    ByteManager byteManager=new ByteManager();

    String tablePath=Global.dirPath+File.separator+databaseName+File.separator+tableName;
    String newfilename="temp.data";
    String oldfilename=databaseName + "_" + tableName + DATA_EXTENSION;
    freeListPtr=-1;
    File newDataFile=new File(tablePath+File.separator+newfilename);
    RandomAccessFile newAccess=new RandomAccessFile(tablePath+File.separator+newfilename,"rw");
    newAccess.writeLong(-1);
    newAccess.writeInt(uniqueID);
    for (Pair<Entry, Long> leaf : index) {
      long ptr=newAccess.getFilePointer();

      byte[] bytes = new byte[columns.size() + byteManager.columnsByteSize(columns)+1+byteManager.getColumnTypeByteSize(column)];
      Arrays.fill(bytes, (byte) 0);
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      byte[] oldbytes = new byte[columns.size() + byteManager.columnsByteSize(columns)];
      dataFile.seek(leaf.getValue());
      dataFile.read(oldbytes);

      buf.put(oldbytes);
      buf.put((byte)1);//null
      for(int k=0;k<byteManager.getColumnTypeByteSize(column);k++){
        buf.put((byte)0);
      }
      newAccess.write(bytes);
      // 更新索引
      index.update(leaf.getKey(), ptr);
    }
    freeListPtr=-1;
    columns.add(column);
    newAccess.close();
    dataFile.close();
    dataFile=newAccess;

    File oldFile=new File(tablePath+File.separator+oldfilename);
    System.gc();
    boolean deleted=oldFile.delete();
    File newDFile=new File(tablePath+File.separator+oldfilename);
    boolean rename=newDataFile.renameTo(newDFile);
    if(deleted){
      System.out.print("删除成功\n");
    }else{
      System.out.print("删除失败\n");
    }
    if(rename){
      System.out.print("重命名成功\n");
      //serialize();
      dataFile=new RandomAccessFile(tablePath+File.separator+oldfilename,"rw");
    }else{
      System.out.print("重命名错误\n");
    }
  }

  /**
   * 删除列
   *
   * @param columnName 删除的列名(暂不支持删除primary)
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public void alterDrop(String columnName) throws IOException {
    //没有这一列或者这一列为主键
    boolean flag=false;
    for(Column c :getColumns()){
      if(c.getName().equals(columnName)){
        flag=!c.isPrimary();
      }
    }
    if(!flag){
      throw new KeyNotExistException();
    }

    ByteManager byteManager=new ByteManager();
    Column deletedColumn = null;
    int idx=0;
    for(Column c:columns){

      if(c.getName().equals(columnName)){
        deletedColumn=c;
        break;
      }
      idx++;
    }

    String tablePath=Global.dirPath+File.separator+databaseName+File.separator+tableName;
    String newfilename="temp.data";
    String oldfilename=databaseName + "_" + tableName + DATA_EXTENSION;
    freeListPtr=-1;
    File newDataFile=new File(tablePath+File.separator+newfilename);
    RandomAccessFile newAccess=new RandomAccessFile(tablePath+File.separator+newfilename,"rw");
    newAccess.writeLong(-1);
    newAccess.writeInt(uniqueID);
    for(Row r:this){

      long ptr=newAccess.getFilePointer();
      byte[] bytes = new byte[columns.size() + byteManager.columnsByteSize(columns)-1-byteManager.getColumnTypeByteSize(deletedColumn)];
      Arrays.fill(bytes, (byte) 0);
      ByteBuffer buf = ByteBuffer.wrap(bytes);
      ArrayList<Entry> entries = r.getEntries();
      int i = 0;
      for (Entry entry : entries) {
        if(i!=idx){
          buf.put( (byte) (entry==null||entry.value == null ? 1 : 0) );
          buf.put(byteManager.entryToBytes(entry, columns.get(i)));
        }
        i++;
      }
      newAccess.write(bytes);

      // 更新索引
      index.update(entries.get(primaryIndex), ptr);
    }
    freeListPtr=-1;
    columns.remove(deletedColumn);
    newAccess.close();;
    dataFile.close();;
    File oldFile=new File(tablePath+File.separator+oldfilename);
    System.gc();
    oldFile.delete();
    File newDFile=new File(tablePath+File.separator+oldfilename);
    newDataFile.renameTo(newDFile);
    //serialize();
    dataFile=new RandomAccessFile(tablePath+File.separator+oldfilename,"rw");
  }

  @Override
  public String toString(){
    return this.tableName + " | " + this.columns.size();
  }
}


