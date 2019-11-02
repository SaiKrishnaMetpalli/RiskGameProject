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
import controller.AttackController;
import controller.CommonController;
import controller.FortificationController;
import controller.MapSelectionController;
import controller.PlayerSelectionController;
import controller.ReinforcementController;
import util.CONSTANTS;

/**
 * This class gives the command line interface for user Every user input is
 * retrieved and navigated to functionality
 */
public class CommandLine {

	String input;
	String result;
	Scanner sc;

	boolean addToCommands;
	ArrayList<String> inputCommandsList;

	ArrayList<String> players;
	HashMap<String, Player> listOfPlayers;
	CONSTANTS cons;

	GameMap gm;
	Player p;
	PlayerSelectionController psc;
	MapSelectionController msc;
	ReinforcementController ric;
	FortificationController fc;
	CommonController cc;
	AttackController ac;

	/**
	 * Default constructor To create the variable objects
	 */
	public CommandLine() {
		sc = new Scanner(System.in);
		inputCommandsList = new ArrayList<String>();

		players = new ArrayList<String>();
		listOfPlayers = new HashMap<String, Player>();
		cons = new CONSTANTS();

		gm = new GameMap();
		p = new Player();
		psc = new PlayerSelectionController();
		msc = new MapSelectionController();
		ric = new ReinforcementController();
		fc = new FortificationController();
		cc = new CommonController();
		ac = new AttackController();
	}

