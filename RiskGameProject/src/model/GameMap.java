package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class contains the map attributes
 */
public class GameMap {
	public HashMap<Integer, Continents> continents;
	public HashMap<Integer, Countries> countries;
	public HashMap<Integer, ArrayList<Integer>> boundries;

	/**
	 * @Default Constructor This method initiates the variables
	 */
	public GameMap() {
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
	}
}
