package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.query.ResultColumn;
import cn.edu.thssdb.schema.Manager;

public class SelectTableStatement extends Statement {
    private String tableName;   // 表名
    MultipleCondition whereCondition;   // where条件
    ResultColumn resultColumn;  // 返回形式

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        return null;
    }
}
