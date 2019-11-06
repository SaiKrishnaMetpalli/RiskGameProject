package view;

import model.Observable;
import model.Player;

public class PhaseView implements Observer {

	public void update(Observable obs,Object x) {
		showMessage((Player) obs);
	}

	private void showMessage(Player p) {
		if(p.getGameState()!="STARTUP") {
			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println("\nPhase View");
			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println("\nGame State: "+p.getGameState());
			System.out.println("\nCurrent Player Name: "+p.getCurrentPlayerTurn());
			System.out.println("\nActions Performed:");
			System.out.println(p.getActionsPerformed());
		}		
	}
}
