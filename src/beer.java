/* 
Login Information
ssh cs421g23@comp421.cs.mcgill.ca
beerDB2016
*/

/* 
Compilation instructions, make sure that the jar file is in the same directory as your java file
javac -cp .:postgresql-9.4.1208.jre6.jar beer.java
java -cp .:postgresql-9.4.1208.jre6.jar Beer
*/


import java.sql.*;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.io.*;

class Beer 
{
	public static void main (String[] args) throws SQLException
	{
		// Register our postrgreSQL driver
		try {
			DriverManager.registerDriver(new org.postgresql.Driver());
		} catch (Exception cnfe) {
			System.out.println("Class Not Found Exception");
		}
		
		// Connect to the database
		String url = "jdbc:postgresql://comp421.cs.mcgill.ca:5432/cs421";
		Connection con = DriverManager.getConnection(url, "cs421g23", "beerDB2016");
		Statement statement = con.createStatement();
		
		// Display the menu options
		String option = "";
		option = showGUI();
		while (!option.equals("Q") && !option.equals("q")) {
			switch (option) {
				
				case "1":
					selectStatement(statement);
					option = showGUI();
				case "2":
					option = showGUI();

				case "3":
					option = showGUI();

				case "4":
					option = showGUI();

				case "5":
					option = showGUI();

				default: 
					option = showGUI();
			}
		}
		statement.close();
		con.close();
	}


private static void selectStatement(Statement statement) throws SQLException {
	try {
		String insert_sql = "SELECT name FROM beer";
		System.out.println(insert_sql);
		java.sql.ResultSet rs = statement.executeQuery(insert_sql);

		while (rs.next()) {
			String name = rs.getString("name");
			System.out.println("Name: " + name);
		}
	} catch (SQLException e) {
		int sqlCode = e.getErrorCode();
		String sqlState = e.getSQLState();
		System.err.println("Error occured: SQL State = " + sqlState + "SQLCode = " + sqlCode);
	}

}

private static String showGUI() {
	BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
	String option = "";
	System.out.println("");
	System.out.println("Beer Retailer Database");
	System.out.println("");
	System.out.println("Please Select An Option!");
	System.out.println("[1] Option 1");
	System.out.println("[2] Option 2");
	System.out.println("[3] Option 3");
	System.out.println("[4] Option 4");
	System.out.println("[5] Option 5");
	System.out.println("[Q] Quit");
	System.out.print(">>> ");

	try {
		option = in.readLine();
	} catch (IOException e) {

	}

	return option;
}















}