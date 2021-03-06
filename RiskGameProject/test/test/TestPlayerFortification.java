package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.CommonController;
import controller.MapSelectionController;
import controller.PlayerController;
import controller.PlayerSelectionController;
import model.GameMap;
import model.Player;
import util.CONSTANTS;

/**
 * This class is used for testing fortification methods
 */
public class TestPlayerFortification {
	/**
	 * msc instantiates MapSelectionController class
	 * psc instantiates PlayerSelectionController class
	 * playerController instantiates PlayerController class
	 * cc instantiates CommonController Class
	 * Constant object having all constant values 
	 * gm instantiates GameMap class
	 * playerDetails represents hashamp with player name and object
	 * listOfPlayers is the Arraylist of players
	 * playersWithStrategies is a hashmap for player and their strategy
	 * playerOwnedCountries is Arraylist of countries owned by player
	 * fileName reusable string variable parsed file name
	 * fromCountry is string variable to to store formCountry for fortification
	 * toCountry is string variable to to store toCountry for fortification
	 * armytoPlace is the variable to store the integer of place able army during fortification
	 * pOb is the object of player class
	 */
	MapSelectionController msc;
	PlayerSelectionController psc;
	PlayerController playerController;
	CommonController cc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	ArrayList<String> listOfPlayers;
	HashMap<String,String> playersWithStrategies;
	ArrayList<String> playerOwnedCountries;
	String fileName;
	String fromCountry;
	String toCountry;
	int armyToPlace;
	Player pOb;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		msc = new MapSelectionController();
		psc = new PlayerSelectionController();
		playerController = new PlayerController();
		cc = new CommonController();
		gm = new GameMap();
		listOfPlayers = new ArrayList<String>(Arrays.asList("sakib", "sai", "garima"));
		playersWithStrategies=new HashMap<String, String>();
		playersWithStrategies.put("sakib","Aggressive");
		playersWithStrategies.put("sai","Benevolent");
		playersWithStrategies.put("garima","Random");
		playerDetails = new HashMap<String, Player>();
		fileName = "risk.map";
		msc.gameMapReading(gm.getContinents(), gm.getCountries(), gm.getBoundries(), fileName);
		psc.assignRandomCountries(listOfPlayers,playersWithStrategies, gm.getCountries(), playerDetails);
		psc.placeAll(gm.getCountries(), playerDetails, CONSTANTS.NO_PLAYER_ARMIES.get(3));
		pOb = playerDetails.get("sakib");
		playerOwnedCountries = pOb.getOwnedCountriesList();

	}

	/**
	 * This method is used for testing player does not own targeted country
	 */
	@Test
	public void testPlayerDontOwnCountry() {
		assertEquals(
				playerController.fortify(playerDetails, "Quebec", "Alberta ", 5, gm.getCountries(), gm.getBoundries()),
				"Targeted country is not owned by player");
	}

	/**
	 * This method is used for testing player does not own the path
	 */
	@Test
	public void testPlayerDontOwnPath() {
		fromCountry = playerOwnedCountries.get(0);
		toCountry = playerOwnedCountries.get(playerOwnedCountries.size()-1);
		
		armyToPlace = pOb.getOwnedCountriesArmiesList().get(fromCountry) - 1;
		assertEquals(playerController.fortify(playerDetails, fromCountry, toCountry, armyToPlace, gm.getCountries(),
				gm.getBoundries()), "Player does not own the path");
	}
	
	/**
	 * This method is used for testing the fortify method
	 */
	@Test
	public void testFortify() {
		fromCountry = playerOwnedCountries.get(1);
		toCountry = playerOwnedCountries.get(1);
		armyToPlace = pOb.getOwnedCountriesArmiesList().get(fromCountry) - 1;
		String result = playerController.fortify(playerDetails, fromCountry, toCountry, armyToPlace,gm.getCountries(),gm.getBoundries());
		assertEquals("Foritified successfully", result);
	}

}
