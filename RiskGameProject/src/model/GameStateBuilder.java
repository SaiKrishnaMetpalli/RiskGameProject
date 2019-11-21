package model;

public abstract class GameStateBuilder {
	protected GameState gameState;

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	abstract void buildGameMap(GameMap gm);	
	abstract void buildPlayersList(PlayersList pl);	
	abstract void buildPlayer(Player p);	
}
