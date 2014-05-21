package assignment.servletExample;

import java.io.*;
import javax.servlet.http.HttpSession;
import java.util.Random;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import assignment.jdbcEample.*;
public class InsertData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	private static long nextLong(Random rng,int n) {
		   // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = (rng.nextLong() << 1) >>> 1;
		      val = bits % n;
		   } while (bits-val+(n-1) < 0L);
		   return val;
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student s=new Student();
		Random rand=new Random();
		PrintWriter pw=response.getWriter();
		HttpSession session = request.getSession();
		System.out.println("req==="+session.getAttribute("DB"));
		String name=request.getParameter("name");
		String marks1= request.getParameter("marks1");
		String marks2= request.getParameter("marks2");
		String percent= request.getParameter("percentage");
		s.setId(nextLong(rand,1000));
		s.setName(name);
		s.setMarks1(Double.valueOf(marks1));
		s.setMarks2(Double.valueOf(marks2));
		s.setPercentage(Double.valueOf(percent));
		s.connectToDB("assignment2", "root", "aakanksha");
		s.save();
		pw.println("Data is inserted");
		s.closeConnection();
		response.sendRedirect("/CompleteAssignments/Database.jsp");
		//request.getRequestDispatcher("#view").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

}
