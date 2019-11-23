package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;

public class ConquestReadWriteController {

	ArrayList<Integer> list;
	MapFormatValidation mapValidate = new MapFormatValidation();
	boolean flag = false;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	String[] continentsDetails, countriesDetails, boundriesDetails;
	
	public String conquestMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);
		Scanner textScanner = new Scanner(file);
		try {
			flag = mapValidate.validateFile(file);

			if (flag) {

				while (textScanner.hasNext()) {
					String continent = textScanner.nextLine();
					if (continent.equals("[continents]")) {
						break;
					}

				}
				int continentsCount = 0;
				while (textScanner.hasNext()) {
					continentsCount++;
					continentsStarted = textScanner.nextLine();
					if (continentsStarted.length() > 0) {

						continentsDetails = continentsStarted.split(" ");
						Continents c1 = new Continents(continentsDetails[0], continentsDetails[1],
								continentsDetails[2]);

						continents.put(continentsCount, c1);
					} else
						break;
				}

				while (textScanner.hasNext()) {

					country = textScanner.nextLine();
					if (!country.equals("[countries]")) {
						continue;
					} else
						break;
				}

				while (textScanner.hasNext()) {

					countriesStarted = textScanner.nextLine();

					if (countriesStarted.length() > 0) {

						countriesDetails = countriesStarted.split(" ");
						Countries c2 = new Countries(countriesDetails[1], Integer.parseInt(countriesDetails[2]),
								countriesDetails[3], countriesDetails[4]);

						countries.put(Integer.parseInt(countriesDetails[0]), c2);
					} else
						break;

				}

				while (textScanner.hasNext()) {

					boundry = textScanner.nextLine();
					if (!boundry.equals("[borders]")) {
						continue;
					} else
						break;
				}

				while (textScanner.hasNext()) {
					boundriesStarted = textScanner.nextLine();
					if (boundriesStarted.length() > 0) {
						boundriesDetails = boundriesStarted.split(" ");
						list = new ArrayList<Integer>();

						for (int i = 1; i < boundriesDetails.length; i++) {
							list.add(Integer.parseInt(boundriesDetails[i]));
						}

						boundries.put(Integer.parseInt(boundriesDetails[0]), list);
					} else
						break;
				}
				textScanner.close();
				return "Success";

			} else {
				textScanner.close();
				return "Failure";
			}

		} catch (Exception ex) {

			return "Failure";
		}

	}

	public void saveConquestGameFile(GameState gs, String fileName) throws IOException {
		GameMap gm = gs.getGameMap();
		PlayersList pl = gs.getPlayersList();
		Player p = gs.getPlayer();

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();

		bw.write("[continents]");
		bw.newLine();
		for (Integer i : gm.getContinents().keySet()) {
			Continents c = gm.getContinents().get(i);
			bw.write(i + " " + c.getContinentName() + " " + c.getcontinentControlValue());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[countries]");
		bw.newLine();
		for (Integer i : gm.getCountries().keySet()) {
			Countries c1 = gm.getCountries().get(i);
			bw.write(i + " " + c1.getCountryName() + " " + c1.getCountryContinentNum() + " " + c1.getOwnerName());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[borders]");
		bw.newLine();
		for (Integer s : gm.getBoundries().keySet()) {
			ArrayList<Integer> tempal = new ArrayList<Integer>();
			String adjacency = "";
			tempal = gm.getBoundries().get(s);
			for (Integer s1 : tempal) {
				adjacency += s1 + " ";
			}
			bw.write(s + " " + adjacency.trim());
			bw.newLine();
		}
		bw.write("\n");
		bw.write("[players]");
		bw.newLine();
		if (pl.getListOfPlayers().size() > 0) {
			for (String playerName : pl.getListOfPlayers().keySet()) {
				Player playerDetails = pl.getListOfPlayers().get(playerName);
				String ownedCountries = "";
				String ownedArmies = "";
				for (String countryName : playerDetails.getOwnedCountriesArmiesList().keySet()) {
					ownedCountries += countryName + ",";
					ownedArmies += playerDetails.getOwnedCountriesArmiesList().get(countryName) + ",";
				}
				ownedCountries = ownedCountries.substring(0, ownedCountries.length() - 1);
				ownedArmies = ownedArmies.substring(0, ownedArmies.length() - 1);
				if (playerDetails.getCurrentCardList().size() > 0) {
					String cardsList = "";
					for (String cardName : playerDetails.getCurrentCardList()) {
						cardsList += cardName + ",";
					}
					cardsList = cardsList.substring(0, cardsList.length() - 1);
					bw.write(playerName + " " + playerDetails.getStrategy() + " " + ownedCountries + " " + ownedArmies
							+ "" + cardsList);
				} else {
					bw.write(playerName + " " + playerDetails.getStrategy() + " " + ownedCountries + " " + ownedArmies);
				}
				bw.newLine();
			}

			bw.write("\n");
			bw.write("[turnInformation]");
			bw.newLine();
			bw.write(p.getGameState());
			bw.newLine();
			if (p.getGameState().equals("REINFORCE")) {
				// write turn information
				bw.write(p.getCurrentPlayerTurn());
				bw.newLine();
				bw.write(p.getAvailableReinforceArmies() + " " + p.getCardReward() + " " + p.getCardBonusArmy());
				bw.newLine();
			} else if (p.getGameState().equals("ATTACK") || p.getGameState().equals("FORTIFY")) {
				bw.write(p.getCurrentPlayerTurn());
				bw.newLine();
				bw.write(p.getAvailableReinforceArmies() + " " + p.getCardReward() + " " + p.getCardBonusArmy());
				bw.newLine();
				if (p.getAttackerDice().size() > 0) {
					String attackerDice = "";
					for (Integer diceNum : p.getAttackerDice()) {
						attackerDice += diceNum + ",";
					}
					attackerDice = attackerDice.substring(0, attackerDice.length() - 1);
					if (p.getConqueredCountries().size() > 0) {
						String conqueredCountries = "";
						for (String conqCon : p.getConqueredCountries()) {
							conqueredCountries += conqCon + ",";
						}
						conqueredCountries = conqueredCountries.substring(0, conqueredCountries.length() - 1);
						bw.write(p.getAttackerName() + " " + p.getAttackerCountry() + " " + p.getAttackerDice() + " "
								+ p.getDiceRolled() + " " + conqueredCountries);
					} else {
						bw.write(p.getAttackerName() + " " + p.getAttackerCountry() + " " + p.getAttackerDice() + " "
								+ p.getDiceRolled());
					}
				}

				bw.newLine();
				bw.write(p.getDefenderName() + " " + p.getDefenderCountry() + " " + p.getDefenderDice());

			}
		}

		bw.close();
	}
}
