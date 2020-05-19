package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.*;
import cn.edu.thssdb.schema.*;

import java.util.ArrayList;
import java.util.List;

public class SelectTableStatement extends Statement {
    private String tableName;                       // 表名
    private MultipleCondition whereCondition;       // where条件
    private ArrayList<ResultColumn> resultColumns;  // 返回形式

    private boolean isAll;                          // ResultColumn type
    private boolean isDistinct;                     // 新增, 区分result Column为distinct还是all
    private ArrayList<Column> columnList;           // resultColumns的对应column
    private ArrayList<Integer> columnIndex;         // resultColumns的对应索引

    public Result result;

    public SelectTableStatement(){
        this.tableName = "error";
    }

    public SelectTableStatement(String tableName, MultipleCondition whereCondition, ArrayList<ResultColumn> resultColumns, boolean isDistinct) {
        this.tableName = tableName;
        this.whereCondition = whereCondition;
        this.resultColumns = resultColumns;
        this.isDistinct = isDistinct;
        isAll = resultColumns.get(0).getResultType().equals(ResultColumn.RESULT_COLUMN_TYPE.all);
    }

    /**
     * 检验resultColumns的合法性, 并记录select column 对应索引
     */
    private boolean checkLegal(Table table) {
        ArrayList<Column> columns =  table.getColumns();
        if (isAll) {
            // 若 select * 就不用考虑
            return true;
        } else {
            columnList = new ArrayList<>();
            columnIndex = new ArrayList<>();
            for (ResultColumn rc : resultColumns) {
                boolean tmp = false;
                int index = 0;
                for(Column col : columns) {
                    if (col.getName().equals(rc.getColumnName())) {
                        tmp = true;
                        columnList.add(col);
                        columnIndex.add(index);
                        break;
                    }
                    index++;
                }
                if(!tmp)
                    return false;
            }
        }
        return true;
    }

    /**
     * 列筛选后插入一行数据到结果集, SelectJoinTableStatement 也会调用
     * @param row 尚未select处理过得一行数据
     * @param isAll ResultColumn type
     * @param result 结果引用
     * @param columnIndex 待选择列索引
     */
    static void addRow2ResultAfterSelectColumns(Row row, boolean isAll, Result result, ArrayList<Integer> columnIndex, boolean isDistinct) {
        if(isAll) {
            result.addRow(row, isDistinct);
        } else {
            int len = columnIndex.size();
            Entry[] entries = new Entry[len];
            for (int i = 0; i < len; i++) {
                entries[i] = row.getEntries().get(i);
            }
            result.addRow(new Row(entries), isDistinct);
        }
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        /** set MetaInfo 到multipleCondition*/
        DeleteTableStatement.setMetaInfo(manager, whereCondition);

        /** next */

        String msg = "[select table]: " + this.tableName;
        Table table = manager.getCurrentDB().selectTable(tableName);
        result = new Result();
        int type = whereCondition.getTypes();

        // 获取where满足条件的row, 并筛选到result
        if (checkLegal(table)) {
            try {
                for (Row row : table) {
                    switch (type) {
                        case 0:
                            if (whereCondition.calculate()) {
                                addRow2ResultAfterSelectColumns(row, isAll, result, columnIndex, this.isDistinct);
                            }
                            break;
                        case 1:
                            if (whereCondition.calculate(row)) {
                                addRow2ResultAfterSelectColumns(row, isAll, result, columnIndex, this.isDistinct);
                            }
                            break;
                        case 2:
                            if (whereCondition.calculate(row, row)) {
                                addRow2ResultAfterSelectColumns(row, isAll, result, columnIndex, this.isDistinct);
                            }
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("fail to " + msg + e.getMessage());
            }
        } else {
            throw new RuntimeException("fail to " + msg + ", column type error");
        }
        result.setColumns(columnList);
        return result;
    }

}
