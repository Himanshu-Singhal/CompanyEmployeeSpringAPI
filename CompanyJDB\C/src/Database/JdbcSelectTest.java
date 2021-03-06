package Database;

import java.sql.*; // Use 'Connection', 'Statement' and 'ResultSet' classes in java.sql package

// JDK 1.7 and above
public class JdbcSelectTest { // Save as "JdbcSelectTest.java"
	public static void main(String[] args) {
		try (
			// Step 1: Allocate a database 'Connection' object
			Connection conn = DriverManager.getConnection(
					// MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"
					"jdbc:mysql://localhost:3306/companymanagement?useSSL=false&serverTimezone=EST", "root", "");

			// Step 2: Allocate a 'Statement' object in the Connection
			Statement stmt = conn.createStatement();) {
			// Step 3: Execute a SQL SELECT query, the query result
			// is returned in a 'ResultSet' object.
			String strSelect = "SELECT employee.name,company.name FROM `employee` INNER JOIN company ON employee.companyid = company.id;";
			System.out.println("The SQL query is: " + strSelect); // Echo For debugging
			System.out.println();

			ResultSet rset = stmt.executeQuery(strSelect);

			// Step 4: Process the ResultSet by scrolling the cursor forward via next().
			// For each row, retrieve the contents of the cells with getXxx(columnName).
			System.out.println("The records selected are:");
			int rowCount = 0;
			while (rset.next()) { // Move the cursor to the next row, return false if no more row
				String empName = rset.getString("employee.name");
				String companyName = rset.getString("company.name");
				// int qty = rset.getInt("qty");
				System.out.println(empName + ", " + companyName);
				++rowCount;
			}
			System.out.println("Total number of records = " + rowCount);

		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		// Step 5: Close the resources - Done automatically by try-with-resources
	}
}
