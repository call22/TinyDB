package cn.edu.thssdb.service;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.parser.SQLErrorListener;
import cn.edu.thssdb.parser.SQLLexer;
import cn.edu.thssdb.parser.SQLParser;
import cn.edu.thssdb.query.statement.Statement;
import cn.edu.thssdb.rpc.thrift.*;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.utils.Global;
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
  private Manager manager;
  public IServiceHandler(Manager manager){
    this.manager = manager;
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
    // 检验用户名和密码
    ConnectResp resp = new ConnectResp();
    if(!manager.checkInfo(req.username, req.password)){
      resp.setStatus(new Status(Global.FAILURE_CODE));
      resp.setSessionId(-1);
    }
    else{
      resp.setStatus(new Status(Global.SUCCESS_CODE));
      Manager.id = Manager.id + 1;
      resp.setSessionId(Manager.id);
      manager.setSessionIds(Manager.id);
    }
    return resp;
  }

  @Override
  public DisconnetResp disconnect(DisconnetReq req) throws TException {
    DisconnetResp resp = new DisconnetResp();
    if(!manager.checkSessionId(req.sessionId)){
      resp.setStatus(new Status(Global.FAILURE_CODE));
    }
    else {
      resp.setStatus(new Status(Global.SUCCESS_CODE));
      manager.dropSessionIds(req.sessionId);
    }
    return resp;
  }

  @Override
  public ExecuteStatementResp executeStatement(ExecuteStatementReq req) throws TException {
    ExecuteStatementResp resp = new ExecuteStatementResp();
    if(manager.checkSessionId(req.sessionId)) {
      try {
        ArrayList<String> statementResult = new ArrayList<>();
        Listener listener = getListenerFromStatement(req.statement);
        System.out.println("get listener.");
        ArrayList<Statement> statements = listener.getStatements();
        for (Statement statement : statements) {
          if (statement.isValid()) {
            statementResult.add(statement.execute(manager).toString()); // 正常运行
          } else {
            System.out.println("execute error.");
            statementResult.add(statement.getMessage());  // 出错
            resp.setIsAbort(true);
          }
        }
        resp.setStatus(new Status(Global.SUCCESS_CODE));
        resp.setHasResult(true);
        resp.setStatementsResult(statementResult);
      } catch (RuntimeException e) {
        System.out.println("parser error.");
        resp.setStatus(new Status(Global.FAILURE_CODE));
        resp.setIsAbort(true);  // 失败, 但是用户登录了
        resp.setHasResult(false);
        resp.setStatementsResult(new ArrayList<>());
      }
    }
    else {
      System.out.println("need login.");
      resp.setStatus(new Status(Global.FAILURE_CODE));
      resp.setIsAbort(false); // 失败, 没有登录
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
    ParseTree tree;
    try{
      tree = parser.parse();
    } catch (RuntimeException e){
      System.out.println(e.getMessage());
      throw new SyntaxErrorException(e.getMessage());
    }
    Listener listener = new Listener();
    ParseTreeWalker.DEFAULT.walk(listener, tree);
    return listener;
  }
}
