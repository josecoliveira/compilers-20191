package Parse;

import java_cup.runtime.Symbol;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


%%


%{
    private java_cup.runtime.Symbol tok(int kind, Object value) {
        return new java_cup.runtime.Symbol(kind, yychar, yychar+yylength(), value);
    }
%}

%eofval{
    {
        return tok(sym.EOF, null);
    }
%eofval}


%%


" " {}

\n {newline();}

"," {return tok(sym.VIRG, null);}
