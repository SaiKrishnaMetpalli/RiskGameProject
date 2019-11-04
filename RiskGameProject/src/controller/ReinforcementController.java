package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import model.Continents;
import model.Countries;
import model.Player;

/**
 * This class intend to calculate the no. of bonus armies in reinforcement phase
 *
 */

public class ReinforcementController {
	
	CommonController cc;
	
	/**
	 * @Default Constructor
	 * Initiates object for CommonController
	 */
	public ReinforcementController() {
		cc=new CommonController();
	}
	
	/**
	 * calculates the reinforcement armies according to Risk rules uses
	 * getContinentByCountryName and getContinentsCountryList methods
	 * 
	 * @param player
	 * @param continents
	 * @param countries
	 * @param countryName
	 * @return returns total armies obtained as integer
	 */
	public int calculateReinforceArmy(Player player, HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries, String countryName, int cardReward) {
		int countryReward = 0;
		int continentReward = 0;
		int total = 0;
		// calculation of reward from owned country list
		countryReward = (int) Math.floor(player.getOwnedCountriesList().size() / 3);

		// reward for occupying all countries in continent
		HashMap<String, ArrayList<String>> listmap = cc.getContinentsCountryList(continents, countries);
		Continents cont = cc.getContinentByCountryName(continents, countries, countryName);
		ArrayList<String> continentsCountryList = listmap.get(cont.getContinentName());
		Collections.sort(continentsCountryList);
		Collections.sort(player.getOwnedCountriesList());
		if (player.getOwnedCountriesList().equals(continentsCountryList)) {
			continentReward = Integer.parseInt(cont.getcontinentControlValue());
		}
		// calculate total reward
		total = countryReward + continentReward + cardReward;
		if (total >= 3) {
			return total;
		} else {
			return 3; // minimum 3 army should be given
		}
	}

	
	public String placeReinforceArmy(String countryName, int numOfArmiesToPlace, HashMap<Integer, Countries> countries,
			HashMap<String, Player> playerMap, HashMap<Integer, Continents> continents, int availableReinforcedArmies) {

		String player = cc.findPlayerNameFromCountry(countries, countryName);

		if (player.equals("")) {
			return "Player Doesn't own the Country";
		}
		
		// Check Player owns the country
		Player p = playerMap.get(player);
		int existingArmy = p.getOwnedCountriesArmiesList().get(countryName);
		if (numOfArmiesToPlace <= availableReinforcedArmies) {
			existingArmy += availableReinforcedArmies;
			p.getOwnedCountriesArmiesList().put(countryName, existingArmy);
			
			return "Reinforcement armies placed successfully";
		} else
			return "Not enough reinforcement armies available";

	}
	
	public int exchangeCard(int num1, int num2, int num3, ArrayList<String> currentCardList, Player player) {
		String card1 = currentCardList.get(num1 - 1);
		String card2 = currentCardList.get(num2 - 1);
		String card3 = currentCardList.get(num3 - 1);
		HashSet<String> cardset = new HashSet<String>();
		cardset.addAll(Arrays.asList(card1,card2,card3));
		if ((card1.equals(card2) && card2.equals(card3))|| (cardset.size() == 3)) {
			player.setCardBonusArmy(player.getCardBonusArmy() + 5);
			return player.getCardBonusArmy();
		} else {
			return 0;
		}
	}

}
