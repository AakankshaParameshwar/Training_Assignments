package assignment.jdbcEample;

import java.io.*;
import java.util.Random;
public class Test {
	private static long nextLong(Random rng,int n) {
		   // error checking and 2^x checking removed for simplicity.
		   long bits, val;
		   do {
		      bits = (rng.nextLong() << 1) >>> 1;
		      val = bits % n;
		   } while (bits-val+(n-1) < 0L);
		   return val;
	}
	
	public static String main(String[] args)throws IOException{
		
		
		Student s= new Student();
		long id=789;
		//Class classObj=s.getClass();
		
		s.connectToDB("assignment2", "root", "aakanksha");
		s=DBPersister.loadById(Student.class, id);
		s.closeConnection();
		return s.getName();
		//Random rand=new Random();
		/*s.setId(789);
		s.setName("anu");
		s.setMarks1(70.90);
		s.setMarks2(90.71);
		s.setPercentage(85.78);
		s.save();
		
		/*Employee e= new Employee();
		e.setId();
		e.setName("achu");
		e.setDepartment("marketing");
		e.setSalary(900.90);
		e.save();
		DBPersister.closeConnection();*/
		
		
	}

}
