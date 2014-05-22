package assignment.jdbcEample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.math.*;
import java.lang.reflect.*;


public class DBPersister {
	private Connection connectionObj = null;
	private Statement statement = null;
	private PreparedStatement preparedStatement = null;
	private ResultSet resultSet = null;

	/**
	 * This method is used to make a successful connection to the database( MYSQL)
	 * @param database
	 * @param user
	 * @param password
	 * @return
	 */
	public Connection connectToDB(String database, String user, String password) {
		// Connection connectionObj=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("MySQL JDBC Driver is not loaded!");
			e.printStackTrace();
			System.exit(1);
		}
		String url = "jdbc:mysql://localhost/" + database;
		try {
			connectionObj = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			System.out.println("Connection Failed! Check output console");
			e.printStackTrace();
			System.exit(1);
		}

		if (connectionObj != null) {
			System.out.println("You made it, take control your database now!");
		} else {
			System.out.println("Failed to make connection!");
			System.exit(1);
		}
		System.out.println("---> Made a connection");
		return connectionObj;
	}
	
	/**
	 * This method is used to close the static connection established earlier
	 */
	public void closeConnection(){
		try{
			if(resultSet!=null){
				resultSet.close();
		}
			if(preparedStatement!=null){
				preparedStatement.close();
		}	
			if(statement!=null){
				statement.close();
		}		
			if(connectionObj!=null){
				connectionObj.close();
		}
		System.out.println("---> Closed the connection");
		}catch (SQLException e) {
			System.out.println("Failed to close Connection");
			e.printStackTrace();
			System.exit(1);
		}
	}

	/**
	 * This method checks if the object I want to store in database already exists.
	 * If it does, it deletes it and stores the updated version of that object in DB.
	 * If it doesnt exist in DB, this method inserts a new record into the database.
	 */
	public void save() {
		String tableName = this.getClass().getSimpleName();
		Field[] fields = this.getClass().getDeclaredFields();
		int len = fields.length;
		String query;
		long id;
		try{
		Field idObj=this.getClass().getDeclaredField("id");
		idObj.setAccessible(true);
		id=(long)idObj.get(this);
		resultSet=checkForOccurence(tableName,id, connectionObj);
		}catch(NoSuchFieldException e){
			System.out.println("error in fetching id");
			e.printStackTrace();
		}catch(IllegalAccessException e){
			System.out.println("error in fetching id");
			e.printStackTrace();
		}
		if (resultSet!=null) {
			this.delete();
			//System.out.println("The record You are trying to insert is already in the database");
			//System.out.println("Do you want to update it?");
		} 
		query = "insert into " + tableName + " (";
		for (int i = 0; i < len; i++) {
			String comma = ",";
			if (i == len - 1)
				comma = "";
			query = query + fields[i].getName() + comma;
		}
		query = query + ") VALUES (";
		for (int i = 0; i < len; i++) {
			String comma = ",";
			if (i == len - 1)
				comma = "";
			query = query + "?" + comma;
		}
		query = query + ")";
		try {
			preparedStatement = connectionObj.prepareStatement(query);
		} catch (SQLException e) {
			System.out.println("ERROR with prepared statement");
			e.printStackTrace();
		}
		
		try {
			for (int i = 0; i < len; i++) {
				Field field=fields[i];
				String type=field.getType().toString();
				field.setAccessible(true);
				try{
				if(type.equals("long")){
					BigDecimal d = new BigDecimal((long)field.get(this));
					preparedStatement.setBigDecimal(i+1, d);
				}
				else if(type.equals("double") || type.equals("float")){
				
					BigDecimal d = new BigDecimal((double)field.get(this));
					preparedStatement.setBigDecimal(i+1, d);
				}else if(type.equals("class java.lang.String")){
					preparedStatement.setString(i+1,(String)field.get(this) );
				}else if(type.equals("int")){
					preparedStatement.setInt(i+1,(int)field.get(this) );
				}
				}catch(IllegalAccessException e){
					System.out.println("Cannot access the fields");
					e.printStackTrace();
					System.exit(2);
				}
			}
			preparedStatement.executeUpdate();
			System.out.println("Record inserted");
		} catch (SQLException e) {
			System.out
					.println("ERROR with inserting values into prepared statement");
			e.printStackTrace();
		}
	}

	
	/**
	 * This method deletes the calling object from the database, only if it exists,
	 * else it displays an error message.
	 */
	public void delete() {
		String tableName = this.getClass().getSimpleName();
		long id=0;
		try{
		Field idObj=this.getClass().getDeclaredField("id");
		idObj.setAccessible(true);
		id=(long)idObj.get(this);
		resultSet=checkForOccurence(tableName,id, connectionObj);
		}catch(NoSuchFieldException e){
			System.out.println("error in fetching id");
			e.printStackTrace();
		}catch(IllegalAccessException e){
			System.out.println("error in fetching id");
			e.printStackTrace();
		}
		
		String query;
		if (resultSet==null) {
			System.out.println("No such records in the database");
			System.exit(2);
		} else {
			query = "delete from " + tableName + " where id = ?";
			System.out.println(query);
			try {
				preparedStatement = connectionObj.prepareStatement(query);
			} catch (SQLException e) {
				System.out.println("ERROR with prepared statement");
				e.printStackTrace();
			}
			try {
				BigDecimal d = new BigDecimal(id);
				preparedStatement.setBigDecimal(1, d);
				preparedStatement.executeUpdate();
				System.out.println("Record deleted");
			} catch (SQLException e) {
				System.out
						.println("ERROR with inserting values into prepared statement");
				e.printStackTrace();
			}
		}

	}

	/**
	 * This method loads a record from the database to an object of same type as the input Class passed as parameter. 
	 * It return null if no such record exists in the database.
	 * @param inClass
	 * @param id
	 * @return
	 */
	 public static <T> T loadById(Class<T> inClass,long id){
		 ResultSet result=null;
		 String tableName = inClass.getSimpleName();
		 T obj=null;
		 Connection connect=null;
		 try{
		 obj=(T)inClass.newInstance();
		 }catch (InstantiationException e) {
				e.printStackTrace();
			}
		 catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		 try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				System.out.println("MySQL JDBC Driver is not loaded!");
				e.printStackTrace();
				System.exit(1);
			}
			String url = "jdbc:mysql://localhost/assignment2";
			try {
				connect = DriverManager.getConnection(url,"root", "aakanksha");
			} catch (SQLException e) {
				System.out.println("Connection Failed! Check output console");
				e.printStackTrace();
				System.exit(1);
			}

			if (connect != null) {
				System.out.println("You made it, take control your database now!");
			} else {
				System.out.println("Failed to make connection!");
				System.exit(1);
			}
		 result=checkForOccurence(tableName,id, connect);
		 if(result==null){
			 System.out.println("No such record with the given Id exists in the database");
			 return null;
		 }else{
			 ResultSetMapper<T> resultSetMapper = new ResultSetMapper<T>();
			 obj= resultSetMapper.mapRersultSetToObject(result,inClass);
		 }
		 return obj;
	 }
	 
	 /**
	  * This method is used to display all the records stored in a table specific to a class.
	 * @return TODO
	  */
	public String[][] displayAllData() {
		String tableName = this.getClass().getSimpleName();
		String query;
		
		
		//StringBuffer buf=new StringBuffer();
		try {
			int j=0;
			statement = connectionObj.createStatement();
			query = "select * from " + tableName;
			System.out.println(query);
			resultSet = statement.executeQuery(query);
			ResultSetMetaData meta = resultSet.getMetaData();
			int cols=meta.getColumnCount();
			resultSet.last();
			int rows=resultSet.getRow();
			String[][] str=new String[rows][cols];
			Field[] fields = this.getClass().getDeclaredFields();
			System.out.println(fields.length);
			for (int i = 0; i < fields.length; i++) {
				//System.out.print(fields[i].getName() + "\t" +j);
				str[j][i]=fields[i].getName();
			}
			//System.out.println();
			j++;
			resultSet.first();
			while (resultSet.next()) {
				for (int i = 0; i < fields.length; i++) {
					//System.out.print(resultSet.getString(fields[i].getName())+ "\t");
					//buf.append(resultSet.getString(fields[i].getName())+"\t");
					str[j][i]=resultSet.getString(fields[i].getName());
				}
				//System.out.println();
				j++;
			}
			//return buf.toString();
			return str;
		} catch (SQLException e) {
			System.out.println("Unable to select data from db");
			e.printStackTrace();
			//return buf.toString();
			return null;
			
		}
	}
	
	/**
	 * This method checks if a record having the specified id as primary key exists in
	 * the database table. If it does, the resultSet ie that record in returned, else
	 * null is returned.
	 * @param tableName
	 * @param id
	 * @param connect TODO
	 * @return
	 */
	private static ResultSet checkForOccurence(String tableName,long id, Connection connect){
		String query;
		Statement stat;
		ResultSet result=null;
		boolean record=false;
		try {
			 stat= connect.createStatement();
			query = "select * from " + tableName + " where id = " + id;
			 result= stat.executeQuery(query);
			 record=result.next();
			 if(record){
				 return result;
			 }else{
				 return null;
			 }
			
		} catch (SQLException e) {
			System.out.println("Unable to select data from db");
			e.printStackTrace();
			return null;
		}
	}

}
