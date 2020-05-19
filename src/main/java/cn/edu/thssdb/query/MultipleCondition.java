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
    private int comp_index = 0;         // 当只有一个comp时, 记录comp出现的位置

    public MultipleCondition(OP_TYPE op_type, ComparerData com1, ComparerData com2){
        this.comparator = op_type;
        this.comparerDataList.add(com1);
        this.comparerDataList.add(com2);
    }

    /**
     * 补丁: 因为初期获取不了metaInfo*/
    public void setMetaInfos(MetaInfo meta){
        this.metaInfos.add(meta);
    }

    public List<MetaInfo> getMetaInfos() {
        return this.metaInfos;
    }

    public List<ComparerData> getComparerDataList() {
        return comparerDataList;
    }

    /**
     * 检查com1, com2所要求的table和attr是否是meta1, meta2所提供的
     * 满足: true
     * 不满足: false*/
    public boolean check(){
        //TOdo 这里有问题
        index.clear();
        int i = 0;
        for(ComparerData comp : comparerDataList){
            if(comp.getComparerType() == ComparerData.COMPARER_TYPE.table_column){
                String tableName = comp.getTableName();
                String columnName = comp.getColumnName();
                if(!tableName.equals(metaInfos.get(i).getTableName()) || metaInfos.get(i).columnFind(columnName) == -1)
                    return false;
                index.add(metaInfos.get(i).columnFind(columnName));    // 索引attr位置
            }
            i++;
        }
        return true;
    }

    /**
     * 0: num op num,
     * 1: row op num, num op row
     * 2: row op row,
     * */
    public int getTypes(){  // compare的类型,
        int count = 0;
        int i=0;
        for(ComparerData comp : comparerDataList){
            if(comp.getComparerType() == ComparerData.COMPARER_TYPE.table_column){
                count++;
                comp_index = i;
            }
            i++;
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

    public boolean calculate(Row row){  // row op num   / num op row
        Entry e1, e2;
        if(comp_index == 0) {
            e1 = row.getEntries().get(index.get(0));
            if (e1 == null || comparerDataList.get(1).getComparerType() == ComparerData.COMPARER_TYPE._null)
                return false;
            e2 = comparerDataList.get(1).getData();
        }else{
            e2 = row.getEntries().get(index.get(0));
            if (e2 == null || comparerDataList.get(0).getComparerType() == ComparerData.COMPARER_TYPE._null)
                return false;
            e1 = comparerDataList.get(0).getData();
        }
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
