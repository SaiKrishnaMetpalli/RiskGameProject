package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.GameMap;
import model.Player;
import model.PlayersList;

public class RandomStrategy implements Strategy {

	CommonController cc;
	PlayerController pc;

	ArrayList<Integer> attackerCountryList;
	ArrayList<Integer> neighbouringList;
	ArrayList<String> countriesOwned;

	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {

		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return "Success";
	}

	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		player.setCardReward(cc.exchangeCardForStrategy(pl, player));

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		countriesOwned = playerData.getOwnedCountriesList();

		Collections.shuffle(countriesOwned);
		String randomCountry = countriesOwned.get(0);
		int countryReward = pc.calculateOwnedCountryReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()));
		int continetReward = pc.calculateContinentReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()),
				gm.getContinents(), gm.getCountries(), randomCountry);

		player.setAvailableReinforceArmies(
				pc.calculateReinforceArmy(countryReward, continetReward, player.getCardReward()));

		
		  while (player.getAvailableReinforceArmies() != 0) {
		  
		  int randomNumber = randomArmyToReinforceGenerator(player);
		  
		  pc.placeReinforceArmy(randomCountry, randomNumber, gm.getCountries(),
		  pl.getListOfPlayers(), gm.getContinents(), player);
		  
		  // check return of placereinforcearmy method
		  
		  Collections.shuffle(countriesOwned); randomCountry = countriesOwned.get(0);
		  
		  player.setAvailableReinforceArmies(player.getAvailableReinforceArmies() -
		  randomNumber);
		  
		  }
		 
		// check if it is a tournament

		player.setCardReward(0);
		player.setGameState("ATTACK");

	}
	
	private void attack(GameMap gm, PlayersList pl, Player player) {

		neighbouringList = new ArrayList<Integer>();
		attackerCountryList = new ArrayList<Integer>();

		Collections.shuffle(countriesOwned);
		String randomCountry = countriesOwned.get(0);

		int attackerCountryNum = cc.getCountryNumberByName(gm.getCountries(), randomCountry);

		for (String country : pl.getListOfPlayers().get(player.getCurrentPlayerTurn()).getOwnedCountriesList()) {

			// player.setAttackerCountry(country);
			int countriesOwnedNumbers = cc.getCountryNumberByName(gm.getCountries(), country);
			attackerCountryList.add(countriesOwnedNumbers);
		}

		neighbouringList = gm.getBoundries().get(attackerCountryNum);

		for (int x = 0; x < neighbouringList.size(); x++) {
			if (attackerCountryList.contains(neighbouringList.get(x))) {
				neighbouringList.remove(x);
				continue;
			}
		}
		Collections.shuffle(neighbouringList);

		player.setAttackerCountry(randomCountry);
		player.setDefenderCountry(cc.getCountryNameByNum(gm.getCountries(), neighbouringList.get(0)));
		String defenderPlayer = cc.findPlayerNameFromCountry(gm.getCountries(), player.getDefenderCountry());

		Player playerData = pl.getListOfPlayers().get(defenderPlayer);

		int totalArmiesOwnedByDefender = playerData.getOwnedCountriesArmiesList().get(player.getDefenderCountry());

		int numberOfAttackToBeDone = randomNumberOfAttack(totalArmiesOwnedByDefender);

		while (numberOfAttackToBeDone != 0) {

			totalArmiesOwnedByDefender = playerData.getOwnedCountriesArmiesList().get(player.getDefenderCountry());

			int totalArmiesownedByAttacker = playerData.getOwnedCountriesArmiesList().get(player.getAttackerCountry());

			int attackerDiceToRoll = 0;

			if (totalArmiesownedByAttacker > 3) {
				attackerDiceToRoll = 3;
			} else if (totalArmiesownedByAttacker == 3) {
				attackerDiceToRoll = 2;
			} else if (totalArmiesownedByAttacker == 2) {
				attackerDiceToRoll = 1;
			} else
				break; // TODO: check at first loop ,if attacker army = 1 , then return

			player.setDiceRolled(attackerDiceToRoll);

			String attackSet = pc.attackPhase(player.getAttackerCountry(), player.getDefenderCountry(),
					attackerDiceToRoll, player);

			int defenderDiceToRoll = 0;

			if (totalArmiesOwnedByDefender > 1) {
				defenderDiceToRoll = 2; // TODO: check at first loop , if defender army = 1 , then return
			} else if (totalArmiesOwnedByDefender == 1)
				defenderDiceToRoll = 1;
			else
				break;

			boolean defendSet = pc.defendPhaseDiceRoll(player.getDefenderCountry(), defenderDiceToRoll, player);

			String warStarted = pc.defendingTheBase(player, pl);

			if (warStarted.contains("Won")) {

				boolean checkAllCountriesOwned = pc.checkGameEnd(pl);

				if (checkAllCountriesOwned) {
					System.out.println("\n" + player.getAttackerName() + " won the Risk Game");
					System.out.println("\nThe game is ended");
					System.exit(0); // TODO : avoid system.exit if tournament mode
				} else {
					String movingArmyResult = pc.movingArmyToConqueredCountry(player.getDiceRolled(),
							pl.getListOfPlayers(), player, gm);
				}
			}

			numberOfAttackToBeDone--;
		}

		player.setGameState("FORTIFY");
	}

	private void fortify(GameMap gm, PlayersList pl, Player player) {

		Collections.shuffle(countriesOwned);
		String randomFromCountry = countriesOwned.get(0);

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
		while(true)
		{
		int fromCountryArmy = playerData.getOwnedCountriesArmiesList().get(randomFromCountry);

		while (!(fromCountryArmy != 1)) {
			if (fromCountryArmy == 1) {

				Collections.shuffle(countriesOwned);
				randomFromCountry = countriesOwned.get(0);
				fromCountryArmy = playerData.getOwnedCountriesArmiesList().get(randomFromCountry);
			}
		}

		int armyToMove = fromCountryArmy - 1;

		String randomToCountry = countriesOwned.get(1);

		String fortifyResult = pc.fortify(pl.getListOfPlayers(), randomFromCountry, randomToCountry, armyToMove,
				gm.getCountries(), gm.getBoundries());
		if(fortifyResult.contains("Player does not own the path"))
		{
			continue;
		}
		else
			break;
		}
		
	//	clearPlayerObject();
	//	setPlayerTurn();
		player.setGameState("REINFORCE");
	}

	
	
	public int randomArmyToReinforceGenerator(Player player) {
		double random = Math.random();
		random = random * player.getAvailableReinforceArmies() + 1;
		int randomNumber = (int) random;
		return randomNumber;

	}

	private int randomNumberOfAttack(int totalArmiesOwnedByDefender) {

		double random = Math.random();
		random = random * totalArmiesOwnedByDefender + 1;
		int randomNumber = (int) random;
		return randomNumber;

	}

	
	
}
