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
 * This class contains conquest read write attributes
 */
public class ConquestReadWrite {

	String continentsStarted, countriesStarted, country;
	String[] continentsDetails, countriesDetails;
	CommonController cc = new CommonController();
	HashMap<Integer, ArrayList<String>> boundaryNames = new HashMap<Integer, ArrayList<String>>();
	
	/**
	 * This function is used for conquest map reading
	 * @param continents  it contains the continents
	 * @param countries it contauins the countries
	 * @param boundries  it contains the boundries
	 * @param fileName  it contains the file name
	 * @return  it returns string message
	 * @throws FileNotFoundException
	 * @author sakib
	 */
	public String conquestMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {

		String readPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(readPath);
		Scanner textScanner = new Scanner(file);
		while (textScanner.hasNext()) {
			String continent = textScanner.nextLine();
			if (continent.equals("[Continents]")) {
				break;
			}

		}
		int continentsCount = 0;
		while (textScanner.hasNext()) {
			continentsCount++;
			continentsStarted = textScanner.nextLine();
			if (continentsStarted.length() > 0) {

				continentsDetails = continentsStarted.split("=");
				Continents c1 = new Continents(continentsDetails[0], continentsDetails[1], "null");
				continents.put(continentsCount, c1);
			} else
				break;
		}
		while (textScanner.hasNext()) {
			country = textScanner.nextLine();
			if (!country.equals("[Territories]")) {
				continue;
			} else
				break;
		}
		int countriesCount = 0;
		while (textScanner.hasNext()) {
			countriesCount++;
			countriesStarted = textScanner.nextLine();

			if (countriesStarted.length() > 0) {
				countriesDetails = countriesStarted.split(",");
				Countries c2 = new Countries(countriesDetails[0], cc.getContinentNum(continents, countriesDetails[3]),
						countriesDetails[1], countriesDetails[2]);
				countries.put(countriesCount, c2);
				ArrayList<String> bouNameList = new ArrayList<String>();
				for (int i = 4; i < countriesDetails.length; i++) {
					bouNameList.add(countriesDetails[i]);
				}
				boundaryNames.put(countriesCount, bouNameList);

			} else
				break;

		}
		for (int i : boundaryNames.keySet()) {
			ArrayList<String> boulist = boundaryNames.get(i);
			ArrayList<Integer> listNum = new ArrayList<Integer>();
			for (String s : boulist) {
				listNum.add(cc.getCountryNumberByName(countries, s));
			}
			boundries.put(i, listNum);
		}
		textScanner.close();
		return "Success";
	}
	
	/**
	 * This function contains write conquest map file attributes
	 * @param continents  it contains the continents
	 * @param countries  it contains the countries
	 * @param boundries  it contains the boundries
	 * @param mapFile  it contains the map file
	 * @return  it returns the string message
	 * @throws IOException\
	 * @author sakib
	 */
	public String writeConquestMapFile(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String mapFile) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + mapFile;
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, false);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();
		bw.write("[Map]");
		bw.write("\n");
		bw.write("\n");
		bw.write("[Continents]");
		bw.newLine();
		for (Integer i : continents.keySet()) {
			Continents c = continents.get(i);
			bw.write(c.getContinentName() + "=" + c.getcontinentControlValue());
			bw.newLine();
		}

		bw.write("\n");
		bw.write("[Territories]");
		bw.newLine();
		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			String territoryStrOne = c1.getCountryName() + "," + c1.getxCoordinate() + "," + c1.getyCoordinate() + ","
					+ cc.getContinentByCountryName(continents, countries, c1.getCountryName()).getContinentName() + ",";
			String territoryStrTwo = "";
			ArrayList<Integer> blist = boundries.get(i);
			for (int bi : blist) {
				territoryStrTwo += cc.getCountryNameByNum(countries, bi) + ",";
			}
			territoryStrTwo = territoryStrTwo.substring(0, territoryStrTwo.length() - 1);
			bw.write(territoryStrOne + territoryStrTwo);
			bw.newLine();
		}

		bw.write("\n");

		
		bw.close();
		return "Success";
	}
}
