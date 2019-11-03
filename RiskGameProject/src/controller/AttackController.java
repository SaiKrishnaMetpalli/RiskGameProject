package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

import model.Countries;
import model.Player;

public class AttackController {

	CommonController cc = new CommonController();
	ArrayList<Integer> adjancyList;
	HashMap<String, Integer> attackerArmiesMap;
	HashMap<String, Integer> defenderArmiesMap;
	ArrayList<Integer> attackerDiceNumbersList;
	ArrayList<Integer> defenderDiceNumbersList;
	ArrayList<String> countryList;
	HashMap<String,Integer> countryArmyList;
	int diceRolledResult = 0;
	int numOfAttackerArmy = 0;
	int numOfDefenderArmy = 0;

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

	public boolean validateNumDice(String attackerCountry, Integer numberOnDice, Player playerData,
			HashMap<Integer, Countries> countryList) {

		String playerName = cc.findPlayerNameFromCountry(countryList, attackerCountry);

		attackerArmiesMap = playerData.getOwnedCountriesArmiesList();

		numOfAttackerArmy = attackerArmiesMap.get(playerName);

		if (numOfAttackerArmy > 1 && numberOnDice <= 3 && numberOnDice > 0) {
			if (numOfAttackerArmy == 2) {
				if (numberOnDice == 1) {
					return true;
				}

			} else if (numOfAttackerArmy == 3) {
				if (numberOnDice > 0 && numberOnDice <= 2) {
					return true;
				}
			} else if (numOfAttackerArmy == 4) {
				if (numberOnDice > 0 && numberOnDice <= 3) {
					return true;
				}
			} else if (numOfAttackerArmy > 4) {
				if (numberOnDice > 0 && numberOnDice <= 3) {
					return true;
				}
			}

		} else {
			return false;
		}

		return false;
	}

	public String attackPhase(String attackerCountry, String defenderCountry, Integer numberOnDice, Player p) {

		p.setAttackerCountry(attackerCountry);
		p.setDefenderCountry(defenderCountry);

		attackerDiceNumbersList = new ArrayList<Integer>(); // check this with SAI
		while (numberOnDice > 0) {
			diceRolledResult = randomNumbergenerator();
			attackerDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(attackerDiceNumbersList);
		p.setAttackerDice(attackerDiceNumbersList);

		return "Attacker ready to attack the Country";
	}

	public String allOutAttackedPhase(String attackerCountry, String defenderCountry, Player attackerPlayerData,
			HashMap<Integer, Countries> countryList, Player p, Player defenderPlayerData) {

		attackerDiceNumbersList = new ArrayList<Integer>(); // check this with SAI

		attackerPlayerData.setAttackerCountry(attackerCountry);
		attackerPlayerData.setDefenderCountry(defenderCountry);

		String defenderPlayerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		String attackerPlayerName = cc.findPlayerNameFromCountry(countryList, attackerCountry);
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		int numOfAttackerArmy = attackerArmiesMap.get(attackerPlayerName);

		int numOfDiceCanRoll = 0;
		while (numOfAttackerArmy >= 1) {
			if (numOfAttackerArmy == 1) {
				numOfDiceCanRoll = 0;
			} else if (numOfAttackerArmy == 2) {
				numOfDiceCanRoll = 1;
			} else if (numOfAttackerArmy == 3) {
				numOfDiceCanRoll = 2;
			} else if (numOfAttackerArmy == 4) {
				numOfDiceCanRoll = 3;
			} else if (numOfAttackerArmy > 4) {
				numOfDiceCanRoll = 3;
			}

			int diceToRoll = numOfDiceCanRoll;

			while (diceToRoll != 0) {
				diceRolledResult = randomNumbergenerator();
				attackerDiceNumbersList.add(diceRolledResult); // check that the list is getting cleared in attack phase
				diceToRoll--;
			}
			Collections.sort(attackerDiceNumbersList);
			p.setAttackerDice(attackerDiceNumbersList);

			numOfAttackerArmy = allOutDefend(defenderPlayerName, p.getDefenderCountry(), numOfAttackerArmy, countryList,
					defenderPlayerData, p, attackerPlayerData , p.getAttackerName() ,p.getAttackerCountry());

			// Defend method();
			int defend = 0; // to be removed
			numOfAttackerArmy = defend; /* = returned from Defend Method */
			// to be corrected as num of army would come from defender action's ----
			// defender sends army left after defeat -------

			// max army results from defend case weather to decrement or not

		}

		return "Attacker attacking with full force";
	}

	public int allOutDefend(String defenderPlayerName, String defenderCountry, Integer attackerArmy,
			HashMap<Integer, Countries> countryList, Player defenderPlayerData, Player p, Player attackerPlayerData , String attackerPlayerName, String attackerCountryname) {

		defenderDiceNumbersList = new ArrayList<Integer>();

		numOfDefenderArmy = defenderArmiesMap.get(defenderPlayerName);
		
		int maxDefenderDiceToRoll = 0;

		if (numOfDefenderArmy == 0) {
			return 0;
		} else if (numOfDefenderArmy > 1) {
			maxDefenderDiceToRoll = 2;
		} else {
			maxDefenderDiceToRoll = 1;
		}

		while (maxDefenderDiceToRoll != 0) {

			diceRolledResult = randomNumbergenerator();
			defenderDiceNumbersList.add(diceRolledResult); // check that the list is getting cleared in attack phase
			maxDefenderDiceToRoll--;

		}
		Collections.sort(defenderDiceNumbersList);
		p.setDefenderDice(defenderDiceNumbersList);

		comparingDiceToWin(p, defenderPlayerName, defenderArmiesMap, attackerArmiesMap, attackerPlayerName , attackerPlayerData ,attackerCountryname);

		return diceRolledResult;

	}

	public void comparingDiceToWin(Player p, String defenderPlayeName, HashMap<String, Integer> defenderArmiesMap,
			HashMap<String, Integer> attackerArmiesMap, String attackerPlayerName , Player attackerPlayerData ,String attackerCountryName) {
		int attackerNum = p.getAttackerDice().get(0);
		int defenderNum = p.getDefenderDice().get(0);

		numOfDefenderArmy = defenderArmiesMap.get(defenderPlayeName);
		numOfAttackerArmy = attackerArmiesMap.get(attackerPlayerName);

		String defenderCountryName = p.getDefenderName();
		
		if (defenderNum == attackerNum || defenderNum > attackerNum) {
			
			int attackerArmyLeft = attackerPlayerData.getOwnedCountriesArmiesList().get(attackerCountryName);
			attackerArmyLeft--;
			attackerPlayerData.getOwnedCountriesArmiesList().put(attackerCountryName, attackerArmyLeft);
			
		}else
		{
			int defenderArmyLeft = attackerPlayerData.getOwnedCountriesArmiesList().get(defenderCountryName);
			defenderArmyLeft--;
			attackerPlayerData.getOwnedCountriesArmiesList().put(defenderCountryName, defenderArmyLeft);
		}
		
		
		
	}

	public int randomNumbergenerator() {
		double random = Math.random();
		random = random * 6 + 1;
		int diceRoll = (int) random;
		return diceRoll;

	}
	
	public boolean validateNumOfArmyMoves(Integer diceRolled, Integer moveArmy) {

		int diceRolledForWin = diceRolled;
		int armyToMove = moveArmy;
		
		if(armyToMove > diceRolledForWin || armyToMove == diceRolledForWin)
		{
			return true;
		}
		
		
		return false;
	}

}
