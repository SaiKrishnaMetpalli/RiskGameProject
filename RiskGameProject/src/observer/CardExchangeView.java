package observer;

import java.util.ArrayList;

import model.Player;
import model.PlayersList;

/**
 * This class gives the information about the cards owned by the player
 * 
 * @author Sai Krishna
 *
 */
public class CardExchangeView implements Observer {

	/**
	 * This method is implemented from the Observer class
	 * 
	 * @author Sai Krishna
	 */
	public void update(Observable obs, Object x) {
		showMessage((PlayersList) obs, (Player) x);
	}

	/**
	 * This method displays the cards owned by the player
	 * 
	 * @param pl this variable contains all the players data
	 * @param p  this variable contains the current turn player data
	 * @author Sai Krishna
	 */
	private void showMessage(PlayersList pl, Player p) {
		int count = 1;
		if (p.getGameState().equals("REINFORCE")) {
			ArrayList<String> cardList = pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList();
			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println("\nCard Exchange View");

			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println();
			if (cardList.size() > 0) {
				for (String s : cardList) {
					System.out.println(count + ". " + s);
					count++;
				}
			} else {
				System.out.println(0);
			}
		}
	}
}
