package controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

import model.Continents;
import model.Countries;
import model.GameMap;
import model.Player;
import model.PlayersList;

/**
 * This class contains all the methods for reinforcement, attack and
 * fortification
 * 
 * @author Sai Krishna
 *
 */
public class PlayerController {

	/**
	 * cc is the common controller object
	 * adjency list is the list of nighbouring countries
	 * attackerarmies map is the map of armies for the attacker
	 * defender armies map is the map of armies for defender
	 * attacker dice number list is the list of dice rolled results
	 * defender dice number is the list of defender dice rolled results
	 * country list is the list of all the countries
	 * countryarmieslist is the list of alll the list of armies for each country
	 * armiesleftwith attacker is list of armies left with attacker
	 * conquered countrieslist is the list of conquered countries by attacker
	 * dicerolled result is the result of dice rolled
	 * numberofattackerarmy is the count of attacker army
	 * numofdefenderarmy is the countr of defender army
	 * attackerArmyleft is the number of army left with attacker
	 * ownedCountriesArmy is the list of countries owned stored by number
	 * owned path is boolean value to check path between two countries
	 * marked is the array of boolean values to check connected graph
	 * 
	 */
	CommonController cc = new CommonController();
	ArrayList<Integer> adjancyList;
	HashMap<String, Integer> attackerArmiesMap;
	HashMap<String, Integer> defenderArmiesMap;
	ArrayList<Integer> attackerDiceNumbersList;
	ArrayList<Integer> defenderDiceNumbersList;
	ArrayList<String> countryList;
	HashMap<String, Integer> countryArmyList;
	ArrayList<Integer> armiesLeftAfterAttack;
	ArrayList<String> conqueredCountriesList;
	int diceRolledResult = 0;
	int numOfAttackerArmy = 0;
	int numOfDefenderArmy = 0;
	int attackerArmyLeft = 0;
	ArrayList<Integer> ownedCountriesNumberList = new ArrayList<Integer>();;
	boolean ownedPath = false;;
	boolean marked[];

	/**
	 * calculates the reinforcement reward armies for total owned country
	 * 
	 * @param player player object containing all player information
	 * @return returns country reward armies obtained as integer
	 * @author sakib
	 */
	public int calculateOwnedCountryReward(Player player) {
		return (int) Math.floor(player.getOwnedCountriesList().size() / 3);
	}

	/**
	 * calculates the reinforcement armies reward continent reward
	 * 
	 * @param player      player object containing all player information
	 * @param continents  hashmap containing continent objects
	 * @param countries   hashmap containing countries objects
	 * @param countryName the country for which we need the continents and country
	 *                    object information
	 * @param cardReward  the reward army obtained after card exchange
	 * @return returns reward armies for continent obtained as integer
	 * @author sakib
	 */

	public int calculateContinentReward(Player player, HashMap<Integer, Continents> continents,
			HashMap<Integer, Countries> countries, String countryName) {
		int continentReward = 0;
		HashMap<String, ArrayList<String>> listmap = cc.getContinentsCountryList(continents, countries);
		Continents cont = cc.getContinentByCountryName(continents, countries, countryName);
		ArrayList<String> continentsCountryList = listmap.get(cont.getContinentName());

		int count = 0;
		for (String conCountryName : continentsCountryList) {
			for (String playerCountryName : player.getOwnedCountriesList()) {
				if (playerCountryName.equals(conCountryName)) {
					count++;
				}
			}
		}
		if (count == continentsCountryList.size()) {
			continentReward = Integer.parseInt(cont.getcontinentControlValue());
		}
		return continentReward;
	}

	/**
	 * This method will calculate rewarded armies to be obtained after exchanging
	 * cards
	 * 
	 * @param num1            1st number selected from card exchange view
	 * @param num2            2nd number selected from card exchange view
	 * @param num3            3rd number selected from card exchange view
	 * @param currentCardList is the list of cards in players possession
	 * @param player          player object containing all player information
	 * @return the method will return number of reward army the player will get
	 *         after exchanging the cards
	 * @author sakib
	 */
	public int exchangeCard(int num1, int num2, int num3, ArrayList<String> currentCardList, Player player) {
		String card1 = currentCardList.get(num1 - 1);
		String card2 = currentCardList.get(num2 - 1);
		String card3 = currentCardList.get(num3 - 1);
		HashSet<String> cardset = new HashSet<String>();
		cardset.addAll(Arrays.asList(card1, card2, card3));
		if ((card1.equals(card2) && card2.equals(card3)) || (cardset.size() == 3)) {
			player.setCardBonusArmy(player.getCardBonusArmy() + 5);
			return player.getCardBonusArmy();
		} else {
			return 0;
		}
	}

