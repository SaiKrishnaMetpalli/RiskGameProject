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
	 * testing the reinforcement country reward 
	 */
	@Test
	public void testCalculateOwnedCountryReward() {
		assertEquals(ric.calculateOwnedCountryReward(playerDetails.get(player)),4);
	}
	
	/**
	 * testing the reinforcement continent reward 
	 */
	@Test
	public void testCalculateContinentReward() {
		//playerDetails.get(player).setOwnedCountriesList(cc.getContinentsCountryList(gm.getContinents(), gm.getCountries()).get(countryName));
		assertEquals(ric.calculateContinentReward(playerDetails.get(player), gm.getContinents(), gm.getCountries(), countryName),0);
	}
	
	/**
	 * This method is used for testing reinforcement armies calculation 
	 */
	@Test
	public void testReinforcementCalculation() {
		assertEquals(ric.calculateReinforceArmy(4,3,2),9);
	}
	
	/**
	 * This method is used for testing reinforcement armies calculation returning at least 3 armies
	 */
	@Test
	public void testReinforcementCalculationMinimumArmy() {
		assertEquals(ric.calculateReinforceArmy(1,0,0),3);
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
		modelP.setAvailableReinforceArmies(5);
		assertEquals(
				ric.placeReinforceArmy(countryName, 6, gm.getCountries(), playerDetails, gm.getContinents(),modelP),
				"Not enough reinforcement armies available");
	}
	
	/**
	 * tests for the exchangeCard method three unique card
	 */
	@Test
	public void testExchangeCardSuccessForUniqeCard() {
		currentCardList.addAll(Arrays.asList("a","b","c"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),5);
		currentCardList.clear();
		currentCardList.addAll(Arrays.asList("e","d","f"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),10);
		
	}
	

	/**
	 * tests for the exchangeCard method three same card
	 */
	@Test
	public void testExchangeCardSuccessForSameCard() {
		currentCardList.addAll(Arrays.asList("a","a","a"));
		assertEquals(ric.exchangeCard(1,2,3, currentCardList, playerDetails.get(player)),5);
		
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
