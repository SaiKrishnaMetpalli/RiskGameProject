package view;

import model.Observable;
import model.Player;
import model.PlayersList;

public class PlayerWorldDominationView implements Observer {

	public void update(Observable obs,Object x) {
		showMessage((PlayersList) obs,(Player) x);
	}

	private void showMessage(PlayersList pl, Player p) {
		if(p.getGameState()!="STARTUP") {
			for (int dashes = 0; dashes < 90; dashes++) {
				System.out.print("_");
			}
			System.out.println("\nPlayer World Domination View");
			System.out.println();
			for (int dashes = 0; dashes < 90; dashes++) {
				System.out.print("_");
			}
			System.out.println();
		}		
	}
}
