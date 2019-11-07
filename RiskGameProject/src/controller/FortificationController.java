package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import model.Countries;
import model.GameMap;
import model.Player;

/**
 * Following class will perform the activity in fortification phase
 * 
 * 
 */
public class FortificationController {

	CommonController cc;
	ArrayList<Integer> ownedCountriesNumberList;
	boolean ownedPath;
	boolean marked[];
	
	/**
	 * @Default constructor
	 * This method initiates the variables
	 * @author Sai Krishna
	 */
	public FortificationController() {
		cc=new CommonController();
		ownedCountriesNumberList=new ArrayList<Integer>();
		ownedPath=false;
	}
	
	/**
	 * This method returns the countries numbers owned by the player		
	 * @param countries this variable contains the list of continents
	 * @param ownedCountries this variable contains the countries owned by the player
	 * @author Sai Krishna
	 */
	public void ownedCountriesNumList(HashMap<Integer, Countries> countries,ArrayList<String> ownedCountries) {
		for(String s:ownedCountries) {
			ownedCountriesNumberList.add(cc.getCountryNumberByName(countries, s));
		}
		marked=new boolean[countries.size()];
	}
	
	/**
	 * This method checks if the player has an path of its own
	 * @param boundaries this variable contains the list of boundries
	 * @param fromCountry this variable contains the source country name
	 * @param toCountry this variables contains the destination country name
	 * @author Sai Krishna
	 */
	public void checkOwnPath(HashMap<Integer, ArrayList<Integer>> boundaries, int fromCountry, int toCountry) {
		ArrayList<Integer> neighbors=boundaries.get(fromCountry);
		for(int i=0;i<neighbors.size();i++) {
			if(ownedCountriesNumberList.contains(neighbors.get(i))) {				
				if(neighbors.get(i)==toCountry) {
					ownedPath=true;
					break;
				} else if(!marked[neighbors.get(i)-1]){
					marked[neighbors.get(i)-1]=true;
					checkOwnPath(boundaries,neighbors.get(i),toCountry);
				}
			}
		}		
	}
	
	
	/**
	 * performs the fortify action uses getCountryNumberByName and
	 * getAdjacencyMatrix methods
	 * 
	 * @param player player model object containing all player information
	 * @param fromCountry the country from where army will be moved
	 * @param toCountry the country to where army will be moved
	 * @param armyToPlace No. armies to place 
	 * @param countries hashmap parsed from map for country list
	 * @param boundaries hashmap for country list for each continent
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
				marked[fromCountryNum-1]=true;
				checkOwnPath(boundaries, fromCountryNum, toCountryNum);

				if (ownedPath) {
//					ArrayList<Integer> existingArmiesList = pOb.getOwnedArmiesList();
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
	 * @param attackerPlayerData this variable contains the attacker player data
	 * @param player this variable contains the current turn of player data
	 * @param gm this variable contains the game map data
	 * @author Sai Krishna
	 */
	public void addGameCardsToAttacker(Player attackerPlayerData, Player player,GameMap gm) {
		for(String countryName:player.getConqueredCountries()) {
			Collections.shuffle(gm.getTotalCardsList());
			attackerPlayerData.getCurrentCardList().add(countryName+"-"+gm.getTotalCardsList().get(0));
			gm.getTotalCardsList().remove(0);
		}
		
	}
	
}
