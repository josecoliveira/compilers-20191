compile: flex cup

flex:
	jflex-1.7.0/bin/jflex Tiger.flex
	javac Yylex.java
	java Yylex test.tig

cup:
	java -jar java_cup/java-cup-11b.jar -interface -parser parser *.cup
	javac -d . -cp java_cup/java-cup-11b-runtime.jar sym.java Yylex.java

# flex dump:
# 	jflex --dump Tiger.flex
# 	javac Yylex.java
# 	java Yylex test.tig

clear:
	rm -f *.class
	rm -f *.class~
	rm -f *.java
	rm -f *.java~
