package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;

import java.io.IOException;
import java.util.ArrayList;

public class InsertTableStatement extends Statement {
    private String tableName;
    private ArrayList<String> columnsName;
    private ArrayList<String> rowValue;

    public InsertTableStatement(){
        tableName = "error";
    }

    public InsertTableStatement(String tableName, ArrayList<String> columnsName, ArrayList<String> rowValue){
        this.tableName = tableName;
        this.columnsName = columnsName;
        this.rowValue = rowValue;
    }

    // 根据给定的值运行插入操作, 此处保证len(columnsName) == len(rowValue)
    @Override
    public Result execute(Manager manager) throws RuntimeException {
        Result result;
        String msg = "[insert to table]: " + this.tableName;
        Database db = manager.getCurrentDB();
        try {
            Table table = db.selectTable(tableName);
            ArrayList<Column> columns = table.getColumns();
            int len = columns.size();
            Entry[] entries = new Entry[len];
            for(Column column : columns) {
                int index = columnsName.indexOf(column.getName());
                if (index == -1)    // 不存在就跳过,为null
                    continue;
                String rawValue = rowValue.get(index);
                switch (column.getType()) {
                    case INT:
                        entries[index] = new Entry(Integer.parseInt(rawValue));
                        break;
                    case LONG:
                        entries[index] = new Entry(Long.parseLong(rawValue));
                        break;
                    case FLOAT:
                        entries[index] = new Entry(Float.parseFloat(rawValue));
                        break;
                    case DOUBLE:
                        entries[index] = new Entry(Double.parseDouble(rawValue));
                        break;
                    case STRING:
                        entries[index] = new Entry(rawValue);
                        break;
                    default:
                        entries[index] = null;
                        break;
                }
            }
            table.insert(new Row(entries));
            result = Result.setMessage("Successfully " + msg);
        } catch (NumberFormatException e) {
            throw new RuntimeException("Fail to " + msg + "entry type format error");
        } catch (IOException e) {
            throw new RuntimeException("Fail to " + msg + e.getMessage());
        }

      return result;
    }
}
