package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This model contains the properties of the players
 */
public class Player extends Observable {
	
	/**
	 * The first variable contains the owned countries list
	 * The second variable contains the owned armies list
	 * The third variable contains owned countries armies list
	 * The forth variable contains the continents country list
	 * The fifth variable contains the total countries
	 * The sixth variable contains the strategy
	 * The seventh variable contains the game state
	 * The eigth varibale contains the current player turn
	 * The ninth variable contains the actions performed
	 * The tenth variable contains the attacker name
	 * The eleventh variable contains the defender name
	 * The twelth variable contains the attacker country
	 * The thirteenth variable contains the defender country
	 * The fourteenth variable contains the arraylist of attacker dice
	 * The fifteenth variable contains the arraylist of defender dice
	 * The sixteenth variable contains the dice rolled
	 * The seventeenth variable contains the card bonus army
	 * The eighteenth variable contains the card reward
	 * The nighteenth variable contains the available reinforce armies
	 * The twentieth variable contains the current card list
	 * The twenty first variable contains the conquered countries
	 * The twenty second variable contains the all out attack performed
	 */
	private ArrayList<String> ownedCountriesList;
	private ArrayList<Integer> ownedArmiesList;
	private HashMap<String, Integer> ownedCountriesArmiesList;
	private HashMap<String, ArrayList<String>> continentsCountryList;
	private int totalCountries;
	private String strategy;
	private String gameState;
	private String currentPlayerTurn;
	private String actionsPerformed;
	private String attackerName;
	private String defenderName;
	private String attackerCountry;
	private String defenderCountry;
	private ArrayList<Integer> attackerDice;
	private ArrayList<Integer> defenderDice;
	private int diceRolled;
	private int cardBonusArmy;
	private int cardReward;
	private int availableReinforceArmies;
	private ArrayList<String> currentCardList;
	private ArrayList<String> conqueredCountries;
	private boolean allOutAttackPerformed;

	/**
	 * @Default Constructor This method initiates the variables
	 */
	public Player() {
		ownedCountriesList = new ArrayList<String>();
		ownedArmiesList = new ArrayList<Integer>();
		ownedCountriesArmiesList = new HashMap<String, Integer>();
		continentsCountryList=new HashMap<String, ArrayList<String>>();
		totalCountries=0;
		gameState = "STARTUP";
		currentPlayerTurn = "";
		attackerName = "";
		defenderName = "";
		attackerCountry = "";
		defenderCountry = "";
		attackerDice = new ArrayList<Integer>();
		defenderDice = new ArrayList<Integer>();
		diceRolled=0;
		cardBonusArmy = 0;
		cardReward = 0;
		availableReinforceArmies = 0;
		currentCardList = new ArrayList<String>();		
		conqueredCountries = new ArrayList<String>();
		setAllOutPerformed(false);
	}
	
	/**
	 * This function get the owned countries armies list
	 * @return This returns the owned countries armies list
	 */
	public HashMap<String, Integer> getOwnedCountriesArmiesList() {
		return ownedCountriesArmiesList;
	}
	
	/**
	 * This function set the owned countries armies list
	 * @param ownedCountriesArmiesList  it contains the owned countries armies list
	 */
	public void setOwnedCountriesArmiesList(HashMap<String, Integer> ownedCountriesArmiesList) {
		this.ownedCountriesArmiesList = ownedCountriesArmiesList;
	}
	
	/**
	 * This function get the countries list
	 * @return This returns the countries list
	 */
	public ArrayList<String> getOwnedCountriesList() {
		return ownedCountriesList;
	}
	
	/**
	 * This function set the owned countries list
	 * @param ownedCountriesList  it contains the owned countries list
	 */
	public void setOwnedCountriesList(ArrayList<String> ownedCountriesList) {
		this.ownedCountriesList = ownedCountriesList;
	}
	
	/**
	 * This function get the owned armies list
	 * @return This returns the owned armies list
	 */
	public ArrayList<Integer> getOwnedArmiesList() {
		return ownedArmiesList;
	}
	
