package assignment.servletExample;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.jdbcEample.*;
public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operation=request.getParameter("operation");
		String selectedId=request.getParameter("selected-id");
		long id=(long)Integer.parseInt(selectedId);
		String db=request.getSession().getAttribute("DB").toString();
		PrintWriter pw=response.getWriter();
		
		
		if(db.equalsIgnoreCase("student")){
			Student obj=new Student();
			obj.connectToDB("assignment2", "root", "aakanksha");
			obj=DBPersister.loadById(Student.class,789 );
			pw.println(obj.getName());
		}else if(db.equalsIgnoreCase("employee")){
			Employee obj=new Employee();
			obj.connectToDB("assignment2", "root", "aakanksha");
			obj=DBPersister.loadById(Employee.class,id );
		} 
		
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
