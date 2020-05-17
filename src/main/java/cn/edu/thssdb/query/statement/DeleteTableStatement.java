package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.MultipleCondition;
import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.*;

import java.io.IOException;
import java.util.Iterator;

public class DeleteTableStatement extends Statement {
    private String tableName;
    private MultipleCondition multipleCondition;    // 删除的条件

    public DeleteTableStatement(String tableName, MultipleCondition multipleCondition){
        this.tableName = tableName;
        this.multipleCondition = multipleCondition;
    }

    @Override
    public Result execute(Manager manager) throws RuntimeException {
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
                            if(multipleCondition.calculate(iterator.next())){
                                table.delete(iterator.next());
                                times++;
                            }
                        }
                    }
                    case 2:{
                        while (iterator.hasNext()){
                            Row temp = iterator.next();
                            if(multipleCondition.calculate(temp, temp)){
                                table.delete(iterator.next());
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
    //TODO
    // 考虑delete失败后, 已经被删除的数据如何恢复
}