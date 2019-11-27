package model;

/**
 * This class contains the Continents information
 * 
 */

public class Continents {

	/**
	 * The first variable contains the continent control value
	 * The second variable contains the continent colour
	 * The third variable contains the continent name
	 */
	private String continentControlValue, continentColour, continentName;

	/**
	 * @Default Constructor
	 * @param continentName  it contains the continent name
	 * @param continentControlValue  it contains the continent control value
	 * @param colour it contains the colour
	 * @author Sai Krishna
	 */
	public Continents(String continentName, String continentControlValue, String colour) {
		this.continentControlValue = continentControlValue;
		this.continentColour = colour;
		this.continentName = continentName;
	}

	/**
	 * This function get the continent control value
	 * @return This returns continent control value
	 * @author Sai Krishna
	 */
	public String getcontinentControlValue() {
		return continentControlValue;
	}
	
	/**
	 * 	This function set the continent control value
	 *	@param continentControlValue  it contains the continent control value
	 *	@author Sai Krishna
	 */
	public void setcontinentControlValue(String continentControlValue) {
		this.continentControlValue = continentControlValue;
	}
	
	/**
	 * This function get the continent colour
	 * @return This returns the continent colour
	 * @author Gagan Jaswal
	 */
	public String getColour() {
		return continentColour;
	}
	
	/**
	 * This function set the continent colour
	 * @param colour  it contains the colour
	 * @author Gagan Jaswal
	 */
	public void setColour(String colour) {
		this.continentColour = colour;
	}
	
	/**
	 * This function get the continent name
	 * @return This returns the continent name
	 * @author Ashish
	 */
	public String getContinentName() {
		return continentName;
	}
	
	/**
	 * This function set the continent name
	 * @param continentName  it contains the continent name
	 * @author Ashish
	 */
	public void setContinent_Name(String continentName) {
		this.continentName = continentName;
	}

}
