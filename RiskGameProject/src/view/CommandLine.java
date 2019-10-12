package view;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import model.GameMap;
import controller.MapSelectionController;
import controller.PlayerSelectionController;

/**
 * This class gives the command line interface for user
 * Every user input is retrieved and navigated to functionality
 */
public class CommandLine {
	
	String input;
	Scanner sc;
	boolean addToCommands;
	ArrayList<String> inputCommandsList;
	GameMap gm;
	ArrayList<String> players;
	PlayerSelectionController psc;
	MapSelectionController msc;
	
	/**
	 * Default constructor
	 * To create the variable objects 
	 */
	public CommandLine()
	{
		sc=new Scanner(System.in);
		inputCommandsList=new ArrayList<String>();
		gm=new GameMap();
		players = new ArrayList<String>();
		psc=new PlayerSelectionController();
		msc=new MapSelectionController();
	}
	
	/**
	 * This method is used for retrieving user input commands and navigating to the functionality
	 */
	public void commandLine()
	{		
		System.out.println("\nEnter the commands");
		input=sc.nextLine();
		String[] inputCommand=input.split(" ");
		if(inputCommand.length>0)
		{
			switch(inputCommand[0])
			{
			case "editcontinent":
				if(inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap"))					
				{
					if(inputCommand.length>1)
					{
						int i=1;
						while(i<inputCommand.length)
						{
							if(inputCommand[i].equals("-add") && (i+2<inputCommand.length))
							{							
								System.out.println(inputCommand[i+1]);
								System.out.println(inputCommand[i+2]);
								i=i+3;
								addToCommands=true;
								//Call the method where we can add the continent and control value
							}
							else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
							{
								System.out.println(inputCommand[i+1]);
								i=i+2;
								addToCommands=true;
								//Call the method where we can remove the continent
							}
							else
							{
								System.out.println("\neditcontinent command format is incorrect");
								addToCommands=false;
								break;
							}
						}						
					}
					else
					{
						System.out.println("\neditcontinent command format is incorrect");
						addToCommands=false;						
					}
				}
				else
				{
					System.out.println("\neditcontinent command cannot be loaded before loadmap or editmap command");
					addToCommands=false;					
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "editcountry":
				if(inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap"))					
				{
					if(inputCommand.length>1)
					{
						int i=1;						
						while(i<inputCommand.length)
						{
							if(inputCommand[i].equals("-add") && (i+2<inputCommand.length))
							{							
								System.out.println(inputCommand[i+1]);
								System.out.println(inputCommand[i+2]);
								i=i+3;
								//Call the method where we can add the country
								addToCommands=true;
							}
							else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
							{
								System.out.println(inputCommand[i+1]);
								i=i+2;
								//Call the method where we can remove the country
								addToCommands=true;
							}
							else
							{
								System.out.println("\neditcountry command format is incorrect");
								addToCommands=false;
							}
						}			
					}
					else
					{
						System.out.println("\neditcountry command format is incorrect");
						addToCommands=false;
					}
				}
				else
				{
					System.out.println("\neditcountry command cannot be loaded before loadmap or editmap command");
					addToCommands=false;
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "editneighbor":
				if(inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap"))					
				{
					if(inputCommand.length>1)
					{
						int i=1;
						while(i<inputCommand.length)
						{
							if(inputCommand[i].equals("-add") && (i+2<inputCommand.length))
							{							
								System.out.println(inputCommand[i+1]);
								System.out.println(inputCommand[i+2]);
								i=i+3;
								//Call the method where we can add the neighbors
								addToCommands=true;
							}
							else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
							{
								System.out.println(inputCommand[i+1]);
								i=i+2;
								//Call the method where we can remove the neighbors
								addToCommands=true;
							}
							else
							{
								System.out.println("\neditneighbor command format is incorrect");
								addToCommands=false;
								break;
							}
						}			
					}
					else
					{
						System.out.println("\neditneighbor command format is incorrect");
						addToCommands=false;
					}
				}
				else
				{
					System.out.println("\neditneighbor command cannot be loaded before loadmap or editmap command");
					addToCommands=false;
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "showmap":
				if(inputCommand.length>2)
				{
					System.out.println("showmap");
				}
				commandLine();
				break;
			case "savemap":
				commandLine();
				break;
			case "editmap":
				if(inputCommand.length>1)
				{
					if(checkFileExist(inputCommand[1]))
					{
						try 
						{
							String result=msc.gameMapReading(gm.continents,gm.countries,gm.boundries,inputCommand[1]);
							if(result.equals("Success"))
							{
								System.out.println("File uploaded successfully");								
							}
							else
							{
								System.out.println("File not uploaded. There are format issues in file. Please upload again");
							}
							
						}
						catch(Exception ex)
						{
							System.out.println("Error Occurred. Please try again");
						}
					}
					else
					{
						System.out.println("\n"+inputCommand[1]+" file does not exist. Please create a new map file");
					}
					addToCommands=true;
				}
				else
				{
					System.out.println("\neditmap command format is incorrect");
					addToCommands=false;
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "validatemap":
				if(gm.boundries.size()>0)
				{
					boolean result=msc.isConnectedMap(gm.boundries);
					if(result)
					{
						System.out.println("Map is connected");
					}
					else
					{
						System.out.println("Map is not connected");
					}
				}
				else
				{
					System.out.println("\nMap has not been loaded. Please load the map and validate");
					addToCommands=false;
				}
				
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "loadmap":
				if(inputCommand.length>1)
				{					
					if(checkFileExist(inputCommand[1]))
					{
						try 
						{
							String result=msc.gameMapReading(gm.continents,gm.countries,gm.boundries,inputCommand[1]);
							if(result.equals("Success"))
							{
								System.out.println("File uploaded successfully");								
								addToCommands=true;
							}
							else
							{
								System.out.println("File not uploaded. There are format issues in file. Please upload again");
								addToCommands=false;
							}
							
						}
						catch(Exception ex)
						{
							System.out.println("Error Occurred. Please try again");
							addToCommands=false;
						}						
					}
					else
					{
						System.out.println("\n"+inputCommand[1]+" file does not exist");
						addToCommands=false;
					}					
				}
				else
				{
					System.out.println("\nloadmap command format is incorrect");
					addToCommands=false;
				}
				if(addToCommands)
				{
					if(!inputCommandsList.contains(inputCommand[0]))
					{
						inputCommandsList.add(inputCommand[0]);
					}					
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "gameplayer":
				if(inputCommand.length>1)
				{
					int i=1;
					while(i<inputCommand.length)
					{
						String result;
						if(inputCommand[i].equals("-add") && (i+1<inputCommand.length))
						{							
							result=psc.addPlayer(players, inputCommand[i+1]);
							if(result.equals("Success"))
							{
								System.out.println("\n"+inputCommand[i+1]+" player added successfully");
								System.out.println(players);
								addToCommands=true;
							}
							else
							{
								System.out.println("\n"+inputCommand[i+1]+" player already exists");
								addToCommands=false;
							}							
						}
						else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
						{
							result=psc.removePlayer(players, inputCommand[i+1]);
							if(result.equals("Success"))
							{
								System.out.println("\n"+inputCommand[i+1]+" player removed successfully");
								System.out.println(players);
								addToCommands=true;
							}
							else
							{
								System.out.println("\n"+inputCommand[i+1]+" player does not exists");
								addToCommands=false;
							}
						}
						else
						{
							System.out.println("\ngameplayer command format is incorrect");
							addToCommands=false;
							break;
						}
						i=i+2;
					}
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "placearmy":
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "placeall":
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "reinforce":
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "fortify":
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "endgame":
				System.out.println("\nThe game is ended");
				System.exit(0);
				break;
			default:
				System.out.println("\nThe command does not exist");
				commandLine();
				break;
			}

		}
	}
	
	/**
	 * This method is used for adding the input commands one by one as the user is entering 
	 * @param input this variable gives true/false for adding to inputCommandsList 
	 * @param command this variable has command name that is used for adding to inputCommandsList
	 */
	public void addInputCommandList(boolean input,String command)
	{
		if(input)
		{
			if(!inputCommandsList.contains(command)) //checks if the command is already present or not
			{
				inputCommandsList.add(command);
			}					
		}
	}
	
	/**
	 * This method is used for checking if file exists or not 
	 * @param fileName this variable gives the filename which user entered
	 * @return returns gives true/false
	 */
	public boolean checkFileExist(String fileName)
	{
		String mapFilePath=Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File mapFile=new File(mapFilePath);
		boolean mapExists=mapFile.exists();
		return mapExists;
	}

}
