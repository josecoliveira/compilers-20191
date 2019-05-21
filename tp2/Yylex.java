package lexsyn;
import java_cup.runtime.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


class Yylex implements java_cup.runtime.Scanner {
	private final int YY_BUFFER_SIZE = 512;
	private final int YY_F = -1;
	private final int YY_NO_STATE = -1;
	private final int YY_NOT_ACCEPT = 0;
	private final int YY_START = 1;
	private final int YY_END = 2;
	private final int YY_NO_ANCHOR = 4;
	private final int YY_BOL = 128;
	private final int YY_EOF = 129;

	String TOKENS_FILENAME = "tokens_list.txt";
	FileWriter fw = null;
	BufferedWriter bw =null;
	private java.io.BufferedReader yy_reader;
	private int yy_buffer_index;
	private int yy_buffer_read;
	private int yy_buffer_start;
	private int yy_buffer_end;
	private char yy_buffer[];
	private boolean yy_at_bol;
	private int yy_lexical_state;

	Yylex (java.io.Reader reader)
		throws 
	IOException

		{
		this ();
		if (null == reader) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(reader);
	}

	Yylex (java.io.InputStream instream)
		throws 
	IOException

		{
		this ();
		if (null == instream) {
			throw (new Error("Error: Bad input stream initializer."));
		}
		yy_reader = new java.io.BufferedReader(new java.io.InputStreamReader(instream));
	}

	private Yylex ()
		throws 
	IOException

		{
		yy_buffer = new char[YY_BUFFER_SIZE];
		yy_buffer_read = 0;
		yy_buffer_index = 0;
		yy_buffer_start = 0;
		yy_buffer_end = 0;
		yy_at_bol = true;
		yy_lexical_state = YYINITIAL;

	fw = new FileWriter(TOKENS_FILENAME);
	bw = new BufferedWriter(fw);
	}

	private boolean yy_eof_done = false;
	private void yy_do_eof ()
		throws 
	IOException

