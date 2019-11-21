package controller;

import model.GameMap;
import model.Player;
import model.PlayersList;

public interface Strategy {

	String executeStrategy(GameMap gm , PlayersList pl , Player player);
		
}
