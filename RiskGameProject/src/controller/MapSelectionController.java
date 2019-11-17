package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

import model.Continents;
import model.Countries;

/**
 * All the map operations are performed in this controller
 */
public class MapSelectionController {

	Scanner validateScanner;

	ArrayList<Integer> list;
	MapFormatValidation mapValidate = new MapFormatValidation();
	boolean flag = false;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	String[] continentsDetails, countriesDetails, boundriesDetails;

	// These two variables are used for map connected and traversal
	private ArrayList<Integer> marked;
	private int count;
	private HashMap<Integer, ArrayList<Integer>> map;
	private int coid = 0, cid = 0;

	/**
	 * This method will read the uploaded file
	 * 
	 * @param continents contains the information about continents
	 * @param countries  contains the information about countries
	 * @param boundries  contains the adjacency list of countries
	 * @param fileName   it is map file which is read
	 * @return success if map is loaded successfully else failure
	 * @throws FileNotFoundException
	 */

	public String gameMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {
		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);
		Scanner textScanner = new Scanner(file);
		try {
			flag = mapValidate.validateFile(file);

			if (flag) {

				while (textScanner.hasNext()) {
					String continent = textScanner.nextLine();
					if (continent.equals("[continents]")) {
						break;
					}

				}
				int continentsCount = 0;
				while (textScanner.hasNext()) {
					continentsCount++;
					continentsStarted = textScanner.nextLine();
					if (continentsStarted.length() > 0) {

						continentsDetails = continentsStarted.split(" ");
						Continents c1 = new Continents(continentsDetails[0], continentsDetails[1],
								continentsDetails[2]);

						continents.put(continentsCount, c1);
					} else
						break;
				}

				while (textScanner.hasNext()) {

					country = textScanner.nextLine();
					if (!country.equals("[countries]")) {
						continue;
					} else
						break;
				}

				while (textScanner.hasNext()) {

					countriesStarted = textScanner.nextLine();

					if (countriesStarted.length() > 0) {

						countriesDetails = countriesStarted.split(" ");
						Countries c2 = new Countries(countriesDetails[1], Integer.parseInt(countriesDetails[2]),
								countriesDetails[3], countriesDetails[4]);

						countries.put(Integer.parseInt(countriesDetails[0]), c2);
					} else
						break;

				}

				while (textScanner.hasNext()) {

					boundry = textScanner.nextLine();
					if (!boundry.equals("[borders]")) {
						continue;
					} else
						break;
				}

				while (textScanner.hasNext()) {
					boundriesStarted = textScanner.nextLine();
					if (boundriesStarted.length() > 0) {
						boundriesDetails = boundriesStarted.split(" ");
						list = new ArrayList<Integer>();

						for (int i = 1; i < boundriesDetails.length; i++) {
							list.add(Integer.parseInt(boundriesDetails[i]));
						}

						boundries.put(Integer.parseInt(boundriesDetails[0]), list);
					} else
						break;
				}
				textScanner.close();
				return "Success";

			} else {
				textScanner.close();
				return "Failure";
			}

		} catch (Exception ex) {

			return "Failure";
		}

	}

	/**
	 * This method is used for adding the continents value
	 * 
	 * @param continents            this variable contains the continents list
	 * @param continentName         this variable contains name of the continent to
	 *                              be added
	 * @param continentControlValue this variable contains value of the control
	 *                              value to be added
	 */
	public String addContinent(HashMap<Integer, Continents> continents, String continentName,
			String continentControlValue) {
		if (continents.size() > 0) {
			for (int i : continents.keySet()) {
				coid = i;
			}
		} else {
			coid = 0;
		}
		for (int i : continents.keySet()) {
			String c = continents.get(i).getContinentName();
			if (c.equals(continentName)) {
				return "Continent already exists";
			}
		}
		Continents co = new Continents(continentName, continentControlValue, "0");
		continents.put(++coid, co);
		return "Continent added successfully";
	}

	/**
	 * This method is used for removing the continents value
	 * 
	 * @param continentName this variable contains name of the continent to be
	 *                      removed
	 * @param continents    this variable contains the continents list
	 * @param countries     this variable contains the countries list
	 * @param boundries     this variable contains the boundries list
	 */
	public String removeContinent(HashMap<Integer, Continents> continents,
			HashMap<Integer, ArrayList<Integer>> boundries, HashMap<Integer, Countries> countries,
			String continentName) {
		int removeState = 0;
		Iterator<Map.Entry<Integer, Continents>> iteratorContinents = continents.entrySet().iterator();
		Iterator<Map.Entry<Integer, Countries>> iteratorCountries = countries.entrySet().iterator();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> iteratorBoundries = boundries.entrySet().iterator();
		while (iteratorContinents.hasNext()) {
			Map.Entry<Integer, Continents> entryContinents = iteratorContinents.next();
			String c = entryContinents.getValue().getContinentName();
			if (c.equals(continentName)) {
				iteratorContinents.remove();
				removeState = 1;
				while (iteratorCountries.hasNext()) {
					Map.Entry<Integer, Countries> entryCountries = iteratorCountries.next();
					Countries key = entryCountries.getValue();
					if (key.getCountryContinentNum() == entryContinents.getKey()) {
						iteratorCountries.remove();
						removeState = 2;
						while (iteratorBoundries.hasNext()) {
							Map.Entry<Integer, ArrayList<Integer>> entryBoundries = iteratorBoundries.next();
							ArrayList<Integer> blist = entryBoundries.getValue();
							if (entryBoundries.getKey() == entryCountries.getKey()) {
								iteratorBoundries.remove();
								removeState = 3;
							} else {
								if (blist.contains(entryCountries.getKey())) {
									blist.remove(Integer.valueOf(entryCountries.getKey()));
								}
							}
						}
					}
				}
			}
		}
		if (removeState == 1) {
			return "Continent removed successfully";
		} else if (removeState == 2) {
			return "Continents and countries under continent removed successfully";
		} else if (removeState == 3) {
			return "Continents and countries under continent and neighbours under country removed successfully";
		} else {
			return "Continent does not exist";
		}

	}

	/**
	 * 
	 * /** This method is used for adding the countries value
	 * 
	 * @param countryName   this variable contains name of the country to be added
	 * @param continentName this variable contains name of the continent to which
	 *                      country is added
	 * @param continents    this variable contains the continents list
	 * @param countries     this variable contains the countries list
	 */
	public String addCountry(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String countryName, String continentName) {
		boolean conFlag = false, couFlag = false;
		;
		int continentNum = 0;
		if (countries.size() > 0) {
			for (int i : countries.keySet()) {
				cid = i;
			}
		} else {
			cid = 0;
		}
		for (int i : continents.keySet()) {
			String s = continents.get(i).getContinentName();
			if (s.equals(continentName)) {
				continentNum = i;
				conFlag = true;
				break;
			}
		}
		if (conFlag) {
			if (countries.size() > 0) {
				for (int j : countries.keySet()) {
					String c = countries.get(j).getCountryName();
					if (c.equals(countryName)) {
						return "Country already exists";
					} else {
						couFlag = true;
					}
				}
			} else {
				Countries ca = new Countries(countryName, continentNum, "0", "0");
				countries.put(++cid, ca);
				boundries.put(cid, new ArrayList<Integer>());
				return "Country added successfully";
			}

		} else {
			return "Continent does not exists. Please add the Continent first";
		}
		if (couFlag) {
			Countries ca = new Countries(countryName, continentNum, "0", "0");
			countries.put(++cid, ca);
			boundries.put(cid, new ArrayList<Integer>());
			return "Country added successfully";
		}
		return "Failure";
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param countries   this variable contains the continents list
	 * @param boundries   this variable contains the boundries list
	 * @param countryName this variable contains the country to be removed
	 */
	public String removeCountry(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName) {
		int removeState = 0;
		Iterator<Map.Entry<Integer, Countries>> iteratorCountries = countries.entrySet().iterator();
		Iterator<Map.Entry<Integer, ArrayList<Integer>>> iteratorBoundries = boundries.entrySet().iterator();

		while (iteratorCountries.hasNext()) {
			Map.Entry<Integer, Countries> entryCountries = iteratorCountries.next();
			Countries key = entryCountries.getValue();
			if (key.getCountryName().equals(countryName)) {
				iteratorCountries.remove();
				removeState = 1;
				while (iteratorBoundries.hasNext()) {
					Map.Entry<Integer, ArrayList<Integer>> entryBoundries = iteratorBoundries.next();
					ArrayList<Integer> blist = entryBoundries.getValue();
					if (entryBoundries.getKey() == entryCountries.getKey()) {
						iteratorBoundries.remove();
						removeState = 2;
					} else {
						if (blist.contains(entryCountries.getKey())) {
							blist.remove(Integer.valueOf(entryCountries.getKey()));
						}
					}
				}
			}
		}

		if (removeState == 1) {
			return "Country removed successfully";
		} else if (removeState == 2) {
			return "Country and it's neighbours are removed successfully";
		} else {
			return "Country does not exists. Please add the Country first";
		}
	}

	/**
	 * This method is used for adding the neighbor countries value
	 * 
	 * @param countryName          this variable contains name of the country
	 * @param neighbourCountryName this variable contains the name of neighboring
	 *                             country
	 * @param countries            this variable contains the countries list
	 * @param boundries            this variable contains the boundries list
	 */
	public String addNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {
		boolean couFlag = false, neighFlag = false;
		int couNum = 0, neighNum = 0;
		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				couNum = i;
				couFlag = true;
				break;
			}
		}
		if (couFlag) {
			for (int l : countries.keySet()) {
				String m = countries.get(l).getCountryName();
				if (neighbourCountryName.equals(m)) {
					neighNum = l;
					neighFlag = true;
					break;
				}
			}
			if (neighFlag) {
				if (boundries.size() > 0) {
					if (boundries.containsKey(couNum)) {
						if (boundries.get(couNum).contains(neighNum)) {
							return "Neighbour country is already present in neighbour list";
						} else {
							boundries.get(couNum).add(neighNum);
						}
					} else {
						ArrayList<Integer> li = new ArrayList<Integer>();
						li.add(neighNum);
						boundries.put(couNum, li);
					}
				} else {
					ArrayList<Integer> li = new ArrayList<Integer>();
					li.add(neighNum);
					boundries.put(couNum, li);
				}
				return "Neighbour country is added successfully";
			} else {
				return "Neighbour country does not exists. Please add the country and then neighbour";
			}
		} else {
			return "Country does not exists. Please add the country and then neighbour";
		}
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param boundries            this variable contains the boundaries list
	 * @param countries            this variable contains the countries list
	 * @param neighbourCountryName this variable contains the country to be removed
	 *                             from neighbour
	 * @param countryName          this variable is the name of the country which
	 *                             contains neighbor country name
	 */
	public String removeNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {

		boolean couFlag = false, neighFlag = false;
		int couNum = 0, neighNum = 0;
		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				couNum = i;
				couFlag = true;
				break;
			}
		}
		if (couFlag) {
			for (int l : countries.keySet()) {
				String m = countries.get(l).getCountryName();
				if (neighbourCountryName.equals(m)) {
					neighNum = l;
					neighFlag = true;
					break;
				}
			}
			if (neighFlag) {
				if (boundries.get(couNum).contains(neighNum)) {
					boundries.get(couNum).remove(Integer.valueOf(neighNum));
					return "Neighbour country is removed successfully";
				} else {
					return "Neighbour country is not present in neighbours list";
				}
			} else {
				return "Neighbour country does not exists";
			}
		} else {
			return "Country does not exists";
		}

	}

	/**
	 * method for write text file from data structure uses buffer reader and writer
	 * to write text file stores the file in the resource folder
	 * 
	 * @param takes the data structure from and file name
	 * @throws IOException as creating file
	 */
	public void writeGameMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + mapFile;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();
		bw.write("name " + mapFile + " Map");
		bw.write("\n");
		bw.write("\n");
		bw.write("[files]");
		bw.write("\n");
		bw.write("\n");
		bw.write("[continents]");
		bw.newLine();
		for (Integer i : continents.keySet()) {
			Continents c = continents.get(i);
			bw.write(c.getContinentName() + " " + c.getcontinentControlValue() + " " + c.getColour());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[countries]");
		bw.newLine();
		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			bw.write(i + " " + c1.getCountryName() + " " + c1.getCountryContinentNum() + " " + c1.getxCoordinate() + " "
					+ c1.getyCoordinate());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[borders]");
		bw.newLine();
		for (Integer s : boundries.keySet()) {
			ArrayList<Integer> tempal = new ArrayList<Integer>();
			String adjacency = "";
			tempal = boundries.get(s);
			for (Integer s1 : tempal) {
				adjacency += s1 + " ";
			}
			bw.write(s + " " + adjacency.trim());
			bw.newLine();
		}
		bw.write("\n");

		bw.close();
	}

	/**
	 * This method is used for checking map connectivity
	 * 
	 * @param boundries This variable contains the adjacency list of countries
	 * @return it returns true if map is connected; otherwise false
	 */
	public boolean isConnectedMap(HashMap<Integer, ArrayList<Integer>> boundries) {
		map = boundries;
		marked = new ArrayList<Integer>();
		count = 0;
		Map.Entry<Integer, ArrayList<Integer>> entry = boundries.entrySet().iterator().next();
		mapTraversal(entry.getKey());
		if (count == boundries.size()) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method is used for traversing the map
	 * 
	 * @param vertex this variable contains one of the vertex of map
	 */
	public void mapTraversal(int vertex) {
		count++;
		marked.add(vertex);
		for (int i : getNeighbours(vertex)) {
			if (!(marked.contains(i))) {
				mapTraversal(i);
			}
		}

	}

	/**
	 * This method gives you the neighbors of a vertex
	 * 
	 * @param v this variable contains vertex of the map
	 * @return this returns array list of neighbors if present; otherwise null
	 */
	public ArrayList<Integer> getNeighbours(int v) {
		return new ArrayList<Integer>(map.get(v));
	}

	/**
	 * This method creates empty file
	 * 
	 * @param fileName this variable contains the filename to be created
	 */
	public void createEmptyFile(String fileName) {
		try {
			String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
			File mapFile = new File(createPath);
			mapFile.createNewFile();
		} catch (Exception ex) {

		}

	}

}
