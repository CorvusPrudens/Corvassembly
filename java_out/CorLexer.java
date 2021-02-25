// Generated from Cor.g4 by ANTLR 4.9.1
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
	static { RuntimeMetaData.checkVersion("4.9.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, STRING=3, COMMENT=4, COMMENT_BLOCK=5, RAM=6, CONST=7, 
		GPU=8, IMPORT=9, AS=10, FOR=11, CONTINUE=12, BREAKALL=13, BREAK=14, IF=15, 
		ELIF=16, ELSE=17, CONDITION=18, SEMI=19, COLON=20, OPAR=21, CPAR=22, OBRACKET=23, 
		CBRACKET=24, OBRACE=25, CBRACE=26, REGISTER=27, MNEMONIC=28, VARIABLE=29, 
		WHITESPACE=30, COMPARATOR=31, OPERATOR=32, NUMBER=33;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "SINGLE_STRING", "DOUBLE_STRING", "STRING", "COMMENT", 
			"COMMENT_BLOCK", "RAM", "ROM", "PRE", "CONST", "GPU", "IMPORT", "AS", 
			"FOR", "CONTINUE", "BREAKALL", "BREAK", "IF", "ELIF", "ELSE", "CONDITION", 
			"SEMI", "COLON", "OPAR", "CPAR", "OBRACKET", "CBRACKET", "OBRACE", "CBRACE", 
			"REGISTER", "MNEMONIC", "VARIABLE", "WHITESPACE", "COMPARATOR", "OPERATOR", 
			"DEC", "HEX", "BIN", "FLT", "NUMBER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "','", null, null, null, "'ram'", null, "'gpu'", "'import'", 
			"'as'", "'for'", "'continue'", "'breakall'", "'break'", "'if'", "'elif'", 
			"'else'", null, "';'", "':'", "'('", "')'", "'['", "']'", "'{'", "'}'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, "STRING", "COMMENT", "COMMENT_BLOCK", "RAM", "CONST", 
			"GPU", "IMPORT", "AS", "FOR", "CONTINUE", "BREAKALL", "BREAK", "IF", 
			"ELIF", "ELSE", "CONDITION", "SEMI", "COLON", "OPAR", "CPAR", "OBRACKET", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2#\u01b6\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\7\4^\n\4\f\4\16\4a\13\4\3\4\3\4\3\5\3\5\3\5\3"+
		"\5\7\5i\n\5\f\5\16\5l\13\5\3\5\3\5\3\6\3\6\5\6r\n\6\3\7\3\7\3\7\3\7\7"+
		"\7x\n\7\f\7\16\7{\13\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0085\n\b\f"+
		"\b\16\b\u0088\13\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\5\f\u009d\n\f\3\r\3\r\3\r\3\r\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25\3\25\3"+
		"\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\5\27\u00f7"+
		"\n\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35\3\35\3\36"+
		"\3\36\3\37\3\37\3 \3 \3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!"+
		"\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\3!\5!\u015e"+
		"\n!\3\"\5\"\u0161\n\"\3\"\3\"\7\"\u0165\n\"\f\"\16\"\u0168\13\"\3#\3#"+
		"\3#\3#\3$\3$\3$\3$\3$\3$\3$\5$\u0175\n$\3%\3%\3%\3%\3%\5%\u017c\n%\3&"+
		"\3&\7&\u0180\n&\f&\16&\u0183\13&\3&\5&\u0186\n&\3\'\3\'\3\'\3\'\3\'\7"+
		"\'\u018d\n\'\f\'\16\'\u0190\13\'\3(\3(\3(\3(\3(\7(\u0197\n(\f(\16(\u019a"+
		"\13(\3)\3)\7)\u019e\n)\f)\16)\u01a1\13)\3)\5)\u01a4\n)\3)\3)\3)\7)\u01a9"+
		"\n)\f)\16)\u01ac\13)\3)\5)\u01af\n)\3*\3*\3*\3*\5*\u01b5\n*\3\u0086\2"+
		"+\3\3\5\4\7\2\t\2\13\5\r\6\17\7\21\b\23\2\25\2\27\t\31\n\33\13\35\f\37"+
		"\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\26\63\27\65\30\67\319\32;\33=\34"+
		"?\35A\36C\37E G!I\"K\2M\2O\2Q\2S#\3\2\20\5\2\f\f\17\17$$\4\2\f\f\17\17"+
		"\3\2cj\5\2C\\aac|\7\2\60\60\62;C\\aac|\5\2\13\f\17\17\"\"\4\2>>@@\t\2"+
		"((,-//\61\61``~~\u0080\u0080\3\2\63;\4\2\62;aa\5\2\62;CHch\6\2\62;CHa"+
		"ach\3\2\62\63\4\2\62\63aa\2\u01e7\2\3\3\2\2\2\2\5\3\2\2\2\2\13\3\2\2\2"+
		"\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33"+
		"\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2"+
		"\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2"+
		"\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2"+
		"\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2\2S"+
		"\3\2\2\2\3U\3\2\2\2\5W\3\2\2\2\7Y\3\2\2\2\td\3\2\2\2\13q\3\2\2\2\rs\3"+
		"\2\2\2\17\u0080\3\2\2\2\21\u008e\3\2\2\2\23\u0092\3\2\2\2\25\u0096\3\2"+
		"\2\2\27\u009c\3\2\2\2\31\u009e\3\2\2\2\33\u00a2\3\2\2\2\35\u00a9\3\2\2"+
		"\2\37\u00ac\3\2\2\2!\u00b0\3\2\2\2#\u00b9\3\2\2\2%\u00c2\3\2\2\2\'\u00c8"+
		"\3\2\2\2)\u00cb\3\2\2\2+\u00d0\3\2\2\2-\u00f6\3\2\2\2/\u00f8\3\2\2\2\61"+
		"\u00fa\3\2\2\2\63\u00fc\3\2\2\2\65\u00fe\3\2\2\2\67\u0100\3\2\2\29\u0102"+
		"\3\2\2\2;\u0104\3\2\2\2=\u0106\3\2\2\2?\u0108\3\2\2\2A\u015d\3\2\2\2C"+
		"\u0160\3\2\2\2E\u0169\3\2\2\2G\u0174\3\2\2\2I\u017b\3\2\2\2K\u0185\3\2"+
		"\2\2M\u0187\3\2\2\2O\u0191\3\2\2\2Q\u01a3\3\2\2\2S\u01b4\3\2\2\2UV\7?"+
		"\2\2V\4\3\2\2\2WX\7.\2\2X\6\3\2\2\2Y_\7)\2\2Z^\n\2\2\2[\\\7)\2\2\\^\7"+
		")\2\2]Z\3\2\2\2][\3\2\2\2^a\3\2\2\2_]\3\2\2\2_`\3\2\2\2`b\3\2\2\2a_\3"+
		"\2\2\2bc\7)\2\2c\b\3\2\2\2dj\7$\2\2ei\n\2\2\2fg\7$\2\2gi\7$\2\2he\3\2"+
		"\2\2hf\3\2\2\2il\3\2\2\2jh\3\2\2\2jk\3\2\2\2km\3\2\2\2lj\3\2\2\2mn\7$"+
		"\2\2n\n\3\2\2\2or\5\7\4\2pr\5\t\5\2qo\3\2\2\2qp\3\2\2\2r\f\3\2\2\2st\7"+
		"\61\2\2tu\7\61\2\2uy\3\2\2\2vx\n\3\2\2wv\3\2\2\2x{\3\2\2\2yw\3\2\2\2y"+
		"z\3\2\2\2z|\3\2\2\2{y\3\2\2\2|}\t\3\2\2}~\3\2\2\2~\177\b\7\2\2\177\16"+
		"\3\2\2\2\u0080\u0081\7\61\2\2\u0081\u0082\7,\2\2\u0082\u0086\3\2\2\2\u0083"+
		"\u0085\13\2\2\2\u0084\u0083\3\2\2\2\u0085\u0088\3\2\2\2\u0086\u0087\3"+
		"\2\2\2\u0086\u0084\3\2\2\2\u0087\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089"+
		"\u008a\7,\2\2\u008a\u008b\7\61\2\2\u008b\u008c\3\2\2\2\u008c\u008d\b\b"+
		"\2\2\u008d\20\3\2\2\2\u008e\u008f\7t\2\2\u008f\u0090\7c\2\2\u0090\u0091"+
		"\7o\2\2\u0091\22\3\2\2\2\u0092\u0093\7t\2\2\u0093\u0094\7q\2\2\u0094\u0095"+
		"\7o\2\2\u0095\24\3\2\2\2\u0096\u0097\7r\2\2\u0097\u0098\7t\2\2\u0098\u0099"+
		"\7g\2\2\u0099\26\3\2\2\2\u009a\u009d\5\23\n\2\u009b\u009d\5\25\13\2\u009c"+
		"\u009a\3\2\2\2\u009c\u009b\3\2\2\2\u009d\30\3\2\2\2\u009e\u009f\7i\2\2"+
		"\u009f\u00a0\7r\2\2\u00a0\u00a1\7w\2\2\u00a1\32\3\2\2\2\u00a2\u00a3\7"+
		"k\2\2\u00a3\u00a4\7o\2\2\u00a4\u00a5\7r\2\2\u00a5\u00a6\7q\2\2\u00a6\u00a7"+
		"\7t\2\2\u00a7\u00a8\7v\2\2\u00a8\34\3\2\2\2\u00a9\u00aa\7c\2\2\u00aa\u00ab"+
		"\7u\2\2\u00ab\36\3\2\2\2\u00ac\u00ad\7h\2\2\u00ad\u00ae\7q\2\2\u00ae\u00af"+
		"\7t\2\2\u00af \3\2\2\2\u00b0\u00b1\7e\2\2\u00b1\u00b2\7q\2\2\u00b2\u00b3"+
		"\7p\2\2\u00b3\u00b4\7v\2\2\u00b4\u00b5\7k\2\2\u00b5\u00b6\7p\2\2\u00b6"+
		"\u00b7\7w\2\2\u00b7\u00b8\7g\2\2\u00b8\"\3\2\2\2\u00b9\u00ba\7d\2\2\u00ba"+
		"\u00bb\7t\2\2\u00bb\u00bc\7g\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7m\2\2"+
		"\u00be\u00bf\7c\2\2\u00bf\u00c0\7n\2\2\u00c0\u00c1\7n\2\2\u00c1$\3\2\2"+
		"\2\u00c2\u00c3\7d\2\2\u00c3\u00c4\7t\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6"+
		"\7c\2\2\u00c6\u00c7\7m\2\2\u00c7&\3\2\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca"+
		"\7h\2\2\u00ca(\3\2\2\2\u00cb\u00cc\7g\2\2\u00cc\u00cd\7n\2\2\u00cd\u00ce"+
		"\7k\2\2\u00ce\u00cf\7h\2\2\u00cf*\3\2\2\2\u00d0\u00d1\7g\2\2\u00d1\u00d2"+
		"\7n\2\2\u00d2\u00d3\7u\2\2\u00d3\u00d4\7g\2\2\u00d4,\3\2\2\2\u00d5\u00d6"+
		"\7|\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7t\2\2\u00d8\u00f7\7q\2\2\u00d9"+
		"\u00da\7e\2\2\u00da\u00db\7c\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7t\2\2"+
		"\u00dd\u00f7\7{\2\2\u00de\u00df\7p\2\2\u00df\u00e0\7g\2\2\u00e0\u00e1"+
		"\7i\2\2\u00e1\u00e2\7c\2\2\u00e2\u00e3\7v\2\2\u00e3\u00e4\7k\2\2\u00e4"+
		"\u00e5\7x\2\2\u00e5\u00f7\7g\2\2\u00e6\u00e7\7g\2\2\u00e7\u00e8\7s\2\2"+
		"\u00e8\u00e9\7w\2\2\u00e9\u00ea\7c\2\2\u00ea\u00f7\7n\2\2\u00eb\u00ec"+
		"\7i\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7g\2\2\u00ee\u00ef\7c\2\2\u00ef"+
		"\u00f0\7v\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f7\7t\2\2\u00f2\u00f3\7n\2\2"+
		"\u00f3\u00f4\7g\2\2\u00f4\u00f5\7u\2\2\u00f5\u00f7\7u\2\2\u00f6\u00d5"+
		"\3\2\2\2\u00f6\u00d9\3\2\2\2\u00f6\u00de\3\2\2\2\u00f6\u00e6\3\2\2\2\u00f6"+
		"\u00eb\3\2\2\2\u00f6\u00f2\3\2\2\2\u00f7.\3\2\2\2\u00f8\u00f9\7=\2\2\u00f9"+
		"\60\3\2\2\2\u00fa\u00fb\7<\2\2\u00fb\62\3\2\2\2\u00fc\u00fd\7*\2\2\u00fd"+
		"\64\3\2\2\2\u00fe\u00ff\7+\2\2\u00ff\66\3\2\2\2\u0100\u0101\7]\2\2\u0101"+
		"8\3\2\2\2\u0102\u0103\7_\2\2\u0103:\3\2\2\2\u0104\u0105\7}\2\2\u0105<"+
		"\3\2\2\2\u0106\u0107\7\177\2\2\u0107>\3\2\2\2\u0108\u0109\t\4\2\2\u0109"+
		"@\3\2\2\2\u010a\u010b\7p\2\2\u010b\u010c\7q\2\2\u010c\u015e\7r\2\2\u010d"+
		"\u010e\7n\2\2\u010e\u010f\7f\2\2\u010f\u015e\7t\2\2\u0110\u0111\7u\2\2"+
		"\u0111\u0112\7v\2\2\u0112\u015e\7t\2\2\u0113\u0114\7n\2\2\u0114\u0115"+
		"\7r\2\2\u0115\u015e\7v\2\2\u0116\u0117\7u\2\2\u0117\u0118\7r\2\2\u0118"+
		"\u015e\7v\2\2\u0119\u011a\7e\2\2\u011a\u011b\7o\2\2\u011b\u015e\7r\2\2"+
		"\u011c\u011d\7c\2\2\u011d\u011e\7f\2\2\u011e\u015e\7f\2\2\u011f\u0120"+
		"\7u\2\2\u0120\u0121\7w\2\2\u0121\u015e\7d\2\2\u0122\u0123\7o\2\2\u0123"+
		"\u0124\7w\2\2\u0124\u015e\7n\2\2\u0125\u0126\7f\2\2\u0126\u0127\7k\2\2"+
		"\u0127\u015e\7x\2\2\u0128\u0129\7o\2\2\u0129\u012a\7q\2\2\u012a\u015e"+
		"\7f\2\2\u012b\u012c\7c\2\2\u012c\u012d\7p\2\2\u012d\u015e\7f\2\2\u012e"+
		"\u012f\7q\2\2\u012f\u015e\7t\2\2\u0130\u0131\7z\2\2\u0131\u0132\7q\2\2"+
		"\u0132\u015e\7t\2\2\u0133\u0134\7p\2\2\u0134\u0135\7q\2\2\u0135\u015e"+
		"\7v\2\2\u0136\u0137\7n\2\2\u0137\u0138\7u\2\2\u0138\u015e\7n\2\2\u0139"+
		"\u013a\7n\2\2\u013a\u013b\7u\2\2\u013b\u015e\7t\2\2\u013c\u013d\7r\2\2"+
		"\u013d\u013e\7u\2\2\u013e\u015e\7j\2\2\u013f\u0140\7r\2\2\u0140\u0141"+
		"\7q\2\2\u0141\u015e\7r\2\2\u0142\u0143\7r\2\2\u0143\u0144\7g\2\2\u0144"+
		"\u015e\7m\2\2\u0145\u0146\7l\2\2\u0146\u0147\7o\2\2\u0147\u015e\7r\2\2"+
		"\u0148\u0149\7l\2\2\u0149\u014a\7u\2\2\u014a\u015e\7t\2\2\u014b\u014c"+
		"\7t\2\2\u014c\u014d\7v\2\2\u014d\u015e\7u\2\2\u014e\u014f\7l\2\2\u014f"+
		"\u0150\7q\2\2\u0150\u015e\7e\2\2\u0151\u0152\7l\2\2\u0152\u0153\7u\2\2"+
		"\u0153\u015e\7e\2\2\u0154\u0155\7t\2\2\u0155\u0156\7u\2\2\u0156\u015e"+
		"\7e\2\2\u0157\u0158\7t\2\2\u0158\u0159\7v\2\2\u0159\u015e\7k\2\2\u015a"+
		"\u015b\7t\2\2\u015b\u015c\7k\2\2\u015c\u015e\7e\2\2\u015d\u010a\3\2\2"+
		"\2\u015d\u010d\3\2\2\2\u015d\u0110\3\2\2\2\u015d\u0113\3\2\2\2\u015d\u0116"+
		"\3\2\2\2\u015d\u0119\3\2\2\2\u015d\u011c\3\2\2\2\u015d\u011f\3\2\2\2\u015d"+
		"\u0122\3\2\2\2\u015d\u0125\3\2\2\2\u015d\u0128\3\2\2\2\u015d\u012b\3\2"+
		"\2\2\u015d\u012e\3\2\2\2\u015d\u0130\3\2\2\2\u015d\u0133\3\2\2\2\u015d"+
		"\u0136\3\2\2\2\u015d\u0139\3\2\2\2\u015d\u013c\3\2\2\2\u015d\u013f\3\2"+
		"\2\2\u015d\u0142\3\2\2\2\u015d\u0145\3\2\2\2\u015d\u0148\3\2\2\2\u015d"+
		"\u014b\3\2\2\2\u015d\u014e\3\2\2\2\u015d\u0151\3\2\2\2\u015d\u0154\3\2"+
		"\2\2\u015d\u0157\3\2\2\2\u015d\u015a\3\2\2\2\u015eB\3\2\2\2\u015f\u0161"+
		"\7&\2\2\u0160\u015f\3\2\2\2\u0160\u0161\3\2\2\2\u0161\u0162\3\2\2\2\u0162"+
		"\u0166\t\5\2\2\u0163\u0165\t\6\2\2\u0164\u0163\3\2\2\2\u0165\u0168\3\2"+
		"\2\2\u0166\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167D\3\2\2\2\u0168\u0166"+
		"\3\2\2\2\u0169\u016a\t\7\2\2\u016a\u016b\3\2\2\2\u016b\u016c\b#\2\2\u016c"+
		"F\3\2\2\2\u016d\u016e\7?\2\2\u016e\u0175\7?\2\2\u016f\u0175\t\b\2\2\u0170"+
		"\u0171\7@\2\2\u0171\u0175\7?\2\2\u0172\u0173\7>\2\2\u0173\u0175\7?\2\2"+
		"\u0174\u016d\3\2\2\2\u0174\u016f\3\2\2\2\u0174\u0170\3\2\2\2\u0174\u0172"+
		"\3\2\2\2\u0175H\3\2\2\2\u0176\u0177\7>\2\2\u0177\u017c\7>\2\2\u0178\u0179"+
		"\7@\2\2\u0179\u017c\7@\2\2\u017a\u017c\t\t\2\2\u017b\u0176\3\2\2\2\u017b"+
		"\u0178\3\2\2\2\u017b\u017a\3\2\2\2\u017cJ\3\2\2\2\u017d\u0181\t\n\2\2"+
		"\u017e\u0180\t\13\2\2\u017f\u017e\3\2\2\2\u0180\u0183\3\2\2\2\u0181\u017f"+
		"\3\2\2\2\u0181\u0182\3\2\2\2\u0182\u0186\3\2\2\2\u0183\u0181\3\2\2\2\u0184"+
		"\u0186\7\62\2\2\u0185\u017d\3\2\2\2\u0185\u0184\3\2\2\2\u0186L\3\2\2\2"+
		"\u0187\u0188\7\62\2\2\u0188\u0189\7z\2\2\u0189\u018a\3\2\2\2\u018a\u018e"+
		"\t\f\2\2\u018b\u018d\t\r\2\2\u018c\u018b\3\2\2\2\u018d\u0190\3\2\2\2\u018e"+
		"\u018c\3\2\2\2\u018e\u018f\3\2\2\2\u018fN\3\2\2\2\u0190\u018e\3\2\2\2"+
		"\u0191\u0192\7\62\2\2\u0192\u0193\7d\2\2\u0193\u0194\3\2\2\2\u0194\u0198"+
		"\t\16\2\2\u0195\u0197\t\17\2\2\u0196\u0195\3\2\2\2\u0197\u019a\3\2\2\2"+
		"\u0198\u0196\3\2\2\2\u0198\u0199\3\2\2\2\u0199P\3\2\2\2\u019a\u0198\3"+
		"\2\2\2\u019b\u019f\t\n\2\2\u019c\u019e\t\13\2\2\u019d\u019c\3\2\2\2\u019e"+
		"\u01a1\3\2\2\2\u019f\u019d\3\2\2\2\u019f\u01a0\3\2\2\2\u01a0\u01a4\3\2"+
		"\2\2\u01a1\u019f\3\2\2\2\u01a2\u01a4\7\62\2\2\u01a3\u019b\3\2\2\2\u01a3"+
		"\u01a2\3\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01ae\7\60\2\2\u01a6\u01aa\t"+
		"\n\2\2\u01a7\u01a9\t\13\2\2\u01a8\u01a7\3\2\2\2\u01a9\u01ac\3\2\2\2\u01aa"+
		"\u01a8\3\2\2\2\u01aa\u01ab\3\2\2\2\u01ab\u01af\3\2\2\2\u01ac\u01aa\3\2"+
		"\2\2\u01ad\u01af\7\62\2\2\u01ae\u01a6\3\2\2\2\u01ae\u01ad\3\2\2\2\u01af"+
		"R\3\2\2\2\u01b0\u01b5\5K&\2\u01b1\u01b5\5O(\2\u01b2\u01b5\5M\'\2\u01b3"+
		"\u01b5\5Q)\2\u01b4\u01b0\3\2\2\2\u01b4\u01b1\3\2\2\2\u01b4\u01b2\3\2\2"+
		"\2\u01b4\u01b3\3\2\2\2\u01b5T\3\2\2\2\32\2]_hjqy\u0086\u009c\u00f6\u015d"+
		"\u0160\u0166\u0174\u017b\u0181\u0185\u018e\u0198\u019f\u01a3\u01aa\u01ae"+
		"\u01b4\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}