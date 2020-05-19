package cn.edu.thssdb.parser;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.query.ComparerData;
import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.ResultColumn;
import cn.edu.thssdb.query.statement.*;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.type.ColumnType;

import java.util.ArrayList;

public class Listener extends SQLBaseListener{
    ArrayList<MultipleCondition> whereConditions = new ArrayList<>();	// 存储where条件
    ArrayList<MultipleCondition> onConditions = new ArrayList<>();		// 存储on条件
    ArrayList<Statement> statements = new ArrayList<>();				// 全部statement
    ArrayList<ResultColumn> select_resultColumn = new ArrayList<>();	// select时返回类型
    ArrayList<Column> create_columns = new ArrayList<>();               // create时存储column
    ArrayList<String> duplicate_columns_check = new ArrayList<>();      // 帮助检查是否重名
    ArrayList<SyntaxErrorException> syntaxErrorExceptions = new ArrayList<>();  // 记录一次statement解析过程中的语法错误

    @Override
    public void enterDrop_db_stmt(SQLParser.Drop_db_stmtContext ctx) {
        Statement drop_db = new SchemaStatement(SchemaStatement.OP_TYPE.drop_db, ctx.database_name().getText().toUpperCase(), "");
        statements.add(drop_db);
    }

    @Override
    public void enterCreate_db_stmt(SQLParser.Create_db_stmtContext ctx) {
        Statement create_db = new SchemaStatement(SchemaStatement.OP_TYPE.create_db, ctx.database_name().getText().toUpperCase(),"");
        statements.add(create_db);
    }

    @Override
    public void enterDrop_table_stmt(SQLParser.Drop_table_stmtContext ctx) {
        Statement drop_tb = new SchemaStatement(SchemaStatement.OP_TYPE.drop_table, "", ctx.table_name().getText().toUpperCase());
        statements.add(drop_tb);
    }

    @Override
    public void enterShow_db_stmt(SQLParser.Show_db_stmtContext ctx) {
        Statement show_db = new SchemaStatement(SchemaStatement.OP_TYPE.show_db, "", "");
        statements.add(show_db);
    }

    @Override
    public void enterShow_table_stmt(SQLParser.Show_table_stmtContext ctx) {
        Statement show_table = new SchemaStatement(SchemaStatement.OP_TYPE.show_table, ctx.database_name().getText().toUpperCase(), "");
        statements.add(show_table);
    }

    @Override
    public void enterShow_meta_stmt(SQLParser.Show_meta_stmtContext ctx) {
        Statement show_meta = new SchemaStatement(SchemaStatement.OP_TYPE.show_meta, "", ctx.table_name().getText().toUpperCase());
        statements.add(show_meta);
    }

    @Override
    public void enterUse_db_stmt(SQLParser.Use_db_stmtContext ctx) {
        Statement use_db = new SchemaStatement(SchemaStatement.OP_TYPE.use_db, ctx.database_name().getText().toUpperCase(), "");
        statements.add(use_db);
    }

    // create_table_stmt
    @Override
    public void enterColumn_def(SQLParser.Column_defContext ctx) {
        String column_name = ctx.column_name().getText().toUpperCase();
        ColumnType type = ColumnType.STRING;    // 初始化为string
        int max_length = 0;
        boolean notNull = false;
        boolean primary = false;
        String input_type = ctx.type_name().getChild(0).getText().toUpperCase();
        switch (input_type){
            case "INT":
                type = ColumnType.INT;
                break;
            case "LONG":
                type = ColumnType.LONG;
                break;
            case "FLOAT":
                type = ColumnType.FLOAT;
                break;
            case "DOUBLE":
                type = ColumnType.DOUBLE;
                break;
            case "CHAR":
                type = ColumnType.STRING;
                try{
                    max_length = Integer.parseInt(ctx.type_name().NUMERIC_LITERAL().toString());
                }catch (NumberFormatException e){
                    syntaxErrorExceptions.add(new SyntaxErrorException(e.getMessage()));
                }
                break;
            default:
                syntaxErrorExceptions.add(new SyntaxErrorException("column type error"));
        }
        for(SQLParser.Column_constraintContext constraint : ctx.column_constraint()){
            if(constraint.getChild(0).equals(constraint.K_PRIMARY())){
                primary = true;
            }
            else{
                notNull = true;
            }
        }
        // 判断column的名称duplicate
        if(duplicate_columns_check.contains(column_name)){
            syntaxErrorExceptions.add(new SyntaxErrorException("duplicate columns"));
        }else {
            duplicate_columns_check.add(column_name);
            Column column = new Column(column_name, type, primary, notNull, max_length);
            create_columns.add(column);
        }
    }

