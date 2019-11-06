package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Countries;
import model.Observable;
import model.Player;
import model.PlayersList;

public class PlayerWorldDominationView implements Observer {

	public void update(Observable obs,Object x) {
		showMessage((PlayersList) obs,(Player) x);
	}

	private void showMessage(PlayersList pl, Player p) {
		if(p.getGameState()!="STARTUP") {
			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println("\nPlayer World Domination View");
			for (int dashes = 0; dashes < 120; dashes++) {
				System.out.print("_");
			}
			System.out.println();
			System.out.println();
			int totalArmies=0;
			totalArmies=getTotalArmiesCount(pl);
			System.out.format("%-30s|%-30s|%-30s|%-30s", "Player Name", "Total Armies",
					"% Map Controlled", "Continents Controlled");
			System.out.println();
			for (int dashes = 0; dashes < 120; dashes++)
				System.out.print("_");
			System.out.println();
			for(String playerName:pl.getListOfPlayers().keySet()) {
				int totalArmiesPlayer=0;
				double percMapPlayer=0.0;
				String continentsControlled="";
				ArrayList<String> countiresList=pl.getListOfPlayers().get(playerName).getOwnedCountriesList();
				HashMap<String,Integer> countiresArmiesList=pl.getListOfPlayers().get(playerName).getOwnedCountriesArmiesList();
				for(String countryName:countiresArmiesList.keySet()) {
					totalArmiesPlayer+=countiresArmiesList.get(countryName);
				}
				percMapPlayer=((double) totalArmiesPlayer/(double) totalArmies)*100;
				Collections.sort(countiresList);				
				for(String continentName:p.getContinentsCountryList().keySet()) {
					Collections.sort(p.getContinentsCountryList().get(continentName));
					if(countiresList.equals(p.getContinentsCountryList().get(continentName))) {
						continentsControlled+=continentName;
					}
				}
				if(continentsControlled.equals("")) {
					continentsControlled="---";
				}
				System.out.format("%-30s|%-30d|%-30.2f|%-30s", playerName,totalArmiesPlayer,percMapPlayer,continentsControlled);
				System.out.println();
			}
		}		
	}

	private int getTotalArmiesCount(PlayersList pl) {
		int totalArmies=0;
		for(String playerName:pl.getListOfPlayers().keySet()) {			
			HashMap<String,Integer> countiresArmiesList=pl.getListOfPlayers().get(playerName).getOwnedCountriesArmiesList();
			for(String countryName:countiresArmiesList.keySet()) {
				totalArmies+=countiresArmiesList.get(countryName);
			}
		}
		return totalArmies;
	}
}
