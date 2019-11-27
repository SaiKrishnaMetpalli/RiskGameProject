package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This model contains the properties of the players
 */
public class Player extends Observable {

	/**
	 * The ownedCountriesList variable contains the owned countries list The
	 * ownedArmiesList variable contains the owned armies list The
	 * ownedCountriesArmiesList variable contains owned countries armies list The
	 * continentsCountryList variable contains the continents country list The
	 * totalCountries variable contains the total countries The strategy variable
	 * contains the strategy The gameState variable contains the game state The
	 * currentPlayerTurn variable contains the current player turn The
	 * actionsPerformed variable contains the actions performed The attackerName
	 * variable contains the attacker name The defenderName variable contains the
	 * defender name The attackerCountry variable contains the attacker country The
	 * defenderCountry variable contains the defender country The attackerDice
	 * variable contains the arraylist of attacker dice The defenderDice variable
	 * contains the arraylist of defender dice The diceRolled variable contains the
	 * dice rolled The cardBonusArmy variable contains the card bonus army The
	 * cardReward variable contains the card reward The availableReinforceArmies
	 * variable contains the available reinforce armies The currentCardList variable
	 * contains the current card list The conqueredCountries variable contains the
	 * conquered countries The allOutAttackPerformed variable contains the all out
	 * attack performed
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
	 * @author Sai Krishna
	 */
	public Player() {
		ownedCountriesList = new ArrayList<String>();
		ownedArmiesList = new ArrayList<Integer>();
		ownedCountriesArmiesList = new HashMap<String, Integer>();
		continentsCountryList = new HashMap<String, ArrayList<String>>();
		totalCountries = 0;
		gameState = "STARTUP";
		currentPlayerTurn = "";
		attackerName = "";
		defenderName = "";
		attackerCountry = "";
		defenderCountry = "";
		attackerDice = new ArrayList<Integer>();
		defenderDice = new ArrayList<Integer>();
		diceRolled = 0;
		cardBonusArmy = 0;
		cardReward = 0;
		availableReinforceArmies = 0;
		currentCardList = new ArrayList<String>();
		conqueredCountries = new ArrayList<String>();
		setAllOutPerformed(false);
	}

	/**
	 * This function get the owned countries armies list
	 * 
	 * @return This returns the owned countries armies list
	 * @author Sai Krishna
	 */
	public HashMap<String, Integer> getOwnedCountriesArmiesList() {
		return ownedCountriesArmiesList;
	}

	/**
	 * This function set the owned countries armies list
	 * 
	 * @param ownedCountriesArmiesList it contains the owned countries armies list
	 * @author Sai Krishna
	 */
	public void setOwnedCountriesArmiesList(HashMap<String, Integer> ownedCountriesArmiesList) {
		this.ownedCountriesArmiesList = ownedCountriesArmiesList;
	}

	/**
	 * This function get the countries list
	 * 
	 * @return This returns the countries list
	 * @author Ashish
	 */
	public ArrayList<String> getOwnedCountriesList() {
		return ownedCountriesList;
	}

	/**
	 * This function set the owned countries list
	 * 
	 * @param ownedCountriesList it contains the owned countries list
	 * @author Ashish
	 */
	public void setOwnedCountriesList(ArrayList<String> ownedCountriesList) {
		this.ownedCountriesList = ownedCountriesList;
	}

	/**
	 * This function get the owned armies list
	 * 
	 * @return This returns the owned armies list
	 * @author Gagan Jaswal
	 */
	public ArrayList<Integer> getOwnedArmiesList() {
		return ownedArmiesList;
	}

	/**
	 * This function set the owned armies list
	 * 
	 * @param ownedArmiesList it contains the owned armies list
	 * @author Gagan Jaswal
	 */
	public void setOwnedArmiesList(ArrayList<Integer> ownedArmiesList) {
		this.ownedArmiesList = ownedArmiesList;
	}

	/**
	 * This function get the continent country list
	 * 
	 * @return This returns the continent country list
	 * @author garimadawar
	 */
	public HashMap<String, ArrayList<String>> getContinentsCountryList() {
		return continentsCountryList;
	}

