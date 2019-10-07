package model;

import java.util.ArrayList;

/**
 * This model contains the properties of the players
 */
public class Player {
	
	ArrayList<String> ownedCountriesList;
	ArrayList<String> ownedArmiesList;
	
	/**
	 * @Default Constructor
	 * This method initiates the variables
	 */
	public Player()
	{
		ownedCountriesList=new ArrayList<String>();
		ownedArmiesList=new ArrayList<String>();
	}
}
