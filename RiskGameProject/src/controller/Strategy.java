package controller;

import model.GameMap;
import model.Player;
import model.PlayersList;
/**
 * This is the strategy interface which contains execute method
 */
public interface Strategy {
    /**
     * this method is used to execute strategy
     * @param gm this contains the game map
     * @param pl this contains the list of players
     * @param player this contains player data
     * @return String 
     * @author garimadawar
     */
	String executeStrategy(GameMap gm , PlayersList pl , Player player);
		
}
