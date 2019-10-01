package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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
	 * method for write text file from data structure
	 * @throws IOException
	 * as creating file
	 */
	public void writeContent() throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + "createdmap.txt";
		File mapfile=new File(createPath);
		FileWriter fw = new FileWriter(mapfile, true);
		BufferedWriter bw = new BufferedWriter(fw);
		mapfile.createNewFile();
		
		bw.write("\n");
		bw.write("[continents]");
		bw.newLine();
		for (String i : continents.keySet()) {
			Continents c = continents.get(i);
			bw.write(i+" "+c.getcontinentControlValue()+ " "+c.getColour());
			bw.newLine();
		}
		
		
		bw.write("\n");
		bw.write("[countries]");
		bw.newLine();
		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			bw.write(i+" "+c1.getCountryName()+ " "+c1.getCountryContinentNum()+" "
							+c1.getxCoordinate()+" "+c1.getyCoordinate());
			bw.newLine();
		}
		
		bw.write("\n");
		bw.write("[borders]");
		bw.newLine();
		for(String s : boundries.keySet()) {
			ArrayList<String> tempal = new ArrayList<String>();
			String adjacency = "";
			tempal= boundries.get(s);
			for(String s1 : tempal) {
				adjacency+=s1+ " ";
			}
			bw.write(s+" "+ adjacency.trim());
			bw.newLine();
		}
		bw.write("\n");
		
		
		bw.close();
	}


	/**
	 * Method is used to parse the file and store in data Structure
	 * 
	 * @throws FileNotFoundException
	 * as reading file
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
