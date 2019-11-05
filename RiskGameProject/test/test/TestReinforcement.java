package test;

import static org.junit.Assert.assertEquals;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

import controller.CommonController;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import controller.ReinforcementController;
import model.GameMap;
import model.Player;
import util.CONSTANTS;

/**
 * This class is used for testing the reinforcement methods 
 */
public class TestReinforcement {

	ReinforcementController ric;
	MapSelectionController msc;
	PlayerSelectionController psc;
	CommonController cc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	ArrayList<String> listOfPlayers;
	String fileName;
	String countryName = "Quebec";
	String player;

	/**
	 * This method is used for initial setting up scenarios for each test case method
	 * @throws FileNotFoundException
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		msc = new MapSelectionController();
		psc = new PlayerSelectionController();
		ric = new ReinforcementController();
		cc=new CommonController();
		gm = new GameMap();
		listOfPlayers = new ArrayList<String>(Arrays.asList("sakib", "sai", "garima"));
		playerDetails = new HashMap<String, Player>();
		fileName = "risk.map";
		msc.gameMapReading(gm.getContinents(), gm.getCountries(), gm.getBoundries(), fileName);
		psc.assignRandomCountries(listOfPlayers, gm.getCountries(), playerDetails);
		psc.placeAll(gm.getCountries(), playerDetails, CONSTANTS.NO_PLAYER_ARMIES.get(3));
		player = cc.findPlayerNameFromCountry(gm.getCountries(), countryName);
	}

	/**
	 * This method is used for testing reinforcement armies calculation 
	 */
	@Test
	public void testReinforcementCalculation() {
		assertEquals(ric.calculateReinforceArmy(playerDetails.get(player), gm.getContinents(), gm.getCountries(), countryName,5),
				9);
	}

	/**
	 * This method is used for testing reinforcement army placement success scenarios
	 */
	@Test
	public void testplaceReinforceArmySucess() {
		int numOfArmiesToPlace = ric.calculateReinforceArmy(playerDetails.get(player), gm.getContinents(), gm.getCountries(),
				countryName,5);
		assertEquals(
				ric.placeReinforceArmy(countryName, numOfArmiesToPlace, gm.getCountries(), playerDetails, gm.getContinents(),40),
				"Reinforcement armies placed successfully");
	}

	/**
	 * This method is used for testing reinforcement army placement failure scenarios
	 */
	@Test
	public void testplaceReinforceArmyFail() {
		int numOfArmiesToPlace = ric.calculateReinforceArmy(playerDetails.get(player), gm.getContinents(), gm.getCountries(),
				countryName,5) + 5;
		assertEquals(
				ric.placeReinforceArmy(countryName, numOfArmiesToPlace, gm.getCountries(), playerDetails, gm.getContinents(),5),
				"Not enough reinforcement armies available");
	}

}
