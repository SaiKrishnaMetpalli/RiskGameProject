package model;

import java.util.ArrayList;

/**
 * This model contains the properties of the players
 */
public class Player {
	
	private ArrayList<String> ownedCountriesList;
	private ArrayList<String> ownedArmiesList;
	
	/**
	 * @Default Constructor
	 * This method initiates the variables
	 */
	public Player()
	{
		ownedCountriesList=new ArrayList<String>();
		ownedArmiesList=new ArrayList<String>();
	}

	public ArrayList<String> getOwnedCountriesList() {
		return ownedCountriesList;
	}

	public void setOwnedCountriesList(ArrayList<String> ownedCountriesList) {
		this.ownedCountriesList = ownedCountriesList;
	}

	public ArrayList<String> getOwnedArmiesList() {
		return ownedArmiesList;
	}

	public void setOwnedArmiesList(ArrayList<String> ownedArmiesList) {
		this.ownedArmiesList = ownedArmiesList;
	}
}
