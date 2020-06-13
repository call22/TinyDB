package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;
import cn.edu.thssdb.type.ColumnType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 以类似table的形式存储操作之后的返回结果
 * */
public class Result {
    private ArrayList<Row> rows; // 按列存储结果
    private ArrayList<Column> columns; // 存储对应的column

    public Result(){
        this.rows = new ArrayList<>();
        this.columns = new ArrayList<>();
    }

    /**修改: 为了统一thrift接口,
     * 将result读取形式有string, 改为column string list 和 row string list
     * @return*/
    public List<List<String>> getStringRows() {
        List<List<String>> stringRows = new ArrayList<>();
        for(Row row : rows){
            ArrayList<String> stringRow = new ArrayList<>();
            for(Entry entry : row.getEntries()){
                stringRow.add(entry.toString());
            }
            stringRows.add(stringRow);
        }
        return stringRows;
    }

    public ArrayList<String> getStringColumns() {
        ArrayList<String> stringColumn = new ArrayList<>();
        for(Column column : columns) {
            stringColumn.add(column.getName());
        }
        return stringColumn;
    }
    public void setColumns(Column[] columns) {
        this.columns = new ArrayList<Column> (Arrays.asList(columns));
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }

    /**
     * toString 将result结果转换为输出的字符串
     * */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();    // 不是线程安全的,但应该对多线程访问不影响
        // print columns
        for (Column column : columns) {
            builder.append("| ");
            builder.append(column.getName());
        }
        builder.append("|\n");
        builder.append("================================\n");
        // print rows
        for (Row row : rows) {
            builder.append(row.toString());
            builder.append("\n");
        }
        return builder.toString();
    }

    /**
     * 添加一列对外接口
     * @param row 待添加的列
     * @param distinct 是否要求distinct
     * */
    public void addRow(Row row, boolean distinct){
        if(distinct){
            addRowDistinct(row);
        }else{
            addRow(row);
        }
    }

    private void addRow(Row row){
        rows.add(row);
    }

//    private void addRowReverse(Row row){
//        rows.add(0, row);
//    }

    private void addRowDistinct(Row row){
        boolean isDuplicate = hasDuplicateRow(row);
        if(!isDuplicate)
            rows.add(row);
    }

//    private void addRowDistinctReverse(Row row){
//        boolean isDuplicate = hasDuplicateRow(row);
//        if(!isDuplicate)
//            rows.add(0, row);
//    }

    /**
     * 检查是否有重复
     * @param row 待检查列
     */
    private boolean hasDuplicateRow(Row row){
        boolean getDuplicate = false;
        for(Row other_row : rows){
            if(other_row.getEntries().equals(row.getEntries())){
                getDuplicate = true;
                break;
            }
        }
        return getDuplicate;
    }

    /**
     * 对于返回非table类型的操作, 直接调用此函数*/
    public static Result setMessage(String message){
        Result result = new Result();
        result.setColumns(new Column[]{new Column("message", ColumnType.STRING, true, true, 256)});
        result.addRow(new Row(new Entry[]{new Entry(message)}));
        return result;
    }
}
