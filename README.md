# TinyDB
a tiny and naive database based on ThssDB.

#### 一、文件目录

```
|- TinyDB
  |- pom.xml
  |- README.md                程序运行环境及方式
  |- sqlResources             测试所用的SQL文件
  |- src
    |- main                   程序源代码
    |- test                   测试文件
  |- target
  |- userinfo
    |- userinfo.info          记录用户信息（username password）								
  |- Thssdb.iml
```



#### 二、编程环境

`SDK java version1.8`

`Maven`

#### 三、运行方式

`Maven clean` `Maven install`安装相关依赖

依次运行`server/server`和`client/client`

然后即可开始在client端操作。

可同时运行多个客户端。

