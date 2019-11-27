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
	
	/**
	 * This function set the game state
	 * @param gameState   it contains the game state
	 */
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}
	
	/**
	 * This function get the current player turn
	 * @return This returns the current player turn
	 */
	public String getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}
	
	/**
	 * This function set the current player turn
	 * @param currentPlayerTurn   it contains the current player turn
	 */
	public void setCurrentPlayerTurn(String currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}
	
	/**
	 * This function get the actions performed
	 * @return  This returns the actions performed
	 */
	public String getActionsPerformed() {
		return actionsPerformed;
	}
	
	/**
	 * This function set the actions performed
	 * @param actionsPerformed  it contains the actions performed
	 */
	public void setActionsPerformed(String actionsPerformed) {
		this.actionsPerformed = actionsPerformed;
	}
	
	/**
	 * This function get the attacker name
	 * @return  This returns the attacker name
	 */
	public String getAttackerName() {
		return attackerName;
	}
	
	/**
	 * This function set the attacker name
	 * @param attackerName  it contains the attacker name
	 */
	public void setAttackerName(String attackerName) {
		this.attackerName = attackerName;
	}
	
	/**
	 * This function get the defender name
	 * @return  This returns the defender name
	 */
	public String getDefenderName() {
		return defenderName;
	}
	
	/**
	 * This function set the defender name
	 * @param defenderName  it contains the defender name
	 */
	public void setDefenderName(String defenderName) {
		this.defenderName = defenderName;
	}
	
	/**
	 * This function get the attacker country
	 * @return  This returns the attacker country
	 */
	public String getAttackerCountry() {
		return attackerCountry;
	}
	
	/**
	 * This function set the attacker country
	 * @param attackerCountry  it contains the attacker country
	 */
	public void setAttackerCountry(String attackerCountry) {
		this.attackerCountry = attackerCountry;
	}
	
	/**
	 * This function get the defender country
	 * @return  This returns the defender country
	 */
	public String getDefenderCountry() {
		return defenderCountry;
	}
	
	/**
	 * This function set the defender country
	 * @param defenderCountry  it contains the defender country
	 */
	public void setDefenderCountry(String defenderCountry) {
		this.defenderCountry = defenderCountry;
	}
	
	/**
	 * This function get the attacker dice
	 * @return This returns tha attacker dice
	 */
	public ArrayList<Integer> getAttackerDice() {
		return attackerDice;
	}
	
	/**
	 * This function set the attacker dice
	 * @param attackerDice  it contains the attacker dice
	 */
	public void setAttackerDice(ArrayList<Integer> attackerDice) {
		this.attackerDice = attackerDice;
	}
	
	/**
	 * This function get the defender dice
	 * @return  This returns the defender dice
	 */
	public ArrayList<Integer> getDefenderDice() {
		return defenderDice;
	}
	
	/**
	 * This function set the defender dice
	 * @param defenderDice  it contains the defender dice
	 */
	public void setDefenderDice(ArrayList<Integer> defenderDice) {
		this.defenderDice = defenderDice;
	}
	
	/**
	 * This function get the dice rolled
	 * @return  This returns the dice rolled
	 */
	public int getDiceRolled() {
		return diceRolled;
	}
	
	/**
	 * This function set the dice rolled
	 * @param diceRolled  it contains the dice rolled
	 */
	public void setDiceRolled(Integer diceRolled) {
		this.diceRolled = diceRolled;
	}
	
	/**
	 * This function notify the observer
	 */
	public void notifyToObserver() {
		notifyObservers(this, this);
	}
	
	/**
	 * This function get the card bonus army
	 * @return  This returns the card bonus army
	 */
	public int getCardBonusArmy() {
		return cardBonusArmy;
	}
	
	/**
	 * This function set the card bonus army
	 * @param cardBonusArmy  it contains the card bonus army
	 */
	public void setCardBonusArmy(int cardBonusArmy) {
		this.cardBonusArmy = cardBonusArmy;
	}
	
	/**
	 * This function get the card reward
	 * @return This returns the card reward
	 */
	public int getCardReward() {
		return cardReward;
	}
	
	/**
	 * This function set the card reward
	 * @param cardReward  it contains the card reward
	 */
	public void setCardReward(int cardReward) {
		this.cardReward = cardReward;
	}
	
	/**
	 * This function get the available reinforce armies
	 * @return  This returns the available reinforce armies
	 */
	public int getAvailableReinforceArmies() {
		return availableReinforceArmies;
	}
	
	/**
	 * This function set the available reinforce armies
	 * @param availableArmies  it contains the available reinforce armies
	 */
	public void setAvailableReinforceArmies(int availableArmies) {
		this.availableReinforceArmies = availableArmies;
	}
	
	/**
	 * This function get the current card list
	 * @return  This returns the current card list
	 */
	public ArrayList<String> getCurrentCardList() {
		return currentCardList;
	}
	
	/**
	 * This function set the current card list
	 * @param currentCardList it contains the current card list
	 */
	public void setCurrentCardList(ArrayList<String> currentCardList) {
		this.currentCardList = currentCardList;
	}
	
	/**
	 * This function get the conquered countries
	 * @return This returns the conquered countries
	 */
	public ArrayList<String> getConqueredCountries() {
		return conqueredCountries;
	}
	
	/**
	 * This function set the conquered countries
	 * @param conqueredCountries it contains the conquered countries
	 */
	public void setConqueredCountries(ArrayList<String> conqueredCountries) {
		this.conqueredCountries = conqueredCountries;
	}
	
	/**
	 * This function contains the all out performed
	 * @return This returns the all out performed
	 */
	public boolean isAllOutPerformed() {
		return allOutAttackPerformed;
	}
	
	/**
	 * This function set the all out performed
	 * @param allOutPerformed  it contains the all out performed
	 */
	public void setAllOutPerformed(boolean allOutPerformed) {
		this.allOutAttackPerformed = allOutPerformed;
	}
}
