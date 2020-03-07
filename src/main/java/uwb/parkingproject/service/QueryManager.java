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

    public ResultSet GetVacantSpotFromLot(String lot_name) throws Exception{


        String query = String.format("SELECT SpotNumber, Level FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) WHERE ParkingLot.Name = %s AND LicensePlate IS null ORDER BY SpotNumber, Level;", lot_name);
		ResultSet results;

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	public ResultSet GetVacantSpotFromType(String type_name) throws Exception{


        String query = String.format("SELECT SpotNumber, Level, ParkingSpotType.Type FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpotType.ID = ParkingSpot.ParkingSpotTypeID) WHERE ParkingSpotType.Type = %s AND LicensePlate IS null ORDER BY SpotNumber, Level;", type_name);
		ResultSet results;

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}
	
	public ResultSet EnterVehicleInfo(String plateNumber, String color, String manu, String model, int typeNum) throws Exception{


        String query = String.format("INSERT INTO Vehicle VALUES (%1$s, %2$s, %3$s, %4$s, %x)", plateNumber, color, nau, model, typeNum);
		ResultSet results;

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	
	// Check later for syntax issues and issues with multiple queries
	public ResultSet ParkCar(String plateNumber, int spotNumber, String lotName) throws Exception{


        String query = String.format("UPDATE ParkingSpot SET ParkingSpot.LicensePlate = %1$s WHERE ParkingSpot.SpotNumber = %x AND ParkingSpot.ParkingLotID IN (SELECT ParkingLot.ID FROM ParkingLot WHERE ParkingLot.name = %2$s); ", plateNumber, spotNumber, lotName);
		String query2 = String.format("INSERT INTO Payment VALUES ('', %s, 0, dateTime('now'), False);", plateNumber);
        ResultSet results;


		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	// Needs to have created trigger as well to work correctly 
	public ResultSet LeaveSpot(int spotNumber, String lotName) throws Exception{


        String query = String.format("UPADTE ParkingSpot SET PakingSpot.LicensePlate = null WHERE ParkingSpot.SpotNumber = %x AND ParkingSpot.ParkingLotID IN  (SELECT ParkingLot.ID FROM ParkingLot WHERE ParkingLot.name = %s); ", spotNumber, lotName);
		ResultSet results;
		
		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	public ResultSet GetPayments(String plateNumber) throws Exception{


        String query = String.format("SELECT StartTime, Duration, Status FROM Payment WHERE LicensePlate = %s ORDER BY startTime;", plateNumber);
		ResultSet results;
		
		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	// Query with no parameters, might have issues
	public ResultSet GetOverduePayments() throws Exception{


        String query = String.format("SELECT LicensePlate, Duration, StartTime, Status FROM Payment WHERE (DATE_ADD(startTime, INTERVAL 7 DAY)) < datetime('now') ORDER BY startTime;");
		ResultSet results;
		
		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	// Long queries with no parameters, might have issues
	public ResultSet GetPercentageForLot() throws Exception{


        String query = String.format("SELECT Total.ParkingLotName, ((1.0 * Vacant.Vacancy) / (1.0 * Total.TotalSpots)) * 100 AS Percentage FROM ( SELECT COUNT(*) as TotalSpots, ParkingLot.name as ParkingLotName FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) GROUP BY ParkingLot.name ) AS Total JOIN ( SELECT COUNT(*) as Vacancy,ParkingLot.name as ParkingLotName FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) WHERE LicensePlate IS null GROUP BY ParkingLot.name ) AS Vacant WHERE Total.ParkingLotName = Vacant.ParkingLotName ORDER BY Total.ParkingLotName;");
		ResultSet results;

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}
	
	// Long queries with no parameters, might have issues
	public ResultSet GetPercentageForType() throws Exception{

        String query = String.format("SELECT Total.TypeName, ((1.0 * Vacant.Vacancy) / (1.0 * Total.TotalSpots)) * 100 AS Percentage FROM ( SELECT COUNT(*) as TotalSpots, ParkingSpotType.Type as TypeName FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpot.ParkingSpotTypeID = ParkingSpotType.ID) GROUP BY ParkingSpotType.ID ) AS Total JOIN ( SELECT COUNT(*) as Vacancy, ParkingSpotType.Type as TypeName FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpot.ParkingSpotTypeID = ParkingSpotType.ID) WHERE LicensePlate IS null GROUP BY ParkingSpotType.ID ) AS Vacant WHERE Total.TypeName = Vacant.TypeName ORDER BY Total.TypeName;");
		ResultSet results;

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		


		return results;

	}

	
	
	public String test () {
		return "talking from QueryManager";
	}
	

}