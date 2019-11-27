package model;

import java.util.HashMap;

/**
 * This class contains the player list
 */
public class PlayersList extends Observable {
	
	/**
	 * This method contains the hashmap of list of players
	 */
	private HashMap<String, Player> listOfPlayers;
	
	/**
	 * This function contains the player list
	 */
	public PlayersList() {
		listOfPlayers = new HashMap<String, Player>();
	}
	
	/**
	 * This function get the list of players
	 * @return This returns the list of players
	 */
	public HashMap<String, Player> getListOfPlayers() {
		return listOfPlayers;
	}
	
	/**
	 * This function set the list of players
	 * @param listOfPlayers  it contains the list of players
	 */
	public void setListOfPlayers(HashMap<String, Player> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}
	
	/**
	 * This function notify the observer
	 * @param p  it represents the player
	 */
	public void notifyToObserver(Player p) {
		notifyObservers(this, p);
	}

}
