package model;

/**
 * 
 * This class contains countries information
 *
 */
public class Countries {
	
	/**
	 * The first variable contains the country name
	 * The second variable contains the country continent number
	 * The third variable contains the x coordinate
	 * The forth variable contains the y coordinate
	 * The fifth variable contains the owner name
	 */
	private String countryName;
	private Integer countryContinentNum;
	private String xCoordinate;
	private String yCoordinate;
	private String ownerName;
	// private int currentArmyPlaced;

	/**
	 * Default Constructor This method is used to initiate the variables
	 * 
	 * @param countryName         this variable contains country name
	 * @param countryContinentNum this variable contains continent number
	 * @param xCoordinate         this variable contains x coordinate number
	 * @param yCoordinate         this variable contains y coordinate number
	 */
	public Countries(String countryName, Integer countryContinentNum, String xCoordinate, String yCoordinate) {

		this.countryName = countryName;
		this.countryContinentNum = countryContinentNum;
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.ownerName="";
	}
	
	/**
	 * The function get the country name
	 * @return This returns the country name
	 */
	public String getCountryName() {
		return countryName;
	}
	
	/**
	 * The function set the country name
	 * @param countryName
	 */
	public void setCountry_Name(String countryName) {
		this.countryName = countryName;
	}
	
	/**
	 * The function get the country continent number
	 * @return This returns the country continent number
	 */
	public Integer getCountryContinentNum() {
		return countryContinentNum;
	}
	
	/**
	 * The function set the country continent number
	 * @param countryContinentNum
	 */
	public void setCountry_Continent_Num(Integer countryContinentNum) {
		this.countryContinentNum = countryContinentNum;
	}
	
	/**
	 * The function get the x coordinate
	 * @return This returns the x coordinate
	 */
	public String getxCoordinate() {
		return xCoordinate;
	}
	
	/**
	 * The function set the x coordinate
	 * @param xCoordinate
	 */
	public void setX_Coordinate(String xCoordinate) {
		this.xCoordinate = xCoordinate;
	}
	
	/**
	 * The function get the y coordinate
	 * @return This returns the y coordinate
	 */
	public String getyCoordinate() {
		return yCoordinate;
	}
	
	/**
	 * The function set the y coordinate
	 * @param yCoordinate
	 */
	public void setyCoordinate(String yCoordinate) {
		this.yCoordinate = yCoordinate;
	}
	
	/**
	 * The function get the owner name
	 * @return This returns the owner name
	 */
	public String getOwnerName() {
		return ownerName;
	}
	
	/**
	 * The function set the owner name
	 * @param ownerName
	 */
	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}
}
