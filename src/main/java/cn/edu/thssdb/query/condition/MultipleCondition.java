package cn.edu.thssdb.query.condition;


import cn.edu.thssdb.schema.Table;

import java.util.List;

public class MultipleCondition {
    public enum MULTI_TYPE {
        base, _and, _or
    }

    public enum OP_TYPE {
        eq, ne, le, ge, lt, gt  // =, !=, <=, >=, <, >
    }

    private MULTI_TYPE type;
    private OP_TYPE comparator;
    private List<ComparerData> comparerDataList;    // 2个comparer比较
    private List<MultipleCondition> multipleConditionList;  // 2个condition运算

    private Table[] tables; // 至多2个table, 对应于comparer
    private boolean hasChecked = false;

    public MultipleCondition(MULTI_TYPE type, MultipleCondition mul1, MultipleCondition mul2){
        this.type = type;
        this.multipleConditionList.add(mul1);
        this.multipleConditionList.add(mul2);
    }

    public MultipleCondition(OP_TYPE op_type, ComparerData com1, ComparerData com2){
        this.type = MULTI_TYPE.base;
        this.comparator = op_type;
        this.comparerDataList.add(com1);
        this.comparerDataList.add(com2);
    }

    // calculate, check Legal,
}
