package model;

public class GameStateScenario extends GameStateBuilder {
	
	public void buildGameMap(GameMap gm) {
		gameState.setGameMap(gm);
	}
	
	public void buildPlayersList(PlayersList pl) {
		gameState.setPlayersList(pl);
	}
	
	public void buildPlayer(Player p) {
		gameState.setPlayer(p);
	}
}
