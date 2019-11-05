package view;

import model.Observable;
import model.Player;
import model.PlayersList;

public class PlayerWorldDominationView implements Observer {

	public void update(Observable obs,Object x) {
		showMessage((PlayersList) obs,(Player) x);
	}

	private void showMessage(PlayersList pl, Player p) {
		
		
	}
}
