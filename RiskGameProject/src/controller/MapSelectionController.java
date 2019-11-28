package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;

/**
 * All the map operations are performed in this controller
 * @author garima
 */
public class MapSelectionController {

	/**
	 * validatescanner is the Scanner Class object 
	 * List is used to put boundries valuues
	 * mapvalidate is the object of mapFormatvalidation class
	 * continentsStarted is the string where continents start in the map
	 * countriesStrated is the String where countries start in a map
	 * Country is the name of the country
	 * ContinentsDetails is array of all continents
	 * CountriesDetails is the array of all the countries
	 * boundry is the string where boundry is found
	 * cardsStrated is used when cards are found 
	 * playersDetails store the player data in array of string
	 * boundries details is used to store boundries values
	 * marked is used to validate a country is traversed for connected graph
	 * count maintains the count of countries traversed
	 * map store information about the game map
	 * coid is the continent id
	 * cid is country id
	 * 
	 */
	Scanner validateScanner;

	ArrayList<Integer> list;
	MapFormatValidation mapValidate = new MapFormatValidation();
	boolean flag = false;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country, cardsStarted;
	String[] continentsDetails, countriesDetails, boundriesDetails, cardDetails, playersDetails;

	// These two variables are used for map connected and traversal
	private ArrayList<Integer> marked;
	private int count;
	private HashMap<Integer, ArrayList<Integer>> map;
	private int coid = 0, cid = 0;

	/**
	 * This method will read the uploaded file
	 * 
	 * @param continents contains the information about continents
	 * @param countries  contains the information about countries
	 * @param boundries  contains the adjacency list of countries
	 * @param fileName   it is map file which is read
	 * @return success if map is loaded successfully else failure
	 * @throws FileNotFoundException throws error if file not found
	 * @author Ashish Chaudhary
	 */

	public String gameMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
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

	/**
	 * This method is used for adding the continents value
	 * 
	 * @param continents            this variable contains the continents list
	 * @param continentName         this variable contains name of the continent to
	 *                              be added
	 * @param continentControlValue this variable contains value of the control
	 *                              value to be added
	 * @return it returns a string message
	 * @author garimadawar
	 */
	public String addContinent(HashMap<Integer, Continents> continents, String continentName,
			String continentControlValue) {
		if (continents.size() > 0) {
			for (int i : continents.keySet()) {
				coid = i;
			}
		} else {
			coid = 0;
		}
		for (int i : continents.keySet()) {
			String c = continents.get(i).getContinentName();
			if (c.equals(continentName)) {
				return "Continent already exists";
			}
		}
		Continents co = new Continents(continentName, continentControlValue, "0");
		continents.put(++coid, co);
		return "Continent added successfully";
	}

	/**
	 * This method is used for removing the continents value
	 * 
	 * @param continentName this variable contains name of the continent to be
	 *                      removed
	 * @param continents    this variable contains the continents list
	 * @param countries     this variable contains the countries list
	 * @param boundries     this variable contains the boundries list
	 * @return returns messages
	 * @author garimadawar
	 */
	public String removeContinent(HashMap<Integer, Continents> continents,
			HashMap<Integer, ArrayList<Integer>> boundries, HashMap<Integer, Countries> countries,
			String continentName) {
		int removeState = 0;
		Iterator<Map.Entry<Integer, Continents>> iteratorContinents = continents.entrySet().iterator();
		Iterator<Map.Entry<Integer, Countries>> iteratorCountries = countries.entrySet().iterator();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> iteratorBoundries = boundries.entrySet().iterator();
		while (iteratorContinents.hasNext()) {
			Map.Entry<Integer, Continents> entryContinents = iteratorContinents.next();
			String c = entryContinents.getValue().getContinentName();
			if (c.equals(continentName)) {
				iteratorContinents.remove();
				removeState = 1;
				while (iteratorCountries.hasNext()) {
					Map.Entry<Integer, Countries> entryCountries = iteratorCountries.next();
					Countries key = entryCountries.getValue();
					if (key.getCountryContinentNum() == entryContinents.getKey()) {
						iteratorCountries.remove();
						removeState = 2;
						while (iteratorBoundries.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entryBoundries = iteratorBoundries.next();
							ArrayList<Integer> blist = entryBoundries.getValue();
							if (entryBoundries.getKey() == entryCountries.getKey()) {
								iteratorBoundries.remove();
								removeState = 3;
							} else {
								if (blist.contains(entryCountries.getKey())) {
									blist.remove(Integer.valueOf(entryCountries.getKey()));
								}
							}
						}
					}
				}
			}
		}
		if (removeState == 1) {
			return "Continent removed successfully";
		} else if (removeState == 2) {
			return "Continents and countries under continent removed successfully";
		} else if (removeState == 3) {
			return "Continents and countries under continent and neighbours under country removed successfully";
		} else {
			return "Continent does not exist";
		}

	}