	/**
	 * This method is used for retrieving user input commands and navigating to the
	 * functionality
	 */
	public void commandLine() {
		System.out.println("\nEnter the commands");
		input = sc.nextLine();
		String[] inputCommand = input.split(" ");
		if (inputCommand.length > 0) {
			switch (inputCommand[0]) {
			case "editcontinent":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap")) {
						if (inputCommand.length > 1) {
							int i = 1;
							while (i < inputCommand.length) {
								if (inputCommand[i].equals("-add") && (i + 2 < inputCommand.length)) {
									result = msc.addContinent(gm.getContinents(), inputCommand[i + 1],
											inputCommand[i + 2]);
									if (result.contains("success")) {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = true;
									} else {
										System.out.println(
												"\n " + inputCommand[i + 1] + " " + inputCommand[i + 2] + " " + result);
										addToCommands = false;
									}
									i = i + 3;

								} else if (inputCommand[i].equals("-remove") && (i + 1 < inputCommand.length)) {
									System.out.println(inputCommand[i + 1]);
									result = msc.removeContinent(gm.getContinents(), gm.getBoundries(),
											gm.getCountries(), inputCommand[i + 1]);
									if (result.contains("success")) {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = true;
									} else {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = false;
									}
									i = i + 2;
								} else {
									System.out.println("\neditcontinent command format is incorrect");
									addToCommands = false;
									break;
								}
							}
						} else {
							System.out.println("\neditcontinent command format is incorrect");
							addToCommands = false;
						}
					} else {
						System.out
								.println("\neditcontinent command cannot be loaded before loadmap or editmap command");
						addToCommands = false;
					}

				} else {
					System.out.println("\neditcontinent command cannot be peformed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "editcountry":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap")) {
						if (inputCommand.length > 1) {
							int i = 1;
							while (i < inputCommand.length) {
								if (inputCommand[i].equals("-add") && (i + 2 < inputCommand.length)) {
									result = msc.addCountry(gm.getContinents(), gm.getCountries(), gm.getBoundries(),
											inputCommand[i + 1], inputCommand[i + 2]);
									if (result.contains("success")) {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = true;
									} else {
										System.out.println(
												"\n " + inputCommand[i + 1] + " " + inputCommand[i + 2] + " " + result);
										addToCommands = false;
									}
									i = i + 3;
								} else if (inputCommand[i].equals("-remove") && (i + 1 < inputCommand.length)) {
									result = msc.removeCountry(gm.getCountries(), gm.getBoundries(),
											inputCommand[i + 1]);
									if (result.contains("success")) {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = true;
									} else {
										System.out.println("\n " + inputCommand[i + 1] + " " + result);
										addToCommands = false;
									}
									i = i + 2;
								} else {
									System.out.println("\neditcountry command format is incorrect");
									addToCommands = false;
								}
							}
						} else {
							System.out.println("\neditcountry command format is incorrect");
							addToCommands = false;
						}
					} else {
						System.out.println("\neditcountry command cannot be loaded before loadmap or editmap command");
						addToCommands = false;
					}
				} else {
					System.out.println("\neditcountry command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "editneighbor":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommandsList.contains("loadmap") || inputCommandsList.contains("editmap")) {
						if (inputCommand.length > 1) {
							int i = 1;
							while (i < inputCommand.length) {
								if (inputCommand[i].equals("-add") && (i + 2 < inputCommand.length)) {
									result = msc.addNeighbour(gm.getCountries(), gm.getBoundries(), inputCommand[i + 1],
											inputCommand[i + 2]);
									if (result.contains("success")) {
										String result2 = msc.addNeighbour(gm.getCountries(), gm.getBoundries(),
												inputCommand[i + 2], inputCommand[i + 1]);
										System.out.println("\n " + inputCommand[i + 2] + " " + result);
										addToCommands = true;
									} else {
										System.out.println(
												"\n " + inputCommand[i + 1] + " " + inputCommand[i + 2] + " " + result);
										addToCommands = false;
									}
									i = i + 3;
								} else if (inputCommand[i].equals("-remove") && (i + 2 < inputCommand.length)) {
									result = msc.removeNeighbour(gm.getCountries(), gm.getBoundries(),
											inputCommand[i + 1], inputCommand[i + 2]);
									if (result.contains("success")) {
										String result2 = msc.removeNeighbour(gm.getCountries(), gm.getBoundries(),
												inputCommand[i + 2], inputCommand[i + 1]);
										System.out.println("\n " + inputCommand[i + 2] + " " + result);
										addToCommands = true;
									} else {
										System.out.println(
												"\n " + inputCommand[i + 1] + " " + inputCommand[i + 2] + " " + result);
										addToCommands = false;
									}
									i = i + 3;
								} else {
									System.out.println("\neditneighbor command format is incorrect");
									addToCommands = false;
									break;
								}
							}
						} else {
							System.out.println("\neditneighbor command format is incorrect");
							addToCommands = false;
						}
					} else {
						System.out.println("\neditneighbor command cannot be loaded before loadmap or editmap command");
						addToCommands = false;
					}
				} else {
					System.out.println("\neditneighbor command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "showmap":
				viewMap();
				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "savemap":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommand.length == 2) {
						if ((gm.getCountries().size() > 0) && (gm.getContinents().size() > 0)
								&& (gm.getBoundries().size() > 0)) {
							boolean result = msc.isConnectedMap(gm.getBoundries());
							if (result) {
								try {
									msc.writeGameMapFile(gm.getContinents(), gm.getCountries(), gm.getBoundries(),
											inputCommand[1]);
									System.out.println("\nMap file saved successfully");
									addToCommands = true;
								} catch (Exception ex) {
									System.out.println("\nSome error has occurred. Please try again");
									addToCommands = false;
								}
							} else {
								System.out.println("\nFile cannot be saved as map is not connected");
								addToCommands = false;
							}
						} else {
							System.out.println("\nMap has not been loaded. Please load the file and save");
							addToCommands = false;
						}
					}
				} else {
					System.out.println("\nsavemap command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "editmap":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommand.length == 2) {
						if (checkFileExist(inputCommand[1])) {
							try {
								gm.getContinents().clear();
								gm.getCountries().clear();
								gm.getBoundries().clear();
								String result = msc.gameMapReading(gm.getContinents(), gm.getCountries(),
										gm.getBoundries(), inputCommand[1]);
								if (result.equals("Success")) {
									System.out.println("\nFile uploaded successfully");
								} else {
									System.out.println(
											"\nFile not uploaded. There are format issues in file. Please upload again");
								}

							} catch (Exception ex) {
								System.out.println("\nError Occurred. Please try again");
							}
						} else {
							System.out
									.println("\n" + inputCommand[1] + " file does not exist. Please create a new map");
							msc.createEmptyFile(inputCommand[1]);
						}
						addToCommands = true;
					} else {
						System.out.println("\neditmap command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\neditmap command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "validatemap":
				if (p.getGameState().equals("STARTUP")) {
					if (gm.getBoundries().size() > 0) {
						boolean result = msc.isConnectedMap(gm.getBoundries());
						if (result) {
							System.out.println("\nMap is connected");
						} else {
							System.out.println("\nMap is not connected");
						}
					} else {
						System.out.println("\nMap has not been loaded. Please load the map and validate");
						addToCommands = false;
					}
				} else {
					System.out.println("\nvalidatemap command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "loadmap":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommand.length == 2) {
						if (checkFileExist(inputCommand[1])) {
							try {
								gm.getContinents().clear();
								gm.getCountries().clear();
								gm.getBoundries().clear();
								result = msc.gameMapReading(gm.getContinents(), gm.getCountries(), gm.getBoundries(),
										inputCommand[1]);
								if (result.equals("Success")) {
									System.out.println("\nFile uploaded successfully");
									boolean result2 = msc.isConnectedMap(gm.getBoundries());
									if (result2) {
										System.out.println("\nMap is connected");
									} else {
										System.out.println("\nMap is not connected");
									}
									addToCommands = true;
								} else {
									System.out.println(
											"\nFile not uploaded. There are format issues in file. Please upload again");
									addToCommands = false;
								}

							} catch (Exception ex) {
								System.out.println("\nError Occurred. Please try again");
								addToCommands = false;
							}
						} else {
							System.out.println("\n" + inputCommand[1] + " file does not exist");
							addToCommands = false;
						}
					} else {
						System.out.println("\nloadmap command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\nloadmap command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "gameplayer":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommandsList.contains("loadmap")) {
						if (inputCommand.length > 1) {
							int i = 1;
							while (i < inputCommand.length) {
								if (inputCommand[i].equals("-add") && (i + 1 < inputCommand.length)) {
									result = psc.addPlayer(players, inputCommand[i + 1]);
									if (result.equals("Success")) {
										System.out.println("\n" + inputCommand[i + 1] + " player added successfully");
										addToCommands = true;
									} else {
										System.out.println("\n" + inputCommand[i + 1] + " player already exists");
										addToCommands = false;
									}
								} else if (inputCommand[i].equals("-remove") && (i + 1 < inputCommand.length)) {
									result = psc.removePlayer(players, inputCommand[i + 1]);
									if (result.equals("Success")) {
										System.out.println("\n" + inputCommand[i + 1] + " player removed successfully");
										addToCommands = true;
									} else {
										System.out.println("\n" + inputCommand[i + 1] + " player does not exists");
										addToCommands = false;
									}
								} else {
									System.out.println("\ngameplayer command format is incorrect");
									addToCommands = false;
								}
								i = i + 2;
							}
						}
					} else {
						System.out.println("\ngameplayer command cannot be performed as map is not loaded");
						addToCommands = false;
					}
				} else {
					System.out.println("\ngameplayer command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "populatecountries":
				if (p.getGameState().equals("STARTUP")) {
					if ((gm.getCountries().size() > 0) && (gm.getContinents().size() > 0)
							&& (gm.getBoundries().size() > 0) && (players.size() > 0)) {
						if (players.size() == 1) {
							System.out.println("\nPlayers should be more than 1 to play the game");
							addToCommands = false;
						} else {
							listOfPlayers.clear();
							result = psc.assignRandomCountries(players, gm.getCountries(), listOfPlayers);
							if (result.equals("Success")) {
								p.setCurrentPlayerTurn(players.get(0));
								System.out.println("Players assigned to countries");
								addToCommands = true;
							}
						}
					} else {
						System.out.println(
								"\nCannot populate countires to player if map is not loaded or players are not added");
						System.out.println("\nPlease try again by loading map and creating players");
						addToCommands = false;
					}
				} else {
					System.out.println(
							"\npopulatecountries command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "placearmy":
				if (p.getGameState().equals("STARTUP")) {
					if (inputCommand.length == 2) {
						if (listOfPlayers.size() > 0) {
							if (checkPlayersTurn(inputCommand[1])) {
								result = psc.placeArmy(gm.getCountries(), listOfPlayers, inputCommand[1],
										cons.NO_PLAYER_ARMIES.get(players.size()));
								if ((players.indexOf(p.getCurrentPlayerTurn())) + 1 < players.size()) {
									p.setCurrentPlayerTurn(
											players.get((players.indexOf(p.getCurrentPlayerTurn())) + 1));
								} else {
									p.setCurrentPlayerTurn(players.get(0));
								}
								System.out.println("\n" + " " + result);
								addToCommands = true;
							} else {
								System.out.println("\nCannot place army for the country, it is not the turn of player");
								addToCommands = false;
							}
						} else {
							System.out.println("\nCannot place army as players are not assigned to countries");
							addToCommands = false;
						}
					} else {
						System.out.println("\nplacearmy command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\nplacearmy command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "placeall":
				if (p.getGameState().equals("STARTUP")) {
					if (listOfPlayers.size() > 0) {
						if (checkArmiesPlaced()) {
							result = psc.placeAll(gm.getCountries(), listOfPlayers,
									cons.NO_PLAYER_ARMIES.get(players.size()));
							System.out.println("\nArmies are placed successfully");
							p.setGameState("REINFORCE");
						} else {
							System.out.println("\nArmies are already placed for the player");
						}
						p.setCurrentPlayerTurn(players.get(0));
						addToCommands = true;
					} else {
						System.out.println("\nCannot place army as players are not assigned to countries");
						addToCommands = false;
					}
				} else {
					System.out.println("\nplaceall command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "reinforce":
				if (p.getGameState().equals("REINFORCE")) {
					if (inputCommand.length == 3) {
						if (checkPlayersTurn(inputCommand[1])) {
							if (Integer.parseInt(inputCommand[2]) < 1) {
								System.out.println(
										"\nReinforcement cannot be performed as armies should be greater than 0");
								addToCommands = false;
							} else {
								if (!checkArmiesPlaced()) {
									result = ric.placeReinforceArmy(inputCommand[1], Integer.parseInt(inputCommand[2]),
											gm.getCountries(), listOfPlayers, gm.getContinents());
									if (result.contains("success")) {
										addToCommands = true;
									} else {
										addToCommands = false;
									}
									System.out.println("\n " + result);
								} else {
									System.out.println(
											"\nReinforcement cannot be performed as armies are not assigned to player");
									addToCommands = false;
								}
							}
						} else {
							System.out.println("\nCannot reinforce army for the country, it is not the turn of player");
							addToCommands = false;
						}
					} else {
						System.out.println("\nreinforce command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\nreinforce command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "attack":				
				if (p.getGameState().equals("ATTACK")) {
					if (inputCommand.length == 3) {
						if (checkPlayersTurn(inputCommand[1])) {
							p.setAttackerName(p.getCurrentPlayerTurn());
							p.setDefenderName(cc.findPlayerNameFromCountry(gm.getCountries(), inputCommand[2]));
							if (ac.validateDefenderCountry(inputCommand[1], inputCommand[2], gm.getCountries(),
									gm.getBoundries())) {

								if (ac.validateNumDice(inputCommand[1], Integer.parseInt(inputCommand[3]), listOfPlayers.get(p.getAttackerName()),
										gm.getCountries())) {

									String attacked = ac.attackPhase(inputCommand[1], inputCommand[2], Integer.parseInt(inputCommand[3]),
											p);
									System.out.println(attacked);

								} else {
									System.out.println("Number of Dice Played is invalid");
								}
							} else {
								System.out.println("Defender Country is not a neighbouring country");
							}

						} else {
							System.out.println("\nCannot attack ,it is not the turn of player");
							addToCommands = false;
						}

					} else {
						System.out.println("\nattack command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\nattack command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}			
			case "defend":
				if(p.getGameState().equals("ATTACK")) {
					if((inputCommand.length == 2)) {
						 if(Integer.parseInt(inputCommand[1]) > 0  && Integer.parseInt(inputCommand[1]) <=2) {
							 if(ac.validateDefenderNumdice(p.getDefenderCountry(), Integer.parseInt(inputCommand[1]), listOfPlayers.get(p.getDefenderCountry()),
									 gm.getCountries())) {
								 String defend = ac.defendPhaseDiceRoll(p.getDefenderCountry(),Integer.parseInt(inputCommand[1]), p);
								 System.out.println(defend);
							 }
					        }
					        else {
					        	System.out.println(" Value of numdice is not valid");
					        	addToCommands = false;
					        }
						}
						else {
							System.out.println("\ndefend command format is incorrect");
							addToCommands = false;
						}
					}
					else {
						System.out.println("\ndefend command cannot be performed in " + p.getGameState() + " phase");
						addToCommands = false;
				}
			case "fortify":
				if (p.getGameState().equals("FORTIFY")) {
					if ((inputCommand.length == 4) || (inputCommand.length == 2)) {
						if ((!checkArmiesPlaced()) && (!inputCommandsList.contains("fortify"))) {
							if (inputCommand.length == 4) {
								if (checkPlayersTurn(inputCommand[1])) {
									result = fc.fortify(listOfPlayers, inputCommand[1], inputCommand[2],
											Integer.parseInt(inputCommand[3]), gm.getCountries(), gm.getBoundries());
									if (result.contains("success")) {
										addToCommands = true;
										p.setGameState("REINFORCE");
									} else {
										addToCommands = false;
									}
									System.out.println("\n " + result);
								} else {
									System.out.println(
											"\nCannot fortify army for the country, it is not the turn of player");
									addToCommands = false;
								}
							} else {
								System.out.println("Fortification none completed");
								addToCommands = true;
							}
						} else {
							System.out.println(
									"\nFortification cannot be performed, only one fortification is allowed per turn");
							addToCommands = false;
						}

					} else {
						System.out.println("\nfortify command format is incorrect");
						addToCommands = false;
					}
				} else {
					System.out.println("\nfortify command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				addInputCommandList(addToCommands, inputCommand[0]);
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
	 * This method is used for displaying the map
	 */
	private void viewMap() {
		String countryName = "";
		String continentName = "";
		String neighbours;
		String playerName = "";
		int armies = 0;
		boolean headerDisplay = true;
		ArrayList<Integer> neighboursNum;

		if (gm.getContinents().size() > 0) {
			if (gm.getCountries().size() > 0) {
				for (int i : gm.getCountries().keySet()) {
					neighbours = "";
					Countries objCou = gm.getCountries().get(i);
					countryName = objCou.getCountryName();
					playerName = objCou.getOwnerName();
					for (int j : gm.getContinents().keySet()) {
						if (objCou.getCountryContinentNum() == j) {
							Continents objCont = gm.getContinents().get(j);
							continentName = objCont.getContinentName();
							break;
						}
					}
					neighboursNum = gm.getBoundries().get(i);
					for (int l : neighboursNum) {
						Countries couNeigh = gm.getCountries().get(l);
						neighbours += couNeigh.getCountryName() + ",";
					}
					if (neighbours.length() > 0) {
						neighbours = neighbours.substring(0, neighbours.length() - 1);
					}
					if (listOfPlayers.size() > 0) {
						armies = (listOfPlayers.get(playerName)).getOwnedCountriesArmiesList().get(countryName);
						if (headerDisplay) {
							headerDisplay = false;
							System.out.format("%-30s|%-30s|%-30s|%-30s|%-30s", "Country Name", "Continent Name",
									"Player Name", "Armies", "Neighbor Countries");
							System.out.println();
							for (int dashes = 0; dashes < 150; dashes++)
								System.out.print("_");
							System.out.println();
						}
						System.out.format("%-30s|%-30s|%-30s|%-30s|%-30s", countryName, continentName, playerName,
								armies, neighbours);
						System.out.println();
					} else {
						if (headerDisplay) {
							headerDisplay = false;
							System.out.format("%-30s|%-30s|%-30s", "Country Name", "Continent Name",
									"Neighbor Countries");
							System.out.println();
							for (int dashes = 0; dashes < 90; dashes++)
								System.out.print("_");
							System.out.println();
						}
						System.out.format("%-30s|%-30s|%-30s", countryName, continentName, neighbours);
						System.out.println();
					}
				}
			} else {
				for (int cont : gm.getContinents().keySet()) {
					if (headerDisplay) {
						headerDisplay = false;
						System.out.format("%-30s", "ContinentName");
						System.out.println();
						for (int dashes = 0; dashes < 30; dashes++)
							System.out.print("_");
						System.out.println();
					}
					Continents objCont = gm.getContinents().get(cont);
					continentName = objCont.getContinentName();
					System.out.format("%-30s", continentName);
					System.out.println();
				}
			}
			addToCommands = true;
		} else {
			System.out.println("\nUnable to view the map as map is not loaded");
			addToCommands = false;
		}
	}

	/**
	 * This method is used for adding the input commands one by one as the user is
	 * entering
	 * 
	 * @param input   this variable gives true/false for adding to inputCommandsList
	 * @param command this variable has command name that is used for adding to
	 *                inputCommandsList
	 */
	public void addInputCommandList(boolean input, String command) {
		if (input) {
			if (!inputCommandsList.contains(command)) // checks if the command is already present or not
			{
				inputCommandsList.add(command);
			}
		}
	}

	/**
	 * This method is used for checking if file exists or not
	 * 
	 * @param fileName this variable gives the filename which user entered
	 * @return returns gives true/false
	 */
	public boolean checkFileExist(String fileName) {
		String mapFilePath = Paths.get("").toAbsolutePath().toString() + "\\src\\resource\\" + fileName;
		File mapFile = new File(mapFilePath);
		boolean mapExists = mapFile.exists();
		return mapExists;
	}

	/**
	 * This method is used for checking if place all command need to perform or not
	 * 
	 * @return true if complete armies are not place; otherwise false
	 */
	public boolean checkArmiesPlaced() {
		boolean flag = false;
		int playerArmiesCount = 0;
		int totalArmiesCount = cons.NO_PLAYER_ARMIES.get(players.size());
		for (String str : listOfPlayers.keySet()) {
			Player p = listOfPlayers.get(str);
			playerArmiesCount = psc.totalArmyCountPlayer(p);
			if (playerArmiesCount < totalArmiesCount) {
				flag = true;
				break;
			} else {
				flag = false;
			}

		}
		return flag;
	}

	/**
	 * This method is used for checking the player's turn or not
	 * 
	 * @param countryName this variable contains the name of the country
	 * @return this returns true when matches with current player; Otherwise false
	 */
	public boolean checkPlayersTurn(String countryName) {
		String playerName = "";
		playerName = cc.findPlayerNameFromCountry(gm.getCountries(), countryName);
		if (playerName.equals(p.getCurrentPlayerTurn())) {
			return true;
		} else {
			return false;
		}
	}

}
