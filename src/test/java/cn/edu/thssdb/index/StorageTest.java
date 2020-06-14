package cn.edu.thssdb.index;

import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.type.ColumnType;
import javafx.util.Pair;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.*;

public class StorageTest {
  private Column[] testColumnList = {
          new Column("ID", ColumnType.INT, true, true, 0),
          new Column("NAME", ColumnType.STRING, false, true, 16)
  };
  private Table testTable;
  private BPlusTree<Entry, Row> checkTree;
  @Rule
  public ExpectedException thrown = ExpectedException.none();
  @Before
  public void setUp() {
    try {
      testTable = new Table("TEST", "INSTRUCTOR", testColumnList);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      throw new SyntaxErrorException(e.getMessage());
    }
    checkTree = new BPlusTree<>();
  }
  @Test
  public void testInsertAndSearch() throws IOException {
    for (int i = 0; i < 5; i++) {
      Entry[] entries = {new Entry(i), new Entry("XiaoLi")};
      testTable.insert(new Row(entries));
      checkTree.put(new Entry(i), new Row(entries));
      assertTrue(testTable.search(new Entry(i)).getEntries().equals(
              checkTree.get(new Entry(i)).getEntries()));
    }
  }

  @Test
  public void testDelete() throws IOException {
    testInsertAndSearch();
    Entry[] entries = {new Entry(2), new Entry("XiaoLi")};
    testTable.delete(new Row(entries));
    checkTree.remove(new Entry(2));
    thrown.expect(KeyNotExistException.class);
    testTable.search(new Entry(2));
  }
  @Test
  public void testUpdate() throws IOException {
    testInsertAndSearch();
    Entry[] entries = {new Entry(3), new Entry("XiaoMing")};
    testTable.update(new Row(entries));
    checkTree.update(new Entry(3), new Row(entries));
    assertEquals(checkTree.get(new Entry(3)).getEntries(), testTable.search(new Entry(3)).getEntries());
  }
  @Test
  public void testSerialAndDeserial() throws IOException {
    testInsertAndSearch();
    testTable.serializeIndex();
    Table deserialTable = new Table("TEST", "INSTRUCTOR", testColumnList);
    BPlusTreeIterator<Entry, Row> iterator = checkTree.iterator();
    while (iterator.hasNext()) {
      Pair<Entry, Row> pair = iterator.next();
      assertEquals(pair.getValue().getEntries(), deserialTable.search(pair.getKey()).getEntries());
    }
  }
  @After
  public void cleanup(){
    deleteDir("DBS");
  }

  private static void deleteDir(String filePath) {
    File file = new File(filePath);
    if(!file.exists()){
      return;
    }
    String[] list = file.list();
    File temp = null;
    String path = null;
    for (String item:list) {
      path = filePath + File.separator + item;
      temp = new File(path);
      if(temp.isFile()){
        temp.delete();
        continue;
      }
      if(temp.isDirectory()) {
        deleteDir(path);
        new File(path).delete();
        continue;
      }
    }
  }
}
