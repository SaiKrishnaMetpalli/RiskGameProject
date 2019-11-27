package view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Countries;
import model.Observable;
import model.Player;
import model.PlayersList;

/**
 * This class gives the information about player statistics
 * 
 * @author Sai Krishna
 *
 */
public class PlayerWorldDominationView implements Observer {

	/**
	 * This method is implemented from the Observer class
	 * 
	 * @author Sai Krishna
	 */
	public void update(Observable obs, Object x) {
		showMessage((PlayersList) obs, (Player) x);
	}

	/**
	 * This method gives the information about the player statistics
	 * 
	 * @param pl this variable contains all the players data
	 * @param p  this variable contains the current turn player data
	 */
	private void showMessage(PlayersList pl, Player p) {
		if (!p.getGameState().equals("STARTUP")) {
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
			int totalArmies = 0;
			totalArmies = getTotalArmiesCount(pl);
			System.out.format("%-30s|%-30s|%-30s|%-30s", "Player Name", "Total Armies", "% Map Controlled",
					"Continents Controlled");
			System.out.println();
			for (int dashes = 0; dashes < 120; dashes++)
				System.out.print("_");
			System.out.println();
			for (String playerName : pl.getListOfPlayers().keySet()) {
				int totalArmiesPlayer = 0;
				double percMapPlayer = 0.0;
				String continentsControlled = "";
				ArrayList<String> countriesList = pl.getListOfPlayers().get(playerName).getOwnedCountriesList();
				HashMap<String, Integer> countiresArmiesList = pl.getListOfPlayers().get(playerName)
						.getOwnedCountriesArmiesList();
				for (String countryName : countiresArmiesList.keySet()) {
					totalArmiesPlayer += countiresArmiesList.get(countryName);
				}

				percMapPlayer = ((double) countriesList.size() / (double) p.getTotalCountries()) * 100;

				for (String continentName : p.getContinentsCountryList().keySet()) {
					int count = 0;
					for (String conCountryName : p.getContinentsCountryList().get(continentName)) {
						for (String playerCountryName : countriesList) {
							if (playerCountryName.equals(conCountryName)) {
								count++;
							}
						}
					}
					if (count == p.getContinentsCountryList().get(continentName).size()) {
						continentsControlled += continentName+",";
					}
				}
				if (continentsControlled.equals("")) {
					continentsControlled = "---";
				} else {
					continentsControlled=continentsControlled.substring(0,continentsControlled.length()-1);
				}
				
				System.out.format("%-30s|%-30d|%-30.2f|%-30s", playerName, totalArmiesPlayer, percMapPlayer,
						continentsControlled);
				System.out.println();
			}
		}
	}

	private int getTotalArmiesCount(PlayersList pl) {
		int totalArmies = 0;
		for (String playerName : pl.getListOfPlayers().keySet()) {
			HashMap<String, Integer> countiresArmiesList = pl.getListOfPlayers().get(playerName)
					.getOwnedCountriesArmiesList();
			for (String countryName : countiresArmiesList.keySet()) {
				totalArmies += countiresArmiesList.get(countryName);
			}
		}
		return totalArmies;
	}
}
