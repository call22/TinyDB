package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.ComparerData;
import cn.edu.thssdb.query.MetaInfo;
import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class DeleteTableStatement extends Statement {
    private String tableName;
    private MultipleCondition multipleCondition;    // 删除的条件

    public DeleteTableStatement(){
        tableName = "error";
    }

    public DeleteTableStatement(String tableName, MultipleCondition multipleCondition){
        this.tableName = tableName;
        this.multipleCondition = multipleCondition;
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
        /** set MetoInfo 到multipleCondition*/
        setMetaInfo(tableName, manager, multipleCondition);

        /** next */
        Result result = null;
        String msg = "[delete row in table]: " + this.tableName;
        Database db = manager.getCurrentDB();
        try{
            int times = 0;
            Table table = db.selectTable(tableName);
            Iterator<Row> iterator = table.iterator();
            if(multipleCondition.check()){
                int type = multipleCondition.getTypes();
                switch (type){
                    case 0:{
                        if(multipleCondition.calculate()){
                            // 全部满足
                            while(iterator.hasNext()){
                                table.delete(iterator.next());
                                times++;
                            }
                        }
                    }
                    case 1:{
                        while (iterator.hasNext()){
                            Row row = iterator.next();
                            if(multipleCondition.calculate(row)){
                                table.delete(row);
                                times++;
                            }
                        }
                    }
                    case 2:{
                        while (iterator.hasNext()){
                            Row temp = iterator.next();
                            if(multipleCondition.calculate(temp, temp)){
                                table.delete(temp);
                                times++;
                            }
                        }
                    }
                    default:
                        break;
                }
            }else{
                // multipleCondition形式不正确
                throw new RuntimeException("condition conflict");
            }
            result = Result.setMessage("Successfully" + msg + ", delete " + times +" rows");
        }catch (IOException e){
            throw new RuntimeException("fail to " + msg + e.getMessage());
        }
        return result;
    }

    /**
     * 设置metaInfo
     * @param tableName 当前table名
     * @param manager 当前manager
     * @param multipleCondition 条件*/
    static void setMetaInfo(String tableName, Manager manager, MultipleCondition multipleCondition) {
        List<ComparerData> comparerDatas = multipleCondition.getComparerDataList();
        ArrayList<ComparerData> comparerDataArrayList = new ArrayList<>();
        for(ComparerData comparerData1 : comparerDatas){
            if(comparerData1.getComparerType() == ComparerData.COMPARER_TYPE.table_column){
                String meta_table_name = comparerData1.getTableName() == null ? tableName : comparerData1.getTableName();
                comparerDataArrayList.add(new ComparerData(meta_table_name, comparerData1.getColumnName()));
                MetaInfo meta = new MetaInfo(meta_table_name, manager.getCurrentDB().selectTable(meta_table_name).getColumns());
                multipleCondition.setMetaInfos(meta);
            }else{
                comparerDataArrayList.add(comparerData1);
            }
        }
        multipleCondition.setComparator(comparerDataArrayList);
    }
    //TODO
    // 考虑delete失败后, 已经被删除的数据如何恢复
}