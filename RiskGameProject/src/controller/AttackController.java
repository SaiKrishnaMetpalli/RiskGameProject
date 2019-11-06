package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import model.Countries;
import model.GameMap;
import model.Player;
import model.PlayersList;

public class AttackController {

	CommonController cc = new CommonController();
	ArrayList<Integer> adjancyList;
	HashMap<String, Integer> attackerArmiesMap;
	HashMap<String, Integer> defenderArmiesMap;
	ArrayList<Integer> attackerDiceNumbersList;
	ArrayList<Integer> defenderDiceNumbersList;
	ArrayList<String> countryList;
	HashMap<String, Integer> countryArmyList;
	ArrayList<Integer> armiesLeftAfterAttack;
	ArrayList<String> conqueredCountriesList;
	int diceRolledResult = 0;
	int numOfAttackerArmy = 0;
	int numOfDefenderArmy = 0;
	int attackerArmyLeft = 0;

	/*
	 * Method is used to validate whether the defender country is the adjacent
	 * neighboring country or not
	 */
	public boolean validateDefenderCountry(String attackerCountry, String defenderCountry,
			HashMap<Integer, Countries> countryList, HashMap<Integer, ArrayList<Integer>> boundries) {

		int attackerCountryNum = cc.getCountryNumberByName(countryList, attackerCountry);

		int defenderCountryNum = cc.getCountryNumberByName(countryList, defenderCountry);

		adjancyList = boundries.get(attackerCountryNum);

		if (adjancyList.contains(defenderCountryNum)) {
			return true;
		}

		return false;
	}

	public boolean validateNumDice(String attackerCountry, Integer numberOnDice, Player playerData) {

		attackerArmiesMap = playerData.getOwnedCountriesArmiesList();

		numOfAttackerArmy = attackerArmiesMap.get(attackerCountry);

		if (numberOnDice < numOfAttackerArmy) {
			return true;
		} else {
			return false;
		}

	}

	public String attackPhase(String attackerCountry, String defenderCountry, Integer numberOnDice, Player player) {

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);
		attackerDiceNumbersList = new ArrayList<Integer>();
		while (numberOnDice > 0) {
			diceRolledResult = randomNumbergenerator();
			attackerDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(attackerDiceNumbersList, Collections.reverseOrder());
		player.setAttackerDice(attackerDiceNumbersList);
		player.setDiceRolled(attackerDiceNumbersList.size());

		return "Attacker Ready and placed his army on field ";
	}

	public String allOutAttackedPhase(String attackerCountry, String defenderCountry, Player attackerPlayerData,
			HashMap<Integer, Countries> countryList, Player player, Player defenderPlayerData) {

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);

		String defenderPlayerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		String attackerPlayerName = cc.findPlayerNameFromCountry(countryList, attackerCountry);
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		int numOfAttackerArmy = attackerArmiesMap.get(attackerCountry);

