package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This model contains the properties of the players
 */
public class Player extends Observable {

	private ArrayList<String> ownedCountriesList;
	private ArrayList<Integer> ownedArmiesList;
	private HashMap<String, Integer> ownedCountriesArmiesList;
	private HashMap<String, ArrayList<String>> continentsCountryList;
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

	public HashMap<String, ArrayList<String>> getContinentsCountryList() {
		return continentsCountryList;
	}

	public void setContinentsCountryList(HashMap<String, ArrayList<String>> continentsCountryList) {
		this.continentsCountryList = continentsCountryList;
	}

	public String getStrategy() {
		return strategy;
	}

	public void setStrategy(String strategy) {
		this.strategy = strategy;
	}

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
