package view;

import model.Observable;
import model.Player;

/**
 * This class gives the the information about the game state, current player and actions performed
 * @author Sai Krishna
 *
 */
public class PhaseView implements Observer {

	/**
	 * This method is implemented from the Observer class
	 * @author Sai Krishna
	 */
	public void update(Observable obs,Object x) {
		showMessage((Player) obs);
	}

	/**
	 * This method displays the current player, game state and actions performed
	 * @param p This variable contains the current turn player data
	 */
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
