package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.type.ColumnType;

import java.io.IOException;

public class LockTest {
  Column[] testColumnList = {
          new Column("id", ColumnType.INT, true, true, 0),
          new Column("name", ColumnType.STRING, false, true, 16)
  };
  Table testTable = new Table("Test", "instructor", testColumnList);

  public LockTest() throws IOException {
  }

  public void insertThread() {
    try {
      Entry[] entries2 = {new Entry(2), new Entry("XiaoLi")};
      System.out.println(Thread.currentThread().getName() + " write start ");
      testTable.lock.writeLock().lock();
      try {
        testTable.insert(new Row(entries2));
      } catch (IOException e) {
        e.printStackTrace();
      }
      Thread.sleep(5000);
      System.out.println(Thread.currentThread().getName() + " write end ");
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      testTable.lock.writeLock().unlock();
    }
  }
  public void searchThread() {
    System.out.println(Thread.currentThread().getName() + " read start ");
      try {
        testTable.search(new Entry(2));
      } catch (IOException e) {
        e.printStackTrace();
      }
    System.out.println(Thread.currentThread().getName() + " read end ");
    printTestTable();
  }


  public static void main(String[] args) throws InterruptedException, IOException {
    LockTest readWriteLock = new LockTest();
    readWriteLock.printTestTable();
    Thread thread1 = new Thread(readWriteLock::insertThread);
    Thread thread2 = new Thread(readWriteLock::searchThread);
    thread1.start();
    Thread.sleep(1000);
    thread2.start();
  }
    public void printTestTable() {
    for(Column c:testTable.getColumns()){
      System.out.print(c.toString()+"\n");
    }
    for (Row row : testTable) {
      System.out.print(row.toString() + '\n');
    }
  }

}
