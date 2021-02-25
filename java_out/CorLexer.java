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
		ELIF=16, ELSE=17, IS=18, CONDITION=19, SEMI=20, COLON=21, OPAR=22, CPAR=23, 
		OBRACKET=24, CBRACKET=25, OBRACE=26, CBRACE=27, REGISTER=28, MNEMONIC=29, 
		VARIABLE=30, WHITESPACE=31, COMPARATOR=32, OPERATOR=33, NUMBER=34;
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
			"FOR", "CONTINUE", "BREAKALL", "BREAK", "IF", "ELIF", "ELSE", "IS", "CONDITION", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2$\u01bb\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\4+\t+\3"+
		"\2\3\2\3\3\3\3\3\4\3\4\3\4\3\4\7\4`\n\4\f\4\16\4c\13\4\3\4\3\4\3\5\3\5"+
		"\3\5\3\5\7\5k\n\5\f\5\16\5n\13\5\3\5\3\5\3\6\3\6\5\6t\n\6\3\7\3\7\3\7"+
		"\3\7\7\7z\n\7\f\7\16\7}\13\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\7\b\u0087"+
		"\n\b\f\b\16\b\u008a\13\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\5\f\u009f\n\f\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\25\3\25"+
		"\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\5\30\u00fc\n\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\35"+
		"\3\35\3\36\3\36\3\37\3\37\3 \3 \3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3"+
		"\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\"\3\""+
		"\3\"\3\"\3\"\3\"\3\"\5\"\u0163\n\"\3#\5#\u0166\n#\3#\3#\7#\u016a\n#\f"+
		"#\16#\u016d\13#\3$\3$\3$\3$\3%\3%\3%\3%\3%\3%\3%\5%\u017a\n%\3&\3&\3&"+
		"\3&\3&\5&\u0181\n&\3\'\3\'\7\'\u0185\n\'\f\'\16\'\u0188\13\'\3\'\5\'\u018b"+
		"\n\'\3(\3(\3(\3(\3(\7(\u0192\n(\f(\16(\u0195\13(\3)\3)\3)\3)\3)\7)\u019c"+
		"\n)\f)\16)\u019f\13)\3*\3*\7*\u01a3\n*\f*\16*\u01a6\13*\3*\5*\u01a9\n"+
		"*\3*\3*\3*\7*\u01ae\n*\f*\16*\u01b1\13*\3*\5*\u01b4\n*\3+\3+\3+\3+\5+"+
		"\u01ba\n+\3\u0088\2,\3\3\5\4\7\2\t\2\13\5\r\6\17\7\21\b\23\2\25\2\27\t"+
		"\31\n\33\13\35\f\37\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\26\63\27\65"+
		"\30\67\319\32;\33=\34?\35A\36C\37E G!I\"K#M\2O\2Q\2S\2U$\3\2\20\5\2\f"+
		"\f\17\17$$\4\2\f\f\17\17\3\2cj\5\2C\\aac|\7\2\60\60\62;C\\aac|\5\2\13"+
		"\f\17\17\"\"\4\2>>@@\t\2((,-//\61\61``~~\u0080\u0080\3\2\63;\4\2\62;a"+
		"a\5\2\62;CHch\6\2\62;CHaach\3\2\62\63\4\2\62\63aa\2\u01ec\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2U\3\2\2\2\3W\3\2\2\2\5Y\3\2\2\2\7[\3"+
		"\2\2\2\tf\3\2\2\2\13s\3\2\2\2\ru\3\2\2\2\17\u0082\3\2\2\2\21\u0090\3\2"+
		"\2\2\23\u0094\3\2\2\2\25\u0098\3\2\2\2\27\u009e\3\2\2\2\31\u00a0\3\2\2"+
		"\2\33\u00a4\3\2\2\2\35\u00ab\3\2\2\2\37\u00ae\3\2\2\2!\u00b2\3\2\2\2#"+
		"\u00bb\3\2\2\2%\u00c4\3\2\2\2\'\u00ca\3\2\2\2)\u00cd\3\2\2\2+\u00d2\3"+
		"\2\2\2-\u00d7\3\2\2\2/\u00fb\3\2\2\2\61\u00fd\3\2\2\2\63\u00ff\3\2\2\2"+
		"\65\u0101\3\2\2\2\67\u0103\3\2\2\29\u0105\3\2\2\2;\u0107\3\2\2\2=\u0109"+
		"\3\2\2\2?\u010b\3\2\2\2A\u010d\3\2\2\2C\u0162\3\2\2\2E\u0165\3\2\2\2G"+
		"\u016e\3\2\2\2I\u0179\3\2\2\2K\u0180\3\2\2\2M\u018a\3\2\2\2O\u018c\3\2"+
		"\2\2Q\u0196\3\2\2\2S\u01a8\3\2\2\2U\u01b9\3\2\2\2WX\7?\2\2X\4\3\2\2\2"+
		"YZ\7.\2\2Z\6\3\2\2\2[a\7)\2\2\\`\n\2\2\2]^\7)\2\2^`\7)\2\2_\\\3\2\2\2"+
		"_]\3\2\2\2`c\3\2\2\2a_\3\2\2\2ab\3\2\2\2bd\3\2\2\2ca\3\2\2\2de\7)\2\2"+
		"e\b\3\2\2\2fl\7$\2\2gk\n\2\2\2hi\7$\2\2ik\7$\2\2jg\3\2\2\2jh\3\2\2\2k"+
		"n\3\2\2\2lj\3\2\2\2lm\3\2\2\2mo\3\2\2\2nl\3\2\2\2op\7$\2\2p\n\3\2\2\2"+
		"qt\5\7\4\2rt\5\t\5\2sq\3\2\2\2sr\3\2\2\2t\f\3\2\2\2uv\7\61\2\2vw\7\61"+
		"\2\2w{\3\2\2\2xz\n\3\2\2yx\3\2\2\2z}\3\2\2\2{y\3\2\2\2{|\3\2\2\2|~\3\2"+
		"\2\2}{\3\2\2\2~\177\t\3\2\2\177\u0080\3\2\2\2\u0080\u0081\b\7\2\2\u0081"+
		"\16\3\2\2\2\u0082\u0083\7\61\2\2\u0083\u0084\7,\2\2\u0084\u0088\3\2\2"+
		"\2\u0085\u0087\13\2\2\2\u0086\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088"+
		"\u0089\3\2\2\2\u0088\u0086\3\2\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2"+
		"\2\2\u008b\u008c\7,\2\2\u008c\u008d\7\61\2\2\u008d\u008e\3\2\2\2\u008e"+
		"\u008f\b\b\2\2\u008f\20\3\2\2\2\u0090\u0091\7t\2\2\u0091\u0092\7c\2\2"+
		"\u0092\u0093\7o\2\2\u0093\22\3\2\2\2\u0094\u0095\7t\2\2\u0095\u0096\7"+
		"q\2\2\u0096\u0097\7o\2\2\u0097\24\3\2\2\2\u0098\u0099\7r\2\2\u0099\u009a"+
		"\7t\2\2\u009a\u009b\7g\2\2\u009b\26\3\2\2\2\u009c\u009f\5\23\n\2\u009d"+
		"\u009f\5\25\13\2\u009e\u009c\3\2\2\2\u009e\u009d\3\2\2\2\u009f\30\3\2"+
		"\2\2\u00a0\u00a1\7i\2\2\u00a1\u00a2\7r\2\2\u00a2\u00a3\7w\2\2\u00a3\32"+
		"\3\2\2\2\u00a4\u00a5\7k\2\2\u00a5\u00a6\7o\2\2\u00a6\u00a7\7r\2\2\u00a7"+
		"\u00a8\7q\2\2\u00a8\u00a9\7t\2\2\u00a9\u00aa\7v\2\2\u00aa\34\3\2\2\2\u00ab"+
		"\u00ac\7c\2\2\u00ac\u00ad\7u\2\2\u00ad\36\3\2\2\2\u00ae\u00af\7h\2\2\u00af"+
		"\u00b0\7q\2\2\u00b0\u00b1\7t\2\2\u00b1 \3\2\2\2\u00b2\u00b3\7e\2\2\u00b3"+
		"\u00b4\7q\2\2\u00b4\u00b5\7p\2\2\u00b5\u00b6\7v\2\2\u00b6\u00b7\7k\2\2"+
		"\u00b7\u00b8\7p\2\2\u00b8\u00b9\7w\2\2\u00b9\u00ba\7g\2\2\u00ba\"\3\2"+
		"\2\2\u00bb\u00bc\7d\2\2\u00bc\u00bd\7t\2\2\u00bd\u00be\7g\2\2\u00be\u00bf"+
		"\7c\2\2\u00bf\u00c0\7m\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7n\2\2\u00c2"+
		"\u00c3\7n\2\2\u00c3$\3\2\2\2\u00c4\u00c5\7d\2\2\u00c5\u00c6\7t\2\2\u00c6"+
		"\u00c7\7g\2\2\u00c7\u00c8\7c\2\2\u00c8\u00c9\7m\2\2\u00c9&\3\2\2\2\u00ca"+
		"\u00cb\7k\2\2\u00cb\u00cc\7h\2\2\u00cc(\3\2\2\2\u00cd\u00ce\7g\2\2\u00ce"+
		"\u00cf\7n\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7h\2\2\u00d1*\3\2\2\2\u00d2"+
		"\u00d3\7g\2\2\u00d3\u00d4\7n\2\2\u00d4\u00d5\7u\2\2\u00d5\u00d6\7g\2\2"+
		"\u00d6,\3\2\2\2\u00d7\u00d8\7k\2\2\u00d8\u00d9\7u\2\2\u00d9.\3\2\2\2\u00da"+
		"\u00db\7|\2\2\u00db\u00dc\7g\2\2\u00dc\u00dd\7t\2\2\u00dd\u00fc\7q\2\2"+
		"\u00de\u00df\7e\2\2\u00df\u00e0\7c\2\2\u00e0\u00e1\7t\2\2\u00e1\u00e2"+
		"\7t\2\2\u00e2\u00fc\7{\2\2\u00e3\u00e4\7p\2\2\u00e4\u00e5\7g\2\2\u00e5"+
		"\u00e6\7i\2\2\u00e6\u00e7\7c\2\2\u00e7\u00e8\7v\2\2\u00e8\u00e9\7k\2\2"+
		"\u00e9\u00ea\7x\2\2\u00ea\u00fc\7g\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed"+
		"\7s\2\2\u00ed\u00ee\7w\2\2\u00ee\u00ef\7c\2\2\u00ef\u00fc\7n\2\2\u00f0"+
		"\u00f1\7i\2\2\u00f1\u00f2\7t\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7c\2\2"+
		"\u00f4\u00f5\7v\2\2\u00f5\u00f6\7g\2\2\u00f6\u00fc\7t\2\2\u00f7\u00f8"+
		"\7n\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7u\2\2\u00fa\u00fc\7u\2\2\u00fb"+
		"\u00da\3\2\2\2\u00fb\u00de\3\2\2\2\u00fb\u00e3\3\2\2\2\u00fb\u00eb\3\2"+
		"\2\2\u00fb\u00f0\3\2\2\2\u00fb\u00f7\3\2\2\2\u00fc\60\3\2\2\2\u00fd\u00fe"+
		"\7=\2\2\u00fe\62\3\2\2\2\u00ff\u0100\7<\2\2\u0100\64\3\2\2\2\u0101\u0102"+
		"\7*\2\2\u0102\66\3\2\2\2\u0103\u0104\7+\2\2\u01048\3\2\2\2\u0105\u0106"+
		"\7]\2\2\u0106:\3\2\2\2\u0107\u0108\7_\2\2\u0108<\3\2\2\2\u0109\u010a\7"+
		"}\2\2\u010a>\3\2\2\2\u010b\u010c\7\177\2\2\u010c@\3\2\2\2\u010d\u010e"+
		"\t\4\2\2\u010eB\3\2\2\2\u010f\u0110\7p\2\2\u0110\u0111\7q\2\2\u0111\u0163"+
		"\7r\2\2\u0112\u0113\7n\2\2\u0113\u0114\7f\2\2\u0114\u0163\7t\2\2\u0115"+
		"\u0116\7u\2\2\u0116\u0117\7v\2\2\u0117\u0163\7t\2\2\u0118\u0119\7n\2\2"+
		"\u0119\u011a\7r\2\2\u011a\u0163\7v\2\2\u011b\u011c\7u\2\2\u011c\u011d"+
		"\7r\2\2\u011d\u0163\7v\2\2\u011e\u011f\7e\2\2\u011f\u0120\7o\2\2\u0120"+
		"\u0163\7r\2\2\u0121\u0122\7c\2\2\u0122\u0123\7f\2\2\u0123\u0163\7f\2\2"+
		"\u0124\u0125\7u\2\2\u0125\u0126\7w\2\2\u0126\u0163\7d\2\2\u0127\u0128"+
		"\7o\2\2\u0128\u0129\7w\2\2\u0129\u0163\7n\2\2\u012a\u012b\7f\2\2\u012b"+
		"\u012c\7k\2\2\u012c\u0163\7x\2\2\u012d\u012e\7o\2\2\u012e\u012f\7q\2\2"+
		"\u012f\u0163\7f\2\2\u0130\u0131\7c\2\2\u0131\u0132\7p\2\2\u0132\u0163"+
		"\7f\2\2\u0133\u0134\7q\2\2\u0134\u0163\7t\2\2\u0135\u0136\7z\2\2\u0136"+
		"\u0137\7q\2\2\u0137\u0163\7t\2\2\u0138\u0139\7p\2\2\u0139\u013a\7q\2\2"+
		"\u013a\u0163\7v\2\2\u013b\u013c\7n\2\2\u013c\u013d\7u\2\2\u013d\u0163"+
		"\7n\2\2\u013e\u013f\7n\2\2\u013f\u0140\7u\2\2\u0140\u0163\7t\2\2\u0141"+
		"\u0142\7r\2\2\u0142\u0143\7u\2\2\u0143\u0163\7j\2\2\u0144\u0145\7r\2\2"+
		"\u0145\u0146\7q\2\2\u0146\u0163\7r\2\2\u0147\u0148\7r\2\2\u0148\u0149"+
		"\7g\2\2\u0149\u0163\7m\2\2\u014a\u014b\7l\2\2\u014b\u014c\7o\2\2\u014c"+
		"\u0163\7r\2\2\u014d\u014e\7l\2\2\u014e\u014f\7u\2\2\u014f\u0163\7t\2\2"+
		"\u0150\u0151\7t\2\2\u0151\u0152\7v\2\2\u0152\u0163\7u\2\2\u0153\u0154"+
		"\7l\2\2\u0154\u0155\7q\2\2\u0155\u0163\7e\2\2\u0156\u0157\7l\2\2\u0157"+
		"\u0158\7u\2\2\u0158\u0163\7e\2\2\u0159\u015a\7t\2\2\u015a\u015b\7u\2\2"+
		"\u015b\u0163\7e\2\2\u015c\u015d\7t\2\2\u015d\u015e\7v\2\2\u015e\u0163"+
		"\7k\2\2\u015f\u0160\7t\2\2\u0160\u0161\7k\2\2\u0161\u0163\7e\2\2\u0162"+
		"\u010f\3\2\2\2\u0162\u0112\3\2\2\2\u0162\u0115\3\2\2\2\u0162\u0118\3\2"+
		"\2\2\u0162\u011b\3\2\2\2\u0162\u011e\3\2\2\2\u0162\u0121\3\2\2\2\u0162"+
		"\u0124\3\2\2\2\u0162\u0127\3\2\2\2\u0162\u012a\3\2\2\2\u0162\u012d\3\2"+
		"\2\2\u0162\u0130\3\2\2\2\u0162\u0133\3\2\2\2\u0162\u0135\3\2\2\2\u0162"+
		"\u0138\3\2\2\2\u0162\u013b\3\2\2\2\u0162\u013e\3\2\2\2\u0162\u0141\3\2"+
		"\2\2\u0162\u0144\3\2\2\2\u0162\u0147\3\2\2\2\u0162\u014a\3\2\2\2\u0162"+
		"\u014d\3\2\2\2\u0162\u0150\3\2\2\2\u0162\u0153\3\2\2\2\u0162\u0156\3\2"+
		"\2\2\u0162\u0159\3\2\2\2\u0162\u015c\3\2\2\2\u0162\u015f\3\2\2\2\u0163"+
		"D\3\2\2\2\u0164\u0166\7&\2\2\u0165\u0164\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u016b\t\5\2\2\u0168\u016a\t\6\2\2\u0169\u0168\3\2"+
		"\2\2\u016a\u016d\3\2\2\2\u016b\u0169\3\2\2\2\u016b\u016c\3\2\2\2\u016c"+
		"F\3\2\2\2\u016d\u016b\3\2\2\2\u016e\u016f\t\7\2\2\u016f\u0170\3\2\2\2"+
		"\u0170\u0171\b$\2\2\u0171H\3\2\2\2\u0172\u0173\7?\2\2\u0173\u017a\7?\2"+
		"\2\u0174\u017a\t\b\2\2\u0175\u0176\7@\2\2\u0176\u017a\7?\2\2\u0177\u0178"+
		"\7>\2\2\u0178\u017a\7?\2\2\u0179\u0172\3\2\2\2\u0179\u0174\3\2\2\2\u0179"+
		"\u0175\3\2\2\2\u0179\u0177\3\2\2\2\u017aJ\3\2\2\2\u017b\u017c\7>\2\2\u017c"+
		"\u0181\7>\2\2\u017d\u017e\7@\2\2\u017e\u0181\7@\2\2\u017f\u0181\t\t\2"+
		"\2\u0180\u017b\3\2\2\2\u0180\u017d\3\2\2\2\u0180\u017f\3\2\2\2\u0181L"+
		"\3\2\2\2\u0182\u0186\t\n\2\2\u0183\u0185\t\13\2\2\u0184\u0183\3\2\2\2"+
		"\u0185\u0188\3\2\2\2\u0186\u0184\3\2\2\2\u0186\u0187\3\2\2\2\u0187\u018b"+
		"\3\2\2\2\u0188\u0186\3\2\2\2\u0189\u018b\7\62\2\2\u018a\u0182\3\2\2\2"+
		"\u018a\u0189\3\2\2\2\u018bN\3\2\2\2\u018c\u018d\7\62\2\2\u018d\u018e\7"+
		"z\2\2\u018e\u018f\3\2\2\2\u018f\u0193\t\f\2\2\u0190\u0192\t\r\2\2\u0191"+
		"\u0190\3\2\2\2\u0192\u0195\3\2\2\2\u0193\u0191\3\2\2\2\u0193\u0194\3\2"+
		"\2\2\u0194P\3\2\2\2\u0195\u0193\3\2\2\2\u0196\u0197\7\62\2\2\u0197\u0198"+
		"\7d\2\2\u0198\u0199\3\2\2\2\u0199\u019d\t\16\2\2\u019a\u019c\t\17\2\2"+
		"\u019b\u019a\3\2\2\2\u019c\u019f\3\2\2\2\u019d\u019b\3\2\2\2\u019d\u019e"+
		"\3\2\2\2\u019eR\3\2\2\2\u019f\u019d\3\2\2\2\u01a0\u01a4\t\n\2\2\u01a1"+
		"\u01a3\t\13\2\2\u01a2\u01a1\3\2\2\2\u01a3\u01a6\3\2\2\2\u01a4\u01a2\3"+
		"\2\2\2\u01a4\u01a5\3\2\2\2\u01a5\u01a9\3\2\2\2\u01a6\u01a4\3\2\2\2\u01a7"+
		"\u01a9\7\62\2\2\u01a8\u01a0\3\2\2\2\u01a8\u01a7\3\2\2\2\u01a9\u01aa\3"+
		"\2\2\2\u01aa\u01b3\7\60\2\2\u01ab\u01af\t\n\2\2\u01ac\u01ae\t\13\2\2\u01ad"+
		"\u01ac\3\2\2\2\u01ae\u01b1\3\2\2\2\u01af\u01ad\3\2\2\2\u01af\u01b0\3\2"+
		"\2\2\u01b0\u01b4\3\2\2\2\u01b1\u01af\3\2\2\2\u01b2\u01b4\7\62\2\2\u01b3"+
		"\u01ab\3\2\2\2\u01b3\u01b2\3\2\2\2\u01b4T\3\2\2\2\u01b5\u01ba\5M\'\2\u01b6"+
		"\u01ba\5Q)\2\u01b7\u01ba\5O(\2\u01b8\u01ba\5S*\2\u01b9\u01b5\3\2\2\2\u01b9"+
		"\u01b6\3\2\2\2\u01b9\u01b7\3\2\2\2\u01b9\u01b8\3\2\2\2\u01baV\3\2\2\2"+
		"\32\2_ajls{\u0088\u009e\u00fb\u0162\u0165\u016b\u0179\u0180\u0186\u018a"+
		"\u0193\u019d\u01a4\u01a8\u01af\u01b3\u01b9\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}