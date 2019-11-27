package model;

/**
 * This class contains the game state scenario attributes
 */
public class GameStateScenario extends GameStateBuilder {
	/**
	 * This function build the game map
	 * 
	 * @param gm it contains the game map
	 * @author Sai Krishna
	 */
	public void buildGameMap(GameMap gm) {
		gameState.setGameMap(gm);
	}

	/**
	 * This function contains the player list
	 * 
	 * @param pl it contains the player list
	 * @author Gagan Jaswal
	 */
	public void buildPlayersList(PlayersList pl) {
		gameState.setPlayersList(pl);
	}

	/**
	 * This function contains the player
	 * 
	 * @param p it contains the player
	 * @author garimadawar
	 */
	public void buildPlayer(Player p) {
		gameState.setPlayer(p);
	}
}
