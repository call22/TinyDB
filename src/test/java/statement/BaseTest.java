package statement;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.parser.SQLErrorListener;
import cn.edu.thssdb.parser.SQLLexer;
import cn.edu.thssdb.parser.SQLParser;
import cn.edu.thssdb.schema.Manager;
import cn.edu.thssdb.schema.Table;

import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;
import org.junit.After;
import org.junit.Before;

import java.io.IOException;
import java.util.LinkedList;

public class BaseTest {
    protected static Manager manager = new Manager();   // manager 全局变量

    /**
     * 获取输入指令对应的listener
     * @param fileName 存储指令的文件名
     * @return Listener
     * */
    protected static Listener getListenerFromStatement(String fileName) throws IOException, SyntaxErrorException {
        CharStream in = CharStreams.fromFileName(fileName);
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

    // 准备工作
    @Before
    public void setUp() throws IOException{
        LinkedList<Table> tables = manager.getCurrentDB().getTables();
        for(Table table : tables) {
            manager.getCurrentDB().drop(table.getTableName());
        }
    }

    @After
    public void cleanUp() throws IOException{
        LinkedList<Table> tables = manager.getCurrentDB().getTables();
        for(Table table : tables) {
            manager.getCurrentDB().drop(table.getTableName());
        }
    }
}
