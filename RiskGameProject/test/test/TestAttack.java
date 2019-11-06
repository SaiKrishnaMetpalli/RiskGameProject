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

/**
 * This class is used for testing attack methods.
 *
 */
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
	 * This method is used for testing the attack phase method : success Case
	 * @author garimadawar
	 * */
	@Test
	public void testAttackPhaseSuccess() {
		String result = ac.attackPhase("India", "China", 3,playerDetails.get(player));
		assertEquals("Attacker Ready and placed his army on field",result);  //need to confirm from Ashish
	}
	
	/**
	 * This method is used for testing the attack phase method: failure Case
	 * @author garimadawar
	 * */
	@Test
	public void testAttackPhaseFailure() {
	      String re = ac.attackPhase("China", "Quebec", 2, playerDetails.get(player));
	      assertEquals("Attacker Ready and placed his army on field", re);
	}
	/**
	 * This method is used for testing the defend phase method
	 * @author garimadawar
	 * */
	@Test
	public void testDefendPhase() {
		boolean ans = true;
		boolean result = ac.defendPhaseDiceRoll("China", 2, playerDetails.get(player));
		assertEquals(ans,result);
	}
	
	/**
	 * This method is used for testing if attacker and defender country are in same continent
	 *  @author garimadawar
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
	 * @author Gagan Jaswal 
	 * */
	@Test
	public void testValidateNumOfArmyMovesSuccessOne() {
		assertEquals(ac.validateNumOfArmyMoves(3, 4), true);
	}
	
	/**
	 * This method is used for testing that the army moved is equal to the number of dice rolled.
	 *  @author Gagan Jaswal
	 * */
	@Test
	public void testValidateNumOfArmyMovesSuccessTwo() {
		assertEquals(ac.validateNumOfArmyMoves(4, 4), true);
	}
	
	/**
	 * This method is used for testing that the army moved is less than the number of dice rolled.
	 * @author Gagan Jaswal
	 * */
	@Test
	public void testValidateNumOfArmyMovesFail() {
		assertEquals(ac.validateNumOfArmyMoves(4, 3), false);
	}
	
	/**
	 * This method is used for testing num dice validation with success case.
	 * @author Gagan Jaswal 
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
	 * @author Gagan Jaswal 
	 * */
	@Test
	public void testValidateNumDiceFail() {
	HashMap<String, Integer> attackerArmiesMap = new HashMap<String, Integer>();
	attackerArmiesMap.put("malasiya", 4);
	p.setOwnedCountriesArmiesList(attackerArmiesMap);
	assertEquals(ac.validateNumDice("malasiya", 5, p), false); 
	}
	
	/**
	 * This method is used for testing the attack alloutphase
	 * @author garimadawar
	 */
	@Test
    public void testAllOutAttackedPhase() {
		
	}
	
	/**
	 * This method is used for testing the defend alloutphase
	 * @author garimadawar
	 */
	@Test
    public void testAllOutDefendPhase() {
		
	}
	
	/**
	 * This method is used for testing the defend phase dice roll
	 * @author garimadawar
	 */
	@Test
    public void testDefendPhaseDiceRoll() {
		boolean ans = true;
		ans = ac.defendPhaseDiceRoll("China", 2,playerDetails.get(player));
		boolean val = true;
		assertEquals(ans,val);
	}
}
