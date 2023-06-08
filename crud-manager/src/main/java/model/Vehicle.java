package model;

public class Vehicle {

	private int vehicleid;
	private String color;
	private String brand;
	private String model;
	private String type;
	private String number_plate;
	private Company company;
	
	
	public Vehicle() {}
	
	public Vehicle(int vehicleid) {
		this.vehicleid = vehicleid;
	}

	public int getVehicleid() {
		return vehicleid;
	}

	public void setVehicleid(int vehicleid) {
		this.vehicleid = vehicleid;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNumber_plate() {
		return number_plate;
	}

	public void setNumber_plate(String number_plate) {
		this.number_plate = number_plate;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}

}
