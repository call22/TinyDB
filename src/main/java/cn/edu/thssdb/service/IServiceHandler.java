package cn.edu.thssdb.service;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.parser.SQLErrorListener;
import cn.edu.thssdb.parser.SQLLexer;
import cn.edu.thssdb.parser.SQLParser;
import cn.edu.thssdb.query.statement.Statement;
import cn.edu.thssdb.rpc.thrift.*;
import cn.edu.thssdb.schema.DBSManager;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.server.ThssDB;
import cn.edu.thssdb.utils.Global;
import cn.edu.thssdb.utils.LogManager;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.apache.thrift.TException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Dictionary;
import java.util.List;

public class IServiceHandler implements IService.Iface {
  private DBSManager dbsManager;

  public IServiceHandler(){
    this.dbsManager = DBSManager.getInstance();
  }

  @Override
  public GetTimeResp getTime(GetTimeReq req) throws TException {
    GetTimeResp resp = new GetTimeResp();
    resp.setTime(new Date().toString());
    resp.setStatus(new Status(Global.SUCCESS_CODE));
    return resp;
  }

  @Override
  public ConnectResp connect(ConnectReq req) throws TException {
    ConnectResp resp = new ConnectResp();
    // 检验用户名和密码
    dbsManager = DBSManager.getInstance();
    int id = dbsManager.login(req.username, req.password);
    if (id == -1) {  // 不合法
      resp.setStatus(new Status(Global.PASSWORD_ERROR_CODE));
      resp.setSessionId(-1);
    } else {
      resp.setStatus(new Status(Global.SUCCESS_CODE));
      resp.setSessionId(id);
    }
    return resp;
  }

  @Override
  public DisconnetResp disconnect(DisconnetReq req) throws TException {
    DisconnetResp resp = new DisconnetResp();
    if(!dbsManager.checkSessionId(req.sessionId)){
      resp.setStatus(new Status(Global.PASSWORD_ERROR_CODE));
    }
    else {
      try {
        dbsManager.logOut(req.sessionId);
        resp.setStatus(new Status(Global.SUCCESS_CODE));
      }catch (IOException e) {
        // manager断开处理失败
        //TODO 此时数据是否需要恢复
        resp.setStatus(new Status(Global.RUN_ERROR_CODE));
      }
    }
    return resp;
  }

  @Override
  public ExecuteStatementResp executeStatement(ExecuteStatementReq req) throws TException {
    ExecuteStatementResp resp = new ExecuteStatementResp();
    if(dbsManager.checkSessionId(req.sessionId)) {
      ArrayList<String> statementResult = new ArrayList<>();
      try {
        Listener listener = getListenerFromStatement(req.statement);
        System.out.println("get listener.");
        ArrayList<Statement> statements = listener.getStatements();
        resp.setStatus(new Status(Global.SUCCESS_CODE));
        for (Statement statement : statements) {
          if (statement.isValid()) {
//            System.out.println(statement);
            statementResult.add(statement.execute(dbsManager.getManager()).toString()); // 正常运行
          } else {
            System.out.println("execute error.");
            statementResult.add(statement.getMessage());  // 出错
            resp.setStatus(new Status(Global.RUN_ERROR_CODE));
            break;
          }
        }
        resp.setHasResult(true);
        resp.setStatementsResult(statementResult);
      } catch (RuntimeException e) {
        System.out.println("parser error.");
        resp.setStatus(new Status(Global.PARSE_ERROR_CODE));
        resp.setHasResult(false);
        statementResult.add("parser error: "+e.getMessage());
        resp.setStatementsResult(statementResult);
      }
    }
    else {
      System.out.println("need login.");
      resp.setStatus(new Status(Global.NEED_LOGIN_CODE));
      resp.setHasResult(false);
      resp.setStatementsResult(new ArrayList<>());
    }
    return resp;
  }

  private Listener getListenerFromStatement(String statement) throws SyntaxErrorException {
    CharStream in = CharStreams.fromString(statement);
    SQLLexer lexer = new SQLLexer(in);
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    SQLParser parser = new SQLParser(tokens);
    parser.removeErrorListeners();      // 去除默认的错误监听器
    parser.addErrorListener(new SQLErrorListener());    // 添加定制的错误监听器
    ParseTree tree = null;
    try{
      tree = parser.parse();

      if(LogManager.getIsTransaction()){
        LogManager.logList.add(statement);
      }else{
        LogManager.writeSingleLog(dbsManager.getManager(),statement);
      }


    } catch (RuntimeException e){
      System.out.println(e.getMessage());
      throw new SyntaxErrorException(e.getMessage());
    }catch (IOException e){
      System.out.println(e.getMessage());
    }
    Listener listener = new Listener();
    ParseTreeWalker.DEFAULT.walk(listener, tree);
    return listener;
  }
}
