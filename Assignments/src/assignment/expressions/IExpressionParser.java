package assignment.expressions;

/**
 * Provides basic functions to be implemented by expression parsers
 * @author Abyeti Technologies
 *
 */
public interface IExpressionParser {
	Expression parse(String name,String exp);
}
