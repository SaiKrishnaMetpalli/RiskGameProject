package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import model.Continents;
import model.Countries;
import model.Player;

/**
 * This class intend to calculate the no. of bonus armies in reinforcement phase
 *
 */

public class ReinforcementController {

	// Player player;

	/**
	 * This method will return country object for a specific country name
	 * 
	 * @param countries takes hashmap of countries populated from map file
	 * @param countryName takes country name string
	 * @return country object
	 */
	public Countries getCountryByName(HashMap<Integer, Countries> countries, String countryName) {
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				return cou;
			}
		}
		return null;
	}

	/**
	 * takes Continent value from countries hashmap from that value find continent
	 * object from continents hashmap Uses getCountryByNam method This method is
	 * used in calculateReinforceArmy
	 * 
	 * @param continents  takes hashmap of continents populated from map file
	 * @param countries   takes hashmap of countries populated from map file
	 * @param countryName takes country name string
	 * @return continent objects for country name
	 */
	private Continents getContinentByCountryName(HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries, String countryName) {

		Countries c1 = getCountryByName(countries, countryName);
		int n = c1.getCountryContinentNum();
		return continents.get(n);
	}

	/**
	 * creates a new hashmap of continents with its country list This method is used
	 * in calculateReinforceArmy for verifying if a player owns all the countries in
	 * continent
	 * 
	 * @param continents
	 * @param countries
	 * @return hashmap where key is continent and value is country list
	 */
	public HashMap<String, ArrayList<String>> getContinentsCountryList(HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries) {

		HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();

		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			int n = c1.getCountryContinentNum();
			Continents co1 = continents.get(n);

			if (!map.containsKey(co1.getContinentName())) {
				ArrayList<String> list = new ArrayList<String>();
				list.add(c1.getCountryName());
				map.put(co1.getContinentName(), list);
			} else {
				map.get(co1.getContinentName()).add(c1.getCountryName());

			}
		}

		return map;
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
			HashMap<Integer, Countries> countries, String countryName) {
		int countryReward = 0;
		int continentReward = 0;
		int cardReward = 0; // for future work
		int total = 0;
		// calculation of reward from owned country list
		countryReward = (int) Math.floor(player.getOwnedCountriesList().size() / 3);

		// reward for occupying all countries in continent
		HashMap<String, ArrayList<String>> listmap = getContinentsCountryList(continents, countries);
		Continents cont = getContinentByCountryName(continents, countries, countryName);
		ArrayList<String> continentsCountryList = listmap.get(cont.getContinentName());
		Collections.sort(continentsCountryList);
		Collections.sort(player.getOwnedCountriesList());
		if (player.getOwnedCountriesList().equals(continentsCountryList)) {
			continentReward = Integer.parseInt(cont.getcontinentControlValue());
		}
		// calculate total reward
		total = countryReward + continentReward;
		if (total >= 3) {
			return total;
		} else {
			return 3; // minimum 3 army should be given
		}
	}

	/**
	 * Method to be called from command to place army uses calculateReinforceArmy
	 * method
	 * 
	 * @param countryName
	 * @param numOfArmiesToPlace
	 * @param countries
	 * @param playerMap
	 * @param continents
	 * @return different messages for view
	 */
	public String findPlayerNameFromCountry(HashMap<Integer, Countries> countries, String countryName) {
		String player = "";
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				player = cou.getOwnerName();
				break;
			}
		}
		return player;
	}

	public String placeReinforceArmy(String countryName, int numOfArmiesToPlace, HashMap<Integer, Countries> countries,
			HashMap<String, Player> playerMap, HashMap<Integer, Continents> continents) {

		String player = findPlayerNameFromCountry(countries, countryName);
		
		if (player.equals("")) {
			return "Player Doesn't own the Country";
		}

		int availableReinforcedArmies = calculateReinforceArmy(playerMap.get(player), continents, countries,
				countryName);
		 //Check Player owns the country
		Player p = playerMap.get(player);		
		int existingArmy = p.getOwnedCountriesArmiesList().get(countryName);
		if (numOfArmiesToPlace <= availableReinforcedArmies) {
			existingArmy += availableReinforcedArmies;
			p.getOwnedCountriesArmiesList().put(countryName, existingArmy);			
			return "Reinforcement armies placed successfully";
		} else
			return "Not enough reinoforcement armies available";

	}

}
