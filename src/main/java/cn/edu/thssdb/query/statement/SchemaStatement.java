package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.type.ColumnType;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class SchemaStatement extends Statement {
    public enum OP_TYPE{
        create_db, drop_db, use_db, drop_table,
        show_db, show_table, show_meta
    }
    private OP_TYPE type;   // 标识具体schema操作

    private String databaseName = "";    // 统一用大写字母
    private String tableName = "";

    /**
     * 初始化schema类型的statement, 因为op_type中执行简单相似,故
     * 放于同一个类中
     * @param opType 标识schemaStatement的类型
     * @param databaseName 如果涉及数据库则传入databaseName
     * @param tableName 如果涉及表单则传入对应tableName
     * */
    public SchemaStatement(OP_TYPE opType, String databaseName, String tableName){
        this.type = opType;
        if (!databaseName.equals("")){
            this.databaseName = databaseName.toUpperCase();
        }
        if(!tableName.equals("")){
            this.tableName = tableName.toUpperCase();
        }
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException{
        Result result = null;
        switch (this.type){
            case create_db: {
                String msg = "[create database]: " + this.databaseName;
                try {
                    manager.createDatabaseIfNotExists(this.databaseName);
                    result = Result.setMessage("Successfully " + msg);
                } catch (DuplicateKeyException e) {
                    result = Result.setMessage("database name: " + this.databaseName + " already exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg + " Unknown Error");    // 运行出错, 反馈到server
                }
                break;
            }
            case drop_db: {
                String msg = "[drop database]: " + this.databaseName;
                try {
                    manager.deleteDatabase(this.databaseName);
                    result = Result.setMessage("Successfully " + msg);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg + " Unknown Error");
                }
                break;
            }
            case use_db: {
                String msg = "[switch database]: " + this.databaseName;
                try {
                    manager.switchDatabase(this.databaseName);
                    result = Result.setMessage("successfully " + msg + ", current database: " + this.databaseName);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg + " Unknown Error" + "\n current database: " + manager.getCurrentDB());
                }
                break;
            }
            case drop_table: {
                String msg = "[drop table]: " + this.tableName;
                try {
                    manager.getCurrentDB().drop(this.tableName);
                    result = Result.setMessage("successfully " + msg);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg + " Unknown Error");
                }
                break;
            }
            case show_db:{
                LinkedList<Database> dbs = manager.getDatabases();
                result = new Result();
                // 【数据库名称 | 包含的表单数】
                Column dbName = new Column("Database Name", ColumnType.STRING, 1, true, 255);
                Column tableNum = new Column("Table Number", ColumnType.INT, -1, true, 0);
                result.setColumns(new Column[]{dbName, tableNum});
                for(Database db : dbs){
                    result.addRow(new Row(new Entry[]{new Entry(db.getName()), new Entry(db.getTables().size())}), false, true);
                }
                break;
            }
            case show_table:{
                LinkedList<Table> tables = manager.getCurrentDB().getTables();
                result = new Result();
                // 【表单名称 | 包含属性数】
                Column tableName = new Column("Table Name", ColumnType.STRING, 1, true, 255);
                Column columnNum = new Column("Column Number", ColumnType.INT, -1, true, 0);
                result.setColumns(new Column[]{tableName, columnNum});
                for(Table tb : tables){
                    result.addRow( new Row(new Entry[]{new Entry(tb.getTableName()), new Entry(tb.getColumns().size())}), false, true);
                }
                break;
            }
            case show_meta:{
                ArrayList<Column> columns = manager.getCurrentDB().selectTable(this.tableName).getColumns();
                result = new Result();
                // 【属性名 | 类型 | 主键 | 是否为空 | 最大长度】
                Column columnName = new Column("Column Name", ColumnType.STRING, 1, true, 255);
                Column type = new Column("Type", ColumnType.STRING, -1, true, 7);
                Column primary = new Column("Is Primary", ColumnType.INT, -1, true, 0);
                Column isNull = new Column("Is Null", ColumnType.INT, -1, true, 0);
                Column maxLength = new Column("Max Length", ColumnType.INT, -1, true, 0);
                result.setColumns(new Column[]{columnName, type, primary, isNull, maxLength});
                for(Column column : columns){
                    result.addRow( new Row(new Entry[]{new Entry(column.getName()), new Entry(column.getType().toString())
                    , new Entry(column.isPrimary() ? 1 : 0), new Entry(column.getNull()), new Entry(column.getMaxLength())}), false, false);
                }
            }
            default:
                break;
        }
        return result;
    }


    private String toString(ColumnType type){
        switch (type){
            case INT:{
                return "INT";
            }
            case LONG:{
                return "LONG";
            }
            case FLOAT:{
                return "FLOAT";
            }
            case DOUBLE:{
                return "DOUBLE";
            }
            case STRING:{
                return "STRING";
            }
            default:
                return "";
        }
    }
}
