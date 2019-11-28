package model;

import java.util.HashMap;

import observer.Observable;

/**
 * This class contains the player list
 */
public class PlayersList extends Observable {

	/**
	 * This method contains the hashmap of list of players
	 * 
	 * @author Sai Krishna
	 */
	private HashMap<String, Player> listOfPlayers;

	/**
	 * This function contains the player list
	 * 
	 * @author Sai Krishna
	 */
	public PlayersList() {
		listOfPlayers = new HashMap<String, Player>();
	}

	/**
	 * This function get the list of players
	 * 
	 * @return This returns the list of players
	 * @author sakib
	 */
	public HashMap<String, Player> getListOfPlayers() {
		return listOfPlayers;
	}

	/**
	 * This function set the list of players
	 * 
	 * @param listOfPlayers it contains the list of players
	 * @author sakib
	 */
	public void setListOfPlayers(HashMap<String, Player> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}

	/**
	 * This function notify the observer
	 * 
	 * @param p it represents the player
	 * @author Gagan Jaswal
	 */
	public void notifyToObserver(Player p) {
		notifyObservers(this, p);
	}

}
