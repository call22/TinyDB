package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.Column;
import cn.edu.thssdb.schema.Database;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.schema.Table;
import cn.edu.thssdb.type.ColumnType;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

public class SchemaTest {


    @Test
    public void setup() throws IOException {
        //默认数据库TEST
        System.out.print("————————————————启动数据库——————————————————\n");
        Manager m=new Manager();
        printDB(m);
        //创建数据库
        System.out.print("————————————————创建数据库——————————————————\n");

        m.createDatabaseIfNotExists("database1");
        m.createDatabaseIfNotExists("database2");
        m.createDatabaseIfNotExists("database3");

        printDB(m);
        //切换数据库
        System.out.print("————————————————切换数据库——————————————————\n");

        m.switchDatabase("database1");
        printDB(m);
        //创建表格
        System.out.print("————————————————为当前数据库创建表格——————————————————\n");

        Column[] columns1 = {
                new Column("id", ColumnType.INT, 1, true, 0),
                new Column("name", ColumnType.STRING, 0, true, 16)
        };
        Column[] columns2 = {
                new Column("name", ColumnType.STRING, 1, true, 0),
                new Column("salary", ColumnType.INT, 0, true, 16)
        };

        Database curDB=m.getCurrentDB();
        curDB.create("table1",columns1);
        curDB.create("table2",columns2);
        printDB(m);
        System.out.print("————————————————为当前数据库修改表格——————————————————\n");
        Column[] columns3 = {
                new Column("id", ColumnType.INT, 1, true, 0),
                new Column("name", ColumnType.STRING, 0, true, 16),
                new Column("dept_name", ColumnType.STRING, 0, true, 16)

        };
        curDB.editTable("table1",columns3);
        printDB(m);

        System.out.print("————————————————为当前数据库删除表格——————————————————\n");
        curDB.drop("table1");
        printDB(m);
        System.out.print("————————————————删除数据库（非当前数据库）——————————————————\n");
        m.deleteDatabase("database3");
        printDB(m);
        System.out.print("————————————————删除数据库（当前）——————————————————\n");
        m.deleteDatabase("database1");
        printDB(m);



    }

    private void printDB(Manager manager) {
       // System.out.print( manager.getDatabases());
        System.out.print("所有数据库:\n");
        for(Database db : manager.getDatabases()){
            System.out.print(db.getName()+'\n');
            Table[] tbs=db.getTables();
            System.out.print("该数据库所有表格:(列名，列类型，是否为主键，是否非空，最大长度)\n");

            for(Table table : tbs){
                System.out.print(table.tableName+'\n');
                ArrayList<Column> columns=table.getColumns();
                for(Column c : columns){
                    System.out.print(c.toString()+'\n');
                }
            }System.out.print('\n');


        }System.out.print('\n');
        System.out.print("当前数据库:"+manager.getCurrentDB().getName()+'\n');

    }

}