	/**
	 * @param countryReward   it contains the country reward
	 * @param continentReward it contains the continent reward
	 * @param cardReward      it contains the card reward
	 * @return returns the total reinforce army for country reward, continent reward
	 *         and card reward
	 * @author sakib
	 */
	public int calculateReinforceArmy(int countryReward, int continentReward, int cardReward) {
		int total = 0;
		total = countryReward + continentReward + cardReward;
		if (total >= 3) {
			return total;
		} else {
			return 3; // minimum 3 army should be given
		}
	}

	/**
	 * this method will be called when the place reinforcement army command will be
	 * given
	 * 
	 * @param countryName        where the army will be placed
	 * @param numOfArmiesToPlace the armies to be placed will be mentioned in
	 *                           command line
	 * @param countries          countries hashmap containing countries objects
	 * @param playerMap          player hashmap containing all the player data
	 * @param continents         continents hashmap containing continent objects
	 * @param mp                 is the player object model for the turn
	 * @return success or failure messages for each situation
	 * @author sakib
	 */
	public String placeReinforceArmy(String countryName, int numOfArmiesToPlace, HashMap<Integer, Countries> countries,
			HashMap<String, Player> playerMap, HashMap<Integer, Continents> continents, Player mp) {

		String player = cc.findPlayerNameFromCountry(countries, countryName);

		if (player.equals("")) {
			return "Player Doesn't own the Country";
		}

		// Check Player owns the country
		Player p = playerMap.get(player);
		int existingArmy = p.getOwnedCountriesArmiesList().get(countryName);
		int availableReinforcedArmies = mp.getAvailableReinforceArmies();
		if (numOfArmiesToPlace <= availableReinforcedArmies) {
			existingArmy += numOfArmiesToPlace;
			availableReinforcedArmies -= numOfArmiesToPlace;
			mp.setAvailableReinforceArmies(availableReinforcedArmies);
			p.getOwnedCountriesArmiesList().put(countryName, existingArmy);

			return "Reinforcement armies placed successfully";
		} else
			return "Reinforcement cannot be performed; the given reinforcement armies are greater than the available reinforcemet armies";

	}

	/**
	 * This method removes the cards position after calculating the card reward
	 * 
	 * @param pos1            this variable contains the position of card
	 * @param pos2            this variable contains the position of card
	 * @param pos3            this variable contains the position of card
	 * @param totalPlayerData this variable contains all the player data
	 * @param playerTurnData  this variable contains the current turn player data
	 * @author Sai Krishna
	 */
	public void removeCardPositions(int pos1, int pos2, int pos3, PlayersList totalPlayerData, Player playerTurnData) {

		totalPlayerData.getListOfPlayers().get(playerTurnData.getCurrentPlayerTurn()).getCurrentCardList()
				.remove(pos1 - 1);
		totalPlayerData.getListOfPlayers().get(playerTurnData.getCurrentPlayerTurn()).getCurrentCardList()
				.remove(pos2 - 1);
		totalPlayerData.getListOfPlayers().get(playerTurnData.getCurrentPlayerTurn()).getCurrentCardList()
				.remove(pos3 - 1);
	}

	/**
	 * This method returns the countries numbers owned by the player
	 * 
	 * @param countries      this variable contains the list of continents
	 * @param ownedCountries this variable contains the countries owned by the
	 *                       player
	 * @author Sai Krishna
	 */
	public void ownedCountriesNumList(HashMap<Integer, Countries> countries, ArrayList<String> ownedCountries) {
		for (String s : ownedCountries) {
			ownedCountriesNumberList.add(cc.getCountryNumberByName(countries, s));
		}
		marked = new boolean[countries.size()];
	}

