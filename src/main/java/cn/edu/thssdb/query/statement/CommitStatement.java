package cn.edu.thssdb.query.statement;

import cn.edu.thssdb.query.Result;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.utils.LogManager;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class CommitStatement extends Statement{

  @Override
  public Result execute(Manager manager) throws RuntimeException {
    // 若无事务启动
    if (!LogManager.getIsTransaction()) {
      throw new RuntimeException("Fail to commit: no transaction before.");
    }
    Result result;
    String msg = "Successfully committed";
    // 所有排它锁都释放
    for (ReentrantReadWriteLock.WriteLock writeLock : LogManager.writeLocks) {
      writeLock.unlock();
    }
    LogManager.setIsTransaction(false);
    result = Result.setMessage(msg);
    return result;
  }
}
