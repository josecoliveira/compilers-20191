%%

%standalone
%class Teste

%{
    int numA = 0;
%}

%eof{
    System.out.println("Numeros de A's = " + numA); 
%eof}

%%

[a|A] { numA++; System.out.println("A"); }
[ \n\t\r] {}
((0|1)*) { System.out.println("Bin"); }
. {}
