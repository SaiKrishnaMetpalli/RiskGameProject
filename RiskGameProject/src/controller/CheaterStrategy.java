package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Countries;
import model.GameMap;
import model.Player;
import model.PlayersList;

public class CheaterStrategy implements Strategy {

	CommonController cc;
	PlayerController pc;
	ArrayList<Integer> attackerCountryList = new ArrayList<Integer>();
	ArrayList<Integer> neighbouringList = new ArrayList<Integer>();
	
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {

		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return null;
	}

	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		for (String country : playerCountriesArmies.keySet()) {
			playerCountriesArmies.put(country, playerCountriesArmies.get(country) * 2);
		}
		player.setCardReward(0);
		player.setAvailableReinforceArmies(0);
		player.setGameState("ATTACK");
	}

	private void attack(GameMap gm, PlayersList pl, Player player) {

		for (String country : pl.getListOfPlayers().get(player.getCurrentPlayerTurn()).getOwnedCountriesList()) {

			//player.setAttackerCountry(country);
			int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), country);
			attackerCountryList.add(attackerCountryNum);
		}
		for(int i = 0 ; i < attackerCountryList.size() ; i++)
		{
			neighbouringList = gm.getBoundries().get(attackerCountryList.get(i));
			
			for(int x = 0 ; x < neighbouringList.size() ; x++)
			{
				if(attackerCountryList.contains(neighbouringList.get(x)))
				{
					continue;
				}
				else
				{
					player.setAttackerCountry(cc.getCountryNameByNum(gm.getCountries(), attackerCountryList.get(i)));
					String defenderCountryName = cc.getCountryNameByNum(gm.getCountries(), neighbouringList.get(x));
					player.setDefenderCountry(defenderCountryName);
					
					Countries c = gm.getCountries().get(x);
					player.setDefenderName(c.getOwnerName());
					
					
					String allOutAttacked = pc.allOutAttackedPhase(player.getAttackerCountry() , defenderCountryName , pl.getListOfPlayers().get(player.getAttackerName())
							, gm.getCountries() , player , pl.getListOfPlayers().get(player.getDefenderName()));
					
					if (allOutAttacked.contains("Won")) {
						String armyMoved = pc.movingArmyToConqueredCountry(
								player.getDiceRolled(), pl.getListOfPlayers(), player, gm);
						boolean checkAllCountriesOwned = pc.checkGameEnd(pl);
						if (checkAllCountriesOwned) {
							System.out.println("\n" + player.getAttackerName() + " won the Risk Game");
							System.out.println("\nThe game is ended");
							System.exit(0);
						}
					}
				}
				
				
			}
		}
		player.setGameState("FORTIFY");
	}

	private void fortify(GameMap gm, PlayersList pl, Player player) {

	}

}
