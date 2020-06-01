//package cn.edu.thssdb.index;
//
//import cn.edu.thssdb.schema.Entry;
//import cn.edu.thssdb.schema.Row;
//import cn.edu.thssdb.schema.Table;
//import cn.edu.thssdb.schema.Column;
//import cn.edu.thssdb.type.ColumnType;
//import javafx.util.Pair;
//import org.junit.Test;
//
//import java.io.IOException;
//
//public class StorageTest {
//  Column[] testColumnList = {
//          new Column("id", ColumnType.INT, true, true, 0),
//          new Column("name", ColumnType.STRING, false, true, 16)
//  };
//  Table testTable;
//
//  @Test
//  public void setup() throws IOException {
//    // 测试初始化与写入文件
//    testTable = new Table("Test", "instructor", testColumnList);
//    for (int i = 0; i < 5; i++) {
//      Entry[] entries = {new Entry(i), new Entry("XiaoLi")};
//      testTable.insert(new Row(entries));
//    }
//    testTable.serialize();
//    System.out.println("测试初始化：");
//    printTestTable();
//    testDelete();
//    testUpdate();
//    testSearch();
//    testTable.serialize();
//    testTable.getRAF().close();;//为测试alter暂时添加。重建Table需要先关闭原有文件流，否则后续alter时文件会删除出错
//    testRestore();
//
//    testAlterAdd();
//    testAlterDrop();
//    System.out.println("测试修改数据，更新id=3的age为25: ");
//    Entry[] entries = {new Entry(3), new Entry(25)};
//    testTable.update(new Row(entries));
//    printTestTable();
//
//  }
//  public void testAlterAdd() throws IOException {
//    // 测试alterADD(age)：
//    System.out.println("测试alterADD(age):");
//    testTable.alterADD(new Column("age",ColumnType.INT,false,false,0));
//
//    //testTable.alterADD("age",ColumnType.INT);
//    printTestTable();
//  }
//  public void testAlterDrop() throws IOException {
//    // 测试alterADD(age)：
//    System.out.println("测试alterDROP(name):");
//    testTable.alterDrop("name");
//
//    printTestTable();
//  }
//
//  public void testSearch() throws IOException{
//    // 测试读取数据，读取id为3的数据
//    System.out.println("读取id=3的数据：\n" + testTable.search(new Entry(3)));
//  }
//
//  public void testDelete() throws IOException {
//    // 测试删除数据，删除id为2
//    System.out.println("测试删除id=2：");
//    Entry[] entries = {new Entry(2), new Entry("XiaoLi")};
//    testTable.delete(new Row(entries));
//    printTestTable();
//  }
//
//  public void testUpdate() throws IOException{
//    // 测试修改数据，更新id=3的名字为XiaoMing
//    System.out.println("测试修改数据，更新id=3的名字为XiaoMing: ");
//    Entry[] entries = {new Entry(3), new Entry("XiaoMing")};
//    testTable.update(new Row(entries));
//    printTestTable();
//  }
//
//  public void testRestore() throws IOException {
//    // 从文件重新载入table，结果需要与之前一致
//    System.out.println("测试从文件重新载入table，结果需要与之前一致: ");
//    testTable = new Table("Test", "instructor", testColumnList);
//    printTestTable();
//  }
//
//  public void printTestTable() {
//    for(Column c:testTable.getColumns()){
//      System.out.print(c.toString()+"\n");
//    }
//    for (Row row : testTable) {
//      System.out.print(row.toString() + '\n');
//    }
//  }
//}
