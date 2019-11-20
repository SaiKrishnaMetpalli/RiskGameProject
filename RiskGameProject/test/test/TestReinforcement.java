package test;

import static org.junit.Assert.assertEquals;
import model.Player;
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
import util.CONSTANTS;

/**
 * This class is used for testing the reinforcement methods
 * 
 */
public class TestReinforcement {

	PlayerController playerController;
	MapSelectionController msc;
	PlayerSelectionController psc;
	CommonController cc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	ArrayList<String> listOfPlayers;
	HashMap<String,String> playersWithStrategies;
	String fileName;
	String countryName = "Quebec";
	String player;
	ArrayList<String> currentCardList;
	Player modelP;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * 
	 * @throws FileNotFoundException if the map file is not find an exception is
	 *                               thrown
	 * @author sakib
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
		player = cc.findPlayerNameFromCountry(gm.getCountries(), countryName);
		currentCardList = new ArrayList<String>();
		modelP = new Player();
	}

	/**
	 * testing the reinforcement country reward
	 * 
	 * @author sakib
	 */
	@Test
	public void testCalculateOwnedCountryReward() {
		assertEquals(playerController.calculateOwnedCountryReward(playerDetails.get(player)), 4);
	}

	/**
	 * testing the reinforcement continent reward
	 * 
	 * @author sakib
	 */
	@Test
	public void testCalculateContinentReward() {
		// playerDetails.get(player).setOwnedCountriesList(cc.getContinentsCountryList(gm.getContinents(),
		// gm.getCountries()).get(countryName));
		assertEquals(playerController.calculateContinentReward(playerDetails.get(player), gm.getContinents(),
				gm.getCountries(), countryName), 0);
	}

	/**
	 * This method is used for testing reinforcement armies calculation
	 * 
	 * @author sakib
	 */
	@Test
	public void testReinforcementCalculation() {
		assertEquals(playerController.calculateReinforceArmy(4, 3, 2), 9);
	}

	/**
	 * This method is used for testing reinforcement armies calculation returning at
	 * least 3 armies
	 * 
	 * @author sakib
	 */
	@Test
	public void testReinforcementCalculationMinimumArmy() {
		assertEquals(playerController.calculateReinforceArmy(1, 0, 0), 3);
	}

	/**
	 * This method is used for testing reinforcement army placement success
	 * scenarios
	 * 
	 * @author sakib
	 */
	@Test
	public void testplaceReinforceArmySucess() {

		modelP.setAvailableReinforceArmies(5);
		assertEquals(playerController.placeReinforceArmy(countryName, 4, gm.getCountries(), playerDetails,
				gm.getContinents(), modelP), "Reinforcement armies placed successfully");
	}

	/**
	 * This method is used for testing reinforcement army placement failure
	 * scenarios
	 * 
	 * @author sakib
	 */
	@Test
	public void testplaceReinforceArmyFail() {
		modelP.setAvailableReinforceArmies(5);
		assertEquals(
				playerController.placeReinforceArmy(countryName, 6, gm.getCountries(), playerDetails,
						gm.getContinents(), modelP),
				"Reinforcement cannot be performed; the given reinforcement armies are greater than the available reinforcemet armies");
	}

	/**
	 * tests for the exchangeCard method three unique card
	 * 
	 * @author sakib
	 */
	@Test
	public void testExchangeCardSuccessForUniqeCard() {
		currentCardList.addAll(Arrays.asList("a", "b", "c"));
		assertEquals(playerController.exchangeCard(1, 2, 3, currentCardList, playerDetails.get(player)), 5);
		currentCardList.clear();
		currentCardList.addAll(Arrays.asList("e", "d", "f"));
		assertEquals(playerController.exchangeCard(1, 2, 3, currentCardList, playerDetails.get(player)), 10);

	}

	/**
	 * tests for the exchangeCard method three same card
	 * 
	 * @author sakib
	 */
	@Test
	public void testExchangeCardSuccessForSameCard() {
		currentCardList.addAll(Arrays.asList("a", "a", "a"));
		assertEquals(playerController.exchangeCard(1, 2, 3, currentCardList, playerDetails.get(player)), 5);

	}

	/**
	 * tests for the exchangeCard method when there is no valid card set
	 * 
	 * @author sakib
	 */
	@Test
	public void testExchangeCardNoArmy() {
		currentCardList.addAll(Arrays.asList("a", "b", "a"));
		assertEquals(playerController.exchangeCard(1, 2, 3, currentCardList, playerDetails.get(player)), 0);
	}

}
