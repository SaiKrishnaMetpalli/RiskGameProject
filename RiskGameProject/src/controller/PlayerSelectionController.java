package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Countries;
import model.Player;

/**
 * This class performs all operation regarding players
 * 
 */
public class PlayerSelectionController {

	/**
	 * method is used to assign countries to each player randomly
	 * 
	 * @param listOfPlayers is a list of players
	 * @param countries is used update information in country data
	 * @param playerDetails is user to update information on player data
	 * @return success if random assignment is done otherwise null
	 */
	public String assignRandomCountries(ArrayList<String> listOfPlayers, HashMap<Integer, Countries> countries,
			HashMap<String, Player> playerDetails) {
		ArrayList<String> countriesList = new ArrayList<String>();
		ArrayList<String> countryCloneList;
		for (Integer a : countries.keySet()) {

			Countries countryList = countries.get(a);
			countriesList.add(countryList.getCountryName());

		}
		countryCloneList = (ArrayList<String>) countriesList.clone();
		while (countryCloneList.size() > 0) {
			for (String i : listOfPlayers) {
				Collections.shuffle(countryCloneList);
				if (countryCloneList.size() == 0) {
					break; 	
				}
				if (playerDetails.containsKey(i)) {
					Player p = playerDetails.get(i);
					p.getOwnedCountriesList().add(countryCloneList.get(0));
					iterateCountriesName(countries, i , countryCloneList.get(0) );
					
				} else {
					Player p = new Player();
					p.getOwnedCountriesList().add(countryCloneList.get(0));
					playerDetails.put(i, p);
					
					iterateCountriesName(countries , i , countryCloneList.get(0));
				}

				countryCloneList.remove(0);
				
			}

			
		}
		
		for(String i : playerDetails.keySet())
		{
			Player p = playerDetails.get(i); 
			System.out.println(i + p.getOwnedCountriesList());
		}
		
		for(Integer j : countries.keySet())
		{
			Countries c = countries.get(j);
			System.out.println(j + c.getOwnerName());
		}
		return "Success";
	}
	/**
	 * method is used to iterate over countries name and assigning player to each country
	 * 
	 * @param countries contains the list of countries data
	 * @param playerName contains the name of player 
	 * @param countryName contains the name of country
	 */
	public void iterateCountriesName(HashMap<Integer, Countries> countries , String playerName , String countryName) {

		for(Integer j:countries.keySet())
		{
			Countries c=countries.get(j);
			if(c.getCountryName().equals(countryName))
			{
				c.setOwnerName(playerName);
			}
		}
		
		
	}

	

	/**
	 * This method is used for adding the players
	 * 
	 * @param players    this variable contains the complete list of players
	 * @param playerName this variable has playerName to be added to list
	 * @return gives Success/Failure message
	 */
	public String addPlayer(ArrayList<String> listOfPlayers, String playerName) {
		if (!listOfPlayers.contains(playerName)) {
			listOfPlayers.add(playerName);
			return "Success";
		} else {
			return "Failure";
		}
	}

	/**
	 * This method is used for removing the players
	 * 
	 * @param players    this variable contains the complete list of players
	 * @param playerName this variable has playerName to be removed from list
	 * @return gives Success/Failure message
	 */
	public String removePlayer(ArrayList<String> listOfPlayers, String playerName) {
		if (listOfPlayers.contains(playerName)) {
			listOfPlayers.remove(playerName);
			return "Success";
		} else {
			return "Failure";
		}
	}

}
