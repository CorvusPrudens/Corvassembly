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
		GPU=8, IMPORT=9, AS=10, FOR=11, CONTINUE=12, BREAKALL=13, BREAK=14, IF=15, 
		ELIF=16, ELSE=17, IS=18, CONDITION=19, SEMI=20, COLON=21, OPAR=22, CPAR=23, 
		OBRACKET=24, CBRACKET=25, OBRACE=26, CBRACE=27, REGISTER=28, MNEMONIC=29, 
		VARIABLE=30, WHITESPACE=31, COMPARATOR=32, OPERATOR=33, NUMBER=34;
	public static final int
		RULE_parse = 0, RULE_block = 1, RULE_file_import = 2, RULE_initial = 3, 
		RULE_statement = 4, RULE_statement_loop = 5, RULE_statement_if = 6, RULE_cond_block = 7, 
		RULE_loop = 8, RULE_loop_keyword = 9, RULE_conditional = 10, RULE_if_stat = 11, 
		RULE_elif_stat = 12, RULE_else_stat = 13, RULE_if_chain = 14, RULE_assignment = 15, 
		RULE_assignment_arr = 16, RULE_declaration = 17, RULE_expression = 18, 
		RULE_math = 19, RULE_exp_number = 20, RULE_array = 21, RULE_exp_var = 22, 
		RULE_array_init = 23, RULE_string = 24, RULE_arr_data = 25, RULE_instruction = 26, 
		RULE_argument = 27, RULE_register = 28, RULE_label = 29;
	private static String[] makeRuleNames() {
		return new String[] {
			"parse", "block", "file_import", "initial", "statement", "statement_loop", 
			"statement_if", "cond_block", "loop", "loop_keyword", "conditional", 
			"if_stat", "elif_stat", "else_stat", "if_chain", "assignment", "assignment_arr", 
			"declaration", "expression", "math", "exp_number", "array", "exp_var", 
			"array_init", "string", "arr_data", "instruction", "argument", "register", 
			"label"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "','", null, null, null, "'ram'", null, "'gpu'", "'import'", 
			"'as'", "'for'", "'continue'", "'breakall'", "'break'", "'if'", "'elif'", 
			"'else'", "'is'", null, "';'", "':'", "'('", "')'", "'['", "']'", "'{'", 
			"'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "STRING", "COMMENT", "COMMENT_BLOCK", "RAM", "CONST", 
			"GPU", "IMPORT", "AS", "FOR", "CONTINUE", "BREAKALL", "BREAK", "IF", 
			"ELIF", "ELSE", "IS", "CONDITION", "SEMI", "COLON", "OPAR", "CPAR", "OBRACKET", 
			"CBRACKET", "OBRACE", "CBRACE", "REGISTER", "MNEMONIC", "VARIABLE", "WHITESPACE", 
			"COMPARATOR", "OPERATOR", "NUMBER"
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
			setState(60);
			initial();
			setState(64);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==VARIABLE) {
				{
				{
				setState(61);
				block();
				}
				}
				setState(66);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(67);
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
		public List<Statement_ifContext> statement_if() {
			return getRuleContexts(Statement_ifContext.class);
		}
		public Statement_ifContext statement_if(int i) {
			return getRuleContext(Statement_ifContext.class,i);
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
			setState(69);
			label();
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << FOR) | (1L << IF) | (1L << MNEMONIC))) != 0)) {
				{
				setState(73);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RAM:
				case CONST:
				case MNEMONIC:
					{
					setState(70);
					statement();
					}
					break;
				case FOR:
					{
					setState(71);
					statement_loop();
					}
					break;
				case IF:
					{
					setState(72);
					statement_if();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(77);
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
			setState(78);
			match(IMPORT);
			setState(79);
			_la = _input.LA(1);
			if ( !(_la==STRING || _la==VARIABLE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(82);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==AS) {
				{
				setState(80);
				match(AS);
				setState(81);
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
			setState(88);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << RAM) | (1L << CONST) | (1L << IMPORT) | (1L << MNEMONIC))) != 0)) {
				{
				setState(86);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case RAM:
				case CONST:
				case MNEMONIC:
					{
					setState(84);
					statement();
					}
					break;
				case IMPORT:
					{
					setState(85);
					file_import();
					}
					break;
				default:
					throw new NoViableAltException(this);
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
			setState(95);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,6,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(91);
				declaration();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(92);
				assignment();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(93);
				assignment_arr();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(94);
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
			setState(97);
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

	public static class Statement_ifContext extends ParserRuleContext {
		public If_chainContext if_chain() {
			return getRuleContext(If_chainContext.class,0);
		}
		public Statement_ifContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_if; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterStatement_if(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitStatement_if(this);
		}
	}

	public final Statement_ifContext statement_if() throws RecognitionException {
		Statement_ifContext _localctx = new Statement_ifContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_statement_if);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			if_chain();
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

	public static class Cond_blockContext extends ParserRuleContext {
		public TerminalNode OBRACE() { return getToken(CorParser.OBRACE, 0); }
		public TerminalNode CBRACE() { return getToken(CorParser.CBRACE, 0); }
		public List<LabelContext> label() {
			return getRuleContexts(LabelContext.class);
		}
		public LabelContext label(int i) {
			return getRuleContext(LabelContext.class,i);
		}
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public List<LoopContext> loop() {
			return getRuleContexts(LoopContext.class);
		}
		public LoopContext loop(int i) {
			return getRuleContext(LoopContext.class,i);
		}
		public List<If_chainContext> if_chain() {
			return getRuleContexts(If_chainContext.class);
		}
		public If_chainContext if_chain(int i) {
			return getRuleContext(If_chainContext.class,i);
		}
		public List<Loop_keywordContext> loop_keyword() {
			return getRuleContexts(Loop_keywordContext.class);
		}
		public Loop_keywordContext loop_keyword(int i) {
			return getRuleContext(Loop_keywordContext.class,i);
		}
		public Cond_blockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond_block; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterCond_block(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitCond_block(this);
		}
	}

	public final Cond_blockContext cond_block() throws RecognitionException {
		Cond_blockContext _localctx = new Cond_blockContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_cond_block);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(OBRACE);
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FOR) | (1L << CONTINUE) | (1L << BREAKALL) | (1L << BREAK) | (1L << IF) | (1L << MNEMONIC) | (1L << VARIABLE))) != 0)) {
				{
				setState(107);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VARIABLE:
					{
					setState(102);
					label();
					}
					break;
				case MNEMONIC:
					{
					setState(103);
					instruction();
					}
					break;
				case FOR:
					{
					setState(104);
					loop();
					}
					break;
				case IF:
					{
					setState(105);
					if_chain();
					}
					break;
				case CONTINUE:
				case BREAKALL:
				case BREAK:
					{
					setState(106);
					loop_keyword();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(111);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(112);
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
		public Cond_blockContext cond_block() {
			return getRuleContext(Cond_blockContext.class,0);
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
		enterRule(_localctx, 16, RULE_loop);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(114);
			match(FOR);
			setState(115);
			instruction();
			setState(116);
			match(SEMI);
			setState(117);
			instruction();
			setState(118);
			match(SEMI);
			setState(119);
			instruction();
			setState(120);
			match(SEMI);
			setState(121);
			cond_block();
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
		enterRule(_localctx, 18, RULE_loop_keyword);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(123);
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

	public static class ConditionalContext extends ParserRuleContext {
		public List<InstructionContext> instruction() {
			return getRuleContexts(InstructionContext.class);
		}
		public InstructionContext instruction(int i) {
			return getRuleContext(InstructionContext.class,i);
		}
		public TerminalNode IS() { return getToken(CorParser.IS, 0); }
		public TerminalNode CONDITION() { return getToken(CorParser.CONDITION, 0); }
		public List<TerminalNode> SEMI() { return getTokens(CorParser.SEMI); }
		public TerminalNode SEMI(int i) {
			return getToken(CorParser.SEMI, i);
		}
		public ConditionalContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_conditional; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterConditional(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitConditional(this);
		}
	}

	public final ConditionalContext conditional() throws RecognitionException {
		ConditionalContext _localctx = new ConditionalContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_conditional);
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(125);
					instruction();
					setState(126);
					match(SEMI);
					}
					} 
				}
				setState(132);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,9,_ctx);
			}
			setState(133);
			instruction();
			setState(134);
			match(IS);
			setState(135);
			match(CONDITION);
			setState(136);
			match(SEMI);
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

	public static class If_statContext extends ParserRuleContext {
		public TerminalNode IF() { return getToken(CorParser.IF, 0); }
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public Cond_blockContext cond_block() {
			return getRuleContext(Cond_blockContext.class,0);
		}
		public If_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterIf_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitIf_stat(this);
		}
	}

	public final If_statContext if_stat() throws RecognitionException {
		If_statContext _localctx = new If_statContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_if_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(138);
			match(IF);
			setState(139);
			conditional();
			setState(140);
			cond_block();
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

	public static class Elif_statContext extends ParserRuleContext {
		public TerminalNode ELIF() { return getToken(CorParser.ELIF, 0); }
		public ConditionalContext conditional() {
			return getRuleContext(ConditionalContext.class,0);
		}
		public Cond_blockContext cond_block() {
			return getRuleContext(Cond_blockContext.class,0);
		}
		public Elif_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_elif_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterElif_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitElif_stat(this);
		}
	}

	public final Elif_statContext elif_stat() throws RecognitionException {
		Elif_statContext _localctx = new Elif_statContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_elif_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(142);
			match(ELIF);
			setState(143);
			conditional();
			setState(144);
			cond_block();
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

	public static class Else_statContext extends ParserRuleContext {
		public TerminalNode ELSE() { return getToken(CorParser.ELSE, 0); }
		public Cond_blockContext cond_block() {
			return getRuleContext(Cond_blockContext.class,0);
		}
		public Else_statContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_else_stat; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterElse_stat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitElse_stat(this);
		}
	}

	public final Else_statContext else_stat() throws RecognitionException {
		Else_statContext _localctx = new Else_statContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_else_stat);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(146);
			match(ELSE);
			setState(147);
			cond_block();
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

	public static class If_chainContext extends ParserRuleContext {
		public If_statContext if_stat() {
			return getRuleContext(If_statContext.class,0);
		}
		public List<Elif_statContext> elif_stat() {
			return getRuleContexts(Elif_statContext.class);
		}
		public Elif_statContext elif_stat(int i) {
			return getRuleContext(Elif_statContext.class,i);
		}
		public List<Else_statContext> else_stat() {
			return getRuleContexts(Else_statContext.class);
		}
		public Else_statContext else_stat(int i) {
			return getRuleContext(Else_statContext.class,i);
		}
		public If_chainContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_if_chain; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).enterIf_chain(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof CorListener ) ((CorListener)listener).exitIf_chain(this);
		}
	}

	public final If_chainContext if_chain() throws RecognitionException {
		If_chainContext _localctx = new If_chainContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_if_chain);
		int _la;
		try {
			setState(165);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,12,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(149);
				if_stat();
				setState(151); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(150);
					elif_stat();
					}
					}
					setState(153); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==ELIF );
				setState(158);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==ELSE) {
					{
					{
					setState(155);
					else_stat();
					}
					}
					setState(160);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(161);
				if_stat();
				setState(162);
				else_stat();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(164);
				if_stat();
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
		enterRule(_localctx, 30, RULE_assignment);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(167);
			match(CONST);
			setState(168);
			match(VARIABLE);
			setState(169);
			match(T__0);
			setState(170);
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
		enterRule(_localctx, 32, RULE_assignment_arr);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(172);
			match(CONST);
			setState(173);
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
		enterRule(_localctx, 34, RULE_declaration);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(RAM);
			setState(176);
			match(VARIABLE);
			setState(180);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBRACKET) {
				{
				{
				setState(177);
				array_init();
				}
				}
				setState(182);
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
		enterRule(_localctx, 36, RULE_expression);
		try {
			setState(186);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,14,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				exp_number();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(184);
				exp_var();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(185);
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
		public List<TerminalNode> CPAR() { return getTokens(CorParser.CPAR); }
		public TerminalNode CPAR(int i) {
			return getToken(CorParser.CPAR, i);
		}
		public List<TerminalNode> OPERATOR() { return getTokens(CorParser.OPERATOR); }
		public TerminalNode OPERATOR(int i) {
			return getToken(CorParser.OPERATOR, i);
		}
		public List<TerminalNode> OPAR() { return getTokens(CorParser.OPAR); }
		public TerminalNode OPAR(int i) {
			return getToken(CorParser.OPAR, i);
		}
		public List<TerminalNode> NUMBER() { return getTokens(CorParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(CorParser.NUMBER, i);
		}
		public List<TerminalNode> VARIABLE() { return getTokens(CorParser.VARIABLE); }
		public TerminalNode VARIABLE(int i) {
			return getToken(CorParser.VARIABLE, i);
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
		enterRule(_localctx, 38, RULE_math);
		int _la;
		try {
			int _alt;
			setState(273);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPERATOR) {
					{
					{
					setState(188);
					match(OPERATOR);
					}
					}
					setState(193);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(197);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(194);
						match(OPAR);
						}
						} 
					}
					setState(199);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				setState(233);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPAR) | (1L << VARIABLE) | (1L << NUMBER))) != 0)) {
					{
					{
					setState(214); 
					_errHandler.sync(this);
					_alt = 1;
					do {
						switch (_alt) {
						case 1:
							{
							{
							setState(203);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==OPAR) {
								{
								{
								setState(200);
								match(OPAR);
								}
								}
								setState(205);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(206);
							_la = _input.LA(1);
							if ( !(_la==VARIABLE || _la==NUMBER) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							setState(210);
							_errHandler.sync(this);
							_la = _input.LA(1);
							while (_la==CPAR) {
								{
								{
								setState(207);
								match(CPAR);
								}
								}
								setState(212);
								_errHandler.sync(this);
								_la = _input.LA(1);
							}
							setState(213);
							match(OPERATOR);
							}
							}
							break;
						default:
							throw new NoViableAltException(this);
						}
						setState(216); 
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
					} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
					setState(221);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==OPAR) {
						{
						{
						setState(218);
						match(OPAR);
						}
						}
						setState(223);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(224);
					_la = _input.LA(1);
					if ( !(_la==VARIABLE || _la==NUMBER) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(228);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(225);
							match(CPAR);
							}
							} 
						}
						setState(230);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
					}
					}
					}
					setState(235);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(236);
				match(CPAR);
				setState(241);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPERATOR) {
					{
					{
					setState(237);
					match(OPERATOR);
					setState(238);
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
					setState(243);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(247);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPAR) {
					{
					{
					setState(244);
					match(OPAR);
					}
					}
					setState(249);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(250);
				match(OPERATOR);
				setState(254);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==OPAR) {
					{
					{
					setState(251);
					match(OPAR);
					}
					}
					setState(256);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(260);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==VARIABLE || _la==NUMBER) {
					{
					{
					setState(257);
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
					setState(262);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(263);
				match(CPAR);
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				{
				setState(266); 
				_errHandler.sync(this);
				_alt = 1;
				do {
					switch (_alt) {
					case 1:
						{
						{
						setState(264);
						_la = _input.LA(1);
						if ( !(_la==VARIABLE || _la==NUMBER) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(265);
						match(OPERATOR);
						}
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(268); 
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,27,_ctx);
				} while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER );
				setState(270);
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
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(271);
				match(OPERATOR);
				setState(272);
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
		enterRule(_localctx, 40, RULE_exp_number);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(275);
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
		enterRule(_localctx, 42, RULE_array);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(VARIABLE);
			setState(279); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(278);
				array_init();
				}
				}
				setState(281); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OBRACKET );
			setState(283);
			match(T__0);
			setState(286);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OBRACE:
				{
				setState(284);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(285);
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
		enterRule(_localctx, 44, RULE_exp_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(288);
			match(VARIABLE);
			setState(292);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==OBRACKET) {
				{
				{
				setState(289);
				array_init();
				}
				}
				setState(294);
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
		enterRule(_localctx, 46, RULE_array_init);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(295);
			match(OBRACKET);
			setState(297);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OPAR) | (1L << CPAR) | (1L << VARIABLE) | (1L << OPERATOR) | (1L << NUMBER))) != 0)) {
				{
				setState(296);
				expression();
				}
			}

			setState(299);
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
		enterRule(_localctx, 48, RULE_string);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(301);
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
		enterRule(_localctx, 50, RULE_arr_data);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(OBRACE);
			setState(313);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(307);
					_errHandler.sync(this);
					switch (_input.LA(1)) {
					case OPAR:
					case CPAR:
					case VARIABLE:
					case OPERATOR:
					case NUMBER:
						{
						setState(304);
						expression();
						}
						break;
					case OBRACE:
						{
						setState(305);
						arr_data();
						}
						break;
					case STRING:
						{
						setState(306);
						string();
						}
						break;
					default:
						throw new NoViableAltException(this);
					}
					setState(309);
					match(T__1);
					}
					} 
				}
				setState(315);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,34,_ctx);
			}
			setState(319);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case OPAR:
			case CPAR:
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				{
				setState(316);
				expression();
				}
				break;
			case OBRACE:
				{
				setState(317);
				arr_data();
				}
				break;
			case STRING:
				{
				setState(318);
				string();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(322);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(321);
				match(T__1);
				}
			}

			setState(324);
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
		enterRule(_localctx, 52, RULE_instruction);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(326);
			match(MNEMONIC);
			setState(335);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,38,_ctx) ) {
			case 1:
				{
				setState(327);
				argument();
				setState(332);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__1) {
					{
					{
					setState(328);
					match(T__1);
					setState(329);
					argument();
					}
					}
					setState(334);
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
		public TerminalNode CONDITION() { return getToken(CorParser.CONDITION, 0); }
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
		enterRule(_localctx, 54, RULE_argument);
		try {
			setState(343);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case REGISTER:
				enterOuterAlt(_localctx, 1);
				{
				setState(337);
				register();
				}
				break;
			case OPAR:
			case CPAR:
			case VARIABLE:
			case OPERATOR:
			case NUMBER:
				enterOuterAlt(_localctx, 2);
				{
				setState(338);
				expression();
				}
				break;
			case RAM:
				enterOuterAlt(_localctx, 3);
				{
				setState(339);
				match(RAM);
				}
				break;
			case CONST:
				enterOuterAlt(_localctx, 4);
				{
				setState(340);
				match(CONST);
				}
				break;
			case GPU:
				enterOuterAlt(_localctx, 5);
				{
				setState(341);
				match(GPU);
				}
				break;
			case CONDITION:
				enterOuterAlt(_localctx, 6);
				{
				setState(342);
				match(CONDITION);
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
		enterRule(_localctx, 56, RULE_register);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(345);
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
		enterRule(_localctx, 58, RULE_label);
		try {
			setState(354);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,40,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				{
				setState(347);
				match(VARIABLE);
				setState(348);
				match(COLON);
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(349);
				match(VARIABLE);
				setState(350);
				match(OPAR);
				setState(351);
				match(VARIABLE);
				setState(352);
				match(CPAR);
				setState(353);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3$\u0167\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\3\2\3\2\7"+
		"\2A\n\2\f\2\16\2D\13\2\3\2\3\2\3\3\3\3\3\3\3\3\7\3L\n\3\f\3\16\3O\13\3"+
		"\3\4\3\4\3\4\3\4\5\4U\n\4\3\5\3\5\7\5Y\n\5\f\5\16\5\\\13\5\3\6\3\6\3\6"+
		"\3\6\5\6b\n\6\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3\t\7\tn\n\t\f\t\16"+
		"\tq\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f"+
		"\3\f\7\f\u0083\n\f\f\f\16\f\u0086\13\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\6\20\u009a\n\20\r\20"+
		"\16\20\u009b\3\20\7\20\u009f\n\20\f\20\16\20\u00a2\13\20\3\20\3\20\3\20"+
		"\3\20\5\20\u00a8\n\20\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\7\23\u00b5\n\23\f\23\16\23\u00b8\13\23\3\24\3\24\3\24\5\24\u00bd"+
		"\n\24\3\25\7\25\u00c0\n\25\f\25\16\25\u00c3\13\25\3\25\7\25\u00c6\n\25"+
		"\f\25\16\25\u00c9\13\25\3\25\7\25\u00cc\n\25\f\25\16\25\u00cf\13\25\3"+
		"\25\3\25\7\25\u00d3\n\25\f\25\16\25\u00d6\13\25\3\25\6\25\u00d9\n\25\r"+
		"\25\16\25\u00da\3\25\7\25\u00de\n\25\f\25\16\25\u00e1\13\25\3\25\3\25"+
		"\7\25\u00e5\n\25\f\25\16\25\u00e8\13\25\7\25\u00ea\n\25\f\25\16\25\u00ed"+
		"\13\25\3\25\3\25\3\25\7\25\u00f2\n\25\f\25\16\25\u00f5\13\25\3\25\7\25"+
		"\u00f8\n\25\f\25\16\25\u00fb\13\25\3\25\3\25\7\25\u00ff\n\25\f\25\16\25"+
		"\u0102\13\25\3\25\7\25\u0105\n\25\f\25\16\25\u0108\13\25\3\25\3\25\3\25"+
		"\6\25\u010d\n\25\r\25\16\25\u010e\3\25\3\25\3\25\5\25\u0114\n\25\3\26"+
		"\3\26\3\27\3\27\6\27\u011a\n\27\r\27\16\27\u011b\3\27\3\27\3\27\5\27\u0121"+
		"\n\27\3\30\3\30\7\30\u0125\n\30\f\30\16\30\u0128\13\30\3\31\3\31\5\31"+
		"\u012c\n\31\3\31\3\31\3\32\3\32\3\33\3\33\3\33\3\33\5\33\u0136\n\33\3"+
		"\33\3\33\7\33\u013a\n\33\f\33\16\33\u013d\13\33\3\33\3\33\3\33\5\33\u0142"+
		"\n\33\3\33\5\33\u0145\n\33\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u014d\n"+
		"\34\f\34\16\34\u0150\13\34\5\34\u0152\n\34\3\35\3\35\3\35\3\35\3\35\3"+
		"\35\5\35\u015a\n\35\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\5\37"+
		"\u0165\n\37\3\37\2\2 \2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,."+
		"\60\62\64\668:<\2\5\4\2\5\5  \3\2\16\20\4\2  $$\2\u0181\2>\3\2\2\2\4G"+
		"\3\2\2\2\6P\3\2\2\2\bZ\3\2\2\2\na\3\2\2\2\fc\3\2\2\2\16e\3\2\2\2\20g\3"+
		"\2\2\2\22t\3\2\2\2\24}\3\2\2\2\26\u0084\3\2\2\2\30\u008c\3\2\2\2\32\u0090"+
		"\3\2\2\2\34\u0094\3\2\2\2\36\u00a7\3\2\2\2 \u00a9\3\2\2\2\"\u00ae\3\2"+
		"\2\2$\u00b1\3\2\2\2&\u00bc\3\2\2\2(\u0113\3\2\2\2*\u0115\3\2\2\2,\u0117"+
		"\3\2\2\2.\u0122\3\2\2\2\60\u0129\3\2\2\2\62\u012f\3\2\2\2\64\u0131\3\2"+
		"\2\2\66\u0148\3\2\2\28\u0159\3\2\2\2:\u015b\3\2\2\2<\u0164\3\2\2\2>B\5"+
		"\b\5\2?A\5\4\3\2@?\3\2\2\2AD\3\2\2\2B@\3\2\2\2BC\3\2\2\2CE\3\2\2\2DB\3"+
		"\2\2\2EF\7\2\2\3F\3\3\2\2\2GM\5<\37\2HL\5\n\6\2IL\5\f\7\2JL\5\16\b\2K"+
		"H\3\2\2\2KI\3\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N\5\3\2\2\2"+
		"OM\3\2\2\2PQ\7\13\2\2QT\t\2\2\2RS\7\f\2\2SU\7 \2\2TR\3\2\2\2TU\3\2\2\2"+
		"U\7\3\2\2\2VY\5\n\6\2WY\5\6\4\2XV\3\2\2\2XW\3\2\2\2Y\\\3\2\2\2ZX\3\2\2"+
		"\2Z[\3\2\2\2[\t\3\2\2\2\\Z\3\2\2\2]b\5$\23\2^b\5 \21\2_b\5\"\22\2`b\5"+
		"\66\34\2a]\3\2\2\2a^\3\2\2\2a_\3\2\2\2a`\3\2\2\2b\13\3\2\2\2cd\5\22\n"+
		"\2d\r\3\2\2\2ef\5\36\20\2f\17\3\2\2\2go\7\34\2\2hn\5<\37\2in\5\66\34\2"+
		"jn\5\22\n\2kn\5\36\20\2ln\5\24\13\2mh\3\2\2\2mi\3\2\2\2mj\3\2\2\2mk\3"+
		"\2\2\2ml\3\2\2\2nq\3\2\2\2om\3\2\2\2op\3\2\2\2pr\3\2\2\2qo\3\2\2\2rs\7"+
		"\35\2\2s\21\3\2\2\2tu\7\r\2\2uv\5\66\34\2vw\7\26\2\2wx\5\66\34\2xy\7\26"+
		"\2\2yz\5\66\34\2z{\7\26\2\2{|\5\20\t\2|\23\3\2\2\2}~\t\3\2\2~\25\3\2\2"+
		"\2\177\u0080\5\66\34\2\u0080\u0081\7\26\2\2\u0081\u0083\3\2\2\2\u0082"+
		"\177\3\2\2\2\u0083\u0086\3\2\2\2\u0084\u0082\3\2\2\2\u0084\u0085\3\2\2"+
		"\2\u0085\u0087\3\2\2\2\u0086\u0084\3\2\2\2\u0087\u0088\5\66\34\2\u0088"+
		"\u0089\7\24\2\2\u0089\u008a\7\25\2\2\u008a\u008b\7\26\2\2\u008b\27\3\2"+
		"\2\2\u008c\u008d\7\21\2\2\u008d\u008e\5\26\f\2\u008e\u008f\5\20\t\2\u008f"+
		"\31\3\2\2\2\u0090\u0091\7\22\2\2\u0091\u0092\5\26\f\2\u0092\u0093\5\20"+
		"\t\2\u0093\33\3\2\2\2\u0094\u0095\7\23\2\2\u0095\u0096\5\20\t\2\u0096"+
		"\35\3\2\2\2\u0097\u0099\5\30\r\2\u0098\u009a\5\32\16\2\u0099\u0098\3\2"+
		"\2\2\u009a\u009b\3\2\2\2\u009b\u0099\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u00a0\3\2\2\2\u009d\u009f\5\34\17\2\u009e\u009d\3\2\2\2\u009f\u00a2\3"+
		"\2\2\2\u00a0\u009e\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a1\u00a8\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a4\5\30\r\2\u00a4\u00a5\5\34\17\2\u00a5\u00a8"+
		"\3\2\2\2\u00a6\u00a8\5\30\r\2\u00a7\u0097\3\2\2\2\u00a7\u00a3\3\2\2\2"+
		"\u00a7\u00a6\3\2\2\2\u00a8\37\3\2\2\2\u00a9\u00aa\7\t\2\2\u00aa\u00ab"+
		"\7 \2\2\u00ab\u00ac\7\3\2\2\u00ac\u00ad\5&\24\2\u00ad!\3\2\2\2\u00ae\u00af"+
		"\7\t\2\2\u00af\u00b0\5,\27\2\u00b0#\3\2\2\2\u00b1\u00b2\7\b\2\2\u00b2"+
		"\u00b6\7 \2\2\u00b3\u00b5\5\60\31\2\u00b4\u00b3\3\2\2\2\u00b5\u00b8\3"+
		"\2\2\2\u00b6\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7%\3\2\2\2\u00b8\u00b6"+
		"\3\2\2\2\u00b9\u00bd\5*\26\2\u00ba\u00bd\5.\30\2\u00bb\u00bd\5(\25\2\u00bc"+
		"\u00b9\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bb\3\2\2\2\u00bd\'\3\2\2\2"+
		"\u00be\u00c0\7#\2\2\u00bf\u00be\3\2\2\2\u00c0\u00c3\3\2\2\2\u00c1\u00bf"+
		"\3\2\2\2\u00c1\u00c2\3\2\2\2\u00c2\u00c7\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c4"+
		"\u00c6\7\30\2\2\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c5\3"+
		"\2\2\2\u00c7\u00c8\3\2\2\2\u00c8\u00eb\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca"+
		"\u00cc\7\30\2\2\u00cb\u00ca\3\2\2\2\u00cc\u00cf\3\2\2\2\u00cd\u00cb\3"+
		"\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00d0\3\2\2\2\u00cf\u00cd\3\2\2\2\u00d0"+
		"\u00d4\t\4\2\2\u00d1\u00d3\7\31\2\2\u00d2\u00d1\3\2\2\2\u00d3\u00d6\3"+
		"\2\2\2\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5\u00d7\3\2\2\2\u00d6"+
		"\u00d4\3\2\2\2\u00d7\u00d9\7#\2\2\u00d8\u00cd\3\2\2\2\u00d9\u00da\3\2"+
		"\2\2\u00da\u00d8\3\2\2\2\u00da\u00db\3\2\2\2\u00db\u00df\3\2\2\2\u00dc"+
		"\u00de\7\30\2\2\u00dd\u00dc\3\2\2\2\u00de\u00e1\3\2\2\2\u00df\u00dd\3"+
		"\2\2\2\u00df\u00e0\3\2\2\2\u00e0\u00e2\3\2\2\2\u00e1\u00df\3\2\2\2\u00e2"+
		"\u00e6\t\4\2\2\u00e3\u00e5\7\31\2\2\u00e4\u00e3\3\2\2\2\u00e5\u00e8\3"+
		"\2\2\2\u00e6\u00e4\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00ea\3\2\2\2\u00e8"+
		"\u00e6\3\2\2\2\u00e9\u00d8\3\2\2\2\u00ea\u00ed\3\2\2\2\u00eb\u00e9\3\2"+
		"\2\2\u00eb\u00ec\3\2\2\2\u00ec\u00ee\3\2\2\2\u00ed\u00eb\3\2\2\2\u00ee"+
		"\u00f3\7\31\2\2\u00ef\u00f0\7#\2\2\u00f0\u00f2\t\4\2\2\u00f1\u00ef\3\2"+
		"\2\2\u00f2\u00f5\3\2\2\2\u00f3\u00f1\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u0114\3\2\2\2\u00f5\u00f3\3\2\2\2\u00f6\u00f8\7\30\2\2\u00f7\u00f6\3"+
		"\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa"+
		"\u00fc\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u0100\7#\2\2\u00fd\u00ff\7\30"+
		"\2\2\u00fe\u00fd\3\2\2\2\u00ff\u0102\3\2\2\2\u0100\u00fe\3\2\2\2\u0100"+
		"\u0101\3\2\2\2\u0101\u0106\3\2\2\2\u0102\u0100\3\2\2\2\u0103\u0105\t\4"+
		"\2\2\u0104\u0103\3\2\2\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106"+
		"\u0107\3\2\2\2\u0107\u0109\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u0114\7\31"+
		"\2\2\u010a\u010b\t\4\2\2\u010b\u010d\7#\2\2\u010c\u010a\3\2\2\2\u010d"+
		"\u010e\3\2\2\2\u010e\u010c\3\2\2\2\u010e\u010f\3\2\2\2\u010f\u0110\3\2"+
		"\2\2\u0110\u0114\t\4\2\2\u0111\u0112\7#\2\2\u0112\u0114\t\4\2\2\u0113"+
		"\u00c1\3\2\2\2\u0113\u00f9\3\2\2\2\u0113\u010c\3\2\2\2\u0113\u0111\3\2"+
		"\2\2\u0114)\3\2\2\2\u0115\u0116\7$\2\2\u0116+\3\2\2\2\u0117\u0119\7 \2"+
		"\2\u0118\u011a\5\60\31\2\u0119\u0118\3\2\2\2\u011a\u011b\3\2\2\2\u011b"+
		"\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2\2\2\u011d\u0120\7\3"+
		"\2\2\u011e\u0121\5\64\33\2\u011f\u0121\5\62\32\2\u0120\u011e\3\2\2\2\u0120"+
		"\u011f\3\2\2\2\u0121-\3\2\2\2\u0122\u0126\7 \2\2\u0123\u0125\5\60\31\2"+
		"\u0124\u0123\3\2\2\2\u0125\u0128\3\2\2\2\u0126\u0124\3\2\2\2\u0126\u0127"+
		"\3\2\2\2\u0127/\3\2\2\2\u0128\u0126\3\2\2\2\u0129\u012b\7\32\2\2\u012a"+
		"\u012c\5&\24\2\u012b\u012a\3\2\2\2\u012b\u012c\3\2\2\2\u012c\u012d\3\2"+
		"\2\2\u012d\u012e\7\33\2\2\u012e\61\3\2\2\2\u012f\u0130\7\5\2\2\u0130\63"+
		"\3\2\2\2\u0131\u013b\7\34\2\2\u0132\u0136\5&\24\2\u0133\u0136\5\64\33"+
		"\2\u0134\u0136\5\62\32\2\u0135\u0132\3\2\2\2\u0135\u0133\3\2\2\2\u0135"+
		"\u0134\3\2\2\2\u0136\u0137\3\2\2\2\u0137\u0138\7\4\2\2\u0138\u013a\3\2"+
		"\2\2\u0139\u0135\3\2\2\2\u013a\u013d\3\2\2\2\u013b\u0139\3\2\2\2\u013b"+
		"\u013c\3\2\2\2\u013c\u0141\3\2\2\2\u013d\u013b\3\2\2\2\u013e\u0142\5&"+
		"\24\2\u013f\u0142\5\64\33\2\u0140\u0142\5\62\32\2\u0141\u013e\3\2\2\2"+
		"\u0141\u013f\3\2\2\2\u0141\u0140\3\2\2\2\u0142\u0144\3\2\2\2\u0143\u0145"+
		"\7\4\2\2\u0144\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146"+
		"\u0147\7\35\2\2\u0147\65\3\2\2\2\u0148\u0151\7\37\2\2\u0149\u014e\58\35"+
		"\2\u014a\u014b\7\4\2\2\u014b\u014d\58\35\2\u014c\u014a\3\2\2\2\u014d\u0150"+
		"\3\2\2\2\u014e\u014c\3\2\2\2\u014e\u014f\3\2\2\2\u014f\u0152\3\2\2\2\u0150"+
		"\u014e\3\2\2\2\u0151\u0149\3\2\2\2\u0151\u0152\3\2\2\2\u0152\67\3\2\2"+
		"\2\u0153\u015a\5:\36\2\u0154\u015a\5&\24\2\u0155\u015a\7\b\2\2\u0156\u015a"+
		"\7\t\2\2\u0157\u015a\7\n\2\2\u0158\u015a\7\25\2\2\u0159\u0153\3\2\2\2"+
		"\u0159\u0154\3\2\2\2\u0159\u0155\3\2\2\2\u0159\u0156\3\2\2\2\u0159\u0157"+
		"\3\2\2\2\u0159\u0158\3\2\2\2\u015a9\3\2\2\2\u015b\u015c\7\36\2\2\u015c"+
		";\3\2\2\2\u015d\u015e\7 \2\2\u015e\u0165\7\27\2\2\u015f\u0160\7 \2\2\u0160"+
		"\u0161\7\30\2\2\u0161\u0162\7 \2\2\u0162\u0163\7\31\2\2\u0163\u0165\7"+
		"\27\2\2\u0164\u015d\3\2\2\2\u0164\u015f\3\2\2\2\u0165=\3\2\2\2+BKMTXZ"+
		"amo\u0084\u009b\u00a0\u00a7\u00b6\u00bc\u00c1\u00c7\u00cd\u00d4\u00da"+
		"\u00df\u00e6\u00eb\u00f3\u00f9\u0100\u0106\u010e\u0113\u011b\u0120\u0126"+
		"\u012b\u0135\u013b\u0141\u0144\u014e\u0151\u0159\u0164";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}