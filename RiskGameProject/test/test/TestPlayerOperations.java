package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.PlayerSelectionController;

/**
 * This class is used for testing the player methods
 * @author garimadawar
 */
public class TestPlayerOperations {
	PlayerSelectionController psc;
	ArrayList<String> listofPlayers;
	HashMap<String, String> playersWithStrategies;
	String playerName;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * @author garimadawar
	 */
	@Before
	public void setUp() {
		listofPlayers = new ArrayList<String>(Arrays.asList("Sakib", "Sai", "Harman", "hsgdf"));
		playersWithStrategies = new HashMap<String, String>();
		psc = new PlayerSelectionController();
		playerName = "";
	}

	/**
	 * This method is used for testing the add player scenarios
	 * @author garimadawar
	 */
	@Test
	public void addPlayerTest() {
		String result = psc.addPlayer(playersWithStrategies, listofPlayers, "gagan", "Aggressive");
		assertEquals(result, "Success");
		String re = psc.addPlayer(playersWithStrategies, listofPlayers, "gagan", "Aggressive");
		assertEquals(re, "Failure");
	}

	/**
	 * This method is used for testing the remove player scenarios
	 * @author garimadawar
	 */
	@Test
	public void removePlayerTest() {
		String result = psc.removePlayer(playersWithStrategies,listofPlayers, "hsgdf");
		assertEquals(result, "Success");
		String re = psc.removePlayer(playersWithStrategies,listofPlayers, "hsgdf");
		assertEquals(re, "Failure");
	}
}
