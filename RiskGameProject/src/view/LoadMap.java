package view;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Continents;
import model.Countries;
import util.CONSTANTS;

import java.util.Scanner;

public class LoadMap {

	String file_Read;
	String continents_Started, countries_Started, boundries_Started, boundry, country;
	String[] continents_Details, countries_Details, boundries_Details;

	ArrayList<String> list;
	HashMap<String, Continents> continents = new HashMap<String, Continents>();
	HashMap<String, Countries> countries = new HashMap <String, Countries>();
	HashMap<String, ArrayList<String>> boundries = new HashMap<String, ArrayList<String>>();

	public static void main(String args[]) throws IOException {
		LoadMap fr = new LoadMap();
		fr.input();

	}

	private void input() throws FileNotFoundException {

		String filePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + CONSTANTS.FILE_NAME ;
		System.out.println(filePath);
		File file = new File(filePath);
		Scanner sc = new Scanner(file);

		while (sc.hasNext()) {
			String continents = sc.nextLine();
			if (continents.indexOf("[continents]") != -1) {
				System.out.println("continents found");
				break;
			}

		}
		while (sc.hasNext()) {

			continents_Started = sc.nextLine();
			if (continents_Started.length() > 0) {
			
			continents_Details = continents_Started.split(" ");
			Continents c1 = new Continents(continents_Details[1], continents_Details[2]);

			continents.put(continents_Details[0], c1);
		}
			else 
				break;
		}
		country = sc.nextLine();
		while (sc.hasNext()) {

			countries_Started = sc.nextLine();

			if (countries_Started.length() > 0) {

			countries_Details = countries_Started.split(" ");
			Countries c2 = new Countries(countries_Details[1], countries_Details[2], countries_Details[3],
					countries_Details[4]);

			countries.put(countries_Details[0], c2);
		}
			else 
				break;
			
		}
		boundry = sc.nextLine();

		while (sc.hasNext()) {
			boundries_Started = sc.nextLine();
			if (boundries_Started.length() > 0) {
			boundries_Details = boundries_Started.split(" ");
			list = new ArrayList<String>();

			for (int i = 1; i < boundries_Details.length; i++) {
				list.add(boundries_Details[i]);
			}

			boundries.put(boundries_Details[0], list);
			}
			else
				break;
		}

		for (String i : continents.keySet()) {
			System.out.println(i);
			Continents c = continents.get(i);
			System.out.println(c.getColour());

		}

		for (String i : countries.keySet()) {
			System.out.println(i);
			Countries c1 = countries.get(i);
			System.out.println(c1.country_Name);
		}
		for (Entry<String, ArrayList<String>> i : boundries.entrySet()) {
			System.out.println(i);
		}
	}
}
