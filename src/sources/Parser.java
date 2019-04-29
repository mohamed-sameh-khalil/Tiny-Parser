package sources;

import java.util.List;

import static sources.type.*;
import static sources.NodeType.*;

public class Parser {
	static String inputFile = "scanned.txt";
	static String parserOutput = "";
	private static List<Token> tokens;
	private static int parse_index = 0;

	static Tree parse() {
		tokens = Token.parseTokens(FileUtilities.readFromFile(inputFile));
		Tree answer = new Tree();
		answer.root = program();
		System.out.println(parserOutput);
		FileUtilities.writeToFile(parserOutput, "parsed.txt");
		System.out.println(answer);
		return answer;
	}

	static private Token current() {
		if (parse_index < tokens.size())
			return tokens.get(parse_index);
		return new Token(INVALID, "");
	}

	static private String match(String matcher) {
		Token tmp = current();
		if (!tmp.value.equals(matcher))
			tmp.value = "expecting " + matcher + " " + tmp.value;
		parse_index++;
//        parserOutput += tmp.value + "\n";
		return tmp.value;
	}

	static private Node program() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		return stmt_seq();
	}

	static private Node stmt_seq() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node root = statement();
		Node current = root;
		while (current().value.equals(";")) {
			Node tmp;
			match(";");
			tmp = statement();
			current.sibling = tmp;
			current = tmp;
		}
		return root;
	}

	static private Node statement() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer;
		String choice = current().value;
		if (choice.equals("if"))
			answer = if_stmt();
		else if (choice.equals("repeat"))
			answer = repeat_stmt();
		else if (current().t == ID)
			answer = assign_stmt();
		else if (choice.equals("read"))
			answer = read_stmt();
		else if (choice.equals("write"))
			answer = write_stmt();
		else
			answer = new Node(choice + " not a statement");
		return answer;
	}

	static private Node if_stmt() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.text = match("if");
		answer.children.add(exp()); //test part
		match("then");
		answer.children.add(stmt_seq()); //then part
		if (current().value.equals("else")) {
			match("else");
			answer.children.add(stmt_seq()); //else_part
		}
		match("end");

		return answer;
	}

	static private Node repeat_stmt() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.text = match("repeat");
		answer.children.add(stmt_seq()); //body
		match("until");
		answer.children.add(exp()); //test
		return answer;
	}

	static private Node assign_stmt() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.text = "assign" + bracket(match(current().value));
		match(":=");
		answer.children.add(exp());
		return answer;
	}

	static private Node read_stmt() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.type = SQUARE;
		answer.text = match("read");
		if (current().t == ID)
			answer.text += bracket(match(current().value));
		else
			answer.text += "error expecting ID";
		return answer;
	}

	static private Node write_stmt() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.text = match("write");
		answer.children.add(exp());
		return answer;
	}

	static private Node exp() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = simple_exp(); // simple exp or term
		if (current().value.equals("<") || current().value.equals("=")) {
			Node tmp = answer;
			answer = comparison_op();
			answer.children.add(tmp);
			answer.children.add(simple_exp());//other simple exp or term
		}
		return answer;//title is comparison op or just a constant
	}

	static private Node comparison_op() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.type = CIRCLE;
		if (current().value.equals("<"))
			answer.text = "OP" + bracket(match("<"));
		else
			answer.text = "OP" + bracket(match("="));
		return answer;
	}

	static private Node simple_exp() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = term();//first term could be the root
		while (current().value.equals("+") || current().value.equals("-")) {
			Node tmp = answer;
			answer = addop();
			answer.children.add(tmp);
			answer.children.add(term());
		}
		return answer;
	}

	static private Node addop() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.type = CIRCLE;
		if (current().value.equals("+"))
			answer.text = "OP" + bracket(match("+"));
		else
			answer.text = "OP" + bracket(match("-"));
		return answer;
	}

	static private Node term() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = factor();//first factor, could be the root
		while (current().value.equals("*") || current().value.equals("/")) {
			Node tmp = answer;
			answer = mulop();
			answer.children.add(tmp);
			answer.children.add(factor());//second factor
		}
		return answer;
	}

	static private Node mulop() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.type = CIRCLE;
		if (current().value.equals("*"))
			answer.text = "OP" + bracket(match("*"));
		else
			answer.text = "OP" + bracket(match("/"));
		return answer;
	}

	static private Node factor() {
		parserOutput += "entered " + new Object() {
		}.getClass().getEnclosingMethod().getName() + "\n";
		Node answer = new Node();
		answer.type = NodeType.CIRCLE;
		if (current().t == ID) {
			answer.text = "ID" + bracket(match(current().value));
		} else if (current().t == NUMBER) {
			answer.text = "CONST" + bracket(match(current().value));

		} else {
			match("(");
			answer = exp();//exp should handle the root
			match("(");
		}
		return answer;
	}

	static private String bracket(String str) {
		return "\n(" + str + ")";
	}

}
