package assignment.jdbcEample;


import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Student extends DBPersister {
	
	@Column(name="id")
	private long id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="marks1")
	private double marks1;
	
	@Column(name="marks2")
	private double marks2;
	
	@Column(name="percentage")
	private double percentage;
	
	
	public void setName(String a){
		this.name=a;
	}
	public void setMarks1(double a){
		this.marks1=a;
	}
	public void setMarks2(double a){
		this.marks2=a;
	}
	public void setPercentage(double a){
		this.percentage=a;
	}

	public void setId(long a){
		this.id=a;
	}
	public String getName( ){
		return this.name;
	}
	public double getMarks1( ){
		return this.marks1;
	}
	public double getMarks2( ){
		return this.marks2;
	}
	public double getPercentage( ){
		return this.percentage;
	}
	public long getId( ){
		return this.id;
	}
	
	@Override
	public void save(){
		super.save();
	}
	@Override
	public void delete(){
		super.delete();
	}
	@Override
	public void displayAllData(){
		super.displayAllData();
	}
	
	@Override
	public String toString() {
		return 	"id: " + id + "\n" + 
				"name: " + name + "\n"+
				"marks1: " + marks1 + "\n" +
				"marks2: " + marks2 + "\n" +
				"percentage: " + percentage + "\n\n";
	}
}
