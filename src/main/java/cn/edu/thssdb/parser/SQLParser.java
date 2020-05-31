// Generated from C:/Users/龙龙/Documents/大三下学期/数据库原理/作业/大作业/TinyDB/src/main/java/cn/edu/thssdb/parser\SQL.g4 by ANTLR 4.8
package cn.edu.thssdb.parser;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SQLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, EQ=7, NE=8, LT=9, GT=10, 
		LE=11, GE=12, T_INT=13, T_LONG=14, T_FLOAT=15, T_DOUBLE=16, T_STRING=17, 
		K_ADD=18, K_ALL=19, K_AS=20, K_BY=21, K_COLUMN=22, K_CREATE=23, K_DATABASE=24, 
		K_DATABASES=25, K_DELETE=26, K_DISTINCT=27, K_DROP=28, K_EXISTS=29, K_FROM=30, 
		K_GRANT=31, K_IF=32, K_IDENTIFIED=33, K_INSERT=34, K_INTO=35, K_JOIN=36, 
		K_KEY=37, K_NOT=38, K_NULL=39, K_ON=40, K_PRIMARY=41, K_QUIT=42, K_REVOKE=43, 
		K_SELECT=44, K_SET=45, K_SHOW=46, K_TABLE=47, K_TO=48, K_UPDATE=49, K_USE=50, 
		K_USER=51, K_VALUES=52, K_VIEW=53, K_WHERE=54, K_COMMIT=55, K_TRANSACTION=56, 
		K_BEGIN=57, IDENTIFIER=58, NUMERIC_LITERAL=59, EXPONENT=60, STRING_LITERAL=61;
	public static final int
		RULE_parse = 0, RULE_sql_stmt_list = 1, RULE_sql_stmt = 2, RULE_begin_transaction_stmt = 3, 
		RULE_commit_stmt = 4, RULE_create_db_stmt = 5, RULE_drop_db_stmt = 6, 
		RULE_create_table_stmt = 7, RULE_show_meta_stmt = 8, RULE_use_db_stmt = 9, 
		RULE_delete_stmt = 10, RULE_drop_table_stmt = 11, RULE_show_db_stmt = 12, 
		RULE_show_table_stmt = 13, RULE_insert_stmt = 14, RULE_select_stmt = 15, 
		RULE_update_stmt = 16, RULE_column_def = 17, RULE_type_name = 18, RULE_column_constraint = 19, 
		RULE_multiple_condition = 20, RULE_comparer = 21, RULE_comparator = 22, 
		RULE_table_constraint = 23, RULE_result_column = 24, RULE_table_query = 25, 
		RULE_literal_value = 26, RULE_column_full_name = 27, RULE_database_name = 28, 
		RULE_table_name = 29, RULE_column_name = 30;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "sql_stmt_list", "sql_stmt", "begin_transaction_stmt", "commit_stmt", 
			"create_db_stmt", "drop_db_stmt", "create_table_stmt", "show_meta_stmt", 
			"use_db_stmt", "delete_stmt", "drop_table_stmt", "show_db_stmt", "show_table_stmt", 
			"insert_stmt", "select_stmt", "update_stmt", "column_def", "type_name", 
			"column_constraint", "multiple_condition", "comparer", "comparator", 
			"table_constraint", "result_column", "table_query", "literal_value", 
			"column_full_name", "database_name", "table_name", "column_name"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", "'('", "','", "')'", "'*'", "'.'", "'='", "'<>'", "'<'", 
			"'>'", "'<='", "'>='"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "EQ", "NE", "LT", "GT", "LE", 
			"GE", "T_INT", "T_LONG", "T_FLOAT", "T_DOUBLE", "T_STRING", "K_ADD", 
			"K_ALL", "K_AS", "K_BY", "K_COLUMN", "K_CREATE", "K_DATABASE", "K_DATABASES", 
			"K_DELETE", "K_DISTINCT", "K_DROP", "K_EXISTS", "K_FROM", "K_GRANT", 
			"K_IF", "K_IDENTIFIED", "K_INSERT", "K_INTO", "K_JOIN", "K_KEY", "K_NOT", 
			"K_NULL", "K_ON", "K_PRIMARY", "K_QUIT", "K_REVOKE", "K_SELECT", "K_SET", 
			"K_SHOW", "K_TABLE", "K_TO", "K_UPDATE", "K_USE", "K_USER", "K_VALUES", 
			"K_VIEW", "K_WHERE", "K_COMMIT", "K_TRANSACTION", "K_BEGIN", "IDENTIFIER", 
			"NUMERIC_LITERAL", "EXPONENT", "STRING_LITERAL"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SQL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SQLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public List<Sql_stmt_listContext> sql_stmt_list() {
			return getRuleContexts(Sql_stmt_listContext.class);
		}
		public Sql_stmt_listContext sql_stmt_list(int i) {
			return getRuleContext(Sql_stmt_listContext.class,i);
		}
		public TerminalNode EOF() { return getToken(SQLParser.EOF, 0); }
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitParse(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitParse(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(65);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << K_CREATE) | (1L << K_DELETE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_SHOW) | (1L << K_UPDATE) | (1L << K_USE) | (1L << K_COMMIT) | (1L << K_BEGIN))) != 0)) {
				{
				{
				setState(62);
				sql_stmt_list();
				}
				}
				setState(67);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(69);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,1,_ctx) ) {
			case 1:
				{
				setState(68);
				match(EOF);
				}
				break;
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmt_listContext extends ParserRuleContext {
		public List<Sql_stmtContext> sql_stmt() {
			return getRuleContexts(Sql_stmtContext.class);
		}
		public Sql_stmtContext sql_stmt(int i) {
			return getRuleContext(Sql_stmtContext.class,i);
		}
		public Sql_stmt_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSql_stmt_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSql_stmt_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSql_stmt_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmt_listContext sql_stmt_list() throws RecognitionException {
		Sql_stmt_listContext _localctx = new Sql_stmt_listContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_sql_stmt_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(71);
				match(T__0);
				}
				}
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(77);
			sql_stmt();
			setState(86);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(79); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(78);
						match(T__0);
						}
						}
						setState(81); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(83);
					sql_stmt();
					}
					} 
				}
				setState(88);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(92);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(89);
					match(T__0);
					}
					} 
				}
				setState(94);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Sql_stmtContext extends ParserRuleContext {
		public Create_table_stmtContext create_table_stmt() {
			return getRuleContext(Create_table_stmtContext.class,0);
		}
		public Create_db_stmtContext create_db_stmt() {
			return getRuleContext(Create_db_stmtContext.class,0);
		}
		public Drop_db_stmtContext drop_db_stmt() {
			return getRuleContext(Drop_db_stmtContext.class,0);
		}
		public Delete_stmtContext delete_stmt() {
			return getRuleContext(Delete_stmtContext.class,0);
		}
		public Drop_table_stmtContext drop_table_stmt() {
			return getRuleContext(Drop_table_stmtContext.class,0);
		}
		public Insert_stmtContext insert_stmt() {
			return getRuleContext(Insert_stmtContext.class,0);
		}
		public Select_stmtContext select_stmt() {
			return getRuleContext(Select_stmtContext.class,0);
		}
		public Show_db_stmtContext show_db_stmt() {
			return getRuleContext(Show_db_stmtContext.class,0);
		}
		public Show_table_stmtContext show_table_stmt() {
			return getRuleContext(Show_table_stmtContext.class,0);
		}
		public Show_meta_stmtContext show_meta_stmt() {
			return getRuleContext(Show_meta_stmtContext.class,0);
		}
		public Use_db_stmtContext use_db_stmt() {
			return getRuleContext(Use_db_stmtContext.class,0);
		}
		public Update_stmtContext update_stmt() {
			return getRuleContext(Update_stmtContext.class,0);
		}
		public Commit_stmtContext commit_stmt() {
			return getRuleContext(Commit_stmtContext.class,0);
		}
		public Begin_transaction_stmtContext begin_transaction_stmt() {
			return getRuleContext(Begin_transaction_stmtContext.class,0);
		}
		public Sql_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sql_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSql_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSql_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSql_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Sql_stmtContext sql_stmt() throws RecognitionException {
		Sql_stmtContext _localctx = new Sql_stmtContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sql_stmt);
		try {
			setState(109);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				create_table_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				create_db_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				drop_db_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
				delete_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(99);
				drop_table_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(100);
				insert_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(101);
				select_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(102);
				show_db_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(103);
				show_table_stmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(104);
				show_meta_stmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(105);
				use_db_stmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(106);
				update_stmt();
				}
				break;
			case 13:
				enterOuterAlt(_localctx, 13);
				{
				setState(107);
				commit_stmt();
				}
				break;
			case 14:
				enterOuterAlt(_localctx, 14);
				{
				setState(108);
				begin_transaction_stmt();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Begin_transaction_stmtContext extends ParserRuleContext {
		public TerminalNode K_BEGIN() { return getToken(SQLParser.K_BEGIN, 0); }
		public TerminalNode K_TRANSACTION() { return getToken(SQLParser.K_TRANSACTION, 0); }
		public Begin_transaction_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_begin_transaction_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterBegin_transaction_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitBegin_transaction_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitBegin_transaction_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Begin_transaction_stmtContext begin_transaction_stmt() throws RecognitionException {
		Begin_transaction_stmtContext _localctx = new Begin_transaction_stmtContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_begin_transaction_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(K_BEGIN);
			setState(112);
			match(K_TRANSACTION);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Commit_stmtContext extends ParserRuleContext {
		public TerminalNode K_COMMIT() { return getToken(SQLParser.K_COMMIT, 0); }
		public Commit_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_commit_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCommit_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCommit_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCommit_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Commit_stmtContext commit_stmt() throws RecognitionException {
		Commit_stmtContext _localctx = new Commit_stmtContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_commit_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(K_COMMIT);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_db_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Create_db_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_db_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreate_db_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreate_db_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreate_db_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_db_stmtContext create_db_stmt() throws RecognitionException {
		Create_db_stmtContext _localctx = new Create_db_stmtContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_create_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(K_CREATE);
			setState(117);
			match(K_DATABASE);
			setState(118);
			database_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_db_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public Drop_db_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_db_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDrop_db_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDrop_db_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDrop_db_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_db_stmtContext drop_db_stmt() throws RecognitionException {
		Drop_db_stmtContext _localctx = new Drop_db_stmtContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_drop_db_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(K_DROP);
			setState(121);
			match(K_DATABASE);
			setState(124);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(122);
				match(K_IF);
				setState(123);
				match(K_EXISTS);
				}
			}

			setState(126);
			database_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Create_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_CREATE() { return getToken(SQLParser.K_CREATE, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public List<Column_defContext> column_def() {
			return getRuleContexts(Column_defContext.class);
		}
		public Column_defContext column_def(int i) {
			return getRuleContext(Column_defContext.class,i);
		}
		public Table_constraintContext table_constraint() {
			return getRuleContext(Table_constraintContext.class,0);
		}
		public Create_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_create_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterCreate_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitCreate_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitCreate_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Create_table_stmtContext create_table_stmt() throws RecognitionException {
		Create_table_stmtContext _localctx = new Create_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_create_table_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(K_CREATE);
			setState(129);
			match(K_TABLE);
			setState(130);
			table_name();
			setState(131);
			match(T__1);
			setState(132);
			column_def();
			setState(137);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(133);
					match(T__2);
					setState(134);
					column_def();
					}
					} 
				}
				setState(139);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(142);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(140);
				match(T__2);
				setState(141);
				table_constraint();
				}
			}

			setState(144);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_meta_stmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Show_meta_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_meta_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShow_meta_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShow_meta_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShow_meta_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_meta_stmtContext show_meta_stmt() throws RecognitionException {
		Show_meta_stmtContext _localctx = new Show_meta_stmtContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_show_meta_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(K_SHOW);
			setState(147);
			match(K_TABLE);
			setState(148);
			table_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Use_db_stmtContext extends ParserRuleContext {
		public TerminalNode K_USE() { return getToken(SQLParser.K_USE, 0); }
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Use_db_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_use_db_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUse_db_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUse_db_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUse_db_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Use_db_stmtContext use_db_stmt() throws RecognitionException {
		Use_db_stmtContext _localctx = new Use_db_stmtContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_use_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(K_USE);
			setState(151);
			database_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Delete_stmtContext extends ParserRuleContext {
		public TerminalNode K_DELETE() { return getToken(SQLParser.K_DELETE, 0); }
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public Multiple_conditionContext multiple_condition() {
			return getRuleContext(Multiple_conditionContext.class,0);
		}
		public Delete_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_delete_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDelete_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDelete_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDelete_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Delete_stmtContext delete_stmt() throws RecognitionException {
		Delete_stmtContext _localctx = new Delete_stmtContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(K_DELETE);
			setState(154);
			match(K_FROM);
			setState(155);
			table_name();
			setState(158);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(156);
				match(K_WHERE);
				setState(157);
				multiple_condition();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Drop_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_DROP() { return getToken(SQLParser.K_DROP, 0); }
		public TerminalNode K_TABLE() { return getToken(SQLParser.K_TABLE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_IF() { return getToken(SQLParser.K_IF, 0); }
		public TerminalNode K_EXISTS() { return getToken(SQLParser.K_EXISTS, 0); }
		public Drop_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_drop_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDrop_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDrop_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDrop_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Drop_table_stmtContext drop_table_stmt() throws RecognitionException {
		Drop_table_stmtContext _localctx = new Drop_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_drop_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(160);
			match(K_DROP);
			setState(161);
			match(K_TABLE);
			setState(164);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(162);
				match(K_IF);
				setState(163);
				match(K_EXISTS);
				}
			}

			setState(166);
			table_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_db_stmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASES() { return getToken(SQLParser.K_DATABASES, 0); }
		public Show_db_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_db_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShow_db_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShow_db_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShow_db_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_db_stmtContext show_db_stmt() throws RecognitionException {
		Show_db_stmtContext _localctx = new Show_db_stmtContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_show_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(K_SHOW);
			setState(169);
			match(K_DATABASES);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Show_table_stmtContext extends ParserRuleContext {
		public TerminalNode K_SHOW() { return getToken(SQLParser.K_SHOW, 0); }
		public TerminalNode K_DATABASE() { return getToken(SQLParser.K_DATABASE, 0); }
		public Database_nameContext database_name() {
			return getRuleContext(Database_nameContext.class,0);
		}
		public Show_table_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_show_table_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterShow_table_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitShow_table_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitShow_table_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Show_table_stmtContext show_table_stmt() throws RecognitionException {
		Show_table_stmtContext _localctx = new Show_table_stmtContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_show_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
			match(K_SHOW);
			setState(172);
			match(K_DATABASE);
			setState(173);
			database_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Insert_stmtContext extends ParserRuleContext {
		public TerminalNode K_INSERT() { return getToken(SQLParser.K_INSERT, 0); }
		public TerminalNode K_INTO() { return getToken(SQLParser.K_INTO, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_VALUES() { return getToken(SQLParser.K_VALUES, 0); }
		public List<Literal_valueContext> literal_value() {
			return getRuleContexts(Literal_valueContext.class);
		}
		public Literal_valueContext literal_value(int i) {
			return getRuleContext(Literal_valueContext.class,i);
		}
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Insert_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_insert_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterInsert_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitInsert_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitInsert_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Insert_stmtContext insert_stmt() throws RecognitionException {
		Insert_stmtContext _localctx = new Insert_stmtContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(K_INSERT);
			setState(176);
			match(K_INTO);
			setState(177);
			table_name();
			setState(189);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(178);
				match(T__1);
				setState(179);
				column_name();
				setState(184);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(180);
					match(T__2);
					setState(181);
					column_name();
					}
					}
					setState(186);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(187);
				match(T__3);
				}
			}

			setState(191);
			match(K_VALUES);
			setState(192);
			match(T__1);
			setState(193);
			literal_value();
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(194);
				match(T__2);
				setState(195);
				literal_value();
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(201);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Select_stmtContext extends ParserRuleContext {
		public TerminalNode K_SELECT() { return getToken(SQLParser.K_SELECT, 0); }
		public List<Result_columnContext> result_column() {
			return getRuleContexts(Result_columnContext.class);
		}
		public Result_columnContext result_column(int i) {
			return getRuleContext(Result_columnContext.class,i);
		}
		public TerminalNode K_FROM() { return getToken(SQLParser.K_FROM, 0); }
		public Table_queryContext table_query() {
			return getRuleContext(Table_queryContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public Multiple_conditionContext multiple_condition() {
			return getRuleContext(Multiple_conditionContext.class,0);
		}
		public TerminalNode K_DISTINCT() { return getToken(SQLParser.K_DISTINCT, 0); }
		public TerminalNode K_ALL() { return getToken(SQLParser.K_ALL, 0); }
		public Select_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_select_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterSelect_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitSelect_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitSelect_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Select_stmtContext select_stmt() throws RecognitionException {
		Select_stmtContext _localctx = new Select_stmtContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(K_SELECT);
			setState(205);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ALL || _la==K_DISTINCT) {
				{
				setState(204);
				_la = _input.LA(1);
				if ( !(_la==K_ALL || _la==K_DISTINCT) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
			}

			setState(207);
			result_column();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(208);
				match(T__2);
				setState(209);
				result_column();
				}
				}
				setState(214);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(215);
			match(K_FROM);
			setState(216);
			table_query();
			setState(219);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(217);
				match(K_WHERE);
				setState(218);
				multiple_condition();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Update_stmtContext extends ParserRuleContext {
		public TerminalNode K_UPDATE() { return getToken(SQLParser.K_UPDATE, 0); }
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public TerminalNode K_SET() { return getToken(SQLParser.K_SET, 0); }
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public TerminalNode K_WHERE() { return getToken(SQLParser.K_WHERE, 0); }
		public Multiple_conditionContext multiple_condition() {
			return getRuleContext(Multiple_conditionContext.class,0);
		}
		public Update_stmtContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_update_stmt; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterUpdate_stmt(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitUpdate_stmt(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitUpdate_stmt(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Update_stmtContext update_stmt() throws RecognitionException {
		Update_stmtContext _localctx = new Update_stmtContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(221);
			match(K_UPDATE);
			setState(222);
			table_name();
			setState(223);
			match(K_SET);
			setState(224);
			column_name();
			setState(225);
			match(EQ);
			setState(226);
			literal_value();
			setState(229);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(227);
				match(K_WHERE);
				setState(228);
				multiple_condition();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_defContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Type_nameContext type_name() {
			return getRuleContext(Type_nameContext.class,0);
		}
		public List<Column_constraintContext> column_constraint() {
			return getRuleContexts(Column_constraintContext.class);
		}
		public Column_constraintContext column_constraint(int i) {
			return getRuleContext(Column_constraintContext.class,i);
		}
		public Column_defContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_def; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumn_def(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumn_def(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumn_def(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_defContext column_def() throws RecognitionException {
		Column_defContext _localctx = new Column_defContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			column_name();
			setState(232);
			type_name();
			setState(236);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT || _la==K_PRIMARY) {
				{
				{
				setState(233);
				column_constraint();
				}
				}
				setState(238);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Type_nameContext extends ParserRuleContext {
		public TerminalNode T_INT() { return getToken(SQLParser.T_INT, 0); }
		public TerminalNode T_LONG() { return getToken(SQLParser.T_LONG, 0); }
		public TerminalNode T_FLOAT() { return getToken(SQLParser.T_FLOAT, 0); }
		public TerminalNode T_DOUBLE() { return getToken(SQLParser.T_DOUBLE, 0); }
		public TerminalNode T_STRING() { return getToken(SQLParser.T_STRING, 0); }
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public Type_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterType_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitType_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitType_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Type_nameContext type_name() throws RecognitionException {
		Type_nameContext _localctx = new Type_nameContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_type_name);
		try {
			setState(247);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(239);
				match(T_INT);
				}
				break;
			case T_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(240);
				match(T_LONG);
				}
				break;
			case T_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(241);
				match(T_FLOAT);
				}
				break;
			case T_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(242);
				match(T_DOUBLE);
				}
				break;
			case T_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(243);
				match(T_STRING);
				setState(244);
				match(T__1);
				setState(245);
				match(NUMERIC_LITERAL);
				setState(246);
				match(T__3);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_constraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public TerminalNode K_NOT() { return getToken(SQLParser.K_NOT, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public Column_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumn_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumn_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumn_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_constraintContext column_constraint() throws RecognitionException {
		Column_constraintContext _localctx = new Column_constraintContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_column_constraint);
		try {
			setState(253);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(249);
				match(K_PRIMARY);
				setState(250);
				match(K_KEY);
				}
				break;
			case K_NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(251);
				match(K_NOT);
				setState(252);
				match(K_NULL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Multiple_conditionContext extends ParserRuleContext {
		public List<ComparerContext> comparer() {
			return getRuleContexts(ComparerContext.class);
		}
		public ComparerContext comparer(int i) {
			return getRuleContext(ComparerContext.class,i);
		}
		public ComparatorContext comparator() {
			return getRuleContext(ComparatorContext.class,0);
		}
		public Multiple_conditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterMultiple_condition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitMultiple_condition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitMultiple_condition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Multiple_conditionContext multiple_condition() throws RecognitionException {
		Multiple_conditionContext _localctx = new Multiple_conditionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_multiple_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(255);
			comparer();
			setState(256);
			comparator();
			setState(257);
			comparer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparerContext extends ParserRuleContext {
		public Column_full_nameContext column_full_name() {
			return getRuleContext(Column_full_nameContext.class,0);
		}
		public Literal_valueContext literal_value() {
			return getRuleContext(Literal_valueContext.class,0);
		}
		public ComparerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparerContext comparer() throws RecognitionException {
		ComparerContext _localctx = new ComparerContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_comparer);
		try {
			setState(261);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(259);
				column_full_name();
				}
				break;
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(260);
				literal_value();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ComparatorContext extends ParserRuleContext {
		public TerminalNode EQ() { return getToken(SQLParser.EQ, 0); }
		public TerminalNode NE() { return getToken(SQLParser.NE, 0); }
		public TerminalNode LE() { return getToken(SQLParser.LE, 0); }
		public TerminalNode GE() { return getToken(SQLParser.GE, 0); }
		public TerminalNode LT() { return getToken(SQLParser.LT, 0); }
		public TerminalNode GT() { return getToken(SQLParser.GT, 0); }
		public ComparatorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_comparator; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterComparator(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitComparator(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitComparator(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ComparatorContext comparator() throws RecognitionException {
		ComparatorContext _localctx = new ComparatorContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(263);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << NE) | (1L << LT) | (1L << GT) | (1L << LE) | (1L << GE))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_constraintContext extends ParserRuleContext {
		public TerminalNode K_PRIMARY() { return getToken(SQLParser.K_PRIMARY, 0); }
		public TerminalNode K_KEY() { return getToken(SQLParser.K_KEY, 0); }
		public List<Column_nameContext> column_name() {
			return getRuleContexts(Column_nameContext.class);
		}
		public Column_nameContext column_name(int i) {
			return getRuleContext(Column_nameContext.class,i);
		}
		public Table_constraintContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_constraint; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTable_constraint(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTable_constraint(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTable_constraint(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_constraintContext table_constraint() throws RecognitionException {
		Table_constraintContext _localctx = new Table_constraintContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(265);
			match(K_PRIMARY);
			setState(266);
			match(K_KEY);
			setState(267);
			match(T__1);
			setState(268);
			column_name();
			setState(273);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(269);
				match(T__2);
				setState(270);
				column_name();
				}
				}
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(276);
			match(T__3);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Result_columnContext extends ParserRuleContext {
		public Column_full_nameContext column_full_name() {
			return getRuleContext(Column_full_nameContext.class,0);
		}
		public Result_columnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_result_column; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterResult_column(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitResult_column(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitResult_column(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Result_columnContext result_column() throws RecognitionException {
		Result_columnContext _localctx = new Result_columnContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_result_column);
		try {
			setState(280);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(278);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(279);
				column_full_name();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_queryContext extends ParserRuleContext {
		public List<Table_nameContext> table_name() {
			return getRuleContexts(Table_nameContext.class);
		}
		public Table_nameContext table_name(int i) {
			return getRuleContext(Table_nameContext.class,i);
		}
		public TerminalNode K_JOIN() { return getToken(SQLParser.K_JOIN, 0); }
		public TerminalNode K_ON() { return getToken(SQLParser.K_ON, 0); }
		public Multiple_conditionContext multiple_condition() {
			return getRuleContext(Multiple_conditionContext.class,0);
		}
		public Table_queryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_query; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTable_query(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTable_query(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTable_query(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_queryContext table_query() throws RecognitionException {
		Table_queryContext _localctx = new Table_queryContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_table_query);
		try {
			setState(289);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(282);
				table_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(283);
				table_name();
				setState(284);
				match(K_JOIN);
				setState(285);
				table_name();
				setState(286);
				match(K_ON);
				setState(287);
				multiple_condition();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Literal_valueContext extends ParserRuleContext {
		public TerminalNode NUMERIC_LITERAL() { return getToken(SQLParser.NUMERIC_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(SQLParser.STRING_LITERAL, 0); }
		public TerminalNode K_NULL() { return getToken(SQLParser.K_NULL, 0); }
		public Literal_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterLiteral_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitLiteral_value(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitLiteral_value(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Literal_valueContext literal_value() throws RecognitionException {
		Literal_valueContext _localctx = new Literal_valueContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(291);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << K_NULL) | (1L << NUMERIC_LITERAL) | (1L << STRING_LITERAL))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_full_nameContext extends ParserRuleContext {
		public Column_nameContext column_name() {
			return getRuleContext(Column_nameContext.class,0);
		}
		public Table_nameContext table_name() {
			return getRuleContext(Table_nameContext.class,0);
		}
		public Column_full_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_full_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumn_full_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumn_full_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumn_full_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_full_nameContext column_full_name() throws RecognitionException {
		Column_full_nameContext _localctx = new Column_full_nameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_column_full_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(296);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				{
				setState(293);
				table_name();
				setState(294);
				match(T__5);
				}
				break;
			}
			setState(298);
			column_name();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Database_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public Database_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_database_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterDatabase_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitDatabase_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitDatabase_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Database_nameContext database_name() throws RecognitionException {
		Database_nameContext _localctx = new Database_nameContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(300);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Table_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public Table_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_table_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterTable_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitTable_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitTable_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Table_nameContext table_name() throws RecognitionException {
		Table_nameContext _localctx = new Table_nameContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(302);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Column_nameContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SQLParser.IDENTIFIER, 0); }
		public Column_nameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_column_name; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).enterColumn_name(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SQLListener ) ((SQLListener)listener).exitColumn_name(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof SQLVisitor ) return ((SQLVisitor<? extends T>)visitor).visitColumn_name(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Column_nameContext column_name() throws RecognitionException {
		Column_nameContext _localctx = new Column_nameContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(304);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3?\u0135\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\7\2B\n\2\f\2\16\2E\13\2\3\2\5\2H\n\2\3\3\7\3K\n\3\f\3\16\3N\13\3\3\3"+
		"\3\3\6\3R\n\3\r\3\16\3S\3\3\7\3W\n\3\f\3\16\3Z\13\3\3\3\7\3]\n\3\f\3\16"+
		"\3`\13\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4p"+
		"\n\4\3\5\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\b\177\n\b\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\7\t\u008a\n\t\f\t\16\t\u008d\13\t\3"+
		"\t\3\t\5\t\u0091\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\5\f\u00a1\n\f\3\r\3\r\3\r\3\r\5\r\u00a7\n\r\3\r\3\r\3\16\3"+
		"\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\7\20\u00b9"+
		"\n\20\f\20\16\20\u00bc\13\20\3\20\3\20\5\20\u00c0\n\20\3\20\3\20\3\20"+
		"\3\20\3\20\7\20\u00c7\n\20\f\20\16\20\u00ca\13\20\3\20\3\20\3\21\3\21"+
		"\5\21\u00d0\n\21\3\21\3\21\3\21\7\21\u00d5\n\21\f\21\16\21\u00d8\13\21"+
		"\3\21\3\21\3\21\3\21\5\21\u00de\n\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\5\22\u00e8\n\22\3\23\3\23\3\23\7\23\u00ed\n\23\f\23\16\23\u00f0"+
		"\13\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\5\24\u00fa\n\24\3\25\3"+
		"\25\3\25\3\25\5\25\u0100\n\25\3\26\3\26\3\26\3\26\3\27\3\27\5\27\u0108"+
		"\n\27\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\7\31\u0112\n\31\f\31\16"+
		"\31\u0115\13\31\3\31\3\31\3\32\3\32\5\32\u011b\n\32\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\5\33\u0124\n\33\3\34\3\34\3\35\3\35\3\35\5\35\u012b\n"+
		"\35\3\35\3\35\3\36\3\36\3\37\3\37\3 \3 \3 \2\2!\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>\2\5\4\2\25\25\35\35\3\2\t\16"+
		"\5\2))==??\2\u013f\2C\3\2\2\2\4L\3\2\2\2\6o\3\2\2\2\bq\3\2\2\2\nt\3\2"+
		"\2\2\fv\3\2\2\2\16z\3\2\2\2\20\u0082\3\2\2\2\22\u0094\3\2\2\2\24\u0098"+
		"\3\2\2\2\26\u009b\3\2\2\2\30\u00a2\3\2\2\2\32\u00aa\3\2\2\2\34\u00ad\3"+
		"\2\2\2\36\u00b1\3\2\2\2 \u00cd\3\2\2\2\"\u00df\3\2\2\2$\u00e9\3\2\2\2"+
		"&\u00f9\3\2\2\2(\u00ff\3\2\2\2*\u0101\3\2\2\2,\u0107\3\2\2\2.\u0109\3"+
		"\2\2\2\60\u010b\3\2\2\2\62\u011a\3\2\2\2\64\u0123\3\2\2\2\66\u0125\3\2"+
		"\2\28\u012a\3\2\2\2:\u012e\3\2\2\2<\u0130\3\2\2\2>\u0132\3\2\2\2@B\5\4"+
		"\3\2A@\3\2\2\2BE\3\2\2\2CA\3\2\2\2CD\3\2\2\2DG\3\2\2\2EC\3\2\2\2FH\7\2"+
		"\2\3GF\3\2\2\2GH\3\2\2\2H\3\3\2\2\2IK\7\3\2\2JI\3\2\2\2KN\3\2\2\2LJ\3"+
		"\2\2\2LM\3\2\2\2MO\3\2\2\2NL\3\2\2\2OX\5\6\4\2PR\7\3\2\2QP\3\2\2\2RS\3"+
		"\2\2\2SQ\3\2\2\2ST\3\2\2\2TU\3\2\2\2UW\5\6\4\2VQ\3\2\2\2WZ\3\2\2\2XV\3"+
		"\2\2\2XY\3\2\2\2Y^\3\2\2\2ZX\3\2\2\2[]\7\3\2\2\\[\3\2\2\2]`\3\2\2\2^\\"+
		"\3\2\2\2^_\3\2\2\2_\5\3\2\2\2`^\3\2\2\2ap\5\20\t\2bp\5\f\7\2cp\5\16\b"+
		"\2dp\5\26\f\2ep\5\30\r\2fp\5\36\20\2gp\5 \21\2hp\5\32\16\2ip\5\34\17\2"+
		"jp\5\22\n\2kp\5\24\13\2lp\5\"\22\2mp\5\n\6\2np\5\b\5\2oa\3\2\2\2ob\3\2"+
		"\2\2oc\3\2\2\2od\3\2\2\2oe\3\2\2\2of\3\2\2\2og\3\2\2\2oh\3\2\2\2oi\3\2"+
		"\2\2oj\3\2\2\2ok\3\2\2\2ol\3\2\2\2om\3\2\2\2on\3\2\2\2p\7\3\2\2\2qr\7"+
		";\2\2rs\7:\2\2s\t\3\2\2\2tu\79\2\2u\13\3\2\2\2vw\7\31\2\2wx\7\32\2\2x"+
		"y\5:\36\2y\r\3\2\2\2z{\7\36\2\2{~\7\32\2\2|}\7\"\2\2}\177\7\37\2\2~|\3"+
		"\2\2\2~\177\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\5:\36\2\u0081\17\3\2"+
		"\2\2\u0082\u0083\7\31\2\2\u0083\u0084\7\61\2\2\u0084\u0085\5<\37\2\u0085"+
		"\u0086\7\4\2\2\u0086\u008b\5$\23\2\u0087\u0088\7\5\2\2\u0088\u008a\5$"+
		"\23\2\u0089\u0087\3\2\2\2\u008a\u008d\3\2\2\2\u008b\u0089\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u0090\3\2\2\2\u008d\u008b\3\2\2\2\u008e\u008f\7\5"+
		"\2\2\u008f\u0091\5\60\31\2\u0090\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091"+
		"\u0092\3\2\2\2\u0092\u0093\7\6\2\2\u0093\21\3\2\2\2\u0094\u0095\7\60\2"+
		"\2\u0095\u0096\7\61\2\2\u0096\u0097\5<\37\2\u0097\23\3\2\2\2\u0098\u0099"+
		"\7\64\2\2\u0099\u009a\5:\36\2\u009a\25\3\2\2\2\u009b\u009c\7\34\2\2\u009c"+
		"\u009d\7 \2\2\u009d\u00a0\5<\37\2\u009e\u009f\78\2\2\u009f\u00a1\5*\26"+
		"\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\27\3\2\2\2\u00a2\u00a3"+
		"\7\36\2\2\u00a3\u00a6\7\61\2\2\u00a4\u00a5\7\"\2\2\u00a5\u00a7\7\37\2"+
		"\2\u00a6\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9"+
		"\5<\37\2\u00a9\31\3\2\2\2\u00aa\u00ab\7\60\2\2\u00ab\u00ac\7\33\2\2\u00ac"+
		"\33\3\2\2\2\u00ad\u00ae\7\60\2\2\u00ae\u00af\7\32\2\2\u00af\u00b0\5:\36"+
		"\2\u00b0\35\3\2\2\2\u00b1\u00b2\7$\2\2\u00b2\u00b3\7%\2\2\u00b3\u00bf"+
		"\5<\37\2\u00b4\u00b5\7\4\2\2\u00b5\u00ba\5> \2\u00b6\u00b7\7\5\2\2\u00b7"+
		"\u00b9\5> \2\u00b8\u00b6\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00b8\3\2\2"+
		"\2\u00ba\u00bb\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be"+
		"\7\6\2\2\u00be\u00c0\3\2\2\2\u00bf\u00b4\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\u00c1\3\2\2\2\u00c1\u00c2\7\66\2\2\u00c2\u00c3\7\4\2\2\u00c3\u00c8\5"+
		"\66\34\2\u00c4\u00c5\7\5\2\2\u00c5\u00c7\5\66\34\2\u00c6\u00c4\3\2\2\2"+
		"\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2\2\2\u00c9\u00cb"+
		"\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\7\6\2\2\u00cc\37\3\2\2\2\u00cd"+
		"\u00cf\7.\2\2\u00ce\u00d0\t\2\2\2\u00cf\u00ce\3\2\2\2\u00cf\u00d0\3\2"+
		"\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d6\5\62\32\2\u00d2\u00d3\7\5\2\2\u00d3"+
		"\u00d5\5\62\32\2\u00d4\u00d2\3\2\2\2\u00d5\u00d8\3\2\2\2\u00d6\u00d4\3"+
		"\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d9\3\2\2\2\u00d8\u00d6\3\2\2\2\u00d9"+
		"\u00da\7 \2\2\u00da\u00dd\5\64\33\2\u00db\u00dc\78\2\2\u00dc\u00de\5*"+
		"\26\2\u00dd\u00db\3\2\2\2\u00dd\u00de\3\2\2\2\u00de!\3\2\2\2\u00df\u00e0"+
		"\7\63\2\2\u00e0\u00e1\5<\37\2\u00e1\u00e2\7/\2\2\u00e2\u00e3\5> \2\u00e3"+
		"\u00e4\7\t\2\2\u00e4\u00e7\5\66\34\2\u00e5\u00e6\78\2\2\u00e6\u00e8\5"+
		"*\26\2\u00e7\u00e5\3\2\2\2\u00e7\u00e8\3\2\2\2\u00e8#\3\2\2\2\u00e9\u00ea"+
		"\5> \2\u00ea\u00ee\5&\24\2\u00eb\u00ed\5(\25\2\u00ec\u00eb\3\2\2\2\u00ed"+
		"\u00f0\3\2\2\2\u00ee\u00ec\3\2\2\2\u00ee\u00ef\3\2\2\2\u00ef%\3\2\2\2"+
		"\u00f0\u00ee\3\2\2\2\u00f1\u00fa\7\17\2\2\u00f2\u00fa\7\20\2\2\u00f3\u00fa"+
		"\7\21\2\2\u00f4\u00fa\7\22\2\2\u00f5\u00f6\7\23\2\2\u00f6\u00f7\7\4\2"+
		"\2\u00f7\u00f8\7=\2\2\u00f8\u00fa\7\6\2\2\u00f9\u00f1\3\2\2\2\u00f9\u00f2"+
		"\3\2\2\2\u00f9\u00f3\3\2\2\2\u00f9\u00f4\3\2\2\2\u00f9\u00f5\3\2\2\2\u00fa"+
		"\'\3\2\2\2\u00fb\u00fc\7+\2\2\u00fc\u0100\7\'\2\2\u00fd\u00fe\7(\2\2\u00fe"+
		"\u0100\7)\2\2\u00ff\u00fb\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100)\3\2\2\2\u0101"+
		"\u0102\5,\27\2\u0102\u0103\5.\30\2\u0103\u0104\5,\27\2\u0104+\3\2\2\2"+
		"\u0105\u0108\58\35\2\u0106\u0108\5\66\34\2\u0107\u0105\3\2\2\2\u0107\u0106"+
		"\3\2\2\2\u0108-\3\2\2\2\u0109\u010a\t\3\2\2\u010a/\3\2\2\2\u010b\u010c"+
		"\7+\2\2\u010c\u010d\7\'\2\2\u010d\u010e\7\4\2\2\u010e\u0113\5> \2\u010f"+
		"\u0110\7\5\2\2\u0110\u0112\5> \2\u0111\u010f\3\2\2\2\u0112\u0115\3\2\2"+
		"\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0116\3\2\2\2\u0115\u0113"+
		"\3\2\2\2\u0116\u0117\7\6\2\2\u0117\61\3\2\2\2\u0118\u011b\7\7\2\2\u0119"+
		"\u011b\58\35\2\u011a\u0118\3\2\2\2\u011a\u0119\3\2\2\2\u011b\63\3\2\2"+
		"\2\u011c\u0124\5<\37\2\u011d\u011e\5<\37\2\u011e\u011f\7&\2\2\u011f\u0120"+
		"\5<\37\2\u0120\u0121\7*\2\2\u0121\u0122\5*\26\2\u0122\u0124\3\2\2\2\u0123"+
		"\u011c\3\2\2\2\u0123\u011d\3\2\2\2\u0124\65\3\2\2\2\u0125\u0126\t\4\2"+
		"\2\u0126\67\3\2\2\2\u0127\u0128\5<\37\2\u0128\u0129\7\b\2\2\u0129\u012b"+
		"\3\2\2\2\u012a\u0127\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012d\5> \2\u012d9\3\2\2\2\u012e\u012f\7<\2\2\u012f;\3\2\2\2\u0130\u0131"+
		"\7<\2\2\u0131=\3\2\2\2\u0132\u0133\7<\2\2\u0133?\3\2\2\2\35CGLSX^o~\u008b"+
		"\u0090\u00a0\u00a6\u00ba\u00bf\u00c8\u00cf\u00d6\u00dd\u00e7\u00ee\u00f9"+
		"\u00ff\u0107\u0113\u011a\u0123\u012a";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}