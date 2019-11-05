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
	ArrayList<String> currentCardList;
	Player modelP;

	/**
	 * This method is used for initial setting up scenarios for each test case method
	 * @throws FileNotFoundException if the map file is not find an exception is thrown 
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
		currentCardList = new ArrayList<String>();
		modelP = new Player();
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
		
		modelP.setAvailableReinforceArmies(5);
		assertEquals(
				ric.placeReinforceArmy(countryName, 4, gm.getCountries(), playerDetails, gm.getContinents(),modelP),
				"Reinforcement armies placed successfully");
	}

	/**
	 * This method is used for testing reinforcement army placement failure scenarios
	 */
	@Test
	public void testplaceReinforceArmyFail() {
		int numOfArmiesToPlace = ric.calculateReinforceArmy(playerDetails.get(player), gm.getContinents(), gm.getCountries(),
				countryName,5) + 5;
		modelP.setAvailableReinforceArmies(5);
		assertEquals(
				ric.placeReinforceArmy(countryName, numOfArmiesToPlace, gm.getCountries(), playerDetails, gm.getContinents(),modelP),
				"Not enough reinforcement armies available");
	}
	
	/**
	 * tests for the exchangeCard method three unique card and 3 same card
	 */
	@Test
	public void testExchangeCardSuccess() {
		currentCardList.addAll(Arrays.asList("a","b","c"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),5);
		currentCardList.clear();
		currentCardList.addAll(Arrays.asList("a","a","a"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),10);
		
	}
	
	/**
	 * tests for the exchangeCard method when there is no valid card set
	 */
	@Test
	public void testExchangeCardNoArmy() {
		currentCardList.addAll(Arrays.asList("a","b","a"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),0);	
	}

}
