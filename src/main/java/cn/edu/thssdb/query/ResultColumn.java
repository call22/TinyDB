package cn.edu.thssdb.query;

/**
 * Select 的返回要求存储*/
public class ResultColumn {
    public enum RESULT_COLUMN_TYPE{
        all, table_column
    }

    private RESULT_COLUMN_TYPE type;
    private String tableName = "";
    private String columnName = "";
    /**
     * ALL,  table_column*/
    public ResultColumn(){
        this.type = RESULT_COLUMN_TYPE.all;
    }

    public ResultColumn(String tableName, String columnName){
        this.type = RESULT_COLUMN_TYPE.table_column;
        this.tableName = tableName.toUpperCase();
        this.columnName = columnName.toUpperCase();
    }

  /**
   * setter */
  public void setTableName(String tableName) {
      this.tableName = tableName;
    }

    /**
     * 获取result column的类型*/
    public RESULT_COLUMN_TYPE getResultType(){
        return this.type;
    }

    public String getTableName(){
        return this.tableName;
    }

    public String getColumnName(){
        return this.columnName;
    }
}
