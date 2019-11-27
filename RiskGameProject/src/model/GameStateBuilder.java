package model;

/**
 * This class contains the game state builder attributes
 */
public abstract class GameStateBuilder {
	/**
	 * This variable contains game state
	 */
	protected GameState gameState;
	
	/**
	 * This function get the game state
	 * @return This returns the game state
	 */
	public GameState getGameState() {
		return gameState;
	}
	
	/**
	 * This function set the game state
	 * @param gameState
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	/**
	 * This is an abstract method for setting the game map
	 * @param gm
	 */
	public abstract void buildGameMap(GameMap gm);	
	
	/**
	 * This is an abstract method for player list
	 * @param pl
	 */
	public abstract void buildPlayersList(PlayersList pl);
	
	/**
	 * This is an abstract method for player
	 * @param p
	 */
	public abstract void buildPlayer(Player p);	
}
