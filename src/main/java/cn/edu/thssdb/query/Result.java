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
    private List<Row> rows; // 按列存储结果
    private List<Column> columns; // 存储对应的column

    public Result(){
        this.rows = new ArrayList<>();
    }

    public List<Row> getRows() {
        return rows;
    }

    public void setColumns(Column[] columns) {
        this.columns = Arrays.asList(columns);
    }

    public void setColumns(List<Column> columns) {
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
            if (column.getType() != ColumnType.STRING) {
                builder.append("    ");
            } else {
                int length = Math.max(0, column.getMaxLength() - column.getName().length());
                for (int i = 0; i < length; i++) {
                    builder.append(" ");
                }
            }
        }
        builder.append("|\n");
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
     * @param reverse 是否需要反转
     * @param distinct 是否要求distinct
     * */
    public void addRow(Row row, boolean reverse, boolean distinct){
        if(reverse){
            if(distinct){
                addRowDistinctReverse(row);
            }else{
                addRowReverse(row);
            }
        }else{
            if(distinct){
                addRowDistinct(row);
            }else{
                addRow(row);
            }
        }
    }

    private void addRow(Row row){
        rows.add(row);
    }

    private void addRowReverse(Row row){
        rows.add(0, row);
    }

    private void addRowDistinct(Row row){
        boolean isDuplicate = hasDuplicateRow(row);
        if(!isDuplicate)
            rows.add(row);
    }

    private void addRowDistinctReverse(Row row){
        boolean isDuplicate = hasDuplicateRow(row);
        if(!isDuplicate)
            rows.add(0, row);
    }

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
        result.setColumns(new Column[]{new Column("message", ColumnType.STRING, 1, true, 256)});
        result.addRow(new Row(new Entry[]{new Entry(message)}));
        return result;
    }
}
