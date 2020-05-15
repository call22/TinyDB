package cn.edu.thssdb.parser;

import cn.edu.thssdb.exception.SyntaxErrorException;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

/**
 * 对语法错误集中处理
 * */
public class SQLErrorListener extends BaseErrorListener {
    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine, String msg, RecognitionException e) {
//        super.syntaxError(recognizer, offendingSymbol, line, charPositionInLine, msg, e);
        throw new SyntaxErrorException("line " + line + ":" + charPositionInLine + "at" + offendingSymbol.toString() + ": " + msg);
    }
}
