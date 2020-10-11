// Generated from Cor.g4 by ANTLR 4.8
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
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, RAM=4, CONST=5, GPU=6, IMPORT=7, AS=8, STRING=9, 
		COMMENT=10, COMMENT_BLOCK=11, OBRACKET=12, CBRACKET=13, OBRACE=14, CBRACE=15, 
		REGISTER=16, MNEMONIC=17, VARIABLE=18, WHITESPACE=19, OPERATOR=20, NUMBER=21;
	public static final int
		RULE_parse = 0, RULE_block = 1, RULE_file_import = 2, RULE_initial = 3, 
		RULE_statement = 4, RULE_assignment = 5, RULE_assignment_arr = 6, RULE_declaration = 7, 
		RULE_expression = 8, RULE_math = 9, RULE_exp_number = 10, RULE_exp_var = 11, 
		RULE_array = 12, RULE_string = 13, RULE_array_init = 14, RULE_arr_data = 15, 
		RULE_instruction = 16, RULE_argument = 17, RULE_register = 18, RULE_label = 19;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "block", "file_import", "initial", "statement", "assignment", 
			"assignment_arr", "declaration", "expression", "math", "exp_number", 
			"exp_var", "array", "string", "array_init", "arr_data", "instruction", 
			"argument", "register", "label"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "','", "':'", "'ram'", null, "'gpu'", "'import'", "'as'", 
			null, null, null, "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, "RAM", "CONST", "GPU", "IMPORT", "AS", "STRING", 
			"COMMENT", "COMMENT_BLOCK", "OBRACKET", "CBRACKET", "OBRACE", "CBRACE", 
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
			setState(40);
			initial();
			setState(44);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE) {
				{
				{
				setState(41);
				block();
				}
				}
				setState(46);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(47);
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
			setState(49);
			label();
			setState(53);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << MNEMONIC))) != 0)) {
				{
				{
				setState(50);
				statement();
				}
				}
				setState(55);
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
			setState(56);
			match(IMPORT);
			setState(57);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==VARIABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(60);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(58);
				match(AS);
				setState(59);
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
			setState(66);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << IMPORT) | (1L << MNEMONIC))) != 0)) {
				{
				setState(64);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RAM:
				case CONST:
				case MNEMONIC:
					{
					setState(62);
					statement();
					}
					break;
				case IMPORT:
					{
					setState(63);
					file_import();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(68);
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
			setState(73);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(69);
				declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(70);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(71);
				assignment_arr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(72);
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
		enterRule(_localctx, 10, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			match(CONST);
			setState(76);
			match(VARIABLE);
			setState(77);
			match(T__0);
			setState(78);
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
		enterRule(_localctx, 12, RULE_assignment_arr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(CONST);
			setState(81);
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
		enterRule(_localctx, 14, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(RAM);
			setState(84);
			match(VARIABLE);
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBRACKET) {
				{
				{
				setState(85);
				array_init();
				}
				}
				setState(90);
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
		enterRule(_localctx, 16, RULE_expression);
		try {
			setState(94);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				exp_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				exp_var();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
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
		enterRule(_localctx, 18, RULE_math);
		int _la;
		try {
			int _alt;
			setState(105);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case NUMBER:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(98); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(96);
						_la = _input.LA(1);
						if ( !(_la==VARIABLE || _la==NUMBER) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(97);
						match(OPERATOR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(100); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(102);
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
				setState(103);
				match(OPERATOR);
				setState(104);
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
		enterRule(_localctx, 20, RULE_exp_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(107);
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

	public static class Exp_varContext extends ParserRuleContext {
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
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
		enterRule(_localctx, 22, RULE_exp_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(VARIABLE);
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
		enterRule(_localctx, 24, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(111);
			match(VARIABLE);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				array_init();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OBRACKET );
			setState(117);
			match(T__0);
			setState(120);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OBRACE:
				{
				setState(118);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(119);
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
		enterRule(_localctx, 26, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(122);
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
		enterRule(_localctx, 28, RULE_array_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(OBRACKET);
			setState(126);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << VARIABLE) | (1L << OPERATOR) | (1L << NUMBER))) != 0)) {
				{
				setState(125);
				expression();
				}
			}

			setState(128);
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
		enterRule(_localctx, 30, RULE_arr_data);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(OBRACE);
			setState(140);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(134);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case VARIABLE:
					case OPERATOR:
					case NUMBER:
						{
						setState(131);
						expression();
						}
						break;
					case OBRACE:
						{
						setState(132);
						arr_data();
						}
						break;
					case STRING:
						{
						setState(133);
						string();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(136);
					match(T__1);
					}
					} 
				}
				setState(142);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			}
			setState(146);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				{
				setState(143);
				expression();
				}
				break;
			case OBRACE:
				{
				setState(144);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(145);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(148);
				match(T__1);
				}
			}

			setState(151);
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
		enterRule(_localctx, 32, RULE_instruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(153);
			match(MNEMONIC);
			setState(162);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,18,_ctx) ) {
			case 1:
				{
				setState(154);
				argument();
				setState(159);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(155);
					match(T__1);
					setState(156);
					argument();
					}
					}
					setState(161);
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
		enterRule(_localctx, 34, RULE_argument);
		try {
			setState(169);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REGISTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(164);
				register();
				}
				break;
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(165);
				expression();
				}
				break;
			case RAM:
				enterOuterAlt(_localctx, 3);
				{
				setState(166);
				match(RAM);
				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 4);
				{
				setState(167);
				match(CONST);
				}
				break;
			case GPU:
				enterOuterAlt(_localctx, 5);
				{
				setState(168);
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
		enterRule(_localctx, 36, RULE_register);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(171);
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
		public TerminalNode VARIABLE() { return getToken(CorParser.VARIABLE, 0); }
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
		enterRule(_localctx, 38, RULE_label);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(VARIABLE);
			setState(174);
			match(T__2);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\27\u00b3\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\7\2-\n\2\f\2\16\2\60\13\2\3\2\3"+
		"\2\3\3\3\3\7\3\66\n\3\f\3\16\39\13\3\3\4\3\4\3\4\3\4\5\4?\n\4\3\5\3\5"+
		"\7\5C\n\5\f\5\16\5F\13\5\3\6\3\6\3\6\3\6\5\6L\n\6\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\7\tY\n\t\f\t\16\t\\\13\t\3\n\3\n\3\n\5\na\n\n"+
		"\3\13\3\13\6\13e\n\13\r\13\16\13f\3\13\3\13\3\13\5\13l\n\13\3\f\3\f\3"+
		"\r\3\r\3\16\3\16\6\16t\n\16\r\16\16\16u\3\16\3\16\3\16\5\16{\n\16\3\17"+
		"\3\17\3\20\3\20\5\20\u0081\n\20\3\20\3\20\3\21\3\21\3\21\3\21\5\21\u0089"+
		"\n\21\3\21\3\21\7\21\u008d\n\21\f\21\16\21\u0090\13\21\3\21\3\21\3\21"+
		"\5\21\u0095\n\21\3\21\5\21\u0098\n\21\3\21\3\21\3\22\3\22\3\22\3\22\7"+
		"\22\u00a0\n\22\f\22\16\22\u00a3\13\22\5\22\u00a5\n\22\3\23\3\23\3\23\3"+
		"\23\3\23\5\23\u00ac\n\23\3\24\3\24\3\25\3\25\3\25\3\25\2\2\26\2\4\6\b"+
		"\n\f\16\20\22\24\26\30\32\34\36 \"$&(\2\4\4\2\13\13\24\24\4\2\24\24\27"+
		"\27\2\u00ba\2*\3\2\2\2\4\63\3\2\2\2\6:\3\2\2\2\bD\3\2\2\2\nK\3\2\2\2\f"+
		"M\3\2\2\2\16R\3\2\2\2\20U\3\2\2\2\22`\3\2\2\2\24k\3\2\2\2\26m\3\2\2\2"+
		"\30o\3\2\2\2\32q\3\2\2\2\34|\3\2\2\2\36~\3\2\2\2 \u0084\3\2\2\2\"\u009b"+
		"\3\2\2\2$\u00ab\3\2\2\2&\u00ad\3\2\2\2(\u00af\3\2\2\2*.\5\b\5\2+-\5\4"+
		"\3\2,+\3\2\2\2-\60\3\2\2\2.,\3\2\2\2./\3\2\2\2/\61\3\2\2\2\60.\3\2\2\2"+
		"\61\62\7\2\2\3\62\3\3\2\2\2\63\67\5(\25\2\64\66\5\n\6\2\65\64\3\2\2\2"+
		"\669\3\2\2\2\67\65\3\2\2\2\678\3\2\2\28\5\3\2\2\29\67\3\2\2\2:;\7\t\2"+
		"\2;>\t\2\2\2<=\7\n\2\2=?\7\24\2\2><\3\2\2\2>?\3\2\2\2?\7\3\2\2\2@C\5\n"+
		"\6\2AC\5\6\4\2B@\3\2\2\2BA\3\2\2\2CF\3\2\2\2DB\3\2\2\2DE\3\2\2\2E\t\3"+
		"\2\2\2FD\3\2\2\2GL\5\20\t\2HL\5\f\7\2IL\5\16\b\2JL\5\"\22\2KG\3\2\2\2"+
		"KH\3\2\2\2KI\3\2\2\2KJ\3\2\2\2L\13\3\2\2\2MN\7\7\2\2NO\7\24\2\2OP\7\3"+
		"\2\2PQ\5\22\n\2Q\r\3\2\2\2RS\7\7\2\2ST\5\32\16\2T\17\3\2\2\2UV\7\6\2\2"+
		"VZ\7\24\2\2WY\5\36\20\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\21\3"+
		"\2\2\2\\Z\3\2\2\2]a\5\26\f\2^a\5\30\r\2_a\5\24\13\2`]\3\2\2\2`^\3\2\2"+
		"\2`_\3\2\2\2a\23\3\2\2\2bc\t\3\2\2ce\7\26\2\2db\3\2\2\2ef\3\2\2\2fd\3"+
		"\2\2\2fg\3\2\2\2gh\3\2\2\2hl\t\3\2\2ij\7\26\2\2jl\t\3\2\2kd\3\2\2\2ki"+
		"\3\2\2\2l\25\3\2\2\2mn\7\27\2\2n\27\3\2\2\2op\7\24\2\2p\31\3\2\2\2qs\7"+
		"\24\2\2rt\5\36\20\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2\2\2"+
		"wz\7\3\2\2x{\5 \21\2y{\5\34\17\2zx\3\2\2\2zy\3\2\2\2{\33\3\2\2\2|}\7\13"+
		"\2\2}\35\3\2\2\2~\u0080\7\16\2\2\177\u0081\5\22\n\2\u0080\177\3\2\2\2"+
		"\u0080\u0081\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0083\7\17\2\2\u0083\37"+
		"\3\2\2\2\u0084\u008e\7\20\2\2\u0085\u0089\5\22\n\2\u0086\u0089\5 \21\2"+
		"\u0087\u0089\5\34\17\2\u0088\u0085\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0087"+
		"\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008b\7\4\2\2\u008b\u008d\3\2\2\2\u008c"+
		"\u0088\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008e\u008f\3\2"+
		"\2\2\u008f\u0094\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0095\5\22\n\2\u0092"+
		"\u0095\5 \21\2\u0093\u0095\5\34\17\2\u0094\u0091\3\2\2\2\u0094\u0092\3"+
		"\2\2\2\u0094\u0093\3\2\2\2\u0095\u0097\3\2\2\2\u0096\u0098\7\4\2\2\u0097"+
		"\u0096\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\7\21"+
		"\2\2\u009a!\3\2\2\2\u009b\u00a4\7\23\2\2\u009c\u00a1\5$\23\2\u009d\u009e"+
		"\7\4\2\2\u009e\u00a0\5$\23\2\u009f\u009d\3\2\2\2\u00a0\u00a3\3\2\2\2\u00a1"+
		"\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a5\3\2\2\2\u00a3\u00a1\3\2"+
		"\2\2\u00a4\u009c\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5#\3\2\2\2\u00a6\u00ac"+
		"\5&\24\2\u00a7\u00ac\5\22\n\2\u00a8\u00ac\7\6\2\2\u00a9\u00ac\7\7\2\2"+
		"\u00aa\u00ac\7\b\2\2\u00ab\u00a6\3\2\2\2\u00ab\u00a7\3\2\2\2\u00ab\u00a8"+
		"\3\2\2\2\u00ab\u00a9\3\2\2\2\u00ab\u00aa\3\2\2\2\u00ac%\3\2\2\2\u00ad"+
		"\u00ae\7\22\2\2\u00ae\'\3\2\2\2\u00af\u00b0\7\24\2\2\u00b0\u00b1\7\5\2"+
		"\2\u00b1)\3\2\2\2\26.\67>BDKZ`fkuz\u0080\u0088\u008e\u0094\u0097\u00a1"+
		"\u00a4\u00ab";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}