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
  public BPlusTree<Entry, Row> index;
  private int primaryIndex = 0;
  private ArrayList<Integer> primaryIndexList;

  private String DATA_EXTENSION = ".data";                // 数据文件后缀名
  private String dataFileName;                            // 数据文件名

  private RandomAccessFile dataFile;                      // 数据文件
  private int uniqueID = 0;                               // 默认的主键值 - 无主键时
  private boolean hasPrimaryKey = false;                  // 是否含有主键
  private boolean isMultiPrimaryKey = false;              // 是否是多主键
//  private boolean hasChanged = false;                      // 创建后是否被修改

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
      Column tmpPrimaryColumn = new Column("IDX", ColumnType.INT, 1, true, 0);
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
    dataFileName = databaseName + "_" + tableName + DATA_EXTENSION;
    dataFile = new RandomAccessFile(dataFileName, "rw");

    // 构建索引树, 若文件存有数据，就反序列化读取
    index = new BPlusTree<>();
    if (dataFile.length() > 0) {
      try {
        ArrayList<Row> rowArrayList = deserialize();
        for (Row row : rowArrayList) {
          this.insert(row);
        }
//        hasChanged = false;
      } catch (ClassNotFoundException e) {
        e.printStackTrace();
      }
    }
  }

//  /**
//   * 析构函数，若table有改变，则重新写入数据文件
//   *
//   * @throws IOException
//   */
//  @Override
//  protected void finalize() throws IOException{
//    System.out.println("finalize被执行");
//    if (hasChanged) {
//      this.serialize();
//    }
//  }

  private void recover() {
    // TODO
  }


  /**
   * 获取一行数据
   *
   * @param entry 主键值
   * @return
   */
  public Row search(Entry entry) {
    return index.get(entry);
  }

  /**
   * 插入一行数据
   *
   * @param row 待插入的元祖
   */
  public void insert(Row row){
    // TODO: 判断是否有NOT_NULL约束
    // end

    // TODO: 无主键时更新uniqueID, 多主键时先把主键们映射到一个值
    // end

    // TODO: 判断主键是否重复
    // end

    index.put(row.getEntries().get(primaryIndex), row);
//    hasChanged = true;
  }

  /**
   * 删除一行数据
   *
   * @param entry 待删除的key
   */
  public void delete(Entry entry) {
    index.remove(entry);
//    hasChanged = true;
  }

  /**
   * 更新一行数据
   *
   * @param row 新的元组
   */
  public void update(Row row) {
    index.update(row.getEntries().get(primaryIndex), row);
//    hasChanged = true;
  }

  /**
   * 序列化所有行数据
   *
   * @throws IOException
   */
  public void serialize() throws IOException {
    FileOutputStream fileOut = new FileOutputStream(dataFileName);
    ObjectOutputStream oos = new ObjectOutputStream(fileOut);
    ArrayList<Row> rowArrayList = new ArrayList<>();
    for (Pair<Entry, Row> entryRowPair : index) {
      rowArrayList.add(entryRowPair.getValue());
    }
    oos.writeObject(rowArrayList);
    oos.close();
    fileOut.close();
  }

  /**
   * 反序列化所有行数据
   *
   * @return ArrayList<Row> 行列表
   * @throws IOException
   * @throws ClassNotFoundException
   */
  public ArrayList<Row> deserialize() throws IOException, ClassNotFoundException{
    FileInputStream fileIn = new FileInputStream(dataFileName);
    ObjectInputStream ois = new ObjectInputStream(fileIn);
    return (ArrayList<Row>) ois.readObject();
  }

  private class TableIterator implements Iterator<Row> {
    private Iterator<Pair<Entry, Row>> iterator;

    TableIterator(Table table) {
      this.iterator = table.index.iterator();
    }

    @Override
    public boolean hasNext() {
      return iterator.hasNext();
    }

    @Override
    public Row next() {
      return iterator.next().getValue();
    }
  }

  @Override
  public Iterator<Row> iterator() {
    return new TableIterator(this);
  }
}