    @Override
    public void enterTable_constraint(SQLParser.Table_constraintContext ctx) {
        for(SQLParser.Column_nameContext name : ctx.column_name()){
            int idx = duplicate_columns_check.indexOf(name.getText().toUpperCase());
            if(idx == -1){
                // not exists
                syntaxErrorExceptions.add(new SyntaxErrorException("column name in constraints does not exist"));
            }else{
                Column column = create_columns.get(idx);
                // 替换为新的column
                create_columns.set(idx, new Column(column.getName(), column.getType(), true, column.getNull(), column.getMaxLength()));
            }
        }
    }

    @Override
    public void exitCreate_table_stmt(SQLParser.Create_table_stmtContext ctx) {
        Statement create_table;
        if(syntaxErrorExceptions.isEmpty()){
            String table_name = ctx.table_name().getText().toUpperCase();
            create_table = new CreateTableStatement(table_name, create_columns);
        }else{  // 有语法错误
            create_table = new CreateTableStatement();
            create_table.setValid(false);
            StringBuilder msg = new StringBuilder();
            for (SyntaxErrorException e : syntaxErrorExceptions){
                msg.append("; ").append(e.getMessage());
            }
            create_table.setMessage(msg.toString());
            syntaxErrorExceptions.clear();  // 清空错误集合
        }
        statements.add(create_table);
    }

    // insert_table_stmt
    @Override
    public void enterInsert_stmt(SQLParser.Insert_stmtContext ctx) {
        Statement insert_table;
        String table_name = ctx.table_name().getText().toUpperCase();
        ArrayList<String> columns_name = new ArrayList<>();
        ArrayList<String> values = new ArrayList<>();
        for(SQLParser.Column_nameContext name : ctx.column_name()){
            columns_name.add(name.getText().toUpperCase());
        }
        for(SQLParser.Value_entryContext value : ctx.value_entry()){
            values.add(value.getText());
        }
        if(columns_name.size() != values.size()){
            insert_table = new InsertTableStatement();
            insert_table.setValid(false);
            insert_table.setMessage("columns doesn't equal to values");
        }else{
            insert_table = new InsertTableStatement(table_name, columns_name, values);
        }
        statements.add(insert_table);
    }

    // get multiple_condition
    private ComparerData getFromLiteral_value(String value){
        ComparerData comparer = new ComparerData();
        ArrayList<String> regexs = new ArrayList<>();
        regexs.add("[nN][uU][lL][lL]"); // null
        regexs.add("'([^']|'')*'");     // string
        regexs.add("\\d+([eE][-+]?\\d+)?");     // int, long
        regexs.add("\\d+.\\d*([eE][-+]?\\d+)?");    // double
        regexs.add(".\\d+([eE][-+]?\\d+)?");    // double
        // TODO 这里可能有问题
        try {
            if (regexs.get(0).matches(value)) {
                comparer = new ComparerData();
            } else if (regexs.get(1).matches(value)) {
                comparer = new ComparerData(new Entry(value.substring(1, value.length() - 1)));
            } else if (regexs.get(2).matches(value)) {
                comparer = new ComparerData(new Entry(Integer.parseInt(value)));
            } else if (regexs.get(3).matches(value)) {
                comparer = new ComparerData(new Entry(Double.parseDouble(value)));
            } else if (regexs.get(4).matches(value)) {
                comparer = new ComparerData(new Entry(Double.parseDouble(value)));
            } else {
                syntaxErrorExceptions.add(new SyntaxErrorException("unexpected value type"));
            }
        }catch (NumberFormatException e){
            syntaxErrorExceptions.add(new SyntaxErrorException("number format exception: " + e.getMessage()));
        }
        return comparer;
    }

