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
 * This Class is for Aggressive Strategy for player Behavior
 * 
 * @author garimadawar
 *
 */
    public class AggressiveStrategy implements Strategy {
	PlayerController pc = new PlayerController();
	CommonController cc = new CommonController();
	GameMap gm = new GameMap();
	ArrayList<Integer> attackerCountryList;
	ArrayList<Integer> neighbouringList;
    
	

	/**
	 * Method overrides the Strategy Pattern Execute method
	 * 
	 * @return Success if all operations are performed
	 * @author garimadawar
	 * @param gm     it is the gameMap
	 * @param pl     contains the hashMap of player object
	 * @param player contains the player object
	 */
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {
		// TODO Auto-generated method stub
		reinforce(gm, pl, player);
		attack(gm, pl, player);
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
		String strongCountry = "";
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
	private void attack(GameMap gm, PlayersList pl, Player player) {
        
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		String strongCountry = "";
		int max = Collections.max(playerCountriesArmies.values());
		for (String c : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(c).equals(max)) {
				strongCountry = c;
				break;
			}
		}
		
		attackerCountryList = new ArrayList<Integer>();
		neighbouringList = new ArrayList<Integer>();
		int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), strongCountry);
		attackerCountryList.add(attackerCountryNum);
	
		
		for (int i = 0; i < attackerCountryList.size(); i++) {
			neighbouringList = gm.getBoundries().get(attackerCountryList.get(i));

	    for (int x = 0; x < neighbouringList.size(); x++) {
				if (attackerCountryList.contains(neighbouringList.get(x))) {
					continue;
				} else {
					player.setAttackerCountry(cc.getCountryNameByNum(gm.getCountries(), attackerCountryList.get(i)));
					String defenderCountryName = cc.getCountryNameByNum(gm.getCountries(), neighbouringList.get(x));
					player.setDefenderCountry(defenderCountryName);

					Countries c = gm.getCountries().get(neighbouringList.get(x));
					player.setDefenderName(c.getOwnerName());

					String allOutAttacked = pc.allOutAttackedPhase(player.getAttackerCountry(), defenderCountryName,
							pl.getListOfPlayers().get(player.getAttackerName()), gm.getCountries(), player,
							pl.getListOfPlayers().get(player.getDefenderName()));
					
					if (allOutAttacked.contains("Won")) {
						String armyMoved = pc.movingArmyToConqueredCountry(player.getDiceRolled(),
								pl.getListOfPlayers(), player, gm);
						boolean checkAllCountriesOwned = pc.checkGameEnd(pl);
						if (checkAllCountriesOwned) {
							System.out.println("\n" + player.getAttackerName() + " won the Risk Game");
							System.out.println("\nThe game is ended");
							System.exit(0);

						} else {
				     pc.movingArmyToConqueredCountry(player.getDiceRolled(),pl.getListOfPlayers(), player, gm);
						}
					}

				}
			}
		}
	}
    
	/**
	 * method performs the fortification of the random player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author garimadawar
	 * 
	 */
	private void fortify(GameMap gm, PlayersList pl, Player player) {
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		String fromCountry = "";
		String toCountry = "";
		int max = Collections.max(playerCountriesArmies.values());
		for (String c : playerCountriesArmies.keySet()) {
			if (playerCountriesArmies.get(c).equals(max)) {
				fromCountry = c;
			}
		}
		int armyToPlace = 0;

		for (String s : playerCountriesArmies.keySet()) {
			pc.checkOwnPath(gm.getBoundries(), cc.getCountryNumberByName(gm.getCountries(), fromCountry),
					cc.getCountryNumberByName(gm.getCountries(), toCountry));
			if (pc.ownedPath) {
				toCountry = s;
				armyToPlace = playerCountriesArmies.get(toCountry);
				break;
			}
		}
		
		pc.fortify(pl.getListOfPlayers(), fromCountry, toCountry, armyToPlace, gm.getCountries(), gm.getBoundries());
	}
}
