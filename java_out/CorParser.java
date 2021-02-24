// Generated from Cor.g4 by ANTLR 4.9.1
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CorParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, STRING=3, COMMENT=4, COMMENT_BLOCK=5, RAM=6, CONST=7, 
		GPU=8, IMPORT=9, AS=10, FOR=11, CONTINUE=12, BREAKALL=13, BREAK=14, SEMI=15, 
		COLON=16, OPAR=17, CPAR=18, OBRACKET=19, CBRACKET=20, OBRACE=21, CBRACE=22, 
		REGISTER=23, MNEMONIC=24, VARIABLE=25, WHITESPACE=26, OPERATOR=27, NUMBER=28;
	public static final int
		RULE_parse = 0, RULE_block = 1, RULE_file_import = 2, RULE_initial = 3, 
		RULE_statement = 4, RULE_statement_loop = 5, RULE_loop = 6, RULE_loop_keyword = 7, 
		RULE_assignment = 8, RULE_assignment_arr = 9, RULE_declaration = 10, RULE_expression = 11, 
		RULE_math = 12, RULE_exp_number = 13, RULE_array = 14, RULE_exp_var = 15, 
		RULE_array_init = 16, RULE_string = 17, RULE_arr_data = 18, RULE_instruction = 19, 
		RULE_argument = 20, RULE_register = 21, RULE_label = 22;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "block", "file_import", "initial", "statement", "statement_loop", 
			"loop", "loop_keyword", "assignment", "assignment_arr", "declaration", 
			"expression", "math", "exp_number", "array", "exp_var", "array_init", 
			"string", "arr_data", "instruction", "argument", "register", "label"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "','", null, null, null, "'ram'", null, "'gpu'", "'import'", 
			"'as'", "'for'", "'continue'", "'breakall'", "'break'", "';'", "':'", 
			"'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "STRING", "COMMENT", "COMMENT_BLOCK", "RAM", "CONST", 
			"GPU", "IMPORT", "AS", "FOR", "CONTINUE", "BREAKALL", "BREAK", "SEMI", 
			"COLON", "OPAR", "CPAR", "OBRACKET", "CBRACKET", "OBRACE", "CBRACE", 
			"REGISTER", "MNEMONIC", "VARIABLE", "WHITESPACE", "OPERATOR", "NUMBER"
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
	public String getGrammarFileName() { return "Cor.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public CorParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ParseContext extends ParserRuleContext {
		public InitialContext initial() {
			return getRuleContext(InitialContext.class,0);
		}
		public TerminalNode EOF() { return getToken(CorParser.EOF, 0); }
		public List<BlockContext> block() {
			return getRuleContexts(BlockContext.class);
		}
		public BlockContext block(int i) {
			return getRuleContext(BlockContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			initial();
			setState(50);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE) {
				{
				{
				setState(47);
				block();
				}
				}
				setState(52);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(53);
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

	public static class BlockContext extends ParserRuleContext {
		public LabelContext label() {
			return getRuleContext(LabelContext.class,0);
		}
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<Statement_loopContext> statement_loop() {
			return getRuleContexts(Statement_loopContext.class);
		}
		public Statement_loopContext statement_loop(int i) {
			return getRuleContext(Statement_loopContext.class,i);
		}
		public BlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitBlock(this);
		}
	}

	public final BlockContext block() throws RecognitionException {
		BlockContext _localctx = new BlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(55);
			label();
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << FOR) | (1L << MNEMONIC))) != 0)) {
				{
				setState(58);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RAM:
				case CONST:
				case MNEMONIC:
					{
					setState(56);
					statement();
					}
					break;
				case FOR:
					{
					setState(57);
					statement_loop();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(62);
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

	public static class File_importContext extends ParserRuleContext {
		public TerminalNode IMPORT() { return getToken(CorParser.IMPORT, 0); }
		public List<TerminalNode> VARIABLE() { return getTokens(CorParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(CorParser.VARIABLE, i);
		}
		public TerminalNode STRING() { return getToken(CorParser.STRING, 0); }
		public TerminalNode AS() { return getToken(CorParser.AS, 0); }
		public File_importContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_file_import; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterFile_import(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitFile_import(this);
		}
	}

	public final File_importContext file_import() throws RecognitionException {
		File_importContext _localctx = new File_importContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_file_import);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(IMPORT);
			setState(64);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==VARIABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(65);
				match(AS);
				setState(66);
				match(VARIABLE);
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

	public static class InitialContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<File_importContext> file_import() {
			return getRuleContexts(File_importContext.class);
		}
		public File_importContext file_import(int i) {
			return getRuleContext(File_importContext.class,i);
		}
		public InitialContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_initial; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterInitial(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitInitial(this);
		}
	}

	public final InitialContext initial() throws RecognitionException {
		InitialContext _localctx = new InitialContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_initial);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(73);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << IMPORT) | (1L << MNEMONIC))) != 0)) {
				{
				setState(71);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RAM:
				case CONST:
				case MNEMONIC:
					{
					setState(69);
					statement();
					}
					break;
				case IMPORT:
					{
					setState(70);
					file_import();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(75);
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

	public static class StatementContext extends ParserRuleContext {
		public DeclarationContext declaration() {
			return getRuleContext(DeclarationContext.class,0);
		}
		public AssignmentContext assignment() {
			return getRuleContext(AssignmentContext.class,0);
		}
		public Assignment_arrContext assignment_arr() {
			return getRuleContext(Assignment_arrContext.class,0);
		}
		public InstructionContext instruction() {
			return getRuleContext(InstructionContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitStatement(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_statement);
		try {
			setState(80);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(76);
				declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(77);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(78);
				assignment_arr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(79);
				instruction();
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

	public static class Statement_loopContext extends ParserRuleContext {
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public Statement_loopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterStatement_loop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitStatement_loop(this);
		}
	}

	public final Statement_loopContext statement_loop() throws RecognitionException {
		Statement_loopContext _localctx = new Statement_loopContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_statement_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(82);
			loop();
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

	public static class LoopContext extends ParserRuleContext {
		public TerminalNode FOR() { return getToken(CorParser.FOR, 0); }
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<TerminalNode> SEMI() { return getTokens(CorParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CorParser.SEMI, i);
		}
		public TerminalNode OBRACE() { return getToken(CorParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(CorParser.CBRACE, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public List<Loop_keywordContext> loop_keyword() {
			return getRuleContexts(Loop_keywordContext.class);
		}
		public Loop_keywordContext loop_keyword(int i) {
			return getRuleContext(Loop_keywordContext.class,i);
		}
		public List<LoopContext> loop() {
			return getRuleContexts(LoopContext.class);
		}
		public LoopContext loop(int i) {
			return getRuleContext(LoopContext.class,i);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitLoop(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_loop);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(FOR);
			setState(85);
			instruction();
			setState(86);
			match(SEMI);
			setState(87);
			instruction();
			setState(88);
			match(SEMI);
			setState(89);
			instruction();
			setState(90);
			match(SEMI);
			setState(91);
			match(OBRACE);
			setState(98);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FOR) | (1L << CONTINUE) | (1L << BREAKALL) | (1L << BREAK) | (1L << MNEMONIC) | (1L << VARIABLE))) != 0)) {
				{
				setState(96);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VARIABLE:
					{
					setState(92);
					label();
					}
					break;
				case MNEMONIC:
					{
					setState(93);
					instruction();
					}
					break;
				case CONTINUE:
				case BREAKALL:
				case BREAK:
					{
					setState(94);
					loop_keyword();
					}
					break;
				case FOR:
					{
					setState(95);
					loop();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(100);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(101);
			match(CBRACE);
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

	public static class Loop_keywordContext extends ParserRuleContext {
		public TerminalNode CONTINUE() { return getToken(CorParser.CONTINUE, 0); }
		public TerminalNode BREAK() { return getToken(CorParser.BREAK, 0); }
		public TerminalNode BREAKALL() { return getToken(CorParser.BREAKALL, 0); }
		public Loop_keywordContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop_keyword; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterLoop_keyword(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitLoop_keyword(this);
		}
	}

	public final Loop_keywordContext loop_keyword() throws RecognitionException {
		Loop_keywordContext _localctx = new Loop_keywordContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_loop_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONTINUE) | (1L << BREAKALL) | (1L << BREAK))) != 0)) ) {
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

	public static class AssignmentContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CorParser.CONST, 0); }
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public AssignmentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterAssignment(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitAssignment(this);
		}
	}

	public final AssignmentContext assignment() throws RecognitionException {
		AssignmentContext _localctx = new AssignmentContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(CONST);
			setState(106);
			match(VARIABLE);
			setState(107);
			match(T__0);
			setState(108);
			expression();
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

	public static class Assignment_arrContext extends ParserRuleContext {
		public TerminalNode CONST() { return getToken(CorParser.CONST, 0); }
		public ArrayContext array() {
			return getRuleContext(ArrayContext.class,0);
		}
		public Assignment_arrContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_assignment_arr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterAssignment_arr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitAssignment_arr(this);
		}
	}

	public final Assignment_arrContext assignment_arr() throws RecognitionException {
		Assignment_arrContext _localctx = new Assignment_arrContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_assignment_arr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(110);
			match(CONST);
			setState(111);
			array();
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

	public static class DeclarationContext extends ParserRuleContext {
		public TerminalNode RAM() { return getToken(CorParser.RAM, 0); }
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
		public List<Array_initContext> array_init() {
			return getRuleContexts(Array_initContext.class);
		}
		public Array_initContext array_init(int i) {
			return getRuleContext(Array_initContext.class,i);
		}
		public DeclarationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declaration; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterDeclaration(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitDeclaration(this);
		}
	}

	public final DeclarationContext declaration() throws RecognitionException {
		DeclarationContext _localctx = new DeclarationContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(RAM);
			setState(114);
			match(VARIABLE);
			setState(118);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBRACKET) {
				{
				{
				setState(115);
				array_init();
				}
				}
				setState(120);
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

	public static class ExpressionContext extends ParserRuleContext {
		public Exp_numberContext exp_number() {
			return getRuleContext(Exp_numberContext.class,0);
		}
		public Exp_varContext exp_var() {
			return getRuleContext(Exp_varContext.class,0);
		}
		public MathContext math() {
			return getRuleContext(MathContext.class,0);
		}
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_expression);
		try {
			setState(124);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,10,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(121);
				exp_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(122);
				exp_var();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(123);
				math();
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

	public static class MathContext extends ParserRuleContext {
		public List<TerminalNode> NUMBER() { return getTokens(CorParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(CorParser.NUMBER, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(CorParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(CorParser.VARIABLE, i);
		}
		public List<TerminalNode> OPERATOR() { return getTokens(CorParser.OPERATOR); }
		public TerminalNode OPERATOR(int i) {
			return getToken(CorParser.OPERATOR, i);
		}
		public MathContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_math; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterMath(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitMath(this);
		}
	}

	public final MathContext math() throws RecognitionException {
		MathContext _localctx = new MathContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_math);
		int _la;
		try {
			int _alt;
			setState(135);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(128); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(126);
						_la = _input.LA(1);
						if ( !(_la==VARIABLE || _la==NUMBER) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(127);
						match(OPERATOR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(130); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,11,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(132);
				_la = _input.LA(1);
				if ( !(_la==VARIABLE || _la==NUMBER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				break;
			case OPERATOR:
				enterOuterAlt(_localctx, 2);
				{
				setState(133);
				match(OPERATOR);
				setState(134);
				_la = _input.LA(1);
				if ( !(_la==VARIABLE || _la==NUMBER) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
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

	public static class Exp_numberContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(CorParser.NUMBER, 0); }
		public Exp_numberContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_number; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterExp_number(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitExp_number(this);
		}
	}

	public final Exp_numberContext exp_number() throws RecognitionException {
		Exp_numberContext _localctx = new Exp_numberContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_exp_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(137);
			match(NUMBER);
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

	public static class ArrayContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
		public Arr_dataContext arr_data() {
			return getRuleContext(Arr_dataContext.class,0);
		}
		public StringContext string() {
			return getRuleContext(StringContext.class,0);
		}
		public List<Array_initContext> array_init() {
			return getRuleContexts(Array_initContext.class);
		}
		public Array_initContext array_init(int i) {
			return getRuleContext(Array_initContext.class,i);
		}
		public ArrayContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterArray(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitArray(this);
		}
	}

	public final ArrayContext array() throws RecognitionException {
		ArrayContext _localctx = new ArrayContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(VARIABLE);
			setState(141); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(140);
				array_init();
				}
				}
				setState(143); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OBRACKET );
			setState(145);
			match(T__0);
			setState(148);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OBRACE:
				{
				setState(146);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(147);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class Exp_varContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
		public List<Array_initContext> array_init() {
			return getRuleContexts(Array_initContext.class);
		}
		public Array_initContext array_init(int i) {
			return getRuleContext(Array_initContext.class,i);
		}
		public Exp_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterExp_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitExp_var(this);
		}
	}

	public final Exp_varContext exp_var() throws RecognitionException {
		Exp_varContext _localctx = new Exp_varContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_exp_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(VARIABLE);
			setState(154);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBRACKET) {
				{
				{
				setState(151);
				array_init();
				}
				}
				setState(156);
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

	public static class Array_initContext extends ParserRuleContext {
		public TerminalNode OBRACKET() { return getToken(CorParser.OBRACKET, 0); }
		public TerminalNode CBRACKET() { return getToken(CorParser.CBRACKET, 0); }
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public Array_initContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_array_init; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterArray_init(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitArray_init(this);
		}
	}

	public final Array_initContext array_init() throws RecognitionException {
		Array_initContext _localctx = new Array_initContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_array_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(157);
			match(OBRACKET);
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << OPERATOR) | (1L << NUMBER))) != 0)) {
				{
				setState(158);
				expression();
				}
			}

			setState(161);
			match(CBRACKET);
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

	public static class StringContext extends ParserRuleContext {
		public TerminalNode STRING() { return getToken(CorParser.STRING, 0); }
		public StringContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_string; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterString(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitString(this);
		}
	}

	public final StringContext string() throws RecognitionException {
		StringContext _localctx = new StringContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(163);
			match(STRING);
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

	public static class Arr_dataContext extends ParserRuleContext {
		public TerminalNode OBRACE() { return getToken(CorParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(CorParser.CBRACE, 0); }
		public List<ExpressionContext> expression() {
			return getRuleContexts(ExpressionContext.class);
		}
		public ExpressionContext expression(int i) {
			return getRuleContext(ExpressionContext.class,i);
		}
		public List<Arr_dataContext> arr_data() {
			return getRuleContexts(Arr_dataContext.class);
		}
		public Arr_dataContext arr_data(int i) {
			return getRuleContext(Arr_dataContext.class,i);
		}
		public List<StringContext> string() {
			return getRuleContexts(StringContext.class);
		}
		public StringContext string(int i) {
			return getRuleContext(StringContext.class,i);
		}
		public Arr_dataContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arr_data; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterArr_data(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitArr_data(this);
		}
	}

	public final Arr_dataContext arr_data() throws RecognitionException {
		Arr_dataContext _localctx = new Arr_dataContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_arr_data);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(OBRACE);
			setState(175);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(169);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case VARIABLE:
					case OPERATOR:
					case NUMBER:
						{
						setState(166);
						expression();
						}
						break;
					case OBRACE:
						{
						setState(167);
						arr_data();
						}
						break;
					case STRING:
						{
						setState(168);
						string();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(171);
					match(T__1);
					}
					} 
				}
				setState(177);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,18,_ctx);
			}
			setState(181);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				{
				setState(178);
				expression();
				}
				break;
			case OBRACE:
				{
				setState(179);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(180);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(184);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(183);
				match(T__1);
				}
			}

			setState(186);
			match(CBRACE);
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

	public static class InstructionContext extends ParserRuleContext {
		public TerminalNode MNEMONIC() { return getToken(CorParser.MNEMONIC, 0); }
		public List<ArgumentContext> argument() {
			return getRuleContexts(ArgumentContext.class);
		}
		public ArgumentContext argument(int i) {
			return getRuleContext(ArgumentContext.class,i);
		}
		public InstructionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_instruction; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterInstruction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitInstruction(this);
		}
	}

	public final InstructionContext instruction() throws RecognitionException {
		InstructionContext _localctx = new InstructionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_instruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(188);
			match(MNEMONIC);
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,22,_ctx) ) {
			case 1:
				{
				setState(189);
				argument();
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(190);
					match(T__1);
					setState(191);
					argument();
					}
					}
					setState(196);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
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

	public static class ArgumentContext extends ParserRuleContext {
		public RegisterContext register() {
			return getRuleContext(RegisterContext.class,0);
		}
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public TerminalNode RAM() { return getToken(CorParser.RAM, 0); }
		public TerminalNode CONST() { return getToken(CorParser.CONST, 0); }
		public TerminalNode GPU() { return getToken(CorParser.GPU, 0); }
		public ArgumentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_argument; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterArgument(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitArgument(this);
		}
	}

	public final ArgumentContext argument() throws RecognitionException {
		ArgumentContext _localctx = new ArgumentContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_argument);
		try {
			setState(204);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REGISTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				register();
				}
				break;
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(200);
				expression();
				}
				break;
			case RAM:
				enterOuterAlt(_localctx, 3);
				{
				setState(201);
				match(RAM);
				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 4);
				{
				setState(202);
				match(CONST);
				}
				break;
			case GPU:
				enterOuterAlt(_localctx, 5);
				{
				setState(203);
				match(GPU);
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

	public static class RegisterContext extends ParserRuleContext {
		public TerminalNode REGISTER() { return getToken(CorParser.REGISTER, 0); }
		public RegisterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_register; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterRegister(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitRegister(this);
		}
	}

	public final RegisterContext register() throws RecognitionException {
		RegisterContext _localctx = new RegisterContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_register);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(206);
			match(REGISTER);
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

	public static class LabelContext extends ParserRuleContext {
		public List<TerminalNode> VARIABLE() { return getTokens(CorParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(CorParser.VARIABLE, i);
		}
		public TerminalNode COLON() { return getToken(CorParser.COLON, 0); }
		public TerminalNode OPAR() { return getToken(CorParser.OPAR, 0); }
		public TerminalNode CPAR() { return getToken(CorParser.CPAR, 0); }
		public LabelContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_label; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterLabel(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitLabel(this);
		}
	}

	public final LabelContext label() throws RecognitionException {
		LabelContext _localctx = new LabelContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_label);
		try {
			setState(215);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,24,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(208);
				match(VARIABLE);
				setState(209);
				match(COLON);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(210);
				match(VARIABLE);
				setState(211);
				match(OPAR);
				setState(212);
				match(VARIABLE);
				setState(213);
				match(CPAR);
				setState(214);
				match(COLON);
				}
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

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\36\u00dc\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\3\2\3\2\7"+
		"\2\63\n\2\f\2\16\2\66\13\2\3\2\3\2\3\3\3\3\3\3\7\3=\n\3\f\3\16\3@\13\3"+
		"\3\4\3\4\3\4\3\4\5\4F\n\4\3\5\3\5\7\5J\n\5\f\5\16\5M\13\5\3\6\3\6\3\6"+
		"\3\6\5\6S\n\6\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\7\bc\n\b\f\b\16\bf\13\b\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\f\3\f\3\f\7\fw\n\f\f\f\16\fz\13\f\3\r\3\r\3\r\5\r\177\n\r\3\16"+
		"\3\16\6\16\u0083\n\16\r\16\16\16\u0084\3\16\3\16\3\16\5\16\u008a\n\16"+
		"\3\17\3\17\3\20\3\20\6\20\u0090\n\20\r\20\16\20\u0091\3\20\3\20\3\20\5"+
		"\20\u0097\n\20\3\21\3\21\7\21\u009b\n\21\f\21\16\21\u009e\13\21\3\22\3"+
		"\22\5\22\u00a2\n\22\3\22\3\22\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u00ac"+
		"\n\24\3\24\3\24\7\24\u00b0\n\24\f\24\16\24\u00b3\13\24\3\24\3\24\3\24"+
		"\5\24\u00b8\n\24\3\24\5\24\u00bb\n\24\3\24\3\24\3\25\3\25\3\25\3\25\7"+
		"\25\u00c3\n\25\f\25\16\25\u00c6\13\25\5\25\u00c8\n\25\3\26\3\26\3\26\3"+
		"\26\3\26\5\26\u00cf\n\26\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\5\30\u00da\n\30\3\30\2\2\31\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \""+
		"$&(*,.\2\5\4\2\5\5\33\33\3\2\16\20\4\2\33\33\36\36\2\u00e7\2\60\3\2\2"+
		"\2\49\3\2\2\2\6A\3\2\2\2\bK\3\2\2\2\nR\3\2\2\2\fT\3\2\2\2\16V\3\2\2\2"+
		"\20i\3\2\2\2\22k\3\2\2\2\24p\3\2\2\2\26s\3\2\2\2\30~\3\2\2\2\32\u0089"+
		"\3\2\2\2\34\u008b\3\2\2\2\36\u008d\3\2\2\2 \u0098\3\2\2\2\"\u009f\3\2"+
		"\2\2$\u00a5\3\2\2\2&\u00a7\3\2\2\2(\u00be\3\2\2\2*\u00ce\3\2\2\2,\u00d0"+
		"\3\2\2\2.\u00d9\3\2\2\2\60\64\5\b\5\2\61\63\5\4\3\2\62\61\3\2\2\2\63\66"+
		"\3\2\2\2\64\62\3\2\2\2\64\65\3\2\2\2\65\67\3\2\2\2\66\64\3\2\2\2\678\7"+
		"\2\2\38\3\3\2\2\29>\5.\30\2:=\5\n\6\2;=\5\f\7\2<:\3\2\2\2<;\3\2\2\2=@"+
		"\3\2\2\2><\3\2\2\2>?\3\2\2\2?\5\3\2\2\2@>\3\2\2\2AB\7\13\2\2BE\t\2\2\2"+
		"CD\7\f\2\2DF\7\33\2\2EC\3\2\2\2EF\3\2\2\2F\7\3\2\2\2GJ\5\n\6\2HJ\5\6\4"+
		"\2IG\3\2\2\2IH\3\2\2\2JM\3\2\2\2KI\3\2\2\2KL\3\2\2\2L\t\3\2\2\2MK\3\2"+
		"\2\2NS\5\26\f\2OS\5\22\n\2PS\5\24\13\2QS\5(\25\2RN\3\2\2\2RO\3\2\2\2R"+
		"P\3\2\2\2RQ\3\2\2\2S\13\3\2\2\2TU\5\16\b\2U\r\3\2\2\2VW\7\r\2\2WX\5(\25"+
		"\2XY\7\21\2\2YZ\5(\25\2Z[\7\21\2\2[\\\5(\25\2\\]\7\21\2\2]d\7\27\2\2^"+
		"c\5.\30\2_c\5(\25\2`c\5\20\t\2ac\5\16\b\2b^\3\2\2\2b_\3\2\2\2b`\3\2\2"+
		"\2ba\3\2\2\2cf\3\2\2\2db\3\2\2\2de\3\2\2\2eg\3\2\2\2fd\3\2\2\2gh\7\30"+
		"\2\2h\17\3\2\2\2ij\t\3\2\2j\21\3\2\2\2kl\7\t\2\2lm\7\33\2\2mn\7\3\2\2"+
		"no\5\30\r\2o\23\3\2\2\2pq\7\t\2\2qr\5\36\20\2r\25\3\2\2\2st\7\b\2\2tx"+
		"\7\33\2\2uw\5\"\22\2vu\3\2\2\2wz\3\2\2\2xv\3\2\2\2xy\3\2\2\2y\27\3\2\2"+
		"\2zx\3\2\2\2{\177\5\34\17\2|\177\5 \21\2}\177\5\32\16\2~{\3\2\2\2~|\3"+
		"\2\2\2~}\3\2\2\2\177\31\3\2\2\2\u0080\u0081\t\4\2\2\u0081\u0083\7\35\2"+
		"\2\u0082\u0080\3\2\2\2\u0083\u0084\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085"+
		"\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u008a\t\4\2\2\u0087\u0088\7\35\2\2"+
		"\u0088\u008a\t\4\2\2\u0089\u0082\3\2\2\2\u0089\u0087\3\2\2\2\u008a\33"+
		"\3\2\2\2\u008b\u008c\7\36\2\2\u008c\35\3\2\2\2\u008d\u008f\7\33\2\2\u008e"+
		"\u0090\5\"\22\2\u008f\u008e\3\2\2\2\u0090\u0091\3\2\2\2\u0091\u008f\3"+
		"\2\2\2\u0091\u0092\3\2\2\2\u0092\u0093\3\2\2\2\u0093\u0096\7\3\2\2\u0094"+
		"\u0097\5&\24\2\u0095\u0097\5$\23\2\u0096\u0094\3\2\2\2\u0096\u0095\3\2"+
		"\2\2\u0097\37\3\2\2\2\u0098\u009c\7\33\2\2\u0099\u009b\5\"\22\2\u009a"+
		"\u0099\3\2\2\2\u009b\u009e\3\2\2\2\u009c\u009a\3\2\2\2\u009c\u009d\3\2"+
		"\2\2\u009d!\3\2\2\2\u009e\u009c\3\2\2\2\u009f\u00a1\7\25\2\2\u00a0\u00a2"+
		"\5\30\r\2\u00a1\u00a0\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a3\3\2\2\2"+
		"\u00a3\u00a4\7\26\2\2\u00a4#\3\2\2\2\u00a5\u00a6\7\5\2\2\u00a6%\3\2\2"+
		"\2\u00a7\u00b1\7\27\2\2\u00a8\u00ac\5\30\r\2\u00a9\u00ac\5&\24\2\u00aa"+
		"\u00ac\5$\23\2\u00ab\u00a8\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2"+
		"\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\7\4\2\2\u00ae\u00b0\3\2\2\2\u00af"+
		"\u00ab\3\2\2\2\u00b0\u00b3\3\2\2\2\u00b1\u00af\3\2\2\2\u00b1\u00b2\3\2"+
		"\2\2\u00b2\u00b7\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b4\u00b8\5\30\r\2\u00b5"+
		"\u00b8\5&\24\2\u00b6\u00b8\5$\23\2\u00b7\u00b4\3\2\2\2\u00b7\u00b5\3\2"+
		"\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00ba\3\2\2\2\u00b9\u00bb\7\4\2\2\u00ba"+
		"\u00b9\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb\u00bc\3\2\2\2\u00bc\u00bd\7\30"+
		"\2\2\u00bd\'\3\2\2\2\u00be\u00c7\7\32\2\2\u00bf\u00c4\5*\26\2\u00c0\u00c1"+
		"\7\4\2\2\u00c1\u00c3\5*\26\2\u00c2\u00c0\3\2\2\2\u00c3\u00c6\3\2\2\2\u00c4"+
		"\u00c2\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c8\3\2\2\2\u00c6\u00c4\3\2"+
		"\2\2\u00c7\u00bf\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c8)\3\2\2\2\u00c9\u00cf"+
		"\5,\27\2\u00ca\u00cf\5\30\r\2\u00cb\u00cf\7\b\2\2\u00cc\u00cf\7\t\2\2"+
		"\u00cd\u00cf\7\n\2\2\u00ce\u00c9\3\2\2\2\u00ce\u00ca\3\2\2\2\u00ce\u00cb"+
		"\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cd\3\2\2\2\u00cf+\3\2\2\2\u00d0"+
		"\u00d1\7\31\2\2\u00d1-\3\2\2\2\u00d2\u00d3\7\33\2\2\u00d3\u00da\7\22\2"+
		"\2\u00d4\u00d5\7\33\2\2\u00d5\u00d6\7\23\2\2\u00d6\u00d7\7\33\2\2\u00d7"+
		"\u00d8\7\24\2\2\u00d8\u00da\7\22\2\2\u00d9\u00d2\3\2\2\2\u00d9\u00d4\3"+
		"\2\2\2\u00da/\3\2\2\2\33\64<>EIKRbdx~\u0084\u0089\u0091\u0096\u009c\u00a1"+
		"\u00ab\u00b1\u00b7\u00ba\u00c4\u00c7\u00ce\u00d9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}