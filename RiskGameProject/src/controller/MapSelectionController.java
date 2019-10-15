package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
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
	
	//These two variables are used for map connected and traversal
	private boolean[] marked;
	private int count;
	private HashMap<Integer, ArrayList<Integer>> map;

	/**
	 * This method will read the uploaded file
	 * 
	 * @param continents
	 * @param countries
	 * @param boundries
	 * @param fileName
	 * @return
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

	static int coid = 0, cid = 0;

	/**
	 * This method is used for adding the continents value
	 * 
	 * @param continents  
	 * @param continentName         this variable contains name of the continent
	 * @param continentControlValue this variable contains value of the control
	 *                              value
	 */
	public String addContinent(HashMap<Integer, Continents> continents, String continentName,
			String continentControlValue) {
		for (int i : continents.keySet()) {
			String c = continents.get(i).getContinentName();
			if (!c.equals(continentName)) {
				Continents co = new Continents(continentName, continentControlValue, "0");
				continents.put(coid++, co);
				return "Success! Continent Added";
			} 
		}
		return "Continent already exists";
	}

	/**
	 * This method is used for removing the continents value
	 * 
	 * @param continentName this variable contains name of the continent
	 * @param continents
	 * @param countries
	 * @param boundries
	 */
	public String removeContinent(HashMap<Integer, Continents> continents,
			HashMap<Integer, ArrayList<Integer>> boundries, HashMap<Integer, Countries> countries,
			String continentName) {
		for (int i : continents.keySet()) {
			String c = continents.get(i).getContinentName();
			if (c.equals(continentName)) {
				continents.remove(i);
				for (int j : countries.keySet()) {
					Countries key = countries.get(j);
					if (key.getCountryContinentNum() == i) {
						countries.remove(i);
						for (int n : boundries.keySet()) {
							ArrayList<Integer> blist = boundries.get(n);
							if (n == j) {
								boundries.remove(n);
								return "Continent Removed";
							}
							for (int k : blist) {
								if (k == j) {
									blist.remove(k);
									return "Continent Removed";
								}
							}
						}
					}
				}
			}
		}
		return "Continent Not Exist";
	}

	/**
	 * 
	 * /** This method is used for adding the countries value
	 * 
	 * @param countryName   this variable contains name of the country
	 * @param continentName this variable contains name of the continent
	 * @param continents
	 * @param countries
	 */
	public String addCountry(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			String countryName, String continentName) {
		for (int i : continents.keySet()) {
			String s = continents.get(i).getContinentName();
			if (s.equals(continentName)) {
				for (int j : countries.keySet()) {
					String c = countries.get(j).getCountryName();
					if (!c.equals(countryName)) {
						Countries ca = new Countries(countryName, i, "0", "0");
						countries.put(cid++, ca);
						return "Success";
					} else {
						return "Country already exists";
					}
				}
			}
		}
		return "Continent Not Exists Add The Continent First";
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param countries
	 * @param boundries
	 * @param countryName this variable contains country to be removed
	 */
	public String removeCountry(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName) {
		for (int j : countries.keySet()) {
			String c = countries.get(j).getCountryName();
			if (c.equals(countryName)) {
				countries.remove(j);
				for (int n : boundries.keySet()) {
					ArrayList<Integer> blist = boundries.get(n);
					if (n == j) {
						boundries.remove(n);
						return "Country Removed";
					}
					for (int k : blist) {
						if (k == j) {
							blist.remove(k);
							return "Country Removed";
						}
					}
				}
			}
		}
		return "Country not exists Add the Country First";
	}

	/**
	 * This method is used for adding the neighbor countries value
	 * 
	 * @param countryName          this variable contains name of the country
	 * @param neighbourCountryName this variable contains value of the adjacent
	 *                             countries
	 * @param countries
	 * @param boundries
	 */
	public String addNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {

		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				for (int l : countries.keySet()) {
					String m = countries.get(l).getCountryName();
					if (neighbourCountryName.equals(m)) {
						if (!boundries.containsKey(i)) {
							ArrayList<Integer> li = new ArrayList<Integer>();
							boundries.put(i, li);
							return "Neighbour Added";
						} else {
							boundries.get(i).add(l);
							return "Neighbour Added";
						}
					} else {
						return "Neighbour Country Not Exists";
					}
				}
				return "Country Not Exists";
			}
		}
		return "Neighbour Country Already Exists";
	}

	/**
	 * This method is used for removing country value
	 * 
	 * @param boundries
	 * @param countries            
	 * @param neighbourCountryName this variable contains the country to be removed
	 * @param countryName          this variable is the name of the country which contains neighbor country name
	 */
	public String removeNeighbour(HashMap<Integer, Countries> countries, HashMap<Integer, ArrayList<Integer>> boundries,
			String countryName, String neighbourCountryName) {

		for (int i : countries.keySet()) {
			String s = countries.get(i).getCountryName();
			if (countryName.equals(s)) {
				for (int j : countries.keySet()) {
					String m = countries.get(j).getCountryName();
					if (m.equals(neighbourCountryName)) {
						for (int k : boundries.keySet()) {
							ArrayList<Integer> blist = boundries.get(k);
							for (int n : blist) {
								if (n == k) {
									blist.remove(n);
									return "Success Neighbour Country";
								}
							}

						}
					}
				}
			}
			return "Country not exists";
		}
		return "Neighbour Country not exists";
	}
	
	/**
	 * method for write text file from data structure
	 * uses buffer reader and writer to write text file
	 * stores the file in the resource folder
	 * @param takes the data structure and file name
	 * @throws IOException as creating file
	 */
		public void writeGameMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + mapFile;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();
		bw.write("name "+mapFile +" Map");
		bw.write("\n");
		bw.write("[files]");
		bw.write("\n");
		bw.write("[continents]");
		bw.newLine();
		for (Integer i : continents.keySet()) {
			Continents c = continents.get(i);
			bw.write(i + " " + c.getcontinentControlValue() + " " + c.getColour());
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
	 * @param boundries this variable contains the adjacency list of countries
	 * @return it returns true if map is connected; otherwise false
	 */
	public boolean isConnectedMap(HashMap<Integer, ArrayList<Integer>> boundries)
	{
		map=boundries;
		marked=new boolean[boundries.size()];
		count=0;
		Map.Entry<Integer, ArrayList<Integer>> entry=boundries.entrySet().iterator().next();
		mapTraversal(entry.getKey());
		if(count==boundries.size())
		{
			return true;
		}
		else
		{
			return false;
		}		
	}
	
	/**
	 * This method is used for traversing the map
	 * @param vertex this variable contains one of the vertex of map
	 */
	public void mapTraversal(int vertex)
	{
		count++;
		marked[vertex-1]=true;
		for(int i:getNeighbours(vertex))
		{
			if(!marked[i-1])
			{
				mapTraversal(i);
			}
		}
		
	}
	
	/**
	 * This method gives you the neighbors of a vertex
	 * @param v this variable contains vertex of the map
	 * @return this returns array list of neighbors if present; otherwise null
	 */
	public ArrayList<Integer> getNeighbours(int v)
	{
		if(v>map.size())
		{
			return null;
		}
		return new ArrayList<Integer>(map.get(v));
	}


}
