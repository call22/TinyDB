package cn.edu.thssdb.schema;

import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.type.ColumnType;
import javafx.util.Pair;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Table implements Iterable<Row> {
  ReentrantReadWriteLock lock;
  private String databaseName;
  public String tableName;
  public ArrayList<Column> columns;
  public BPlusTree<Entry, Integer> index;
  private int primaryIndex = 0;
  private ArrayList<Integer> primaryIndexList;

  private static final String DATA_EXTENSION = ".data";   // 数据文件后缀名
  private static final String INDEX_EXTENSION = ".idx";   // 索引文件后缀名
  private RandomAccessFile dataFile;                      // 数据文件
  private long freeListPtr = -1;                          // 当前空闲列表区域指针
  private int uniqueID = 0;                               // 默认的主键值（自增） - 无主键时
  private boolean hasPrimaryKey = false;                  // 是否含有主键
  private boolean isMultiPrimaryKey = false;              // 是否是多主键

  public Table(String databaseName, String tableName, Column[] columns) throws IOException {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<>(Arrays.asList(columns));
    primaryIndexList = new ArrayList<>();

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
    if (!hasPrimaryKey) {
      Column tmpPrimaryColumn = new Column("IDX", ColumnType.INT, 1, true, -1);
      this.columns.add(0, tmpPrimaryColumn);
    }
    else if (isMultiPrimaryKey) {
      // TODO: 根据多主键映射函数，修改max-length
      // 多主键会映射为string
      Column tmpPrimaryColumn = new Column("IDX", ColumnType.STRING, 1, true, 32);
      this.columns.add(0, tmpPrimaryColumn);
    }
    else {
      primaryIndex = primaryIndexList.get(0);   //单主键时的索引
    }

    // 创建或读取数据文件
    String dataFileName = databaseName + "_" + tableName + DATA_EXTENSION;
    dataFile = new RandomAccessFile(dataFileName, "rw");
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
    RandomAccessFile indexFile = new RandomAccessFile(indexFileName, "r");
    if (indexFile.length() > 0) {
      try {
        deserialize(indexFileName);
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
    indexFile.close();
  }

  private void recover() {
    // TODO
  }

  /**
   * 插入单行数据
   *
   * @param row 待插入的行
   * @throws IOException
   */
  public void insert(Row row) throws IOException {
    // TODO: 判断是否有NOT_NULL约束
    // end

    // TODO: 判断主键是否重复
    // end

    // 写入单行数据
    if(freeListPtr == -1) {
      dataFile.seek((dataFile.length()));
    }
    else {
      dataFile.seek(freeListPtr);
      long nextPtr = dataFile.readLong();
      dataFile.seek((freeListPtr));
      freeListPtr = nextPtr;
    }
    // TODO: row要存储position吗？
    // end
    dataFile.write(serialize(row));

    // TODO: 插入至索引
    // 插入至索引

    // end
  }

  public void delete() {
    // TODO
  }

  public void update() {
    // TODO
  }

  private void serialize() throws IOException {
    String indexFileName = databaseName + "_" + tableName + "_" + this.columns.get(primaryIndex).getName() +INDEX_EXTENSION;
    RandomAccessFile indexFile = new RandomAccessFile(indexFileName, "w");
    FileOutputStream fileOut = new FileOutputStream(indexFileName);
    ObjectOutputStream oos = new ObjectOutputStream(fileOut);
    ArrayList<Pair<Entry, Integer>> leafArrayList = new ArrayList<>();
    for (Pair<Entry, Integer> leaf : index) {
      leafArrayList.add(leaf);
    }
    oos.writeObject(leafArrayList);
    oos.close();
    fileOut.close();
    indexFile.close();
  }

  private void deserialize(String indexFileName) throws IOException, ClassNotFoundException {
    // 从文件读取所有叶子节点
    FileInputStream fileIn = new FileInputStream(indexFileName);
    ObjectInputStream ois = new ObjectInputStream(fileIn);
    ArrayList<Pair<Entry, Integer>> leafArrayList = (ArrayList<Pair<Entry, Integer>>) ois.readObject();
    // 恢复索引树
    for (Pair<Entry, Integer> leaf : leafArrayList) {
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

  private Row getRowFromFile(int position) throws IOException {
    return new Row();
  }


  private class TableIterator implements Iterator<Row> {
    private final Iterator<Pair<Entry, Integer>> iterator;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      Row row = null;
      try {
        row = getRowFromFile(iterator.next().getValue());
      } catch (IOException e) {
        e.printStackTrace();
      }
      return row;
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }
}
