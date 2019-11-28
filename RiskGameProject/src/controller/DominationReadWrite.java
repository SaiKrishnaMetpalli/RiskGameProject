package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import model.Continents;
import model.Countries;

/**
 * This class contains read/wirte methods for domination files
 * @author sakib
 *
 */
public class DominationReadWrite {
	/**
	 *
	 * continentsStarted is the string where continents start in the map
	 * countriesStrated is the String where countries start in a map
	 * boundriesStarted is the string where boundries info starts in map
	 * boundry is the string of first boundry
	 * country is the name of the country
	 *
	 */
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	
	/**
	 * strings arrays to store the split lines
	 */
	String[] continentsDetails, countriesDetails, boundriesDetails;
	
	/**
	 * This method will read a domination map file and store extracted information in respective hashmaps
	 * @param continents
	 * @param countries
	 * @param boundries
	 * @param fileName
	 * @return success or failure message 
	 * @throws FileNotFoundException
	 * @author sakib
	 */
	public String dominationMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {

		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(filePath);
		Scanner textScanner = new Scanner(file);

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
				Continents c1 = new Continents(continentsDetails[0], continentsDetails[1], continentsDetails[2]);

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
				ArrayList<Integer> list = new ArrayList<Integer>();

				for (int i = 1; i < boundriesDetails.length; i++) {
					list.add(Integer.parseInt(boundriesDetails[i]));
				}

				boundries.put(Integer.parseInt(boundriesDetails[0]), list);
			} else
				break;
		}
		textScanner.close();
		return "Success";
	}
	
	/**
	 * This method will write a domination map file from informations in respective hashmaps 
	 * @param continents
	 * @param countries
	 * @param boundries
	 * @param mapFile
	 * @return success and failure message 
	 * @throws IOException
	 * @author sakib
	 */
	public String writeDominationMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
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
		return "Success";
	}
}
