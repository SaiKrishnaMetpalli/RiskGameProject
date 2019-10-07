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
 * All the map operations are performed in this controller
 */
public class MapSelectionController {

	Scanner validateScanner;

	ArrayList<Integer> list;
	MapFormatValidation mapValidate = new MapFormatValidation();
	boolean flag = false;
	String continentsStarted, countriesStarted, boundriesStarted, boundry, country;
	String[] continentsDetails, countriesDetails, boundriesDetails;

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
					if (continents.equals("[continents]")) {
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
					if (!country.equals("[countries]")) {
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
	 * creates a map from user input.
	 */
	public void createMap() {
		// TODO Auto-generated method stub
		String continentName, continentControlValue;
		HashMap<Integer, Continents> continents = new HashMap<Integer, Continents>();
		HashMap<Integer, Countries> countries = new HashMap<Integer, Countries>();
		HashMap<Integer, ArrayList<Integer>> boundries = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<String> countryNameList = new ArrayList<String>();
		ArrayList<String> continentNameList = new ArrayList<String>();
		ArrayList<String> toRemove = new ArrayList<String>();
		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of continents");
		int numContinents = sc.nextInt();

		if (numContinents >= 7) {
			System.out.println("Please enter correct value");
		}
		sc.nextLine();

		for (int i = 0; i < numContinents; i++) {
			String ci = Integer.toString(i);
			System.out.println("Enter the name of continent");
			continentName = sc.nextLine();
			System.out.println("Enter the continent control value");
			continentControlValue = sc.nextLine();
			Continents c = new Continents(continentName, continentControlValue, "white");
			continentNameList.add(continentName);
			continents.put(i, c);
			System.out.println("Enter the number of countries under " + continentName + " continent");
			int numCountries = sc.nextInt();
			if (numCountries >= 42) {
				System.out.println("Please enter correct value");
			}

			sc.nextLine();
			for (int j = 0; j < numCountries; j++) {
				String cp = Integer.toString(j);
				System.out.println("Enter the names of countries");
				String countryName = sc.nextLine();
				countryNameList.add(countryName);
				Countries co = new Countries(countryName, Integer.parseInt(ci), "0", "0");
				countries.put(j, co);
			}

		}
		System.out.println("Continent names are");
		for (String continent : continentNameList) {
			System.out.println(continent + " ");
		}
		System.out.println("");
		System.out.println("Country names are ");
		for (String country : countryNameList) {

			System.out.println(country + " ");
		}

		System.out.println("Select a country to add neighbouring countries");
		// Scanner sc = new Scanner(System.in);
		int counter = sc.nextInt();
		while (counter < countryNameList.size()) {
			String countryName = countryNameList.toArray(new String[countryNameList.size()])[counter];

			System.out.println(" Assign neighbor for Country-->" + countryName);
			sc.nextLine();
			System.out.println("Assign neighnouring countries seperated by commas ");

			String neighbourCountries[] = sc.nextLine().split(",");
			ArrayList<Integer> neighbournodes = new ArrayList<Integer>();

			for (String neighbor : neighbourCountries) {
				if (neighbor.equals(countryName)) {
					System.out.println("Country Can not be neighbor to itself Please enter correct value");
					countryNameList.add(countryName);
					if (!toRemove.contains(countryName)) {
						toRemove.add(countryName);
					}
				} else if (!countryNameList.contains(neighbor)) {
					System.out.println(
							"One of the country you have entered is not part of CountryList Please enter correct value");
					countryNameList.add(countryName);
					if (!toRemove.contains(countryName)) {
						toRemove.add(countryName);
					}
				} else {
					for (int i : countries.keySet()) {
						Countries obj = countries.get(i);
						{
							if (obj.getCountryName().equals(neighbor))
								;

							neighbournodes.add(i);
						}

					}
					boundries.put(counter + 1, neighbournodes);
				}

			}

		}


	}

	/**
	 * method for write text file from data structure
	 * 
	 * @throws IOException as creating file
	 */
	public void writeContent(HashMap<String, Continents> continents, HashMap<Integer, Countries> countries,
			HashMap<String, ArrayList<String>> boundries) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + "CreatedMap.txt";
		File mapfile = new File(createPath);
		FileWriter fw = new FileWriter(mapfile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();

		bw.write("\n");
		bw.write("[continents]");
		bw.newLine();
		for (String i : continents.keySet()) {
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
		for (String s : boundries.keySet()) {
			ArrayList<String> tempal = new ArrayList<String>();
			String adjacency = "";
			tempal = boundries.get(s);
			for (String s1 : tempal) {
				adjacency += s1 + " ";
			}
			bw.write(s + " " + adjacency.trim());
			bw.newLine();
		}
		bw.write("\n");

		bw.close();
	}

}
