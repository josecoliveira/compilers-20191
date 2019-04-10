%%

%standalone

%eof{
    // System.out.println("Numeros de A's = " + numA); 
%eof}

%%

// Numeral
[+|-]?[0-9]*[.][0-9]+([E|e][+|-]?[0-9]+)? {
	System.out.print(yytext());
}

[ \n\t\r] {}

. {}
