package model;

import java.util.HashMap;

public class Tournament {

	private HashMap<String,String> gameResults;
	
	public Tournament() {
		gameResults=new HashMap<String, String>();
	}

	public HashMap<String, String> getGameResults() {
		return gameResults;
	}

	public void setGameResults(HashMap<String, String> gameResults) {
		this.gameResults = gameResults;
	}

	
}
