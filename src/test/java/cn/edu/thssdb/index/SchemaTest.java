//package cn.edu.thssdb.index;
//
//import cn.edu.thssdb.schema.*;
//import cn.edu.thssdb.type.ColumnType;
//import org.junit.Test;
//
//import java.io.IOException;
//import java.util.ArrayList;
//
//public class SchemaTest {
//    Column[] columns1 = {
//            new Column("id".toUpperCase(), ColumnType.INT, true, true, 0),
//            new Column("name".toUpperCase(), ColumnType.STRING, false, true, 16)
//    };
//    Column[] columns2 = {
//            new Column("name".toUpperCase(), ColumnType.STRING, true, true, 0),
//            new Column("salary".toUpperCase(), ColumnType.INT, false, true, 16)
//    };
//
//
//    @Test
//    public void setup() throws IOException {
//        //默认数据库TEST
//        System.out.print("————————————————启动数据库——————————————————\n");
//        Manager m=new Manager();
//        printDB(m);
//        //创建数据库
//        System.out.print("————————————————创建数据库并为当前数据库创建表——————————————————\n");
//
//        m.createDatabaseIfNotExists("database1".toUpperCase());
//        m.createDatabaseIfNotExists("database2".toUpperCase());
//        m.createDatabaseIfNotExists("database3".toUpperCase());
//        Database curDB=m.getCurrentDB();
//        curDB.create("table1".toUpperCase(),columns1);
//        curDB.create("table2".toUpperCase(),columns2);
//        printDB(m);
//
//        //切换数据库
//        System.out.print("————————————————切换数据库——————————————————\n");
//
//        m.switchDatabase("database1".toUpperCase());
//        printDB(m);
//        //创建表格
//        System.out.print("————————————————为当前数据库创建表格——————————————————\n");
//
//        curDB=m.getCurrentDB();
//        curDB.create("table1".toUpperCase(),columns1);
//        curDB.create("table2".toUpperCase(),columns2);
//        Table testTable = null;
//        for(Table t:curDB.getTables()){
//            if(t.getTableName().equals("table1".toUpperCase())){
//                testTable=t;
//            }
//        }
//        for (int i = 0; i < 5; i++) {
//            Entry[] entries = {new Entry(i),new Entry("XiaoLi")};
//            testTable.insert(new Row(entries));
//        }
//        testTable.serialize();
//        printDB(m);
//        System.out.print("此时的table1:\n");
//        printTestTable(testTable);
//
//        System.out.print("————————————————为当前数据库删除表格——————————————————\n");
//        curDB.drop("table2".toUpperCase());
//        printDB(m);
//        System.out.print("————————————————为当前table1添加age(INT)列——————————————————\n");
//        //curDB.alterTableAdd("table1","age",ColumnType.INT);
//        curDB.alterTableAdd("table1".toUpperCase(),new Column("age".toUpperCase(),ColumnType.INT,false,false,0));
//
//        printDB(m);
//        System.out.print("此时的table1:\n");
//        printTestTable(testTable);
//
//        System.out.print("————————————————为当前table1删除name列——————————————————\n");
//        curDB.alterTableDrop("table1".toUpperCase(),"name".toUpperCase());
//        printDB(m);
//        System.out.print("此时的table1:\n");
//        printTestTable(testTable);
//
//
//
//        System.out.print("————————————————删除数据库（非当前数据库）——————————————————\n");
//        m.deleteDatabase("database3".toUpperCase());
//        printDB(m);
//        System.out.print("————————————————删除数据库（当前）——————————————————\n");
//        m.deleteDatabase("database1".toUpperCase());
//        printDB(m);
//        System.out.print("————————————————从当前文件建立重建整体数据库系统如下（应与上一阶段完全一致,当前数据库默认为第一个dataBase）——————————————————\n");
//        m.quit();
//        Manager n =new Manager() ;
//        printDB(n);
//    }
//
//    private void printDB(Manager manager) {
//       // System.out.print( manager.getDatabases());
//        System.out.print("所有数据库:\n");
//        for(Database db : manager.getDatabases()){
//            System.out.print(db.getName()+'\n');
//            System.out.print("该数据库所有表格:(列名，列类型，是否为主键，是否非空，最大长度)\n");
//
//            for(Table table : db.getTables()){
//                System.out.print(table.getTableName()+'\n');
//                ArrayList<Column> columns=table.getColumns();
//                for(Column c : columns){
//                    System.out.print(c.toString()+'\n');
//                }
//            }System.out.print('\n');
//
//
//        }System.out.print('\n');
//        System.out.print("当前数据库:"+manager.getCurrentDB().getName()+'\n');
//
//    }
//    public void printTestTable(Table testTable) {
//        for(Column c:testTable.getColumns()){
//            System.out.print(c.toString()+"\n");
//        }
//        for (Row row : testTable) {
//            System.out.print(row.toString() + '\n');
//        }
//    }
//
//
//}
