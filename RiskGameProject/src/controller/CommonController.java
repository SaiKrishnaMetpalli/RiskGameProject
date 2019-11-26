package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import model.Continents;
import model.Countries;
import model.Player;
import model.PlayersList;

/**
 * This class contains the common methods which can be used in multiple
 * controllers
 */
public class CommonController {

	/**
	 * This method will return country object for a specific country name
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
	 * This Method return the Country Name from its number
	 * 
	 * @param countries contains list of countries
	 * @param countryNum is the country number
	 * @return country name
	 * @author Ashish
	 */
	public String getCountryNameByNum(HashMap<Integer, Countries> countries, int countryNum)
	{
		Countries cou = countries.get(countryNum);
		
		return cou.getCountryName();
		
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
	 * Provides continent key from continent hashmap by its name
	 * @param continents
	 * @param continentName
	 * @return continent number key
	 * @author sakib
	 */
	
	public int getContinentNum(HashMap<Integer, Continents> continents, String continentName) {
		
		for (int i : continents.keySet()) {
			if(continents.get(i).getContinentName().equals(continentName)) {
				return i;
			}
		}
		return 0;
	}
	

	/**
	 * creates a new hashmap of continents with its country list This method is used
	 * in calculateReinforceArmy for verifying if a player owns all the countries in
	 * 
	 * @param continents takes hashmap of countries populated from map file
	 * @param countries  takes hashmap of countries populated from map file
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
	 * 
	 * @param countryName this variable contains the country name
	 * @param countries   takes hashmap of countries populated from map file
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
	 * 
	 * @param countries   takes hashmap of countries populated from map file
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
	
	public int exchangeCardForStrategy(PlayersList pl,Player player)
	{
		ArrayList<String> exchangeCardList;	
		 Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
		 
		 exchangeCardList = playerData.getCurrentCardList();
		 ArrayList<String> infantry = new ArrayList<>(Arrays.asList("INFANTRY","INFANTRY","INFANTRY"));
		 ArrayList<String> cavalry = new ArrayList<>(Arrays.asList("CAVALRY","CAVALRY","CAVALRY"));
		 ArrayList<String> artillery = new ArrayList<>(Arrays.asList("ARTILLERY","ARTILLERY","ARTILLERY"));
		 if(exchangeCardList.containsAll(infantry) || exchangeCardList.containsAll(cavalry) || exchangeCardList.containsAll(artillery))
		 {
			 player.setCardBonusArmy(player.getCardBonusArmy() + 5);
			 exchangeCardList.remove("INFANTRY")
			 return player.getCardBonusArmy();
		 }
		 
		 else return 0;
	}	

}
