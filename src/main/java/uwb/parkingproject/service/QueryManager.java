package uwb.parkingproject.service;

import java.sql.*;
import java.util.Properties;

public class QueryManager {

    private final String host = "university-parking-winter-20.mysql.database.azure.com";
	private final String database = "UniversityParkingSystem";
	private final String user = "no_name@university-parking-winter-20";
    private final String password = "Uwbcss475";
    private Connection connection = null;

    public QueryManager() throws Exception {
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
    }

    public Connection getConnection() {
        return connection;
    }

    public void GetVacantSpotFromLot(String lot_name) {
        // SELECT SpotNumber, Level
		// FROM ParkingSpot
		// 	JOIN ParkingLot ON (ParkingLot.ID = ParkingSPot.ParkingLotID)
		// WHERE ParkingLot.Name = lotName AND LicensePlate IS null;
	}
	
	

}