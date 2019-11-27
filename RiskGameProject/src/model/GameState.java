package model;

/**
 * This class contains the game state attributes
 */
public class GameState {
	
	/**
	 * The first variable contains the game map
	 * The second variable contains the players list
	 * The third variable contains the player
	 */
	private GameMap gameMap;
	private PlayersList playersList;
	private Player player;
	
	/**
	 * @Default Constructor This method initiates the variables
	 * @author Sai Krishna
	 */
	public GameState() {
		gameMap=new GameMap();
		playersList=new PlayersList();
		player=new Player();
	}
	
	/**
	 * This function get ths game map
	 * @return This returns the game map
	 */
	public GameMap getGameMap() {
		return gameMap;
	}
	
	/**
	 * This function set the game map
	 * @param gameMap
	 */
	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	/**
	 * This function get the player list
	 * @return This returns the player list
	 */
	public PlayersList getPlayersList() {
		return playersList;
	}
	
	/**
	 * This function set the player list
	 * @param playersList
	 */
	public void setPlayersList(PlayersList playersList) {
		this.playersList = playersList;
	}
	
	/**
	 * This function get the player
	 * @return This returns the player
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * This function set the player
	 * @param player
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
