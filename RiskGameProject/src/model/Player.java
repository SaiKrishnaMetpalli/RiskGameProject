package model;

import java.util.ArrayList;

/**
 * This model contains the properties of the players
 */
public class Player {
	
	private ArrayList<String> ownedCountriesList;
	private ArrayList<Integer> ownedArmiesList;
	
	/**
	 * @Default Constructor
	 * This method initiates the variables
	 */
	public Player()
	{
		ownedCountriesList=new ArrayList<String>();
		ownedArmiesList=new ArrayList<Integer>();
	}

	public ArrayList<String> getOwnedCountriesList() {
		return ownedCountriesList;
	}

	public void setOwnedCountriesList(ArrayList<String> ownedCountriesList) {
		this.ownedCountriesList = ownedCountriesList;
	}

	public ArrayList<Integer> getOwnedArmiesList() {
		return ownedArmiesList;
	}

	public void setOwnedArmiesList(ArrayList<Integer> ownedArmiesList) {
		this.ownedArmiesList = ownedArmiesList;
	}
}
