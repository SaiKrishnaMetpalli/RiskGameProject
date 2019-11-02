package controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import model.Countries;
import model.Player;

public class AttackController {

	CommonController cc = new CommonController();
	ArrayList<Integer> adjancyList;
	HashMap<String, Integer> armies;
	ArrayList<Integer> diceNumbers;

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

		armies = playerData.getOwnedCountriesArmiesList();

		int numOfArmy = armies.get(playerName);

		if (numOfArmy > 1 && numberOnDice <= 3 && numberOnDice > 0) {
			if (numOfArmy == 2) {
				if (numberOnDice == 1) {
					return true;
				}

			} else if (numOfArmy == 3) {
				if (numberOnDice > 0 && numberOnDice <= 2) {
					return true;
				}
			} else if (numOfArmy == 4) {
				if (numberOnDice > 0 && numberOnDice <= 3) {
					return true;
				}
			} else if (numOfArmy > 4) {
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

		while (numberOnDice > 0) {
			double random = Math.random();
			random = random * 6 + 1;
			int diceRoll = (int) random;

			p.getAttackerDice().add(diceRoll);

			numberOnDice--;
		}

		return "Attacker ready to attack the Country";
	}

	public boolean validateDefenderNumdice(String defenderCountry, Integer numberOnDice, Player playerData,
			HashMap<Integer, Countries> countryList) {

		String playerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		armies= playerData.getOwnedCountriesArmiesList();
		int numArmy = armies.get(playerName);
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
		return defenderCountry;

	}
}
