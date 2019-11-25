package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.GameMap;
import model.Player;
import model.PlayersList;

public class RandomStrategy implements Strategy{

	CommonController cc;
	PlayerController pc;
	
	HashMap<String, Integer> ownedCountriesArmiesList;
	
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {
		// TODO Auto-generated method stub
		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return "Success";
	}

	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		player.setCardReward(cc.exchangeCardForStrategy(pl ,player));
		
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());
		
		ownedCountriesArmiesList = playerData.getOwnedCountriesArmiesList();
		
		ArrayList<String> countriesOwned = playerData.getOwnedCountriesList();
		
		Collections.shuffle(countriesOwned);
		String randomCountry = countriesOwned.get(0);
		int countryReward = pc.calculateOwnedCountryReward(
				pl.getListOfPlayers().get(player.getCurrentPlayerTurn()));
		int continetReward = pc.calculateContinentReward(
				pl.getListOfPlayers().get(player.getCurrentPlayerTurn()), gm.getContinents(),
				gm.getCountries(), randomCountry);
		
		player.setAvailableReinforceArmies(pc.calculateReinforceArmy(
				countryReward, continetReward, player.getCardReward()));
		
		
		
		int randomNumber = randomArmyToReinforceGenerator(player);
		
		
	}

	private void attack(GameMap gm, PlayersList pl, Player player) {
		// TODO Auto-generated method stub
		
	}

	private void fortify(GameMap gm, PlayersList pl, Player player) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	public int randomArmyToReinforceGenerator(Player player) {
		double random = Math.random();
		random = random * player.getAvailableReinforceArmies() + 1;
		int randomNumber = (int) random;
		return randomNumber;

	}
}
