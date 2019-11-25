package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Continents;
import model.Countries;

public class MapFileAdapterController extends DominationReadWriteController {
	
	private ConquestReadWriteController crw;
	
	public void conquestToDomination(ConquestReadWriteController crw) {
		this.crw =crw;
	}
	
	public void dominationMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {
		
		crw.conquestMapReading(continents, countries, boundries, fileName);
	}

	public void writeDominationMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {
		crw.writeConquestMapFile(continents, countries, boundries, mapFile);
	}

}
