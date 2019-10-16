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

}
