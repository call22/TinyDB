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
		K_USER=51, K_VALUES=52, K_VIEW=53, K_WHERE=54, IDENTIFIER=55, NUMERIC_LITERAL=56, 
		EXPONENT=57, STRING_LITERAL=58;
	public static final int
		RULE_parse = 0, RULE_sql_stmt_list = 1, RULE_sql_stmt = 2, RULE_create_db_stmt = 3, 
		RULE_drop_db_stmt = 4, RULE_create_table_stmt = 5, RULE_show_meta_stmt = 6, 
		RULE_use_db_stmt = 7, RULE_delete_stmt = 8, RULE_drop_table_stmt = 9, 
		RULE_show_db_stmt = 10, RULE_show_table_stmt = 11, RULE_insert_stmt = 12, 
		RULE_select_stmt = 13, RULE_update_stmt = 14, RULE_column_def = 15, RULE_type_name = 16, 
		RULE_column_constraint = 17, RULE_multiple_condition = 18, RULE_comparer = 19, 
		RULE_comparator = 20, RULE_table_constraint = 21, RULE_result_column = 22, 
		RULE_table_query = 23, RULE_literal_value = 24, RULE_column_full_name = 25, 
		RULE_database_name = 26, RULE_table_name = 27, RULE_column_name = 28;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "sql_stmt_list", "sql_stmt", "create_db_stmt", "drop_db_stmt", 
			"create_table_stmt", "show_meta_stmt", "use_db_stmt", "delete_stmt", 
			"drop_table_stmt", "show_db_stmt", "show_table_stmt", "insert_stmt", 
			"select_stmt", "update_stmt", "column_def", "type_name", "column_constraint", 
			"multiple_condition", "comparer", "comparator", "table_constraint", "result_column", 
			"table_query", "literal_value", "column_full_name", "database_name", 
			"table_name", "column_name"
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
			"K_VIEW", "K_WHERE", "IDENTIFIER", "NUMERIC_LITERAL", "EXPONENT", "STRING_LITERAL"
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
		public TerminalNode EOF() { return getToken(SQLParser.EOF, 0); }
		public List<Sql_stmt_listContext> sql_stmt_list() {
			return getRuleContexts(Sql_stmt_listContext.class);
		}
		public Sql_stmt_listContext sql_stmt_list(int i) {
			return getRuleContext(Sql_stmt_listContext.class,i);
		}
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
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << K_CREATE) | (1L << K_DELETE) | (1L << K_DROP) | (1L << K_INSERT) | (1L << K_SELECT) | (1L << K_SHOW) | (1L << K_UPDATE) | (1L << K_USE))) != 0)) {
				{
				{
				setState(58);
				sql_stmt_list();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(EOF);
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
			setState(69);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0) {
				{
				{
				setState(66);
				match(T__0);
				}
				}
				setState(71);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(72);
			sql_stmt();
			setState(81);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(74); 
					_errHandler.sync(this);
					_la = _input.LA(1);
					do {
						{
						{
						setState(73);
						match(T__0);
						}
						}
						setState(76); 
						_errHandler.sync(this);
						_la = _input.LA(1);
					} while ( _la==T__0 );
					setState(78);
					sql_stmt();
					}
					} 
				}
				setState(83);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,3,_ctx);
			}
			setState(87);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(84);
					match(T__0);
					}
					} 
				}
				setState(89);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
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
			setState(102);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(90);
				create_table_stmt();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(91);
				create_db_stmt();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(92);
				drop_db_stmt();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(93);
				delete_stmt();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(94);
				drop_table_stmt();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(95);
				insert_stmt();
				}
				break;
			case 7:
				enterOuterAlt(_localctx, 7);
				{
				setState(96);
				select_stmt();
				}
				break;
			case 8:
				enterOuterAlt(_localctx, 8);
				{
				setState(97);
				show_db_stmt();
				}
				break;
			case 9:
				enterOuterAlt(_localctx, 9);
				{
				setState(98);
				show_table_stmt();
				}
				break;
			case 10:
				enterOuterAlt(_localctx, 10);
				{
				setState(99);
				show_meta_stmt();
				}
				break;
			case 11:
				enterOuterAlt(_localctx, 11);
				{
				setState(100);
				use_db_stmt();
				}
				break;
			case 12:
				enterOuterAlt(_localctx, 12);
				{
				setState(101);
				update_stmt();
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
		enterRule(_localctx, 6, RULE_create_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(104);
			match(K_CREATE);
			setState(105);
			match(K_DATABASE);
			setState(106);
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
		enterRule(_localctx, 8, RULE_drop_db_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(108);
			match(K_DROP);
			setState(109);
			match(K_DATABASE);
			setState(112);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(110);
				match(K_IF);
				setState(111);
				match(K_EXISTS);
				}
			}

			setState(114);
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
		enterRule(_localctx, 10, RULE_create_table_stmt);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			match(K_CREATE);
			setState(117);
			match(K_TABLE);
			setState(118);
			table_name();
			setState(119);
			match(T__1);
			setState(120);
			column_def();
			setState(125);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(121);
					match(T__2);
					setState(122);
					column_def();
					}
					} 
				}
				setState(127);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
			}
			setState(130);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__2) {
				{
				setState(128);
				match(T__2);
				setState(129);
				table_constraint();
				}
			}

			setState(132);
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
		enterRule(_localctx, 12, RULE_show_meta_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(134);
			match(K_SHOW);
			setState(135);
			match(K_TABLE);
			setState(136);
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
		enterRule(_localctx, 14, RULE_use_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(K_USE);
			setState(139);
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
		enterRule(_localctx, 16, RULE_delete_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(141);
			match(K_DELETE);
			setState(142);
			match(K_FROM);
			setState(143);
			table_name();
			setState(146);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(144);
				match(K_WHERE);
				setState(145);
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
		enterRule(_localctx, 18, RULE_drop_table_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(148);
			match(K_DROP);
			setState(149);
			match(K_TABLE);
			setState(152);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_IF) {
				{
				setState(150);
				match(K_IF);
				setState(151);
				match(K_EXISTS);
				}
			}

			setState(154);
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
		enterRule(_localctx, 20, RULE_show_db_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(156);
			match(K_SHOW);
			setState(157);
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
		enterRule(_localctx, 22, RULE_show_table_stmt);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(159);
			match(K_SHOW);
			setState(160);
			match(K_DATABASE);
			setState(161);
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
		enterRule(_localctx, 24, RULE_insert_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(K_INSERT);
			setState(164);
			match(K_INTO);
			setState(165);
			table_name();
			setState(177);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(166);
				match(T__1);
				setState(167);
				column_name();
				setState(172);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__2) {
					{
					{
					setState(168);
					match(T__2);
					setState(169);
					column_name();
					}
					}
					setState(174);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(175);
				match(T__3);
				}
			}

			setState(179);
			match(K_VALUES);
			setState(180);
			match(T__1);
			setState(181);
			literal_value();
			setState(186);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(182);
				match(T__2);
				setState(183);
				literal_value();
				}
				}
				setState(188);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(189);
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
		enterRule(_localctx, 26, RULE_select_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(K_SELECT);
			setState(193);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_ALL || _la==K_DISTINCT) {
				{
				setState(192);
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

			setState(195);
			result_column();
			setState(200);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(196);
				match(T__2);
				setState(197);
				result_column();
				}
				}
				setState(202);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(203);
			match(K_FROM);
			setState(204);
			table_query();
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(205);
				match(K_WHERE);
				setState(206);
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
		enterRule(_localctx, 28, RULE_update_stmt);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
			match(K_UPDATE);
			setState(210);
			table_name();
			setState(211);
			match(K_SET);
			setState(212);
			column_name();
			setState(213);
			match(EQ);
			setState(214);
			literal_value();
			setState(217);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==K_WHERE) {
				{
				setState(215);
				match(K_WHERE);
				setState(216);
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
		enterRule(_localctx, 30, RULE_column_def);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			column_name();
			setState(220);
			type_name();
			setState(224);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==K_NOT || _la==K_PRIMARY) {
				{
				{
				setState(221);
				column_constraint();
				}
				}
				setState(226);
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
		enterRule(_localctx, 32, RULE_type_name);
		try {
			setState(235);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T_INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(227);
				match(T_INT);
				}
				break;
			case T_LONG:
				enterOuterAlt(_localctx, 2);
				{
				setState(228);
				match(T_LONG);
				}
				break;
			case T_FLOAT:
				enterOuterAlt(_localctx, 3);
				{
				setState(229);
				match(T_FLOAT);
				}
				break;
			case T_DOUBLE:
				enterOuterAlt(_localctx, 4);
				{
				setState(230);
				match(T_DOUBLE);
				}
				break;
			case T_STRING:
				enterOuterAlt(_localctx, 5);
				{
				setState(231);
				match(T_STRING);
				setState(232);
				match(T__1);
				setState(233);
				match(NUMERIC_LITERAL);
				setState(234);
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
		enterRule(_localctx, 34, RULE_column_constraint);
		try {
			setState(241);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case K_PRIMARY:
				enterOuterAlt(_localctx, 1);
				{
				setState(237);
				match(K_PRIMARY);
				setState(238);
				match(K_KEY);
				}
				break;
			case K_NOT:
				enterOuterAlt(_localctx, 2);
				{
				setState(239);
				match(K_NOT);
				setState(240);
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
		enterRule(_localctx, 36, RULE_multiple_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(243);
			comparer();
			setState(244);
			comparator();
			setState(245);
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
		enterRule(_localctx, 38, RULE_comparer);
		try {
			setState(249);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(247);
				column_full_name();
				}
				break;
			case K_NULL:
			case NUMERIC_LITERAL:
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(248);
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
		enterRule(_localctx, 40, RULE_comparator);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(251);
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
		enterRule(_localctx, 42, RULE_table_constraint);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(K_PRIMARY);
			setState(254);
			match(K_KEY);
			setState(255);
			match(T__1);
			setState(256);
			column_name();
			setState(261);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__2) {
				{
				{
				setState(257);
				match(T__2);
				setState(258);
				column_name();
				}
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(264);
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
		enterRule(_localctx, 44, RULE_result_column);
		try {
			setState(268);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__4:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(T__4);
				}
				break;
			case IDENTIFIER:
				enterOuterAlt(_localctx, 2);
				{
				setState(267);
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
		enterRule(_localctx, 46, RULE_table_query);
		try {
			setState(277);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(270);
				table_name();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(271);
				table_name();
				setState(272);
				match(K_JOIN);
				setState(273);
				table_name();
				setState(274);
				match(K_ON);
				setState(275);
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
		enterRule(_localctx, 48, RULE_literal_value);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(279);
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
		enterRule(_localctx, 50, RULE_column_full_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(284);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				{
				setState(281);
				table_name();
				setState(282);
				match(T__5);
				}
				break;
			}
			setState(286);
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
		enterRule(_localctx, 52, RULE_database_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
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
		enterRule(_localctx, 54, RULE_table_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(290);
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
		enterRule(_localctx, 56, RULE_column_name);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(292);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3<\u0129\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\7\2>\n\2\f\2\16"+
		"\2A\13\2\3\2\3\2\3\3\7\3F\n\3\f\3\16\3I\13\3\3\3\3\3\6\3M\n\3\r\3\16\3"+
		"N\3\3\7\3R\n\3\f\3\16\3U\13\3\3\3\7\3X\n\3\f\3\16\3[\13\3\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4i\n\4\3\5\3\5\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\5\6s\n\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7~\n\7\f\7\16"+
		"\7\u0081\13\7\3\7\3\7\5\7\u0085\n\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\n\3\n\3\n\3\n\3\n\5\n\u0095\n\n\3\13\3\13\3\13\3\13\5\13\u009b\n"+
		"\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\7\16\u00ad\n\16\f\16\16\16\u00b0\13\16\3\16\3\16\5\16\u00b4\n\16"+
		"\3\16\3\16\3\16\3\16\3\16\7\16\u00bb\n\16\f\16\16\16\u00be\13\16\3\16"+
		"\3\16\3\17\3\17\5\17\u00c4\n\17\3\17\3\17\3\17\7\17\u00c9\n\17\f\17\16"+
		"\17\u00cc\13\17\3\17\3\17\3\17\3\17\5\17\u00d2\n\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\5\20\u00dc\n\20\3\21\3\21\3\21\7\21\u00e1\n\21\f"+
		"\21\16\21\u00e4\13\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\5\22\u00ee"+
		"\n\22\3\23\3\23\3\23\3\23\5\23\u00f4\n\23\3\24\3\24\3\24\3\24\3\25\3\25"+
		"\5\25\u00fc\n\25\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\7\27\u0106\n"+
		"\27\f\27\16\27\u0109\13\27\3\27\3\27\3\30\3\30\5\30\u010f\n\30\3\31\3"+
		"\31\3\31\3\31\3\31\3\31\3\31\5\31\u0118\n\31\3\32\3\32\3\33\3\33\3\33"+
		"\5\33\u011f\n\33\3\33\3\33\3\34\3\34\3\35\3\35\3\36\3\36\3\36\2\2\37\2"+
		"\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:\2\5\4\2\25"+
		"\25\35\35\3\2\t\16\5\2))::<<\2\u0132\2?\3\2\2\2\4G\3\2\2\2\6h\3\2\2\2"+
		"\bj\3\2\2\2\nn\3\2\2\2\fv\3\2\2\2\16\u0088\3\2\2\2\20\u008c\3\2\2\2\22"+
		"\u008f\3\2\2\2\24\u0096\3\2\2\2\26\u009e\3\2\2\2\30\u00a1\3\2\2\2\32\u00a5"+
		"\3\2\2\2\34\u00c1\3\2\2\2\36\u00d3\3\2\2\2 \u00dd\3\2\2\2\"\u00ed\3\2"+
		"\2\2$\u00f3\3\2\2\2&\u00f5\3\2\2\2(\u00fb\3\2\2\2*\u00fd\3\2\2\2,\u00ff"+
		"\3\2\2\2.\u010e\3\2\2\2\60\u0117\3\2\2\2\62\u0119\3\2\2\2\64\u011e\3\2"+
		"\2\2\66\u0122\3\2\2\28\u0124\3\2\2\2:\u0126\3\2\2\2<>\5\4\3\2=<\3\2\2"+
		"\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\2\2\3C\3\3\2"+
		"\2\2DF\7\3\2\2ED\3\2\2\2FI\3\2\2\2GE\3\2\2\2GH\3\2\2\2HJ\3\2\2\2IG\3\2"+
		"\2\2JS\5\6\4\2KM\7\3\2\2LK\3\2\2\2MN\3\2\2\2NL\3\2\2\2NO\3\2\2\2OP\3\2"+
		"\2\2PR\5\6\4\2QL\3\2\2\2RU\3\2\2\2SQ\3\2\2\2ST\3\2\2\2TY\3\2\2\2US\3\2"+
		"\2\2VX\7\3\2\2WV\3\2\2\2X[\3\2\2\2YW\3\2\2\2YZ\3\2\2\2Z\5\3\2\2\2[Y\3"+
		"\2\2\2\\i\5\f\7\2]i\5\b\5\2^i\5\n\6\2_i\5\22\n\2`i\5\24\13\2ai\5\32\16"+
		"\2bi\5\34\17\2ci\5\26\f\2di\5\30\r\2ei\5\16\b\2fi\5\20\t\2gi\5\36\20\2"+
		"h\\\3\2\2\2h]\3\2\2\2h^\3\2\2\2h_\3\2\2\2h`\3\2\2\2ha\3\2\2\2hb\3\2\2"+
		"\2hc\3\2\2\2hd\3\2\2\2he\3\2\2\2hf\3\2\2\2hg\3\2\2\2i\7\3\2\2\2jk\7\31"+
		"\2\2kl\7\32\2\2lm\5\66\34\2m\t\3\2\2\2no\7\36\2\2or\7\32\2\2pq\7\"\2\2"+
		"qs\7\37\2\2rp\3\2\2\2rs\3\2\2\2st\3\2\2\2tu\5\66\34\2u\13\3\2\2\2vw\7"+
		"\31\2\2wx\7\61\2\2xy\58\35\2yz\7\4\2\2z\177\5 \21\2{|\7\5\2\2|~\5 \21"+
		"\2}{\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0084"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\7\5\2\2\u0083\u0085\5,\27\2\u0084"+
		"\u0082\3\2\2\2\u0084\u0085\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\7\6"+
		"\2\2\u0087\r\3\2\2\2\u0088\u0089\7\60\2\2\u0089\u008a\7\61\2\2\u008a\u008b"+
		"\58\35\2\u008b\17\3\2\2\2\u008c\u008d\7\64\2\2\u008d\u008e\5\66\34\2\u008e"+
		"\21\3\2\2\2\u008f\u0090\7\34\2\2\u0090\u0091\7 \2\2\u0091\u0094\58\35"+
		"\2\u0092\u0093\78\2\2\u0093\u0095\5&\24\2\u0094\u0092\3\2\2\2\u0094\u0095"+
		"\3\2\2\2\u0095\23\3\2\2\2\u0096\u0097\7\36\2\2\u0097\u009a\7\61\2\2\u0098"+
		"\u0099\7\"\2\2\u0099\u009b\7\37\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3"+
		"\2\2\2\u009b\u009c\3\2\2\2\u009c\u009d\58\35\2\u009d\25\3\2\2\2\u009e"+
		"\u009f\7\60\2\2\u009f\u00a0\7\33\2\2\u00a0\27\3\2\2\2\u00a1\u00a2\7\60"+
		"\2\2\u00a2\u00a3\7\32\2\2\u00a3\u00a4\5\66\34\2\u00a4\31\3\2\2\2\u00a5"+
		"\u00a6\7$\2\2\u00a6\u00a7\7%\2\2\u00a7\u00b3\58\35\2\u00a8\u00a9\7\4\2"+
		"\2\u00a9\u00ae\5:\36\2\u00aa\u00ab\7\5\2\2\u00ab\u00ad\5:\36\2\u00ac\u00aa"+
		"\3\2\2\2\u00ad\u00b0\3\2\2\2\u00ae\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b1\3\2\2\2\u00b0\u00ae\3\2\2\2\u00b1\u00b2\7\6\2\2\u00b2\u00b4\3\2"+
		"\2\2\u00b3\u00a8\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5"+
		"\u00b6\7\66\2\2\u00b6\u00b7\7\4\2\2\u00b7\u00bc\5\62\32\2\u00b8\u00b9"+
		"\7\5\2\2\u00b9\u00bb\5\62\32\2\u00ba\u00b8\3\2\2\2\u00bb\u00be\3\2\2\2"+
		"\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00bf\3\2\2\2\u00be\u00bc"+
		"\3\2\2\2\u00bf\u00c0\7\6\2\2\u00c0\33\3\2\2\2\u00c1\u00c3\7.\2\2\u00c2"+
		"\u00c4\t\2\2\2\u00c3\u00c2\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2"+
		"\2\2\u00c5\u00ca\5.\30\2\u00c6\u00c7\7\5\2\2\u00c7\u00c9\5.\30\2\u00c8"+
		"\u00c6\3\2\2\2\u00c9\u00cc\3\2\2\2\u00ca\u00c8\3\2\2\2\u00ca\u00cb\3\2"+
		"\2\2\u00cb\u00cd\3\2\2\2\u00cc\u00ca\3\2\2\2\u00cd\u00ce\7 \2\2\u00ce"+
		"\u00d1\5\60\31\2\u00cf\u00d0\78\2\2\u00d0\u00d2\5&\24\2\u00d1\u00cf\3"+
		"\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\35\3\2\2\2\u00d3\u00d4\7\63\2\2\u00d4"+
		"\u00d5\58\35\2\u00d5\u00d6\7/\2\2\u00d6\u00d7\5:\36\2\u00d7\u00d8\7\t"+
		"\2\2\u00d8\u00db\5\62\32\2\u00d9\u00da\78\2\2\u00da\u00dc\5&\24\2\u00db"+
		"\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\37\3\2\2\2\u00dd\u00de\5:\36"+
		"\2\u00de\u00e2\5\"\22\2\u00df\u00e1\5$\23\2\u00e0\u00df\3\2\2\2\u00e1"+
		"\u00e4\3\2\2\2\u00e2\u00e0\3\2\2\2\u00e2\u00e3\3\2\2\2\u00e3!\3\2\2\2"+
		"\u00e4\u00e2\3\2\2\2\u00e5\u00ee\7\17\2\2\u00e6\u00ee\7\20\2\2\u00e7\u00ee"+
		"\7\21\2\2\u00e8\u00ee\7\22\2\2\u00e9\u00ea\7\23\2\2\u00ea\u00eb\7\4\2"+
		"\2\u00eb\u00ec\7:\2\2\u00ec\u00ee\7\6\2\2\u00ed\u00e5\3\2\2\2\u00ed\u00e6"+
		"\3\2\2\2\u00ed\u00e7\3\2\2\2\u00ed\u00e8\3\2\2\2\u00ed\u00e9\3\2\2\2\u00ee"+
		"#\3\2\2\2\u00ef\u00f0\7+\2\2\u00f0\u00f4\7\'\2\2\u00f1\u00f2\7(\2\2\u00f2"+
		"\u00f4\7)\2\2\u00f3\u00ef\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f4%\3\2\2\2\u00f5"+
		"\u00f6\5(\25\2\u00f6\u00f7\5*\26\2\u00f7\u00f8\5(\25\2\u00f8\'\3\2\2\2"+
		"\u00f9\u00fc\5\64\33\2\u00fa\u00fc\5\62\32\2\u00fb\u00f9\3\2\2\2\u00fb"+
		"\u00fa\3\2\2\2\u00fc)\3\2\2\2\u00fd\u00fe\t\3\2\2\u00fe+\3\2\2\2\u00ff"+
		"\u0100\7+\2\2\u0100\u0101\7\'\2\2\u0101\u0102\7\4\2\2\u0102\u0107\5:\36"+
		"\2\u0103\u0104\7\5\2\2\u0104\u0106\5:\36\2\u0105\u0103\3\2\2\2\u0106\u0109"+
		"\3\2\2\2\u0107\u0105\3\2\2\2\u0107\u0108\3\2\2\2\u0108\u010a\3\2\2\2\u0109"+
		"\u0107\3\2\2\2\u010a\u010b\7\6\2\2\u010b-\3\2\2\2\u010c\u010f\7\7\2\2"+
		"\u010d\u010f\5\64\33\2\u010e\u010c\3\2\2\2\u010e\u010d\3\2\2\2\u010f/"+
		"\3\2\2\2\u0110\u0118\58\35\2\u0111\u0112\58\35\2\u0112\u0113\7&\2\2\u0113"+
		"\u0114\58\35\2\u0114\u0115\7*\2\2\u0115\u0116\5&\24\2\u0116\u0118\3\2"+
		"\2\2\u0117\u0110\3\2\2\2\u0117\u0111\3\2\2\2\u0118\61\3\2\2\2\u0119\u011a"+
		"\t\4\2\2\u011a\63\3\2\2\2\u011b\u011c\58\35\2\u011c\u011d\7\b\2\2\u011d"+
		"\u011f\3\2\2\2\u011e\u011b\3\2\2\2\u011e\u011f\3\2\2\2\u011f\u0120\3\2"+
		"\2\2\u0120\u0121\5:\36\2\u0121\65\3\2\2\2\u0122\u0123\79\2\2\u0123\67"+
		"\3\2\2\2\u0124\u0125\79\2\2\u01259\3\2\2\2\u0126\u0127\79\2\2\u0127;\3"+
		"\2\2\2\34?GNSYhr\177\u0084\u0094\u009a\u00ae\u00b3\u00bc\u00c3\u00ca\u00d1"+
		"\u00db\u00e2\u00ed\u00f3\u00fb\u0107\u010e\u0117\u011e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}