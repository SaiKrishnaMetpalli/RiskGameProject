package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.CommonController;
import controller.MapFormatValidation;
import controller.MapSelectionController;
import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;
/**
 * test cases for common methods
 * @author sakib
 *
 */

public class TestCommonMethods {
	/**
	 * cc instantiates CommonController Class
	 * msc instantiates MapSelectionController class
	 * mfv instantiates MapFormatValidation class
	 * continents represents hashamp parsed from text file
	 * Countries represents hashamp parsed from text file
	 * boundries represents hashamp parsed from text file
	 * filename to parsed
	 * gm instantiates GameMap class
	 * pl instantiates PlayerList class
	 * P represents Player class object
	 * Gs is Gamestate object
	 * flag is boolean variable used for map validation
	 */
	CommonController cc;
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
	
	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * @author garimadawar
	 */
	@Before
	public void setUp() throws FileNotFoundException {
		fileName = "risk.map";
		msc = new MapSelectionController();
		gm = new GameMap();
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		msc.gameMapReading(continents, countries, boundries, fileName);
		gs = new GameState();
		cc = new CommonController();
		
	}
	/**
	 * Test success case for GetContinentNumberfromName
	 * @author sakib
	 */
	@Test
	public void TestGetContinentNumberfromName() {
		assertEquals(cc.getContinentNum(continents, "Oceania"),6);
	}
	
	/**
	 * test failure case for GetContinentNumberfromName
	 * @author sakib
	 */
	@Test
	public void TestGetContinentNumberfromNameFail() {
		assertNotEquals(cc.getContinentNum(continents, "Oceania"),4);
	}
	
	/**
	 * Test GetCountryNumberfromName success
	 *  @author sakib
	 */
	@Test
	public void TestGetCountryNumberfromName() {
		assertEquals(cc.getCountryNumberByName(countries, "Japan"),37);
	}
	
	/**
	 * Test GetCountryNumberfromName failure
	 *  @author sakib
	 */
	@Test
	public void TestGetCountryNumberfromNameFail() {
		assertNotEquals(cc.getCountryNumberByName(countries, "Japan"),3);
	}
	
	/**
	 * Test GetCountryNameByNum success
	 *  @author sakib
	 */
	@Test
	public void TestGetCountryNameByNum() {
		assertEquals(cc.getCountryNameByNum(countries, 37),"Japan");
	}
	
	/**
	 * Test GetCountryNameByNum failure
	 *  @author sakib
	 */
	@Test
	public void TestGetCountryNameByNumFail() {
		assertNotEquals(cc.getCountryNameByNum(countries, 37),"Canada");
	}

}
