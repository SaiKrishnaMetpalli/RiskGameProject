package controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

import model.Continents;
import model.Countries;

/**
 *All the map operations are performed in this controller
 */
public class MapSelectionController {
	/**
	 * creates a map from user input.
	 */
	public void createMap() {
		// TODO Auto-generated method stub
    	String continentName,continentControlValue;
		HashMap<Integer, Continents> continents = new HashMap<Integer, Continents>();
		HashMap<Integer, Countries> countries = new HashMap<Integer, Countries>();
		HashMap<Integer, ArrayList<Integer>> boundries = new HashMap<Integer, ArrayList<Integer>>();
		ArrayList<String> countryNameList = new ArrayList<String>() ;
		ArrayList<String> continentNameList = new ArrayList<String>();
		ArrayList<String> toRemove= new ArrayList<String>();
	    Scanner sc = new Scanner(System.in);
		System.out.println("Enter the number of continents");
		int numContinents = sc.nextInt();
		
		if(numContinents>=7) {
			System.out.println("Please enter correct value");
		}
		sc.nextLine();
		
		for(int i =0;i<numContinents;i++)
		{
			String ci = Integer.toString(i);
			System.out.println("Enter the name of continent");			
			continentName=sc.nextLine();        	
        	System.out.println("Enter the continent control value");
            continentControlValue = sc.nextLine();
    		Continents c =new Continents(continentName,continentControlValue,"white");  
    		continentNameList.add(continentName);
		    continents.put(i,c);
		    System.out.println("Enter the number of countries under "+continentName+" continent");
		    int numCountries = sc.nextInt();	
		    if(numCountries>=42)
		    {
				System.out.println("Please enter correct value");
			}
			
		    sc.nextLine();
			for(int j =0;j<numCountries;j++) 
			{
			String cp = Integer.toString(j);
				System.out.println("Enter the names of countries");				
				String countryName=sc.nextLine();
				countryNameList.add(countryName);
				Countries co = new Countries(cp,countryName,ci,"0","0");				
				countries.put(j,co);
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
        int counter =sc.nextInt();
        while (counter < countryNameList.size())
        {
            String countryName = countryNameList.toArray(new String[countryNameList.size()])[counter];
           
        System.out.println(" Assign neighbor for Country-->" + countryName);
        sc.nextLine();
		System.out.println("Assign neighnouring countries seperated by commas ");
		
		String neighbourCountries[]=sc.nextLine().split(",");
		ArrayList<Integer> neighbournodes =new ArrayList<Integer>();
		
		for (String neighbor : neighbourCountries) {
            if (neighbor.equals(countryName)) 
            {
                System.out.println("Country Can not be neighbor to itself Please enter correct value");
                countryNameList.add(countryName);
                if (!toRemove.contains(countryName))
                {
                    toRemove.add(countryName);
                }
            } 
            else if (!countryNameList.contains(neighbor))
            {
                System.out.println("One of the country you have entered is not part of CountryList Please enter correct value");
                countryNameList.add(countryName);
                if (!toRemove.contains(countryName)) 
                {
                    toRemove.add(countryName);
                }
            }
            else {
            	for(int i : countries.keySet()) {
            		Countries obj = countries.get(i);
            		{
            			  if(obj.getCountryName().equals(neighbor));
            			  
            			  neighbournodes.add(Integer.parseInt(obj.getCountry_Id()));
            		}
                   
            	}
            	boundries.put(counter+1, neighbournodes);
            	}
            
            }
            
		}
		for (int i : continents.keySet()) {
			Continents c = continents.get(i);
			System.out.println(c.getContinentName() + " " + c.getcontinentControlValue() + " " + c.getColour());

		}

		for (Integer i : countries.keySet()) {
			Countries c1 = countries.get(i);
			System.out.println(c1.getCountry_Id() + " " + c1.getCountryName() + " " + c1.getCountryContinentNum()
					+ " " + c1.getxCoordinate() + " " + c1.getyCoordinate());
		}
		
		for (Entry<Integer, ArrayList<Integer>> i : boundries.entrySet()) {
			System.out.println(i);
		}

	    	
}
	
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
