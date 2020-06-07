namespace java cn.edu.thssdb.rpc.thrift

struct Status {
  1: required i32 code;
  2: optional string msg;
}

struct GetTimeReq {
}

struct ConnectReq{
  1: required string username
  2: required string password
}

struct ConnectResp{
  1: required Status status
  2: required i32 sessionId
}

struct DisconnetReq{
  1: required i32 sessionId
}

struct DisconnetResp{
  1: required Status status
}

struct GetTimeResp {
  1: required string time
  2: required Status status
}

struct ExecuteStatementReq {
  1: required i32 sessionId
  2: required string statement
}

struct ExecuteStatementResp{
  1: required Status status
  2: required bool hasResult
  3: optional list<string> statementsResult
}

service IService {
  GetTimeResp getTime(1: GetTimeReq req);
  ConnectResp connect(1: ConnectReq req);
  DisconnetResp disconnect(1: DisconnetReq req);
  ExecuteStatementResp executeStatement(1: ExecuteStatementReq req);
}
