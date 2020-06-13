package cn.edu.thssdb.utils;

public class Global {
  public static int fanout = 129;
  public static String dirPath="DBS//";
  public static String infoPath="userInfo//userInfo.info";

  /**返回的status信息:
   * 200: 正常
   * 400: 用户未登录
   * 401: 语法解析错误
   * 402: 语句运行错误
   * 403: 用户已经登录
   * 404: IO读取出错
   * 405: 用户名密码错误/sessionId错误*/
  public static int SUCCESS_CODE = 200;
  public static int NEED_LOGIN_CODE = 400;
  public static int PARSE_ERROR_CODE = 401;
  public static int RUN_ERROR_CODE = 402;
  public static int ALREADY_LOGIN_CODE = 403;
  public static int IO_ERROR_CODE = 404;
  public static int PASSWORD_ERROR_CODE = 405;

  public static String DEFAULT_SERVER_HOST = "127.0.0.1";
  public static int DEFAULT_SERVER_PORT = 6667;

  public static String CLI_PREFIX = "ThssDB>";
  public static final String SHOW_TIME = "showtime;";
  public static final String QUIT = "quit;";
  public static final String CONNECT = "connect";
  public static final String DISCONNECT = "disconnect;";
  public static final String HELP = "help;";
  public static final String S_URL_INTERNAL = "jdbc:default:connection";
}
