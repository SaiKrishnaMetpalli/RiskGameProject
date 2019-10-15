package controller;

import java.util.ArrayList;
import java.util.HashMap;
import model.Continents;
import model.Countries;
import model.Player;

/**
 * This class intend to calculate the no. of bonus armies in reinforcement phase
 * 
 * @param takes player and continent objects to calculate the armies based on
 *              the parameters
 * @return returns total armies obtained as integer
 * @author sakib
 *
 */

public class ReinforcementController {

	Player player;

	public Countries getCountryByName(HashMap<Integer, Countries> countries, String countryName) {
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				return cou;
			}
		}
		return null;
	}

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

	public int calculateReinforceArmy(Player player, HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries, String countryName) {
		int countryReward = 0;
		int continentReward = 0;
		int cardReward = 0;
		int total = 0;
		HashMap<String, ArrayList<String>> listmap = getContinentsCountryList(continents, countries);
		Continents cont = getContinentByCountryName(continents, countries, countryName);
		ArrayList<String> countrylist = listmap.get(cont.getContinentName());
		countryReward = (int) Math.floor(player.getOwnedCountriesList().size() / 3);

		if (player.getOwnedCountriesList().equals(countrylist)) {
			continentReward = Integer.parseInt(cont.getcontinentControlValue());
		}
		total = countryReward + continentReward;
		if (total >= 3) {
			return total;
		} else {
			return 3; // ???? confused
		}
	}

	private Continents getContinentByCountryName(HashMap<Integer, Continents> continents,
								HashMap<Integer, Countries> countries, String countryName) {
		
		Countries c1 = getCountryByName(countries, countryName);
		int n = c1.getCountryContinentNum();
		return continents.get(n);
	}

	public String placeReinforceArmy(String countryName, int numOfArmiesToPlace, HashMap<Integer, Countries> countries,
			HashMap<String, Player> playerMap, HashMap<Integer, Continents> continents) {
		String player = "";

		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				player = cou.getOwnerName();
				break;
			}
		}

		int availableReinforcedArmies = calculateReinforceArmy(playerMap.get(player), continents, countries,
				countryName);
		// Check Player owns the country
		Player p = playerMap.get(player);
		int index = p.getOwnedCountriesList().indexOf(countryName);
		ArrayList<Integer> existingArmiesList = p.getOwnedArmiesList();
		int existingArmy = existingArmiesList.get(index);
		if (numOfArmiesToPlace <= availableReinforcedArmies) {
			existingArmy += availableReinforcedArmies;
			existingArmiesList.add(index, existingArmy);
			p.setOwnedArmiesList(existingArmiesList);
		} else
			return "Not enough reinoforcement armies available";

		return "Country doesn't exist";
	}

}
