package model;

public class Countries {

	/**
	 * This File contains the Countries information
	 * 
	 * @author 14382
	 *
	 */
	private String countryId;
	private String countryName;
	private String countryContinentNum;
	private String xCoordinate;
	private String yCoordinate;

	public Countries(String countryId ,String countryName, String countryContinentNum, String xCoordinate, String yCoordinate) {
		this.countryId = countryId;
		this.countryName = countryName;
		this.countryContinentNum = countryContinentNum;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getCountry_Id() {
		return countryId;
	}

	public void setCountry_Id(String countryId) {
		this.countryId = countryId;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountry_Name(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryContinentNum() {
		return countryContinentNum;
	}

	public void setCountry_Continent_Num(String countryContinentNum) {
		this.countryContinentNum = countryContinentNum;
	}

	public String getxCoordinate() {
		return xCoordinate;
	}

	public void setX_Coordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}

	public String getyCoordinate() {
		return yCoordinate;
	}

	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

}
