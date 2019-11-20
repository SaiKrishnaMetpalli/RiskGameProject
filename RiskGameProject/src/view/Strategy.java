package view;

import model.GameMap;
import model.Player;
import model.PlayersList;

public interface Strategy {

	String executeBehaviour(GameMap gm , Player p , PlayersList pl);
}
