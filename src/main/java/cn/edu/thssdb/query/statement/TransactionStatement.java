package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.utils.LogManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class TransactionStatement extends Statement{

  @Override
  public Result execute(Manager manager) throws RuntimeException {
    // 若之前有事务尚未提交
    if (LogManager.getIsTransaction()) {
      throw new RuntimeException("Fail to start transaction: last transaction has not committed.");
    }
    // 首先序列化索引树
    LinkedList<Table> tables = manager.getCurrentDB().getTables();
    for (Table table : tables) {
      try {
        table.serialize();
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    Result result;
    String msg = "[Start transaction...]";
    LogManager.setIsTransaction(true);
    LogManager.writeLocks = new ArrayList<>();
    result = Result.setMessage(msg);
    return result;
  }
}
