package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;


import controller.AttackController;
import controller.CommonController;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import controller.ReinforcementController;
import model.GameMap;
import model.Player;
import util.CONSTANTS;

public class TestAttack {
    
    AttackController ac;
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
	Player p;
	
	
	/**
	 * This method is used for initial setting up scenarios for each test case method
	 * @throws FileNotFoundException
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		ac = new AttackController();
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
		p= new Player();
	}
	
	
	/**
	 * This method is used for testing the attack phase method
	 * 
	 * */
	@Test
	public void testAttackPhase() {
		/*
		String result = ac.attackPhase("India", "China", 3,playerDetails.get(player),playerDetails.get(player));
		assertEquals("Attacker ready to attack the Country",result);  //need to confirm from Ashish
		String re = ac.attackPhase("China", "Quebec", 2, playerDetails.get(player),playerDetails.get(player));
		assertEquals("Attacker ready to attack the Country", re);
		*/
	}
	
	/**
	 * This method is used for testing the attack phase method
	 * 
	 * */
	@Test
	public void testDefendPhase() {
		boolean ans = true;
		boolean result = ac.defendPhaseDiceRoll("China", 2, playerDetails.get(player));
		assertEquals(ans,result);
	}
	
	/**
	 * This method is used for testing if attacker and defender country are in same continent
	 *  
	 * */
	@Test
	public void testValidateDefenderCountry() {
		boolean ans = true;
	ans = ac.validateDefenderCountry("India", "China", gm.getCountries(), gm.getBoundries());
	boolean val = true;
	assertEquals(ans,val);
	}
	
	/**
	 * This method is used for testing that the army moved is greater than the number of dice rolled.
	 *  
	 * */
	@Test
	public void testValidateNumOfArmyMovesSuccessOne() {
		assertEquals(ac.validateNumOfArmyMoves(3, 4), true);
	}
	
	/**
	 * This method is used for testing that the army moved is equal to the number of dice rolled.
	 *  
	 * */
	@Test
	public void testValidateNumOfArmyMovesSuccessTwo() {
		assertEquals(ac.validateNumOfArmyMoves(4, 4), true);
	}
	
	/**
	 * This method is used for testing that the army moved is less than the number of dice rolled.
	 *  
	 * */
	@Test
	public void testValidateNumOfArmyMovesFail() {
		assertEquals(ac.validateNumOfArmyMoves(4, 3), false);
	}
	
	/**
	 * This method is used for testing num dice validation with success case.
	 *  
	 * */
	@Test
	public void testValidateNumDiceSuccess() {
		HashMap<String, Integer> attackerArmiesMap = new HashMap<String, Integer>();
		attackerArmiesMap.put("malasiya", 4);
		p.setOwnedCountriesArmiesList(attackerArmiesMap);
		assertEquals(ac.validateNumDice("malasiya", 3, p), true); 
	}
	
	/**
	 * This method is used for testing num dice validation with fail case.
	 *  
	 * */
	@Test
	public void testValidateNumDiceFail() {
	HashMap<String, Integer> attackerArmiesMap = new HashMap<String, Integer>();
	attackerArmiesMap.put("malasiya", 4);
	p.setOwnedCountriesArmiesList(attackerArmiesMap);
	assertEquals(ac.validateNumDice("malasiya", 5, p), false); 
	}
}