    private MultipleCondition.OP_TYPE getFromComparator(String comp){
        comp = comp.toUpperCase();
        MultipleCondition.OP_TYPE comparator = MultipleCondition.OP_TYPE.eq;
        switch (comp) {
            case "EQ":
                comparator = MultipleCondition.OP_TYPE.eq;
                break;
            case "NE":
                comparator = MultipleCondition.OP_TYPE.ne;
                break;
            case "LE":
                comparator = MultipleCondition.OP_TYPE.le;
                break;
            case "GE":
                comparator = MultipleCondition.OP_TYPE.ge;
                break;
            case "LT":
                comparator = MultipleCondition.OP_TYPE.lt;
                break;
            case "GT":
                comparator = MultipleCondition.OP_TYPE.gt;
                break;
            default:
                break;
        }
        return comparator;
    }

    @Override
    public void enterMultiple_condition(SQLParser.Multiple_conditionContext ctx) {
        ArrayList<ComparerData> comparerList = new ArrayList<>();
        for(SQLParser.ComparerContext comp : ctx.comparer()){
            ComparerData comparer = new ComparerData();
            if(comp.column_full_name() != null){
                comparer = new ComparerData(comp.column_full_name().table_name().getText().toUpperCase(),
                        comp.column_full_name().column_name().getText().toUpperCase());
            }else{
                String value = comp.literal_value().getChild(0).getText();
                comparer = getFromLiteral_value(value);
            }
            comparerList.add(comparer);
        }

        String comp = ctx.comparator().getText();
        MultipleCondition.OP_TYPE comparator = getFromComparator(comp);

        whereConditions.add(new MultipleCondition(comparator, comparerList.get(0), comparerList.get(1)));
    }

    // delete_table_stmt
    @Override
    public void exitDelete_stmt(SQLParser.Delete_stmtContext ctx) {
        Statement delete_row;
        if(syntaxErrorExceptions.isEmpty()){
            String table_name = ctx.table_name().getText().toUpperCase();
            delete_row = new DeleteTableStatement(table_name, whereConditions.get(0));
        }else{  // 有语法错误
            delete_row = new DeleteTableStatement();
            delete_row.setValid(false);
            StringBuilder msg = new StringBuilder();
            for (SyntaxErrorException e : syntaxErrorExceptions){
                msg.append("; ").append(e.getMessage());
            }
            delete_row.setMessage(msg.toString());
            syntaxErrorExceptions.clear();  // 清空错误集合
        }
        statements.add(delete_row);
    }

    // update_table_stmt
    @Override
    public void exitUpdate_stmt(SQLParser.Update_stmtContext ctx) {
        Statement update_row;
        if(syntaxErrorExceptions.isEmpty()) {
            String tableName = ctx.table_name().getText().toUpperCase();
            String columnName = ctx.column_name().getText().toUpperCase();
            String value = ctx.literal_value().getText();
            ComparerData comparerData = getFromLiteral_value(value);
            update_row = new UpdateTableStatement(tableName, columnName, comparerData, whereConditions.get(0));
        }else{
            update_row = new DeleteTableStatement();
            update_row.setValid(false);
            StringBuilder msg = new StringBuilder();
            for (SyntaxErrorException e : syntaxErrorExceptions){
                msg.append("; ").append(e.getMessage());
            }
            update_row.setMessage(msg.toString());
            syntaxErrorExceptions.clear();  // 清空错误集合
        }
        statements.add(update_row);
    }

    // select_table_stmt

    @Override
    public void exitSelect_stmt(SQLParser.Select_stmtContext ctx) {

    }
}
