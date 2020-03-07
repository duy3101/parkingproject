package uwb.parkingproject.service;

import java.sql.*;
import java.util.Properties;

public class CreateUniversityDatabase {
	
	public CreateUniversityDatabase()  throws Exception
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
			throw new SQLException("Failed to create connection to database.", e);
		}
		if (connection != null) 
		{ 
			System.out.println("Successfully created connection to database.");
		
			// Perform some SQL queries over the connection.
			try
			{
				Statement statement = connection.createStatement();
			
                statement.execute("DROP TABLE IF EXISTS ParkingLot, ParkingLotType, ParkingSpot, ParkingSpotType, Vehicle, VehicleType, Payment;");
                System.out.println("Finished dropping tables (if existed).");
				
				statement.execute("CREATE TABLE ParkingLotType (ID INT AUTO_INCREMENT, Type VARCHAR(20) NOT NULL, PRIMARY KEY(ID));");
				System.out.println("Created ParkingLotType Table");

				statement.execute("CREATE TABLE ParkingSpotType (ID INT AUTO_INCREMENT, Type VARCHAR(10) NOT NULL, PRIMARY KEY(ID));");
				System.out.println("Created ParkingSpotType Table");

				statement.execute("CREATE TABLE VehicleType (ID INT AUTO_INCREMENT, Type VARCHAR(30), PRIMARY KEY(ID));");
				System.out.println("Created VehicleType Table");

				statement.execute("CREATE TABLE Vehicle (LicensePlate VARCHAR(8), Color VARCHAR(15), Manufacturer VARCHAR(30), Model VARCHAR(30), TypeID INT, PRIMARY KEY(LicensePlate), FOREIGN KEY(TypeID) REFERENCES VehicleType(ID));");
				System.out.println("Created Vehicle Table");

				statement.execute("CREATE TABLE Payment (ID INT AUTO_INCREMENT, LicensePlate VARCHAR(8), Duration INT NOT NULL, StartTime DATETIME NOT NULL, PRIMARY KEY(ID), FOREIGN KEY(LicensePlate) REFERENCES Vehicle(LicensePlate));");
				System.out.println("Created Payment Table");

                statement.execute("CREATE TABLE ParkingLot(ID INT AUTO_INCREMENT, ParkingLotTypeID INT NOT NULL, Name VARCHAR(20) NOT NULL, PRIMARY KEY(ID), FOREIGN KEY(ParkingLotTypeID) REFERENCES ParkingLotType(ID));");
				System.out.println("Created ParkingLot Table");

				statement.execute("CREATE TABLE ParkingSpot (ID INT AUTO_INCREMENT, ParkingSpotTypeID INT NOT NULL, ParkingLotID INT NOT NULL, LicensePlate VARCHAR(8), SpotNumber INT NOT NULL, Level INT NOT NULL, PRIMARY KEY(ID), FOREIGN KEY(ParkingSpotTypeID) REFERENCES ParkingSpotType(ID), FOREIGN KEY(ParkingLotID) REFERENCES ParkingLot(ID), FOREIGN KEY(LicensePlate) REFERENCES Vehicle(LicensePlate));");
				System.out.println("Created ParkingSpot Table");
            }
            catch (SQLException e)
			{
				throw new SQLException("Encountered an error when executing given sql statement", e);
			}	
        }
        else
        {
            System.out.println("Failed to create connection to database.");
        }
        System.out.println("Execution finished.");
    }
}