package model;

public abstract class GameStateBuilder {
	protected GameState gameState;

	public GameState getGameState() {
		return gameState;
	}

	public void setGameState(GameState gameState) {
		this.gameState = gameState;
	}
	
	public abstract void buildGameMap(GameMap gm);	
	public abstract void buildPlayersList(PlayersList pl);	
	public abstract void buildPlayer(Player p);	
}
