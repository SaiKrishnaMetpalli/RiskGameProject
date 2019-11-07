package model;

/**
 * This File contains the Continents information
 * 
 */

public class Continents {

	private String continentControlValue, continentColour, continentName;

	public Continents(String continentName, String continentControlValue, String colour) {
		this.continentControlValue = continentControlValue;
		this.continentColour = colour;
		this.continentName = continentName;
	}

	public String getcontinentControlValue() {
		return continentControlValue;
	}

	public void setcontinentControlValue(String continentControlValue) {
		this.continentControlValue = continentControlValue;
	}

	public String getColour() {
		return continentColour;
	}

	public void setColour(String colour) {
		this.continentColour = colour;
	}

	public String getContinentName() {
		return continentName;
	}

	public void setContinent_Name(String continentName) {
		this.continentName = continentName;
	}

}
