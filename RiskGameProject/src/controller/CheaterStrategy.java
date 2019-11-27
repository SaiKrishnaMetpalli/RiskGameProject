package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Countries;
import model.GameMap;
import model.Player;
import model.PlayersList;

/**
 * This Class implements the Strategy Pattern for Cheater behaviour
 * 
 * @author Ashish Chaudhary
 */
public class CheaterStrategy implements Strategy {

	CommonController cc=new CommonController();
	PlayerController pc=new PlayerController();
	ArrayList<Integer> attackerCountryList;
	ArrayList<Integer> neighbouringList;

	/**
	 * Method overrides the Strategy Pattern Execute method
	 * 
	 * @return Success if all operation are performed
	 * @author Ashish Chaudhary
	 * @param gm     it is the gameMap
	 * @param pl     contains the hashmap of player object
	 * @param player contains the player object
	 */
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {

		reinforce(gm, pl, player);
		String attackResult = attack(gm, pl, player);
		if(attackResult.equals("Won")) {
			return "Won";
		}
		fortify(gm, pl, player);
		return "Success";
	}

	/**
	 * Method is used to perform reinforce of Cheater behavior player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author Ashish Chaudhary
	 */
	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		for (String country : playerCountriesArmies.keySet()) {
			playerCountriesArmies.put(country, playerCountriesArmies.get(country) * 2);
		}
		cc.observerViews("\nReinforcement armies are placed successfully for all countries", pl, player);
		player.setCardReward(0);
		player.setAvailableReinforceArmies(0);
		player.setActionsPerformed("");
		player.setGameState("ATTACK");
		cc.observerViews("", pl, player);
	}

	/**
	 * method performs the attack of the cheater player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @return  it returns a empty string
	 * @author Ashish Chaudhary
	 * 
	 */
	private String attack(GameMap gm, PlayersList pl, Player player) {

		attackerCountryList = new ArrayList<Integer>();
		neighbouringList = new ArrayList<Integer>();
		
		for (String country : pl.getListOfPlayers().get(player.getCurrentPlayerTurn()).getOwnedCountriesList()) {

			int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), country);
			attackerCountryList.add(attackerCountryNum);
		}
		for (int i = 0; i < attackerCountryList.size(); i++) {
			neighbouringList = gm.getBoundries().get(attackerCountryList.get(i));

			for (int x = 0; x < neighbouringList.size(); x++) {
				if (attackerCountryList.contains(neighbouringList.get(x))) {
					continue;
				} else {
					player.setAttackerCountry(cc.getCountryNameByNum(gm.getCountries(), attackerCountryList.get(i)));
					player.setAttackerName(player.getCurrentPlayerTurn());
					String defenderCountryName = cc.getCountryNameByNum(gm.getCountries(), neighbouringList.get(x));
					player.setDefenderCountry(defenderCountryName);

					Countries c = gm.getCountries().get(neighbouringList.get(x));
					player.setDefenderName(c.getOwnerName());					
					
					String allOutAttacked = pc.allOutAttackedPhase(player.getAttackerCountry(), defenderCountryName,
							pl.getListOfPlayers().get(player.getAttackerName()), gm.getCountries(), player,
							pl.getListOfPlayers().get(player.getDefenderName()));
					cc.observerViews("\n"+allOutAttacked, pl, player);

					if (allOutAttacked.contains("Won")) {
						String armyMoved = pc.movingArmyToConqueredCountry(player.getDiceRolled(),
								pl.getListOfPlayers(), player, gm);
						cc.observerViews("\n"+armyMoved, pl, player);
						boolean checkAllCountriesOwned = pc.checkGameEnd(pl);
						if (checkAllCountriesOwned) {
							return "Won";						
						}
					}					
				}

			}
		}
		player.setActionsPerformed("");
		player.setGameState("FORTIFY");
		cc.observerViews("", pl, player);
		return "";
	}

	/**
	 * method performs the fortification for the cheater player
	 * 
	 * @param gm     is the game map containing all info about game
	 * @param pl     contains all information about player
	 * @param player it is the player object
	 * @author Ashish Chaudhary
	 * 
	 */
	private void fortify(GameMap gm, PlayersList pl, Player player) {

		attackerCountryList = new ArrayList<Integer>();
		neighbouringList = new ArrayList<Integer>();

		for (String country : pl.getListOfPlayers().get(player.getCurrentPlayerTurn()).getOwnedCountriesList()) {

			int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), country);
			attackerCountryList.add(attackerCountryNum);
		}
		for (int i = 0; i < attackerCountryList.size(); i++) {
			neighbouringList = gm.getBoundries().get(attackerCountryList.get(i));

			for (int x = 0; x < neighbouringList.size(); x++) {

				if (attackerCountryList.contains(neighbouringList.get(x))) {
					continue;
				} else {
					String countryToFortify = cc.getCountryNameByNum(gm.getCountries(), attackerCountryList.get(i));

					Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

					HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();

					playerCountriesArmies.put(countryToFortify, playerCountriesArmies.get(countryToFortify) * 2);
					break;
				}

			}
		}
		cc.observerViews("\nFortification has done for all the countries", pl, player);

	}	

}
