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
					p.getOwnedCountriesArmiesList().put(countryCloneList.get(0), 1);
					iterateCountriesName(countries, i , countryCloneList.get(0) );
					
				} else {
					Player p = new Player();
					p.getOwnedCountriesList().add(countryCloneList.get(0));
					p.getOwnedCountriesArmiesList().put(countryCloneList.get(0), 1);
					playerDetails.put(i, p);
					
					iterateCountriesName(countries , i , countryCloneList.get(0));
				}

				countryCloneList.remove(0);
				
			}

			
		}
		
		/*
		 * for(String i : playerDetails.keySet()) { Player p = playerDetails.get(i);
		 * System.out.println(i + p.getOwnedCountriesList()); }
		 * 
		 * for(Integer j : countries.keySet()) { Countries c = countries.get(j);
		 * System.out.println(j + c.getOwnerName()); }
		 */
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
	
	/**
	 * This method is used for placing a army for country
	 * 
	 * @param countries this variable contains the list of countries
	 * @param playerDetails this variable contains the player details
	 * @param countryName this variable contains the name of country to which army is added 
	 * @param totalArmiesCount this variable contains total armies for a player
	 * @return this returns the success/failure message to user
	 */
	public String placeArmy(HashMap<Integer, Countries> countries, HashMap<String, Player> playerDetails, String countryName, 
			int totalArmiesCount)
	{
		String playerName="";
		int armiesCount=0, armiesToPlace=0;
		for(int i:countries.keySet())
		{
			Countries c=countries.get(i);
			if(c.getCountryName().equals(countryName))
			{
				playerName=c.getOwnerName();
				break;
			}
		}
		
		Player p=playerDetails.get(playerName);				
		armiesCount=totalArmyCountPlayer(p);
		if(armiesCount<totalArmiesCount)
		{
			armiesToPlace=p.getOwnedCountriesArmiesList().get(countryName);
			armiesToPlace++;
			p.getOwnedCountriesArmiesList().put(countryName, armiesToPlace);
			return "Army is placed successfully";
		}
		else
		{
			return "Armies cannot be placed for this country as the player is outreached armies";
		}		
	}
	
	/**
	 * This method is used for placing all the armies for the players 
	 * 
	 * @param countries this variable contains the list of all countries
	 * @param playerDetails this variable contains the player details
	 * @param totalArmiesCount this variable contains total armies for a player
	 * @return this returns the success/failure message to user
	 */
	public String placeAll(HashMap<Integer, Countries> countries, HashMap<String, Player> playerDetails,int totalArmiesCount)
	{		
		int playerArmiesCount=0;
		for(String str:playerDetails.keySet())
		{
			Player p=playerDetails.get(str);
			playerArmiesCount=totalArmyCountPlayer(p);			
			while(playerArmiesCount<totalArmiesCount)
			{
				for(String strCountry:p.getOwnedCountriesArmiesList().keySet())
				{					
					int addArmies=p.getOwnedCountriesArmiesList().get(strCountry);
					if(playerArmiesCount<totalArmiesCount)
					{
						//p.getOwnedCountriesArmiesList().replaceAll((strCountry,addArmies) -> addArmies + 1);
						p.getOwnedCountriesArmiesList().put(strCountry, addArmies+1);
						playerArmiesCount++;
					}
					else
					{						
						break;
					}
					
				}
			}
		}
		return "Success";
	}
	
	/**
	 * This method gives the total armies count of a player
	 *  
	 * @param p this is the player object which contains a single player details
	 * @return this returns count of armies of player
	 */
	public int totalArmyCountPlayer(Player p)
	{
		int count=0;
		for(String str:p.getOwnedCountriesArmiesList().keySet())
		{
			count+=p.getOwnedCountriesArmiesList().get(str);
		}
		return count;
	}	

}
