package test;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Test;

import controller.MapFormatValidation;
import controller.MapSelectionController;
import model.Continents;
import model.Countries;

/**
 * This class performs basic map testing operations.
 */
public class TestMapOperations {

	MapSelectionController msc = new MapSelectionController();
	MapFormatValidation mfv = new MapFormatValidation();
	HashMap<Integer, Continents> continents = new HashMap<Integer, Continents>();
	HashMap<Integer, Countries> countries = new HashMap<Integer, Countries>();
	HashMap<Integer, ArrayList<Integer>> boundries = new HashMap<Integer, ArrayList<Integer>>();
	String fileName = "riskdemo.txt";
	boolean flag = false;
	
	@Test
	public void fileLoadTest() throws FileNotFoundException {

		String result = msc.gameMapReading(continents, countries, boundries, fileName);
		System.out.println(result);
		assertEquals("Success", result);
	}

	@Test
	public void isConnectedMapTest() throws FileNotFoundException {
		msc.gameMapReading(continents, countries, boundries, fileName);
		flag = msc.isConnectedMap(boundries);
		assertEquals(false, flag);
	}

	@Test
	public void validateFileFormatTest() throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);

		flag = mfv.validateFile(file);
		assertEquals(true, flag);
	}

}
