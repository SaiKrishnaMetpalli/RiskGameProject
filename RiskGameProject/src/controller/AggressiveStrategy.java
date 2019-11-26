package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Countries;
import model.GameMap;
import model.Player;
import model.PlayersList;

/**
 * This Class is for Aggressive Strategy for player Behavior An aggressive
 * computer player strategy that focuses on attack (reinforces its strongest
 * country, then always attack with it until it cannot attack anymore, then
 * fortifies in order to maximize aggregation of forces in one country).
 *
 * 
 * @author garimadawar
 *
 */
public class AggressiveStrategy implements Strategy {
	PlayerController pc = new PlayerController();
	CommonController cc = new CommonController();
	GameMap gm = new GameMap();

	ArrayList<Integer> neighbouringList;
	String strongCountry = "";

	/**
	 * Method overrides the Strategy Pattern Execute method
	 * 
	 * @return Success if all operations are performed
	 * @param gm     it is the gameMap
	 * @param pl     contains the hashMap of player object
	 * @param player contains the player object
	 * @author garimadawar
	 */
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {

		reinforce(gm, pl, player);
		player.notifyToObserver();
		pl.notifyToObserver(player);
		attack(gm, pl, player);
		player.notifyToObserver();
		pl.notifyToObserver(player);
		fortify(gm, pl, player);

		return "Success";
	}

	/**
	 * Method is used to perform reinforce of Random behavior player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author garimadawar
	 */
	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int max = Collections.max(playerCountriesArmies.values());
		for (String c : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(c).equals(max)) {
				strongCountry = c;
				break;
			}
		}
		int countryReward = pc.calculateOwnedCountryReward(playerData);
		int continentReward = pc.calculateContinentReward(playerData, gm.getContinents(), gm.getCountries(),
				strongCountry);
		int cardReward = cc.exchangeCardForStrategy(pl, player);
		int numOfArmiesToPlace = pc.calculateReinforceArmy(countryReward, continentReward, cardReward);
		int strongCountryArmies = playerData.getOwnedCountriesArmiesList().get(strongCountry);
		playerData.getOwnedCountriesArmiesList().put(strongCountry, strongCountryArmies + numOfArmiesToPlace);

		player.setGameState("ATTACK");
	}

	/**
	 * method performs the attack of the Random Behavior player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author garimadawar
	 * 
	 */
	private String attack(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();

		neighbouringList = new ArrayList<Integer>();
		int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), strongCountry);

		neighbouringList = gm.getBoundries().get(attackerCountryNum);
		if (neighbouringList.size() > 0) {

			for (int x = 0; x < neighbouringList.size(); x++) {
				if (attackerCountryNum == neighbouringList.get(x)) {
					continue;
				} else {
					player.setAttackerCountry(strongCountry);
					player.setDefenderCountry(cc.getCountryNameByNum(gm.getCountries(), neighbouringList.get(x)));
					player.setDefenderName(
							cc.findPlayerNameFromCountry(gm.getCountries(), player.getDefenderCountry()));

					Player attackerPlayerData = pl.getListOfPlayers().get(player.getAttackerName());
					Player defenderPlayerData = pl.getListOfPlayers().get(player.getDefenderName());

					String attack = pc.allOutAttackedPhase(strongCountry, player.getDefenderCountry(),
							attackerPlayerData, gm.getCountries(), player, defenderPlayerData);

					if (attack.contains("Won")) {
						boolean checkAllCountriesOwned = pc.checkGameEnd(pl);
						if (checkAllCountriesOwned) {
							System.out.println("\n" + player.getAttackerName() + " won the Risk Game");
							System.out.println("\nThe game is ended");
							System.exit(0);
						} else {
							String moveArmy = pc.movingArmyToConqueredCountry(player.getDiceRolled(),
									pl.getListOfPlayers(), player, gm);
							break;
						}
					}
				}
			}
		}
		return "";
	}

	/**
	 * method performs the fortification of the random player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author garima dawar
	 * 
	 */
	private void fortify(GameMap gm, PlayersList pl, Player player) {
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		String fromCountry = "";
		String toCountry = "";
		int max = Collections.max(playerCountriesArmies.values());
		for (String country : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(country).equals(max)) {
				fromCountry = country;
			}
		}
		int armyToPlace = 0;

		for (String country : playerCountriesArmies.keySet()) {
			pc.checkOwnPath(gm.getBoundries(), cc.getCountryNumberByName(gm.getCountries(), fromCountry),
					cc.getCountryNumberByName(gm.getCountries(), country));
			if (pc.ownedPath) {
				toCountry = country;
				armyToPlace = playerCountriesArmies.get(toCountry) - 1;
				break;
			}
		}
		player.setAttackerName(player.getCurrentPlayerTurn());
		pc.fortify(pl.getListOfPlayers(), fromCountry, toCountry, armyToPlace, gm.getCountries(), gm.getBoundries());
		if (player.getConqueredCountries().size() > 0) {
			pc.addGameCardsToAttacker(pl.getListOfPlayers().get(player.getAttackerName()), player, gm);
		}
	}
}