	/**
	 * This function set the continents country list
	 * 
	 * @param continentsCountryList it contains the continents country list
	 * @author garimadawar
	 */
	public void setContinentsCountryList(HashMap<String, ArrayList<String>> continentsCountryList) {
		this.continentsCountryList = continentsCountryList;
	}

	/**
	 * This function get the total countries
	 * 
	 * @return it returns the total countries
	 * @author sakib
	 */
	public int getTotalCountries() {
		return totalCountries;
	}

	/**
	 * This function set the total countries
	 * 
	 * @param totalCountries it contains the total countries
	 * @author sakib
	 */
	public void setTotalCountries(int totalCountries) {
		this.totalCountries = totalCountries;
	}

	/**
	 * This function get the strategy
	 * 
	 * @return This returns the strategy
	 * @author Sai Krishna
	 */
	public String getStrategy() {
		return strategy;
	}

	/**
	 * This function set the strategy
	 * 
	 * @param strategy it contains the strategy
	 * @author Sai Krishna
	 */
	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

	/**
	 * This function get the game state
	 * 
	 * @return This returns the game state
	 * @author Ashish
	 */
	public String getGameState() {
		return gameState;
	}

	/**
	 * This function set the game state
	 * 
	 * @param gameState it contains the game state
	 * @author Ashish
	 */
	public void setGameState(String gameState) {
		this.gameState = gameState;
	}

	/**
	 * This function get the current player turn
	 * 
	 * @return This returns the current player turn
	 * @author Gagan Jaswal
	 */
	public String getCurrentPlayerTurn() {
		return currentPlayerTurn;
	}

	/**
	 * This function set the current player turn
	 * 
	 * @param currentPlayerTurn it contains the current player turn
	 * @author Gagan Jaswal
	 */
	public void setCurrentPlayerTurn(String currentPlayerTurn) {
		this.currentPlayerTurn = currentPlayerTurn;
	}

	/**
	 * This function get the actions performed
	 * 
	 * @return This returns the actions performed
	 * @author sakib
	 */
	public String getActionsPerformed() {
		return actionsPerformed;
	}

	/**
	 * This function set the actions performed
	 * 
	 * @param actionsPerformed it contains the actions performed
	 * @author sakib
	 */
	public void setActionsPerformed(String actionsPerformed) {
		this.actionsPerformed = actionsPerformed;
	}

	/**
	 * This function get the attacker name
	 * 
	 * @return This returns the attacker name
	 * @author garimadawar
	 */
	public String getAttackerName() {
		return attackerName;
	}

	/**
	 * This function set the attacker name
	 * 
	 * @param attackerName it contains the attacker name
	 * @author garimadawar
	 */
	public void setAttackerName(String attackerName) {
		this.attackerName = attackerName;
	}

	/**
	 * This function get the defender name
	 * 
	 * @return This returns the defender name
	 * @author Ashish
	 */
	public String getDefenderName() {
		return defenderName;
	}

	/**
	 * This function set the defender name
	 * 
	 * @param defenderName it contains the defender name
	 * @author Ashish
	 */
	public void setDefenderName(String defenderName) {
		this.defenderName = defenderName;
	}

	/**
	 * This function get the attacker country
	 * 
	 * @return This returns the attacker country
	 * @author sakib
	 */
	public String getAttackerCountry() {
		return attackerCountry;
	}

	/**
	 * This function set the attacker country
	 * 
	 * @param attackerCountry it contains the attacker country
	 * @author sakib
	 */
	public void setAttackerCountry(String attackerCountry) {
		this.attackerCountry = attackerCountry;
	}

	/**
	 * This function get the defender country
	 * 
	 * @return This returns the defender country
	 * @author garimadawar
	 */
	public String getDefenderCountry() {
		return defenderCountry;
	}

	/**
	 * This function set the defender country
	 * 
	 * @param defenderCountry it contains the defender country
	 * @author garimadawar
	 */
	public void setDefenderCountry(String defenderCountry) {
		this.defenderCountry = defenderCountry;
	}

	/**
	 * This function get the attacker dice
	 * 
	 * @return This returns tha attacker dice
	 * @author Gagan Jaswal
	 */
	public ArrayList<Integer> getAttackerDice() {
		return attackerDice;
	}

