package cn.edu.thssdb.query.condition;

public class ComparerData {
    public enum COMPARER_TYPE{
        table_column, literal
    }

    public enum LITERAL_TYPE{
        number, _string, _null
    }
    // 用于标识Data具体类型
    private COMPARER_TYPE comparerType;
    private LITERAL_TYPE literalType;

    // for table_column
    private String tableName;
    private String columnName;

    // for literal
    private String stringData;
    private Double numberData;

    /**
     * 不同情况的初始化函数*/
    public ComparerData(String stringData){ // string
        this.comparerType = COMPARER_TYPE.literal;
        this.literalType = LITERAL_TYPE._string;
        this.stringData = stringData;
    }

    public ComparerData(Double numberData){ // int, float, double
        this.comparerType = COMPARER_TYPE.literal;
        this.literalType = LITERAL_TYPE.number;
        this.numberData = numberData;
    }

    public ComparerData(Long numberData){ // long
        this.comparerType = COMPARER_TYPE.literal;
        this.literalType = LITERAL_TYPE.number;
        this.stringData = numberData.toString();    // 转为字符串存储, 没有损失
    }

    public ComparerData(){
        this.comparerType = COMPARER_TYPE.literal;
        this.literalType = LITERAL_TYPE._null;
    }

    public ComparerData(String tableName, String columnName){
        this.comparerType = COMPARER_TYPE.table_column;
        this.literalType = LITERAL_TYPE._null;
        this.tableName = tableName == null ? null : tableName.toUpperCase();    //     ( table_name '.' )? column_name ;
        this.columnName = columnName;
    }

    /**
     * 属性获取和判断*/
    public COMPARER_TYPE getComparerType(){
        return this.comparerType;
    }

    public LITERAL_TYPE getLiteralType(){
        return this.literalType;
    }

    public String getTableName() {
        return tableName;
    }

    public String getColumnName() {
        return columnName;
    }

    public String getStringData() {
        return stringData;
    }

    public Double getNumberData() {
        return numberData;
    }

    public boolean isInt(){
        return literalType == LITERAL_TYPE.number && !this.numberData.toString().contains(".");
    }
}
