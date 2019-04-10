# Trabalho Prático de Compiladores I - 2019/1


## Analisador Léxico com JLex

Estamos usando o JLex e tentando deixar pronto para integrar com o CUP na próxima parte do trabalho. Na pasta `JLex` há o arquivo `Main.java` do JLex. Deve-se compilar o JLex antes com `javac JLex/Main.java`. Na pasta `java_cup` há os arquivos do CUP. Compile com `javac java_cup/*.java`.

A pasta `Parse` terá o analisador léxico. O arquivo onde devemos colocar a especificação do analisador léxico é o `Parse/Tiger.lex`. `Parse` é um pacote e no topo do arquivo, na parte de código do usuário, deve-se declarar que este arquivo faz parte do pacote `Parse`. Para gerar o analisador léxico, execute `java JLex.Main Parse\Tiger.lex`. O arquivo deve ser renomeado para `Yylex.java` com `mv Tiger.lex.java Yylex.java`.

### Makefile

O comando `make lex` roda (ou deveria rodar) todos os comandos para testar o analisador léxico.

- `javac JLex/Main.java`: Compila o JLex.
- `javac java_cup/Main.java`: Compila o CUP.
- `java JLex.Main Parse/Tiger.lex`: Gera o código-fonte do analisador léxico.
- `mv Parse/Tiger.lex.java Parse/Yylex.java`: Renomeia o código-fonte para ser compatível com o CUP.
- `javac Parse/Yylex.java`: Compila o analisador léxico.

O comando `make clear` apaga dos os arquivos gerados pelo `lex`.


### Problemas

Um erro do qual não sabemos a causa (ainda) está acontecendo ao tentar compilar o arquivo `Parse/Yylex.java`. É necessário rodar este arquivo, pois é com ele que o arquivo com os tokens será gerado.


## Links úteis

- [Jlex y CUP integrado en Java no YouTube](https://www.youtube.com/watch?v=fnv1RbtKHAk)


## Analisador Léxico com JFlex

Antes de tudo, instalar o [JFlex](https://www.jflex.de/download.html). Por enquanto, não vamos nos preocupar em integração com o CUP, pois sabemos que é possível. Apenas vamos colocar a especificação léxica e ver a saída. Por enquanto, podemos usar a saída padrão. Depois pensamos em trocar a saída para um arquivo.

### Makefile

O comando `make flex` roda todos os comandos para gerar e testar o analisador léxico.

- `jflex Tiger.flex`: Gera o código-fonte do analisador léxico pelo arquivo `Tiger.flex`.
- `javac Yylex.java`: Compila o código-fonte do analisador léxico.
- `java Yylex test.tig`: Aplica do analisador léxico em um código-fonte de exemplo `test.tig`.

O comando `make clear` apaga dos os arquivos `*.java` e `*.class`.