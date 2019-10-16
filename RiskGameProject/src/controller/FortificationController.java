package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Countries;
import model.Player;
import controller.ReinforcementController;

/**
 * FOllowing class will perform the activity in fortification phase
 * 
 * @author sakib
 * 
 */
public class FortificationController {
	
//	public HashMap<String, Integer> countryNameNumMap(HashMap<Integer, Countries> countries){
//		HashMap <String, Integer> map = new HashMap <String, Integer>();
//		for(int i : countries.keySet()) {
//			countries.get(key)
//			
//		}
//	}
//	public boolean[][] getAdjacencyMatrix(HashMap<Integer, ArrayList<Integer>> boundaries) {
//		boolean[][] matrix = new boolean[boundaries.size()][boundaries.size()];
//		ArrayList<Integer> list;
//		for (int k = 0; k < boundaries.size(); k++) {
//			list = boundaries.get(k);
//			for (int i : boundaries.keySet()) {
//				
//				if (true) {
//
//				}
//				matrix[k][i] = true;
//			}
//		}
//		return matrix;
//	}

	public int getCountryIndexByName(HashMap<Integer, Countries> countries, String countryName) {
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				return i;
			}
		}
		return 0;
	}

	public String fortify(HashMap<String, Player> player, String fromCountry, String toCountry, int armyToPlace,
			HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundaries) {
		ReinforcementController rc = new ReinforcementController();
		String pName = rc.findPlayerNameFromCountry(countries, fromCountry);
		Player pOb = player.get(pName);

		if (pOb.getOwnedCountriesList().contains(fromCountry)) {
			if (pOb.getOwnedCountriesList().contains(toCountry)) {
				int fromCIdx = getCountryIndexByName(countries, fromCountry);
				int toCIdx = getCountryIndexByName(countries, toCountry);

				if (boundaries.get(fromCIdx).contains(toCIdx)) {
					ArrayList<Integer> existingArmiesList = pOb.getOwnedArmiesList();
					int existingArmy = existingArmiesList.get(fromCIdx);
					if (armyToPlace < existingArmy) {
						int destinationArmy = existingArmiesList.get(toCIdx);
						existingArmy -= armyToPlace;
						destinationArmy += armyToPlace;
						existingArmiesList.add(fromCIdx, existingArmy);
						existingArmiesList.add(toCIdx, destinationArmy);
						pOb.setOwnedArmiesList(existingArmiesList);
					}

				}

			} else
				return "Trageted country is not owned by player";
		} else
			return "Player doesn't own this country";

		return null;
	}
}
