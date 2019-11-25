package controller;

import java.util.Collections;
import java.util.HashMap;

import model.GameMap;
import model.Player;
import model.PlayersList;

public class BenevolentStrategy implements Strategy {
	PlayerController pc = new PlayerController();
	CommonController cc = new CommonController();
	
	@Override
	public String executeStrategy(GameMap gm, PlayersList pl, Player player) {
		reinforce(gm, pl, player);
		attack(gm, pl, player);
		fortify(gm, pl, player);

		return "Success";
	}
	
	private void reinforce(GameMap gm, PlayersList pl, Player player) {

		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int min = Collections.min(playerCountriesArmies.values());
		String weakCountryName="";
		for(String s : playerCountriesArmies.keySet()) {
			if(playerCountriesArmies.get(s).equals(min)) {
				weakCountryName=s;
				break;
			}
		}
		int countryReward = pc.calculateOwnedCountryReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()));
		int continentReward = pc.calculateContinentReward(pl.getListOfPlayers().get(player.getCurrentPlayerTurn()), gm.getContinents(), gm.getCountries(), weakCountryName);
		int cardReward = cc.exchangeCardForStrategy(pl, player);
		int numOfArmiesToPlace = pc.calculateReinforceArmy(countryReward, continentReward, cardReward);
		pc.calculateReinforceArmy(countryReward, continentReward, cardReward);
		//pc.placeReinforceArmy(weakCountryName, numOfArmiesToPlace, gm.getCountries(), pl.getListOfPlayers(), gm.getContinents(), player);
		int weakCountryArmy = playerData.getOwnedCountriesArmiesList().get(weakCountryName);
		playerData.getOwnedCountriesArmiesList().put(weakCountryName, weakCountryArmy+numOfArmiesToPlace);
	}

	private void attack(GameMap gm, PlayersList pl, Player player) {
		player.setGameState("FORTIFY");
	}

	private void fortify(GameMap gm, PlayersList pl, Player player) {
		Player playerData = pl.getListOfPlayers().get(player.getCurrentPlayerTurn());

		HashMap<String, Integer> playerCountriesArmies = playerData.getOwnedCountriesArmiesList();
		int min = Collections.min(playerCountriesArmies.values());
		String fromCountry="";
		for(String s : playerCountriesArmies.keySet()) {
			if(playerCountriesArmies.get(s).equals(min)) {
				fromCountry=s;
				break;
			} 
		}
		String toCountry="";
		int armyToPlace =0;
		
		for(String s : playerCountriesArmies.keySet()) {
			pc.checkOwnPath(gm.getBoundries(), cc.getCountryNumberByName(gm.getCountries(), fromCountry), cc.getCountryNumberByName(gm.getCountries(), s));
			if(pc.ownedPath){
				toCountry=s;
				armyToPlace =playerCountriesArmies.get(toCountry);
				break;
			} 
		}
		pc.fortify(pl.getListOfPlayers(), fromCountry, toCountry, armyToPlace, gm.getCountries(), gm.getBoundries());
		
	}

}
