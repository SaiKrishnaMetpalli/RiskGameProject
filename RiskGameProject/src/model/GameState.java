package model;

/**
 * This class contains the game state attributes
 */
public class GameState {
	
	/**
	 * The gameMap variable contains the game map
	 * The playersList variable contains the players list
	 * The player variable contains the player
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
	 * This function get the game map
	 * @return This returns the game map
	 * @author Sai Krishna
	 */
	public GameMap getGameMap() {
		return gameMap;
	}
	
	/**
	 * This function set the game map
	 * @param gameMap  it contains the game map
	 * @return Sai Krishna
	 */
	public void setGameMap(GameMap gameMap) {
		this.gameMap = gameMap;
	}
	
	/**
	 * This function get the player list
	 * @return This returns the player list
	 * @author sakib
	 */
	public PlayersList getPlayersList() {
		return playersList;
	}
	
	/**
	 * This function set the player list
	 * @param playersList  it contains the player list
	 * @author sakib
	 */
	public void setPlayersList(PlayersList playersList) {
		this.playersList = playersList;
	}
	
	/**
	 * This function get the player
	 * @return This returns the player
	 * @author Ashish
	 */
	public Player getPlayer() {
		return player;
	}
	
	/**
	 * This function set the player
	 * @param player  it contains the player
	 * @author Ashish
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}
}
