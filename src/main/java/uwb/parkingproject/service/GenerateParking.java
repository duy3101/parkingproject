package uwb.parkingproject.service;

import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;


public class GenerateParking {

	public GenerateParking()  throws Exception
	{
		// Initialize connection variables.	
		String host = "university-parking-winter-20.mysql.database.azure.com";
		String database = "UniversityParkingSystem";
		String user = "no_name@university-parking-winter-20";
        String password = "Uwbcss475";
        
        ArrayList<String> parkingSpotTypeList = new ArrayList<>();
        parkingSpotTypeList.add("Normal");
        parkingSpotTypeList.add("Carpool");
        parkingSpotTypeList.add("Handicapped");

        ArrayList<String> parkingLotTypeList = new ArrayList<>();
        parkingLotTypeList.add("Garage");
        parkingLotTypeList.add("Gravel");

		ArrayList<String> parkingLotNameList = new ArrayList<>();
		parkingLotNameList.add("North");
		parkingLotNameList.add("South");
		parkingLotNameList.add("Discovery");



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
			throw new SQLException("Failed to create connection to database.", e);
		}
		if (connection != null) 
		{ 
			System.out.println("Successfully created connection to database.");
		
			// Perform some SQL queries over the connection.
			try
			{
				System.out.println("check");


				Statement statement = connection.createStatement();

				for (int i = 0; i < parkingSpotTypeList.size(); i++) { 		
					System.out.println(parkingSpotTypeList.get(i));      
					statement.execute(String.format("INSERT INTO ParkingSpotType (Type) VALUES (%s)", parkingSpotTypeList.get(i)));		
				}  
				System.out.println("Finished adding ParkingSpotType");


				for (int i = 0; i < parkingLotTypeList.size(); i++) { 		      
					statement.execute(String.format("INSERT INTO ParkingLotType (Type) VALUES (%s)", parkingLotTypeList.get(i)));		
				}  
				System.out.println("Finished adding ParkingLotType");


				for (int i = 0; i < parkingLotNameList.size(); i++) { 		      
					statement.execute(String.format("INSERT INTO ParkingLot (ParkingLotTypeID, Name) VALUES (%1$s, %2$d)", parkingLotNameList.get(i), 1));		
				}  
				System.out.println("Finished adding ParkingLot");

				for (int i = 1; i <= parkingLotNameList.size(); i++) { 		      
					for (int j = 1; j < 3; j++) {	 	
						for (int k = 1; k < 11; k++) {
							statement.execute(String.format("INSERT INTO ParkingSpot (ParkingSpotTypeID, ParkingLotID, SpotNumber, Level) VALUES (%1$d, %2$d, %3$d, %4$d)", 
							1, i, k, j));		

						}	
					}	      
				}
				System.out.println("Finished adding ParkingSpot");

				/* Specifying argument positions. %1$ is for the first argument and
				* %2$ is for the second argument
				*/
	
			}
			catch (SQLException e)
			{
				throw new SQLException("Encountered an error when executing given sql statement.", e);
			}		
		}
		else {
			System.out.println("Failed to create connection to database.");
		}
		System.out.println("Execution finished.");
	}
}