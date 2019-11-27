package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.MapFormatValidation;
import controller.MapSelectionController;
import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;

/**
 * Test method for game runner
 * 
 * @author garimadawar,sakib
 *
 */
public class TestLoadGame {
	MapSelectionController msc;
	MapFormatValidation mfv;
	HashMap<Integer, Continents> continents;
	HashMap<Integer, Countries> countries;
	HashMap<Integer, ArrayList<Integer>> boundries;
	String fileName;
	GameMap gm;
	PlayersList pl;
	Player p;
	GameState gs;
	boolean flag;
	@Before
	public void setUp()throws FileNotFoundException {
		msc = new MapSelectionController();
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		fileName = "testSaveGame.map";
		gm = new GameMap();
		pl = new PlayersList();
		p = new Player();
		mfv = new MapFormatValidation();
		flag = false;
		gs = new GameState();
	}
	/**
	 * test case for loading saved game
	 * @throws FileNotFoundException
	 */
	@Test
	public void testLoadGameSuccess() throws FileNotFoundException {
		assertEquals("Success",msc.loadGameReading(gm, pl, p, fileName));
	}
	
	

}
