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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, RAM=6, CONST=7, IMPORT=8, AS=9, 
		STRING=10, COMMENT=11, COMMENT_BLOCK=12, OBRACKET=13, CBRACKET=14, REGISTER=15, 
		MNEMONIC=16, VARIABLE=17, WHITESPACE=18, OPERATOR=19, NUMBER=20;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "RAM", "ROM", "PRE", "CONST", 
			"IMPORT", "AS", "SINGLE_STRING", "DOUBLE_STRING", "STRING", "COMMENT", 
			"COMMENT_BLOCK", "OBRACKET", "CBRACKET", "REGISTER", "MNEMONIC", "VARIABLE", 
			"WHITESPACE", "OPERATOR", "DEC", "HEX", "BIN", "FLT", "NUMBER"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'='", "'{'", "','", "'}'", "':'", "'ram'", null, "'import'", "'as'", 
			null, null, null, "'['", "']'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, "RAM", "CONST", "IMPORT", "AS", "STRING", 
			"COMMENT", "COMMENT_BLOCK", "OBRACKET", "CBRACKET", "REGISTER", "MNEMONIC", 
			"VARIABLE", "WHITESPACE", "OPERATOR", "NUMBER"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\26\u0132\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\3\2\3\2\3\3\3\3\3\4\3\4"+
		"\3\5\3\5\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\n\3"+
		"\n\5\nT\n\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\r\3\r\3\r"+
		"\3\r\7\rd\n\r\f\r\16\rg\13\r\3\r\3\r\3\16\3\16\3\16\3\16\7\16o\n\16\f"+
		"\16\16\16r\13\16\3\16\3\16\3\17\3\17\5\17x\n\17\3\20\3\20\3\20\3\20\7"+
		"\20~\n\20\f\20\16\20\u0081\13\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3"+
		"\21\7\21\u008b\n\21\f\21\16\21\u008e\13\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\22\3\22\3\23\3\23\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\5\25\u00eb\n\25\3\26\3\26\7\26\u00ef\n\26\f\26\16\26\u00f2\13"+
		"\26\3\27\3\27\3\27\3\27\3\30\3\30\3\31\3\31\7\31\u00fc\n\31\f\31\16\31"+
		"\u00ff\13\31\3\31\5\31\u0102\n\31\3\32\3\32\3\32\3\32\3\32\7\32\u0109"+
		"\n\32\f\32\16\32\u010c\13\32\3\33\3\33\3\33\3\33\3\33\7\33\u0113\n\33"+
		"\f\33\16\33\u0116\13\33\3\34\3\34\7\34\u011a\n\34\f\34\16\34\u011d\13"+
		"\34\3\34\5\34\u0120\n\34\3\34\3\34\3\34\7\34\u0125\n\34\f\34\16\34\u0128"+
		"\13\34\3\34\5\34\u012b\n\34\3\35\3\35\3\35\3\35\5\35\u0131\n\35\3\u008c"+
		"\2\36\3\3\5\4\7\5\t\6\13\7\r\b\17\2\21\2\23\t\25\n\27\13\31\2\33\2\35"+
		"\f\37\r!\16#\17%\20\'\21)\22+\23-\24/\25\61\2\63\2\65\2\67\29\26\3\2\17"+
		"\5\2\f\f\17\17$$\4\2\f\f\17\17\3\2cj\5\2C\\aac|\7\2\60\60\62;C\\aac|\5"+
		"\2\13\f\17\17\"\"\n\2((*-//\61\61>@``~~\u0080\u0080\3\2\63;\4\2\62;aa"+
		"\5\2\62;CHch\6\2\62;CHaach\3\2\62\63\4\2\62\63aa\2\u0157\2\3\3\2\2\2\2"+
		"\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\23\3\2\2"+
		"\2\2\25\3\2\2\2\2\27\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3"+
		"\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2"+
		"\2\2\29\3\2\2\2\3;\3\2\2\2\5=\3\2\2\2\7?\3\2\2\2\tA\3\2\2\2\13C\3\2\2"+
		"\2\rE\3\2\2\2\17I\3\2\2\2\21M\3\2\2\2\23S\3\2\2\2\25U\3\2\2\2\27\\\3\2"+
		"\2\2\31_\3\2\2\2\33j\3\2\2\2\35w\3\2\2\2\37y\3\2\2\2!\u0086\3\2\2\2#\u0094"+
		"\3\2\2\2%\u0096\3\2\2\2\'\u0098\3\2\2\2)\u00ea\3\2\2\2+\u00ec\3\2\2\2"+
		"-\u00f3\3\2\2\2/\u00f7\3\2\2\2\61\u0101\3\2\2\2\63\u0103\3\2\2\2\65\u010d"+
		"\3\2\2\2\67\u011f\3\2\2\29\u0130\3\2\2\2;<\7?\2\2<\4\3\2\2\2=>\7}\2\2"+
		">\6\3\2\2\2?@\7.\2\2@\b\3\2\2\2AB\7\177\2\2B\n\3\2\2\2CD\7<\2\2D\f\3\2"+
		"\2\2EF\7t\2\2FG\7c\2\2GH\7o\2\2H\16\3\2\2\2IJ\7t\2\2JK\7q\2\2KL\7o\2\2"+
		"L\20\3\2\2\2MN\7r\2\2NO\7t\2\2OP\7g\2\2P\22\3\2\2\2QT\5\17\b\2RT\5\21"+
		"\t\2SQ\3\2\2\2SR\3\2\2\2T\24\3\2\2\2UV\7k\2\2VW\7o\2\2WX\7r\2\2XY\7q\2"+
		"\2YZ\7t\2\2Z[\7v\2\2[\26\3\2\2\2\\]\7c\2\2]^\7u\2\2^\30\3\2\2\2_e\7)\2"+
		"\2`d\n\2\2\2ab\7)\2\2bd\7)\2\2c`\3\2\2\2ca\3\2\2\2dg\3\2\2\2ec\3\2\2\2"+
		"ef\3\2\2\2fh\3\2\2\2ge\3\2\2\2hi\7)\2\2i\32\3\2\2\2jp\7$\2\2ko\n\2\2\2"+
		"lm\7$\2\2mo\7$\2\2nk\3\2\2\2nl\3\2\2\2or\3\2\2\2pn\3\2\2\2pq\3\2\2\2q"+
		"s\3\2\2\2rp\3\2\2\2st\7$\2\2t\34\3\2\2\2ux\5\31\r\2vx\5\33\16\2wu\3\2"+
		"\2\2wv\3\2\2\2x\36\3\2\2\2yz\7\61\2\2z{\7\61\2\2{\177\3\2\2\2|~\n\3\2"+
		"\2}|\3\2\2\2~\u0081\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0082"+
		"\3\2\2\2\u0081\177\3\2\2\2\u0082\u0083\t\3\2\2\u0083\u0084\3\2\2\2\u0084"+
		"\u0085\b\20\2\2\u0085 \3\2\2\2\u0086\u0087\7\61\2\2\u0087\u0088\7,\2\2"+
		"\u0088\u008c\3\2\2\2\u0089\u008b\13\2\2\2\u008a\u0089\3\2\2\2\u008b\u008e"+
		"\3\2\2\2\u008c\u008d\3\2\2\2\u008c\u008a\3\2\2\2\u008d\u008f\3\2\2\2\u008e"+
		"\u008c\3\2\2\2\u008f\u0090\7,\2\2\u0090\u0091\7\61\2\2\u0091\u0092\3\2"+
		"\2\2\u0092\u0093\b\21\2\2\u0093\"\3\2\2\2\u0094\u0095\7]\2\2\u0095$\3"+
		"\2\2\2\u0096\u0097\7_\2\2\u0097&\3\2\2\2\u0098\u0099\t\4\2\2\u0099(\3"+
		"\2\2\2\u009a\u009b\7p\2\2\u009b\u009c\7q\2\2\u009c\u00eb\7r\2\2\u009d"+
		"\u009e\7n\2\2\u009e\u009f\7f\2\2\u009f\u00eb\7t\2\2\u00a0\u00a1\7u\2\2"+
		"\u00a1\u00a2\7v\2\2\u00a2\u00eb\7t\2\2\u00a3\u00a4\7n\2\2\u00a4\u00a5"+
		"\7r\2\2\u00a5\u00eb\7v\2\2\u00a6\u00a7\7u\2\2\u00a7\u00a8\7r\2\2\u00a8"+
		"\u00eb\7v\2\2\u00a9\u00aa\7e\2\2\u00aa\u00ab\7o\2\2\u00ab\u00eb\7r\2\2"+
		"\u00ac\u00ad\7c\2\2\u00ad\u00ae\7f\2\2\u00ae\u00eb\7f\2\2\u00af\u00b0"+
		"\7u\2\2\u00b0\u00b1\7w\2\2\u00b1\u00eb\7d\2\2\u00b2\u00b3\7o\2\2\u00b3"+
		"\u00b4\7w\2\2\u00b4\u00eb\7n\2\2\u00b5\u00b6\7f\2\2\u00b6\u00b7\7k\2\2"+
		"\u00b7\u00eb\7x\2\2\u00b8\u00b9\7o\2\2\u00b9\u00ba\7q\2\2\u00ba\u00eb"+
		"\7f\2\2\u00bb\u00bc\7c\2\2\u00bc\u00bd\7p\2\2\u00bd\u00eb\7f\2\2\u00be"+
		"\u00bf\7q\2\2\u00bf\u00eb\7t\2\2\u00c0\u00c1\7z\2\2\u00c1\u00c2\7q\2\2"+
		"\u00c2\u00eb\7t\2\2\u00c3\u00c4\7p\2\2\u00c4\u00c5\7q\2\2\u00c5\u00eb"+
		"\7v\2\2\u00c6\u00c7\7n\2\2\u00c7\u00c8\7u\2\2\u00c8\u00eb\7n\2\2\u00c9"+
		"\u00ca\7n\2\2\u00ca\u00cb\7u\2\2\u00cb\u00eb\7t\2\2\u00cc\u00cd\7r\2\2"+
		"\u00cd\u00ce\7u\2\2\u00ce\u00eb\7j\2\2\u00cf\u00d0\7r\2\2\u00d0\u00d1"+
		"\7q\2\2\u00d1\u00eb\7r\2\2\u00d2\u00d3\7r\2\2\u00d3\u00d4\7g\2\2\u00d4"+
		"\u00eb\7m\2\2\u00d5\u00d6\7l\2\2\u00d6\u00d7\7o\2\2\u00d7\u00eb\7r\2\2"+
		"\u00d8\u00d9\7l\2\2\u00d9\u00da\7u\2\2\u00da\u00eb\7t\2\2\u00db\u00dc"+
		"\7t\2\2\u00dc\u00dd\7v\2\2\u00dd\u00eb\7u\2\2\u00de\u00df\7l\2\2\u00df"+
		"\u00e0\7q\2\2\u00e0\u00eb\7e\2\2\u00e1\u00e2\7l\2\2\u00e2\u00e3\7u\2\2"+
		"\u00e3\u00eb\7e\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6\7u\2\2\u00e6\u00eb"+
		"\7e\2\2\u00e7\u00e8\7v\2\2\u00e8\u00e9\7h\2\2\u00e9\u00eb\7o\2\2\u00ea"+
		"\u009a\3\2\2\2\u00ea\u009d\3\2\2\2\u00ea\u00a0\3\2\2\2\u00ea\u00a3\3\2"+
		"\2\2\u00ea\u00a6\3\2\2\2\u00ea\u00a9\3\2\2\2\u00ea\u00ac\3\2\2\2\u00ea"+
		"\u00af\3\2\2\2\u00ea\u00b2\3\2\2\2\u00ea\u00b5\3\2\2\2\u00ea\u00b8\3\2"+
		"\2\2\u00ea\u00bb\3\2\2\2\u00ea\u00be\3\2\2\2\u00ea\u00c0\3\2\2\2\u00ea"+
		"\u00c3\3\2\2\2\u00ea\u00c6\3\2\2\2\u00ea\u00c9\3\2\2\2\u00ea\u00cc\3\2"+
		"\2\2\u00ea\u00cf\3\2\2\2\u00ea\u00d2\3\2\2\2\u00ea\u00d5\3\2\2\2\u00ea"+
		"\u00d8\3\2\2\2\u00ea\u00db\3\2\2\2\u00ea\u00de\3\2\2\2\u00ea\u00e1\3\2"+
		"\2\2\u00ea\u00e4\3\2\2\2\u00ea\u00e7\3\2\2\2\u00eb*\3\2\2\2\u00ec\u00f0"+
		"\t\5\2\2\u00ed\u00ef\t\6\2\2\u00ee\u00ed\3\2\2\2\u00ef\u00f2\3\2\2\2\u00f0"+
		"\u00ee\3\2\2\2\u00f0\u00f1\3\2\2\2\u00f1,\3\2\2\2\u00f2\u00f0\3\2\2\2"+
		"\u00f3\u00f4\t\7\2\2\u00f4\u00f5\3\2\2\2\u00f5\u00f6\b\27\2\2\u00f6.\3"+
		"\2\2\2\u00f7\u00f8\t\b\2\2\u00f8\60\3\2\2\2\u00f9\u00fd\t\t\2\2\u00fa"+
		"\u00fc\t\n\2\2\u00fb\u00fa\3\2\2\2\u00fc\u00ff\3\2\2\2\u00fd\u00fb\3\2"+
		"\2\2\u00fd\u00fe\3\2\2\2\u00fe\u0102\3\2\2\2\u00ff\u00fd\3\2\2\2\u0100"+
		"\u0102\7\62\2\2\u0101\u00f9\3\2\2\2\u0101\u0100\3\2\2\2\u0102\62\3\2\2"+
		"\2\u0103\u0104\7\62\2\2\u0104\u0105\7z\2\2\u0105\u0106\3\2\2\2\u0106\u010a"+
		"\t\13\2\2\u0107\u0109\t\f\2\2\u0108\u0107\3\2\2\2\u0109\u010c\3\2\2\2"+
		"\u010a\u0108\3\2\2\2\u010a\u010b\3\2\2\2\u010b\64\3\2\2\2\u010c\u010a"+
		"\3\2\2\2\u010d\u010e\7\62\2\2\u010e\u010f\7d\2\2\u010f\u0110\3\2\2\2\u0110"+
		"\u0114\t\r\2\2\u0111\u0113\t\16\2\2\u0112\u0111\3\2\2\2\u0113\u0116\3"+
		"\2\2\2\u0114\u0112\3\2\2\2\u0114\u0115\3\2\2\2\u0115\66\3\2\2\2\u0116"+
		"\u0114\3\2\2\2\u0117\u011b\t\t\2\2\u0118\u011a\t\n\2\2\u0119\u0118\3\2"+
		"\2\2\u011a\u011d\3\2\2\2\u011b\u0119\3\2\2\2\u011b\u011c\3\2\2\2\u011c"+
		"\u0120\3\2\2\2\u011d\u011b\3\2\2\2\u011e\u0120\7\62\2\2\u011f\u0117\3"+
		"\2\2\2\u011f\u011e\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u012a\7\60\2\2\u0122"+
		"\u0126\t\t\2\2\u0123\u0125\t\n\2\2\u0124\u0123\3\2\2\2\u0125\u0128\3\2"+
		"\2\2\u0126\u0124\3\2\2\2\u0126\u0127\3\2\2\2\u0127\u012b\3\2\2\2\u0128"+
		"\u0126\3\2\2\2\u0129\u012b\7\62\2\2\u012a\u0122\3\2\2\2\u012a\u0129\3"+
		"\2\2\2\u012b8\3\2\2\2\u012c\u0131\5\61\31\2\u012d\u0131\5\65\33\2\u012e"+
		"\u0131\5\63\32\2\u012f\u0131\5\67\34\2\u0130\u012c\3\2\2\2\u0130\u012d"+
		"\3\2\2\2\u0130\u012e\3\2\2\2\u0130\u012f\3\2\2\2\u0131:\3\2\2\2\26\2S"+
		"cenpw\177\u008c\u00ea\u00f0\u00fd\u0101\u010a\u0114\u011b\u011f\u0126"+
		"\u012a\u0130\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}