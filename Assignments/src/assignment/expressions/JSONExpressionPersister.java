package assignment.expressions;

import java.io.*;

import com.google.gson.Gson;

import java.util.ArrayList;

/**
 * Implemnts the IExpressionPersister for storing expressions in JSON format
 * @author Abyeti Technologies
 *
 */
public class JSONExpressionPersister implements IExpressionPersister {

	/**
	 * This method looks up for the expression name in the expressionList files
	 * stored at a particular location, if it is present it loads it as an
	 * Expression object by converting JSON file to Expression object.
	 * 
	 * @param name
	 *            of the expression
	 * @return Expression object
	 * @throws IOException
	 */
	public Expression load(String name) throws IOException {
		String directoryPath = "C:\\Users\\Abyeti Technologies\\Desktop\\Aakanksha\\Assignments\\json files\\";
		Gson gson = new Gson();
		ArrayList<String> expList = getExpressionList();
		try {

			if (expList.contains(name)) {
				String filePath = directoryPath + name + ".json";
				FileReader fileReader = new FileReader(filePath);
				BufferedReader bufferedReader = new BufferedReader(fileReader);
				Expression Eobj = gson.fromJson(bufferedReader,
						Expression.class);
				bufferedReader.close();
				fileReader.close();
				return Eobj;
			} else {
				throw new ExpressionNotInListException(
						"The given expression is not defined.");
			}
		} catch (ExpressionNotInListException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {

		}
	}

	/**
	 * This method converts Expression object to a JSON object and stores it in
	 * a particular location. After which it includes the filename to the List
	 * of defined expressions.
	 * 
	 * @param exp
	 * @throws IOException
	 */
	public void store(Expression exp) throws IOException {
		String path = "C:\\Users\\Abyeti Technologies\\Desktop\\Aakanksha\\Assignments\\json files\\";
		String newpath = path + exp.getName() + ".json";
		ArrayList<String> expList = getExpressionList();
		File newfile = new File(newpath);
		newfile.createNewFile();
		String listPath = path + "expressionList.txt";
		File listFile = new File(listPath);
		if (!listFile.exists()) {
			listFile.createNewFile();
		}
	
		
			expList=getExpressionList();
			if(!expList.contains(exp.getName())){
				try {
			FileWriter flist = new FileWriter(listPath, true);
			BufferedWriter bufferWritter = new BufferedWriter(flist);
			bufferWritter.write(exp.getName());
			bufferWritter.newLine();
			bufferWritter.close();
			}catch (IOException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
		}
		Gson gson = new Gson();
		String jsonObj = gson.toJson(exp);
		try {
			FileWriter fw = new FileWriter(newpath);
			fw.write(jsonObj);
			fw.close();
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

	public ArrayList<String> getExpressionList() throws IOException {
		String directoryPath = "C:\\Users\\Abyeti Technologies\\Desktop\\Aakanksha\\Assignments\\json files\\";
		String expListPath = directoryPath + "expressionList.txt";
		ArrayList<String> expList = new ArrayList<String>();
		FileReader fileReader;
		BufferedReader bufferedReader;
		File listFile = new File(expListPath);
		if (!listFile.exists()) {
			listFile.createNewFile();
		}
		try {
			fileReader = new FileReader(expListPath);
			bufferedReader = new BufferedReader(fileReader);
			String expName = bufferedReader.readLine();
			while (expName != null) {
				
				expList.add(expName);
				expName = bufferedReader.readLine();
			}
			bufferedReader.close();
			fileReader.close();
			return expList;
		} catch (IOException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
			return null;
		} finally {
			
		}
	}
}
