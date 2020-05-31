package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.ComparerData;
import cn.edu.thssdb.query.MetaInfo;
import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.utils.LogManager;
import javafx.scene.control.Tab;

import javax.swing.plaf.nimbus.State;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static javafx.scene.input.KeyCode.L;

public class UpdateTableStatement extends Statement {
    private String tableName;
    private String columnName;
    private ComparerData value; // check赋值是否符合column要求
    private MultipleCondition multipleCondition;

    public UpdateTableStatement(){
        tableName = "error";
    }

    public UpdateTableStatement(String tableName, String columnName, ComparerData value, MultipleCondition multipleCondition){
        this.tableName = tableName;
        this.columnName = columnName;
        this.value = value;
        this.multipleCondition = multipleCondition;
    }

    private boolean checkLegal(int idx, Table table){
        if(idx == -1)return false;
        Column column = table.getColumns().get(idx);
        // check
        if(value.getComparerType() == ComparerData.COMPARER_TYPE._null){
            if(!column.getNull())
                return false;
        }else { // 如果value可以与column.type值比较, 则认为合法
            try {
                switch (column.getType()){
                    case INT:
                        value.getData().compareTo(new Entry((int) 1));
                        break;
                    case STRING:
                        value.getData().compareTo(new Entry((String) "hello"));
                        break;
                    case LONG:
                        value.getData().compareTo(new Entry((long) 1.234));
                        break;
                    case FLOAT:
                        value.getData().compareTo(new Entry((float) 1.234));
                        break;
                    case DOUBLE:
                        value.getData().compareTo(new Entry((double) 1.234));
                        break;
                    default:
                        break;
                }
            }catch (ClassCastException e){
                return false;
            }
        }
        return true;
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        /** set MetoInfo 到multipleCondition*/
        DeleteTableStatement.setMetaInfo(tableName, manager, multipleCondition);

        /** next */
        String msg = "[update table]: " + this.tableName;
        Table table = manager.getCurrentDB().selectTable(tableName);
        Iterator<Row> iterator = table.iterator();
        Result result = null;
        int times = 0;
        MetaInfo mt = new MetaInfo(tableName, table.getColumns());
        int idx = mt.columnFind(columnName);
        if(checkLegal(idx, table)){
            try {
                if(multipleCondition.check()) {
                    int type = multipleCondition.getTypes();
                    switch (type) {
                        case 0: {
                            if (multipleCondition.calculate()) {
                                // 全部满足
                                while (iterator.hasNext()) {
                                    ArrayList<Entry> entries = iterator.next().getEntries();
                                    entries.set(idx, value.getData());
                                    Row tmp = new Row(entries.toArray(new Entry[0]));
                                    table.update(tmp);
                                    times++;
                                }
                            }
                        }
                        case 1: {
                            while (iterator.hasNext()) {
                                Row row = iterator.next();
                                if (multipleCondition.calculate(row)) {
                                    ArrayList<Entry> entries = row.getEntries();
                                    entries.set(idx, value.getData());
                                    Row tmp = new Row(entries.toArray(new Entry[0]));
                                    table.update(tmp);
                                    times++;
                                }
                            }
                        }
                        case 2: {
                            while (iterator.hasNext()) {
                                Row temp = iterator.next();
                                if (multipleCondition.calculate(temp, temp)) {
                                    ArrayList<Entry> entries = temp.getEntries();
                                    entries.set(idx, value.getData());
                                    Row tmp = new Row(entries.toArray(new Entry[0]));
                                    table.update(tmp);
                                    times++;
                                }
                            }
                        }
                        default:
                            break;
                    }
                }else{
                    // multipleCondition形式不正确
                    LogManager.removeWritelock();
                    throw new RuntimeException("condition conflict");
                }
                result = Result.setMessage("Successfully " + msg + ", update " + times + "rows");
            }catch (IOException e){
                LogManager.removeWritelock();
                throw new RuntimeException("fail to " + msg + e.getMessage());
            }
        }else {
            LogManager.removeWritelock();
            throw new RuntimeException("fail to " + msg + ", assignment type error");
        }
        return result;
    }
}
