

package uwb.parkingproject.service;

import java.sql.*;
import java.util.Properties;
import java.util.ArrayList;
import uwb.parkingproject.model.ReturnType;


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

	// public ArrayList<ReturnType> ResultSetToArrayList(ResultSet set, int num_com) {
	// 	ArrayList<ReturnType> res = new ArrayList<>();
	
	// 	return res;
	// }

    public ArrayList<ReturnType> GetVacantSpotFromLot(String lot_name) throws Exception{


		String query = String.format("SELECT SpotNumber, Level FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) WHERE ParkingLot.Name = \"%s\" AND LicensePlate IS null ORDER BY SpotNumber, Level;", lot_name);


		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
		{
			Statement statement = this.connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next())
			{
				ReturnType temp = new ReturnType(results.getString(1), results.getString(2));
				return_table.add(temp);
			}
			return return_table;
		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);

		}		

	}

	public ArrayList<ReturnType> GetVacantSpotFromType(String type_name) throws Exception{


        String query = String.format("SELECT SpotNumber, Level, ParkingSpotType.Type FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpotType.ID = ParkingSpot.ParkingSpotTypeID) WHERE ParkingSpotType.Type = \"%s\" AND LicensePlate IS null ORDER BY SpotNumber, Level;", type_name);
		

		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
		{
			Statement statement = this.connection.createStatement();
			ResultSet results = statement.executeQuery(query);
			while (results.next())
			{
				ReturnType temp = new ReturnType(results.getString(1), results.getString(2), results.getString(3));
				return_table.add(temp);
			}
			return return_table;

		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}	
			
	}

	
	public int EnterVehicleInfo(String plateNumber, String color, String manu, String model, int typeNum) throws Exception{

		System.out.println(plateNumber + " " + color + " " +manu + " " + model + " " +typeNum);
        String query = String.format("INSERT INTO Vehicle VALUES (\"%1$s\", \"%2$s\", \"%3$s\", \"%4$s\", %5$d);", plateNumber, color, manu, model, typeNum);
		System.out.println(query);

		try
		{
			Statement statement = this.connection.createStatement();
			int result = statement.executeUpdate(query);
			return result;

		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		

	}

	
	// Check later for syntax issues and issues with multiple queries
	public int ParkCar(String plateNumber, int spotNumber, int level, String lotName) throws Exception{


        String query = String.format("UPDATE ParkingSpot SET ParkingSpot.LicensePlate = \"%1$s\" WHERE ParkingSpot.SpotNumber = \"%2$d\" AND ParkingSpot.Level = \"%3$d\" AND ParkingSpot.ParkingLotID IN (SELECT ParkingLot.ID FROM ParkingLot WHERE ParkingLot.name = \"%4$s\"); ", plateNumber, spotNumber, level, lotName);
		String query2 = String.format("INSERT INTO Payment (LicensePlate, Duration, StartTime, Status) VALUES (\"%s\", 0, NOW(), False);", plateNumber);

		System.out.println(query);
		System.out.println(query2);

		try
		{
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("passed first query");
			statement.executeUpdate(query2);
			return 1;

		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		

	}

	// Needs to have created trigger as well to work correctly 
	public int LeaveSpot(String plateNumber) throws Exception{


		String query = String.format("UPDATE ParkingSpot SET ParkingSpot.LicensePlate = null WHERE ParkingSpot.LicensePlate = \"%1$s\";", plateNumber);
		String query2 = String.format("UPDATE Payment SET duration = TIMESTAMPDIFF(MINUTE,StartTime, NOW()) WHERE Payment.LicensePlate = \"%s\" AND Payment.Duration = 0;", plateNumber);
		
		System.out.println(query);

		try
		{
			Statement statement = this.connection.createStatement();
			statement.executeUpdate(query);
			System.out.println("passed first query");

			statement.executeUpdate(query2);
			return 1;
		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		
	}

	public ArrayList<ReturnType> GetParkedInfo(String plateNumber) throws Exception{


        String query = String.format("SELECT ParkingLot.Name, ParkingSpot.SpotNumber, ParkingSpot.Level FROM ParkingSpot JOIN ParkingLot ON (ParkingSpot.ParkingLotID = ParkingLot.ID) WHERE ParkingSpot.LicensePlate = \"%s\";", plateNumber);
		ResultSet results;
		
		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
			{
				Statement statement = this.connection.createStatement();
				results = statement.executeQuery(query);

				while (results.next())
				{
					ReturnType temp = new ReturnType(results.getString(1), results.getString(2), results.getString(3));
					return_table.add(temp);
				}
				return return_table;
			}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		
	}

	// Query with no parameters, might have issues
	public ArrayList<ReturnType> GetOverduePayments() throws Exception{


        String query = String.format("SELECT LicensePlate, Duration, StartTime, Status FROM Payment WHERE (DATE_ADD(startTime, INTERVAL 7 DAY)) < datetime('now') ORDER BY startTime;");
		ResultSet results;
		
		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
		{
			Statement statement = this.connection.createStatement();
			results = statement.executeQuery(query);

			while (results.next())
			{
				ReturnType temp = new ReturnType(results.getString(1), results.getString(2), results.getString(3), results.getString(4));
				return_table.add(temp);
			}
			return return_table;
		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		
	}

	// Long queries with no parameters, might have issues
	public ArrayList<ReturnType> GetPercentageForLot() throws Exception{


        String query = String.format("SELECT Total.ParkingLotName, ((1.0 * Vacant.Vacancy) / (1.0 * Total.TotalSpots)) * 100 AS Percentage FROM ( SELECT COUNT(*) as TotalSpots, ParkingLot.name as ParkingLotName FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) GROUP BY ParkingLot.name ) AS Total JOIN ( SELECT COUNT(*) as Vacancy,ParkingLot.name as ParkingLotName FROM ParkingSpot JOIN ParkingLot ON (ParkingLot.ID = ParkingSpot.ParkingLotID) WHERE LicensePlate IS null GROUP BY ParkingLot.name ) AS Vacant WHERE Total.ParkingLotName = Vacant.ParkingLotName ORDER BY Total.ParkingLotName;");
		ResultSet results;

		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
		{
			Statement statement = this.connection.createStatement();
			results = statement.executeQuery(query);

			while (results.next())
			{
				ReturnType temp = new ReturnType(results.getString(1), results.getString(2));
				return_table.add(temp);
			}
			return return_table;
		}

		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		
	}
	
	// Long queries with no parameters, might have issues
	public ArrayList<ReturnType> GetPercentageForType() throws Exception{

        String query = String.format("SELECT Total.TypeName, ((1.0 * Vacant.Vacancy) / (1.0 * Total.TotalSpots)) * 100 AS Percentage FROM ( SELECT COUNT(*) as TotalSpots, ParkingSpotType.Type as TypeName FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpot.ParkingSpotTypeID = ParkingSpotType.ID) GROUP BY ParkingSpotType.ID ) AS Total JOIN ( SELECT COUNT(*) as Vacancy, ParkingSpotType.Type as TypeName FROM ParkingSpot JOIN ParkingSpotType ON (ParkingSpot.ParkingSpotTypeID = ParkingSpotType.ID) WHERE LicensePlate IS null GROUP BY ParkingSpotType.ID ) AS Vacant WHERE Total.TypeName = Vacant.TypeName ORDER BY Total.TypeName;");
		ResultSet results;

		ArrayList<ReturnType> return_table = new ArrayList<>();

		try
		{
			Statement statement = this.connection.createStatement();
			results = statement.executeQuery(query);

			while (results.next())
			{
				ReturnType temp = new ReturnType(results.getString(1), results.getString(2));
				return_table.add(temp);
			}
			return return_table;
		}
		catch (SQLException e)
		{
			throw new SQLException("Encountered an error when executing given sql statement", e);
		}		
	}

	public String test () {
		return "talking from QueryManager";
	}
	
	public int CarTypeToInt (String type) {
	
		if (type.equals("Sedan")) {
			return 1;
		}
		else if (type.equals("Truck")) {
			return 2;
		}
		else if (type.equals("Compact")) {
			return 3;
		}
		else if (type.equals("SUV")) {
			return 4;
		}
		else if (type.equals("Hybird")) {
			return 5;
		}
		else {
			return 6; // motocycle
		}
	}

}