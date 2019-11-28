package controller;

import model.GameMap;
import model.Player;
import model.PlayersList;

/**
 * This class contains all the methods for set Strategy and execute behaviour of Strategy
 * 
 * @author garimadawar
 *
 */
public class StrategyController{
	
	/**
	 * strategy variable is the refrence of the Strategy class
	 */
	private Strategy strategy;
	
	/**
	 * This method is used to set the strategy
	 * @param strategy  it represents the strategy
	 * @author garimadawar
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}
	/**
	 * This method is used to execute the strategy
	 * @param gm  it contains the game map
	 * @param  pl  it contains player list
	 * @param player  it contains the player
	 * @throws InterruptedException 
	 * @author garima
	 * @return calls the appropriate executeStrategy method of player
	 */
	public String executeBehaviour(GameMap gm , PlayersList pl, Player player ) throws InterruptedException
	{	
		return this.strategy.executeStrategy(gm, pl, player);		
	}

}
