package assignment.expressions;
import java.io.*;
import java.util.*;
public interface IExpressionPersister {
	Expression load(String name)throws IOException;
	void store(Expression exp)throws IOException;
	ArrayList<String> getExpressionList()throws IOException;
}
