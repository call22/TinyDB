package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Entry;

public class ComparerData {
    public enum COMPARER_TYPE{
        table_column, literal, _null
    }

    // 用于标识Data具体类型
    private COMPARER_TYPE comparerType;

    // for table_column
    private String tableName;
    private String columnName;

    // for literal
    private Entry data;

    /**
     * 不同情况的初始化函数*/
    public ComparerData(Entry data){ // string, int, float, double, long
        this.comparerType = COMPARER_TYPE.literal;
        this.data = data;
    }

    public ComparerData(String tableName, String columnName){
        this.comparerType = COMPARER_TYPE.table_column;
        this.tableName = tableName == null ? null : tableName.toUpperCase();    //     ( table_name '.' )? column_name ;
        this.columnName = columnName;
    }

    public ComparerData(){
        this.comparerType = COMPARER_TYPE._null;
        this.data = null;
    }

    /**
     * 属性获取和判断*/
    public COMPARER_TYPE getComparerType(){
        return this.comparerType;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public Entry getData() {
        return data;
    }
}
