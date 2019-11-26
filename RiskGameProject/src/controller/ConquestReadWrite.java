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

public class ConquestReadWrite {

	String continentsStarted, countriesStarted, country;
	String[] continentsDetails, countriesDetails;
	CommonController cc = new CommonController();
	HashMap<Integer, ArrayList<String>> boundaryNames = new HashMap<Integer, ArrayList<String>>();

	public String conquestMapReading(HashMap<Integer, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<Integer, ArrayList<Integer>> boundries, String fileName) throws FileNotFoundException {

		String readPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File file = new File(readPath);
		Scanner sc = new Scanner(file);
		while (sc.hasNext()) {
			String continent = sc.nextLine();
			if (continent.equals("[Continents]")) {
				break;
			}

		}
		int continentsCount = 0;
		while (sc.hasNext()) {
			continentsCount++;
			continentsStarted = sc.nextLine();
			if (continentsStarted.length() > 0) {

				continentsDetails = continentsStarted.split("=");
				Continents c1 = new Continents(continentsDetails[0], continentsDetails[1], "null");
				continents.put(continentsCount, c1);
			} else
				break;
		}
		while (sc.hasNext()) {
			country = sc.nextLine();
			if (!country.equals("[Territories]")) {
				continue;
			} else
				break;
		}
		int countriesCount = 0;
		while (sc.hasNext()) {
			countriesCount++;
			countriesStarted = sc.nextLine();

			if (countriesStarted.length() > 0) {

				countriesDetails = countriesStarted.split(",");
				Countries c2 = new Countries(countriesDetails[0], cc.getContinentNum(continents, countriesDetails[3]),
						countriesDetails[1], countriesDetails[2]);
				countries.put(countriesCount, c2);
				ArrayList<String> temp = new ArrayList<String>();
				for (int i = 4; i < countriesDetails.length; i++) {
					temp.add(countriesDetails[i]);
				}
				boundaryNames.put(countriesCount, temp);

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
		sc.close();
		return "Success";
	}

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
			String s1 = c1.getCountryName() + "," + c1.getxCoordinate() + "," + c1.getyCoordinate() + ","
					+ cc.getContinentByCountryName(continents, countries, c1.getCountryName()).getContinentName() + ",";
			String s2 = "";
			ArrayList<Integer> blist = boundries.get(i);
			for (int bi : blist) {
				s2 += cc.getCountryNameByNum(countries, bi) + ",";
			}
			s2 = s2.substring(0, s2.length() - 1);
			bw.write(s1 + s2);
			bw.newLine();
		}

		bw.write("\n");

		bw.close();
		return "Success";
	}
}
