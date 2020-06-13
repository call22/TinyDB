package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.*;
import cn.edu.thssdb.schema.*;

import java.util.ArrayList;
import java.util.List;

public class SelectJoinTableStatement extends Statement {

    private ArrayList<String> tableName;                 // 2个table
    private MultipleCondition onCondition;          // on条件
    private MultipleCondition whereCondition;       // where条件
    private ArrayList<ResultColumn> resultColumns;  // 最终返回形式

    private boolean isAll;                          // ResultColumn type
    private boolean isDistinct;                     // 新增, 区分result Column为distinct还是all
    private ArrayList<Column> columnList;           // resultColumns的对应column
    private ArrayList<Integer> columnIndex;         // resultColumns的对应索引

    private Table table1, table2;
    public Result result;

    public SelectJoinTableStatement(ArrayList<String> tableName, MultipleCondition onCondition,
                             MultipleCondition whereCondition, ArrayList<ResultColumn> resultColumns, boolean isDistinct) {
        this.tableName = tableName;
        this.onCondition = onCondition;
        this.whereCondition = whereCondition;
        this.resultColumns = resultColumns;
        this.isDistinct = isDistinct;
        isAll = resultColumns.get(0).getResultType().equals(ResultColumn.RESULT_COLUMN_TYPE.all);
    }

    /**
     * 检验resultColumns的合法性, 并记录select column 对应索引
     */
    private boolean checkLegal(ArrayList<Column> columns) {
        if (isAll) {
            // 若 select * 就不用考虑
            columnList = columns;
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
    private Row matchRowToTableName(MetaInfo metaInfo, Row row1, Row row2) {
        if (metaInfo.getTableName().equals(table1.getTableName())) {
            return row1;
        } else {
            return row2;
        }
    }

    /**
     * 执行where语句
     */
    private void runWhereCondition(Row row1, Row row2) {
        if(whereCondition.check()) {
            int type = whereCondition.getTypes();
            List<MetaInfo> metaInfos = whereCondition.getMetaInfos();
            switch (type) {
                case 0:
                    if (whereCondition.calculate()) {
                        SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex, this.isDistinct);
                    }
                    break;
                case 1:
                    if (whereCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2))) {
                        SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex, this.isDistinct);
                    }
                    break;
                case 2:
                    if (whereCondition.calculate(matchRowToTableName(metaInfos.get(0), row1, row2),
                            matchRowToTableName(metaInfos.get(1), row1, row2))) {
                        SelectTableStatement.addRow2ResultAfterSelectColumns(combineRows(row1, row2), isAll, result, columnIndex, this.isDistinct);
                    }
                    break;
                default:
                    break;
            }
        }else{
            // multipleCondition形式不正确
            throw new RuntimeException("condition conflict");
        }
    }
    public Result execute(Manager manager) throws RuntimeException {
        table1 = manager.getCurrentDB().selectTable(tableName.get(0));
        table2 = manager.getCurrentDB().selectTable(tableName.get(1));
        if(table1 == null || table2 == null)    // 出现不存在的table
        {
            throw new RuntimeException("table name does not exists");
        }

        /** set MetaInfo 到multipleCondition*/
        setMetaInfo(tableName, manager, onCondition);
        setMetaInfo(tableName, manager, whereCondition);

        ArrayList<Column> columns1 = table1.getColumns();
        ArrayList<Column> columns2 = table2.getColumns();
        ArrayList<Column> tmpColumns = new ArrayList<>();
        /** 若select语句column不是 tablename.column格式,修正*/
        for (ResultColumn rc : resultColumns) {
          if (rc.getTableName().equals("")) {
            for (Column col : columns1) {
              if(col.getName().equals(rc.getColumnName())) {
                rc.setTableName(table1.getTableName());
                break;
              }
            }
            for (Column col : columns2) {
              if(col.getName().equals(rc.getColumnName())) {
                rc.setTableName(table2.getTableName());
                break;
              }
            }
          }
        }
        // 连接Columns
        for (Column column : columns1) {
            // 名字改为： tablename + "." + columnname
            tmpColumns.add(new Column(table1.getTableName() + "." + column.getName(),
                    column.getType(), false,
                    column.getNull(), column.getMaxLength()));
        }
        for (Column column : columns2) {
            // 名字改为： tablename + "." + columnname
            tmpColumns.add(new Column(table2.getTableName() + "." + column.getName(),
                    column.getType(), false,
                    column.getNull(), column.getMaxLength()));
        }

        String msg = "[select table]: " + table1.getTableName() + ", " + table2.getTableName();
        result = new Result();
        if (checkLegal(tmpColumns)) {
            try {
                if(onCondition.check()) {
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
                                            matchRowToTableName(metaInfos.get(1), row1, row2))) {
                                        runWhereCondition(row1, row2);
                                    }
                                    break;
                                default:
                                    break;
                            }
                        }
                    }
                }else{
                    // multipleCondition形式不正确
                    throw new RuntimeException("condition conflict");
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

    /**
     * 设置metaInfo
     * @param tableName 当前table list
     * @param manager 当前manager
     * @param multipleCondition 条件*/
    private void setMetaInfo(ArrayList<String> tableName, Manager manager, MultipleCondition multipleCondition) {
        List<ComparerData> comparerDatas = multipleCondition.getComparerDataList();
        ArrayList<ArrayList<String>> column_info = new ArrayList<>();
        ArrayList<ComparerData> comparerDataArrayList = new ArrayList<>();
        for( String table : tableName){
            ArrayList<Column> columns = manager.getCurrentDB().selectTable(table).getColumns();
            ArrayList<String> info = new ArrayList<>();
            for(Column column : columns){
                info.add(column.getName());
            }
            column_info.add(info);
        }

        for(ComparerData comparerData1 : comparerDatas){
            if(comparerData1.getComparerType() == ComparerData.COMPARER_TYPE.table_column){
                String meta_table_name = comparerData1.getTableName();
                if(meta_table_name == null) {   // 判断到底在哪个table中
                    String column_name = comparerData1.getColumnName();

                    int in = 0;
                    int i = 1;
                    for(ArrayList<String> column : column_info){
                        if(column.contains(column_name))
                            in += i;
                        i++;
                    }
                    if(in == 0 || in == 3){
                        throw new RuntimeException(" ambiguous column name: " + column_name);
                    }
                    else {
                        meta_table_name = tableName.get(in-1);
                    }
                }
                comparerDataArrayList.add(new ComparerData(meta_table_name, comparerData1.getColumnName()));
                MetaInfo meta = new MetaInfo(meta_table_name, manager.getCurrentDB().selectTable(meta_table_name).getColumns());
                multipleCondition.setMetaInfos(meta);
            }else{
                comparerDataArrayList.add(comparerData1);
            }
        }
        multipleCondition.setComparator(comparerDataArrayList);
    }
}
