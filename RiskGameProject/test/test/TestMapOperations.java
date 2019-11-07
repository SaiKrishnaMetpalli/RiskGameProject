package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
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

/**
 * This class is used for testing map methods
 */
public class TestMapOperations {

	MapSelectionController msc;
	MapFormatValidation mfv;
	HashMap<Integer, Continents> continents;
	HashMap<Integer, Countries> countries;
	HashMap<Integer, ArrayList<Integer>> boundries;
	String fileName;
	boolean flag;

	/**
	 * This method is used for initial setting up scenarios for each test case
	 * method
	 */
	@Before
	public void setUp() {
		msc = new MapSelectionController();
		mfv = new MapFormatValidation();
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		fileName = "world.map";
		flag = false;
	}

	/**
	 * This method is used for testing the game map load
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void fileLoadTest() throws FileNotFoundException {

		String result = msc.gameMapReading(continents, countries, boundries, fileName);
		assertEquals("Success", result);
	}

	/**
	 * This method is used for checking whether map is connected or not
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void isConnectedMapTest() throws FileNotFoundException {
		msc.gameMapReading(continents, countries, boundries, fileName);
		flag = msc.isConnectedMap(boundries);
		assertTrue(flag);
	}

	/**
	 * This method is used for testing the file formats of map
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void validateFileFormatTest() throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);

		flag = mfv.validateFile(file);
		assertEquals(true, flag);
	}

	/**
	 * This method is used for testing the add continent scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void addContinentTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addContinent(continents, "Asiaa", "7");
		String re = msc.addContinent(continents, "Europe", "5");
		assertEquals("Continent added successfully", result);
		assertEquals("Continent already exists", re);
	}

	/**
	 * This method is used for testing the add country scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void addCountryTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addCountry(continents, countries, boundries, "Inde", "Asia");
		String re = msc.addCountry(continents, countries, boundries, "India", "Asiia");
		assertEquals("Country added successfully", result);
		assertEquals("Continent does not exists. Please add the Continent first", re);

	}

	/**
	 * This method is used for testing the add neighbour scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void addNeighbourTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addNeighbour(countries, boundries, "Ontario", "Peru");
		String re = msc.addNeighbour(countries, boundries, "Argentina", "Peruu");
		assertEquals("Neighbour country is added successfully", result);
		assertEquals("Neighbour country does not exists. Please add the country and then neighbour", re);

	}

	/**
	 * This method is used for testing the remove continent scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void removeContinentTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeContinent(continents, boundries, countries, "Asiass");
		String re = msc.removeContinent(continents, boundries, countries, "Europe");
		assertEquals("Continents and countries under continent removed successfully", re);
		assertEquals("Continent does not exist", result);
	}

	/**
	 * This method is used for testing the remove country scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void removeCountryTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeCountry(countries, boundries, "Egypt");
		String re = msc.removeCountry(countries, boundries, "Jappan");
		assertEquals("Country and it's neighbours are removed successfully", result);
		assertEquals("Country does not exists. Please add the Country first", re);
	}

	/**
	 * This method is used for testing the remove neighbour scenarios
	 * 
	 * @throws FileNotFoundException
	 */
	@Test
	public void removeNeighbourTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeNeighbour(countries, boundries, "China", "Indiaaa");
		String re = msc.removeNeighbour(countries, boundries, "China", "India");
		assertEquals("Neighbour country does not exists", result);
		assertEquals("Neighbour country is removed successfully", re);

	}
}
