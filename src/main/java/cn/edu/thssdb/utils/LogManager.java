package cn.edu.thssdb.utils;

import java.util.ArrayList;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class LogManager {
  private static Boolean isTransaction = false;   // 事务开始时true，事务commit时false
  public static ArrayList<ReentrantReadWriteLock.WriteLock> writeLocks;
  private String logFileName;

  public static Boolean getIsTransaction() {
    return isTransaction;
  }

  public static void setIsTransaction(Boolean isTransaction) {
    LogManager.isTransaction = isTransaction;
  }

  /**
   * 若当前是事务状态，则插入排它锁至list
   * @param writeLock table的排它锁
   */
  public static void addWritelock(ReentrantReadWriteLock.WriteLock writeLock) {
    if (isTransaction) {
      writeLock.lock();
      writeLocks.add(writeLock);
    }
  }

  /**
   * 在table执行写操作时，若发现异常
   * 且当前状态是事务状态，则删除最近插入的排它锁
   */
  public static void removeWritelock() {
    if (isTransaction) {
      ReentrantReadWriteLock.WriteLock writeLock =
              writeLocks.remove(writeLocks.size()-1);
      writeLock.unlock();
    }
  }

  /**
   * 写一条log
   */
  public void writeLog() {
  }
  /**
   * 读一条log
   */
  public void readLog() {

  }
  public void deleteLogs() {
  }

  /**
   * 查看log文件是否有log内容
   * @return true: 有 false: 空
   */
  public Boolean checkHasLog() {
    return false;
  }
}
