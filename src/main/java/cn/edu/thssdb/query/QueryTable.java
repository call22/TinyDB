package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.schema.Table;

import java.util.Iterator;

/**
 * 用迭代器的方式从当前表中不断获取新的行并判断其是否满足where中条件*/
public class QueryTable implements Iterator<Row> {
  private Table table;
  private Iterator<Row> iterator;

  QueryTable(Table table) {
    // TODO
    this.table = table;
    this.iterator = this.table.iterator();
  }

  @Override
  public boolean hasNext() {
    // TODO
    return this.iterator.hasNext();
  }

  @Override
  public Row next() {
    // TODO
    return this.iterator.next();
  }
}