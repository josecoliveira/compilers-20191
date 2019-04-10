# Trabalho Prático de Compiladores I - 2019/1


# Analisador Léxico

Estamos usando o JLex e tentando deixar pronto para integrar com o CUP na próxima parte do trabalho. Na pasta `JLex` há o arquivo `Main.java` do JLex. Deve-se compilar o JLex antes com `javac JLex/Main.java`. Na pasta `java_cup` há os arquivos do CUP. Compile com `javac java_cup/*.java`.

A pasta `Parse` terá o analisador léxico. O arquivo onde devemos colocar a especificação do analisador léxico é o `Parse/Tiger.lex`. `Parse` é um pacote e no topo do arquivo, na parte de código do usuário, deve-se declarar que este arquivo faz parte do pacote `Parse`. Para gerar o analisador léxico, execute `java JLex.Main Parse\Tiger.lex`. O arquivo deve ser renomeado para `Yylex.java` com `mv Tiger.lex.java Yylex.java`.


# Links úteis

- [Jlex y CUP integrado en Java no YouTube](https://www.youtube.com/watch?v=fnv1RbtKHAk)