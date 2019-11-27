package model;

/**
 * This class contains the game state scenario attributes
 */
public class GameStateScenario extends GameStateBuilder {
	/**
	 * This function build the game map
	 */
	public void buildGameMap(GameMap gm) {
		gameState.setGameMap(gm);
	}
	
	/**
	 * 
	 */
	public void buildPlayersList(PlayersList pl) {
		gameState.setPlayersList(pl);
	}
	
	public void buildPlayer(Player p) {
		gameState.setPlayer(p);
	}
}
