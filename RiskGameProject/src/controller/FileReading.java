package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import util.CONSTANTS;

public class FileReading {
	
	String fileRead;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	String[] continentsDetails, countriesDetails, boundriesDetails;

	ArrayList<String> list;
	HashMap<String, Continents> continents = new HashMap<String, Continents>();
	HashMap<Integer, Countries> countries = new HashMap<Integer, Countries>();
	HashMap<String, ArrayList<String>> boundries = new HashMap<String, ArrayList<String>>();

	public void input() throws FileNotFoundException {

		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + CONSTANTS.FILE_NAME;
		System.out.println(filePath);
		File file = new File(filePath);
		Scanner sc = new Scanner(file);

		
		/*
		 * validate CONTINENT is VISIBLE --- (Remaining)
		 */
		
		
		while (sc.hasNext()) {
			String continents = sc.nextLine();
			if (continents.indexOf("[continents]") != -1) {
				System.out.println("continents found");
				break;
			}
		
		}
		while (sc.hasNext()) {

			continentsStarted = sc.nextLine();
			if (continentsStarted.length() > 0) {

				continentsDetails = continentsStarted.split(" ");
				Continents c1 = new Continents(continentsDetails[1], continentsDetails[2]);

				continents.put(continentsDetails[0], c1);
			} else
				break;
		}
		country = sc.nextLine();
		while (sc.hasNext()) {

			countriesStarted = sc.nextLine();

			if (countriesStarted.length() > 0) {

				countriesDetails = countriesStarted.split(" ");
				Countries c2 = new Countries(countriesDetails[0], countriesDetails[1], countriesDetails[2],
						countriesDetails[3], countriesDetails[4]);

				countries.put(Integer.parseInt(countriesDetails[0]), c2);
			} else
				break;

		}
		boundry = sc.nextLine();

		while (sc.hasNext()) {
			boundriesStarted = sc.nextLine();
			if (boundriesStarted.length() > 0) {
				boundriesDetails = boundriesStarted.split(" ");
				list = new ArrayList<String>();

				for (int i = 1; i < boundriesDetails.length; i++) {
					list.add(boundriesDetails[i]);
				}

				boundries.put(boundriesDetails[0], list);
			} else
				break;
		}

		/*
		 * for (String i : continents.keySet()) { System.out.println(i); Continents c =
		 * continents.get(i); System.out.println(c.getColour());
		 * 
		 * }
		 */

		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			System.out.println(i);
			System.out.println(c1.getCountry_Id() + c1.getCountryName() + c1.getCountryContinentNum()
					+ c1.getxCoordinate() + c1.getyCoordinate());
		}
		/*
		 * for (Entry<String, ArrayList<String>> i : boundries.entrySet()) {
		 * System.out.println(i); }
		 */
	}

}
