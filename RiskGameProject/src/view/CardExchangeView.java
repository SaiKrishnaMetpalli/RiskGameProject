package view;

import model.Observable;
import model.PlayersList;

public class CardExchangeView implements Observer {

	public void update(Observable obs,Object x) {
		showMessage((PlayersList) obs);
	}
	
	private void showMessage(PlayersList pl) {
		
	}
}
