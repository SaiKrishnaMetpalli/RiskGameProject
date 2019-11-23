package controller;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map.Entry;

import model.GameMap;
import model.Player;
import model.PlayersList;

public class AggressiveStrategy implements Strategy {

	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {
		// TODO Auto-generated method stub
		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return "Success";
	}
	
	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
        
		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int max= Collections.max(playerCountriesArmies.values());
        for(String country : playerCountriesArmies.keySet()) {
        	playerCountriesArmies.put(country,playerCountriesArmies.get(country) + max);
        }
		
	}
	private void attack(GameMap gm, PlayersList pl, Player player) {
		
	}
	
	private void fortify(GameMap gm, PlayersList pl, Player player) {

	}
}
