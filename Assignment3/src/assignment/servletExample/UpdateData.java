package assignment.servletExample;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.*;

public class UpdateData extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//String ID=request.getAttribute("UPDATE-ID").toString();
		PrintWriter pw=response.getWriter();
		Enumeration names=request.getParameterNames();
		response.setContentType("text/html");
		pw.println("<html><body><h1>heyyyyy</h1>");
		//HttpSession session=request.getSession();
		//pw.println("up-id"+ request.getAttribute("UPDATE-ID"));
		while(names.hasMoreElements()) 
		{
			String Name = (String)names.nextElement();
			String[] paramValues = request.getParameterValues(Name);
			pw.println("<h1>"+Name+" "+paramValues[0]+"</h1>");
		}
pw.println("<h1>id--->"+request.getSession().getAttribute("UPDATE-ID")+"</h1>");
		pw.println("</html></body>");
		//String db=request.getAttribute("DB").toString();
		//long id=Long.parseLong(ID);

		
		//System.out.println(db+ "     "+ id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
