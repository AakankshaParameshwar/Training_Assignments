package assignment.expressions;


import java.util.ArrayList;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * A class that implements a expression parser interface
 * @author Abyeti Technologies
 *
 */
public class SimpleExpressionParser implements IExpressionParser {

	/**
	 * This method takes an operator and returns its precedence.
	 * @param ch
	 * @returns precedence
	 */
	private int getPrecedence(char ch) {
		switch (ch) {
		case '+':
		case '-':
			return 2;
		case '*':
			return 3;
		case '/':
			return 4;
		case '(':
		case ')':
			return 1;
		default:
			return 0;
		}
	}
	
	/**
	 * This method parses an expression and converts it to an Expression Tree.
	 * It converts the expression from infix to prefix form.
	 * stack- 		contains the operators.
	 * output-		contains the prefix form of expression.
	 * nodeStack-	contains the nodes, which are popped out and connected to a 
	 * 				node representing an operator, whenever an operator is encountered. 	
	 * */
	@Override
	public Expression parse(String name, String exp) {
		ArrayList<String> tokens = new ArrayList<String>();
		String splitExp = "[+-/*()]";
		StringTokenizer st = new StringTokenizer(exp, splitExp);
		while (st.hasMoreElements()) {
			tokens.add(st.nextToken());
		}
		Expression expressionObject = new Expression();
		StringBuffer output = new StringBuffer();
		
		Stack<ExpressionNode> nodeStack = new Stack<ExpressionNode>();
		Stack<Character> stack = new Stack<Character>();
		stack.push('(');
		try{
		for (int i = exp.length() - 1; i >= 0;) {
			
			char c = exp.charAt(i);
			if (c == ')') {
				System.out.println("i-"+i+"\tc-"+c+"\t-----)");
				stack.push('(');
				i--;
			} else if(Character.isDigit(c) || Character.isLetter(c)){
				StringBuffer variable = new StringBuffer();
				while (c != '+' && c != '-' && c != '*' && c != '/'&& c!='(' && c!=')'){
					System.out.println("i-"+i+"\tc-"+c+"\t-----char");
					variable.append(c);
					if(i==0){
						i--;
						break;
					}
						i--;
						c = exp.charAt(i);
				}
				String s=variable.reverse().toString();
				if (tokens.contains(s)) {
					int k = tokens.indexOf(s);
					String token = tokens.get(k);
					System.out.println("token="+token);
					output.append(token);
					ExpressionNode n = new ExpressionNode();
					n.setData(token);
					nodeStack.push(n);
				}
				int l=variable.length();
				variable.delete(0, l-1);
			}else if (c == '(') {
				System.out.println("i-"+i+"\tc-"+c+"\t-----(");
				char popped = stack.pop();
				i--;
				while (popped != '(') {
					output.append(popped);
					ExpressionNode n = new ExpressionNode();
					nodeStack.push(expressionObject.insertExpressionNode(n, popped,
							nodeStack.pop(), nodeStack.pop()));
					popped = stack.pop();
				}
			} else if (c == '+' || c == '-' || c == '*' || c == '/') {
				System.out.println("i-"+i+"\tc-"+c+"\t-----operator");
				i--;
				while (getPrecedence(stack.peek()) >= getPrecedence(c)) {
					char popped = stack.pop();
					output.append(popped);
					ExpressionNode n = new ExpressionNode();
					nodeStack.push(expressionObject.insertExpressionNode(n, popped,
							nodeStack.pop(), nodeStack.pop()));
				}
				stack.push(c);
			} 
			System.out.println(c+"\t"+output+"\t"+stack+"\t"+nodeStack);

		}

		while (!stack.empty()) {
			char popped = stack.pop();
			if (popped != '(') {
				output.append(popped);
				ExpressionNode n = new ExpressionNode();
				nodeStack.push(expressionObject.insertExpressionNode(n, popped,
						nodeStack.pop(), nodeStack.pop()));
			}
		}
		
		int uniqueCount=0;
		ArrayList<String> variables=new ArrayList<String>();
		for (int j=0;j<tokens.size();j++){
			String p=tokens.get(j);
			String s=tokens.get(tokens.indexOf(p));
			if(!variables.contains(s)){
				uniqueCount++;
				variables.add(s);
			}
		}
		String[] a=new String[uniqueCount];
		variables.trimToSize();
		variables.toArray(a);
		for(int o=0;o<a.length;o++){
			System.out.println(a[o]);
		}
		expressionObject.setExpressionTree(nodeStack.pop());
		expressionObject.setExpression(exp);
		expressionObject.setVariables(a);
		expressionObject.setName(name);
		ExpressionNode p = new ExpressionNode();
		p.setData("z");
		expressionObject.displayExpressionTree(p, expressionObject.getExpressionTree());
		
		return expressionObject;
		
		}
		catch(NullPointerException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		catch(StringIndexOutOfBoundsException e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		}
	}

}
