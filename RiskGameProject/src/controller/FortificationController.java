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
	
/**
 * creates an Adjacency Matrix for the boundaries hashmap
 * @param boundaries
 * @return
 */
	public boolean[][] getAdjacencyMatrix(HashMap<Integer, ArrayList<Integer>> boundaries) {
		boolean[][] matrix = new boolean[boundaries.size()][boundaries.size()];
		ArrayList<Integer> list;
		int neighborIndex;

		for (int k = 0; k < matrix.length; k++) {
			list = boundaries.get(k);
			for (int i = 0; i < list.size(); i++) {
				neighborIndex = list.get(i) - 1;
				matrix[k][neighborIndex] = true;
			}
		}
		return matrix;
	}
	/**
	 * To find the key of country hashmap 
	 * @param countries
	 * @param countryName
	 * @return
	 */

	public int getCountryNumberByName(HashMap<Integer, Countries> countries, String countryName) {
		for (int i : countries.keySet()) {
			Countries cou = countries.get(i);
			if (cou.getCountryName().equals(countryName)) {
				return i;
			}
		}
		return 0;
	}
	
	/**
	 * performs the fortify action
	 * uses getCountryNumberByName and getAdjacencyMatrix methods
	 * @param player
	 * @param fromCountry
	 * @param toCountry
	 * @param armyToPlace
	 * @param countries
	 * @param boundaries
	 * @return appropiate messages for view
	 */

	public String fortify(HashMap<String, Player> player, String fromCountry, String toCountry, int armyToPlace,
			HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundaries) {
		ReinforcementController rc = new ReinforcementController();
		String pName = rc.findPlayerNameFromCountry(countries, fromCountry);
		Player pOb = player.get(pName);

		if (pOb.getOwnedCountriesList().contains(fromCountry)) {
			if (pOb.getOwnedCountriesList().contains(toCountry)) {
				int fromCIdx = getCountryNumberByName(countries, fromCountry) - 1;
				int toCIdx = getCountryNumberByName(countries, toCountry) - 1;
				boolean[][] matrix = getAdjacencyMatrix(boundaries);

				if (matrix[fromCIdx][toCIdx]) {
					ArrayList<Integer> existingArmiesList = pOb.getOwnedArmiesList();
					int existingArmy = existingArmiesList.get(fromCIdx);
					if (armyToPlace < existingArmy) {
						int destinationArmy = existingArmiesList.get(toCIdx);
						existingArmy -= armyToPlace;
						destinationArmy += armyToPlace;
						existingArmiesList.set(fromCIdx, existingArmy);
						existingArmiesList.set(toCIdx, destinationArmy);
						pOb.setOwnedArmiesList(existingArmiesList);
						return "Foritified successfully";
					}else return "All armies cannot be fortified";

				}

			} else
				return "Trageted country is not owned by player";
		} else
			return "Player doesn't own this country";

		return null;
	}
}
