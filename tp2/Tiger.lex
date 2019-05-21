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
    // System.out.print("<ARRAY> ");
    fw.write("<ARRAY> ");
    return new Symbol(sym.ARRAY);
}

if {
    // System.out.print("<IF> ");
    fw.write("<IF> ");
    return new Symbol(sym.IF);
}

then {
    // System.out.print("<THEN> ");
    fw.write("<THEN> ");
    return new Symbol(sym.THEN);
}

else {
    // System.out.print("<ELSE> ");
    fw.write("<ELSE> ");
    return new Symbol(sym.ELSE);
}

while {
    // System.out.print("<WHILE> ");
    fw.write("<WHILE> ");
    return new Symbol(sym.WHILE);
}

for {
    // System.out.print("<FOR> ");
    fw.write("<FOR> ");
    return new Symbol(sym.FOR);
}

to {
    // System.out.print("<TO> ");
    fw.write("<TO> ");
    return new Symbol(sym.FOR);
}

do {
    // System.out.print("<DO> ");
    fw.write("<DO> ");
    return new Symbol(sym.DO);
}

let {
    // System.out.print("<LET> ");
    fw.write("<LET> ");
    return new Symbol(sym.LET);
}

in {
    // System.out.print("<IN> ");
    fw.write("<IN> ");
    return new Symbol(sym.IN);
}

end {
    // System.out.print("<END> ");
    fw.write("<END> ");
    return new Symbol(sym.END);
}

of {
    // System.out.print("<OF> ");
    fw.write("<OF> ");
    return new Symbol(sym.OF);
}

break {
    // System.out.print("<BREAK> ");
    fw.write("<BREAK> ");
    return new Symbol(sym.BREAK);
}

nil {
    // System.out.print("<NIL> ");
    fw.write("<NIL> ");
    return new Symbol(sym.NIL);
}

function {
    // System.out.print("<FUNCTION> ");
    fw.write("<FUNCTION> ");
    return new Symbol(sym.FUNCTION);
}

var {
    // System.out.print("<VAR> ");
    fw.write("<VAR> ");
    return new Symbol(sym.VAR);
}

type {
    // System.out.print("<TYPE> ");
    fw.write("<TYPE> ");
    return new Symbol(sym.TYPE);
}




(,) {
    // System.out.print("<COMMA> ");
    fw.write("<COMMA>");
    return new Symbol(sym.COMMA);
}

(:) {
    // System.out.print("<COLON> ");
    fw.write("<COLON>");
    return new Symbol(sym.COLON);
}

(;) {
    // System.out.print("<SEMICOLON> ");
    fw.write("<SEMICOLON>");
    return new Symbol(sym.SEMICOLON);
}

(\() {
    // System.out.print("<LPAREN> ");
    fw.write("<LPAREN>");
    return new Symbol(sym.LPAREN);
}

(\)) {
    // System.out.print("<RPAREN> ");
    fw.write("<RPAREN>");
    return new Symbol(sym.RPAREN);
}

(\[) {
    // System.out.print("<LBRACKET> ");
    fw.write("<LBRACKET>");
    return new Symbol(sym.LBRACKET);
}

(\]) {
    // System.out.print("<RBRACKET> ");
    fw.write("<RBRACKET>");
    return new Symbol(sym.RBRACKET);
}

(\{) {
    // System.out.print("<LBRACE> ");
    fw.write("<LBRACE>");
    return new Symbol(sym.LBRACE);
}

(\}) {
    // System.out.print("<RBRACE> ");
    fw.write("<RBRACE>");
    return new Symbol(sym.RBRACE);
}

(\.) {
    // System.out.print("<DOT> ");
    fw.write("<DOT>");
    return new Symbol(sym.DOT);
}

\+ {
    // System.out.print("<PLUS> ");
    fw.write("<PLUS>");
    return new Symbol(sym.PLUS);
}

(-) {
    // System.out.print("<MINUS> ");
    fw.write("<MINUS>");
    return new Symbol(sym.MINUS);
}

(\*) {
    // System.out.print("<TIMES> ");
    fw.write("<TIMES>");
    return new Symbol(sym.TIMES);
}

(\/) {
    // System.out.print("<DIV> ");
    fw.write("<DIV>");
    return new Symbol(sym.DIV);
}

(=) {
    // System.out.print("<EQCOMP> ");
    fw.write("<EQCOMP>");
    return new Symbol(sym.EQCOMP);
}

(<>) {
    // System.out.print("<NEQCOMP> ");
    fw.write("<NEQCOMP>");
    return new Symbol(sym.NEQCOMP);
}

(<) {
    // System.out.print("<LTCOMP> ");
    fw.write("<LTCOMP>");
    return new Symbol(sym.LTCOMP);
}

(<=) {
    // System.out.print("<LECOMP> ");
    fw.write("<LECOMP>");
    return new Symbol(sym.LECOMP);
}

(>) {
    // System.out.print("<GTCOMP> ");
    fw.write("<GTCOMP>");
    return new Symbol(sym.GTCOMP);
}

(>=) {
    // System.out.print("<GECOMP> ");
    fw.write("<GECOMP>");
    return new Symbol(sym.GECOMP);
}

(&) {
    // System.out.print("<ANDBOOL> ");
    fw.write("<ANDBOOL>");
    return new Symbol(sym.ANDBOOL);
}

(\|) {
    // System.out.print("<ORBOOL> ");
    fw.write("<ORBOOL>");
    return new Symbol(sym.ORBOOL);
}

(:=) {
    // System.out.print("<ASSIGN> ");
    fw.write("<ASSIGN>");
    return new Symbol(sym.ASSIGN);
}




[ \t\r\f] {
    // System.out.print(yytext());
    fw.write(yytext());
}

[\n] {
    // System.out.print(yytext());
    fw.write(yytext());
}




(\"((.*)?)\") {
    // System.out.print("<STRING, \"" + yytext() + "\"> ");
    fw.write("<STRING, \"" + yytext() + "\"> ");
    return new Symbol(sym.STRING, new String(yytext()));
}

[A-Za-z][A-Za-z0-9_]* {
    // System.out.print("<ID, " + yytext() + "> ");
    fw.write("<ID, " + yytext() + "> ");
    return new Symbol(sym.ID, new String(yytext()));
}

[+|-]?[0-9] {
    // System.out.print("<NUM, " + yytext() + "> ");
    fw.write("<NUM, " + yytext() + "> ");
    return new Symbol(sym.NUM, new Integer(yytext()));
}




. {
    System.out.println("\n\nError: Illegal character: " + yytext());
    fw.write("\n\nError: Illegal character: " + yytext());
    System.exit(1);
}
