package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.query.ResultColumn;
import cn.edu.thssdb.schema.Manager;

import java.util.List;

public class SelectJoinTableStatement extends Statement {

    private List<String> tableName;     // 2个table
    // 需要考虑join顺序和on中比较的table顺序不一样. --> 怎么固定顺序.
    private MultipleCondition onCondition;
    private MultipleCondition whereCondition;
    private ResultColumn resultColumn;  //最终返回形式

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        return null;
    }
}
