package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.FortificationController;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import model.GameMap;
import model.Player;
import util.CONSTANTS;

/**
 * This class is used for testing fortification methods
 */
public class TestFortification {

	FortificationController fc;
	MapSelectionController msc;
	PlayerSelectionController psc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	ArrayList<String> listOfPlayers;
	ArrayList<String> playerOwnedCountries;
	String fileName;
	String fromCountry;
	String toCountry;
	int armyToPlace;
	Player pOb;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * 
	 * @throws FileNotFoundException
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		msc = new MapSelectionController();
		psc = new PlayerSelectionController();
		fc = new FortificationController();
		gm = new GameMap();
		listOfPlayers = new ArrayList<String>(Arrays.asList("sakib", "sai", "garima"));
		playerDetails = new HashMap<String, Player>();
		fileName = "risk.map";
		msc.gameMapReading(gm.getContinents(), gm.getCountries(), gm.getBoundries(), fileName);
		psc.assignRandomCountries(listOfPlayers, gm.getCountries(), playerDetails);
		psc.placeAll(gm.getCountries(), playerDetails, CONSTANTS.NO_PLAYER_ARMIES.get(3));
		pOb = playerDetails.get("sakib");
		playerOwnedCountries = pOb.getOwnedCountriesList();

	}

	/**
	 * This method is used for testing player does not own targeted country
	 */
	@Test
	public void testPlayerDontOwnCountry() {
		assertEquals(fc.fortify(playerDetails, "Quebec", "Alberta ", 5, gm.getCountries(), gm.getBoundries()),
				"Targeted country is not owned by player");
	}

	/**
	 * This method is used for testing player does not own the path
	 */
	@Test
	public void testPlayerDontOwnPath() {
		fromCountry = playerOwnedCountries.get(0);
		toCountry = playerOwnedCountries.get(2);
		armyToPlace = pOb.getOwnedCountriesArmiesList().get(fromCountry) - 1;
		assertEquals(
				fc.fortify(playerDetails, fromCountry, toCountry, armyToPlace, gm.getCountries(), gm.getBoundries()),
				"Player does not own the path");
	}

}