	/**
	 * This function set the attacker dice
	 * 
	 * @param attackerDice it contains the attacker dice
	 * @author Gagan Jaswal
	 */
	public void setAttackerDice(ArrayList<Integer> attackerDice) {
		this.attackerDice = attackerDice;
	}

	/**
	 * This function get the defender dice
	 * 
	 * @return This returns the defender dice
	 * @author Sai Krishna
	 */
	public ArrayList<Integer> getDefenderDice() {
		return defenderDice;
	}

	/**
	 * This function set the defender dice
	 * 
	 * @param defenderDice it contains the defender dice
	 * @author Sai Krishna
	 */
	public void setDefenderDice(ArrayList<Integer> defenderDice) {
		this.defenderDice = defenderDice;
	}

	/**
	 * This function get the dice rolled
	 * 
	 * @return This returns the dice rolled
	 * @author Ashish
	 */
	public int getDiceRolled() {
		return diceRolled;
	}

	/**
	 * This function set the dice rolled
	 * 
	 * @param diceRolled it contains the dice rolled
	 * @author Ashish
	 */
	public void setDiceRolled(Integer diceRolled) {
		this.diceRolled = diceRolled;
	}

	/**
	 * This function notify the observer
	 * 
	 * @author Sai Krishna
	 */
	public void notifyToObserver() {
		notifyObservers(this, this);
	}

	/**
	 * This function get the card bonus army
	 * 
	 * @return This returns the card bonus army
	 * @author Sai Krishna
	 */
	public int getCardBonusArmy() {
		return cardBonusArmy;
	}

	/**
	 * This function set the card bonus army
	 * 
	 * @param cardBonusArmy it contains the card bonus army
	 * @author Gagan Jaswal
	 */
	public void setCardBonusArmy(int cardBonusArmy) {
		this.cardBonusArmy = cardBonusArmy;
	}

	/**
	 * This function get the card reward
	 * 
	 * @return This returns the card reward
	 * @author Ashish
	 */
	public int getCardReward() {
		return cardReward;
	}

	/**
	 * This function set the card reward
	 * 
	 * @param cardReward it contains the card reward
	 * @author garimadawar
	 */
	public void setCardReward(int cardReward) {
		this.cardReward = cardReward;
	}

	/**
	 * This function get the available reinforce armies
	 * 
	 * @return This returns the available reinforce armies
	 * @author garimadawar
	 */
	public int getAvailableReinforceArmies() {
		return availableReinforceArmies;
	}

	/**
	 * This function set the available reinforce armies
	 * 
	 * @param availableArmies it contains the available reinforce armies
	 * @author Sai Krishna
	 */
	public void setAvailableReinforceArmies(int availableArmies) {
		this.availableReinforceArmies = availableArmies;
	}

	/**
	 * This function get the current card list
	 * 
	 * @return This returns the current card list
	 * @author Sai Krishna
	 */
	public ArrayList<String> getCurrentCardList() {
		return currentCardList;
	}

	/**
	 * This function set the current card list
	 * 
	 * @param currentCardList it contains the current card list
	 * @author Gagan Jaswal
	 */
	public void setCurrentCardList(ArrayList<String> currentCardList) {
		this.currentCardList = currentCardList;
	}

	/**
	 * This function get the conquered countries
	 * 
	 * @return This returns the conquered countries
	 * @author sakib
	 */
	public ArrayList<String> getConqueredCountries() {
		return conqueredCountries;
	}

	/**
	 * This function set the conquered countries
	 * 
	 * @param conqueredCountries it contains the conquered countries
	 * @author sakib
	 */
	public void setConqueredCountries(ArrayList<String> conqueredCountries) {
		this.conqueredCountries = conqueredCountries;
	}

	/**
	 * This function contains the all out performed
	 * 
	 * @return This returns the all out performed
	 * @author Sai Krishna
	 */
	public boolean isAllOutPerformed() {
		return allOutAttackPerformed;
	}

	/**
	 * This function set the all out performed
	 * 
	 * @param allOutPerformed it contains the all out performed
	 * @author Ashish
	 */
	public void setAllOutPerformed(boolean allOutPerformed) {
		this.allOutAttackPerformed = allOutPerformed;
	}
}
