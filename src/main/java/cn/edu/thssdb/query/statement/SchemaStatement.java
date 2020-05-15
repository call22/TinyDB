package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.exception.KeyNotExistException;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.type.ColumnType;

import java.io.IOException;
import java.util.LinkedList;

public class SchemaStatement extends Statement {
    public enum OP_TYPE{
        create_db, drop_db, use_db, drop_table,
        show_db, show_table
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
                String msg = "create database: " + this.databaseName;
                try {
                    manager.createDatabaseIfNotExists(this.databaseName);
                    result = Result.setMessage("Successfully " + msg);
                } catch (DuplicateKeyException e) {
                    result = Result.setMessage("database name: " + this.databaseName + " already exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg);    // 运行出错, 反馈到server
                }
                break;
            }
            case drop_db: {
                String msg = "drop database: " + this.databaseName;
                try {
                    manager.deleteDatabase(this.databaseName);
                    result = Result.setMessage("Successfully " + msg);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg);
                }
                break;
            }
            case use_db: {
                String msg = "switch database: " + this.databaseName;
                try {
                    manager.switchDatabase(this.databaseName);
                    result = Result.setMessage("successfully " + msg + ", current database: " + this.databaseName);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg + ", current database: " + manager.getCurrentDB());
                }
                break;
            }
            case drop_table: {
                String msg = "drop table: " + this.tableName;
                try {
                    manager.getCurrentDB().drop(this.tableName);
                    result = Result.setMessage("successfully " + msg);
                } catch (KeyNotExistException e) {
                    result = Result.setMessage("database name: " + this.databaseName + "doesn't exists");
                } catch (IOException e) {
                    throw new RuntimeException("Fail to " + msg);
                }
                break;
            }
            case show_db:{
                LinkedList<Database> dbs = manager.getDatabases();
                result = new Result();
                result.setColumns(new Column[]{new Column("database", ColumnType.STRING, 1, true, 256)});
                for(Database db : dbs){
                    result.addRow(new Row(new Entry[]{new Entry(db.getName())}), false, true);
                }
                break;
            }
            case show_table:{
                LinkedList<Table> tables = manager.getCurrentDB().getTables();
                result = new Result();
                result.setColumns(new Column[]{ new Column("table", ColumnType.STRING, 1, true, 256)});
                for(Table tb : tables){
                    result.addRow( new Row(new Entry[]{new Entry(tb.getTableName())}), false, true);
                }
                break;
            }
            default:
                break;
        }
        return result;
    }
}
