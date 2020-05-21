package statement;

import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.query.statement.Statement;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertTrue;

public class DeleteTableStatementTest extends BaseTest {
    @Test
    public void testValid() throws IOException, RuntimeException {
        Listener listener = getListenerFromStatement("sqlResources/delete/delete_valid.sql");
        ArrayList<Statement> statementArrayList = listener.getStatements();
        for (Statement statement : statementArrayList){
            assertTrue(statement.isValid());    // ±ÿ–Î≈–∂œ’˝»∑
            System.out.println(statement.execute(manager).toString());
        }
    }
}
