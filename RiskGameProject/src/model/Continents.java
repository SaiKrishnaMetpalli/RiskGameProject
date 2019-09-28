package model;
/**
 * This File contains the Continents information
 * 
 * @author 14382
 *
 */

public class Continents {

	private String num_Of_Countries , colour , continent_Name;
	
	public Continents(String num_Of_Countries , String colour)
	{
		this.num_Of_Countries = num_Of_Countries;
		this.colour = colour;
	}

	public String getNum_Of_Countries() {
		return num_Of_Countries;
	}

	public void setNum_Of_Countries(String num_Of_Countries) {
		this.num_Of_Countries = num_Of_Countries;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public String getContinent_Name() {
		return continent_Name;
	}

	public void setContinent_Name(String continent_Name) {
		this.continent_Name = continent_Name;
	}
	
	
	
}
