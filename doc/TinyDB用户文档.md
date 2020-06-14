# Tiny DB用户说明文档

### 1、系统的启动和连接

#### 1.1连接到服务器

当用户username和password被记录到user.info文件中后，

```plain
connect -u <username> -p <password> ;
```
* 连接到系统服务器。
#### 1.2显示时间

```plain
showtime;                           
```
* 显示当前时间
### 2、数据库的管理和使用

启动系统后若无数据库，则默认创建并使用默认数据库TEST。

#### 2.1查看所有数据库

```plain
show databases;
```
信息显示格式如下：
*| DATABASE NAME| TABLE NUMBER|*

================================

| TEST | 1 |

#### 2.2查看某个数据库信息

```plain
show database <dbname>;
```
信息显示格式如下：
*| TABLE NAME| COLUMN NUMBER|*

*================================*

| INSTRUCTOR | 4 | 

#### 2.3切换到某个数据库并开始使用

```plain
use <dbname>;
```
### 3、在数据库内操作

进入到某个确定数据库后可在数据库中创建、删除、修改、查询其中的表。

#### 3.1创建表

```plain
CREATE TABLE <tableName>(attrName1 Type1, attrName2 Type2,…,attrNameN TypeN NOT NULL, PRIMARY KEY(attrName1));
```
* 当该数据库不包含该表时可按照提供的列信息创建，否则不能重复创建；
* PRIMARY KEY仅针对一列，用来表明主键；
* NOT NULL用来表示该列元素不可为NULL；
* 数据类型Type为Int，Long，Float，Double，String（必须给出长度）之一。
#### 3.2删除表

```plain
DROP TABLE <tableName>;
```
* 当数据库中包含该表时可删除，否则返回错误
#### 3.3展示表的模式信息

```plain
show table <tablename>;
```
信息显示格式如下：
*| COLUMN NAME | TYPE | IS PRIMARY | IS NULL | MAX LENGTH |*

*==========================================================*

*| S_ID | STRING | 1 | false | 5 |*

*| I_ID | STRING | 0 | false | 5 |*

#### 3.4对表中的信息操作

##### 3.4.1插入行

```plain
INSERT INTO [tableName(attrName1, attrName2,…, attrNameN)] VALUES (attrValue1, attrValue2,…, attrValueN)
```
* 字符串需要用单引号包围，如‘studentName’
* 插入时缺失某NOT NULL列，会插入失败
* 每个列的信息都不缺失时可省略[tableName(attrName1, attrName2,…, attrNameN)]中列属性名
##### 3.4.2删除行

删除满足where后条件的行。

```plain
DELETE FROM tableName WHERE attrName = attValue
```
##### 3.4.3修改行

对满足特定要求的行，修改其中特定的信息。

```plain
UPDATE tableName SET attrName = attrValue WHERE attrName = attrValue
```
##### 3.4.4查询

从某一或某些表中查询满足特定要求的信息。

```plain
SELECT [distinct] attrName1, attrName2, … attrNameN  FROM  tableName [ WHERE attrName1 = attrValue]
SELECT tableName1.AttrName1, tableName1.AttrName2…, tableName2.AttrName1, tableName2.AttrName2,…  FROM tableName1 JOIN tableName2 ON tableName1.attrName1 = tableName2.attrName2 [ WHERE attrName1 = attrValue ]
```
* Where子句至多涉及一个比较，关系为‘’<“,”>”,”<=”,”>=”,”=”,”<>”之一
* Select子句不包含表达式
* Join至多涉及2张表
* On子句的限制同Where子句。
#### 3.5事务和恢复

##### 3.5.1开始一个事务

```plain
begin transaction;
```
##### 3.5.2事务提交

```plain
commit;
```
##### 3.5.3checkpoint

```plain
checkpoint;
```
* 保证截止到目前为止的操作不会丢失。
##### 3.5.4恢复

* 当系统异常退出时，重启数据库，只要.log文件未损坏，之前的操作均可恢复。
### 4、从服务端断开连接

```plain
disconnect;
```
* 切断与服务器连接，保存当前操作
### 5、退出客户端

```plain
quit;
```
* 在断开与服务端连接后安全退出client
### 6、多个客户端

* 可同时运行多个client
* 一个客户端在事务运行中时，另一个客户端将无法对该事务操作的数据进行读写操作。
* 当所有client都与server断开连接时，系统将之前的所有操作持久化。
### 7、系统的报错

当用户输入指令存在语法错误或在系统中存在执行错误时会产生报错，用户可参考报错信息检查并重写指令。

```shell
please connect to system before you execute statement.
                                 未连接到服务端，需先进行连接(参1.1)
Fail to connect server, please check your username and password
                                 用户名或密码错误
you already connect to system, please disconnect first.
                                 更换用户名需先切断连接(参4.从服务器断开连接)
empty command!                   无效空指令
invalid statement.               指令存在语法错误，具体语法参1～5
[dbname/tablename] already exists!
                                 待创建的数据库/表已经存在
[dbname/tablename] doesn't exists!
                                 待删除/添加的数据库/表不存在    
system run error, please try again.
                                 断开连接时server端出错，断开连接失败
'word' syntax error.
                                 statement第一个word语法错误
line [line] : [charPositionInLine] at [offendingSymbol] : [msg]
                                 statement中语法报错【1】                                
Fail to [statement] [msg]
                                 statement执行过程中出现系统错误，执行失败
```
其他报错信息根据语义可以理解。
【1】：报错示例：

输入： use database db；

输入：line 1:4at[@1,4:11='database',<24>,1:4]: extraneous input 'database' expecting IDENTIFIER

