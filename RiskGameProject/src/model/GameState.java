package model;

public class GameState {
	
	private GameMap gameMap;
	private PlayersList playersList;
	private Player player;
	
	public GameMap getGameMap() {
		return gameMap;
	}
	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	public PlayersList getPlayersList() {
		return playersList;
	}
	public void setPlayersList(PlayersList playersList) {
		this.playersList = playersList;
	}
	public Player getPlayer() {
		return player;
	}
	public void setPlayer(Player player) {
		this.player = player;
	}
}
