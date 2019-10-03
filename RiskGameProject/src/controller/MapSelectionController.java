package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import model.Continents;
import model.Countries;

/**
 *All the map operations are performed in this controller
 */
public class MapSelectionController {
	
	/**
	 * method for write text file from data structure
	 * @throws IOException
	 * as creating file
	 */
	public void writeContent(HashMap<String, Continents> continents,HashMap<Integer, Countries> countries,
								HashMap<String, ArrayList<String>> boundries) throws IOException {

		String createPath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + "CreatedMap.txt";
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


}
