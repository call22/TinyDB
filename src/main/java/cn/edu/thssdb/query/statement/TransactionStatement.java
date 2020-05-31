package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.utils.LogManager;

import java.util.ArrayList;

public class TransactionStatement extends Statement{

  @Override
  public Result execute(Manager manager) throws RuntimeException {
    // 若之前有事务尚未提交
    if (LogManager.getIsTransaction()) {
      throw new RuntimeException("Fail to start transaction: last transaction has not committed.");
    }
    Result result;
    String msg = "[Start transaction...]";
    LogManager.setIsTransaction(true);
    LogManager.writeLocks = new ArrayList<>();
    result = Result.setMessage(msg);
    return result;
  }
}