		{
		if (false == yy_eof_done) {

	bw.close();
	fw.close();
		}
		yy_eof_done = true;
	}
	private final int YYINITIAL = 0;
	private final int yy_state_dtrans[] = {
		0
	};
	private void yybegin (int state) {
		yy_lexical_state = state;
	}
	private int yy_advance ()
		throws java.io.IOException {
		int next_read;
		int i;
		int j;

		if (yy_buffer_index < yy_buffer_read) {
			return yy_buffer[yy_buffer_index++];
		}

		if (0 != yy_buffer_start) {
			i = yy_buffer_start;
			j = 0;
			while (i < yy_buffer_read) {
				yy_buffer[j] = yy_buffer[i];
				++i;
				++j;
			}
			yy_buffer_end = yy_buffer_end - yy_buffer_start;
			yy_buffer_start = 0;
			yy_buffer_read = j;
			yy_buffer_index = j;
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}

		while (yy_buffer_index >= yy_buffer_read) {
			if (yy_buffer_index >= yy_buffer.length) {
				yy_buffer = yy_double(yy_buffer);
			}
			next_read = yy_reader.read(yy_buffer,
					yy_buffer_read,
					yy_buffer.length - yy_buffer_read);
			if (-1 == next_read) {
				return YY_EOF;
			}
			yy_buffer_read = yy_buffer_read + next_read;
		}
		return yy_buffer[yy_buffer_index++];
	}
	private void yy_move_end () {
		if (yy_buffer_end > yy_buffer_start &&
		    '\n' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
		if (yy_buffer_end > yy_buffer_start &&
		    '\r' == yy_buffer[yy_buffer_end-1])
			yy_buffer_end--;
	}
	private boolean yy_last_was_cr=false;
	private void yy_mark_start () {
		yy_buffer_start = yy_buffer_index;
	}
	private void yy_mark_end () {
		yy_buffer_end = yy_buffer_index;
	}
	private void yy_to_mark () {
		yy_buffer_index = yy_buffer_end;
		yy_at_bol = (yy_buffer_end > yy_buffer_start) &&
		            ('\r' == yy_buffer[yy_buffer_end-1] ||
		             '\n' == yy_buffer[yy_buffer_end-1] ||
		             2028/*LS*/ == yy_buffer[yy_buffer_end-1] ||
		             2029/*PS*/ == yy_buffer[yy_buffer_end-1]);
	}
	private java.lang.String yytext () {
		return (new java.lang.String(yy_buffer,
			yy_buffer_start,
			yy_buffer_end - yy_buffer_start));
	}
	private int yylength () {
		return yy_buffer_end - yy_buffer_start;
	}
	private char[] yy_double (char buf[]) {
		int i;
		char newbuf[];
		newbuf = new char[2*buf.length];
		for (i = 0; i < buf.length; ++i) {
			newbuf[i] = buf[i];
		}
		return newbuf;
	}
	private final int YY_E_INTERNAL = 0;
	private final int YY_E_MATCH = 1;
	private java.lang.String yy_error_string[] = {
		"Error: Internal error.\n",
		"Error: Unmatched input.\n"
	};
	private void yy_error (int code,boolean fatal) {
		java.lang.System.out.print(yy_error_string[code]);
		java.lang.System.out.flush();
		if (fatal) {
			throw new Error("Fatal Error.\n");
		}
	}
	private int[][] unpackFromString(int size1, int size2, String st) {
		int colonIndex = -1;
		String lengthString;
		int sequenceLength = 0;
		int sequenceInteger = 0;

		int commaIndex;
		String workString;

		int res[][] = new int[size1][size2];
		for (int i= 0; i < size1; i++) {
			for (int j= 0; j < size2; j++) {
				if (sequenceLength != 0) {
					res[i][j] = sequenceInteger;
					sequenceLength--;
					continue;
				}
				commaIndex = st.indexOf(',');
				workString = (commaIndex==-1) ? st :
					st.substring(0, commaIndex);
				st = st.substring(commaIndex+1);
				colonIndex = workString.indexOf(':');
				if (colonIndex == -1) {
					res[i][j]=Integer.parseInt(workString);
					continue;
				}
				lengthString =
					workString.substring(colonIndex+1);
				sequenceLength=Integer.parseInt(lengthString);
				workString=workString.substring(0,colonIndex);
				sequenceInteger=Integer.parseInt(workString);
				res[i][j] = sequenceInteger;
				sequenceLength--;
			}
		}
		return res;
	}
	private int yy_acpt[] = {
		/* 0 */ YY_NOT_ACCEPT,
		/* 1 */ YY_NO_ANCHOR,
		/* 2 */ YY_NO_ANCHOR,
		/* 3 */ YY_NO_ANCHOR,
		/* 4 */ YY_NO_ANCHOR,
		/* 5 */ YY_NO_ANCHOR,
		/* 6 */ YY_NO_ANCHOR,
		/* 7 */ YY_NO_ANCHOR,
		/* 8 */ YY_NO_ANCHOR,
		/* 9 */ YY_NO_ANCHOR,
		/* 10 */ YY_NO_ANCHOR,
		/* 11 */ YY_NO_ANCHOR,
		/* 12 */ YY_NO_ANCHOR,
		/* 13 */ YY_NO_ANCHOR,
		/* 14 */ YY_NO_ANCHOR,
		/* 15 */ YY_NO_ANCHOR,
		/* 16 */ YY_NO_ANCHOR,
		/* 17 */ YY_NO_ANCHOR,
		/* 18 */ YY_NO_ANCHOR,
		/* 19 */ YY_NO_ANCHOR,
		/* 20 */ YY_NO_ANCHOR,
		/* 21 */ YY_NO_ANCHOR,
		/* 22 */ YY_NO_ANCHOR,
		/* 23 */ YY_NO_ANCHOR,
		/* 24 */ YY_NO_ANCHOR,
		/* 25 */ YY_NO_ANCHOR,
		/* 26 */ YY_NO_ANCHOR,
		/* 27 */ YY_NO_ANCHOR,
		/* 28 */ YY_NO_ANCHOR,
		/* 29 */ YY_NO_ANCHOR,
		/* 30 */ YY_NO_ANCHOR,
		/* 31 */ YY_NO_ANCHOR,
		/* 32 */ YY_NO_ANCHOR,
		/* 33 */ YY_NO_ANCHOR,
		/* 34 */ YY_NO_ANCHOR,
		/* 35 */ YY_NO_ANCHOR,
		/* 36 */ YY_NO_ANCHOR,
		/* 37 */ YY_NO_ANCHOR,
		/* 38 */ YY_NO_ANCHOR,
		/* 39 */ YY_NO_ANCHOR,
		/* 40 */ YY_NO_ANCHOR,
		/* 41 */ YY_NO_ANCHOR,
		/* 42 */ YY_NO_ANCHOR,
		/* 43 */ YY_NO_ANCHOR,
		/* 44 */ YY_NO_ANCHOR,
		/* 45 */ YY_NO_ANCHOR,
		/* 46 */ YY_NO_ANCHOR,
		/* 47 */ YY_NO_ANCHOR,
		/* 48 */ YY_NO_ANCHOR,
		/* 49 */ YY_NO_ANCHOR,
		/* 50 */ YY_NO_ANCHOR,
		/* 51 */ YY_NO_ANCHOR,
		/* 52 */ YY_NO_ANCHOR,
		/* 53 */ YY_NO_ANCHOR,
		/* 54 */ YY_NOT_ACCEPT,
		/* 55 */ YY_NO_ANCHOR,
		/* 56 */ YY_NO_ANCHOR,
		/* 57 */ YY_NO_ANCHOR,
		/* 58 */ YY_NOT_ACCEPT,
		/* 59 */ YY_NO_ANCHOR,
		/* 60 */ YY_NOT_ACCEPT,
		/* 61 */ YY_NO_ANCHOR,
		/* 62 */ YY_NOT_ACCEPT,
		/* 63 */ YY_NO_ANCHOR,
		/* 64 */ YY_NOT_ACCEPT,
		/* 65 */ YY_NO_ANCHOR,
		/* 66 */ YY_NOT_ACCEPT,
		/* 67 */ YY_NO_ANCHOR,
		/* 68 */ YY_NOT_ACCEPT,
		/* 69 */ YY_NO_ANCHOR,
		/* 70 */ YY_NOT_ACCEPT,
		/* 71 */ YY_NO_ANCHOR,
		/* 72 */ YY_NO_ANCHOR,
		/* 73 */ YY_NO_ANCHOR,
		/* 74 */ YY_NO_ANCHOR,
		/* 75 */ YY_NO_ANCHOR,
		/* 76 */ YY_NO_ANCHOR,
		/* 77 */ YY_NO_ANCHOR,
		/* 78 */ YY_NO_ANCHOR,
		/* 79 */ YY_NO_ANCHOR,
		/* 80 */ YY_NO_ANCHOR,
		/* 81 */ YY_NO_ANCHOR,
		/* 82 */ YY_NO_ANCHOR,
		/* 83 */ YY_NO_ANCHOR,
		/* 84 */ YY_NO_ANCHOR,
		/* 85 */ YY_NO_ANCHOR,
		/* 86 */ YY_NO_ANCHOR,
		/* 87 */ YY_NO_ANCHOR,
		/* 88 */ YY_NO_ANCHOR,
		/* 89 */ YY_NO_ANCHOR,
		/* 90 */ YY_NO_ANCHOR,
		/* 91 */ YY_NO_ANCHOR,
		/* 92 */ YY_NO_ANCHOR,
		/* 93 */ YY_NO_ANCHOR,
		/* 94 */ YY_NO_ANCHOR,
		/* 95 */ YY_NO_ANCHOR,
		/* 96 */ YY_NO_ANCHOR,
		/* 97 */ YY_NO_ANCHOR,
		/* 98 */ YY_NO_ANCHOR,
		/* 99 */ YY_NO_ANCHOR,
		/* 100 */ YY_NO_ANCHOR,
		/* 101 */ YY_NO_ANCHOR,
		/* 102 */ YY_NO_ANCHOR,
		/* 103 */ YY_NO_ANCHOR,
		/* 104 */ YY_NO_ANCHOR,
		/* 105 */ YY_NO_ANCHOR,
		/* 106 */ YY_NO_ANCHOR,
		/* 107 */ YY_NO_ANCHOR,
		/* 108 */ YY_NO_ANCHOR,
		/* 109 */ YY_NO_ANCHOR,
		/* 110 */ YY_NO_ANCHOR,
		/* 111 */ YY_NO_ANCHOR,
		/* 112 */ YY_NO_ANCHOR
	};
	private int yy_cmap[] = unpackFromString(1,130,
"49:9,1,2,49,1:2,49:18,1,49,39,49:3,31,49,33,34,29,27,42,28,43,30,44:10,40,4" +
"1,25,24,26,48,49,46:4,45,46:21,35,49,36,49,47,49,16,15,22,12,9,6,23,8,4,46," +
"17,5,46,3,13,19,46,14,10,7,21,20,11,46,18,46,37,32,38,49:2,0:2")[0];

	private int yy_rmap[] = unpackFromString(1,113,
"0,1:3,2,1,3,4,5:2,1:3,5,6,1:6,7,1:2,8,9,1,10,11:4,1:4,12,11:14,1,11:2,8,13," +
"14,15,16,17,18,19,20,21,22,23,14,24,25,26,15,27,28,29,30,31,32,33,34,35,36," +
"37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,11,60," +
"61,62,63,64,65,66,67")[0];

	private int yy_nxt[][] = unpackFromString(68,50,
"1,2,3,4,55,97,102,59,104,106,107,108,61,63,104,109,110,104:3,111,104,112,10" +
"4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,104:2,26:3,-1:5" +
"3,104,65,104:19,-1:20,104:4,-1:26,32,-1,33,-1:47,34,-1:68,54,25,-1:48,58,-1" +
":30,35,-1:69,36,-1:14,60,-1:22,60,-1:10,54,25,60,-1:7,104:4,38,104:16,-1:20" +
",104:4,-1:5,104:21,-1:20,104:4,-1:11,64,-1:22,64,-1:11,36,64,-1:7,27,104:2," +
"28,104:17,-1:20,104:4,-1:46,56,-1:49,57,-1:34,62,-1:23,104:5,73,104:4,29,10" +
"4:4,74,104:5,-1:20,104:4,-1:29,66:2,-1:3,66,-1:11,56,-1:8,104:10,30,104:10," +
"-1:20,104:4,-1:50,68,-1:4,104:3,31,104:17,-1:20,104:4,-1:29,70:2,-1:3,70,-1" +
":11,57,-1:8,104:2,37,104:18,-1:20,104:4,-1:5,104:4,39,104:16,-1:20,104:4,-1" +
":36,51,-1:18,104:10,101,104:10,-1:20,104:4,-1:5,104:11,40,104:9,-1:20,104:4" +
",-1:5,81,104:20,-1:20,104:4,-1:5,104:6,82,104:14,-1:20,104:4,-1:5,104:16,83" +
",104:4,-1:20,104:4,-1:5,104:9,41,104:11,-1:20,104:4,-1:5,104:7,84,104:13,-1" +
":20,104:4,-1:5,104:11,100,104:9,-1:20,104:4,-1:5,104,85,104:19,-1:20,104:4," +
"-1:5,104:11,42,104:9,-1:20,104:4,-1:5,104:13,86,104:7,-1:20,104:4,-1:5,104:" +
"19,88,104,-1:20,104:4,-1:5,43,104:20,-1:20,104:4,-1:5,104:6,44,104:14,-1:20" +
",104:4,-1:5,104:6,45,104:14,-1:20,104:4,-1:5,104:2,90,104:18,-1:20,104:4,-1" +
":5,104:11,46,104:9,-1:20,104:4,-1:5,104:4,47,104:16,-1:20,104:4,-1:5,104:4," +
"93,104:16,-1:20,104:4,-1:5,94,104:20,-1:20,104:4,-1:5,104:6,48,104:14,-1:20" +
",104:4,-1:5,104:14,49,104:6,-1:20,104:4,-1:5,104:15,50,104:5,-1:20,104:4,-1" +
":5,104,95,104:19,-1:20,104:4,-1:5,104:20,52,-1:20,104:4,-1:5,104:10,96,104:" +
"10,-1:20,104:4,-1:5,53,104:20,-1:20,104:4,-1:5,104:6,67,104:14,-1:20,104:4," +
"-1:5,104:6,103,104:14,-1:20,104:4,-1:5,104:11,105,104:9,-1:20,104:4,-1:5,10" +
"4,89,104:19,-1:20,104:4,-1:5,104:13,87,104:7,-1:20,104:4,-1:5,104:2,69,104:" +
"7,71,104:7,72,104:2,-1:20,104:4,-1:5,104:13,91,104:7,-1:20,104:4,-1:5,104:1" +
"3,92,104:7,-1:20,104:4,-1:5,75,104,76,104:18,-1:20,104:4,-1:5,104:4,77,104:" +
"16,-1:20,104:4,-1:5,104:5,78,104:15,-1:20,104:4,-1:5,104:11,98,104:9,-1:20," +
"104:4,-1:5,104:11,99,104:9,-1:20,104:4,-1:5,104:13,79,104:7,-1:20,104:4,-1:" +
"5,104:5,80,104:15,-1:20,104:4,-1:2");

	public java_cup.runtime.Symbol next_token ()
		throws java.io.IOException {
		int yy_lookahead;
		int yy_anchor = YY_NO_ANCHOR;
		int yy_state = yy_state_dtrans[yy_lexical_state];
		int yy_next_state = YY_NO_STATE;
		int yy_last_accept_state = YY_NO_STATE;
		boolean yy_initial = true;
		int yy_this_accept;

		yy_mark_start();
		yy_this_accept = yy_acpt[yy_state];
		if (YY_NOT_ACCEPT != yy_this_accept) {
			yy_last_accept_state = yy_state;
			yy_mark_end();
		}
		while (true) {
			if (yy_initial && yy_at_bol) yy_lookahead = YY_BOL;
			else yy_lookahead = yy_advance();
			yy_next_state = YY_F;
			yy_next_state = yy_nxt[yy_rmap[yy_state]][yy_cmap[yy_lookahead]];
			if (YY_EOF == yy_lookahead && true == yy_initial) {
				yy_do_eof();
				return null;
			}
			if (YY_F != yy_next_state) {
				yy_state = yy_next_state;
				yy_initial = false;
				yy_this_accept = yy_acpt[yy_state];
				if (YY_NOT_ACCEPT != yy_this_accept) {
					yy_last_accept_state = yy_state;
					yy_mark_end();
				}
			}
			else {
				if (YY_NO_STATE == yy_last_accept_state) {
					throw (new Error("Lexical Error: Unmatched Input."));
				}
				else {
					yy_anchor = yy_acpt[yy_last_accept_state];
					if (0 != (YY_END & yy_anchor)) {
						yy_move_end();
					}
					yy_to_mark();
					switch (yy_last_accept_state) {
					case 1:
						
					case -2:
						break;
					case 2:
						{
	System.out.print(yytext());
}
					case -3:
						break;
					case 3:
						{
  System.out.print(yytext());
	bw.write(yytext());
}
					case -4:
						break;
					case 4:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -5:
						break;
					case 5:
						{
    System.out.print("<eq_comp> ");
}
					case -6:
						break;
					case 6:
						{
    System.out.print("<lt_comp> ");
}
					case -7:
						break;
					case 7:
						{
    System.out.print("<gt_comp> ");
}
					case -8:
						break;
					case 8:
						{
    System.out.print("<plus_comp> ");
}
					case -9:
						break;
					case 9:
						{
    System.out.print("<minus_comp> ");
}
					case -10:
						break;
					case 10:
						{
    System.out.print("<multi_comp> ");
}
					case -11:
						break;
					case 11:
						{
    System.out.print("<div_comp> ");
}
					case -12:
						break;
					case 12:
						{
    System.out.print("<and_bool> ");
}
					case -13:
						break;
					case 13:
						{
    System.out.print("<or_bool> ");
}
					case -14:
						break;
					case 14:
						{
    System.out.print("<left_paren> ");
}
					case -15:
						break;
					case 15:
						{
    System.out.print("<right_paren> ");
}
					case -16:
						break;
					case 16:
						{
    System.out.print("<left_bracket> ");
}
					case -17:
						break;
					case 17:
						{
    System.out.print("<right_bracket> ");
}
					case -18:
						break;
					case 18:
						{
  System.out.print(yytext());
	bw.write("<" + "lcbrack, >");
	return new Symbol(sym.LCBRACK);
}
					case -19:
						break;
					case 19:
						{
    System.out.print("<right_brace> ");
}
					case -20:
						break;
					case 20:
						{
    System.out.print("<quote>");
}
					case -21:
						break;
					case 21:
						{
    System.out.print("<colon> ");
}
					case -22:
						break;
					case 22:
						{
    System.out.print("<semicolon> ");
}
					case -23:
						break;
					case 23:
						{
    System.out.print("<semicolon> ");
}
					case -24:
						break;
					case 24:
						{
    System.out.print("<dot>");
}
					case -25:
						break;
					case 25:
						{
    System.out.print("<num, " + yytext() + "> ");
}
					case -26:
						break;
					case 26:
						{
  System.out.println("\n\n[ERROR] Illegal character: " + yytext());
	System.exit(1);
}
					case -27:
						break;
					case 27:
						{
    System.out.print("<in> ");
}
					case -28:
						break;
					case 28:
						{
  System.out.print(yytext());
	bw.write("<" + "if, >");
	return new Symbol(sym.IF);
}
					case -29:
						break;
					case 29:
						{
    System.out.print("<to> ");
}
					case -30:
						break;
					case 30:
						{
    System.out.print("<do> ");
}
					case -31:
						break;
					case 31:
						{
    System.out.print("<of> ");
}
					case -32:
						break;
					case 32:
						{
    System.out.print("<le_comp> ");
}
					case -33:
						break;
					case 33:
						{
    System.out.print("<neq_comp> ");
}
					case -34:
						break;
					case 34:
						{
    System.out.print("<ge_comp> ");
}
					case -35:
						break;
					case 35:
						{
    System.out.print("<assign> ");
}
					case -36:
						break;
					case 36:
						{
    System.out.print("<num, " + yytext() + "> ");
}
					case -37:
						break;
					case 37:
						{
    System.out.print("<nil> ");
}
					case -38:
						break;
					case 38:
						{
    System.out.print("<type, \"" + yytext() + "\"> ");
}
					case -39:
						break;
					case 39:
						{
    System.out.print("<let> ");
}
					case -40:
						break;
					case 40:
						{
    System.out.print("<for> ");
}
					case -41:
						break;
					case 41:
						{
    System.out.print("<end> ");
}
					case -42:
						break;
					case 42:
						{
    System.out.print("<var> ");
}
					case -43:
						break;
					case 43:
						{
    System.out.print("<then> ");
}
					case -44:
						break;
					case 44:
						{
    System.out.print("<type> ");
}
					case -45:
						break;
					case 45:
						{
    System.out.print("<else> ");
}
					case -46:
						break;
					case 46:
						{
    System.out.print("<type, \"" + yytext() + "\"> ");
}
					case -47:
						break;
					case 47:
						{
    System.out.print("<type, \"" + yytext() + "\"> ");
}
					case -48:
						break;
					case 48:
						{
    System.out.print("<while> ");
}
					case -49:
						break;
					case 49:
						{
    System.out.print("<break> ");
}
					case -50:
						break;
					case 50:
						{
    System.out.print("<array> ");
}
					case -51:
						break;
					case 51:
						{
    System.out.print("<string, \"" + yytext() + "\"> ");
}
					case -52:
						break;
					case 52:
						{
    System.out.print("<type, \"" + yytext() + "\"> ");
}
					case -53:
						break;
					case 53:
						{
    System.out.print("<function> ");
}
					case -54:
						break;
					case 55:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -55:
						break;
					case 56:
						{
    System.out.print("<num, " + yytext() + "> ");
}
					case -56:
						break;
					case 57:
						{
    System.out.print("<num, " + yytext() + "> ");
}
					case -57:
						break;
					case 59:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -58:
						break;
					case 61:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -59:
						break;
					case 63:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -60:
						break;
					case 65:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -61:
						break;
					case 67:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -62:
						break;
					case 69:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -63:
						break;
					case 71:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -64:
						break;
					case 72:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -65:
						break;
					case 73:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -66:
						break;
					case 74:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -67:
						break;
					case 75:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -68:
						break;
					case 76:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -69:
						break;
					case 77:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -70:
						break;
					case 78:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -71:
						break;
					case 79:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -72:
						break;
					case 80:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -73:
						break;
					case 81:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -74:
						break;
					case 82:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -75:
						break;
					case 83:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -76:
						break;
					case 84:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -77:
						break;
					case 85:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -78:
						break;
					case 86:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -79:
						break;
					case 87:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -80:
						break;
					case 88:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -81:
						break;
					case 89:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -82:
						break;
					case 90:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -83:
						break;
					case 91:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -84:
						break;
					case 92:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -85:
						break;
					case 93:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -86:
						break;
					case 94:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -87:
						break;
					case 95:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -88:
						break;
					case 96:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -89:
						break;
					case 97:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -90:
						break;
					case 98:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -91:
						break;
					case 99:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -92:
						break;
					case 100:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -93:
						break;
					case 101:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -94:
						break;
					case 102:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -95:
						break;
					case 103:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -96:
						break;
					case 104:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -97:
						break;
					case 105:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -98:
						break;
					case 106:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -99:
						break;
					case 107:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -100:
						break;
					case 108:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -101:
						break;
					case 109:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -102:
						break;
					case 110:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -103:
						break;
					case 111:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -104:
						break;
					case 112:
						{
    System.out.print("<id, " + yytext() + "> ");
}
					case -105:
						break;
					default:
						yy_error(YY_E_INTERNAL,false);
					case -1:
					}
					yy_initial = true;
					yy_state = yy_state_dtrans[yy_lexical_state];
					yy_next_state = YY_NO_STATE;
					yy_last_accept_state = YY_NO_STATE;
					yy_mark_start();
					yy_this_accept = yy_acpt[yy_state];
					if (YY_NOT_ACCEPT != yy_this_accept) {
						yy_last_accept_state = yy_state;
						yy_mark_end();
					}
				}
			}
		}
	}
}
