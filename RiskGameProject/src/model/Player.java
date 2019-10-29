package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This model contains the properties of the players
 */
public class Player {

	private ArrayList<String> ownedCountriesList;
	private ArrayList<Integer> ownedArmiesList;
	private HashMap<String, Integer> ownedCountriesArmiesList;
	private String currentPlayerTurn;
	
	/**
	 * @Default Constructor This method initiates the variables
	 */
	public Player() {
		ownedCountriesList = new ArrayList<String>();
		ownedArmiesList = new ArrayList<Integer>();
		ownedCountriesArmiesList = new HashMap<String, Integer>();
		currentPlayerTurn="";
	}

	public HashMap<String, Integer> getOwnedCountriesArmiesList() {
		return ownedCountriesArmiesList;
	}

	public void setOwnedCountriesArmiesList(HashMap<String, Integer> ownedCountriesArmiesList) {
		this.ownedCountriesArmiesList = ownedCountriesArmiesList;
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
	
	public String getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(String currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}
}
