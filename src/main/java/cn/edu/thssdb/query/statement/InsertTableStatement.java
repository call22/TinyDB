package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.utils.LogManager;

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
        this.rowValue = rowValue;
        this.columnsName = columnsName;     // 考虑此为空的情况
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        Result result;
        String msg = "[insert to table]: " + this.tableName;
        Database db = manager.getCurrentDB();
        try {
            Table table = db.selectTable(tableName);
            LogManager.addWritelock(table.lock.writeLock());
            ArrayList<Column> columns = table.getColumns();
            // 考虑columnName为空时, 将columns名称补全到columnName
            if (this.columnsName.isEmpty()){
                for(Column column : columns){
                    this.columnsName.add(column.getName());
                }
            }

            int len = columns.size();
            Entry[] entries = new Entry[len];
            for(Column column : columns) {
                int index = columnsName.indexOf(column.getName());
                if (index == -1)    // 不存在就跳过,为null
                    continue;
                String rawValue = rowValue.get(index);
                // 对于string类型, 要去掉''
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
                        entries[index] = new Entry(rawValue.substring(1, rawValue.length()-1));
                        break;
                    default:
                        entries[index] = null;
                        break;
                }
            }
            table.insert(new Row(entries));
            result = Result.setMessage("Successfully " + msg);
        } catch (NumberFormatException e) {
            LogManager.removeWritelock();
            throw new RuntimeException("Fail to " + msg + "entry type format error");
        } catch (IOException e) {
          LogManager.removeWritelock();
          throw new RuntimeException("Fail to " + msg + e.getMessage());
        }
      return result;
    }
}
