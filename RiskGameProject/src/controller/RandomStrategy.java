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
		// TODO Auto-generated method stub
		reinforce(gm, pl, player);
	//	attack(gm, pl, player);
	//	fortify(gm, pl, player);

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

		/*
		 * while (player.getAvailableReinforceArmies() != 0) {
		 * 
		 * int randomNumber = randomArmyToReinforceGenerator(player);
		 * 
		 * pc.placeReinforceArmy(randomCountry, randomNumber, gm.getCountries(),
		 * pl.getListOfPlayers(), gm.getContinents(), player);
		 * 
		 * // check return of placereinforcearmy method
		 * 
		 * Collections.shuffle(countriesOwned); randomCountry = countriesOwned.get(0);
		 * 
		 * player.setAvailableReinforceArmies(player.getAvailableReinforceArmies() -
		 * randomNumber);
		 * 
		 * }
		 */
		// check if it is a tournament

		player.setCardReward(0);
		player.setGameState("ATTACK");

	}

	
	
}
