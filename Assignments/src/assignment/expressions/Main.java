package assignment.expressions;

import java.io.*;
import java.util.*;

/**
 * This class invokes all the functions on Expressions based on user'd choice
 * @author Abyeti Technologies
 *
 */
public class Main {


	/**
	 * This method checks if expression for correctness.
	 * Rules are:
	 * -an operator can only have ) or any letter or digit on its left hand side 
	 * -an operator can only have ( or any letter or digit on its right hand side
	 * -a floating point number is allowed
	 * -equal number of ( and ) brackets r supposed to be there
	 * -the last character can be a ) or a letter or a digit
	 * - the begining character can be ( or a letter or digit. 
	 * @return
	 */
	private int checkExpression(String expression) {
		int len = expression.length();
		int count = 0, flag = 0;
		for (int i = 0; i < len; i++) {
			char c = expression.charAt(i);

			if (c == '(')
				count++;
			else if (c == ')')
				count--;

			else if (c != '+' && c != '-' && c != '*' && c != '/' && c!='.'
					&& !Character.isDigit(c) && !Character.isLetter(c)) {
				System.out.println("error");
				flag = 1;
				break;
			} else {
				if (i == 0 && !Character.isDigit(c) && !Character.isLetter(c)
						&& c != '(') {
					flag = 1;
					break;
				} else if (i != len - 1 && i != 0
						&& (c == '+' || c == '-' || c == '*' || c == '/')) {
					char x = expression.charAt(i + 1), y = expression
							.charAt(i - 1);
					if ((x != '(' && !Character.isLetter(x) && !Character
							.isDigit(x))
							&& (y != '(' && !Character.isLetter(y) && !Character
									.isDigit(y))) {
						flag = 1;
						break;
					}
				} else if (i == len - 1 && !Character.isDigit(c)
						&& !Character.isLetter(c) && c != ')') {
					flag = 1;
					break;
				}
			}
		}

		if (count != 0 || flag == 1)
			return 0;
		else
			return 1;

	}

	/**
	 * This method reads the expression name followed by the expression string
	 * from the standard input. It validates the expression for ...... and
	 * accepts only if it is valid.
	 * 
	 * @throws IOException
	 *             if there is a read error.
	 */
	private String[] readExpression() throws IOException {
		int valid;
		String[] data = new String[2];
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		do {
			System.out.println("WHAT WUD U CALL UR EXPRESSION?");
			data[0] = br.readLine();
			System.out
					.println("EXPRESSION SYNTAX IS? \n use only +,-,/,* in ur expression");
			data[1] = br.readLine();
			valid = checkExpression(data[1]);
		} while (valid == 0);
		return data;
	}
	
	/**
	 * This method accepts the values of the variables and returns it as a hash map
	 * @param variableList
	 * @return
	 */
	private HashMap<String, Double> readVariables(String[] variableList) {
		HashMap<String,Double> variableValues=new HashMap<String,Double>();
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		double value=0;
		int i=0;
		for(;i<variableList.length;i++){
			System.out.println(variableList[i]);
			try{
				Double.valueOf(variableList[i]);
			}catch(NumberFormatException e){
				System.out.println("Enter the values of "+variableList[i]);
				try{
				String s=br.readLine();
				value=Double.valueOf(s);
				}
				catch(NumberFormatException e1){
					System.out.println(e.getMessage());
					e.printStackTrace();
					return null;
				}catch(IOException e1){
					System.out.println(e.getMessage());
					e.printStackTrace();
					return null;
				}
				variableValues.put(variableList[i], value);
				
			}
		}
		return variableValues;
	}
	
	/**
	 * This method provides the user with options and depending on the user's
	 *  input it either define's a new expresion, evaluates defined expression or exits.
	 */
	public void options() throws IOException {
		int choice;
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		Expression expressionObject = new Expression();
		IExpressionParser sParser = new SimpleExpressionParser();
		IExpressionPersister jPersister = new JSONExpressionPersister();
		IExpressionEvaluater sEvaluator = new SimpleExpressionEvaluator();
		String endChoice;
		ArrayList<String> expList=new ArrayList<String>();
		HashMap<String,Double> variableValues=new HashMap<String,Double>();
		do {
			System.out
					.println("\n1.Define Expressions\n2.Solve Defined Expression\n3.Exit\n");
			choice = Integer.parseInt(br.readLine());
			switch (choice) {
			case 1:
				String[] data = readExpression();
				expressionObject = sParser.parse(data[0],data[1]);
				jPersister.store(expressionObject);
				break;
				
			case 2:
				expList=jPersister.getExpressionList();
				for(int i=0;i<expList.size();i++){
					System.out.println(expList.get(i));
				}
				System.out.println("Choose from the above list of defined Expressions and enter its name as it is in the list.");
				String expressionName=br.readLine();
				expressionObject=jPersister.load(expressionName);
				System.out.println("\nYOU CHOSEN THE EXPRESSION -- "+expressionObject.getExpression());
				variableValues=readVariables(expressionObject.getVariables());
				double result=sEvaluator.evaluate(expressionObject,variableValues);
				System.out.println("RESULT OF EXPRESSION IS : "+result);
				break;
				
			case 3:
				System.exit(0);
			}
			System.out.println("Do you want to continue? If yes press 'y' else 'n'.");
			endChoice=br.readLine();
			
		} while (choice != 3 && endChoice.equalsIgnoreCase("y"));
	}

	public static void main(String args[]) throws IOException {
		Main instance = new Main();
		instance.options();
	}
}
