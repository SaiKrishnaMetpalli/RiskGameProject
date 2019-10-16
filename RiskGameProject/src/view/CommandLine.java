package view;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import model.Continents;
import model.Countries;
import model.GameMap;
import model.Player;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import util.CONSTANTS;

/**
 * This class gives the command line interface for user
 * Every user input is retrieved and navigated to functionality
 */
public class CommandLine {
	
	String input;
	String result;
	Scanner sc;
	boolean addToCommands;
	ArrayList<String> inputCommandsList;
	GameMap gm;
	ArrayList<String> players;
	PlayerSelectionController psc;
	MapSelectionController msc;
	HashMap<String , Player> listOfPlayers;
	CONSTANTS cons;
	
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
		listOfPlayers = new HashMap<String, Player>();
		cons=new CONSTANTS();
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
								result=msc.addContinent(gm.continents, inputCommand[i+1], inputCommand[i+2]);
								if(result.contains("success"))
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+inputCommand[i+2]+" "+result);
									addToCommands=false;
								}
								i=i+3;								
								
							}
							else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
							{
								System.out.println(inputCommand[i+1]);
								result=msc.removeContinent(gm.continents, gm.boundries, gm.countries, inputCommand[i+1]);
								if(result.contains("success"))
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=false;
								}
								i=i+2;								
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
								result=msc.addCountry(gm.continents, gm.countries, inputCommand[i+1], inputCommand[i+2]);
								if(result.contains("success"))
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+inputCommand[i+2]+" "+result);
									addToCommands=false;
								}
								i=i+3;
							}
							else if(inputCommand[i].equals("-remove") && (i+1<inputCommand.length))
							{
								result=msc.removeCountry(gm.countries, gm.boundries, inputCommand[i+1]);
								if(result.contains("success"))
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+result);
									addToCommands=false;
								}
								i=i+2;								
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
								result=msc.addNeighbour(gm.countries, gm.boundries, inputCommand[i+1], inputCommand[i+2]);
								if(result.contains("success"))
								{
									String result2=msc.addNeighbour(gm.countries, gm.boundries, inputCommand[i+2], inputCommand[i+1]);
									System.out.println("\n "+inputCommand[i+2]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+inputCommand[i+2]+" "+result);
									addToCommands=false;
								}
								i=i+3;								
							}
							else if(inputCommand[i].equals("-remove") && (i+2<inputCommand.length))
							{
								result=msc.removeNeighbour(gm.countries, gm.boundries, inputCommand[i+1], inputCommand[i+2]);
								if(result.contains("success"))
								{
									String result2=msc.removeNeighbour(gm.countries, gm.boundries, inputCommand[i+2], inputCommand[i+1]);
									System.out.println("\n "+inputCommand[i+2]+" "+result);
									addToCommands=true;
								}
								else
								{
									System.out.println("\n "+inputCommand[i+1]+" "+inputCommand[i+2]+" "+result);
									addToCommands=false;
								}
								i=i+3;
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
				if((gm.countries.size()>0) && (gm.continents.size()>0) && (gm.boundries.size()>0))
				{
					if(listOfPlayers.size()>0)
					{
						viewMapWithPlayer();
					}
					else
					{
						viewMapWithoutPlayer();
					}					
					addToCommands=true;
				}
				else
				{
					System.out.println("\nUnable to view the map as map is not loaded");
					addToCommands=false;
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "savemap":
				if(inputCommand.length>1)
				{
					if((gm.countries.size()>0) && (gm.continents.size()>0) && (gm.boundries.size()>0))
					{
						if(!checkFileExist(inputCommand[1]))
						{
							try
							{
								msc.writeGameMapFile(gm.continents, gm.countries, gm.boundries, inputCommand[1]);
								System.out.println("\nMap file saved successfully");
								addToCommands=true;
							}
							catch(Exception ex)
							{
								System.out.println("\nSome error has occurred. Please try again");
								addToCommands=false;
							}
							
						}
						else
						{
							System.out.println("\nFile already exists");
							addToCommands=false;
						}						
					}					
					else
					{
						System.out.println("\nMap has not been loaded. Please load the file and save");
						addToCommands=false;
					}
				}
				addInputCommandList(addToCommands,inputCommand[0]);
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
								System.out.println("\nFile uploaded successfully");								
							}
							else
							{
								System.out.println("\nFile not uploaded. There are format issues in file. Please upload again");
							}
							
						}
						catch(Exception ex)
						{
							System.out.println("\nError Occurred. Please try again");
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
						System.out.println("\nMap is connected");
					}
					else
					{
						System.out.println("\nMap is not connected");
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
							result=msc.gameMapReading(gm.continents,gm.countries,gm.boundries,inputCommand[1]);
							if(result.equals("Success"))
							{
								System.out.println("\nFile uploaded successfully");								
								addToCommands=true;
							}
							else
							{
								System.out.println("\nFile not uploaded. There are format issues in file. Please upload again");
								addToCommands=false;
							}
							
						}
						catch(Exception ex)
						{
							System.out.println("\nError Occurred. Please try again");
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
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "gameplayer":
				if(inputCommand.length>1)
				{
					int i=1;
					while(i<inputCommand.length)
					{						
						if(inputCommand[i].equals("-add") && (i+1<inputCommand.length))
						{							
							result=psc.addPlayer(players, inputCommand[i+1]);
							if(result.equals("Success"))
							{
								System.out.println("\n"+inputCommand[i+1]+" player added successfully");
								//System.out.println(players);
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
								//System.out.println(players);
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
			case "populatecountries" :
				if((gm.countries.size()>0) && (gm.continents.size()>0) && (gm.boundries.size()>0) && (players.size()>0))
				{
					listOfPlayers.clear();
					result=psc.assignRandomCountries(players,gm.countries,listOfPlayers);
					if(result.equals("Success"))
					{
						System.out.println("Players assigned to countries");
					}
				}
				else
				{
					System.out.println("\nCannot populate countires to player if map is not loaded or players are not added");
					System.out.println("\nPlease try again by loading map and creating players");
				}
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "placearmy":
				if(inputCommand.length>1)
				{
					if(listOfPlayers.size()>0)
					{
						result=psc.placeArmy(gm.countries, listOfPlayers, inputCommand[1], cons.NO_PLAYER_ARMIES.get(players.size()));						
						System.out.println("\n"+" "+result);
						addToCommands=true;						
						
					}
					else
					{
						System.out.println("\nCannot place army as players are not assigned to countries");
						addToCommands=false;
					}
				}
				else
				{
					System.out.println("\nplacearmy command format is incorrect");
					addToCommands=false;
				}				
				addInputCommandList(addToCommands,inputCommand[0]);
				commandLine();
				break;
			case "placeall":
				if(listOfPlayers.size()>0)
				{
					boolean flag=checkArmiesPlaced();
					if(flag)
					{
						result=psc.placeAll(gm.countries, listOfPlayers, cons.NO_PLAYER_ARMIES.get(players.size()));
						System.out.println("\nArmies are placed successfully");
					}
					else
					{
						System.out.println("\nArmies are already placed for the player");
					}
					addToCommands=true;
				}
				else
				{
					System.out.println("\nCannot place army as players are not assigned to countries");
					addToCommands=false;
				}
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
	* This method is used for displaying the map without player
	*/
	private void viewMapWithoutPlayer() {
		String countryName="";
		String continentName="";
		String neighbours;
		String playerName="";
		ArrayList<Integer> neighboursNum;
		System.out.format("%-30s|%-30s|%-30s", "Country Name","ContinentName","Neighbor Countries");
		System.out.println();
		for(int i=0;i<90;i++)
			System.out.print("_");
		System.out.println();
		for(int i:gm.countries.keySet())
		{
			neighbours="";
			Countries cou=gm.countries.get(i);
			countryName=cou.getCountryName();
			playerName=cou.getOwnerName();
			for(int j:gm.continents.keySet())
			{
				if(cou.getCountryContinentNum()==j)
				{
					Continents con=gm.continents.get(j);
					continentName=con.getContinentName();
					break;
				}
			}
			neighboursNum=gm.boundries.get(i);
			for(int l:neighboursNum)
			{
				Countries couNeigh=gm.countries.get(l);
				neighbours+=couNeigh.getCountryName()+",";
			}
			neighbours=neighbours.substring(0,neighbours.length()-1);
			System.out.format("%-30s|%-30s|%-30s", countryName,continentName,neighbours);
			System.out.println();
		}
	}
	
	/**
	* This method is used for displaying the map without player
	*/
	private void viewMapWithPlayer() {
		String countryName="";
		String continentName="";
		String neighbours;
		String playerName="";
		ArrayList<Integer> neighboursNum;
		System.out.format("%-30s|%-30s|%-30s|%-30s", "Country Name","ContinentName","Player Name","Neighbor Countries");
		System.out.println();
		for(int i=0;i<120;i++)
			System.out.print("_");
		System.out.println();
		for(int i:gm.countries.keySet())
		{
			neighbours="";
			Countries cou=gm.countries.get(i);
			countryName=cou.getCountryName();
			playerName=cou.getOwnerName();
			for(int j:gm.continents.keySet())
			{
				if(cou.getCountryContinentNum()==j)
				{
					Continents con=gm.continents.get(j);
					continentName=con.getContinentName();
					break;
				}
			}
			neighboursNum=gm.boundries.get(i);
			for(int l:neighboursNum)
			{
				Countries couNeigh=gm.countries.get(l);
				neighbours+=couNeigh.getCountryName()+",";
			}
			neighbours=neighbours.substring(0,neighbours.length()-1);
			System.out.format("%-30s|%-30s|%-30s|%-30s",
			countryName,continentName,playerName,neighbours);
			System.out.println();
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
	
	/**
	 * This method is used for checking if place all command need to perform or not 
	 * @return true if complete armies are not place; otherwise false
	 */
	public boolean checkArmiesPlaced()
	{
		boolean flag=false;
		int playerArmiesCount=0;
		int totalArmiesCount=cons.NO_PLAYER_ARMIES.get(players.size());
		for(String str:listOfPlayers.keySet())
		{
			Player p=listOfPlayers.get(str);
			playerArmiesCount=psc.totalArmyCountPlayer(p);
			if(playerArmiesCount<totalArmiesCount)
			{
				flag=true;
				break;
			}
			else
			{
				flag=false;
			}
			
		}
		return flag;
	}

}
