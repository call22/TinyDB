package statement;

import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.query.statement.Statement;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class SelectTableStatementTest extends BaseTest{
    @Test
    public void testValid() throws IOException, RuntimeException {
        Listener listener = getListenerFromStatement("sqlResources/select/select_valid.sql");
        ArrayList<Statement> statementArrayList = listener.getStatements();
        for (Statement statement : statementArrayList){
            assertTrue(statement.isValid());    // 必须判断正确
            System.out.println(statement.execute(manager).toString());
        }
    }


    @Test
    public void testJoinValid() throws IOException, RuntimeException {
        Listener listener = getListenerFromStatement("sqlResources/select/select_join_valid.sql");
        ArrayList<Statement> statementArrayList = listener.getStatements();
        for (Statement statement : statementArrayList){
            assertTrue(statement.isValid());    // 必须判断正确
            System.out.println(statement.execute(manager).toString());
        }
    }
}
