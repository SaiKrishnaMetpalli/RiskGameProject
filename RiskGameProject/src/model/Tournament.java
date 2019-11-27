package model;

import java.util.HashMap;

/**
 * This class contains the tournament details
 */
public class Tournament {

	/**
	 * This variable contains the hashmap of game results
	 */
	private HashMap<String, String> gameResults;

	/**
	 * @Default Constructor
	 * @author Sai Krishna
	 */
	public Tournament() {
		gameResults = new HashMap<String, String>();
	}

	/**
	 * This function get the game results
	 * 
	 * @return This returns the game results
	 * @author Ashish
	 */
	public HashMap<String, String> getGameResults() {
		return gameResults;
	}

	/**
	 * This function set the game results
	 * 
	 * @param gameResults it contains the game results
	 * @author sakib
	 */
	public void setGameResults(HashMap<String, String> gameResults) {
		this.gameResults = gameResults;
	}

}
