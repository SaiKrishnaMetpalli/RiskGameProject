package model;

public class Countries {

	/**
	 * This File contains the Countries information
	 * 
	 * @author 14382
	 *
	 */
	private String country_Id;
	public String country_Name;
	private String country_Continent_Num;
	private String x_Coordinate;
	private String y_Coordinate;

	public Countries(String country_name, String country_Continent_Num, String x_Coordinate, String y_Coordinate) {
		this.country_Name = country_name;
		this.country_Continent_Num = country_Continent_Num;
		this.x_Coordinate = x_Coordinate;
		this.y_Coordinate = y_Coordinate;
	}

	public String getCountry_Id() {
		return country_Id;
	}

	public void setCountry_Id(String country_Id) {
		this.country_Id = country_Id;
	}

	public String getCountry_Name() {
		return country_Name;
	}

	public void setCountry_Name(String country_Name) {
		this.country_Name = country_Name;
	}

	public String getCountry_Continent_Num() {
		return country_Continent_Num;
	}

	public void setCountry_Continent_Num(String country_Continent_Num) {
		this.country_Continent_Num = country_Continent_Num;
	}

	public String getX_Coordinate() {
		return x_Coordinate;
	}

	public void setX_Coordinate(String x_Coordinate) {
		this.x_Coordinate = x_Coordinate;
	}

	public String getY_Coordinate() {
		return y_Coordinate;
	}

	public void setY_Coordinate(String y_Coordinate) {
		this.y_Coordinate = y_Coordinate;
	}

}
