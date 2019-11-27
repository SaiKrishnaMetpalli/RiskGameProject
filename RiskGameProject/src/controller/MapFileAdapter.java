package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Continents;
import model.Countries;

/**
 * This class implements an adapter to adapt a conquest file to a domination file format
 * @author sakib
 *
 */
public class MapFileAdapter extends DominationReadWrite {
	
	private ConquestReadWrite crw;
	
	/**
	 * Constructor of adapter class with adaptee class object as parameter
	 * @param crw  it means conquest read write
	 */
	public MapFileAdapter(ConquestReadWrite crw) {
		this.crw =crw;
	}
	
	/**
	 * Overrides domination map reading method with conquest map reading capabilities
	 * @param continents  it contains the continents
	 * @param countries  it contains the countries
	 * @param  boundries  it contains the boundries
	 * @param  fileName it contains the file name
	 * @author sakib
	 */
	public String dominationMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {
		
		return crw.conquestMapReading(continents, countries, boundries, fileName);
	}
	
	/**
	 * Overrides domination map writing method with conquest map writing capabilities
	 * @param continents  it contains the continents
	 * @param countries  it contains the countries
	 * @param  boundries  it contains the boundries
	 * @param  mapFile it contains the map file
	 * @author sakib
	 */
	public String writeDominationMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {
		return crw.writeConquestMapFile(continents, countries, boundries, mapFile);
	}

}
