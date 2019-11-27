package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the map attributes
 */
public class GameMap {
	
	/**
	 * The first variable contains hashmap of continents
	 * The second variable contains hashmap of countries
	 * The third variable contains hashmap of boundaries
	 * The forth variable contains arraylist of total cards list
	 * The fifth variable contains arraylist of players setup
	 * The sixth variable contains hashmap of players with strategies
	 * The seventh variable contains the file type
	 * The eight variable contains the game mode
	 */
	private HashMap<Integer, Continents> continents;
	private HashMap<Integer, Countries> countries;
	private HashMap<Integer, ArrayList<Integer>> boundries;
	private ArrayList<String> totalCardsList;
	private ArrayList<String> playersSetup;
	private HashMap<String, String> playersWithStrategies;
	private String fileType;	

	/**
	 * @Default Constructor This method initiates the variables
	 * @author Sai Krishna
	 */
	public GameMap() {
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		totalCardsList = new ArrayList<String>();
		playersSetup = new ArrayList<String>();
		playersWithStrategies = new HashMap<String, String>();
		fileType="Domination";		
	}
	
	/**
	 * This function get the continents
	 * @return This returns the continents
	 * @author Ashish
	 */
	public HashMap<Integer, Continents> getContinents() {
		return continents;
	}
	
	/**
	 * This function set the continents
	 * @param continents  it contains the continents
	 * @author Ashish
	 */
	public void setContinents(HashMap<Integer, Continents> continents) {
		this.continents = continents;
	}
	
	/**
	 * This function get the countries
	 * @return This returns the countries
	 * @author sakib
	 */
	public HashMap<Integer, Countries> getCountries() {
		return countries;
	}
	
	/**
	 * This function set the countries
	 * @param countries  it contains the countries
	 * @author sakib
	 */
	public void setCountries(HashMap<Integer, Countries> countries) {
		this.countries = countries;
	}
	
	/**
	 * This function get the boundries
	 * @return This returns the boundries
	 * @author Gagan Jaswal
	 */
	public HashMap<Integer, ArrayList<Integer>> getBoundries() {
		return boundries;
	}
	
	/**
	 * This function set the boundries
	 * @param boundries  it contain the boundries
	 * @author Gagan Jaswal
	 */
	public void setBoundries(HashMap<Integer, ArrayList<Integer>> boundries) {
		this.boundries = boundries;
	}
	
	/**
	 * This function get the total cards list
	 * @return This returns the total cards list
	 * @author garimadawar
	 */
	public ArrayList<String> getTotalCardsList() {
		return totalCardsList;
	}
	
	/**
	 * This function set the total cards list
	 * @param totalCardsList  it contains the total cards list
	 * @author garimadawar
	 */
	public void setTotalCardsList(ArrayList<String> totalCardsList) {
		this.totalCardsList = totalCardsList;
	}
	
	/**
	 * This function get the player setup
	 * @return This returns the player with strategies
	 * @author Sai Krishna
	 */
	public ArrayList<String> getPlayersSetup() {
		return playersSetup;
	}
	
	/**
	 * This function set the player setup
	 * @param playersSetup  it contains the player setup
	 * @author Sai Krishna
	 */
	public void setPlayersSetup(ArrayList<String> playersSetup) {
		this.playersSetup = playersSetup;
	}
	
	/**
	 * This function get the player with strategies
	 * @return This returns the player with strategies
	 * @author Gagan Jaswal
	 */
	public HashMap<String, String> getPlayersWithStrategies() {
		return playersWithStrategies;
	}
	
	/**
	 * This function set the player with strategies
	 * @param playersWithStrategies  it contains the player with strategies
	 * @author Gagan Jaswal
	 */
	public void setPlayersWithStrategies(HashMap<String, String> playersWithStrategies) {
		this.playersWithStrategies = playersWithStrategies;
	}
	
	/**
	 * This function get the file type
	 * @return This returns the file type
	 * @author garimadawar
	 */
	public String getFileType() {
		return fileType;
	}
	
	/**
	 * This function set the file type
	 * @param fileType  it contains the file type
	 * @author garimadawar
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
}
