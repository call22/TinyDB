package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.exception.DuplicateKeyException;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;

import java.io.IOException;
import java.util.ArrayList;

public class CreateTableStatement extends Statement {
    private String tableName;
    private Column[] columns;

    /**
     * 初始化
     * */
    public CreateTableStatement(){
        this.tableName = "error";    // 当语法出错时这样初始化
    }
    public CreateTableStatement(String tableName, ArrayList<Column> columns){
        // 检查 类型为string的column是否给定了maxlength, 在解析时完成
        this.tableName = tableName;
        this.columns = columns.toArray(new Column[0]);
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        Result result;
        String msg = "[create table]: " + this.tableName;
        Database db = manager.getCurrentDB();
        try{
            db.create(tableName, columns);
            result = Result.setMessage("Successfully " + msg);
        } catch (IOException e) {
            throw new RuntimeException("Fail to " + msg + e.getMessage());
        } catch (DuplicateKeyException e){
            result = Result.setMessage("table name: " + this.tableName + " already exists");
            this.setValid(false);
        }
        return result;
    }
}
