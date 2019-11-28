package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import model.Tournament;
import view.CommandLine;

/**
 * This class is used for testing the tournament mode operations
 * @author Sai Krishna
 *
 */
public class TestTournament {	
	
	CommandLine cl;
	
	String[] tags;
	String[] tags2;
	
	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * @author Sai Krishna
	 */
	@Before
	public void setUp() {
		cl=new CommandLine();
		
		tags=new String[] {"tournament","-M","ABC_Map.map,bbbb.map","-P","Cheater,Aggressive","-G","3","-D","10"};
		tags2=new String[] {"tournament","-B","ABC_Map.map,gggg.map","-P","Cheater,Human","-G","6","-D","60"};
	}

	/**
	 * This method is used for testing the tags format
	 * @author Sai Krishna
	 */
	@Test
	public void testTournamentTagsFormat() {
		assertTrue(cl.checkTournamentTagsFormat(tags));
		assertFalse(cl.checkTournamentTagsFormat(tags2));
	}
	
	/**
	 * This methos is used for testing the file conditions
	 * @author Sai Krishna
	 */
	@Test
	public void testTournamentFileConditions() {
		assertTrue(cl.checkTournamentFileConditions(tags[2]));
		assertFalse(cl.checkTournamentFileConditions(tags2[2]));
	}
	
	/**
	 * This method is used for testing the strategies conditions
	 * @author Sai Krishna
	 */
	@Test
	public void testTournamentStrategiesConditions() {
		assertTrue(cl.checkTournamentStrategiesConditions(tags[4]));
		assertFalse(cl.checkTournamentStrategiesConditions(tags2[4]));
	}
	
	/**
	 * This method is used for testing the games conditions
	 * @author Sai Krishna
	 */
	@Test
	public void testTournamentGamesConditions() {
		assertTrue(cl.checkTournamentGamesConditions(Integer.parseInt(tags[6])));
		assertFalse(cl.checkTournamentGamesConditions(Integer.parseInt(tags2[6])));
	}
	
	/**
	 * This method is used for testing the turns conditions
	 * @author Sai Krishna
	 */
	@Test
	public void testTournamentTurnsConditions() {
		assertTrue(cl.checkTournamentTurnsConditions(Integer.parseInt(tags[8])));
		assertFalse(cl.checkTournamentTurnsConditions(Integer.parseInt(tags2[8])));
	}
	
	/**
	 * This method is used for testing the tournament mode
	 * @author Sai Krishna
	 */
	@Test
	public void testRunTournamentMode() {
		String result=cl.runTournamentMode();
		assertEquals("Success", result);
	}

}
