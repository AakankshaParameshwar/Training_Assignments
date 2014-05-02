package assignment.expressions;

/**
 * Class contains the info abt an expression object
 * @author Abyeti Technologies
 *
 */
public class Expression {
	private String name;
	private String expression;
	private String[] variables;
	private ExpressionNode expressionTree;
	
	/**
	 * Method to insert a node to expression tree
	 * @param current
	 * @param data
	 * @param L
	 * @param R
	 * @return
	 */
	public ExpressionNode insertExpressionNode(ExpressionNode current, char data, ExpressionNode L, ExpressionNode R) {
		current.setData(String.valueOf(data));
		current.setLeft(L);
		current.setRight(R);
		return current;
	}
	
	/**
	 * Method to print the expression tree.
	 * @param parent
	 * @param root
	 */
	public void displayExpressionTree(ExpressionNode parent, ExpressionNode root) {
		if (root != null) {
			System.out.println(parent.getData() + "\t"+ root.getData());
			displayExpressionTree(root, root.getLeft());
			displayExpressionTree(root, root.getRight());
		} else
			return;
	}
	
	public String getExpression(){
		return this.expression;
	}
	public void setExpression(String exp){
		this.expression=exp;
	}
	public String[] getVariables(){
		return this.variables;
	}
	public void setVariables(String[] s){
		this.variables=s;
	}
	public String getName(){
		return this.name;
	}
	public void setName(String name){
		this.name=name;
	}
	public ExpressionNode getExpressionTree(){
		return this.expressionTree;
	}
	public void setExpressionTree(ExpressionNode n){
		this.expressionTree=n;
	}
}
