package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;

import java.util.ArrayList;
import java.util.List;

public class MetaInfo {

  private String tableName;
  private ArrayList<Column> columns;

  public MetaInfo(String tableName, ArrayList<Column> columns) {
    this.tableName = tableName;
    this.columns = columns;
  }

  /**
   * 从table中查找相应column
   * @param name 待查找column
   * @return index column在columns中的位置
   */
  public int columnFind(String name) {
    for(Column column : columns){
      if(name.equals(column.getName())){
        return columns.lastIndexOf(column);
      }
    }
    return -1;
  }

  public String getTableName(){
    return tableName;
  }
}