package controller;

import java.util.ArrayList;
import java.util.Arrays;

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
	 * strategy this variables contains the strategy which is to be executed
	 */
	private Strategy strategy;
	
	/**
	 * This method is used to set the strategy
	 * @param strategy
	 */
	public void setStrategy(Strategy strategy)
	{
		this.strategy = strategy;
	}
	/**
	 * This method is used to execute the strategy
	 * @param strategy
	 */
	public String executeBehaviour(GameMap gm , PlayersList pl, Player player )
	{	
		return this.strategy.executeStrategy(gm, pl, player);		
	}

}
