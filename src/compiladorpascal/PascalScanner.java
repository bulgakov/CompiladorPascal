/* The following code was generated by JFlex 1.6.1 */

/*
    Mini Pascal
*/
package compiladorpascal;
import java_cup.runtime.Symbol;
import java_cup.runtime.ComplexSymbolFactory;
import java_cup.runtime.ComplexSymbolFactory.Location;
import java.util.List;
import java.util.ArrayList;
import table.ErrorMsg;


/**
 * This class is a scanner generated by 
 * <a href="http://www.jflex.de/">JFlex</a> 1.6.1
 * from the specification file <tt>D:/Code/Java/CompiladorPascal/src/flex/Pascal.flex</tt>
 */
public class PascalScanner implements java_cup.runtime.Scanner, sym {

  /** This character denotes the end of file */
  public static final int YYEOF = -1;

  /** initial size of the lookahead buffer */
  private static final int ZZ_BUFFERSIZE = 16384;

  /** lexical states */
  public static final int YYINITIAL = 0;
  public static final int STRING = 2;

  /**
   * ZZ_LEXSTATE[l] is the state in the DFA for the lexical state l
   * ZZ_LEXSTATE[l+1] is the state in the DFA for the lexical state l
   *                  at the beginning of a line
   * l is of the form l = 2*k, k a non negative integer
   */
  private static final int ZZ_LEXSTATE[] = { 
     0,  0,  1, 1
  };

  /** 
   * Translates characters to character classes
   */
  private static final String ZZ_CMAP_PACKED = 
    "\10\0\2\2\1\1\1\60\1\61\1\3\22\0\1\2\1\0\1\12"+
    "\4\0\1\13\1\43\1\44\1\22\1\20\1\40\1\21\1\6\1\23"+
    "\12\11\1\42\1\41\1\16\1\15\1\17\2\0\1\24\1\52\1\47"+
    "\1\26\1\37\1\51\1\46\1\54\1\33\2\10\1\55\1\35\1\25"+
    "\1\27\1\45\1\10\1\30\1\57\1\31\1\50\1\34\1\36\1\10"+
    "\1\53\1\10\1\0\1\14\2\0\1\7\1\0\1\24\1\52\1\47"+
    "\1\26\1\37\1\51\1\46\1\54\1\33\2\10\1\55\1\35\1\25"+
    "\1\27\1\45\1\10\1\30\1\57\1\31\1\50\1\34\1\36\1\10"+
    "\1\53\1\10\1\4\1\0\1\5\7\0\1\60\252\0\2\32\115\0"+
    "\1\56\u1ea8\0\1\60\1\60\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\uffff\0\udfe6\0";

  /** 
   * Translates characters to character classes
   */
  private static final char [] ZZ_CMAP = zzUnpackCMap(ZZ_CMAP_PACKED);

  /** 
   * Translates DFA states to action switch labels.
   */
  private static final int [] ZZ_ACTION = zzUnpackAction();

  private static final String ZZ_ACTION_PACKED_0 =
    "\2\0\1\1\2\2\1\1\1\3\1\4\1\5\1\1"+
    "\1\6\1\7\1\10\1\11\1\12\1\13\1\14\6\4"+
    "\1\1\5\4\1\15\1\16\1\17\1\20\1\21\5\4"+
    "\1\22\2\23\1\24\1\1\1\0\1\2\1\0\1\25"+
    "\1\26\1\27\1\30\2\4\1\31\1\0\1\4\1\32"+
    "\1\4\1\33\3\4\1\0\1\34\1\4\1\34\6\4"+
    "\1\35\10\4\1\36\1\37\2\40\6\4\1\0\1\4"+
    "\1\41\1\42\1\0\1\4\1\0\1\4\1\43\1\0"+
    "\5\4\1\44\3\4\1\45\2\4\1\46\1\47\1\50"+
    "\1\0\1\4\1\0\1\4\1\0\1\4\2\51\2\4"+
    "\1\52\1\0\1\4\1\0\3\4\1\0\3\4\1\0"+
    "\1\4\2\53\2\54\2\4\2\55\2\56\2\4\2\57"+
    "\1\60\1\61\1\0\3\4\1\0\2\4\2\62\1\63"+
    "\1\4\1\0\1\4\1\64\1\4\2\65\1\66";

