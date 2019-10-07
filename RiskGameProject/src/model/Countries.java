package model;

/**
 * 
 * This class contains countries information
 *
 */
public class Countries {

	private String countryName;
	private Integer countryContinentNum;
	private String xCoordinate;
	private String yCoordinate;
	private String ownerName;

	/**
	 * Default Constructor
	 * This method is used to initiate the variables 
	 * 
	 * @param countryName this variable contains country name
	 * @param countryContinentNum this variable contains continent number
	 * @param xCoordinate this variable contains x coordinate number
	 * @param yCoordinate this variable contains y coordinate number
	 */
	public Countries(String countryName, Integer countryContinentNum, String xCoordinate, String yCoordinate) {

		this.countryName = countryName;
		this.countryContinentNum = countryContinentNum;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountry_Name(String countryName) {
		this.countryName = countryName;
	}

	public Integer getCountryContinentNum() {
		return countryContinentNum;
	}

	public void setCountry_Continent_Num(Integer countryContinentNum) {
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
