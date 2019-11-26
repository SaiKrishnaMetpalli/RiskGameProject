package controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import model.Continents;
import model.Countries;

public class MapFileAdapter extends DominationReadWrite {
	
	private ConquestReadWrite crw;
	
	public MapFileAdapter(ConquestReadWrite crw) {
		this.crw =crw;
	}
	
	public String dominationMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {
		
		return crw.conquestMapReading(continents, countries, boundries, fileName);
	}

	public String writeDominationMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {
		return crw.writeConquestMapFile(continents, countries, boundries, mapFile);
	}

}
