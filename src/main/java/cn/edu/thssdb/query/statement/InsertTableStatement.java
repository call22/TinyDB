package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.exception.SyntaxErrorException;
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
            ArrayList<String> columns = new ArrayList<>();
            for(Column column : table.getColumns()) {
                columns.add(column.getName());
            }
            // 考虑columnName为空时, 将columns名称补全到columnName
            if (this.columnsName.isEmpty()){
                this.columnsName.addAll(columns);
            }

            int len = columns.size();
            Entry[] entries = new Entry[len];
            //初始化为null
            for(int idx = 0;idx<len;idx++) {
                entries[idx] = null;
            }

            int count = -1;  // 记录对应column，以获取相应value
            for(String column : columnsName) {
                count ++;
                int index = columns.indexOf(column);
                if (index == -1) {  // columns中存在非法column
                    throw new SyntaxErrorException(column + " doesn't exist in table's columns");
                }
                String rawValue = rowValue.get(count);
                switch (table.getColumns().get(index).getType()) {
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
            table.insert(new Row(entries)); // 判断插入的值是否符合要求
            result = Result.setMessage("Successfully " + msg);
        } catch (NumberFormatException e) {
            LogManager.removeWritelock();
            result = Result.setMessage("Fail to " + msg + " entry type format error");
            this.setValid(false);
//            throw new RuntimeException("Fail to " + msg + "entry type format error");
        } catch (IOException e) {
          LogManager.removeWritelock();
          throw new RuntimeException("Fail to " + msg + " " + e.getMessage());
        } catch (SyntaxErrorException e) {
            result = Result.setMessage(e.getMessage());
            this.setValid(false);
        }
      return result;
    }
}