	/**
	 * This method checks if the player has an path of its own
	 * 
	 * @param boundaries  this variable contains the list of boundries
	 * @param fromCountry this variable contains the source country name
	 * @param toCountry   this variables contains the destination country name
	 * @author Sai Krishna
	 */
	public void checkOwnPath(HashMap<Integer, ArrayList<Integer>> boundaries, int fromCountry, int toCountry) {
		ArrayList<Integer> neighbors = boundaries.get(fromCountry);
		for (int i = 0; i < neighbors.size(); i++) {
			if (ownedCountriesNumberList.contains(neighbors.get(i))) {
				if (neighbors.get(i) == toCountry) {
					ownedPath = true;
					break;
				} else if (!marked[neighbors.get(i) - 1]) {
					marked[neighbors.get(i) - 1] = true;
					checkOwnPath(boundaries, neighbors.get(i), toCountry);
				}
			}
		}
	}

	/**
	 * performs the fortify action uses getCountryNumberByName and
	 * getAdjacencyMatrix methods
	 * 
	 * @param player      player model object containing all player information
	 * @param fromCountry the country from where army will be moved
	 * @param toCountry   the country to where army will be moved
	 * @param armyToPlace No. armies to place
	 * @param countries   hashmap parsed from map for country list
	 * @param boundaries  hashmap for country list for each continent
	 * @return appropriate messages for view
	 * @author sakib
	 */
	public String fortify(HashMap<String, Player> player, String fromCountry, String toCountry, int armyToPlace,
			HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundaries) {

		String pName = cc.findPlayerNameFromCountry(countries, fromCountry);
		Player pOb = player.get(pName);

		if (pOb.getOwnedCountriesList().contains(fromCountry)) {
			if (pOb.getOwnedCountriesList().contains(toCountry)) {
				int fromCountryNum = cc.getCountryNumberByName(countries, fromCountry);
				int toCountryNum = cc.getCountryNumberByName(countries, toCountry);
				ownedCountriesNumList(countries, pOb.getOwnedCountriesList());
				marked[fromCountryNum - 1] = true;
				checkOwnPath(boundaries, fromCountryNum, toCountryNum);

				if (ownedPath) {
					int existingArmy = pOb.getOwnedCountriesArmiesList().get(fromCountry);
					if (armyToPlace < existingArmy) {
						int destinationArmy = pOb.getOwnedCountriesArmiesList().get(toCountry);
						existingArmy -= armyToPlace;
						destinationArmy += armyToPlace;
						pOb.getOwnedCountriesArmiesList().put(fromCountry, existingArmy);
						pOb.getOwnedCountriesArmiesList().put(toCountry, destinationArmy);
						return "Foritified successfully";
					} else
						return "Player should leave at least one army for the country";

				} else
					return "Player does not own the path";
			} else
				return "Targeted country is not owned by player";
		} else
			return "Player doesn't own this country";
	}

	/**
	 * This method is used for adding the game cards to attacker
	 * 
	 * @param attackerPlayerData this variable contains the attacker player data
	 * @param player             this variable contains the current turn of the
	 *                           player data
	 * @param gm                 this variable contains the game map data
	 * @author Sai Krishna
	 */
	public void addGameCardsToAttacker(Player attackerPlayerData, Player player, GameMap gm) {
		for (String countryName : player.getConqueredCountries()) {
			if (gm.getTotalCardsList().size() > 0) {
				Collections.shuffle(gm.getTotalCardsList());
				attackerPlayerData.getCurrentCardList().add(countryName + " - " + gm.getTotalCardsList().get(0));
				gm.getTotalCardsList().remove(0);
			} else {
				break;
			}
		}

	}

	/**
	 * Method is used to validate whether the defender country is the adjacent
	 * neighboring country or not
	 * 
	 * @param attackerCountry is the name of the attacker country
	 * @param defenderCountry is the name of the defender country
	 * @param countryList     is the total country list
	 * @param boundries       contains information about neighboring countries
	 * @return true if defender country is the adjacent country
	 * @author garimadawar
	 */
	public boolean validateDefenderCountry(String attackerCountry, String defenderCountry,
			HashMap<Integer, Countries> countryList, HashMap<Integer, ArrayList<Integer>> boundries,
			ArrayList<String> attackerOwnedCountries) {

		int attackerCountryNum = cc.getCountryNumberByName(countryList, attackerCountry);

		int defenderCountryNum = cc.getCountryNumberByName(countryList, defenderCountry);

		adjancyList = boundries.get(attackerCountryNum);

		if (adjancyList.contains(defenderCountryNum)) {
			if (!attackerOwnedCountries.contains(defenderCountry)) {
				return true;
			}
		}

		return false;
	}