  private static int [] zzUnpackAction() {
    int [] result = new int[173];
    int offset = 0;
    offset = zzUnpackAction(ZZ_ACTION_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAction(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /** 
   * Translates a state to a row index in the transition table
   */
  private static final int [] ZZ_ROWMAP = zzUnpackRowMap();

  private static final String ZZ_ROWMAP_PACKED_0 =
    "\0\0\0\62\0\144\0\226\0\310\0\372\0\u012c\0\u012c"+
    "\0\u015e\0\u0190\0\144\0\u01c2\0\u01f4\0\144\0\144\0\144"+
    "\0\144\0\u0226\0\u0258\0\u028a\0\u02bc\0\u02ee\0\u0320\0\u0352"+
    "\0\u0384\0\u03b6\0\u03e8\0\u041a\0\u044c\0\144\0\144\0\u047e"+
    "\0\144\0\144\0\u04b0\0\u04e2\0\u0514\0\u0546\0\u0578\0\u05aa"+
    "\0\144\0\u05dc\0\144\0\u060e\0\372\0\144\0\u0190\0\u0640"+
    "\0\144\0\144\0\144\0\u0672\0\u06a4\0\u012c\0\u06d6\0\u0708"+
    "\0\u012c\0\u073a\0\u012c\0\u076c\0\u079e\0\u07d0\0\u0802\0\144"+
    "\0\u0834\0\u012c\0\u0866\0\u0898\0\u08ca\0\u08fc\0\u092e\0\u0960"+
    "\0\144\0\u0992\0\u09c4\0\u09f6\0\u0a28\0\u0a5a\0\u0a8c\0\u0abe"+
    "\0\u0af0\0\144\0\u012c\0\144\0\u012c\0\u0b22\0\u0b54\0\u0b86"+
    "\0\u0bb8\0\u0bea\0\u0c1c\0\u0c4e\0\u0c80\0\u012c\0\u012c\0\u0cb2"+
    "\0\u0ce4\0\u0d16\0\u0d48\0\u012c\0\u0d7a\0\u0dac\0\u0dde\0\u0e10"+
    "\0\u0e42\0\u0e74\0\u012c\0\u0ea6\0\u0ed8\0\u0f0a\0\u012c\0\u0f3c"+
    "\0\u0f6e\0\u012c\0\u012c\0\u012c\0\u0fa0\0\u0fd2\0\u1004\0\u1036"+
    "\0\u1068\0\u109a\0\144\0\u012c\0\u10cc\0\u10fe\0\u012c\0\u1130"+
    "\0\u1162\0\u1194\0\u11c6\0\u11f8\0\u122a\0\u125c\0\u128e\0\u12c0"+
    "\0\u12f2\0\u1324\0\u1356\0\144\0\u012c\0\144\0\u012c\0\u1388"+
    "\0\u13ba\0\144\0\u012c\0\144\0\u012c\0\u13ec\0\u141e\0\144"+
    "\0\u012c\0\u012c\0\u012c\0\u1450\0\u1482\0\u14b4\0\u14e6\0\u1518"+
    "\0\u154a\0\u157c\0\144\0\u012c\0\u012c\0\u15ae\0\u15e0\0\u1612"+
    "\0\u012c\0\u1644\0\144\0\u012c\0\u012c";

  private static int [] zzUnpackRowMap() {
    int [] result = new int[173];
    int offset = 0;
    offset = zzUnpackRowMap(ZZ_ROWMAP_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackRowMap(String packed, int offset, int [] result) {
    int i = 0;  /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int high = packed.charAt(i++) << 16;
      result[j++] = high | packed.charAt(i++);
    }
    return j;
  }

  /** 
   * The transition table of the DFA
   */
  private static final int [] ZZ_TRANS = zzUnpackTrans();

  private static final String ZZ_TRANS_PACKED_0 =
    "\1\3\1\4\1\5\1\4\1\6\1\3\1\7\1\3"+
    "\1\10\1\11\1\3\1\12\1\3\1\13\1\14\1\15"+
    "\1\16\1\17\1\20\1\21\1\22\1\23\1\24\1\25"+
    "\1\26\1\27\1\30\1\31\1\32\1\33\1\34\1\35"+
    "\1\36\1\37\1\40\1\41\1\42\1\43\1\10\1\44"+
    "\1\45\1\46\1\47\3\10\1\3\1\10\1\0\1\5"+
    "\1\50\1\51\1\50\1\52\6\50\1\53\1\50\1\54"+
    "\45\50\63\0\1\4\1\0\1\4\60\0\1\5\56\0"+
    "\1\5\5\55\1\56\54\55\7\0\3\10\12\0\6\10"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\13\0\1\11"+
    "\50\0\1\57\1\0\1\57\1\0\7\57\1\60\46\57"+
    "\15\0\1\61\1\0\1\62\57\0\1\63\53\0\3\10"+
    "\12\0\1\10\1\64\4\10\1\0\5\10\5\0\11\10"+
    "\1\0\1\10\11\0\3\10\12\0\3\10\1\65\2\10"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\11\0\3\10"+
    "\12\0\3\10\1\66\2\10\1\67\1\70\4\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\4\10\1\71"+
    "\1\10\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\6\10\1\0\4\10\1\72\5\0\11\10"+
    "\1\0\1\10\11\0\3\10\12\0\3\10\1\73\1\74"+
    "\1\10\1\0\5\10\5\0\6\10\1\75\1\76\1\10"+
    "\1\0\1\10\27\0\1\77\23\0\1\100\17\0\3\10"+
    "\12\0\1\10\1\101\4\10\1\0\5\10\5\0\4\10"+
    "\1\102\4\10\1\0\1\10\11\0\3\10\12\0\1\103"+
    "\5\10\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\3\10\1\104\2\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\4\10\1\105"+
    "\1\10\1\0\5\10\5\0\7\10\1\106\1\10\1\0"+
    "\1\10\11\0\3\10\12\0\1\10\1\107\4\10\1\0"+
    "\5\10\5\0\10\10\1\110\1\0\1\10\17\0\1\111"+
    "\53\0\3\10\12\0\4\10\1\112\1\10\1\0\5\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\6\10"+
    "\1\0\5\10\5\0\7\10\1\113\1\10\1\0\1\10"+
    "\11\0\3\10\12\0\1\10\1\114\4\10\1\0\5\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\1\115"+
    "\2\10\1\116\2\10\1\0\5\10\5\0\3\10\1\117"+
    "\5\10\1\0\1\10\11\0\3\10\12\0\3\10\1\120"+
    "\2\10\1\0\4\10\1\121\5\0\11\10\1\0\1\10"+
    "\2\0\1\50\1\0\1\50\1\0\6\50\1\0\1\50"+
    "\1\0\45\50\1\0\1\51\60\0\1\122\1\0\1\122"+
    "\1\0\54\122\15\0\1\57\55\0\3\10\12\0\2\10"+
    "\1\71\3\10\1\0\5\10\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\5\10\1\123\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\36\0\1\124\34\0\3\10\12\0"+
    "\6\10\1\0\1\10\1\125\3\10\5\0\11\10\1\0"+
    "\1\10\11\0\3\10\12\0\1\126\5\10\1\0\5\10"+
    "\5\0\1\127\1\10\1\130\6\10\1\0\1\10\11\0"+
    "\3\10\12\0\6\10\1\0\5\10\5\0\3\10\1\131"+
    "\5\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\5\10\5\0\1\132\10\10\1\0\1\10\11\0\3\10"+
    "\12\0\6\10\1\0\4\10\1\133\5\0\11\10\1\0"+
    "\1\10\33\0\1\134\37\0\3\10\12\0\5\10\1\135"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\11\0\3\10"+
    "\12\0\4\10\1\136\1\10\1\0\5\10\5\0\11\10"+
    "\1\0\1\10\11\0\3\10\12\0\2\10\1\137\3\10"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\11\0\3\10"+
    "\12\0\6\10\1\140\1\141\4\10\5\0\11\10\1\0"+
    "\1\10\11\0\3\10\12\0\6\10\1\142\1\143\4\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\2\10"+
    "\1\144\3\10\1\0\5\10\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\6\10\1\0\5\10\5\0\11\10"+
    "\1\145\1\146\11\0\3\10\12\0\3\10\1\147\2\10"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\11\0\3\10"+
    "\12\0\1\150\5\10\1\0\5\10\5\0\11\10\1\0"+
    "\1\10\11\0\3\10\12\0\5\10\1\151\1\0\5\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\6\10"+
    "\1\0\5\10\5\0\10\10\1\152\1\0\1\10\11\0"+
    "\3\10\12\0\4\10\1\153\1\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\1\10\1\154"+
    "\4\10\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\3\10\1\155\2\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\5\10\5\0\1\10\1\156\7\10\1\0\1\10\11\0"+
    "\3\10\12\0\2\10\1\157\3\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\4\10\1\160\5\0\11\10\1\0\1\10\11\0\3\10"+
    "\12\0\3\10\1\161\2\10\1\0\5\10\5\0\11\10"+
    "\1\0\1\10\11\0\3\10\12\0\6\10\1\0\4\10"+
    "\1\162\5\0\11\10\1\0\1\10\11\0\3\10\12\0"+
    "\6\10\1\0\4\10\1\163\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\1\10\1\164\4\10\1\0\5\10"+
    "\5\0\11\10\1\0\1\10\41\0\1\165\31\0\3\10"+
    "\12\0\6\10\1\0\4\10\1\166\5\0\11\10\1\0"+
    "\1\10\33\0\1\167\37\0\3\10\12\0\5\10\1\170"+
    "\1\0\5\10\5\0\11\10\1\0\1\10\57\0\1\171"+
    "\13\0\3\10\12\0\6\10\1\0\5\10\5\0\10\10"+
    "\1\172\1\0\1\10\41\0\1\173\31\0\3\10\12\0"+
    "\6\10\1\0\4\10\1\174\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\6\10\1\0\5\10\5\0\1\10"+
    "\1\175\1\176\6\10\1\0\1\10\11\0\3\10\12\0"+
    "\4\10\1\177\1\10\1\0\5\10\5\0\11\10\1\0"+
    "\1\10\11\0\3\10\12\0\6\10\1\200\1\201\4\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\6\10"+
    "\1\0\5\10\5\0\11\10\1\202\1\203\11\0\3\10"+
    "\12\0\6\10\1\0\5\10\5\0\2\10\1\204\6\10"+
    "\1\0\1\10\11\0\3\10\12\0\6\10\1\0\5\10"+
    "\5\0\10\10\1\205\1\0\1\10\11\0\3\10\12\0"+
    "\6\10\1\206\1\207\4\10\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\1\210\5\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\4\10\1\211"+
    "\1\10\1\0\5\10\5\0\11\10\1\0\1\10\50\0"+
    "\1\212\22\0\3\10\12\0\6\10\1\0\5\10\5\0"+
    "\1\10\1\213\7\10\1\0\1\10\41\0\1\214\31\0"+
    "\3\10\12\0\6\10\1\0\4\10\1\215\5\0\11\10"+
    "\1\0\1\10\41\0\1\216\31\0\3\10\12\0\6\10"+
    "\1\0\4\10\1\217\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\4\10\1\220\1\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\4\10\1\221\5\0\11\10\1\0\1\10\57\0\1\222"+
    "\13\0\3\10\12\0\6\10\1\0\5\10\5\0\10\10"+
    "\1\223\1\0\1\10\41\0\1\224\31\0\3\10\12\0"+
    "\6\10\1\0\4\10\1\225\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\5\10\1\226\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\4\10\1\227\5\0\11\10\1\0\1\10\27\0\1\230"+
    "\43\0\3\10\12\0\1\10\1\231\4\10\1\0\5\10"+
    "\5\0\11\10\1\0\1\10\11\0\3\10\12\0\5\10"+
    "\1\232\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\2\10\1\233\3\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\41\0\1\234\31\0\3\10\12\0"+
    "\6\10\1\0\4\10\1\235\5\0\11\10\1\0\1\10"+
    "\11\0\3\10\12\0\1\236\5\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\2\10\1\237"+
    "\3\10\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\6\10\1\240\1\241\4\10\5\0\11\10"+
    "\1\0\1\10\11\0\3\10\12\0\1\242\5\10\1\0"+
    "\5\10\5\0\11\10\1\0\1\10\32\0\1\243\40\0"+
    "\3\10\12\0\4\10\1\244\1\10\1\0\5\10\5\0"+
    "\11\10\1\0\1\10\11\0\3\10\12\0\6\10\1\0"+
    "\2\10\1\245\2\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\6\10\1\0\5\10\5\0\3\10\1\246"+
    "\5\10\1\0\1\10\31\0\1\247\41\0\3\10\12\0"+
    "\3\10\1\250\2\10\1\0\5\10\5\0\11\10\1\0"+
    "\1\10\11\0\3\10\12\0\1\10\1\251\4\10\1\0"+
    "\5\10\5\0\11\10\1\0\1\10\11\0\3\10\12\0"+
    "\4\10\1\252\1\10\1\0\5\10\5\0\11\10\1\0"+
    "\1\10\27\0\1\253\43\0\3\10\12\0\1\10\1\254"+
    "\4\10\1\0\5\10\5\0\11\10\1\0\1\10\11\0"+
    "\3\10\12\0\6\10\1\0\4\10\1\255\5\0\11\10"+
    "\1\0\1\10\2\0";

  private static int [] zzUnpackTrans() {
    int [] result = new int[5750];
    int offset = 0;
    offset = zzUnpackTrans(ZZ_TRANS_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackTrans(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      value--;
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }


  /* error codes */
  private static final int ZZ_UNKNOWN_ERROR = 0;
  private static final int ZZ_NO_MATCH = 1;
  private static final int ZZ_PUSHBACK_2BIG = 2;

  /* error messages for the codes above */
  private static final String ZZ_ERROR_MSG[] = {
    "Unknown internal scanner error",
    "Error: could not match input",
    "Error: pushback value was too large"
  };

  /**
   * ZZ_ATTRIBUTE[aState] contains the attributes of state <code>aState</code>
   */
  private static final int [] ZZ_ATTRIBUTE = zzUnpackAttribute();

  private static final String ZZ_ATTRIBUTE_PACKED_0 =
    "\2\0\1\11\7\1\1\11\2\1\4\11\14\1\2\11"+
    "\1\1\2\11\6\1\1\11\1\1\1\11\1\1\1\0"+
    "\1\11\1\0\1\1\3\11\3\1\1\0\7\1\1\0"+
    "\1\11\10\1\1\11\10\1\1\11\1\1\1\11\7\1"+
    "\1\0\3\1\1\0\1\1\1\0\2\1\1\0\17\1"+
    "\1\0\1\1\1\0\1\1\1\0\1\1\1\11\4\1"+
    "\1\0\1\1\1\0\3\1\1\0\3\1\1\0\1\1"+
    "\1\11\1\1\1\11\3\1\1\11\1\1\1\11\3\1"+
    "\1\11\3\1\1\0\3\1\1\0\2\1\1\11\3\1"+
    "\1\0\3\1\1\11\2\1";

  private static int [] zzUnpackAttribute() {
    int [] result = new int[173];
    int offset = 0;
    offset = zzUnpackAttribute(ZZ_ATTRIBUTE_PACKED_0, offset, result);
    return result;
  }

  private static int zzUnpackAttribute(String packed, int offset, int [] result) {
    int i = 0;       /* index in packed string  */
    int j = offset;  /* index in unpacked array */
    int l = packed.length();
    while (i < l) {
      int count = packed.charAt(i++);
      int value = packed.charAt(i++);
      do result[j++] = value; while (--count > 0);
    }
    return j;
  }

  /** the input device */
  private java.io.Reader zzReader;

  /** the current state of the DFA */
  private int zzState;

  /** the current lexical state */
  private int zzLexicalState = YYINITIAL;

  /** this buffer contains the current text to be matched and is
      the source of the yytext() string */
  private char zzBuffer[] = new char[ZZ_BUFFERSIZE];

  /** the textposition at the last accepting state */
  private int zzMarkedPos;

  /** the current text position in the buffer */
  private int zzCurrentPos;

  /** startRead marks the beginning of the yytext() string in the buffer */
  private int zzStartRead;

  /** endRead marks the last character in the buffer, that has been read
      from input */
  private int zzEndRead;

  /** number of newlines encountered up to the start of the matched text */
  private int yyline;

  /** the number of characters up to the start of the matched text */
  private int yychar;

  /**
   * the number of characters from the last newline up to the start of the 
   * matched text
   */
  private int yycolumn;

  /** 
   * zzAtBOL == true <=> the scanner is currently at the beginning of a line
   */
  private boolean zzAtBOL = true;

  /** zzAtEOF == true <=> the scanner is at the EOF */
  private boolean zzAtEOF;

  /** denotes if the user-EOF-code has already been executed */
  private boolean zzEOFDone;
  
  /** 
   * The number of occupied positions in zzBuffer beyond zzEndRead.
   * When a lead/high surrogate has been read from the input stream
   * into the final zzBuffer position, this will have a value of 1;
   * otherwise, it will have a value of 0.
   */
  private int zzFinalHighSurrogate = 0;

  /* user code: */
    StringBuilder sb = new StringBuilder();
    List<ErrorMsg> errors = new ArrayList<ErrorMsg>();
  
    public PascalScanner(java.io.Reader in, ComplexSymbolFactory sf){
        this(in);
        symbolFactory = sf;
    }
    ComplexSymbolFactory symbolFactory;

    private Symbol symbol(String name, int sym) {
        return symbolFactory.newSymbol(name, sym, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+yylength(),yychar+yylength()));
    }

    private Symbol symbol(String name, int sym, Object val) {
        Location left = new Location(yyline+1,yycolumn+1,yychar);
        Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
        return symbolFactory.newSymbol(name, sym, left, right,val);
    }
    private Symbol symbol(String name, int sym, Object val,int buflength) {
        Location left = new Location(yyline+1,yycolumn+yylength()-buflength,yychar+yylength()-buflength);
        Location right= new Location(yyline+1,yycolumn+yylength(), yychar+yylength());
        return symbolFactory.newSymbol(name, sym, left, right,val);
    }
    private void addError() {
        errors.add(new ErrorMsg(yyline+1, yycolumn+1, "Unexpected character \"" + yytext() + "\""));
    }

    private Symbol symbol(int type) {
        return new Symbol(type, yyline+1, yycolumn+1);
    }

    private Symbol symbol(int type, Object value) {
        return new Symbol(type, yyline+1, yycolumn+1, value);
    }


  /**
   * Creates a new scanner
   *
   * @param   in  the java.io.Reader to read input from.
   */
  public PascalScanner(java.io.Reader in) {
    this.zzReader = in;
  }


  /** 
   * Unpacks the compressed character translation table.
   *
   * @param packed   the packed character translation table
   * @return         the unpacked character translation table
   */
  private static char [] zzUnpackCMap(String packed) {
    char [] map = new char[0x110000];
    int i = 0;  /* index in packed string  */
    int j = 0;  /* index in unpacked array */
    while (i < 222) {
      int  count = packed.charAt(i++);
      char value = packed.charAt(i++);
      do map[j++] = value; while (--count > 0);
    }
    return map;
  }


  /**
   * Refills the input buffer.
   *
   * @return      <code>false</code>, iff there was new input.
   * 
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  private boolean zzRefill() throws java.io.IOException {

    /* first: make room (if you can) */
    if (zzStartRead > 0) {
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
      System.arraycopy(zzBuffer, zzStartRead,
                       zzBuffer, 0,
                       zzEndRead-zzStartRead);

      /* translate stored positions */
      zzEndRead-= zzStartRead;
      zzCurrentPos-= zzStartRead;
      zzMarkedPos-= zzStartRead;
      zzStartRead = 0;
    }

    /* is the buffer big enough? */
    if (zzCurrentPos >= zzBuffer.length - zzFinalHighSurrogate) {
      /* if not: blow it up */
      char newBuffer[] = new char[zzBuffer.length*2];
      System.arraycopy(zzBuffer, 0, newBuffer, 0, zzBuffer.length);
      zzBuffer = newBuffer;
      zzEndRead += zzFinalHighSurrogate;
      zzFinalHighSurrogate = 0;
    }

    /* fill the buffer with new input */
    int requested = zzBuffer.length - zzEndRead;
    int numRead = zzReader.read(zzBuffer, zzEndRead, requested);

    /* not supposed to occur according to specification of java.io.Reader */
    if (numRead == 0) {
      throw new java.io.IOException("Reader returned 0 characters. See JFlex examples for workaround.");
    }
    if (numRead > 0) {
      zzEndRead += numRead;
      /* If numRead == requested, we might have requested to few chars to
         encode a full Unicode character. We assume that a Reader would
         otherwise never return half characters. */
      if (numRead == requested) {
        if (Character.isHighSurrogate(zzBuffer[zzEndRead - 1])) {
          --zzEndRead;
          zzFinalHighSurrogate = 1;
        }
      }
      /* potentially more input available */
      return false;
    }

    /* numRead < 0 ==> end of stream */
    return true;
  }

    
  /**
   * Closes the input stream.
   */
  public final void yyclose() throws java.io.IOException {
    zzAtEOF = true;            /* indicate end of file */
    zzEndRead = zzStartRead;  /* invalidate buffer    */

    if (zzReader != null)
      zzReader.close();
  }


  /**
   * Resets the scanner to read from a new input stream.
   * Does not close the old reader.
   *
   * All internal variables are reset, the old input stream 
   * <b>cannot</b> be reused (internal buffer is discarded and lost).
   * Lexical state is set to <tt>ZZ_INITIAL</tt>.
   *
   * Internal scan buffer is resized down to its initial length, if it has grown.
   *
   * @param reader   the new input stream 
   */
  public final void yyreset(java.io.Reader reader) {
    zzReader = reader;
    zzAtBOL  = true;
    zzAtEOF  = false;
    zzEOFDone = false;
    zzEndRead = zzStartRead = 0;
    zzCurrentPos = zzMarkedPos = 0;
    zzFinalHighSurrogate = 0;
    yyline = yychar = yycolumn = 0;
    zzLexicalState = YYINITIAL;
    if (zzBuffer.length > ZZ_BUFFERSIZE)
      zzBuffer = new char[ZZ_BUFFERSIZE];
  }


  /**
   * Returns the current lexical state.
   */
  public final int yystate() {
    return zzLexicalState;
  }


  /**
   * Enters a new lexical state
   *
   * @param newState the new lexical state
   */
  public final void yybegin(int newState) {
    zzLexicalState = newState;
  }


  /**
   * Returns the text matched by the current regular expression.
   */
  public final String yytext() {
    return new String( zzBuffer, zzStartRead, zzMarkedPos-zzStartRead );
  }


  /**
   * Returns the character at position <tt>pos</tt> from the 
   * matched text. 
   * 
   * It is equivalent to yytext().charAt(pos), but faster
   *
   * @param pos the position of the character to fetch. 
   *            A value from 0 to yylength()-1.
   *
   * @return the character at position pos
   */
  public final char yycharat(int pos) {
    return zzBuffer[zzStartRead+pos];
  }


  /**
   * Returns the length of the matched text region.
   */
  public final int yylength() {
    return zzMarkedPos-zzStartRead;
  }


  /**
   * Reports an error that occured while scanning.
   *
   * In a wellformed scanner (no or only correct usage of 
   * yypushback(int) and a match-all fallback rule) this method 
   * will only be called with things that "Can't Possibly Happen".
   * If this method is called, something is seriously wrong
   * (e.g. a JFlex bug producing a faulty scanner etc.).
   *
   * Usual syntax/scanner level error handling should be done
   * in error fallback rules.
   *
   * @param   errorCode  the code of the errormessage to display
   */
  private void zzScanError(int errorCode) {
    String message;
    try {
      message = ZZ_ERROR_MSG[errorCode];
    }
    catch (ArrayIndexOutOfBoundsException e) {
      message = ZZ_ERROR_MSG[ZZ_UNKNOWN_ERROR];
    }

    throw new Error(message);
  } 


  /**
   * Pushes the specified amount of characters back into the input stream.
   *
   * They will be read again by then next call of the scanning method
   *
   * @param number  the number of characters to be read again.
   *                This number must not be greater than yylength()!
   */
  public void yypushback(int number)  {
    if ( number > yylength() )
      zzScanError(ZZ_PUSHBACK_2BIG);

    zzMarkedPos -= number;
  }


  /**
   * Contains user EOF-code, which will be executed exactly once,
   * when the end of file is reached
   */
  private void zzDoEOF() throws java.io.IOException {
    if (!zzEOFDone) {
      zzEOFDone = true;
      yyclose();
    }
  }


  /**
   * Resumes scanning until the next regular expression is matched,
   * the end of input is encountered or an I/O-Error occurs.
   *
   * @return      the next token
   * @exception   java.io.IOException  if any I/O-Error occurs
   */
  public java_cup.runtime.Symbol next_token() throws java.io.IOException {
    int zzInput;
    int zzAction;

    // cached fields:
    int zzCurrentPosL;
    int zzMarkedPosL;
    int zzEndReadL = zzEndRead;
    char [] zzBufferL = zzBuffer;
    char [] zzCMapL = ZZ_CMAP;

    int [] zzTransL = ZZ_TRANS;
    int [] zzRowMapL = ZZ_ROWMAP;
    int [] zzAttrL = ZZ_ATTRIBUTE;

    while (true) {
      zzMarkedPosL = zzMarkedPos;

      yychar+= zzMarkedPosL-zzStartRead;

      boolean zzR = false;
      int zzCh;
      int zzCharCount;
      for (zzCurrentPosL = zzStartRead  ;
           zzCurrentPosL < zzMarkedPosL ;
           zzCurrentPosL += zzCharCount ) {
        zzCh = Character.codePointAt(zzBufferL, zzCurrentPosL, zzMarkedPosL);
        zzCharCount = Character.charCount(zzCh);
        switch (zzCh) {
        case '\u000B':
        case '\u000C':
        case '\u0085':
        case '\u2028':
        case '\u2029':
          yyline++;
          yycolumn = 0;
          zzR = false;
          break;
        case '\r':
          yyline++;
          yycolumn = 0;
          zzR = true;
          break;
        case '\n':
          if (zzR)
            zzR = false;
          else {
            yyline++;
            yycolumn = 0;
          }
          break;
        default:
          zzR = false;
          yycolumn += zzCharCount;
        }
      }

      if (zzR) {
        // peek one character ahead if it is \n (if we have counted one line too much)
        boolean zzPeek;
        if (zzMarkedPosL < zzEndReadL)
          zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        else if (zzAtEOF)
          zzPeek = false;
        else {
          boolean eof = zzRefill();
          zzEndReadL = zzEndRead;
          zzMarkedPosL = zzMarkedPos;
          zzBufferL = zzBuffer;
          if (eof) 
            zzPeek = false;
          else 
            zzPeek = zzBufferL[zzMarkedPosL] == '\n';
        }
        if (zzPeek) yyline--;
      }
      zzAction = -1;

      zzCurrentPosL = zzCurrentPos = zzStartRead = zzMarkedPosL;
  
      zzState = ZZ_LEXSTATE[zzLexicalState];

      // set up zzAction for empty match case:
      int zzAttributes = zzAttrL[zzState];
      if ( (zzAttributes & 1) == 1 ) {
        zzAction = zzState;
      }


      zzForAction: {
        while (true) {
    
          if (zzCurrentPosL < zzEndReadL) {
            zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
            zzCurrentPosL += Character.charCount(zzInput);
          }
          else if (zzAtEOF) {
            zzInput = YYEOF;
            break zzForAction;
          }
          else {
            // store back cached positions
            zzCurrentPos  = zzCurrentPosL;
            zzMarkedPos   = zzMarkedPosL;
            boolean eof = zzRefill();
            // get translated positions and possibly new buffer
            zzCurrentPosL  = zzCurrentPos;
            zzMarkedPosL   = zzMarkedPos;
            zzBufferL      = zzBuffer;
            zzEndReadL     = zzEndRead;
            if (eof) {
              zzInput = YYEOF;
              break zzForAction;
            }
            else {
              zzInput = Character.codePointAt(zzBufferL, zzCurrentPosL, zzEndReadL);
              zzCurrentPosL += Character.charCount(zzInput);
            }
          }
          int zzNext = zzTransL[ zzRowMapL[zzState] + zzCMapL[zzInput] ];
          if (zzNext == -1) break zzForAction;
          zzState = zzNext;

          zzAttributes = zzAttrL[zzState];
          if ( (zzAttributes & 1) == 1 ) {
            zzAction = zzState;
            zzMarkedPosL = zzCurrentPosL;
            if ( (zzAttributes & 8) == 8 ) break zzForAction;
          }

        }
      }

      // store back cached position
      zzMarkedPos = zzMarkedPosL;

      if (zzInput == YYEOF && zzStartRead == zzCurrentPos) {
        zzAtEOF = true;
            zzDoEOF();
          {     return symbolFactory.newSymbol("EOF", sym.EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
 }
      }
      else {
        switch (zzAction < 0 ? zzAction : ZZ_ACTION[zzAction]) {
          case 1: 
            { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext());
            }
          case 55: break;
          case 2: 
            { 
            }
          case 56: break;
          case 3: 
            { return symbol("DOT",sym.DOT);
            }
          case 57: break;
          case 4: 
            { return symbol("IDENTIFIER",sym.IDENTIFIER, yytext());
            }
          case 58: break;
          case 5: 
            { return symbol("INTEGER_LITERAL",sym.INTEGER_LITERAL, new Integer(yytext()));
            }
          case 59: break;
          case 6: 
            { return symbol("EQ",sym.EQ,yytext());
            }
          case 60: break;
          case 7: 
            { return symbol("LT",sym.LT,yytext());
            }
          case 61: break;
          case 8: 
            { return symbol("GT",sym.GT,yytext());
            }
          case 62: break;
          case 9: 
            { return symbol("PLUS",sym.PLUS,yytext());
            }
          case 63: break;
          case 10: 
            { return symbol("MINUS",sym.MINUS,yytext());
            }
          case 64: break;
          case 11: 
            { return symbol("MULT",sym.MULT,yytext());
            }
          case 65: break;
          case 12: 
            { return symbol("SLASH",sym.SLASH,yytext());
            }
          case 66: break;
          case 13: 
            { return symbol("COMMA",sym.COMMA);
            }
          case 67: break;
          case 14: 
            { return symbol("SEMICOLON",sym.SEMICOLON);
            }
          case 68: break;
          case 15: 
            { return symbol("COLON",sym.COLON);
            }
          case 69: break;
          case 16: 
            { return symbol("LPAREN",sym.LPAREN);
            }
          case 70: break;
          case 17: 
            { return symbol("RPAREN",sym.RPAREN);
            }
          case 71: break;
          case 18: 
            { sb.append( yytext() );
            }
          case 72: break;
          case 19: 
            { System.out.println("Cadena no finalizada"); 
                                  return symbol("error",sym.error, yytext());
            }
          case 73: break;
          case 20: 
            { yybegin(YYINITIAL); 
                                  return symbol("STRING_LITERAL",sym.STRING_LITERAL, sb.toString());
            }
          case 74: break;
          case 21: 
            { String cadena = yytext().substring(1, yytext().length() - 1);
                                    if (cadena.length() == 1)
                                        return symbol("CHAR_LITERAL",sym.STRING_LITERAL, cadena);
                                    else
                                        return symbol("STRING_LITERAL",sym.STRING_LITERAL, cadena);
            }
          case 75: break;
          case 22: 
            { return symbol("LE",sym.LE,yytext());
            }
          case 76: break;
          case 23: 
            { return symbol("NEQ",sym.NEQ,yytext());
            }
          case 77: break;
          case 24: 
            { return symbol("GE",sym.GE,yytext());
            }
          case 78: break;
          case 25: 
            { return symbol("DO",sym.DO);
            }
          case 79: break;
          case 26: 
            { return symbol("AND",sym.AND, yytext());
            }
          case 80: break;
          case 27: 
            { return symbol("TO",sym.TO);
            }
          case 81: break;
          case 28: 
            { return symbol("IF",sym.IF);
            }
          case 82: break;
          case 29: 
            { return symbol("ASSIGN",sym.ASSIGN,yytext());
            }
          case 83: break;
          case 30: 
            { System.out.println("Caracter no valido \""+yytext()+"\""); 
                                  return symbol("error",sym.error, yytext());
            }
          case 84: break;
          case 31: 
            { return symbol("NOT",sym.NOT,yytext());
            }
          case 85: break;
          case 32: 
            { return symbol("DIV",sym.DIV,yytext());
            }
          case 86: break;
          case 33: 
            { return symbol("VAR",sym.VAR);
            }
          case 87: break;
          case 34: 
            { return symbol("MOD",sym.MOD,yytext());
            }
          case 88: break;
          case 35: 
            { return symbol("END",sym.END);
            }
          case 89: break;
          case 36: 
            { return symbol("FOR",sym.FOR);
            }
          case 90: break;
          case 37: 
            { return symbol("READ",sym.READ);
            }
          case 91: break;
          case 38: 
            { return symbol("TRUE",sym.TRUE);
            }
          case 92: break;
          case 39: 
            { return symbol("TYPE",sym.TYPE);
            }
          case 93: break;
          case 40: 
            { return symbol("THEN",sym.THEN);
            }
          case 94: break;
          case 41: 
            { return symbol("ELSE",sym.ELSE);
            }
          case 95: break;
          case 42: 
            { return symbol("CHAR",sym.CHAR);
            }
          case 96: break;
          case 43: 
            { return symbol("WRITE",sym.WRITE);
            }
          case 97: break;
          case 44: 
            { return symbol("WHILE",sym.WHILE);
            }
          case 98: break;
          case 45: 
            { return symbol("UNTIL",sym.UNTIL);
            }
          case 99: break;
          case 46: 
            { return symbol("FALSE",sym.FALSE);
            }
          case 100: break;
          case 47: 
            { return symbol("BEGIN",sym.BEGIN);
            }
          case 101: break;
          case 48: 
            { return symbol("REPEAT",sym.REPEAT);
            }
          case 102: break;
          case 49: 
            { return symbol("RECORD",sym.RECORD);
            }
          case 103: break;
          case 50: 
            { return symbol("INTEGER",sym.INTEGER);
            }
          case 104: break;
          case 51: 
            { return symbol("PROGRAM",sym.PROGRAM);
            }
          case 105: break;
          case 52: 
            { return symbol("BOOLEAN",sym.BOOLEAN);
            }
          case 106: break;
          case 53: 
            { return symbol("FUNCTION",sym.FUNCTION);
            }
          case 107: break;
          case 54: 
            { return symbol("PROCEDURE",sym.PROCEDURE);
            }
          case 108: break;
          default:
            zzScanError(ZZ_NO_MATCH);
        }
      }
    }
  }


}
