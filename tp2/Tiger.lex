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

[ \t\r\f] {
	System.out.print(yytext());
}

[\n] {
  System.out.print(yytext());
	bw.write(yytext());
}

nil {
    System.out.print("<nil> ");
}

if {
  System.out.print(yytext());
	bw.write("<" + "if, >");
	return new Symbol(sym.IF);
}

then {
    System.out.print("<then> ");
}

else {
    System.out.print("<else> ");
}

while {
    System.out.print("<while> ");
}

do {
    System.out.print("<do> ");
}

for {
    System.out.print("<for> ");
}

to {
    System.out.print("<to> ");
}

break {
    System.out.print("<break> ");
}

let {
    System.out.print("<let> ");
}

in {
    System.out.print("<in> ");
}

end {
    System.out.print("<end> ");
}

type {
    System.out.print("<type> ");
}

var {
    System.out.print("<var> ");
}

function {
    System.out.print("<function> ");
}

array {
    System.out.print("<array> ");
}

of {
    System.out.print("<of> ");
}

int {
    System.out.print("<type, \"" + yytext() + "\"> ");
}

float {
    System.out.print("<type, \"" + yytext() + "\"> ");
}

string {
    System.out.print("<type, \"" + yytext() + "\"> ");
}

char {
    System.out.print("<type, \"" + yytext() + "\"> ");
}

(=) {
    System.out.print("<eq_comp> ");
}

(<>) {
    System.out.print("<neq_comp> ");
}

(<) {
    System.out.print("<lt_comp> ");
}

(>) {
    System.out.print("<gt_comp> ");
}

(<=) {
    System.out.print("<le_comp> ");
}

(>=) {
    System.out.print("<ge_comp> ");
}

\+ {
    System.out.print("<plus_comp> ");
}

(-) {
    System.out.print("<minus_comp> ");
}

(\*) {
    System.out.print("<multi_comp> ");
}

(\/) {
    System.out.print("<div_comp> ");
}

(&) {
    System.out.print("<and_bool> ");
}

(\|) {
    System.out.print("<or_bool> ");
}

(\() {
    System.out.print("<left_paren> ");
}

(\)) {
    System.out.print("<right_paren> ");
}

(\[) {
    System.out.print("<left_bracket> ");
}

(\]) {
    System.out.print("<right_bracket> ");
}

(\{) {
  System.out.print(yytext());
	bw.write("<" + "lcbrack, >");
	return new Symbol(sym.LCBRACK);
}

(\}) {
    System.out.print("<right_brace> ");
}

(\") {
    System.out.print("<quote>");
}

(:=) {
    System.out.print("<assign> ");
}

(:) {
    System.out.print("<colon> ");
}

(;) {
    System.out.print("<semicolon> ");
}

(,) {
    System.out.print("<semicolon> ");
}

(\.) {
    System.out.print("<dot>");
}


[+|-]?[0-9]*[.][0-9]+([E|e][+|-]?[0-9]+)? {
    System.out.print("<num, " + yytext() + "> ");
}

[+|-]?[0-9]+([E|e][+|-]?[0-9]+)? {
    System.out.print("<num, " + yytext() + "> ");
}

[A-Za-z][A-Za-z0-9_]* {
    System.out.print("<id, " + yytext() + "> ");
}

"(.*?)" {
    System.out.print("<string, \"" + yytext() + "\"> ");
}

. {
  System.out.println("\n\n[ERROR] Illegal character: " + yytext());
	System.exit(1);
}
