package Parse;

import java_cup.runtime.Symbol;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

%%

%type int

%{
%}

%eofval{
    {
        // return tok(sym.EOF, null);
    }
%eofval}


%%


" " {System.out.print("space");}