package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import util.CONSTANTS;
import controller.MapFormatValidation;

/**
 * This Class performs File reading operation
 *
 */

public class FileReading {

	MapFormatValidation mapValidate = new MapFormatValidation();
	boolean flag = false;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	String[] continentsDetails, countriesDetails, boundriesDetails;

	Scanner validateScanner;

	ArrayList<String> list;
	HashMap<String, Continents> continents = new HashMap<String, Continents>();
	HashMap<Integer, Countries> countries = new HashMap<Integer, Countries>();
	HashMap<String, ArrayList<String>> boundries = new HashMap<String, ArrayList<String>>();

	/**
	 * Method is used to parse the file and store in data Structure
	 * 
	 * @throws FileNotFoundException
	 */
	public void input() throws FileNotFoundException {

		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + CONSTANTS.FILE_NAME;
		File file = new File(filePath);
		Scanner textScanner = new Scanner(file);

		flag = mapValidate.validateFile(file);

		if (flag) {

			while (textScanner.hasNext()) {
				String continents = textScanner.nextLine();
				if (continents.equals("[continents]")) {
					break;
				}

			}
			while (textScanner.hasNext()) {

				continentsStarted = textScanner.nextLine();
				if (continentsStarted.length() > 0) {

					continentsDetails = continentsStarted.split(" ");
					Continents c1 = new Continents(continentsDetails[0], continentsDetails[1], continentsDetails[2]);

					continents.put(continentsDetails[0], c1);
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
					Countries c2 = new Countries(countriesDetails[0], countriesDetails[1], countriesDetails[2],
							countriesDetails[3], countriesDetails[4]);

					countries.put(Integer.parseInt(countriesDetails[0]), c2);
				} else
					break;

			}

			while (textScanner.hasNext()) {

				boundry = textScanner.nextLine();
				if (!country.equals("[countries]")) {
					continue;
				} else
					break;
			}

			while (textScanner.hasNext()) {
				boundriesStarted = textScanner.nextLine();
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

			for (String i : continents.keySet()) {
				Continents c = continents.get(i);
				System.out.println(c.getContinentName() + " " + c.getcontinentControlValue() + " " + c.getColour());

			}

			for (Integer i : countries.keySet()) {
				Countries c1 = countries.get(i);
				System.out.println(c1.getCountry_Id() + " " + c1.getCountryName() + " " + c1.getCountryContinentNum()
						+ " " + c1.getxCoordinate() + " " + c1.getyCoordinate());
			}

			for (Entry<String, ArrayList<String>> i : boundries.entrySet()) {
				System.out.println(i);
			}

		} else
			System.out.println("Map Invalid");

		textScanner.close();
	}

}
