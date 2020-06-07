package cn.edu.thssdb.client;

import cn.edu.thssdb.rpc.thrift.*;
import cn.edu.thssdb.utils.Global;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.thrift.TException;
import org.apache.thrift.protocol.TBinaryProtocol;
import org.apache.thrift.protocol.TProtocol;
import org.apache.thrift.transport.TSocket;
import org.apache.thrift.transport.TTransport;
import org.apache.thrift.transport.TTransportException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.PrintStream;
import java.util.Scanner;

public class Client {

  private static final Logger logger = LoggerFactory.getLogger(Client.class);

  static final String HOST_ARGS = "h";
  static final String HOST_NAME = "host";

  static final String HELP_ARGS = "help";
  static final String HELP_NAME = "help";

  static final String PORT_ARGS = "p";
  static final String PORT_NAME = "port";

  private static final PrintStream SCREEN_PRINTER = new PrintStream(System.out);
  private static final Scanner SCANNER = new Scanner(System.in);

  private static TTransport transport;
  private static TProtocol protocol;
  private static IService.Client client;
  private static CommandLine commandLine;

  public static void main(String[] args) {
    int sessionId = -1;

    commandLine = parseCmd(args);
    if (commandLine.hasOption(HELP_ARGS)) {
      showHelp();
      return;
    }
    try {
      echoStarting();
      String host = commandLine.getOptionValue(HOST_ARGS, Global.DEFAULT_SERVER_HOST);
      int port = Integer.parseInt(commandLine.getOptionValue(PORT_ARGS, String.valueOf(Global.DEFAULT_SERVER_PORT)));
      transport = new TSocket(host, port);
      transport.open();
      protocol = new TBinaryProtocol(transport);
      client = new IService.Client(protocol);
      boolean open = true;
      while (true) {
        print(Global.CLI_PREFIX);
        String msg = SCANNER.nextLine();
        // 解析msg
        /**connect -u xxx  -p xxx;
         * disconnect;
         * showtime;
         * quit;*/
        String[] strings = msg.split(" ");
        long startTime = System.currentTimeMillis();
        switch (strings[0]) {
          case Global.SHOW_TIME:
            getTime();
            break;
          case Global.QUIT:
            open = false;
            break;
          case Global.HELP:
            showHelp();
            break;
          case Global.CONNECT:
            // 判断输入是否正确
            if(strings.length == 6 && strings[1].equals("-u") && strings[3].equals("-p") && strings[5].equals(";")){
              String username = strings[2];
              String password = strings[4];
              sessionId = connect(username, password);
            }else{
              println("invalid statement.");
            }
            break;
          case Global.DISCONNECT:
            disconnect(sessionId);
            break;
          default:
            // statement
            executeStatement(msg, sessionId);
            break;
        }
        long endTime = System.currentTimeMillis();
        println("It costs " + (endTime - startTime) + " ms.");
        if (!open) {
          break;
        }
      }
      transport.close();
    } catch (TTransportException e) {
      logger.error(e.getMessage());
    }
  }

  private static void getTime() {
    GetTimeReq req = new GetTimeReq();
    try {
      println(client.getTime(req).getTime());
    } catch (TException e) {
      logger.error(e.getMessage());
    }
  }

  /**connect*/
  private static int connect(String username, String password) {
    ConnectReq req = new ConnectReq();
    int sessionId = -1;
    req.setUsername(username);
    req.setPassword(password);
    try{
      ConnectResp resp = client.connect(req);
      sessionId = resp.sessionId;
      if(resp.status.getCode() == Global.PASSWORD_ERROR_CODE){  // 连接出错
        println("Fail to connect server, please check your username and password\n");
      }else if(resp.status.getCode() == Global.ALREADY_LOGIN_CODE){
        println("you already connect to system, please disconnect first.");
      }
      else{  // 连接正常
        println("Success to connect server\n");
      }
    } catch (TException e) {
      logger.error(e.getMessage());
    }
    return sessionId;
  }

  /**disconnect*/
  private static void disconnect(int sessionId) {
    DisconnetReq req = new DisconnetReq();
    req.setSessionId(sessionId);
    try{
      DisconnetResp resp;
      resp = client.disconnect(req);
      if(resp.status.getCode() == Global.PASSWORD_ERROR_CODE){
        println("Error sessionId, fail to disconnect, please connect first.");
      } else if(resp.status.getCode() == Global.RUN_ERROR_CODE){
        println("system run error, please try again.");
      }
      else{
        println("Success to disconnect.");
      }
    } catch (TException e) {
      logger.error(e.getMessage());
    }
  }

  /**statement运行*/
  private static void executeStatement(String msg, int sessionId) {
    ExecuteStatementReq req = new ExecuteStatementReq();
    req.setSessionId(sessionId);
    req.setStatement(msg);
    try{
      ExecuteStatementResp resp;
      resp = client.executeStatement(req);
      if(resp.status.getCode() == Global.PARSE_ERROR_CODE){
        println("fail to parse statement, please check your statement.");
        for(String result : resp.statementsResult){
          println(result);
        }
      } else if(resp.status.getCode() == Global.RUN_ERROR_CODE) {
        println("fail to run statement, please check your statement. ");
        for(String result : resp.statementsResult) {
          println(result);
        }
      } else if(resp.status.getCode() == Global.NEED_LOGIN_CODE) {
        println("please connect to system before you execute statement.");
      }
      else {
        // 运行全部正确
        for (String result : resp.statementsResult) {
          println(result);
        }
      }
    } catch (TException e) {
      logger.error(e.getMessage());
    }
  }

  static Options createOptions() {
    Options options = new Options();
    options.addOption(Option.builder(HELP_ARGS)
            .argName(HELP_NAME)
            .desc("Display help information(optional)")
            .hasArg(false)
            .required(false)
            .build()
    );
    options.addOption(Option.builder(HOST_ARGS)
            .argName(HOST_NAME)
            .desc("Host (optional, default 127.0.0.1)")
            .hasArg(false)
            .required(false)
            .build()
    );
    options.addOption(Option.builder(PORT_ARGS)
            .argName(PORT_NAME)
            .desc("Port (optional, default 6667)")
            .hasArg(false)
            .required(false)
            .build()
    );
    return options;
  }

  static CommandLine parseCmd(String[] args) {
    Options options = createOptions();
    CommandLineParser parser = new DefaultParser();
    CommandLine cmd = null;
    try {
      cmd = parser.parse(options, args);
    } catch (ParseException e) {
      logger.error(e.getMessage());
      println("Invalid command line argument!");
      System.exit(-1);
    }
    return cmd;
  }

  static void showHelp() {
    println("1. connect to server:");
    println("connect -u username -p password ;");
    println("2. execute statement:");
    println("your statement");
    println("3. disconnect from server:");
    println("disconnect;");
    println("4. show time:");
    println("showtime;");
    println("5. quit client");
    println("quit;");
  }

  static void echoStarting() {
    println("----------------------");
    println("Starting ThssDB Client");
    println("----------------------");
  }

  static void print(String msg) {
    SCREEN_PRINTER.print(msg);
  }

  static void println(String msg) {
    SCREEN_PRINTER.println(msg);
  }
}