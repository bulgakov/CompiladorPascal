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

%%

%public
%cup
%class PascalScanner
%implements sym
%char
%line
%column
%ignorecase
//%debug

%{
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
%}

%eofval{
    return symbolFactory.newSymbol("EOF", sym.EOF, new Location(yyline+1,yycolumn+1,yychar), new Location(yyline+1,yycolumn+1,yychar+1));
%eofval}

/* Comunes */
Caracteres      = [^\r\n]
Espacios        = [ \t\f\b]
FinDeLinea      = (\r|\n|\r\n)

/* Comentarios */
Comentario      = "{" [^\}]* "}"

/* identificador */
Identificador   = [a-zA-Z.][a-zA-Z0-9_]*

/* Literales */
IntegerLiteral  = [0-9]+  
Cadena          = [^\r\n\"\\]
Caracter        = [^\r\n\'\\]
CadenaLiteral = "'" ("''" | [^\r\n\'])* "'"

/*Operadores*/
OPREL           = "=" | "<>" | ">" | "<" | ">=" | "<="
OPSUM           = "+" | "-"
OPMULT          = "*" | "/" 
OPLOG           = "and" | "or"
NOT             = "not"
DIV             = "div"
MOD             = "mod"

%state STRING

%%

<YYINITIAL> {
    /* palabras reservadas */
    "write"                     { return symbol("WRITE",sym.WRITE); }
    "read"                      { return symbol("READ",sym.READ); }
    
    "."                         { return symbol("DOT",sym.DOT); }
    ","                         { return symbol("COMMA",sym.COMMA); }
    ";"                         { return symbol("SEMICOLON",sym.SEMICOLON); }
    ":"                         { return symbol("COLON",sym.COLON); }
    "("                         { return symbol("LPAREN",sym.LPAREN); }
    ")"                         { return symbol("RPAREN",sym.RPAREN); }
    
    "program"                   { return symbol("PROGRAM",sym.PROGRAM); }
    "procedure"                 { return symbol("PROCEDURE",sym.PROCEDURE); }
    "function"                  { return symbol("FUNCTION",sym.FUNCTION); }
    "begin"                     { return symbol("BEGIN",sym.BEGIN); }
    "end"                       { return symbol("END",sym.END); }
    "var"                       { return symbol("VAR",sym.VAR); }
    "type"                      { return symbol("TYPE",sym.TYPE); }
    "record"                    { return symbol("RECORD",sym.RECORD); }
    
    "if"                        { return symbol("IF",sym.IF); }
    "then"                      { return symbol("THEN",sym.THEN); }
    "else"                      { return symbol("ELSE",sym.ELSE); }
    "for"                       { return symbol("FOR",sym.FOR); }
    "to"                        { return symbol("TO",sym.TO); }
    "do"                        { return symbol("DO",sym.DO); }
    "while"                     { return symbol("WHILE",sym.WHILE); }
    "repeat"                    { return symbol("REPEAT",sym.REPEAT); }
    "until"                     { return symbol("UNTIL",sym.UNTIL); }

    /* TIPOS */
    "char"                      { return symbol("CHAR",sym.CHAR); }
    "integer"                   { return symbol("INTEGER",sym.INTEGER); }
    "boolean"                   { return symbol("BOOLEAN",sym.BOOLEAN); }

    /* LITERALES */
    //"nil"                       { return symbol("NIL",sym.NIL); }
    "true"                      { return symbol("TRUE",sym.TRUE); }
    "false"                     { return symbol("FALSE",sym.FALSE); }

    /* operadores */
    ":="                        { return symbol("ASSIGN",sym.ASSIGN,"ASG"); }
    "="                         { return symbol("EQ",sym.EQ,"EQ"); }
    "<>"                        { return symbol("NEQ",sym.NEQ,"NEQ"); }
    "<"                         { return symbol("LT",sym.LT,"LT"); }
    ">"                         { return symbol("GT",sym.GT,"GT"); }
    "<="                        { return symbol("LE",sym.LE,"LE"); }
    ">="                        { return symbol("GE",sym.GE,"GE"); }
    "+"                         { return symbol("PLUS",sym.PLUS,"PLUS"); }
    "-"                         { return symbol("MINUS",sym.MINUS,"MINUS"); }
    "*"                         { return symbol("MULT",sym.MULT,"MULT"); }
    "/"                         { return symbol("SLASH",sym.SLASH,"SLASH"); }
    "div"                       { return symbol("DIV",sym.DIV,"DIV"); }
    "mod"                       { return symbol("MOD",sym.MOD,"MOD"); }
    "and"                       { return symbol("AND",sym.AND,"AND"); }
    "or"                        { return symbol("AND",sym.OR,"OR"); }
    "not"                       { return symbol("NOT",sym.NOT,"NOT"); }
    
    
    /* cadena literal */
    //\"                        { yybegin(STRING); sb.setLength(0); }
    //{CaracterLiteral}         { return symbol("CHAR_LITERAL",sym.CHAR_LITERAL, yytext().charAt(1)); }
    {CadenaLiteral}             {   String cadena = yytext().substring(1, yytext().length() - 1);
                                    if (cadena.length() == 1)
                                        return symbol("CHAR_LITERAL",sym.STRING_LITERAL, cadena);
                                    else
                                        return symbol("STRING_LITERAL",sym.STRING_LITERAL, cadena);
                                }
    {IntegerLiteral}            { return symbol("INTEGER_LITERAL",sym.INTEGER_LITERAL, new Integer(yytext())); }
    {Comentario}                { }
    {Espacios}+                 { }
    {FinDeLinea}+               { }

    /* identifiers */ 
    {Identificador}             { return symbol("IDENTIFIER",sym.IDENTIFIER, yytext()); }

    .                           { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }
}

<STRING> {
    \"                          { yybegin(YYINITIAL); 
                                  return symbol("STRING_LITERAL",sym.STRING_LITERAL, sb.toString()); 
                                }
  
    {Cadena}+                   { sb.append( yytext() ); }

    /* errores */
    {FinDeLinea}                { System.out.println("Cadena no finalizada"); 
                                  return symbol("error",sym.error, yytext()); }
    \\.                         { System.out.println("Caracter no valido \""+yytext()+"\""); 
                                  return symbol("error",sym.error, yytext()); }
}

/* errores */
.                               { System.out.println("Caracter no valido \"" + yytext() + "\" at line " + yyline + ", column " + yycolumn); 
                                  return symbol("error",sym.error, yytext()); }