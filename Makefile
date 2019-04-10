lex:
	javac JLex/Main.java
	javac java_cup/*.java
	java JLex.Main Parse/Tiger.lex
	mv Parse/Tiger.lex.java Parse/Yylex.java
	javac Parse/Yylex.java

clean:
	rm -f JLex/*.class
	rm -f java_cup/*.class
	rm -f Parse/*.lex.java
	rm -f Parse/Yylex.java
	