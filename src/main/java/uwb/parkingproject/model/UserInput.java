package uwb.parkingproject.model;


public class UserInput {

	private String ParkingLotName;
    private String Level;
    private String SpotNumber;

	public String getParkingLotName() {
		return ParkingLotName;
	}

	public void setParkingLotName(String ParkingLotName) {
		this.ParkingLotName = ParkingLotName;
    }
    
    public String getLevel() {
		return Level;
	}

	public void setLevel(String Level) {
		this.Level = Level;
    }
    
    public String getSpotNumber() {
		return SpotNumber;
	}

	public void setSpotNumber(String SpotNumber) {
		this.SpotNumber = SpotNumber;
	}
	
}