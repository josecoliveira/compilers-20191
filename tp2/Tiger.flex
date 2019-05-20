import java_cup.runtime.*;
import java.io.BufferedWriter;
import java.io.FileWriter;

%%
%public
%class Scanner
%implements sym

%line
%column

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

%eof{
	bw.close();
	fw.close();
%eof}

%%

// Ignore
[ \t\r\f] {}

// New line
[\n] {
    System.out.print("\n");
}

// Keywords
nil {
    System.out.print("<nil> ");
}

if {
    System.out.print("<if> ");
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

// Types
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

// Comparison Operators
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

// Arithmetic Operators
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

// Boolean Operators
(&) {
    System.out.print("<and_bool> ");
}

(\|) {
    System.out.print("<or_bool> ");
}

// Parentheses
(\() {
    System.out.print("<left_paren> ");
}

(\)) {
    System.out.print("<right_paren> ");
}

// Brackets
(\[) {
    System.out.print("<left_bracket> ");
}

(\]) {
    System.out.print("<right_bracket> ");
}

// Braces
(\{) {
    System.out.print("<left_brace> ");
}

(\}) {
    System.out.print("<right_brace> ");
}

// Quotes
(\") {
    System.out.print("<quote>");
}

// Others
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


// Number
[+|-]?[0-9]*[.][0-9]+([E|e][+|-]?[0-9]+)? {
    System.out.print("<num, " + yytext() + "> ");
}

[+|-]?[0-9]+([E|e][+|-]?[0-9]+)? {
    System.out.print("<num, " + yytext() + "> ");
}

// Id
[A-Za-z][A-Za-z0-9_]* {
    System.out.print("<id, " + yytext() + "> ");
}

// String
"(.*?)" {
    System.out.print("<string, \"" + yytext() + "\"> ");
}

// Error
. {
    System.out.print("Error: Illegal pattern: \"" + yytext() + "\" at " + yyline + ":" + yycolumn + "\n");
    System.exit(1);
}
