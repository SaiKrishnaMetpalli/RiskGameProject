package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;

import controller.PlayerSelectionController;

/**
 * This class is used for testing the player methods
 */
public class TestPlayerOperations {
	PlayerSelectionController psc;
	ArrayList<String> listofPlayers;
	String playerName;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 */
	@Before
	public void setUp() {
		listofPlayers = new ArrayList<String>(Arrays.asList("Sakib", "Sai", "Harman", "hsgdf"));
		psc = new PlayerSelectionController();
		playerName = "";
	}

	/**
	 * This method is used for testing the add player scenarios
	 */
	@Test
	public void addPlayerTest() {
		String result = psc.addPlayer(listofPlayers, "gagan");
		assertEquals(result, "Success");
		String re = psc.addPlayer(listofPlayers, "gagan");
		assertEquals(re, "Failure");
	}

	/**
	 * This method is used for testing the remove player scenarios
	 */
	@Test
	public void removePlayerTest() {
		String result = psc.removePlayer(listofPlayers, "hsgdf");
		assertEquals(result, "Success");
		String re = psc.removePlayer(listofPlayers, "hsgdf");
		assertEquals(re, "Failure");
	}
}
