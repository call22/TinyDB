package cn.edu.thssdb.query;

import cn.edu.thssdb.schema.Row;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
/**multiple Condition在此判断, 则最终返回Result?*/
public class QueryResult {

  private List<MetaInfo> metaInfos;
  private List<Integer> index;  // 记录Column对应columns的位置

  public QueryResult(QueryTable[] queryTables) {
    // TODO
    this.index = new ArrayList<>();
  }

  public void setResultMeta(MetaInfo resultMeta){   // 对应generateQueryRecord
    metaInfos.add(resultMeta);
  }

  // A join B 合成
  public static Row combineRow(LinkedList<Row> rows) {
    // TODO
    return null;
  }

  public Row generateQueryRecord(Row row) { // metaInfos最后一个为select期待的返回情况

    // TODO
    return null;
  }

  /**
   * 返回Result.*/
  public Result generateResult(){
    Result result = new Result();
    //TODO

    // case1: querytables.size == 1
        // iterator, 获取满足condition的row, 并generateQueryRecord, 插入result中
    // case2: join
        // 若where的condition只有0,1两种情况, 将其涉及的table放在外层, 之后双重循环获得result.
    return null;
  }
}