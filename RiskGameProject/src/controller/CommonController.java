package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Continents;
import model.Countries;

/**
 * This class contains the common methods which can be used in multiple
 * controllers
 */
public class CommonController {

	/**
	 * This method will return country object for a specific country name
	 * 
	 * @param countries   takes hashmap of countries populated from map file
	 * @param countryName takes country name string
	 * @return country object
	 * @author sakib
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
	 * @author sakib
	 */
	public Continents getContinentByCountryName(HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries, String countryName) {

		Countries c1 = getCountryByName(countries, countryName);
		int n = c1.getCountryContinentNum();
		return continents.get(n);
	}

	/**
	 * creates a new hashmap of continents with its country list This method is used
	 * in calculateReinforceArmy for verifying if a player owns all the countries in
	 * @param continents takes hashmap of countries populated from map file
	 * @param countries takes hashmap of countries populated from map file
	 * @return hashmap where key is continent and value is country list
	 * @author sakib
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
	 * Method to be called from command to place army uses calculateReinforceArmy
	 * method
	 * @param countryName this variable contains the country name
	 * @param countries takes hashmap of countries populated from map file
	 * @return different messages for view
	 * @author sakib
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

	/**
	 * To find the key of country hashmap
	 * @param countries takes hashmap of countries populated from map file
	 * @param countryName this variable contains the country name
	 * @return this returns the country number if present else 0
	 * @author sakib 
	 */
	public int getCountryNumberByName(HashMap<Integer, Countries> countries, String countryName) {
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				return i;
			}
		}
		return 0;
	}

}
