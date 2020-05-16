package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Manager;

import java.util.ArrayList;

public class InsertTableStatement extends Statement {
    private String tableName;
    private ArrayList<String> columnsName;
    private ArrayList<String> rowValue;

    public InsertTableStatement(String tableName, ArrayList<String> columnsName, ArrayList<String> rowValue){
        this.tableName = tableName;
        this.columnsName = columnsName;
        this.rowValue = rowValue;
    }

    // 根据给定的值运行插入操作, 此处保证len(columnsName) == len(rowValue)
    @Override
    public Result execute(Manager manager) throws RuntimeException {
        return null;
    }
}