	/**
	 * this method is used to validate whether dice rolled is valid or not as per
	 * attacker army
	 * 
	 * @param attackerCountry    is the name of the attacker country
	 * @param numberOnDice       is the number of dice rolled for attack
	 * @param attackerPlayerData it contains the data of the attacker
	 * @return true if dice roll is valid else false
	 * @author Ashish Chaudhary
	 */
	public boolean validateNumDice(String attackerCountry, Integer numberOnDice, Player attackerPlayerData) {

		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		numOfAttackerArmy = attackerArmiesMap.get(attackerCountry);

		if (numberOnDice < numOfAttackerArmy) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * Method performs attack by attacker
	 * 
	 * @param attackerCountry   is the name of the attacker country
	 * @param defenderCountryis the name of the defender country
	 * @param numberOnDice      is the number of dice used for attacking
	 * @param player            contains the current player's turn data
	 * @return ready after placing army
	 * @author ashish
	 */
	public String attackPhase(String attackerCountry, String defenderCountry, Integer numberOnDice, Player player) {

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);
		attackerDiceNumbersList = new ArrayList<Integer>();
		while (numberOnDice > 0) {
			diceRolledResult = randomNumberGenerator();
			attackerDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(attackerDiceNumbersList, Collections.reverseOrder());
		player.setAttackerDice(attackerDiceNumbersList);
		player.setDiceRolled(attackerDiceNumbersList.size());

		return "Attacker Ready and placed his army on field";
	}

	/**
	 * method performs -all out attack phase
	 * 
	 * @param attackerCountry    is the name of the attacker country
	 * @param defenderCountry    is the name of the defender country
	 * @param attackerPlayerData it contains the data of the attacker
	 * @param countryList        contains list of countries
	 * @param player             contains the current Player's turn
	 * @param defenderPlayerData it contains defender player data
	 * @return won if attacker wins else army having less then 1 for attacker
	 *         message
	 * @author ashish
	 */
	public String allOutAttackedPhase(String attackerCountry, String defenderCountry, Player attackerPlayerData,
			HashMap<Integer, Countries> countryList, Player player, Player defenderPlayerData) {

		player.setAttackerCountry(attackerCountry);
		player.setDefenderCountry(defenderCountry);

		String defenderPlayerName = cc.findPlayerNameFromCountry(countryList, defenderCountry);
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		String attackerPlayerName = cc.findPlayerNameFromCountry(countryList, attackerCountry);
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		int numOfAttackerArmy = attackerArmiesMap.get(attackerCountry);

		int numOfDiceCanRoll = 0;
		if (numOfAttackerArmy > 1) {
			while (numOfAttackerArmy > 1) {
				if (numOfAttackerArmy == 2) {
					numOfDiceCanRoll = 1;
				} else if (numOfAttackerArmy == 3) {
					numOfDiceCanRoll = 2;
				} else {
					numOfDiceCanRoll = 3;
				}

				int diceToRoll = numOfDiceCanRoll;
				attackerDiceNumbersList = new ArrayList<Integer>();

				while (diceToRoll != 0) {
					diceRolledResult = randomNumberGenerator();
					attackerDiceNumbersList.add(diceRolledResult);
					diceToRoll--;
				}
				Collections.sort(attackerDiceNumbersList, Collections.reverseOrder());
				player.setAttackerDice(attackerDiceNumbersList);

				numOfAttackerArmy = allOutDefend(defenderPlayerName, player.getDefenderCountry(), numOfAttackerArmy,
						countryList, defenderPlayerData, player, attackerPlayerData, attackerPlayerName,
						player.getAttackerCountry());

				if (defenderArmiesMap.get(player.getDefenderCountry()) == 0) {
					player.setDiceRolled(numOfDiceCanRoll); // numOfDiceCanRoll
					player.getConqueredCountries().add(player.getDefenderCountry());
					player.setAllOutPerformed(true);
					return "Attacker Won the " + player.getDefenderCountry() + " country";
				} else if (numOfAttackerArmy == 1) {
					player.setAllOutPerformed(true);
					return "Attacker cannot conquer defender country as he is left with 1 army in -allout mode";
				}
			}
			return "";
		} else {
			return "Attacker cannot perform attack as he is left with 1 army";
		}
	}

	/**
	 * Method is used to defend the -all out command from attacker
	 * 
	 * @param defenderPlayerName  is the name of the defender
	 * @param defenderCountry     is the name of the defender country name
	 * @param attackerArmy        is the number of army left with the attacker
	 * @param countryList         is the list of the countries
	 * @param defenderPlayerData  is the data of the defender
	 * @param player              it contains the current player's turn data
	 * @param attackerPlayerData  it contains the attacker player data
	 * @param attackerPlayerName  is the name of the attacker
	 * @param attackerCountryname is the name of attacking country
	 * @return number of attacker army left
	 * @author garimadawar
	 */
	public int allOutDefend(String defenderPlayerName, String defenderCountry, Integer attackerArmy,
			HashMap<Integer, Countries> countryList, Player defenderPlayerData, Player player,
			Player attackerPlayerData, String attackerPlayerName, String attackerCountryname) {

		defenderDiceNumbersList = new ArrayList<Integer>();

		numOfDefenderArmy = defenderArmiesMap.get(defenderCountry);

		int maxDefenderDiceToRoll = 0;

		if (player.getAttackerDice().size() == 1) {
			maxDefenderDiceToRoll = 1;
		} else {
			if (numOfDefenderArmy > 1) {
				maxDefenderDiceToRoll = 2;
			} else {
				maxDefenderDiceToRoll = 1;
			}
		}

		while (maxDefenderDiceToRoll != 0) {

			diceRolledResult = randomNumberGenerator();
			defenderDiceNumbersList.add(diceRolledResult); // check that the list is getting cleared in attack phase
			maxDefenderDiceToRoll--;

		}
		Collections.sort(defenderDiceNumbersList, Collections.reverseOrder());
		player.setDefenderDice(defenderDiceNumbersList);

		attackerArmyLeft = comparingDiceToWin(player, defenderArmiesMap, attackerArmiesMap, attackerPlayerData,
				defenderPlayerData, attackerCountryname, defenderCountry);

		return attackerArmyLeft;

	}

	/**
	 * method is used to compare the dice roll of both attacker and defender
	 * 
	 * @param p                   contains the current player's turn data
	 * @param defenderArmiesMap   it contains the defender's list of countries and
	 *                            its armies
	 * @param attackerArmiesMap   it contains the attacker's list of countries and
	 *                            its armies
	 * @param attackerPlayerData  it contains the attacker's player data
	 * @param defenderPlayerData  it contains the defender's player data
	 * @param attackerCountryName is the name of the attacker country name
	 * @param defenderCountryName is the name of the defender country name
	 * @return number of attacker army left after dice rolls comparison
	 * @author Ashish Chaudhary
	 */
	public int comparingDiceToWin(Player p, HashMap<String, Integer> defenderArmiesMap,
			HashMap<String, Integer> attackerArmiesMap, Player attackerPlayerData, Player defenderPlayerData,
			String attackerCountryName, String defenderCountryName) {

		int defenderDiceRolled = p.getDefenderDice().size();
		while (defenderDiceRolled != 0) {
			int attackerNum = p.getAttackerDice().get(0);
			int defenderNum = p.getDefenderDice().get(0);

			numOfDefenderArmy = defenderArmiesMap.get(defenderCountryName);
			numOfAttackerArmy = attackerArmiesMap.get(attackerCountryName);
			if (defenderNum == attackerNum || defenderNum > attackerNum) {

				int attackerArmyLeft = attackerPlayerData.getOwnedCountriesArmiesList().get(attackerCountryName);
				attackerArmyLeft--;
				attackerPlayerData.getOwnedCountriesArmiesList().put(attackerCountryName, attackerArmyLeft);

			} else {
				int defenderArmyLeft = defenderPlayerData.getOwnedCountriesArmiesList().get(defenderCountryName);
				defenderArmyLeft--;
				defenderPlayerData.getOwnedCountriesArmiesList().put(defenderCountryName, defenderArmyLeft);
			}

			p.getAttackerDice().remove(0);
			p.getDefenderDice().remove(0);
			defenderDiceRolled--;
		}
		return attackerPlayerData.getOwnedCountriesArmiesList().get(p.getAttackerCountry());
	}

	/**
	 * method is used to generate a random number between 0 to 6
	 * 
	 * @return the random number generated
	 * @author Ashish Chaudhary
	 */
	public int randomNumberGenerator() {
		double random = Math.random();
		random = random * 6 + 1;
		int diceRoll = (int) random;
		return diceRoll;

	}

	/**
	 * Method is used to validate number of armies moving to conquered countries
	 * 
	 * @param diceRolled is the number of dice rolled to conquer country
	 * @param moveArmy   is the number of armies moving to conquered country
	 * @return true if number of army to move is greater of equal to dice rolled to
	 *         defeat the country completly
	 * @author Gagan Jaswal
	 */
	public boolean validateNumOfArmyMoves(Integer diceRolled, Integer moveArmy) {

		int diceRolledForWin = diceRolled;
		int armyToMove = moveArmy;

		if (armyToMove > diceRolledForWin || armyToMove == diceRolledForWin) {
			return true;
		}

		return false;
	}

	/**
	 * Method is used to move the armies to the conquered country
	 * 
	 * @param armyToMove is the number of army to move to conquered country
	 * @param playerData contain's the data of all player
	 * @param player     contains the
	 * @param gm         contains the GameMap data
	 * @return army moved to the Conquered country
	 * @author Gagan jaswal
	 */
	public String movingArmyToConqueredCountry(Integer armyToMove, HashMap<String, Player> playerData, Player player,
			GameMap gm) {

		int movingArmy = armyToMove;

		moveArmy(movingArmy, playerData, player);

		assigningCountryToAttacker(playerData, player, gm);

		return "Army Moved to Conquered Country";
	}

	/**
	 * method is used to move armies to conquered country
	 * 
	 * @param movingArmy is the number of army to move
	 * @param playerData contains the player's data
	 * @param player     it contains the current player's data
	 * @return true when all armies are moved to conquered country
	 * @author Gagan jaswal
	 */
	public boolean moveArmy(int movingArmy, HashMap<String, Player> playerData, Player player) {

		boolean flag = false;
		Player attackerData = playerData.get(player.getAttackerName());
		countryArmyList = attackerData.getOwnedCountriesArmiesList();
		int attackerArmy = countryArmyList.get(player.getAttackerCountry());

		int remainingArmy = attackerArmy - movingArmy;

		attackerData.getOwnedCountriesArmiesList().put(player.getAttackerCountry(), remainingArmy);

		Player defenderData = playerData.get(player.getDefenderName());
		countryArmyList = defenderData.getOwnedCountriesArmiesList();
		int defenderArmy = countryArmyList.get(player.getDefenderCountry());
		defenderArmy = movingArmy;
		flag = true;

		defenderData.getOwnedCountriesArmiesList().put(player.getDefenderCountry(), defenderArmy);

		return flag;
	}

	/**
	 * Method is used to assign country to the attacker countries list
	 * 
	 * @param playerData contains the player data
	 * @param player     contains the current player's turn data
	 * @param gm         contains the full game map data
	 * @author Gagan Jaswal
	 */
	public void assigningCountryToAttacker(HashMap<String, Player> playerData, Player player, GameMap gm) {

		Player defenderData = playerData.get(player.getDefenderName());
		countryList = defenderData.getOwnedCountriesList();
		int defenderCountryIndex = countryList.indexOf(player.getDefenderCountry());
		countryList.remove(defenderCountryIndex);
		defenderData.setOwnedCountriesList(countryList);
		int defenderArmy = defenderData.getOwnedCountriesArmiesList().get(player.getDefenderCountry());
		defenderData.getOwnedCountriesArmiesList().remove(player.getDefenderCountry());

		if (defenderData.getOwnedCountriesArmiesList().size() <= 0) {
			playerData.remove(player.getDefenderName());
			gm.getPlayersSetup().remove(player.getDefenderName());
			gm.getPlayersWithStrategies().remove(player.getDefenderName());
		}

		Player attackerData = playerData.get(player.getAttackerName());
		countryList = attackerData.getOwnedCountriesList();
		countryList.add(player.getDefenderCountry());
		attackerData.getOwnedCountriesArmiesList().put(player.getDefenderCountry(), defenderArmy);
		attackerData.setOwnedCountriesList(countryList);

		int defenderCountryNum = cc.getCountryNumberByName(gm.getCountries(), player.getDefenderCountry());
		Countries c = gm.getCountries().get(defenderCountryNum);
		c.setOwnerName(player.getAttackerName());
	}

	/**
	 * This method is used to validate the defender dice.
	 * 
	 * @author garimadawar
	 * @param defenderCountry    contains the name of defender country
	 * @param numberOnDice       contains the number on dice
	 * @param defenderPlayerData contains the information about player
	 * @return true if defender dice rolled is less then or equal to number of army
	 *         the defender have
	 * 
	 */
	public boolean validateDefenderNumdice(String defenderCountry, Integer numberOnDice, Player defenderPlayerData) {

		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();
		int numArmy = defenderArmiesMap.get(defenderCountry);
		if (numberOnDice <= numArmy) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used to defend phase dice roll.
	 * 
	 * @author garimadawar
	 * @param defenderCountry contains the name of defender country
	 * @param numberOnDice    contains the number on dice
	 * @param p               contains the information about player
	 * @return true after storing all dice number of each dice rolled
	 */
	public boolean defendPhaseDiceRoll(String defenderCountry, Integer numberOnDice, Player p) {

		defenderDiceNumbersList = new ArrayList<Integer>();
		while (numberOnDice != 0) {
			diceRolledResult = randomNumberGenerator();
			defenderDiceNumbersList.add(diceRolledResult);
			numberOnDice--;
		}
		Collections.sort(defenderDiceNumbersList, Collections.reverseOrder());
		p.setDefenderDice(defenderDiceNumbersList);

		return true;
	}

	/**
	 * This method is used to defend the base
	 * 
	 * @author garimadawar
	 * @param p  contains the information about player
	 * @param pl contains the list of player
	 * @return attacker won or number of armies left for both attacker and defender
	 */
	public String defendingTheBase(Player p, PlayersList pl) {

		Player defenderPlayerData = pl.getListOfPlayers().get(p.getDefenderName());
		defenderArmiesMap = defenderPlayerData.getOwnedCountriesArmiesList();

		Player attackerPlayerData = pl.getListOfPlayers().get(p.getAttackerName());
		attackerArmiesMap = attackerPlayerData.getOwnedCountriesArmiesList();

		String attackerCountryName = p.getAttackerCountry();
		String defenderCountryName = p.getDefenderCountry();

		int attackerArmyLeft = comparingDiceToWin(p, defenderArmiesMap, attackerArmiesMap, attackerPlayerData,
				defenderPlayerData, attackerCountryName, defenderCountryName);

		if (defenderArmiesMap.get(p.getDefenderCountry()) == 0) {

			p.getConqueredCountries().add(p.getDefenderCountry());

			return "Attacker Won the " + p.getDefenderCountry() + " country";
		} else {
			return " Attacked Performed " + "; Armies left with Attacker = "
					+ attackerArmiesMap.get(p.getAttackerCountry()) + " and Armies Left with Defender =  "
					+ defenderArmiesMap.get(p.getDefenderCountry());
		}
	}

	/**
	 * method is used to check whether the player won the game
	 * 
	 * @param attackerPlayerData contains the attacker's data
	 * @return true if player conquer all countries else false
	 * @author Ashish Chaudhary
	 */
	public boolean checkGameEnd(PlayersList attackerPlayerData) {
		if (attackerPlayerData.getListOfPlayers().size() == 1) {
			return true;
		} else {
			return false;
		}

	}

	/**
	 * method is used to check whether attack move can be performed or not
	 *
	 * @param armyMove               is the number of army to move
	 * @param diceRolled             is the last dice played to defeat country
	 * @param conqueredCountriesList is the list of all conquered countries list
	 * @param player                 contains the current players's turn data
	 * @return true if army can be moved to conquered country
	 */
	public boolean isvalidAttackMove(int armyMove, int diceRolled, ArrayList<String> conqueredCountriesList,
			Player player, int attackerArmy) {

		if (armyMove >= diceRolled && conqueredCountriesList.contains(player.getDefenderCountry()) && diceRolled>0
				&& armyMove < attackerArmy) {
			return true;
		} else
			return false;

	}

}
