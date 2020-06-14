package cn.edu.thssdb.index;

import cn.edu.thssdb.schema.*;
import cn.edu.thssdb.type.ColumnType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;

public class SchemaTest {
    Column[] columns1 = {
            new Column("id".toUpperCase(), ColumnType.INT, true, true, 0),
            new Column("name".toUpperCase(), ColumnType.STRING, false, true, 16)
    };
    Column[] columns2 = {
            new Column("name".toUpperCase(), ColumnType.STRING, true, true, 0),
            new Column("salary".toUpperCase(), ColumnType.INT, false, true, 16)
    };
    private Manager manager;


    @Before
    public void setup(){
       manager =new Manager();


    }

    @After
    public void cleanup(){
        deleteDir("DBS");
    }

    private static void deleteDir(String filePath) {
        File file = new File(filePath);
        if(!file.exists()){
            return;
        }
        String[] list = file.list();
        File temp = null;
        String path = null;
        for (String item:list) {
            path = filePath + File.separator + item;
            temp = new File(path);
            if(temp.isFile()){
                temp.delete();
                continue;
            }
            if(temp.isDirectory()) {
                deleteDir(path);
                new File(path).delete();
                continue;
            }
        }
    }

    public void testManagerStart(){

        assertTrue(manager.getDatabases().size()==1) ;
        assertTrue(manager.getCurrentDB().getName().equals("TEST")) ;

    }
    public void testDatabaseCreate(){
        try {
            manager.createDatabaseIfNotExists("database1".toUpperCase());
            manager.createDatabaseIfNotExists("database2".toUpperCase());
            manager.createDatabaseIfNotExists("database3".toUpperCase());
            assertTrue(manager.getDatabases().size()==4) ;
            assertTrue(manager.getCurrentDB().getName()=="TEST") ;

            Database curDB = manager.getCurrentDB();
            curDB.create("table1".toUpperCase(), columns1);
            curDB.create("table2".toUpperCase(), columns2);
        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void testDatabaseDrop(){
        try {
            manager.deleteDatabase("database3".toUpperCase());
            LinkedList<Database> dblist=manager.getDatabases();
            assertTrue(dblist.size()==3) ;
            for(Database db :dblist){
                assertFalse(db.getName().equals("DATABASE3"));
            }
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
        try {
            manager.deleteDatabase("database1".toUpperCase());
            LinkedList<Database> dblist=manager.getDatabases();
            assertTrue(dblist.size()==2) ;

            for(Database db :dblist){
                assertFalse(db.getName().equals("DATABASE1"));
            }
            assertTrue(manager.getCurrentDB().getName().equals("TEST"));
        }catch (IOException e){
            System.out.println(e.getMessage());
        }
    }
    public void testDatabaseSwitch(){
        try{
            manager.switchDatabase("database1".toUpperCase());
            assertTrue(manager.getCurrentDB().getName().equals("DATABASE1")) ;

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    public void testRecover(){
        try {
            manager.quit();
            Manager restartManager =new Manager() ;
            LinkedList<Database> dblist=restartManager.getDatabases();
            assertTrue(dblist.size()==2) ;
            for(Database db :dblist){
                assertTrue(db.getName().equals("DATABASE2")||db.getName().equals("TEST"));
            }

        }catch (IOException e){
            System.out.println(e.getMessage());
        }

    }
    @Test
    public void test() {
        testManagerStart();
        testDatabaseCreate();
        testDatabaseSwitch();
        testDatabaseDrop();testRecover();
    }


}
