package main.java.com.gunendu.codingchallenge;

import java.util.Scanner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class WorkBook {

	private List<Integer> availableslots = null;
	private List<Integer> freeslots = null;
	private Map<Integer,ParkingSpace> parkingslots = null;
	private int max_val = 1000;
	private String output = "";

	public void readInputFile(Scanner inputScanner) {				
		while (inputScanner.hasNextLine()) {			
			String data = inputScanner.nextLine();
			String[] commands = data.split("\\s+");
			switch (commands[0]) {
			case "create_parking_lot":
				output = output + createParkingLot(Integer.parseInt(commands[1]));
				break;
			case "leave":
				output = output + unpark(Integer.parseInt(commands[1]));
				break;
			case "status":
				output = output + status();
				break;
			case "park":
				output = output + park(commands[1],commands[2]);
				break;
			case "registration_numbers_for_cars_with_colour":
				output = output + regNoByColor(commands[1]);
				break;
			case "slot_numbers_for_cars_with_colour":
				output = output + slotByColor(commands[1]);
				break;
			case "slot_number_for_registration_number":
				output = output + slotByRegNo(commands[1]);
				break;
			case "default":
				System.out.println("Not found");
			}
		}				
	}
	
	public String getOutput() {
		return output;
	}

	public String createParkingLot(int slots) {
		parkingslots = new HashMap<Integer,ParkingSpace>(slots);
		availableslots = new ArrayList<Integer>(slots);
		int i = 0;
		while (i < slots) {
			availableslots.add(i + 1);
			i++;
		}
		freeslots = new ArrayList<Integer>();
		String str = "Created a parking lot with "+slots+" slots\n";		
		return str;
	}

	public String park(String reg_no,String color) {		
		int slotId = getNearestSlot();
		String str = "";
		if (slotId != max_val) {
			Vehicle v = new Vehicle(color,reg_no);			
			ParkingSpace pkspace = new ParkingSpace();			
			pkspace.setAvailable(false);
			pkspace.setVehicle(v);			
			parkingslots.put(slotId,pkspace);
			str = "Allocated slot number: "+slotId+"\n";						
		}
		else {
			str = "Sorry, parking lot is full\n";
		}		
		return str;
	}

	public String unpark(int slotId) {
		parkingslots.remove(slotId);
		addSlotInFreePool(slotId);
		String str = "Slot number " +slotId+ " is free\n";		
		return str;
	}


	public String status() {
		String str = "";
		for (Map.Entry<Integer, ParkingSpace> entry : parkingslots.entrySet()) {
			ParkingSpace pkspace = entry.getValue();
			str = str + entry.getKey() + " " + pkspace.getVehicle().getReg_no() + " " + pkspace.getVehicle().getColor() + "\n" ;			
		}
		return str;
	}

	public int getNearestSlot() {
		int min = availableslots.get(0);
		int minindex = 0;
		for (int i = 1; i < availableslots.size(); i++) {
			if (availableslots.get(i) < min) {
				min = availableslots.get(i);
				minindex = i;
			}
		}
		availableslots.set(minindex, max_val);
		return min;
	}
	
	public void addSlotInFreePool(int slotId) {
		for (int i = 0; i < availableslots.size(); i++) {
			if (availableslots.get(i) == max_val) {				
				availableslots.set(i, slotId);
				return;
			}
		}		
	}
	
	public String regNoByColor(String color) {
		String str = "";
		for (Map.Entry<Integer, ParkingSpace> entry : parkingslots.entrySet()) {
			ParkingSpace pkspace = entry.getValue();
			if(pkspace.getVehicle().getColor().equals(color)) {
				str = str + pkspace.getVehicle().getReg_no() + ", ";				
			}			
		}
		str = str.replaceAll(", $", "");
		if(str.length()==0) {
			str = "Not found";
		} else {
			str = str + "\n";			
		}
		return str;
	}
	
	public String slotByColor(String color) {
		String str = "";
		for (Map.Entry<Integer, ParkingSpace> entry : parkingslots.entrySet()) {
			ParkingSpace pkspace = entry.getValue();
			if(pkspace.getVehicle().getColor().equals(color)) {				
				str = str + entry.getKey() + ", ";
			}			
		}
		str = str.replaceAll(", $", "");
		if(str.length()==0) {
			str = "Not found";
		} else {
			str = str + "\n";			
		}
		return str;
	}
	
	public String slotByRegNo(String regNo) {
		String str = "";
		for (Map.Entry<Integer, ParkingSpace> entry : parkingslots.entrySet()) {
			ParkingSpace pkspace = entry.getValue();
			if(pkspace.getVehicle().getReg_no().equals(regNo)) {
				str = str + entry.getKey() + ", ";
			}			
		}
		str = str.replaceAll(", $", "");
		if(str.length()==0) {
			str = "Not found";
		} else {
			str = str+"\n";			
		}
		return str;
	}
	
}
