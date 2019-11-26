package controller;

import java.util.Collections;
import java.util.HashMap;

import model.GameMap;
import model.Player;
import model.PlayersList;

/**
 * This class will implement Benevolent player strategy
 * 
 * @author sakib
 *
 */
public class BenevolentStrategy implements Strategy {

	PlayerController pc = new PlayerController();
	CommonController cc = new CommonController();

	/**
	 * this mehtods executes phases of the game according to benevolent behaviour
	 * 
	 * @author sakib
	 */
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {
		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return "Success";
	}

	/**
	 * implement benevolent reinforcement strategies
	 * 
	 * @param gm
	 * @param pl
	 * @param player
	 * @author sakib
	 */
	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int min = Collections.min(playerCountriesArmies.values());
		String weakCountryName = "";
		for (String pCountry : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(pCountry) == min) {
				weakCountryName = pCountry;
				break;
			}
		}
		int countryReward = pc.calculateOwnedCountryReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()));
		int continentReward = pc.calculateContinentReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()),
				gm.getContinents(), gm.getCountries(), weakCountryName);
		int cardReward = cc.exchangeCardForStrategy(pl, player);
		int numOfArmiesToPlace = pc.calculateReinforceArmy(countryReward, continentReward, cardReward);
		pc.calculateReinforceArmy(countryReward, continentReward, cardReward);
		// pc.placeReinforceArmy(weakCountryName, numOfArmiesToPlace, gm.getCountries(),
		// pl.getListOfPlayers(), gm.getContinents(), player);
		int weakCountryArmy = playerData.getOwnedCountriesArmiesList().get(weakCountryName);
		playerData.getOwnedCountriesArmiesList().put(weakCountryName, weakCountryArmy + numOfArmiesToPlace);
	}

	/**
	 * implement benevolent attack strategies
	 * 
	 * @param gm
	 * @param pl
	 * @param player
	 * @author sakib
	 */
	private void attack(GameMap gm, PlayersList pl, Player player) {
		player.setGameState("FORTIFY");
	}

	/**
	 * implement benevolent fortify strategies
	 * 
	 * @param gm
	 * @param pl
	 * @param player
	 * @author sakib
	 */
	private void fortify(GameMap gm, PlayersList pl, Player player) {
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int min = Collections.min(playerCountriesArmies.values());
		String toCountry = "";
		for (String pCountry : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(pCountry) == min) {
				toCountry = pCountry;
				break;
			}
		}
		String fromCountry = "";
		int armyToPlace = 0;

		for (String pCountry : playerCountriesArmies.keySet()) {
			if (!pCountry.equals(toCountry)) {
				pc.checkOwnPath(gm.getBoundries(), cc.getCountryNumberByName(gm.getCountries(), pCountry),
						cc.getCountryNumberByName(gm.getCountries(), toCountry));
				if (pc.ownedPath) {
					fromCountry = pCountry;
					armyToPlace = playerCountriesArmies.get(fromCountry) - 1;
					break;
				}
			}
		}
		if (!fromCountry.equals("")) {
			pc.fortify(pl.getListOfPlayers(), fromCountry, toCountry, armyToPlace, gm.getCountries(),
					gm.getBoundries());
		}

	}

}
