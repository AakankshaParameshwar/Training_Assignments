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
	public static void main(String[] args)throws IOException{
		
		Student s= new Student();
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
		
		long id=789;
		//Class classObj=s.getClass();
		Employee obj=new Employee();
		s.connectToDB("assignment2", "root", "aakanksha");
		String[][] data=s.displayAllData();
		for(int i=0;i<data.length;i++){
			for(int j=0; j<data[0].length;j++){
			System.out.print(data[i][j]+"\t");
			}
			System.out.println();
		}
		s.closeConnection();
		/*obj=DBPersister.loadById(Employee.class, id);
		System.out.println(obj.getName());*/
	}

}
