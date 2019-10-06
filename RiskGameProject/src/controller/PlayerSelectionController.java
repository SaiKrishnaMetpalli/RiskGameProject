package controller;

import java.util.ArrayList;


public class PlayerSelectionController 
{	
	
	/**
	 * This method is used for adding the players
	 * @param players this variable contains the complete list of players
	 * @param playerName this variable has playerName to be added to list
	 * @return gives Success/Failure message
	 */
	public String addPlayer(ArrayList<String> players, String playerName)
	{
		if(!players.contains(playerName))
		{
			players.add(playerName);			
			return "Success";
		}
		else
		{
			return "Failure";
		}
	}
	
	/**
	 * This method is used for removing the players
	 * @param players this variable contains the complete list of players
	 * @param playerName this variable has playerName to be removed from list
	 * @return gives Success/Failure message
	 */
	public String removePlayer(ArrayList<String> players, String playerName)
	{
		if(players.contains(playerName))
		{
			players.remove(playerName);
			return "Success";
		}
		else
		{
			return "Failure";
		}		
	}	
	
}
