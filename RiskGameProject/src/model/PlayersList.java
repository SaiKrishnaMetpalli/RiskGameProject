package model;

import java.util.HashMap;

public class PlayersList extends Observable {

	private HashMap<String, Player> listOfPlayers;
	
	public PlayersList() {
		listOfPlayers = new HashMap<String, Player>();
	}

	public HashMap<String, Player> getListOfPlayers() {
		return listOfPlayers;
	}

	public void setListOfPlayers(HashMap<String, Player> listOfPlayers) {
		this.listOfPlayers = listOfPlayers;
	}
	
	public void notifyToObserver(Player p) {
		notifyObservers(this,p);
	}
	
}
