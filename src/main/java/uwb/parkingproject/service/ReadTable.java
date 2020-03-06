package uwb.parkingproject.service;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;
import uwb.parkingproject.model.Fruit;

public class ReadTable {

	private ArrayList<Fruit> fruitList = new ArrayList<>();

	public ReadTable() throws Exception
	{
		// Initialize connection variables.
		String host = "university-parking-winter-20.mysql.database.azure.com";
		String database = "UniversityParkingSystem";
		String user = "no_name@university-parking-winter-20";
        String password = "Uwbcss475";

		Connection connection = null;

		// Initialize connection object
		try
		{
			String url = String.format("jdbc:mariadb://%s/%s", host, database);

			// Set connection properties.
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", password);
			properties.setProperty("useSSL", "true");
			properties.setProperty("verifyServerCertificate", "true");
			properties.setProperty("requireSSL", "false");
			
			// get connection
			connection = DriverManager.getConnection(url, properties);
		}
		catch (SQLException e)
		{
			throw new SQLException("Failed to create connection to database", e);
		}
		if (connection != null) 
		{ 
			System.out.println("Successfully created connection to database.");
			
			// Perform some SQL queries over the connection.
			try
			{
				Statement statement = connection.createStatement();
				ResultSet results = statement.executeQuery("SELECT * from inventory;");
				while (results.next())
				{

					Fruit temp = new Fruit(results.getString(1), results.getString(2), results.getString(3));
					fruitList.add(temp);
					// String outputString = 
					// 	String.format(
					// 		"Data row = (%s, %s, %s)",
					// 		results.getString(1),
					// 		results.getString(2),
					// 		results.getString(3));
					// System.out.println(outputString);
				}
			}
			catch (SQLException e)
			{
				System.out.println("TEST");
				throw new SQLException("Encountered an error when executing given sql statement", e);
			}		
		}
		else {
			System.out.println("Failed to create connection to database.");	
		}
		System.out.println("Execution finished.");
	}

	public ArrayList<Fruit> getResult() {
		return fruitList;
	}
}