package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.utils.LogManager;

import java.io.IOException;

public class CheckPointStatement extends Statement {

    @Override
    public Result execute(Manager manager) throws RuntimeException{
        if (LogManager.getIsTransaction()) {
            throw new RuntimeException("Transaction not committed yet");
        }
        Result result;
        String msg = "Successfully persist";

        try{
            manager.persist();
            for(Database db :manager.getDatabases()){
                LogManager.deleteLogs(db);
            }
        }catch (IOException e){
            throw new RuntimeException("Fail to " + msg + e.getMessage());
        }
        result = Result.setMessage(msg);
        return result;
    }

}
