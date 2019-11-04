package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
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
	HashMap<String, Integer> countryArmyList;
	ArrayList<Integer> armiesLeftAfterAttack;
	int diceRolledResult = 0;
	int numOfAttackerArmy = 0;
	int numOfDefenderArmy = 0;
	int attackerArmyLeft = 0;

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

	public String attackPhase(String attackerCountry, String defenderCountry, Integer numberOnDice, Player player , Player attackerPlayerData) {

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);

		while (numberOnDice > 0) {
			diceRolledResult = randomNumbergenerator();
			attackerDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(attackerDiceNumbersList);
		player.setAttackerDice(attackerDiceNumbersList);

		return "Attacker Ready and placed his army on field ";
	}

	public String allOutAttackedPhase(String attackerCountry, String defenderCountry, Player attackerPlayerData,
			HashMap<Integer, Countries> countryList, Player player, Player defenderPlayerData) {

		// check this with SAI

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);

		String defenderPlayerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		String attackerPlayerName = cc.findPlayerNameFromCountry(countryList, attackerCountry);
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		int numOfAttackerArmy = attackerArmiesMap.get(attackerCountry);

		int numOfDiceCanRoll = 0;
		while (numOfAttackerArmy >= 1) {
			if (numOfAttackerArmy == 1) {
				numOfDiceCanRoll = 0;
			} else if (numOfAttackerArmy == 2) {
				numOfDiceCanRoll = 1;
			} else if (numOfAttackerArmy == 3) {
				numOfDiceCanRoll = 2;
			} else if (numOfAttackerArmy > 3) {
				numOfDiceCanRoll = 3;
			}

			int diceToRoll = numOfDiceCanRoll;
			attackerDiceNumbersList = new ArrayList<Integer>();

			while (diceToRoll != 0) {
				diceRolledResult = randomNumbergenerator();
				attackerDiceNumbersList.add(diceRolledResult);
				diceToRoll--;
			}
			Collections.sort(attackerDiceNumbersList);
			player.setAttackerDice(attackerDiceNumbersList);

			numOfAttackerArmy = allOutDefend(defenderPlayerName, player.getDefenderCountry(), numOfAttackerArmy,
					countryList, defenderPlayerData, player, attackerPlayerData, attackerPlayerName,
					player.getAttackerCountry());

			if (attackerArmiesMap.get(player.getAttackerCountry()) == 0) {
				return "Attacker Lost His Armies ";
			}
			if (defenderArmiesMap.get(player.getDefenderCountry()) == 0) {
				return "Defender Lost his Armies ";
			}
		}
		return "Attacking wiht full force";
	}

	public int allOutDefend(String defenderPlayerName, String defenderCountry, Integer attackerArmy,
			HashMap<Integer, Countries> countryList, Player defenderPlayerData, Player player,
			Player attackerPlayerData, String attackerPlayerName, String attackerCountryname) {

		defenderDiceNumbersList = new ArrayList<Integer>();

		numOfDefenderArmy = defenderArmiesMap.get(defenderCountry);

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
		player.setDefenderDice(defenderDiceNumbersList);

		attackerArmyLeft = comparingDiceToWin(player, defenderPlayerName, defenderArmiesMap, attackerArmiesMap,
				attackerPlayerName, attackerPlayerData, defenderPlayerData, attackerCountryname, defenderCountry);

		return attackerArmyLeft;

	}

	public int comparingDiceToWin(Player p, String defenderPlayeName, HashMap<String, Integer> defenderArmiesMap,
			HashMap<String, Integer> attackerArmiesMap, String attackerPlayerName, Player attackerPlayerData,
			Player defenderPlayerData, String attackerCountryName, String defenderCountryName) {

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
		return numOfAttackerArmy;
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

		if (armyToMove > diceRolledForWin || armyToMove == diceRolledForWin) {
			return true;
		}

		return false;
	}

	public String movingArmyToConqueredCountry(Integer armyToMove, HashMap<String, Player> playerData, Player player) {

		int movingArmy = armyToMove;

		moveArmy(movingArmy, playerData, player);

		assigningCountryToAttacker(playerData, player);

		return "Army Moved to Conquered Country ";
	}

	public boolean moveArmy(int movingArmy, HashMap<String, Player> playerData, Player player) {

		boolean flag = false;
		Player attackerData = playerData.get(player.getAttackerName());
		countryArmyList = attackerData.getOwnedCountriesArmiesList();
		int attackerArmy = countryArmyList.get(player.getAttackerCountry()); // TODO : check weather army left with
																				// attacker is > 1
		int remainingArmy = attackerArmy - movingArmy;
		countryArmyList.replace(player.getAttackerCountry(), remainingArmy); // now army moved from attacker country

		Player defenderData = playerData.get(player.getDefenderName());
		countryArmyList = defenderData.getOwnedCountriesArmiesList();
		int defenderArmy = countryArmyList.get(player.getDefenderCountry());
		if (defenderArmy == 0) {
			defenderArmy = movingArmy;
			flag = true;
		}
		countryArmyList.replace(player.getDefenderCountry(), defenderArmy);

		return flag;
	}

	public boolean armyLeftWithAttacker(Integer armyToMove, HashMap<String, Player> playerData, Player player) {
		int movedArmy = armyToMove;

		Player attackerData = playerData.get(player.getAttackerName());
		countryArmyList = attackerData.getOwnedCountriesArmiesList();
		int attackerArmy = countryArmyList.get(player.getAttackerCountry()); // TODO : check weather army left with
																				// attacker is > 1
		int remainingArmy = attackerArmy - movedArmy;

		if (remainingArmy >= 1) {
			return true;
		}
		return false;
	}

	public void assigningCountryToAttacker(HashMap<String, Player> playerData, Player player) {

		Player defenderData = playerData.get(player.getDefenderName());
		countryList = defenderData.getOwnedCountriesList();
		int defenderCountryIndex = countryList.indexOf(player.getDefenderCountry());
		countryList.remove(defenderCountryIndex);
		defenderData.setOwnedCountriesList(countryList);
		// change country owner name
		Player attackerData = playerData.get(player.getAttackerName());
		countryList = attackerData.getOwnedCountriesList();
		countryList.add(player.getDefenderCountry());
		attackerData.setOwnedCountriesList(countryList);
	} // change country owner

	public boolean validateDefenderNumdice(String defenderCountry, Integer numberOnDice, Player playerData,
			HashMap<Integer, Countries> countryList) {

		String playerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		defenderArmiesMap = playerData.getOwnedCountriesArmiesList();
		int numArmy = defenderArmiesMap.get(playerName);
		if (numArmy > 1 && numberOnDice <= 2 && numberOnDice > 0) {
			if (numArmy == 2)
				if (numberOnDice == 1) {
					return true;
				}
		} else if (numArmy == 3) {
			if (numberOnDice > 0 && numberOnDice <= 2) {
				return true;
			}
		} else if (numArmy == 4) {
			if (numberOnDice > 0 && numberOnDice <= 3) {
				return true;
			}
		} else if (numArmy > 3) {
			if (numberOnDice > 0 && numberOnDice <= 2) {
				return true;
			}
		} else {

			return false;
		}

		return false;
	}

	public String defendPhaseDiceRoll(String defenderCountry, Integer numberOnDice, Player p) {

		while (numberOnDice > 0) {
			double random = Math.random();
			random = random * 6 + 1;
			int diceRoll = (int) random;

			p.getDefenderDice().add(diceRoll);

			numberOnDice--;
		}
		return "Defender ready to defend";
	}
}
