package statement;

import cn.edu.thssdb.exception.SyntaxErrorException;
import cn.edu.thssdb.parser.Listener;
import cn.edu.thssdb.query.statement.Statement;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import static org.junit.Assert.*;

public class CreateTableStatementTest extends BaseTest {
    // 调整valid.sql, 报错正常
    @Test
    public void testValid() throws IOException, RuntimeException {
        Listener listener = getListenerFromStatement("sqlResources/create/create_valid.sql");
        ArrayList<Statement> statementArrayList = listener.getStatements();
        for (Statement statement : statementArrayList){
            assertTrue(statement.isValid());    // 必须判断正确
            System.out.println(statement.execute(manager).toString());
        }
        // 检测运行结果 ==> 生成table
        assertNotNull(manager.getCurrentDB().selectTable("student".toUpperCase()));
    }

}
