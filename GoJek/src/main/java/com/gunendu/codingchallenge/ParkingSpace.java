package main.java.com.gunendu.codingchallenge;


public class ParkingSpace {
	private boolean available;
	private Vehicle vehicle;
	
	public ParkingSpace() {			
	}
	
	public boolean isAvailable() {
		return available;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public Vehicle getVehicle() {
		return vehicle;
	}

	public void setVehicle(Vehicle vehicle) {
		this.vehicle = vehicle;
	}
		
	
}
