package model;

public class Tournament {

	private String game;
	private String result;
	
	public Tournament() {
		game="";
		result="Draw";
	}

	public String getGame() {
		return game;
	}

	public void setGame(String game) {
		this.game = game;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
}
