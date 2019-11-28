package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import adapter.ConquestReadWrite;
import adapter.DominationReadWrite;
import controller.MapFormatValidation;
import controller.MapSelectionController;
import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;

/**
 * This class is used for testing map methods
 */
public class TestMapOperations {
	/**
	 * msc instantiates MapSelectionController class
	 * mfv instantiates MapFormatValidation class
	 * crw instantiates ConquestReadWrite class
	 * drw instantiates DominationReadWrite class
	 * continents represents hashamp parsed from text file
	 * Countries represents hashamp parsed from text file
	 * boundries represents hashamp parsed from text file
	 * domination file string name to parsed
	 * conquest file string name to parsed
	 * save file string name to write saved file
	 * flag is boolean variable used for map validation
	 * gm instantiates GameMap class
	 * pl instantiates PlayerList class
	 * P represents Player class object
	 * Gs is Gamestate object
	 */
	MapSelectionController msc;
	MapFormatValidation mfv;
	ConquestReadWrite crw;
	DominationReadWrite drw;
	HashMap<Integer, Continents> continents;
	HashMap<Integer, Countries> countries;
	HashMap<Integer, ArrayList<Integer>> boundries;
	String domFile;
	String conFile;
	String saveFile;
	boolean flag;
	GameMap gm;
	PlayersList pl;
	Player p;
	GameState gs;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 * @author sakib
	 */
	@Before
	public void setUp() {
		msc = new MapSelectionController();
		mfv = new MapFormatValidation();
		crw = new ConquestReadWrite();
		drw = new DominationReadWrite();
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		domFile = "world.map";
		conFile = "ABC_Map.map";
		flag = false;
		saveFile = "testSaveGame.map";
		gm = new GameMap();
		pl = new PlayersList();
		p = new Player();
		mfv = new MapFormatValidation();
		flag = false;
		gs = new GameState();
	}

	/**
	 * test case for loading saved game
	 * @author sakib
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void testLoadGameSuccess() throws FileNotFoundException {
		assertEquals("Success",msc.loadGameReading(gm, pl, p, saveFile));
	}
	
	/**
	 * test case for saving a current game
	 * @throws IOException this throws exception on file writing
	 * @author sakib
	 */
	@Test
	public void testSaveGameSuccess() throws IOException {
		assertEquals("Success",msc.saveGameFile(gs, saveFile));
	}
	

	/**
	 * This method is used for testing the game map load
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void fileLoadTest() throws FileNotFoundException {

		String result = msc.gameMapReading(continents, countries, boundries, domFile);
		assertEquals("Success", result);
	}

	/**
	 * This method is used for testing the conquest map reading
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author sakib
	 */
	@Test
	public void TestConquestRead() throws FileNotFoundException {

		String result = crw.conquestMapReading(continents, countries, boundries, conFile);
		assertEquals("Success", result);
	}
	
	/**
	 * This method is used for testing the conquest map writing
	 * @throws IOException this throws exception on file writing 
	 * @author sakib
	 */
	@Test
	public void TestConquestWrite() throws IOException {

		String result = crw.writeConquestMapFile(continents, countries, boundries, "test.map");
		assertEquals("Success", result);
	}
	
	/**
	 * This method is used for testing the conquest map reading
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author sakib
	 */
	@Test
	public void TestDominationReadTest() throws FileNotFoundException {

		String result = drw.dominationMapReading(continents, countries, boundries, domFile);
		assertEquals("Success", result);
	}
	
	/**
	 * This method is used for testing the conquest map writing
	 * @throws IOException this throws exception on file writing 
	 * @author sakib
	 */
	@Test
	public void TestDominationWrite() throws IOException {

		String result = drw.writeDominationMapFile(continents, countries, boundries,"test.map");
		assertEquals("Success", result);
	}
	
	/**
	 * This method is used for checking whether map is connected or not
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void isConnectedMapTest() throws FileNotFoundException {
		msc.gameMapReading(continents, countries, boundries, domFile);
		flag = msc.isConnectedMap(boundries);
		assertTrue(flag);
	}

	/**
	 * This method is used for testing the file formats of map
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void validateFileFormatTest() throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + domFile;
		File file = new File(filePath);

		flag = mfv.validateFile(file);
		assertEquals(true, flag);
	}

	/**
	 * This method is used for testing the add continent scenarios
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void addContinentTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addContinent(continents, "Asiaa", "7");
		assertEquals("Continent added successfully", result);
	}
	
	/**
	 * This method is used for testing the add continent scenarios
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void addContinentTestFailure() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addContinent(continents, "Europe", "5");
		assertEquals("Continent already exists", result);
	}

	/**
	 * This method is used for testing the add country scenarios
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void addCountryTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addCountry(continents, countries, boundries, "Inde", "Asia");
		
		assertEquals("Country added successfully", result);
	}
	
	/**
	 * This method is used for testing the add country scenarios
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void addCountryTestFailure() throws FileNotFoundException {
		fileLoadTest();
	    String result = msc.addCountry(continents, countries, boundries, "India", "Asiia");
        assertEquals("Continent does not exists. Please add the Continent first", result);
	}

	/**
	 * This method is used for testing the add neighbour scenarios
	 * 
	 * @throws FileNotFoundException this throws exception if file not found
	 * @author garimadawar
	 */
	@Test
	public void addNeighbourTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addNeighbour(countries, boundries, "Ontario", "Peru");
		String re = msc.addNeighbour(countries, boundries, "Argentina", "Peruu");
		assertEquals("Neighbour country is added successfully", result);
		}
    

	/**
	 * This method is used for testing the add neighbour scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void addNeighbourTestFailure() throws FileNotFoundException {
		fileLoadTest();	
		String result = msc.addNeighbour(countries, boundries, "Argentina", "Peruu");
		assertEquals("Neighbour country does not exists. Please add the country and then neighbour", result);
	}
	
	/**
	 * This method is used for testing the remove continent scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeContinentTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeContinent(continents, boundries, countries, "Europe");
		assertEquals("Continents and countries under continent removed successfully", result);
	}
	
	/**
	 * This method is used for testing the remove continent scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeContinentTestFailure() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeContinent(continents, boundries, countries, "Asiass");
		assertEquals("Continent does not exist", result);
	}

	/**
	 * This method is used for testing the remove country scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeCountryTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeCountry(countries, boundries, "Egypt");
		assertEquals("Country and it's neighbours are removed successfully", result);
	}
	
	/**
	 * This method is used for testing the remove country scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeCountryTestFailure() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeCountry(countries, boundries, "Jappan");
		assertEquals("Country does not exists. Please add the Country first", result);
	}

	/**
	 * This method is used for testing the remove neighbour scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeNeighbourTestSuccess() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeNeighbour(countries, boundries, "China", "India");
		assertEquals("Neighbour country is removed successfully", result);
	}
	
	/**
	 * This method is used for testing the remove neighbour scenarios
	 * @author garimadawar
	 * @throws FileNotFoundException this throws exception if file not found
	 */
	@Test
	public void removeNeighbourTestFailure() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeNeighbour(countries, boundries, "China", "Indiaaa");
		assertEquals("Neighbour country does not exists", result);
	}
}
