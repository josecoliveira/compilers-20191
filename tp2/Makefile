run:
	javac JLex/Main.java
	java JLex.Main *.lex
	mv *.lex.java Yylex.java
	java -jar java_cup/java-cup-11b.jar -interface -parser parser *.cup
	javac -d . -cp java_cup/java-cup-11b-runtime.jar *.java
	java -cp java_cup/java-cup-11b-runtime.jar:. lexsyn.parser < test.tig

# run:
# 	jflex-1.7.0/bin/jflex Tiger.flex
# 	java -cp .:java_cup/java-cup-11b.jar java_cup.Main  < Tiger.cup;
# 	javac -cp .:java_cup/java-cup-11b.jar *.java;

# compile: flex cup

# flex:
# 	jflex-1.7.0/bin/jflex Tiger.flex
# 	javac Scanner.java
# 	java Scanner test.tig

# cup:
# 	java -jar java_cup/java-cup-11b.jar -interface -parser parser *.cup
# 	javac -d . -cp java_cup/java-cup-11b-runtime.jar *.java

# flex dump:
# 	jflex --dump Tiger.flex
# 	javac Yylex.java
# 	java Yylex test.tig

clear:
	rm -f *.class
	rm -f *.class~
	rm -f *.java
	rm -f *.java~
