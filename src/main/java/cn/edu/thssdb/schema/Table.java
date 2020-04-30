package cn.edu.thssdb.schema;

import cn.edu.thssdb.index.BPlusTree;
import cn.edu.thssdb.type.ColumnType;
import javafx.util.Pair;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
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

  private static final String DATA_EXTENSION = ".data";   // 数据文件后缀名
  private static final String INDEX_EXTENSION = ".idx";   // 索引文件后缀名
  private RandomAccessFile dataFile;                      // 数据文件
  private RandomAccessFile indexFile;                     // 索引文件
  private long freeListPtr = -1;                          // 当前空闲列表区域指针
  private boolean hasPrimaryKey = false;                  // 是否含有主键
  private boolean isMultiPrimaryKey = false;              // 是否是多主键

  public Table(String databaseName, String tableName, Column[] columns) throws FileNotFoundException {
    this.databaseName = databaseName;
    this.tableName = tableName;
    this.columns = new ArrayList<Column>(Arrays.asList(columns));
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

    // 创建数据文件
    String dataFileName = databaseName + "_" + tableName + DATA_EXTENSION;
    dataFile = new RandomAccessFile(dataFileName, "rw");

    // TODO: 初始化空闲列表？

    // end

    // 创建索引文件
    String indexFileName = databaseName + "_" + tableName + INDEX_EXTENSION;
    indexFile = new RandomAccessFile(indexFileName, "rw");

    // TODO: 存储索引树，如何？

    // end
  }

  private void recover() {
    // TODO
  }

  public void insert() {
    // TODO
  }

  public void delete() {
    // TODO
  }

  public void update() {
    // TODO
  }

  private void serialize() {
    // TODO
  }

  private ArrayList<Row> deserialize() {
    // TODO
    return null;
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
