package test;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import controller.ReinforcementController;
import model.GameMap;
import model.Player;
import util.CONSTANTS;

public class TestReinforcement {

	ReinforcementController ric;
	MapSelectionController msc;
	PlayerSelectionController psc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	ArrayList<String> listOfPlayers;
	String fileName;
	String countryName = "Quebec";
	String player;

	@Before
	public void setUp() throws FileNotFoundException {
		msc = new MapSelectionController();
		psc = new PlayerSelectionController();
		ric = new ReinforcementController();
		gm = new GameMap();
		listOfPlayers = new ArrayList<String>(Arrays.asList("sakib", "sai", "garima"));
		playerDetails = new HashMap<String, Player>();
		fileName = "risk.map";
		msc.gameMapReading(gm.continents, gm.countries, gm.boundries, fileName);
		psc.assignRandomCountries(listOfPlayers, gm.countries, playerDetails);
		psc.placeAll(gm.countries, playerDetails, CONSTANTS.NO_PLAYER_ARMIES.get(3));
		player = ric.findPlayerNameFromCountry(gm.countries, countryName);
	}

	@Test
	public void testReinforcementCalculation() {
		assertEquals(ric.calculateReinforceArmy(playerDetails.get(player), gm.continents, gm.countries, countryName),
				4);
	}

	@Test
	public void testplaceReinforceArmySucess() {
		int numOfArmiesToPlace = ric.calculateReinforceArmy(playerDetails.get(player), gm.continents, gm.countries,
				countryName);
		assertEquals(
				ric.placeReinforceArmy(countryName, numOfArmiesToPlace, gm.countries, playerDetails, gm.continents),
				"Reinforcement armies placed successfully");
	}

	@Test
	public void testplaceReinforceArmyFail() {
		int numOfArmiesToPlace = ric.calculateReinforceArmy(playerDetails.get(player), gm.continents, gm.countries,
				countryName) + 5;
		assertEquals(
				ric.placeReinforceArmy(countryName, numOfArmiesToPlace, gm.countries, playerDetails, gm.continents),
				"Not enough reinoforcement armies available");
	}

}
