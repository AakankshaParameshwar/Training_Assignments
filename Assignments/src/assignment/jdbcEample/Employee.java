package assignment.jdbcEample;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Employee extends DBPersister {
	
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="department")
	private String department;
	
	@Column(name="salary")
	private double salary;
	
	public void setName(String a){
		this.name=a;
	}
	public void setDepartment(String a){
		this.department=a;
	}
	public void setSalary(double a){
		this.salary=a;
	}
	
	public void setId(long a){
		this.id=a;
	}
	public String getName( ){
		return this.name;
	}
	public String getDepartment( ){
		return this.department;
	}
	public double getSalary( ){
		return this.salary;
	}
	public long getId( ){
		return this.id;
	}
	
	@Override
	public void save(){
		//super.connectToDB("assignment2", "root", "aakanksha");
		super.save();
	}
	@Override
	public void delete(){
		//super.connectToDB("assignment2", "root", "aakanksha");
		super.delete();
	}
	@Override
	public String[][] displayAllData(){
		
	String[][] data=super.displayAllData();
	return data;
	}
	@Override
	public String toString() {
		return 	"id: " + id + "\n" + 
				"name: " + name + "\n"+
				"department: " + department + "\n" +
				"salary: " + salary + "\n\n";
	}

}
