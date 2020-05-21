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
import java.util.regex.Pattern;

public class Listener extends SQLBaseListener{
    private ArrayList<MultipleCondition> whereConditions = new ArrayList<>();	// 存储where条件
    private ArrayList<MultipleCondition> onConditions = new ArrayList<>();		// 存储on条件
    private ArrayList<Statement> statements = new ArrayList<>();				// 全部statement
    private ArrayList<Column> create_columns = new ArrayList<>();               // create时存储column
    private ArrayList<String> duplicate_columns_check = new ArrayList<>();      // 帮助检查是否重名
    private ArrayList<SyntaxErrorException> syntaxErrorExceptions = new ArrayList<>();  // 记录一次statement解析过程中的语法错误
    private MultipleCondition default_condition = new MultipleCondition(MultipleCondition.OP_TYPE.eq,
            new ComparerData(new Entry(1)), new ComparerData(new Entry(1)));
    /**获取解析之后的statement*/
    public ArrayList<Statement> getStatements(){
        return statements;
    }

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
            case "STRING":
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
        create_columns.clear();
        duplicate_columns_check.clear();
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
        for(SQLParser.Literal_valueContext value : ctx.literal_value()){
            values.add(value.getText());
        }
        if(columns_name.size() != 0 && columns_name.size() != values.size()){   // 存在columns, 但与values不等
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
            if (Pattern.matches(regexs.get(0),value)) {
                comparer = new ComparerData();
            } else if (Pattern.matches(regexs.get(1),value)) {
                comparer = new ComparerData(new Entry(value.substring(1, value.length() - 1)));
            } else if (Pattern.matches(regexs.get(2),value)) {
                comparer = new ComparerData(new Entry(Integer.parseInt(value)));
            } else if (Pattern.matches(regexs.get(3),value)) {
                comparer = new ComparerData(new Entry(Double.parseDouble(value)));
            } else if (Pattern.matches(regexs.get(4),value)) {
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
            case "=":
                comparator = MultipleCondition.OP_TYPE.eq;
                break;
            case "<>":
                comparator = MultipleCondition.OP_TYPE.ne;
                break;
            case "<=":
                comparator = MultipleCondition.OP_TYPE.le;
                break;
            case ">=":
                comparator = MultipleCondition.OP_TYPE.ge;
                break;
            case "<":
                comparator = MultipleCondition.OP_TYPE.lt;
                break;
            case ">":
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
            ComparerData comparer;
            if(comp.column_full_name() != null){
                String table_name = null;
                if(comp.column_full_name().table_name() != null){
                    table_name = comp.column_full_name().table_name().getText().toUpperCase();
                }
                comparer = new ComparerData(table_name,
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
            if (whereConditions.isEmpty())  // 没有where选择
                whereConditions.add(default_condition);
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
        whereConditions.clear();
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
            if (whereConditions.isEmpty())  // where为空时
                whereConditions.add(default_condition);
            update_row = new UpdateTableStatement(tableName, columnName, comparerData, whereConditions.get(0));
        }else{
            update_row = new UpdateTableStatement();
            update_row.setValid(false);
            StringBuilder msg = new StringBuilder();
            for (SyntaxErrorException e : syntaxErrorExceptions){
                msg.append("; ").append(e.getMessage());
            }
            update_row.setMessage(msg.toString());
            syntaxErrorExceptions.clear();  // 清空错误集合
        }
        statements.add(update_row);
        whereConditions.clear();
    }

    // select_table_stmt

    @Override
    public void exitTable_query(SQLParser.Table_queryContext ctx) {
        if(ctx.getChildCount() > 1){
            // where中添加的condition为on condition
            onConditions.add(whereConditions.get(whereConditions.size() - 1));
            whereConditions.remove(whereConditions.size() - 1);
        }
    }

    @Override
    public void exitSelect_stmt(SQLParser.Select_stmtContext ctx) {
        Statement select_row;

        if(syntaxErrorExceptions.isEmpty()){
            boolean isDistinct = false;
            if (ctx.K_DISTINCT() != null ){
                isDistinct = true;
            }

            ArrayList<ResultColumn> select_resultColumn = new ArrayList<>();	// select时返回类型
            for (SQLParser.Result_columnContext result_columnContext : ctx.result_column()) {
                if(result_columnContext.column_full_name() == null){
                    // all
                    select_resultColumn.add(new ResultColumn());
                } else {
                    String table_name = "";
                    if (result_columnContext.column_full_name().table_name() != null){
                        table_name = result_columnContext.column_full_name().table_name().getText().toUpperCase();
                    }
                    select_resultColumn.add(
                            new ResultColumn(table_name,
                                    result_columnContext.column_full_name().column_name().getText().toUpperCase()));
                }
            }

            if (whereConditions.isEmpty()) // where为空
                whereConditions.add(default_condition);
            ArrayList<String> table_name = new ArrayList<>();
            if(ctx.table_query().getChildCount() > 1){
                for(SQLParser.Table_nameContext table_nameContext : ctx.table_query().table_name()){
                    table_name.add(table_nameContext.getText().toUpperCase());
                }
                select_row = new SelectJoinTableStatement(table_name, onConditions.get(0), whereConditions.get(0), select_resultColumn, isDistinct);
            }
            else{
                table_name.add(ctx.table_query().getChild(0).getText().toUpperCase());
                select_row = new SelectTableStatement(table_name.get(0), whereConditions.get(0), select_resultColumn, isDistinct);
            }
        }else{
            select_row = new SelectTableStatement();
            select_row.setValid(false);
            StringBuilder msg = new StringBuilder();
            for (SyntaxErrorException e : syntaxErrorExceptions){
                msg.append("; ").append(e.getMessage());
            }
            select_row.setMessage(msg.toString());
            syntaxErrorExceptions.clear();  // 清空错误集合
        }
        statements.add(select_row);
        onConditions.clear();
        whereConditions.clear();
    }
}
