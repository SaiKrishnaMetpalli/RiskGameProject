package model;

import java.util.HashMap;

/**
 * This class contains the tournament details
 */
public class Tournament {
	
	/**
	 * This variable contains the hashmap of game results
	 */
	private HashMap<String,String> gameResults;
	
	/**
	 * This function contauns the game results
	 */
	public Tournament() {
		gameResults=new HashMap<String, String>();
	}
	
	/**
	 * This function get the game results
	 * @return This returns the game results
	 */
	public HashMap<String, String> getGameResults() {
		return gameResults;
	}
	
	/**
	 * This function set the game results
	 * @param gameResults   it contains the game results
	 */
	public void setGameResults(HashMap<String, String> gameResults) {
		this.gameResults = gameResults;
	}

	
}
