// Generated from Cor.g4 by ANTLR 4.8
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class CorLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.8", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, RAM=4, CONST=5, GPU=6, IMPORT=7, AS=8, STRING=9, 
		COMMENT=10, COMMENT_BLOCK=11, OBRACKET=12, CBRACKET=13, OBRACE=14, CBRACE=15, 
		REGISTER=16, MNEMONIC=17, VARIABLE=18, WHITESPACE=19, OPERATOR=20, NUMBER=21;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "RAM", "ROM", "PRE", "CONST", "GPU", "IMPORT", 
			"AS", "SINGLE_STRING", "DOUBLE_STRING", "STRING", "COMMENT", "COMMENT_BLOCK", 
			"OBRACKET", "CBRACKET", "OBRACE", "CBRACE", "REGISTER", "MNEMONIC", "VARIABLE", 
			"WHITESPACE", "OPERATOR", "DEC", "HEX", "BIN", "FLT", "NUMBER"
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


	public CorLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Cor.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\27\u013b\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\3\3"+
		"\3\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\5\b"+
		"R\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\f\3"+
		"\f\3\f\3\f\7\ff\n\f\f\f\16\fi\13\f\3\f\3\f\3\r\3\r\3\r\3\r\7\rq\n\r\f"+
		"\r\16\rt\13\r\3\r\3\r\3\16\3\16\5\16z\n\16\3\17\3\17\3\17\3\17\7\17\u0080"+
		"\n\17\f\17\16\17\u0083\13\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\7"+
		"\20\u008d\n\20\f\20\16\20\u0090\13\20\3\20\3\20\3\20\3\20\3\20\3\21\3"+
		"\21\3\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\5\26\u00f1\n\26\3\27\5\27\u00f4\n\27\3\27\3\27"+
		"\7\27\u00f8\n\27\f\27\16\27\u00fb\13\27\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\32\3\32\7\32\u0105\n\32\f\32\16\32\u0108\13\32\3\32\5\32\u010b\n\32"+
		"\3\33\3\33\3\33\3\33\3\33\7\33\u0112\n\33\f\33\16\33\u0115\13\33\3\34"+
		"\3\34\3\34\3\34\3\34\7\34\u011c\n\34\f\34\16\34\u011f\13\34\3\35\3\35"+
		"\7\35\u0123\n\35\f\35\16\35\u0126\13\35\3\35\5\35\u0129\n\35\3\35\3\35"+
		"\3\35\7\35\u012e\n\35\f\35\16\35\u0131\13\35\3\35\5\35\u0134\n\35\3\36"+
		"\3\36\3\36\3\36\5\36\u013a\n\36\3\u008e\2\37\3\3\5\4\7\5\t\6\13\2\r\2"+
		"\17\7\21\b\23\t\25\n\27\2\31\2\33\13\35\f\37\r!\16#\17%\20\'\21)\22+\23"+
		"-\24/\25\61\26\63\2\65\2\67\29\2;\27\3\2\17\5\2\f\f\17\17$$\4\2\f\f\17"+
		"\17\3\2cj\5\2C\\aac|\7\2\60\60\62;C\\aac|\5\2\13\f\17\17\"\"\n\2((*-/"+
		"/\61\61>@``~~\u0080\u0080\3\2\63;\4\2\62;aa\5\2\62;CHch\6\2\62;CHaach"+
		"\3\2\62\63\4\2\62\63aa\2\u0161\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t"+
		"\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\33\3\2"+
		"\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3"+
		"\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2;\3\2"+
		"\2\2\3=\3\2\2\2\5?\3\2\2\2\7A\3\2\2\2\tC\3\2\2\2\13G\3\2\2\2\rK\3\2\2"+
		"\2\17Q\3\2\2\2\21S\3\2\2\2\23W\3\2\2\2\25^\3\2\2\2\27a\3\2\2\2\31l\3\2"+
		"\2\2\33y\3\2\2\2\35{\3\2\2\2\37\u0088\3\2\2\2!\u0096\3\2\2\2#\u0098\3"+
		"\2\2\2%\u009a\3\2\2\2\'\u009c\3\2\2\2)\u009e\3\2\2\2+\u00f0\3\2\2\2-\u00f3"+
		"\3\2\2\2/\u00fc\3\2\2\2\61\u0100\3\2\2\2\63\u010a\3\2\2\2\65\u010c\3\2"+
		"\2\2\67\u0116\3\2\2\29\u0128\3\2\2\2;\u0139\3\2\2\2=>\7?\2\2>\4\3\2\2"+
		"\2?@\7.\2\2@\6\3\2\2\2AB\7<\2\2B\b\3\2\2\2CD\7t\2\2DE\7c\2\2EF\7o\2\2"+
		"F\n\3\2\2\2GH\7t\2\2HI\7q\2\2IJ\7o\2\2J\f\3\2\2\2KL\7r\2\2LM\7t\2\2MN"+
		"\7g\2\2N\16\3\2\2\2OR\5\13\6\2PR\5\r\7\2QO\3\2\2\2QP\3\2\2\2R\20\3\2\2"+
		"\2ST\7i\2\2TU\7r\2\2UV\7w\2\2V\22\3\2\2\2WX\7k\2\2XY\7o\2\2YZ\7r\2\2Z"+
		"[\7q\2\2[\\\7t\2\2\\]\7v\2\2]\24\3\2\2\2^_\7c\2\2_`\7u\2\2`\26\3\2\2\2"+
		"ag\7)\2\2bf\n\2\2\2cd\7)\2\2df\7)\2\2eb\3\2\2\2ec\3\2\2\2fi\3\2\2\2ge"+
		"\3\2\2\2gh\3\2\2\2hj\3\2\2\2ig\3\2\2\2jk\7)\2\2k\30\3\2\2\2lr\7$\2\2m"+
		"q\n\2\2\2no\7$\2\2oq\7$\2\2pm\3\2\2\2pn\3\2\2\2qt\3\2\2\2rp\3\2\2\2rs"+
		"\3\2\2\2su\3\2\2\2tr\3\2\2\2uv\7$\2\2v\32\3\2\2\2wz\5\27\f\2xz\5\31\r"+
		"\2yw\3\2\2\2yx\3\2\2\2z\34\3\2\2\2{|\7\61\2\2|}\7\61\2\2}\u0081\3\2\2"+
		"\2~\u0080\n\3\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2\2\u0081"+
		"\u0082\3\2\2\2\u0082\u0084\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0085\t\3"+
		"\2\2\u0085\u0086\3\2\2\2\u0086\u0087\b\17\2\2\u0087\36\3\2\2\2\u0088\u0089"+
		"\7\61\2\2\u0089\u008a\7,\2\2\u008a\u008e\3\2\2\2\u008b\u008d\13\2\2\2"+
		"\u008c\u008b\3\2\2\2\u008d\u0090\3\2\2\2\u008e\u008f\3\2\2\2\u008e\u008c"+
		"\3\2\2\2\u008f\u0091\3\2\2\2\u0090\u008e\3\2\2\2\u0091\u0092\7,\2\2\u0092"+
		"\u0093\7\61\2\2\u0093\u0094\3\2\2\2\u0094\u0095\b\20\2\2\u0095 \3\2\2"+
		"\2\u0096\u0097\7]\2\2\u0097\"\3\2\2\2\u0098\u0099\7_\2\2\u0099$\3\2\2"+
		"\2\u009a\u009b\7}\2\2\u009b&\3\2\2\2\u009c\u009d\7\177\2\2\u009d(\3\2"+
		"\2\2\u009e\u009f\t\4\2\2\u009f*\3\2\2\2\u00a0\u00a1\7p\2\2\u00a1\u00a2"+
		"\7q\2\2\u00a2\u00f1\7r\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5\7f\2\2\u00a5"+
		"\u00f1\7t\2\2\u00a6\u00a7\7u\2\2\u00a7\u00a8\7v\2\2\u00a8\u00f1\7t\2\2"+
		"\u00a9\u00aa\7n\2\2\u00aa\u00ab\7r\2\2\u00ab\u00f1\7v\2\2\u00ac\u00ad"+
		"\7u\2\2\u00ad\u00ae\7r\2\2\u00ae\u00f1\7v\2\2\u00af\u00b0\7e\2\2\u00b0"+
		"\u00b1\7o\2\2\u00b1\u00f1\7r\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7f\2\2"+
		"\u00b4\u00f1\7f\2\2\u00b5\u00b6\7u\2\2\u00b6\u00b7\7w\2\2\u00b7\u00f1"+
		"\7d\2\2\u00b8\u00b9\7o\2\2\u00b9\u00ba\7w\2\2\u00ba\u00f1\7n\2\2\u00bb"+
		"\u00bc\7f\2\2\u00bc\u00bd\7k\2\2\u00bd\u00f1\7x\2\2\u00be\u00bf\7o\2\2"+
		"\u00bf\u00c0\7q\2\2\u00c0\u00f1\7f\2\2\u00c1\u00c2\7c\2\2\u00c2\u00c3"+
		"\7p\2\2\u00c3\u00f1\7f\2\2\u00c4\u00c5\7q\2\2\u00c5\u00f1\7t\2\2\u00c6"+
		"\u00c7\7z\2\2\u00c7\u00c8\7q\2\2\u00c8\u00f1\7t\2\2\u00c9\u00ca\7p\2\2"+
		"\u00ca\u00cb\7q\2\2\u00cb\u00f1\7v\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce"+
		"\7u\2\2\u00ce\u00f1\7n\2\2\u00cf\u00d0\7n\2\2\u00d0\u00d1\7u\2\2\u00d1"+
		"\u00f1\7t\2\2\u00d2\u00d3\7r\2\2\u00d3\u00d4\7u\2\2\u00d4\u00f1\7j\2\2"+
		"\u00d5\u00d6\7r\2\2\u00d6\u00d7\7q\2\2\u00d7\u00f1\7r\2\2\u00d8\u00d9"+
		"\7r\2\2\u00d9\u00da\7g\2\2\u00da\u00f1\7m\2\2\u00db\u00dc\7l\2\2\u00dc"+
		"\u00dd\7o\2\2\u00dd\u00f1\7r\2\2\u00de\u00df\7l\2\2\u00df\u00e0\7u\2\2"+
		"\u00e0\u00f1\7t\2\2\u00e1\u00e2\7t\2\2\u00e2\u00e3\7v\2\2\u00e3\u00f1"+
		"\7u\2\2\u00e4\u00e5\7l\2\2\u00e5\u00e6\7q\2\2\u00e6\u00f1\7e\2\2\u00e7"+
		"\u00e8\7l\2\2\u00e8\u00e9\7u\2\2\u00e9\u00f1\7e\2\2\u00ea\u00eb\7t\2\2"+
		"\u00eb\u00ec\7u\2\2\u00ec\u00f1\7e\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef"+
		"\7h\2\2\u00ef\u00f1\7o\2\2\u00f0\u00a0\3\2\2\2\u00f0\u00a3\3\2\2\2\u00f0"+
		"\u00a6\3\2\2\2\u00f0\u00a9\3\2\2\2\u00f0\u00ac\3\2\2\2\u00f0\u00af\3\2"+
		"\2\2\u00f0\u00b2\3\2\2\2\u00f0\u00b5\3\2\2\2\u00f0\u00b8\3\2\2\2\u00f0"+
		"\u00bb\3\2\2\2\u00f0\u00be\3\2\2\2\u00f0\u00c1\3\2\2\2\u00f0\u00c4\3\2"+
		"\2\2\u00f0\u00c6\3\2\2\2\u00f0\u00c9\3\2\2\2\u00f0\u00cc\3\2\2\2\u00f0"+
		"\u00cf\3\2\2\2\u00f0\u00d2\3\2\2\2\u00f0\u00d5\3\2\2\2\u00f0\u00d8\3\2"+
		"\2\2\u00f0\u00db\3\2\2\2\u00f0\u00de\3\2\2\2\u00f0\u00e1\3\2\2\2\u00f0"+
		"\u00e4\3\2\2\2\u00f0\u00e7\3\2\2\2\u00f0\u00ea\3\2\2\2\u00f0\u00ed\3\2"+
		"\2\2\u00f1,\3\2\2\2\u00f2\u00f4\7&\2\2\u00f3\u00f2\3\2\2\2\u00f3\u00f4"+
		"\3\2\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f9\t\5\2\2\u00f6\u00f8\t\6\2\2\u00f7"+
		"\u00f6\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9\u00f7\3\2\2\2\u00f9\u00fa\3\2"+
		"\2\2\u00fa.\3\2\2\2\u00fb\u00f9\3\2\2\2\u00fc\u00fd\t\7\2\2\u00fd\u00fe"+
		"\3\2\2\2\u00fe\u00ff\b\30\2\2\u00ff\60\3\2\2\2\u0100\u0101\t\b\2\2\u0101"+
		"\62\3\2\2\2\u0102\u0106\t\t\2\2\u0103\u0105\t\n\2\2\u0104\u0103\3\2\2"+
		"\2\u0105\u0108\3\2\2\2\u0106\u0104\3\2\2\2\u0106\u0107\3\2\2\2\u0107\u010b"+
		"\3\2\2\2\u0108\u0106\3\2\2\2\u0109\u010b\7\62\2\2\u010a\u0102\3\2\2\2"+
		"\u010a\u0109\3\2\2\2\u010b\64\3\2\2\2\u010c\u010d\7\62\2\2\u010d\u010e"+
		"\7z\2\2\u010e\u010f\3\2\2\2\u010f\u0113\t\13\2\2\u0110\u0112\t\f\2\2\u0111"+
		"\u0110\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2\2\2\u0113\u0114\3\2"+
		"\2\2\u0114\66\3\2\2\2\u0115\u0113\3\2\2\2\u0116\u0117\7\62\2\2\u0117\u0118"+
		"\7d\2\2\u0118\u0119\3\2\2\2\u0119\u011d\t\r\2\2\u011a\u011c\t\16\2\2\u011b"+
		"\u011a\3\2\2\2\u011c\u011f\3\2\2\2\u011d\u011b\3\2\2\2\u011d\u011e\3\2"+
		"\2\2\u011e8\3\2\2\2\u011f\u011d\3\2\2\2\u0120\u0124\t\t\2\2\u0121\u0123"+
		"\t\n\2\2\u0122\u0121\3\2\2\2\u0123\u0126\3\2\2\2\u0124\u0122\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125\u0129\3\2\2\2\u0126\u0124\3\2\2\2\u0127\u0129\7\62"+
		"\2\2\u0128\u0120\3\2\2\2\u0128\u0127\3\2\2\2\u0129\u012a\3\2\2\2\u012a"+
		"\u0133\7\60\2\2\u012b\u012f\t\t\2\2\u012c\u012e\t\n\2\2\u012d\u012c\3"+
		"\2\2\2\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130"+
		"\u0134\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0134\7\62\2\2\u0133\u012b\3"+
		"\2\2\2\u0133\u0132\3\2\2\2\u0134:\3\2\2\2\u0135\u013a\5\63\32\2\u0136"+
		"\u013a\5\67\34\2\u0137\u013a\5\65\33\2\u0138\u013a\59\35\2\u0139\u0135"+
		"\3\2\2\2\u0139\u0136\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u0138\3\2\2\2\u013a"+
		"<\3\2\2\2\27\2Qegpry\u0081\u008e\u00f0\u00f3\u00f9\u0106\u010a\u0113\u011d"+
		"\u0124\u0128\u012f\u0133\u0139\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}