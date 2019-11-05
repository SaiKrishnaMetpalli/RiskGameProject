package controller;

import java.util.ArrayList;
import java.util.HashMap;

import model.Countries;
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
	 * @Default Constructor
	 * Initiates object for CommonController 
	 */
	public FortificationController() {
		cc=new CommonController();
		ownedCountriesNumberList=new ArrayList<Integer>();
		ownedPath=false;
	}
			
	public void ownedCountriesNumList(HashMap<Integer, Countries> countries,ArrayList<String> ownedCountries) {
		for(String s:ownedCountries) {
			ownedCountriesNumberList.add(cc.getCountryNumberByName(countries, s));
		}
		marked=new boolean[countries.size()];
	}
	
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
	 * @param player
	 * @param fromCountry
	 * @param toCountry
	 * @param armyToPlace
	 * @param countries
	 * @param boundaries
	 * @return appropriate messages for view
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
}
