package lexsyn;

import java_cup.runtime.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

%%
%cup

%{
    String TOKENS_FILENAME = "tokens_list.txt";

    FileWriter fw = null;
    BufferedWriter bw =null;
%}

%init{
    fw = new FileWriter(TOKENS_FILENAME);
    bw = new BufferedWriter(fw);
%init}

%initthrow{
    IOException
%initthrow}

%eof{
    bw.close();
    fw.close();
%eof}

%eofthrow{
    IOException
%eofthrow}

%%




array {
    System.out.print("<ARRAY> ");
    bw.write("<ARRAY> ");
    return new Symbol(sym.ARRAY);
}

if {
    System.out.print("<IF> ");
    bw.write("<IF> ");
    return new Symbol(sym.IF);
}

then {
    System.out.print("<THEN> ");
    bw.write("<THEN> ");
    return new Symbol(sym.THEN);
}

else {
    System.out.print("<ELSE> ");
    bw.write("<ELSE> ");
    return new Symbol(sym.ELSE);
}

while {
    System.out.print("<WHILE> ");
    bw.write("<WHILE> ");
    return new Symbol(sym.WHILE);
}

for {
    System.out.print("<FOR> ");
    bw.write("<FOR> ");
    return new Symbol(sym.FOR);
}

to {
    System.out.print("<TO> ");
    bw.write("<TO> ");
    return new Symbol(sym.FOR);
}

do {
    System.out.print("<DO> ");
    bw.write("<DO> ");
    return new Symbol(sym.DO);
}

let {
    System.out.print("<LET> ");
    bw.write("<LET> ");
    return new Symbol(sym.LET);
}

in {
    System.out.print("<IN> ");
    bw.write("<IN> ");
    return new Symbol(sym.IN);
}

end {
    System.out.print("<END> ");
    bw.write("<END> ");
    return new Symbol(sym.END);
}

of {
    System.out.print("<OF> ");
    bw.write("<OF> ");
    return new Symbol(sym.OF);
}

break {
    System.out.print("<BREAK> ");
    bw.write("<BREAK> ");
    return new Symbol(sym.BREAK);
}

nil {
    System.out.print("<NIL> ");
    bw.write("<NIL> ");
    return new Symbol(sym.NIL);
}

function {
    System.out.print("<FUNCTION> ");
    bw.write("<FUNCTION> ");
    return new Symbol(sym.FUNCTION);
}

var {
    System.out.print("<VAR> ");
    bw.write("<VAR> ");
    return new Symbol(sym.VAR);
}

type {
    System.out.print("<TYPE> ");
    bw.write("<TYPE> ");
    return new Symbol(sym.TYPE);
}




(,) {
    System.out.print("<COMMA> ");
    bw.write("<COMMA>");
    return new Symbol(sym.COMMA);
}

(:) {
    System.out.print("<COLON> ");
    bw.write("<COLON>");
    return new Symbol(sym.COLON);
}

(;) {
    System.out.print("<SEMICOLON> ");
    bw.write("<SEMICOLON>");
    return new Symbol(sym.SEMICOLON);
}

(\() {
    System.out.print("<LPAREN> ");
    bw.write("<LPAREN>");
    return new Symbol(sym.LPAREN);
}

(\)) {
    System.out.print("<RPAREN> ");
    bw.write("<RPAREN>");
    return new Symbol(sym.RPAREN);
}

(\[) {
    System.out.print("<LBRACKET> ");
    bw.write("<LBRACKET>");
    return new Symbol(sym.LBRACKET);
}

(\]) {
    System.out.print("<RBRACKET> ");
    bw.write("<RBRACKET>");
    return new Symbol(sym.RBRACKET);
}

(\{) {
    System.out.print("<LBRACE> ");
    bw.write("<LBRACE>");
    return new Symbol(sym.LBRACE);
}

(\}) {
    System.out.print("<right_brace> ");
    System.out.print("<RBRACE> ");
    bw.write("<RBRACE>");
    return new Symbol(sym.RBRACE);
}

(\.) {
    System.out.print("<DOT> ");
    bw.write("<DOT>");
    return new Symbol(sym.DOT);
}

\+ {
    System.out.print("<PLUS> ");
    bw.write("<PLUS>");
    return new Symbol(sym.PLUS);
}

(-) {
    System.out.print("<MINUS> ");
    bw.write("<MINUS>");
    return new Symbol(sym.MINUS);
}

(\*) {
    System.out.print("<TIMES> ");
    bw.write("<TIMES>");
    return new Symbol(sym.TIMES);
}

(\/) {
    System.out.print("<DIV> ");
    bw.write("<DIV>");
    return new Symbol(sym.DIV);
}

(=) {
    System.out.print("<EQCOMP> ");
    bw.write("<EQCOMP>");
    return new Symbol(sym.EQCOMP);
}

(<>) {
    System.out.print("<NEQCOMP> ");
    bw.write("<NEQCOMP>");
    return new Symbol(sym.NEQCOMP);
}

(<) {
    System.out.print("<LTCOMP> ");
    bw.write("<LTCOMP>");
    return new Symbol(sym.LTCOMP);
}

(<=) {
    System.out.print("<LECOMP> ");
    bw.write("<LECOMP>");
    return new Symbol(sym.LECOMP);
}

(>) {
    System.out.print("<GTCOMP> ");
    bw.write("<GTCOMP>");
    return new Symbol(sym.GTCOMP);
}

(>=) {
    System.out.print("<GECOMP> ");
    bw.write("<GECOMP>");
    return new Symbol(sym.GECOMP);
}

(&) {
    System.out.print("<ANDBOOL> ");
    bw.write("<ANDBOOL>");
    return new Symbol(sym.ANDBOOL);
}

(\|) {
    System.out.print("<ORBOOL> ");
    bw.write("<ORBOOL>");
    return new Symbol(sym.ORBOOL);
}

(:=) {
    System.out.print("<ASSIGN> ");
    bw.write("<ASSIGN>");
    return new Symbol(sym.ASSIGN);
}




[ \t\r\f] {
    System.out.print(yytext());
    bw.write(yytext());
}

[\n] {
    System.out.print(yytext());
    bw.write(yytext());
}




"(.*?)" {
    System.out.print("<STRING, \"" + yytext() + "\"> ");
    bw.write("<STRING, \"" + yytext() + "\"> ");
    return new Symbol(sym.STRING, new String(yytext()));
}

[A-Za-z][A-Za-z0-9_]* {
    System.out.print("<ID, " + yytext() + "> ");
    bw.write("<ID, " + yytext() + "> ");
    return new Symbol(sym.ID, new String(yytext()));
}

[+|-]?[0-9] {
    System.out.print("<NUM, " + yytext() + "> ");
    bw.write("<NUM, " + yytext() + "> ");
    return new Symbol(sym.NUM, new Integer(yytext()));
}




. {
    System.out.println("\n\n[ERROR] Illegal character: " + yytext());
    System.exit(1);
}
