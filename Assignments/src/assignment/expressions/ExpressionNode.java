package assignment.expressions;

/**
 * Expression Node class to contain all the functions related to an Expression node
 * @author Abyeti Technologies
 *
 */
public class ExpressionNode {
	private ExpressionNode left;
	private ExpressionNode right;
	private String data;
	
	public void setLeft(ExpressionNode a){
		left=a;
	}
	public void setRight(ExpressionNode a){
		right=a;
	}
	public void setData(String a){
		data=a;
	}
	public ExpressionNode getLeft(){
		return left;
	}
	public ExpressionNode getRight(){
		return right;
	}
	public String getData(){
		return data;
	}
}
