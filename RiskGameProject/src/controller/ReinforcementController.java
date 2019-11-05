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
	 * Initiates object for CommonController
	 */
	public ReinforcementController() {
		cc=new CommonController();
	}
	
	/**
	 * calculates the reinforcement armies according to Risk rules uses
	 * getContinentByCountryName and getContinentsCountryList methods
	 * 
	 * @param player player object containing all player information
	 * @param continents hashmap containing continent objects
	 * @param countries hashmap containing countries objects
	 * @param countryName the country for which we need the continents and country object information
	 * @param cardReward the reward army obtained after card exchange
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
	
	/**
	 * this method will be called when the place reinforcement army command will be given
	 * @param countryName where the army will be placed
	 * @param numOfArmiesToPlace the armies to be placed will be mentioned in command line 
	 * @param countries countries hashmap containing countries objects
	 * @param playerMap player hashmap containing player objects
	 * @param continents continents hashmap containing continent objects
	 * @param availableReinforcedArmies the value received from calculate reinforce army method
	 * @return success or failure messages for each situation 
	 */
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
			existingArmy += numOfArmiesToPlace;
			p.getOwnedCountriesArmiesList().put(countryName, existingArmy);
			
			return "Reinforcement armies placed successfully";
		} else
			return "Not enough reinforcement armies available";

	}
	
	/**
	 * This method will calculate rewarded armies to be obtained after exchanging cards 
	 * @param num1 1st number selected from card exchange view
	 * @param num2 2nd number selected from card exchange view
	 * @param num3 3rd number selected from card exchange view
	 * @param currentCardList is the list of cards in players possession 
	 * @param player player object containing all player information
	 * @return the method will return number of reward army the player will get after exchanging the cards
	 */
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
