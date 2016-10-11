package main.java.com.gunendu.codingchallenge;


public class Vehicle {
	
	private final String color;
	private final String reg_no;
	
	public Vehicle(String color,String reg_no){
		this.color = color;
		this.reg_no = reg_no;
	}

	public String getColor() {
		return color;
	}

	public String getReg_no() {
		return reg_no;
	}
}
