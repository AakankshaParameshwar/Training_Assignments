package assignment.expressions;

import java.util.Map;
import java.util.Stack;

public class SimpleExpressionEvaluator implements IExpressionEvaluater {
	private double result;
	private ExpressionNode modifiedTree;
	private Stack<Double> valueStack = new Stack<Double>();
	
	/**
	 * Method to evaluate an expression and print the result and the modified tree
	 */
	@Override
	public double evaluate(Expression exp,Map<String, Double> map){
		modifiedTree=postOrder(exp.getExpressionTree(),map);
		result=valueStack.pop();
		ExpressionNode p = new ExpressionNode();
		p.setData("z");
		exp.displayExpressionTree(p, modifiedTree);
		return result;
	}
	
	/**
	 * Method to traverse through the tree in post order and modify the data stored
	 * in the tree( variables)  with the values and evaluate the expression based on 
	 * the operators encountered during traversal.
	 * @param root
	 * @param map
	 * @return
	 */
	public ExpressionNode postOrder(ExpressionNode root, Map<String, Double> map) {
		if(root !=null){
			postOrder(root.getLeft(), map);
			postOrder(root.getRight(), map);
			String key=root.getData();
			System.out.println(key);
			if(key.equals("+")||key.equals("-")||key.equals("*") || key.equals("/")){
				double y=valueStack.pop();
				double x=valueStack.pop();
				//System.out.println("rootDATA-- "+key+"  "+x+"  "+y);
				switch(key){
				case "+":
					valueStack.push(x+y);
					break;
				case "-":
					valueStack.push(x-y);
					break;
				case "*":
					valueStack.push(x*y);
					break;
				case "/":
					valueStack.push(x/y);
					break;
				}
				//System.out.println(valueStack);
			}
			else if(map.containsKey(key)){
				double value=map.get(key);
				valueStack.push(value);
				root.setData(String.valueOf(value));
				//System.out.println(valueStack);
				}
			else if(!map.containsKey(key)){
				double constant=Double.valueOf(key);
				valueStack.push(constant);
				//System.out.println(valueStack);
			}
			return root;
		}else
			return root;
	}

}
