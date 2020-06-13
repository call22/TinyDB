
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

import java.io.*;
import java.util.ArrayList;
import java.util.List;
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
    long sessionId = -1;
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
      /**添加数据生成代码*/

      List<String> insertStatements = loadInsertStatements();

      sessionId = connect();
      createDatabase(sessionId);
      useDatabase(sessionId);
      createTable(sessionId);
      insertData(sessionId, insertStatements);
      queryData(sessionId);


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
    } catch (TException | IOException e) {
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
  private static long connect(String username, String password) {
    ConnectReq req = new ConnectReq();
    long sessionId = -1L;
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
  private static void disconnect(long sessionId) {
    DisconnectReq req = new DisconnectReq();
    req.setSessionId(sessionId);
    try{
      DisconnectResp resp;
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
  private static void executeStatement(String msg, long sessionId) {
    ExecuteStatementReq req = new ExecuteStatementReq();
    req.setSessionId(sessionId);
//    req.setStatement(msg);

    // 对statement进行拆分.
    String[] statement = msg.split("(?<=;)");
    for(String state : statement) {
      println("<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<");
      try {
        req.setStatement(state);
        ExecuteStatementResp resp;
        resp = client.executeStatement(req);
        if (resp.status.getCode() == Global.PARSE_ERROR_CODE) {
          println(resp.getColumnsList().get(0));
        } else if (resp.status.getCode() == Global.RUN_ERROR_CODE) {
          println("exception occurs when execute statement.");
          println(resp.getColumnsList().get(0));
        } else if (resp.status.getCode() == Global.NEED_LOGIN_CODE) {
          println("please connect to system before you execute statement.");
        } else {
          // 输出结果为字符串
          print("| ");
          print(String.join(" | ", resp.getColumnsList()));
          println(" |");
          println("==========================================================");
          for (List<String> row : resp.getRowList()) {
            print("| ");
            print(String.join(" | ", row));
            println(" |");
          }
        }
      } catch (TException e) {
        logger.error(e.getMessage());
      }
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


  private static List<String> loadInsertStatements() throws IOException {
    List<String> statements = new ArrayList<>();
    File file = new File("insert_into.sql");
    if (file.exists() && file.isFile()) {
      FileInputStream fileInputStream = new FileInputStream(file);
      InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
      BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
      String line;
      while ((line = bufferedReader.readLine()) != null) {
        statements.add(line);
      }
      bufferedReader.close();
      inputStreamReader.close();
      fileInputStream.close();
    }
    return statements;
  }

  private static long connect() throws TException {
    String username = "username";
    String password = "password";
    ConnectReq req = new ConnectReq(username, password);
    ConnectResp resp = client.connect(req);
    if (resp.getStatus().code == Global.SUCCESS_CODE) {
      println("Connect Successfully!");
    } else {
      println("Connect Unsuccessfully!");
    }
    return resp.getSessionId();
  }

  private static void createDatabase(long sessionId) throws TException {
    String statement = "create database test;";
    ExecuteStatementReq req = new ExecuteStatementReq(sessionId, statement);
    ExecuteStatementResp resp = client.executeStatement(req);
    if (resp.getStatus().code == Global.SUCCESS_CODE) {
      println("Create Database Successfully!");
    } else {
      println(resp.getColumnsList().get(0));
//      println("Create Database Unsuccessfully!");
    }
  }

  private static void useDatabase(long sessionId) throws TException {
    String statement = "use test;";
    ExecuteStatementReq req = new ExecuteStatementReq(sessionId, statement);
    ExecuteStatementResp resp = client.executeStatement(req);
    if (resp.getStatus().code == Global.SUCCESS_CODE) {
      println("Use Database Successfully!");
    } else {
      println(resp.getColumnsList().get(0));
//      println("Use Database Unsuccessfully!");
    }
  }

  private static void createTable(long sessionId) throws TException {
    String[] statements = {
            "create table department (dept_name String(20), building String(15), budget Double, primary key(dept_name));",
            "create table course (course_id String(8), title String(50), dept_name String(20), credits Int, primary key(course_id));",
            "create table instructor (i_id String(5), i_name String(20) not null, dept_name String(20), salary Float, primary key(i_id));",
            "create table student (s_id String(5), s_name String(20) not null, dept_name String(20), tot_cred Int, primary key(s_id));",
            "create table advisor (s_id String(5), i_id String(5), primary key (s_id));" };
    for (String statement : statements) {
      ExecuteStatementReq req = new ExecuteStatementReq(sessionId, statement);
      ExecuteStatementResp resp = client.executeStatement(req);
      if (resp.getStatus().code == Global.SUCCESS_CODE) {
        println("Create Table Successfully!");
      } else {
        println(resp.getColumnsList().get(0));
//        println("Create Table Unsuccessfully!");
      }
    }
  }

  private static void insertData(long sessionId, List<String> statements) throws TException {
    long startTime = System.currentTimeMillis();
    boolean success = true;
    for (String statement : statements) {
      ExecuteStatementReq req = new ExecuteStatementReq(sessionId, statement);
      ExecuteStatementResp resp = client.executeStatement(req);
      if (resp.getStatus().code != Global.SUCCESS_CODE) {
        success = false;
        println(resp.getColumnsList().get(0));
      }
    }
    if (success) {
      println("Insert Data Successfully!");
    } else {
      println("Insert Data Unsuccessfully!");
    }
    println("It costs " + (System.currentTimeMillis() - startTime) + "ms.");
  }

  private static void queryData(long sessionId) throws TException {
    long startTime = System.currentTimeMillis();
    String[] statements = { "select s_id, s_name, dept_name, tot_cred from student;",
            "select course_id, title from course where credits >= 4;",
            "select s_id, s_name from student where dept_name = 'Physics';",
            "select course_id, title from course join department on course.dept_name = department.dept_name where building <> 'Palmer';",
            "select s_id from instructor join advisor on instructor.i_id = advisor.i_id where i_name = 'Luo';" };
    int[] results = { 2000, 92, 96, 182, 44 };
    for (int i = 0; i < statements.length; i++) {
      ExecuteStatementReq req = new ExecuteStatementReq(sessionId, statements[i]);
      ExecuteStatementResp resp = client.executeStatement(req);
      if (resp.getStatus().code == Global.SUCCESS_CODE) {
        println("Query Data Successfully!");
      } else {
//        println("Query Data Unsuccessfully!");
        println(resp.getColumnsList().get(0));
      }
      if (resp.getRowList().size() == results[i]) {
        println("The Result Set is Correct!");
      } else {
        println("The Result Set is Wrong!");
      }
    }
    println("It costs " + (System.currentTimeMillis() - startTime) + "ms.");
  }

}
