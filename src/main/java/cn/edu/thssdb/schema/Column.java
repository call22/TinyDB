package cn.edu.thssdb.schema;

import cn.edu.thssdb.type.ColumnType;

public class Column implements Comparable<Column> {
  private String name;
  private ColumnType type;
  private boolean primary;
  private boolean notNull;
  private int maxLength;

  public Column(String name, ColumnType type, boolean primary, boolean notNull, int maxLength) {
    this.name = name.toUpperCase();
    this.type = type;
    this.primary = primary;
    this.notNull = notNull;
    this.maxLength = maxLength;
  }

  @Override
  public int compareTo(Column e) {
    return name.compareTo(e.name);
  }

  public String toString() {
    return name + " | " + type + " | " + primary + " | " + notNull + " | " + maxLength;
  }

  public String getName() {
    return this.name;
  }
  public ColumnType getType() {
    return this.type;
  }
  public int getMaxLength() {
    return this.maxLength;
  }

  public boolean getNull(){return notNull;}
  public boolean isPrimary() {
    return primary;
  }
}
