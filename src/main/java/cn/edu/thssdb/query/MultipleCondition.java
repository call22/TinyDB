package cn.edu.thssdb.query;


import cn.edu.thssdb.schema.Entry;
import cn.edu.thssdb.schema.Row;

import java.util.List;

public class MultipleCondition {
    public enum OP_TYPE {
        eq, ne, le, ge, lt, gt  // =, !=, <=, >=, <, >
    }

    private OP_TYPE comparator;
    private List<ComparerData> comparerDataList;    // 2个comparer比较
    private List<MetaInfo> metaInfos;
    private List<Integer> index;

    public MultipleCondition(OP_TYPE op_type, ComparerData com1, ComparerData com2, MetaInfo meta1, MetaInfo meta2){
        this.comparator = op_type;
        this.comparerDataList.add(com1);
        this.comparerDataList.add(com2);
        this.metaInfos.add(meta1);
        this.metaInfos.add(meta2);
    }

    /**
     * 检查com1, com2所要求的table和attr是否是meta1, meta2所提供的
     * 满足: true
     * 不满足: false*/
    public boolean check(){
        index.clear();
        boolean flag = false;   // 只能row op num, 不能num op row
        int i = 0;
        for(ComparerData comp : comparerDataList){
            if(comp.getComparerType() == ComparerData.COMPARER_TYPE.table_column){
                String tableName = comp.getTableName();
                String columnName = comp.getColumnName();
                if(!tableName.equals(metaInfos.get(i).getTableName()) || metaInfos.get(i).columnFind(columnName) == -1 || flag)
                    return false;   // flag == true ==> num op row
                index.add(metaInfos.get(i).columnFind(columnName));    // 索引attr位置
            }else {
                flag = true;
            }
            i++;
        }
        return true;
    }

    /**
     * 0: num op num,
     * 1: row op num,
     * 2: row op row,
     * */
    public int getTypes(){  // compare的类型,
        int count = 0;
        for(ComparerData comp : comparerDataList){
            if(comp.getComparerType() == ComparerData.COMPARER_TYPE.table_column)
                count++;
        }
        return count;
    }

    public boolean calculate(){ // num op num
        for(ComparerData comp : comparerDataList){
            if(comp.getComparerType() == ComparerData.COMPARER_TYPE._null)
                return false;
        }
        Entry e1=comparerDataList.get(0).getData();
        Entry e2=comparerDataList.get(1).getData();
        return compare(e1, e2);
    }

    public boolean calculate(Row row){  // row op num
        Entry e1 = row.getEntries().get(index.get(0));
        if(e1 == null || comparerDataList.get(1).getComparerType() == ComparerData.COMPARER_TYPE._null)
            return false;
        Entry e2 = comparerDataList.get(1).getData();
        return compare(e1, e2);
    }

    public boolean calculate(Row row1, Row row2){   // row op row
        Entry e1 = row1.getEntries().get(index.get(0));
        Entry e2 = row2.getEntries().get(index.get(1));
        if(e1 == null || e2 == null)
            return false;
        return compare(e1, e2);
    }

    /**
     * 对e值为null情况, 在calculate时检查
     * 对e1, e2对应类型不同情况, 报错throw RuntimeException*/
    private boolean compare(Entry e1, Entry e2){
        try {
            switch (this.comparator) {
                case eq:
                    return e1.equals(e2);
                case ge:
                    return e1.equals(e2) || e1.compareTo(e2) > 0;
                case gt:
                    return e1.compareTo(e2) > 0;
                case le:
                    return e1.equals(e2) || e1.compareTo(e2) < 0;
                case lt:
                    return e1.compareTo(e2) < 0;
                case ne:
                    return !e1.equals(e2);
                default:
                    return false;
            }
        }catch (ClassCastException e){
            throw new RuntimeException("[compare error] class type conflict");
        }
    }
}
