package model;

/**
 * This class contains the game state builder attributes
 */
public abstract class GameStateBuilder {
	/**
	 * This variable contains game state
	 * 
	 * @author Sai Krishna
	 */
	protected GameState gameState;

	/**
	 * This function get the game state
	 * 
	 * @return This returns the game state
	 * @author Sai Krishna
	 */
	public GameState getGameState() {
		return gameState;
	}

	/**
	 * This function set the game state
	 * 
	 * @param gameState it contains the game state
	 * @author Gagan Jaswal
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}

	/**
	 * This is an abstract method for setting the game map
	 * 
	 * @param gm it contains the game map
	 * @author sakib
	 */
	public abstract void buildGameMap(GameMap gm);

	/**
	 * This is an abstract method for player list
	 * 
	 * @param pl it contains the player list
	 * @author Ashish
	 */
	public abstract void buildPlayersList(PlayersList pl);

	/**
	 * This is an abstract method for player
	 * 
	 * @param p it contains the player
	 * @author Gagan Jaswal
	 */
	public abstract void buildPlayer(Player p);
}
