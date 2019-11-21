package controller;

import model.GameMap;
import model.Player;
import model.PlayersList;

public class StrategyController{
	
	private Strategy strategy;
	
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}
	
	public String executeBehaviour(GameMap gm , PlayersList pl, Player player )
	{
	
		return this.strategy.executeStrategy(gm, pl, player);
		
	}
	
}
