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
 * This class performs basic map testing operations.
 */
public class TestMapOperations {

	MapSelectionController msc;
	MapFormatValidation mfv;
	HashMap<Integer, Continents> continents;
	HashMap<Integer, Countries> countries;
	HashMap<Integer, ArrayList<Integer>> boundries;
	String fileName;
	boolean flag;

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

	@Test
	public void fileLoadTest() throws FileNotFoundException {

		String result = msc.gameMapReading(continents, countries, boundries, fileName);
		assertEquals("Success", result);
	}

	@Test
	public void isConnectedMapTest() throws FileNotFoundException {
		msc.gameMapReading(continents, countries, boundries, fileName);
		flag = msc.isConnectedMap(boundries);
		assertTrue(flag);
	}

	@Test
	public void validateFileFormatTest() throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);

		flag = mfv.validateFile(file);
		assertEquals(true, flag);
	}
	
	@Test
	public void addContinentTest() throws FileNotFoundException{
		fileLoadTest();
		String result = msc.addContinent(continents, "Asiaa", "7");
		String re = msc.addContinent(continents, "Europe", "5");
		assertEquals("Continent added successfully",result);
		assertEquals("Continent already exists",re);
		
		
	}
	
	@Test
	public void addCountryTest() throws FileNotFoundException{
		fileLoadTest();
		String result = msc.addCountry(continents, countries, boundries, "Inde", "Asia");
		String re = msc.addCountry(continents, countries, boundries, "India", "Asiia");
		assertEquals("Country added successfully",result);
		assertEquals("Continent does not exists. Please add the Continent first",re);
		
		
	}

	@Test
	public void addNeighbourTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.addNeighbour(countries, boundries, "Argentina", "Peru");
		String re = msc.addNeighbour(countries, boundries, "Argentina", "Peru");
		assertEquals("Neighbour country is already present in neighbour list",result);
		assertEquals("Neighbour country is already present in neighbour list",result);
		
		
	}
	
	@Test
	public void removeContinentTest() throws FileNotFoundException { 
		fileLoadTest();
		String result = msc.removeContinent(continents, boundries, countries, "Asiass");
		String re = msc.removeContinent(continents, boundries, countries, "Europe");
		assertEquals("Continents and countries under continent removed successfully",re);
		assertEquals("Continent does not exist",result);
	}
	
	@Test
	public void removeCountryTest() throws FileNotFoundException {
		fileLoadTest();
		String result = msc.removeCountry(countries, boundries,"Egypt" );
		String re = msc.removeCountry(countries, boundries, "Jappan");
		assertEquals("Country and it's neighbours are removed successfully",result);
		assertEquals("Country does not exists. Please add the Country first",re);
	}
	
    @Test
    public void removeNeighbourTest() throws FileNotFoundException {
    	fileLoadTest();
    	String result = msc.removeNeighbour(countries, boundries, "China", "Indiaaa");
    	String re = msc.removeNeighbour(countries, boundries, "China", "India");
    	assertEquals("Neighbour country does not exists",result);
    	assertEquals("Neighbour country is removed successfully",re);
    	
    	
    }
}

