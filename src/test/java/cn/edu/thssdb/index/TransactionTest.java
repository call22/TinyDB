package cn.edu.thssdb.index;

import cn.edu.thssdb.query.statement.*;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.type.ColumnType;
import javafx.scene.input.Mnemonic;

import java.io.IOException;
import java.util.ArrayList;

public class TransactionTest {
  static Manager manager;
  static {
    try {
      manager = new Manager();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
  ArrayList<Column> testColumnList = new ArrayList<>();
  ArrayList<String> columnsName = new ArrayList<>();
  ArrayList<String> rowValue = new ArrayList<>();

  public TransactionTest() throws IOException {
    testColumnList.add(new Column("ID", ColumnType.INT, true, true, 0));
    testColumnList.add(new Column("NAME", ColumnType.STRING, false, true, 16));
    CreateTableStatement createTableStatement = new CreateTableStatement("INSTRUCTOR", testColumnList);
    createTableStatement.execute(manager);
    columnsName.add("ID");
    columnsName.add("NAME");
    rowValue.add("1");
    rowValue.add("'XiaoLi'");
  }

  public void transactionThread() {
    // start transaction
    System.out.println("start transaction...");
    TransactionStatement transactionStatement = new TransactionStatement();
    transactionStatement.execute(manager);

    System.out.println("insert: require write lock");
    InsertTableStatement insertTableStatement = new InsertTableStatement("INSTRUCTOR", columnsName, rowValue);
    insertTableStatement.execute(manager);
    try {
      Thread.sleep(5000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    // thread1 commit, thread2 可以获取锁来查询
    CommitStatement commitStatement = new CommitStatement();
    commitStatement.execute(manager);
    System.out.println("commit: release write locks");
  }
  public void searchThread() {
    System.out.println("search: require read lock");
    try {
      manager.getCurrentDB().selectTable("INSTRUCTOR").search(new Entry(1));
    } catch (IOException e) {
      e.printStackTrace();
    }
    System.out.println("release read lock");
    System.out.println("result table: ");
    printTestTable(manager.getCurrentDB().selectTable("INSTRUCTOR"));
  }

  public static void main(String[] args) throws InterruptedException, IOException {
    TransactionTest test = new TransactionTest();
    System.out.println("init table: ");
    printTestTable(manager.getCurrentDB().selectTable("INSTRUCTOR"));
    // thread1 starttransaction with insert
    Thread thread1 = new Thread(test::transactionThread);
    // 查询一条记录，需要在等待至thread1 commit
    Thread thread2 = new Thread(test::searchThread);
    thread1.start();
    Thread.sleep(1000);
    thread2.start();
  }
  public static void printTestTable(Table testTable) {
    for(Column c:testTable.getColumns()){
      System.out.print(c.toString()+"\n");
    }
    for (Row row : testTable) {
      System.out.print(row.toString() + '\n');
    }
  }
}
