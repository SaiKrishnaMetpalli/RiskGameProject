package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the map attributes
 */
public class GameMap {
	private HashMap<Integer, Continents> continents;
	private HashMap<Integer, Countries> countries;
	private HashMap<Integer, ArrayList<Integer>> boundries;
	private ArrayList<String> totalCardsList;
	private ArrayList<String> playersSetup;
	private HashMap<String, String> playersWithStrategies;

	/**
	 * @Default Constructor This method initiates the variables
	 */
	public GameMap() {
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		totalCardsList = new ArrayList<String>();
		playersSetup = new ArrayList<String>();
		playersWithStrategies = new HashMap<String, String>();
	}

	public HashMap<Integer, Continents> getContinents() {
		return continents;
	}

	public void setContinents(HashMap<Integer, Continents> continents) {
		this.continents = continents;
	}

	public HashMap<Integer, Countries> getCountries() {
		return countries;
	}

	public void setCountries(HashMap<Integer, Countries> countries) {
		this.countries = countries;
	}

	public HashMap<Integer, ArrayList<Integer>> getBoundries() {
		return boundries;
	}

	public void setBoundries(HashMap<Integer, ArrayList<Integer>> boundries) {
		this.boundries = boundries;
	}

	public ArrayList<String> getTotalCardsList() {
		return totalCardsList;
	}

	public void setTotalCardsList(ArrayList<String> totalCardsList) {
		this.totalCardsList = totalCardsList;
	}

	public ArrayList<String> getPlayersSetup() {
		return playersSetup;
	}

	public void setPlayersSetup(ArrayList<String> playersSetup) {
		this.playersSetup = playersSetup;
	}

	public HashMap<String, String> getPlayersWithStrategies() {
		return playersWithStrategies;
	}

	public void setPlayersWithStrategies(HashMap<String, String> playersWithStrategies) {
		this.playersWithStrategies = playersWithStrategies;
	}
}
