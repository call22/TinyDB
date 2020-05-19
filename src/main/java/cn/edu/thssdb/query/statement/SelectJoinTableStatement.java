package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.*;
import cn.edu.thssdb.schema.*;

import java.util.ArrayList;
import java.util.List;

public class SelectJoinTableStatement extends Statement {

    private List<String> tableName;                 // 2个table
    private MultipleCondition onCondition;          // on条件
    private MultipleCondition whereCondition;       // where条件
    private ArrayList<ResultColumn> resultColumns;  // 最终返回形式

    private boolean isAll;                          // ResultColumn type
    private ArrayList<Column> columnList;           // resultColumns的对应column
    private ArrayList<Integer> columnIndex;         // resultColumns的对应索引

    private Table table1, table2;
    public Result result;

    SelectJoinTableStatement(List<String> tableName, MultipleCondition onCondition,
                             MultipleCondition whereCondition, ArrayList<ResultColumn> resultColumns) {
        this.tableName = tableName;
        this.onCondition = onCondition;
        this.whereCondition = whereCondition;
        this.resultColumns = resultColumns;
        isAll = resultColumns.get(0).getResultType().equals(ResultColumn.RESULT_COLUMN_TYPE.all);
    }

    /**
     * 检验resultColumns的合法性, 并记录select column 对应索引
     */
    private boolean checkLegal(ArrayList<Column> columns) {
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
                    if (col.getName().equals(rc.getTableName() + "." +rc.getColumnName())) {
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
     * 连接两行row
     */
    private Row combineRows(Row row1, Row row2) {
        ArrayList<Entry> entries = new ArrayList<>();
        entries.addAll(row1.getEntries());
        entries.addAll(row2.getEntries());
        return new Row(entries.toArray(new Entry[entries.size()]));
    }

    /**
     * 根据metainfo的table名返回对应row
     */
    public Row matchRowToTableName(MetaInfo metaInfo, Row row1, Row row2) {
        if (metaInfo.getTableName().equals(table1.getTableName())) {
            return row1;
        } else {
            return row2;
        }
    }

    /**
     * 执行where语句
     */
    public void runWhereCondition(Row row1, Row row2) {
        int type = whereCondition.getTypes();
        List<MetaInfo> metaInfos = whereCondition.getMetaInfos();
        switch (type) {
            case 0:
                if (whereCondition.calculate()) {
                    SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex);
                }
                break;
            case 1:
                if (whereCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2))) {
                    SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex);
                }
                break;
            case 2:
                if (whereCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2),
                        matchRowToTableName(metaInfos.get(1), row1, row2))) {
                    SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex);
                }
                break;
            default:
                break;
        }
    }
    public Result execute(Manager manager) throws RuntimeException {
        /** set MetaInfo 到multipleCondition*/
        DeleteTableStatement.setMetaInfo(manager, onCondition);
        DeleteTableStatement.setMetaInfo(manager, whereCondition);

        /** next */
        // 连接Columns
        table1 = manager.getCurrentDB().selectTable(tableName.get(0));
        table2 = manager.getCurrentDB().selectTable(tableName.get(1));
        ArrayList<Column> columns1 = table1.getColumns();
        ArrayList<Column> columns2 = table2.getColumns();
        ArrayList<Column> tmpColumns = new ArrayList<>();
        for (Column column : columns1) {
            // 名字改为： tablename + "." + columnname
            tmpColumns.add(new Column(table1.getTableName() + "." + column.getName(),
                    column.getType(), 0,
                    column.getNull(), column.getMaxLength()));
        }
        for (Column column : columns2) {
            // 名字改为： tablename + "." + columnname
            tmpColumns.add(new Column(table2.getTableName() + "." + column.getName(),
                    column.getType(), 0,
                    column.getNull(), column.getMaxLength()));
        }

        String msg = "[select table]: " + table1.getTableName() + ", " + table2.getTableName();
        result = new Result();
        if (checkLegal(tmpColumns)) {
            try {
                List<MetaInfo> metaInfos = onCondition.getMetaInfos();
                int type = onCondition.getTypes();
                for (Row row1 : table1) {
                    for (Row row2 : table2) {
                        switch (type) {
                            case 0:
                                if (onCondition.calculate()) {
                                    runWhereCondition(row1, row2);
                                }
                                break;
                            case 1:
                                if (onCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2))) {
                                    runWhereCondition(row1, row2);
                                }
                                break;
                            case 2:
                                if (onCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2),
                                        matchRowToTableName(metaInfos.get(1), row1, row2)))) {
                                    runWhereCondition(row1, row2);
                                }
                                break;
                            default:
                                break;
                        }
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