	/**
	 * This function set the owned armies list
	 * @param ownedArmiesList  it contains the owned armies list
	 */
	public void setOwnedArmiesList(ArrayList<Integer> ownedArmiesList) {
		this.ownedArmiesList = ownedArmiesList;
	}
	
	/**
	 * This function get the continent country list
	 * @return This returns the continent country list
	 */
	public HashMap<String, ArrayList<String>> getContinentsCountryList() {
		return continentsCountryList;
	}
	
	/**
	 * This function set the continents country list
	 * @param continentsCountryList  it contains the continents country list
	 */
	public void setContinentsCountryList(HashMap<String, ArrayList<String>> continentsCountryList) {
		this.continentsCountryList = continentsCountryList;
	}
	
	/**
	 * This function get the total countries 
	 * @return  it returns the total countries
	 */
	public int getTotalCountries() {
		return totalCountries;
	}
	
	/**
	 * This function set the total countries
	 * @param totalCountries  it contains the total countries
	 */
	public void setTotalCountries(int totalCountries) {
		this.totalCountries = totalCountries;
	}
	
	/**
	 * This function get the strategy
	 * @return This returns the strategy
	 */
	public String getStrategy() {
		return strategy;
	}
	
	/**
	 * This function set the strategy
	 * @param strategy  it contains the strategy
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}
	
	/**
	 * This function get the game state
	 * @return This returns the game state
	 */
	public String getGameState() {
		return gameState;
	}

	public void setGameState(String gameState) {
		this.gameState = gameState;
	}

	public String getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	public void setCurrentPlayerTurn(String currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	public String getActionsPerformed() {
		return actionsPerformed;
	}

	public void setActionsPerformed(String actionsPerformed) {
		this.actionsPerformed = actionsPerformed;
	}

	public String getAttackerName() {
		return attackerName;
	}

	public void setAttackerName(String attackerName) {
		this.attackerName = attackerName;
	}

	public String getDefenderName() {
		return defenderName;
	}

	public void setDefenderName(String defenderName) {
		this.defenderName = defenderName;
	}

	public String getAttackerCountry() {
		return attackerCountry;
	}

	public void setAttackerCountry(String attackerCountry) {
		this.attackerCountry = attackerCountry;
	}

	public String getDefenderCountry() {
		return defenderCountry;
	}

	public void setDefenderCountry(String defenderCountry) {
		this.defenderCountry = defenderCountry;
	}

	public ArrayList<Integer> getAttackerDice() {
		return attackerDice;
	}

	public void setAttackerDice(ArrayList<Integer> attackerDice) {
		this.attackerDice = attackerDice;
	}

	public ArrayList<Integer> getDefenderDice() {
		return defenderDice;
	}

	public void setDefenderDice(ArrayList<Integer> defenderDice) {
		this.defenderDice = defenderDice;
	}

	public int getDiceRolled() {
		return diceRolled;
	}

	public void setDiceRolled(Integer diceRolled) {
		this.diceRolled = diceRolled;
	}

	public void notifyToObserver() {
		notifyObservers(this, this);
	}

	public int getCardBonusArmy() {
		return cardBonusArmy;
	}

	public void setCardBonusArmy(int cardBonusArmy) {
		this.cardBonusArmy = cardBonusArmy;
	}

	public int getCardReward() {
		return cardReward;
	}

	public void setCardReward(int cardReward) {
		this.cardReward = cardReward;
	}

	public int getAvailableReinforceArmies() {
		return availableReinforceArmies;
	}

	public void setAvailableReinforceArmies(int availableArmies) {
		this.availableReinforceArmies = availableArmies;
	}

	public ArrayList<String> getCurrentCardList() {
		return currentCardList;
	}

	public void setCurrentCardList(ArrayList<String> currentCardList) {
		this.currentCardList = currentCardList;
	}

	public ArrayList<String> getConqueredCountries() {
		return conqueredCountries;
	}

	public void setConqueredCountries(ArrayList<String> conqueredCountries) {
		this.conqueredCountries = conqueredCountries;
	}

	public boolean isAllOutPerformed() {
		return allOutAttackPerformed;
	}

	public void setAllOutPerformed(boolean allOutPerformed) {
		this.allOutAttackPerformed = allOutPerformed;
	}
}
