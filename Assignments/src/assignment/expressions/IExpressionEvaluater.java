package assignment.expressions;
import java.util.*;

/**
 * provides basic functions for an expression evaluator 
 * @author Abyeti Technologies
 *
 */
public interface IExpressionEvaluater {
	double evaluate(Expression exp, Map<String,Double> m);

}
