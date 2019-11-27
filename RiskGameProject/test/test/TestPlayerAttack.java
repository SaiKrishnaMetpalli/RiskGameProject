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
import model.Continents;
import model.GameMap;
import model.Player;
import model.PlayersList;
import util.CONSTANTS;

/**
 * This class is used for testing attack methods.
 *
 */
public class TestPlayerAttack {

	PlayerController playerController;
	MapSelectionController msc;
	PlayerSelectionController psc;
	CommonController cc;
	CONSTANTS con;
	GameMap gm;
	HashMap<String, Player> playerDetails;
	HashMap<Integer, Continents> continents;
	String continentName;
	ArrayList<String> listOfPlayers;
	HashMap<String,String> playersWithStrategies;
	String fileName;
	String countryName = "Quebec";
	String player;
	Player p;
	PlayersList pl;
	HashMap<String, Player> playersList;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * 
	 * @throws FileNotFoundException
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		playerController = new PlayerController();
		msc = new MapSelectionController();
		psc = new PlayerSelectionController();
		cc = new CommonController();
		gm = new GameMap();
		listOfPlayers = new ArrayList<String>(Arrays.asList("sakib", "sai", "garima"));
		playersWithStrategies=new HashMap<String, String>();
		playersWithStrategies.put("sakib","Aggressive");
		playersWithStrategies.put("sai","Benevolent");
		playersWithStrategies.put("garima","Random");
		playerDetails = new HashMap<String, Player>();
		fileName = "risk.map";
		continents = gm.getContinents();
		msc.gameMapReading(gm.getContinents(), gm.getCountries(), gm.getBoundries(), fileName);
		psc.assignRandomCountries(listOfPlayers,playersWithStrategies, gm.getCountries(), playerDetails);
		psc.placeAll(gm.getCountries(), playerDetails, CONSTANTS.NO_PLAYER_ARMIES.get(3));
		player = cc.findPlayerNameFromCountry(gm.getCountries(), countryName);
		p = new Player();
		pl = new PlayersList();
		playersList = new HashMap<String, Player>();
		playersList.put("a", new Player());
		playersList.put("b", new Player());
		pl.setListOfPlayers(playersList);
		p.setConqueredCountries(new ArrayList<String>(Arrays.asList("India", "Japan", "Quebec")));
		

	}

	/**
	 * This method is used for testing the attack phase method : success Case
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testAttackPhaseSuccess() {
		String result = playerController.attackPhase("India", "China", 3, playerDetails.get(player));
		assertEquals("Attacker Ready and placed his army on field", result); 
	}

	/**
	 * This method is used for testing the attack phase method: failure Case
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testAttackPhaseFailure() {
		String re = playerController.attackPhase("China", "Quebec", 2, playerDetails.get(player));
		assertEquals("Attacker Ready and placed his army on field", re);
	}

	/**
	 * This method is used for testing the defend phase method
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testDefendPhase() {
		boolean ans = true;
		boolean result = playerController.defendPhaseDiceRoll("China", 2, playerDetails.get(player));
		assertEquals(ans, result);
	}

	/**
	 * This method is used for testing if attacker and defender country are in same
	 * continent
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testValidateDefenderCountry() {
		assertTrue(playerController.validateDefenderCountry("India", "China", gm.getCountries(), gm.getBoundries(),
				new ArrayList<String>(Arrays.asList("India", "Pakistan", "Bangladesh"))));
		assertFalse(playerController.validateDefenderCountry("India", "China", gm.getCountries(), gm.getBoundries(),
				new ArrayList<String>(Arrays.asList("India", "Pakistan", "China"))));
	}

	/**
	 * This method is used for testing that the army moved is greater than the
	 * number of dice rolled.
	 * 
	 * @author Gagan Jaswal
	 */
	@Test
	public void testValidateNumOfArmyMovesSuccessOne() {
		assertEquals(playerController.validateNumOfArmyMoves(3, 4), true);
	}

	/**
	 * This method is used for testing that the army moved is equal to the number of
	 * dice rolled.
	 * 
	 * @author Gagan Jaswal
	 */
	@Test
	public void testValidateNumOfArmyMovesSuccessTwo() {
		assertEquals(playerController.validateNumOfArmyMoves(4, 4), true);
	}

	/**
	 * This method is used for testing that the army moved is less than the number
	 * of dice rolled.
	 * 
	 * @author Gagan Jaswal
	 */
	@Test
	public void testValidateNumOfArmyMovesFail() {
		assertEquals(playerController.validateNumOfArmyMoves(4, 3), false);
	}

	/**
	 * This method is used for testing num dice validation with success case.
	 * 
	 * @author Gagan Jaswal
	 */
	@Test
	public void testValidateNumDiceSuccess() {
		HashMap<String, Integer> attackerArmiesMap = new HashMap<String, Integer>();
		attackerArmiesMap.put("malasiya", 4);
		p.setOwnedCountriesArmiesList(attackerArmiesMap);
		assertEquals(playerController.validateNumDice("malasiya", 3, p), true);
	}

	/**
	 * This method is used for testing num dice validation with fail case.
	 * 
	 * @author Gagan Jaswal
	 */
	@Test
	public void testValidateNumDiceFail() {
		HashMap<String, Integer> attackerArmiesMap = new HashMap<String, Integer>();
		attackerArmiesMap.put("malasiya", 4);
		p.setOwnedCountriesArmiesList(attackerArmiesMap);
		assertEquals(playerController.validateNumDice("malasiya", 5, p), false);
	}

	/**
	 * This method is used for testing the defend phase dice roll
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testDefendPhaseDiceRoll() {
		boolean ans = false;
		ans = playerController.defendPhaseDiceRoll("China", 2, playerDetails.get(player));
		boolean val = true;
		assertEquals(ans, val);
	}

	/**
	 * Method is used to test valid army move to conquered country
	 * 
	 * @author Ashish Chaudhary
	 */
	@Test
	public void testIsValidAttackMove() {
		p.setDefenderCountry("India");
		assertTrue(playerController.isvalidAttackMove(3, 3, p.getConqueredCountries(), p, 4));
		p.setDefenderCountry("Canada");
		assertFalse(playerController.isvalidAttackMove(3, 3, p.getConqueredCountries(), p, 3));
	}
	/**
	 * Method is used to test game end 
	 * 
	 * @author Ashish Chaudhary
	 */
	@Test
	public void testCheckGameEnd() {
		assertFalse(playerController.checkGameEnd(pl));
	}
	/**
	 * Method is used to test get continent num 
	 * 
	 * @author garimadawar
	 */
	@Test
	public void testgetContinentNum() {
	int result=cc.getContinentNum(continents, "India");
	assertEquals(0, result);
	
	}
}