	/**
	 * 
	 * This method is used for adding the countries value
	 * 
	 * @param countryName   this variable contains name of the country to be added
	 * @param continentName this variable contains name of the continent to which
	 *                      country is added
	 * @param continents    this variable contains the continents list
	 * @param countries     this variable contains the countries list
	 * @param boundries		this variable contains the boundries
	 * @return it returns string messages
	 * @author garimadawar
	 */
	public String addCountry(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String countryName, String continentName) {
		boolean conFlag = false, couFlag = false;
		;
		int continentNum = 0;
		if (countries.size() > 0) {
			for (int i : countries.keySet()) {
				cid = i;
			}
		} else {
			cid = 0;
		}
		for (int i : continents.keySet()) {
			String s = continents.get(i).getContinentName();
			if (s.equals(continentName)) {
				continentNum = i;
				conFlag = true;
				break;
			}
		}
		if (conFlag) {
			if (countries.size() > 0) {
				for (int j : countries.keySet()) {
					String c = countries.get(j).getCountryName();
					if (c.equals(countryName)) {
						return "Country already exists";
					} else {
						couFlag = true;
					}
				}
			} else {
				Countries ca = new Countries(countryName, continentNum, "0", "0");
				countries.put(++cid, ca);
				boundries.put(cid, new ArrayList<Integer>());
				return "Country added successfully";
			}

		} else {
			return "Continent does not exists. Please add the Continent first";
		}
		if (couFlag) {
			Countries ca = new Countries(countryName, continentNum, "0", "0");
			countries.put(++cid, ca);
			boundries.put(cid, new ArrayList<Integer>());
			return "Country added successfully";
		}
		return "Failure";
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param countries   this variable contains the continents list
	 * @param boundries   this variable contains the boundries list
	 * @param countryName this variable contains the country to be removed
	 * @return it returns string messages
	 * @author garimadawar
	 */
	public String removeCountry(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName) {
		int removeState = 0;
		Iterator<Map.Entry<Integer, Countries>> iteratorCountries = countries.entrySet().iterator();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> iteratorBoundries = boundries.entrySet().iterator();

		while (iteratorCountries.hasNext()) {
			Map.Entry<Integer, Countries> entryCountries = iteratorCountries.next();
			Countries key = entryCountries.getValue();
			if (key.getCountryName().equals(countryName)) {
				iteratorCountries.remove();
				removeState = 1;
				while (iteratorBoundries.hasNext()) {
					Map.Entry<Integer, ArrayList<Integer>> entryBoundries = iteratorBoundries.next();
					ArrayList<Integer> blist = entryBoundries.getValue();
					if (entryBoundries.getKey() == entryCountries.getKey()) {
						iteratorBoundries.remove();
						removeState = 2;
					} else {
						if (blist.contains(entryCountries.getKey())) {
							blist.remove(Integer.valueOf(entryCountries.getKey()));
						}
					}
				}
			}
		}

		if (removeState == 1) {
			return "Country removed successfully";
		} else if (removeState == 2) {
			return "Country and it's neighbours are removed successfully";
		} else {
			return "Country does not exists. Please add the Country first";
		}
	}

	/**
	 * This method is used for adding the neighbor countries value
	 * 
	 * @param countryName          this variable contains name of the country
	 * @param neighbourCountryName this variable contains the name of neighboring
	 *                             country
	 * @param countries            this variable contains the countries list
	 * @param boundries            this variable contains the boundries list
	 * @return it return string messages
	 * @author garimadawar
	 */
	public String addNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {
		boolean couFlag = false, neighFlag = false;
		int couNum = 0, neighNum = 0;
		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				couNum = i;
				couFlag = true;
				break;
			}
		}
		if (couFlag) {
			for (int l : countries.keySet()) {
				String m = countries.get(l).getCountryName();
				if (neighbourCountryName.equals(m)) {
					neighNum = l;
					neighFlag = true;
					break;
				}
			}
			if (neighFlag) {
				if (boundries.size() > 0) {
					if (boundries.containsKey(couNum)) {
						if (boundries.get(couNum).contains(neighNum)) {
							return "Neighbour country is already present in neighbour list";
						} else {
							boundries.get(couNum).add(neighNum);
						}
					} else {
						ArrayList<Integer> li = new ArrayList<Integer>();
						li.add(neighNum);
						boundries.put(couNum, li);
					}
				} else {
					ArrayList<Integer> li = new ArrayList<Integer>();
					li.add(neighNum);
					boundries.put(couNum, li);
				}
				return "Neighbour country is added successfully";
			} else {
				return "Neighbour country does not exists. Please add the country and then neighbour";
			}
		} else {
			return "Country does not exists. Please add the country and then neighbour";
		}
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param boundries            this variable contains the boundaries list
	 * @param countries            this variable contains the countries list
	 * @param neighbourCountryName this variable contains the country to be removed
	 *                             from neighbour
	 * @param countryName          this variable is the name of the country which
	 *                             contains neighbor country name
	 * @return it returns string message
	 * @author garimadawar
	 */
	public String removeNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {

		boolean couFlag = false, neighFlag = false;
		int couNum = 0, neighNum = 0;
		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				couNum = i;
				couFlag = true;
				break;
			}
		}
		if (couFlag) {
			for (int l : countries.keySet()) {
				String m = countries.get(l).getCountryName();
				if (neighbourCountryName.equals(m)) {
					neighNum = l;
					neighFlag = true;
					break;
				}
			}
			if (neighFlag) {
				if (boundries.get(couNum).contains(neighNum)) {
					boundries.get(couNum).remove(Integer.valueOf(neighNum));
					return "Neighbour country is removed successfully";
				} else {
					return "Neighbour country is not present in neighbours list";
				}
			} else {
				return "Neighbour country does not exists";
			}
		} else {
			return "Country does not exists";
		}

	}

	/**
	 * method for write text file from data structure uses buffer reader and writer
	 * to write text file stores the file in the resource folder
	 * 
	 * @param continents this variable is used for storing continents
	 * @param countries this variable is used for storing countries
	 * @param boundries this variable is used for storing boundries
	 * @param mapFile this variable is used for storing file name 
	 * @throws IOException throws exception in file writing
	 * @author sakib
	 */
	public void writeGameMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + mapFile;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();
		bw.write("name " + mapFile + " Map");
		bw.write("\n");
		bw.write("\n");
		bw.write("[files]");
		bw.write("\n");
		bw.write("\n");
		bw.write("[continents]");
		bw.newLine();
		for (Integer i : continents.keySet()) {
			Continents c = continents.get(i);
			bw.write(c.getContinentName() + " " + c.getcontinentControlValue() + " " + c.getColour());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[countries]");
		bw.newLine();
		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			bw.write(i + " " + c1.getCountryName() + " " + c1.getCountryContinentNum() + " " + c1.getxCoordinate() + " "
					+ c1.getyCoordinate());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[borders]");
		bw.newLine();
		for (Integer s : boundries.keySet()) {
			ArrayList<Integer> tempal = new ArrayList<Integer>();
			String adjacency = "";
			tempal = boundries.get(s);
			for (Integer s1 : tempal) {
				adjacency += s1 + " ";
			}
			bw.write(s + " " + adjacency.trim());
			bw.newLine();
		}
		bw.write("\n");

		bw.close();
	}

	/**
	 * This method checks the continents and country validation for connection of
	 * map
	 * 
	 * @param continents this variable contains the list of continents
	 * @param countries  this variable contains the list of countries
	 * @return true if contientNum in countries are equal with continents
	 *         object;else false
	 * @author Sai Krishna
	 */
	public boolean checkContinentsCountriesValidation(HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries) {
		ArrayList<Integer> continentsList = new ArrayList<Integer>();
		for (Integer countryNum : countries.keySet()) {
			Countries countryObject = countries.get(countryNum);
			if (!continentsList.contains(countryObject.getCountryContinentNum())) {
				continentsList.add(countryObject.getCountryContinentNum());
			}
		}
		if (continentsList.size() == continents.size()) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * This method is used for checking map connectivity
	 * 
	 * @param boundries This variable contains the adjacency list of countries
	 * @return it returns true if map is connected; otherwise false
	 * @author Sai Krishna
	 */
	public boolean isConnectedMap(HashMap<Integer, ArrayList<Integer>> boundries) {
		map = boundries;
		marked = new ArrayList<Integer>();
		count = 0;
		Map.Entry<Integer, ArrayList<Integer>> entry = boundries.entrySet().iterator().next();
		mapTraversal(entry.getKey());
		if (count == boundries.size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used for traversing the map
	 * 
	 * @param vertex this variable contains one of the vertex of map
	 * @author Sai Krishna
	 */
	public void mapTraversal(int vertex) {
		count++;
		marked.add(vertex);
		for (int i : getNeighbours(vertex)) {
			if (!(marked.contains(i))) {
				mapTraversal(i);
			}
		}

	}

	/**
	 * This method gives you the neighbors of a vertex
	 * 
	 * @param v this variable contains vertex of the map
	 * @return this returns array list of neighbors if present; otherwise null
	 * @author Sai Krishna
	 */
	public ArrayList<Integer> getNeighbours(int v) {
		return new ArrayList<Integer>(map.get(v));
	}

	/**
	 * This method creates empty file
	 * 
	 * @param fileName this variable contains the filename to be created
	 * @author Sai Krishna
	 */
	public void createEmptyFile(String fileName) {
		try {
			String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
			File mapFile = new File(createPath);
			mapFile.createNewFile();
		} catch (Exception ex) {

		}

	}

	/**
	 * This method save the state of the game
	 * 
	 * @param gs       this variable contains the game state information
	 * @param fileName this variable contains the filename to be stored
	 * @throws IOException throws error on file exceptions
	 * @return it returns success message
	 * @author Sai Krishna
	 */
	public String saveGameFile(GameState gs, String fileName) throws IOException {
		GameMap gm = gs.getGameMap();
		PlayersList pl = gs.getPlayersList();
		Player p = gs.getPlayer();

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();

		// writing continents to the file
		bw.write("[continents]");
		bw.newLine();
		for (Integer i : gm.getContinents().keySet()) {
			Continents c = gm.getContinents().get(i);
			bw.write(i + " " + c.getContinentName() + " " + c.getcontinentControlValue());
			bw.newLine();
		}

		// writing countries to the file
		bw.write("\n");
		bw.write("[countries]");
		bw.newLine();
		for (Integer i : gm.getCountries().keySet()) {
			Countries c1 = gm.getCountries().get(i);
			bw.write(i + " " + c1.getCountryName() + " " + c1.getCountryContinentNum() + " " + c1.getOwnerName());
			bw.newLine();
		}

		// writing borders to the file
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

		// writing the total cards list to the file
		bw.write("\n");
		bw.write("[totalCardsList]");
		bw.newLine();
		if (gm.getTotalCardsList().size() > 0) {
			String cardsList = "";
			for (String cardName : gm.getTotalCardsList()) {
				cardsList += cardName + ",";
			}
			cardsList = cardsList.substring(0, cardsList.length() - 1);
			bw.write(cardsList);
		} else {
			bw.write(0);
		}
		bw.newLine();

		// checking if the players are present or not
		if (pl.getListOfPlayers().size() > 0) {

			// writing the players info to the file
			bw.write("\n");
			bw.write("[players]");
			bw.newLine();

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
							+ " " + cardsList);
				} else {
					bw.write(playerName + " " + playerDetails.getStrategy() + " " + ownedCountries + " " + ownedArmies);
				}
				bw.newLine();
			}
		} else if (gm.getPlayersWithStrategies().size() > 0) {

			bw.write("\n");
			bw.write("[players]");
			bw.newLine();

			for (String playerName : gm.getPlayersWithStrategies().keySet()) {
				bw.write(playerName + " " + gm.getPlayersWithStrategies().get(playerName));
				bw.newLine();
			}
		}

		// writing the turn information to the file
		bw.write("\n");
		bw.write("[turnInformation]");
		bw.newLine();
		bw.write(p.getGameState());
		bw.newLine();

		// checking for the state of the game to write the required properties to the
		// file
		if (p.getGameState().equals("REINFORCE")) {
			bw.write(p.getCurrentPlayerTurn());
			bw.newLine();
			bw.write(p.getAvailableReinforceArmies() + " " + p.getCardReward() + " " + p.getCardBonusArmy());
			bw.newLine();
		} else if (p.getGameState().equals("ATTACK") || p.getGameState().equals("FORTIFY")) {
			bw.write(p.getCurrentPlayerTurn());
			bw.newLine();
			bw.write(p.getAvailableReinforceArmies() + " " + p.getCardReward() + " " + p.getCardBonusArmy());
			bw.newLine();
			if (!p.getAttackerName().equals("")) {
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
						bw.write(p.getAttackerName() + " " + p.getAttackerCountry() + " " + attackerDice + " "
								+ p.getDiceRolled() + " " + conqueredCountries);
					} else {
						bw.write(p.getAttackerName() + " " + p.getAttackerCountry() + " " + attackerDice + " "
								+ p.getDiceRolled());
					}
				} else {
					bw.write(p.getAttackerName() + " " + p.getAttackerCountry());
				}
				bw.newLine();
			}

			if (!p.getDefenderName().equals("")) {
				if (p.getDefenderDice().size() > 0) {
					String defenderDice = "";
					for (Integer diceNum : p.getDefenderDice()) {
						defenderDice += diceNum + ",";
					}
					defenderDice = defenderDice.substring(0, defenderDice.length() - 1);
					bw.write(p.getDefenderName() + " " + p.getDefenderCountry() + " " + defenderDice);
				} else {
					bw.write(p.getDefenderName() + " " + p.getDefenderCountry());
				}
				bw.newLine();
			}

		}

		bw.close();
		return "Success";
	}

	/**
	 * This method loads the game from the file
	 * 
	 * @param gm       this variable contains the info about the game map
	 * @param pl       this variable contains the info about all the players
	 * @param p        this variable contains the info about the turn
	 * @param fileName this variable contains the filename to read from
	 * @return returns success if read successfully else failure
	 * @throws FileNotFoundException throws error on file exceptions
	 * @author Sai Krishna
	 */
	public String loadGameReading(GameMap gm, PlayersList pl, Player p, String fileName) throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);
		Scanner textScanner = new Scanner(file);

		try {

			// getting the continents from the file
			while (textScanner.hasNext()) {
				String continent = textScanner.nextLine();
				if (continent.equals("[continents]")) {
					break;
				}
			}

			while (textScanner.hasNext()) {
				continentsStarted = textScanner.nextLine();
				if (continentsStarted.length() > 0) {
					continentsDetails = continentsStarted.split(" ");
					Continents c1 = new Continents(continentsDetails[1], continentsDetails[2], "0");
					gm.getContinents().put(Integer.parseInt(continentsDetails[0]), c1);
				} else
					break;
			}

			// getting the countries from the file
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
					Countries c2;
					if (countriesDetails.length == 4) {
						c2 = new Countries(countriesDetails[1], Integer.parseInt(countriesDetails[2]), "0", "0");
						c2.setOwnerName(countriesDetails[3]);
					} else {
						c2 = new Countries(countriesDetails[1], Integer.parseInt(countriesDetails[2]), "0", "0");
					}
					gm.getCountries().put(Integer.parseInt(countriesDetails[0]), c2);
				} else
					break;
			}

			p.setTotalCountries(gm.getCountries().size());

			// getting the borders from the file
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
					gm.getBoundries().put(Integer.parseInt(boundriesDetails[0]), list);
				} else
					break;
			}

			// getting the cards list from the file
			while (textScanner.hasNext()) {
				String cardList = textScanner.nextLine();
				if (!cardList.equals("[totalCardsList]")) {
					continue;
				} else
					break;
			}

			while (textScanner.hasNext()) {
				cardsStarted = textScanner.nextLine();
				if (cardsStarted.length() > 0) {
					if (cardsStarted.equals("0")) {
						gm.setTotalCardsList(new ArrayList<String>());
					} else {
						cardDetails = cardsStarted.split(",");
						ArrayList<String> cardsList = new ArrayList<String>();
						for (String cardName : cardDetails) {
							cardsList.add(cardName);
						}
						gm.setTotalCardsList(cardsList);
					}
				} else
					break;
			}

			// getting the players info if present in the file
			String headingName = "";
			while (textScanner.hasNext()) {
				headingName = textScanner.nextLine();
				if (!(headingName.equals("[players]") || headingName.equals("[turnInformation]"))) {
					continue;
				} else
					break;
			}

			// writing to the model class, the player info based on the properties info
			// available
			if (headingName.equals("[players]")) {
				while (textScanner.hasNext()) {
					String playersStarted = textScanner.nextLine();
					if (playersStarted.length() > 0) {
						playersDetails = playersStarted.split(" ");
						gm.getPlayersSetup().add(playersDetails[0]);
						gm.getPlayersWithStrategies().put(playersDetails[0], playersDetails[1]);
						if (playersDetails.length == 4) {
							String[] ownedCountries = playersDetails[2].split(",");
							String[] ownedArmies = playersDetails[3].split(",");
							Player player = new Player();
							player.setStrategy(playersDetails[1]);
							for (int i = 0; i < ownedCountries.length; i++) {
								player.getOwnedCountriesList().add(ownedCountries[i]);
								player.getOwnedCountriesArmiesList().put(ownedCountries[i],
										Integer.parseInt(ownedArmies[i]));
							}
							pl.getListOfPlayers().put(playersDetails[0], player);
						} else if (playersDetails.length == 5) {
							String[] ownedCountries = playersDetails[2].split(",");
							String[] ownedArmies = playersDetails[3].split(",");
							Player player = new Player();
							player.setStrategy(playersDetails[1]);
							for (int i = 0; i < ownedCountries.length; i++) {
								player.getOwnedCountriesList().add(ownedCountries[i]);
								player.getOwnedCountriesArmiesList().put(ownedCountries[i],
										Integer.parseInt(ownedArmies[i]));
							}
							String[] playercardsList = playersDetails[4].split(",");
							ArrayList<String> cardsList = new ArrayList<String>();
							for (String cardName : playercardsList) {
								cardsList.add(cardName);
							}
							p.setCurrentCardList(cardsList);
							pl.getListOfPlayers().put(playersDetails[0], player);
						}
					} else
						break;
				}

				// getting the turn info from the file
				while (textScanner.hasNext()) {
					String turnInfo = textScanner.nextLine();
					if (!turnInfo.equals("[turnInformation]")) {
						continue;
					} else
						break;
				}

				// writing to the required properties based on the info available
				while (textScanner.hasNext()) {
					p.setGameState(textScanner.nextLine());
					if (p.getGameState().equals("REINFORCE")) {
						p.setCurrentPlayerTurn(textScanner.nextLine());
						String[] headerReinforceDetails = textScanner.nextLine().split(" ");
						p.setAvailableReinforceArmies(Integer.parseInt(headerReinforceDetails[0]));
						p.setCardReward(Integer.parseInt(headerReinforceDetails[1]));
						p.setCardBonusArmy(Integer.parseInt(headerReinforceDetails[2]));
					} else if (p.getGameState().equals("ATTACK") || p.getGameState().equals("FORTIFY")) {
						p.setCurrentPlayerTurn(textScanner.nextLine());
						String[] headerReinforceDetails = textScanner.nextLine().split(" ");
						p.setAvailableReinforceArmies(Integer.parseInt(headerReinforceDetails[0]));
						p.setCardReward(Integer.parseInt(headerReinforceDetails[1]));
						p.setCardBonusArmy(Integer.parseInt(headerReinforceDetails[2]));

						if (textScanner.hasNext()) {
							String[] attackInfoDetails = textScanner.nextLine().split(" ");
							if (attackInfoDetails.length == 4) {
								p.setAttackerName(attackInfoDetails[0]);
								p.setAttackerCountry(attackInfoDetails[1]);
								ArrayList<Integer> attackerDice = new ArrayList<Integer>();
								String[] attackerDiceInfo = attackInfoDetails[2].split(",");
								for (String diceInfo : attackerDiceInfo) {
									attackerDice.add(Integer.parseInt(diceInfo));
								}
								p.setAttackerDice(attackerDice);
								p.setDiceRolled(Integer.parseInt(attackInfoDetails[3]));

							} else if (attackInfoDetails.length == 5) {
								p.setAttackerName(attackInfoDetails[0]);
								p.setAttackerCountry(attackInfoDetails[1]);
								ArrayList<Integer> attackerDice = new ArrayList<Integer>();
								String[] attackerDiceInfo = attackInfoDetails[2].split(",");
								for (String diceInfo : attackerDiceInfo) {
									attackerDice.add(Integer.parseInt(diceInfo));
								}
								p.setAttackerDice(attackerDice);
								p.setDiceRolled(Integer.parseInt(attackInfoDetails[3]));
								ArrayList<String> conqueredList = new ArrayList<String>();
								String[] conqueredInfo;
								if (attackInfoDetails[4].contains(",")) {
									conqueredInfo = attackInfoDetails[4].split(",");
								} else {
									conqueredInfo = new String[1];
									conqueredInfo[0] = attackInfoDetails[4];
								}
								for (String conqName : conqueredInfo) {
									conqueredList.add(conqName);
								}
								p.setConqueredCountries(conqueredList);
							}
						}

						if (textScanner.hasNext()) {
							String[] defenderInfoDetails = textScanner.nextLine().split(" ");
							p.setDefenderName(defenderInfoDetails[0]);
							p.setDefenderCountry(defenderInfoDetails[1]);
						}
					}
				}

			} else {
				while (textScanner.hasNext()) {
					p.setGameState(textScanner.nextLine());
				}
			}

			textScanner.close();
			return "Success";
		} catch (Exception ex) {
			textScanner.close();
			return "Failure";
		}

	}

}
