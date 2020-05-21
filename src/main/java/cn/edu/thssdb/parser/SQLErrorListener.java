package cn.edu.thssdb.parser;

import cn.edu.thssdb.exception.SyntaxErrorException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * handle syntax error
 * */
public class SQLErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
        throw new SyntaxErrorException("line " + line + ":" + charPositionInLine + "at" + offendingSymbol.toString() + ": " + msg);
    }
}
