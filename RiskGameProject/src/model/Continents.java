package model;
/**
 * This File contains the Continents information
 * 
 * @author 14382
 *
 */

public class Continents {

	private String continentControlValue , continentColour , continentName;
	
	public Continents(String continentControlValue , String colour)
	{
		this.continentControlValue = continentControlValue;
		this.continentColour = colour;
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

	public String getContinent_Name() {
		return continentName;
	}

	public void setContinent_Name(String continentName) {
		this.continentName = continentName;
	}
	
	
	
}
