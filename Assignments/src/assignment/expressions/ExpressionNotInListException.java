package assignment.expressions;

public class ExpressionNotInListException extends Exception {
	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 2789626022332797000L;

	public ExpressionNotInListException() {
		super();
	}

	public ExpressionNotInListException(String arg0) {
		super(arg0);
	
	}

	public ExpressionNotInListException(Throwable arg0) {
		super(arg0);

	}

	public ExpressionNotInListException(String arg0, Throwable arg1) {
		super(arg0, arg1);
	}

	public ExpressionNotInListException(String arg0, Throwable arg1,boolean arg2, boolean arg3) {
		super(arg0, arg1, arg2, arg3);
	}

}