		int numOfDiceCanRoll = 0;
		if (numOfAttackerArmy > 1) {
			while (numOfAttackerArmy > 1) {
				if (numOfAttackerArmy == 2) {
					numOfDiceCanRoll = 1;
				} else if (numOfAttackerArmy == 3) {
					numOfDiceCanRoll = 2;
				} else {
					numOfDiceCanRoll = 3;
				}

				int diceToRoll = numOfDiceCanRoll;
				attackerDiceNumbersList = new ArrayList<Integer>();

				while (diceToRoll != 0) {
					diceRolledResult = randomNumbergenerator();
					attackerDiceNumbersList.add(diceRolledResult);
					diceToRoll--;
				}
				Collections.sort(attackerDiceNumbersList, Collections.reverseOrder());
				player.setAttackerDice(attackerDiceNumbersList);

				numOfAttackerArmy = allOutDefend(defenderPlayerName, player.getDefenderCountry(), numOfAttackerArmy,
						countryList, defenderPlayerData, player, attackerPlayerData, attackerPlayerName,
						player.getAttackerCountry());

				if (defenderArmiesMap.get(player.getDefenderCountry()) == 0) {
					player.setDiceRolled(numOfDiceCanRoll); // numOfDiceCanRoll
					player.getConqueredCountries().add(player.getDefenderCountry());
					player.setAllOutPerformed(true);
					return "Attacker Won the " + player.getDefenderCountry() + " country";
				} else if (numOfAttackerArmy == 1) {
					player.setAllOutPerformed(true);
					return "Attacker cannot conquer defender country as he is left with 1 army in -allout mode";
				}
			}
			return "";
		} else {
			return "Attacker cannot perform attack as he is left with 1 army";
		}
	}

	public int allOutDefend(String defenderPlayerName, String defenderCountry, Integer attackerArmy,
			HashMap<Integer, Countries> countryList, Player defenderPlayerData, Player player,
			Player attackerPlayerData, String attackerPlayerName, String attackerCountryname) {

		defenderDiceNumbersList = new ArrayList<Integer>();

		numOfDefenderArmy = defenderArmiesMap.get(defenderCountry);

		int maxDefenderDiceToRoll = 0;

		if (player.getAttackerDice().size() == 1) {
			maxDefenderDiceToRoll = 1;
		} else {
			if (numOfDefenderArmy > 1) {
				maxDefenderDiceToRoll = 2;
			} else {
				maxDefenderDiceToRoll = 1;
			}
		}

		while (maxDefenderDiceToRoll != 0) {

			diceRolledResult = randomNumbergenerator();
			defenderDiceNumbersList.add(diceRolledResult); // check that the list is getting cleared in attack phase
			maxDefenderDiceToRoll--;

		}
		Collections.sort(defenderDiceNumbersList, Collections.reverseOrder());
		player.setDefenderDice(defenderDiceNumbersList);

		attackerArmyLeft = comparingDiceToWin(player, defenderArmiesMap, attackerArmiesMap, attackerPlayerData,
				defenderPlayerData, attackerCountryname, defenderCountry);

		return attackerArmyLeft;

	}

	public int comparingDiceToWin(Player p, HashMap<String, Integer> defenderArmiesMap,
			HashMap<String, Integer> attackerArmiesMap, Player attackerPlayerData, Player defenderPlayerData,
			String attackerCountryName, String defenderCountryName) {

		int defenderDiceRolled = p.getDefenderDice().size();
		while (defenderDiceRolled != 0) {
			int attackerNum = p.getAttackerDice().get(0);
			int defenderNum = p.getDefenderDice().get(0);

			numOfDefenderArmy = defenderArmiesMap.get(defenderCountryName);
			numOfAttackerArmy = attackerArmiesMap.get(attackerCountryName);
			if (defenderNum == attackerNum || defenderNum > attackerNum) {

				int attackerArmyLeft = attackerPlayerData.getOwnedCountriesArmiesList().get(attackerCountryName);
				attackerArmyLeft--;
				attackerPlayerData.getOwnedCountriesArmiesList().put(attackerCountryName, attackerArmyLeft);

			} else {
				int defenderArmyLeft = defenderPlayerData.getOwnedCountriesArmiesList().get(defenderCountryName);
				defenderArmyLeft--;
				defenderPlayerData.getOwnedCountriesArmiesList().put(defenderCountryName, defenderArmyLeft);
			}

			p.getAttackerDice().remove(0);
			p.getDefenderDice().remove(0);
			defenderDiceRolled--;
		}
		return attackerPlayerData.getOwnedCountriesArmiesList().get(p.getAttackerCountry());
	}

	public int randomNumbergenerator() {
		double random = Math.random();
		random = random * 6 + 1;
		int diceRoll = (int) random;
		return diceRoll;

	}

	/**
	 * This method is used for the validation of army moves.
	 * 
	 * @author Gagan Jaswal
	 */
	public boolean validateNumOfArmyMoves(Integer diceRolled, Integer moveArmy) {

		int diceRolledForWin = diceRolled;
		int armyToMove = moveArmy;

		if (armyToMove > diceRolledForWin || armyToMove == diceRolledForWin) {
			return true;
		}

		return false;
	}

	/**
	 * This method is used for moving army to conquered country.
	 * 
	 * @author Gagan Jaswal
	 */
	public String movingArmyToConqueredCountry(Integer armyToMove, HashMap<String, Player> playerData, Player player,
			GameMap gm) {

		int movingArmy = armyToMove;

		moveArmy(movingArmy, playerData, player);

		assigningCountryToAttacker(playerData, player, gm);

		return "Army Moved to Conquered Country";
	}

	/**
	 * This method is used for moving army.
	 * 
	 * @author Gagan Jaswal
	 */
	public boolean moveArmy(int movingArmy, HashMap<String, Player> playerData, Player player) {

		boolean flag = false;
		Player attackerData = playerData.get(player.getAttackerName());
		countryArmyList = attackerData.getOwnedCountriesArmiesList();
		int attackerArmy = countryArmyList.get(player.getAttackerCountry());

		int remainingArmy = attackerArmy - movingArmy;

		attackerData.getOwnedCountriesArmiesList().put(player.getAttackerCountry(), remainingArmy);

		Player defenderData = playerData.get(player.getDefenderName());
		countryArmyList = defenderData.getOwnedCountriesArmiesList();
		int defenderArmy = countryArmyList.get(player.getDefenderCountry());
		defenderArmy = movingArmy;
		flag = true;

		defenderData.getOwnedCountriesArmiesList().put(player.getDefenderCountry(), defenderArmy);

		return flag;
	}

	/**
	 * This method is used to calculate army left with attacker.
	 * 
	 * @author Gagan Jaswal
	 */
	public boolean armyLeftWithAttacker(Integer armyToMove, HashMap<String, Player> playerData, Player player) {
		int movedArmy = armyToMove;

		Player attackerData = playerData.get(player.getAttackerName());
		countryArmyList = attackerData.getOwnedCountriesArmiesList();
		int attackerArmy = countryArmyList.get(player.getAttackerCountry());

		int remainingArmy = attackerArmy - movedArmy;

		if (remainingArmy >= 1) {
			return true;
		}
		return false;
	}

	public void assigningCountryToAttacker(HashMap<String, Player> playerData, Player player, GameMap gm) {

		Player defenderData = playerData.get(player.getDefenderName());
		countryList = defenderData.getOwnedCountriesList();
		int defenderCountryIndex = countryList.indexOf(player.getDefenderCountry());
		countryList.remove(defenderCountryIndex);
		defenderData.setOwnedCountriesList(countryList);
		int defenderArmy = defenderData.getOwnedCountriesArmiesList().get(player.getDefenderCountry());
		defenderData.getOwnedCountriesArmiesList().remove(player.getDefenderCountry());
		// change country owner name

		Player attackerData = playerData.get(player.getAttackerName());
		countryList = attackerData.getOwnedCountriesList();
		countryList.add(player.getDefenderCountry());
		attackerData.getOwnedCountriesArmiesList().put(player.getDefenderCountry(), defenderArmy);
		attackerData.setOwnedCountriesList(countryList);

		int defenderCountryNum = cc.getCountryNumberByName(gm.getCountries(), player.getDefenderCountry());
		Countries c = gm.getCountries().get(defenderCountryNum);
		c.setOwnerName(player.getAttackerName());
	} // change country owner

	public boolean validateDefenderNumdice(String defenderCountry, Integer numberOnDice, Player defenderPlayerData) {

		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();
		int numArmy = defenderArmiesMap.get(defenderCountry);
		if (numberOnDice <= numArmy) {
			return true;
		} else {
			return false;
		}
	}

	public boolean defendPhaseDiceRoll(String defenderCountry, Integer numberOnDice, Player p) {

		defenderDiceNumbersList = new ArrayList<Integer>();
		while (numberOnDice != 0) {
			diceRolledResult = randomNumbergenerator();
			defenderDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(defenderDiceNumbersList, Collections.reverseOrder());
		p.setDefenderDice(defenderDiceNumbersList);

		return true;
	}

	public String defendingTheBase(Player p, PlayersList pl) {

		Player defenderPlayerData = pl.getListOfPlayers().get(p.getDefenderName());
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		Player attackerPlayerData = pl.getListOfPlayers().get(p.getAttackerName());
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		String attackerCountryName = p.getAttackerCountry();
		String defenderCountryName = p.getDefenderCountry();

		int attackerArmyLeft = comparingDiceToWin(p, defenderArmiesMap, attackerArmiesMap, attackerPlayerData,
				defenderPlayerData, attackerCountryName, defenderCountryName);

		if (defenderArmiesMap.get(p.getDefenderCountry()) == 0) {

			p.getConqueredCountries().add(p.getDefenderCountry());

			return "Attacker Won the " + p.getDefenderCountry() + " country";
		} else {
			return " Attacked Performed " + "; Armies left with Attacker = "
					+ attackerArmiesMap.get(p.getAttackerCountry()) + " and Armies Left with Defender =  "
					+ defenderArmiesMap.get(p.getDefenderCountry());
		}
	}
}
