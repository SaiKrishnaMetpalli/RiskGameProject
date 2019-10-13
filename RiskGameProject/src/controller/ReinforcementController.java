package controller;

import model.Continents;
import model.Player;

public class ReinforcementController {

	public int reinforce(Player player, Continents continent) {
		int countryReward=0;
		int continentReward=0;
		int cardReward=0;
		countryReward = (int) Math.floor(Player.occupiedCountries/3);
		if(player.getCountires == continent.getCountires) { // if has same elements in both object 
			continentReward = Integer.ParseInt(Continents.continentControlValue);
			}
		return countryReward+continentReward;
	}
}
