package view;

import java.io.File;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import controller.CheaterStrategy;
import controller.CommonController;
import controller.MapSelectionController;
import controller.PlayerController;
import controller.PlayerSelectionController;
import controller.StrategyController;
import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.GameStateBuilder;
import model.GameStateScenario;
import model.Player;
import model.PlayersList;
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

	ArrayList<String> playersSetup;
	HashMap<String, String> playersWithStrategies;
	CONSTANTS cons;
	String actions;

	GameMap gm;
	Player p;
	PlayersList pl;
	PlayerSelectionController psc;
	MapSelectionController msc;
	CommonController cc;
	PlayerController playerController;
	PhaseView pv;
	PlayerWorldDominationView pwdv;
	CardExchangeView cev;
	GameState gs;
	StrategyController behaviour;


	/**
	 * Default constructor To create the variable objects
	 * 
	 * @author Sai Krishna
	 */
	public CommandLine() {
		sc = new Scanner(System.in);
		inputCommandsList = new ArrayList<String>();

		playersSetup = new ArrayList<String>();
		playersWithStrategies = new HashMap<String, String>();
		cons = new CONSTANTS();
		actions = "";

		gm = new GameMap();
		p = new Player();
		pl = new PlayersList();
		psc = new PlayerSelectionController();
		msc = new MapSelectionController();
		cc = new CommonController();
		playerController = new PlayerController();
		pv = new PhaseView();
		pwdv = new PlayerWorldDominationView();
		cev = new CardExchangeView();
		behaviour = new StrategyController();

		p.attach(pv);
		pl.attach(pwdv);
		pl.attach(cev);
	}

	/**
	 * This method is used for retrieving user input commands and navigating to the
	 * functionality
	 * 
	 * @author Sai Krishna
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
				commandLine();
				break;
			case "savegame":
				if (inputCommand.length == 2) {
					if ((gm.getCountries().size() > 0) && (gm.getContinents().size() > 0)
							&& (gm.getBoundries().size() > 0)) {
						boolean result = msc.isConnectedMap(gm.getBoundries());
						if (result) {
							try {
								GameStateBuilder gsb=new GameStateScenario();
								//gsb.buildGameMap(gm);
								//gsb.buildPlayersList(pl);
								//gsb.buildPlayer(p);
								gs=gsb.getGameState();
								msc.saveGameFile(gs, inputCommand[1]);
								System.out.println("\nGame saved successfully");
								addToCommands = true;
							} catch (Exception ex) {
								System.out.println("\nSome error has occurred. Please try again");
								addToCommands = false;
							}
						} else {
							System.out.println("\nGame cannot be saved as map is not connected");
							addToCommands = false;
						}
					} else {
						System.out.println("\nMap has not been loaded. Please load the file and save");
						addToCommands = false;
					}
				} else {
					System.out.println("\nsavemap command format is incorrect");
					addToCommands = false;
				}				
				
				commandLine();
				break;
			case "loadgame":
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
				} else {
					System.out.println("\nsavemap command format is incorrect");
					addToCommands = false;
				}
				
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
					} else {
						System.out.println("\nsavemap command format is incorrect");
						addToCommands = false;
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
										addGameCards();
										p.setContinentsCountryList(
												cc.getContinentsCountryList(gm.getContinents(), gm.getCountries()));
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
								if (inputCommand[i].equals("-add") && (i + 2 < inputCommand.length)) {
									result = psc.addPlayer(playersWithStrategies, playersSetup, inputCommand[i + 1],
											inputCommand[i + 2]);
									if (result.equals("Success")) {
										System.out.println("\n" + inputCommand[i + 1] + " player added successfully");
										addToCommands = true;
									} else {
										System.out.println("\n" + inputCommand[i + 1] + " player already exists");
										addToCommands = false;
									}
									i = i + 3;
								} else if (inputCommand[i].equals("-remove") && (i + 1 < inputCommand.length)) {
									result = psc.removePlayer(playersWithStrategies, playersSetup, inputCommand[i + 1]);
									if (result.equals("Success")) {
										System.out.println("\n" + inputCommand[i + 1] + " player removed successfully");
										addToCommands = true;
									} else {
										System.out.println("\n" + inputCommand[i + 1] + " player does not exists");
										addToCommands = false;
									}
									i = i + 2;
								} else {
									System.out.println("\ngameplayer command format is incorrect");
									addToCommands = false;
									break;
								}
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
							&& (gm.getBoundries().size() > 0) && (playersSetup.size() > 0)) {
						if (playersSetup.size() == 1) {
							System.out.println("\nPlayers should be more than 1 to play the game");
							addToCommands = false;
						} else {
							if (playersSetup.size() > gm.getCountries().size()) {
								System.out
										.println("\nCannot populate countries as players are more than countires size");
								addToCommands = false;
							} else {
								pl.getListOfPlayers().clear();
								result = psc.assignRandomCountries(playersSetup, playersWithStrategies,
										gm.getCountries(), pl.getListOfPlayers());
								if (result.equals("Success")) {
									p.setCurrentPlayerTurn(playersSetup.get(0));
									System.out.println("Players assigned to countries");
									addToCommands = true;
								}
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
						if (pl.getListOfPlayers().size() > 0) {
							if (checkPlayersTurn(inputCommand[1])) {
								result = psc.placeArmy(gm.getCountries(), pl.getListOfPlayers(), inputCommand[1],
										cons.NO_PLAYER_ARMIES.get(playersSetup.size()));
								setPlayerTurn();
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
					if (pl.getListOfPlayers().size() > 0) {
						if (checkArmiesPlaced()) {
							result = psc.placeAll(gm.getCountries(), pl.getListOfPlayers(),
									cons.NO_PLAYER_ARMIES.get(playersSetup.size()));
							System.out.println("\nArmies are placed successfully");
						} else {
							System.out.println("\nArmies are already placed for the player");
						}
						p.setActionsPerformed("");
						p.setCurrentPlayerTurn(playersSetup.get(0));
						p.setGameState("REINFORCE");
						executeBehaviour(pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getStrategy());

						addToCommands = true;
					} else {
						System.out.println("\nCannot place army as players are not assigned to countries");
						addToCommands = false;
					}
				} else {
					System.out.println("\nplaceall command cannot be performed in " + p.getGameState() + " phase");
					addToCommands = false;
				}

				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "exchangecards":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("REINFORCE")) {
					if (inputCommand.length == 4) {
						if ((Integer.parseInt(inputCommand[1]) > 0) && (Integer.parseInt(inputCommand[2]) > 0)
								&& (Integer.parseInt(inputCommand[3]) > 0)
								&& (Math.max(
										Math.max(Integer.parseInt(inputCommand[1]), Integer.parseInt(inputCommand[2])),
										Integer.parseInt(inputCommand[3])) <= pl.getListOfPlayers()
												.get(p.getCurrentPlayerTurn()).getCurrentCardList().size())) {
							p.setCardReward(playerController.exchangeCard(Integer.parseInt(inputCommand[1]),
									Integer.parseInt(inputCommand[2]), Integer.parseInt(inputCommand[3]),
									pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList(),
									pl.getListOfPlayers().get(p.getCurrentPlayerTurn())));
							System.out.println("\nCards are exchanged and the card reward is " + p.getCardReward());
							actions += "\nCards are exchanged and the card reward is " + p.getCardReward();
							playerController.removeCardPositions(Integer.parseInt(inputCommand[1]),
									Integer.parseInt(inputCommand[2]), Integer.parseInt(inputCommand[3]), pl, p);
							addToCommands = true;
						} else {
							System.out.println("\nCards cannot be exchanged; the given positions are invalid");
							actions += "\nCards cannot be exchanged; the given positions are invalid";
							addToCommands = false;
						}
					} else if (inputCommand.length == 2) {
						if (pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList().size() >= 5) {
							System.out.println("\nCannot perform none operation because your cards are "
									+ pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList().size());
							actions += "\nCannot perform none operation because your cards are "
									+ pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList().size();
							addToCommands = false;
						} else {
							System.out.println("\nCards exchange none has been performed");
							actions += "\nCards exchange none has been performed";
							addToCommands = true;
							p.setCardReward(0);
						}

					} else {
						System.out.println("\nexchangecard command format is incorrect");
						actions += "\nexchangecard command format is incorrect";
						addToCommands = false;
					}
				} else {
					System.out.println("\nexchangecard command cannot be performed in " + p.getGameState() + " phase");
					actions += "\nexchangecard command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;

			case "reinforce":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("REINFORCE")) {
					if (inputCommand.length == 3) {
						if (checkPlayersTurn(inputCommand[1])) {
							if (Integer.parseInt(inputCommand[2]) < 1) {
								System.out.println(
										"\nReinforcement cannot be performed as armies should be greater than 0");
								actions += "\nReinforcement cannot be performed as armies should be greater than 0";
								addToCommands = false;
							} else {

								if (pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getCurrentCardList()
										.size() < 5) {
									if (p.getAvailableReinforceArmies() == 0) {
										int countryReward = playerController.calculateOwnedCountryReward(
												pl.getListOfPlayers().get(p.getCurrentPlayerTurn()));
										int continetReward = playerController.calculateContinentReward(
												pl.getListOfPlayers().get(p.getCurrentPlayerTurn()), gm.getContinents(),
												gm.getCountries(), inputCommand[1]);
										p.setAvailableReinforceArmies(playerController.calculateReinforceArmy(
												countryReward, continetReward, p.getCardReward()));
									}

									result = playerController.placeReinforceArmy(inputCommand[1],
											Integer.parseInt(inputCommand[2]), gm.getCountries(), pl.getListOfPlayers(),
											gm.getContinents(), p);
									System.out.println("\n" + result);
									actions += "\n" + result;
									if (result.contains("success")) {
										if (p.getAvailableReinforceArmies() == 0) {
											actions = "";
											p.setGameState("ATTACK");
										} else {
											System.out.println("\nPlease place the remaining "
													+ p.getAvailableReinforceArmies() + " reinforcement armies");
											actions += "\nPlease place the remaining " + p.getAvailableReinforceArmies()
													+ " reinforcement armies";
										}
										addToCommands = true;
									} else {
										addToCommands = false;
									}

								} else {
									System.out
											.println("\nCannot perform reinforcement as there are "
													+ pl.getListOfPlayers().get(p.getCurrentPlayerTurn())
															.getCurrentCardList().size()
													+ " cards which need to be exchanged");
									actions += "\nCannot perform reinforcement as there are " + pl.getListOfPlayers()
											.get(p.getCurrentPlayerTurn()).getCurrentCardList().size()
											+ " cards which need to be exchanged";
									addToCommands = false;
								}

							}
						} else {
							System.out.println("\nCannot reinforce army for the country, it is not the turn of player");
							actions += "\nCannot reinforce army for the country, it is not the turn of player";
							addToCommands = false;
						}
					} else {
						System.out.println("\nreinforce command format is incorrect");
						actions += "\nreinforce command format is incorrect";
						addToCommands = false;
					}
				} else {
					System.out.println("\nreinforce command cannot be performed in " + p.getGameState() + " phase");
					actions += "\nreinforce command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;

			case "attack":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("ATTACK")) {
					if (inputCommandsList.get(inputCommandsList.size() - 1).equals("reinforce")
							|| inputCommandsList.get(inputCommandsList.size() - 1).equals("attackmove")
							|| inputCommandsList.get(inputCommandsList.size() - 1).equals("defend")
							|| p.isAllOutPerformed()) {
						if (inputCommand.length == 4) {
							p.setAllOutPerformed(false);
							if (inputCommand[3].equals("-allout")) {
								if (checkPlayersTurn(inputCommand[1])) {
									if (playerController.validateDefenderCountry(inputCommand[1], inputCommand[2],
											gm.getCountries(), gm.getBoundries(), pl.getListOfPlayers()
													.get(p.getCurrentPlayerTurn()).getOwnedCountriesList())) {

										p.setAttackerName(
												cc.findPlayerNameFromCountry(gm.getCountries(), inputCommand[1]));
										p.setDefenderName(
												cc.findPlayerNameFromCountry(gm.getCountries(), inputCommand[2]));

										String allOutAttacked = playerController.allOutAttackedPhase(inputCommand[1],
												inputCommand[2], pl.getListOfPlayers().get(p.getAttackerName()),
												gm.getCountries(), p, pl.getListOfPlayers().get(p.getDefenderName()));
										System.out.println("\n" + allOutAttacked + "\n" + "The last dice rolled: "
												+ p.getDiceRolled());
										actions += "\n" + allOutAttacked + "\n" + "The last dice rolled: "
												+ p.getDiceRolled();
										if (allOutAttacked.contains("Won")) {
											boolean checkAllCountriesOwned = playerController.checkGameEnd(pl);
											if (checkAllCountriesOwned) {
												System.out.println("\n" + p.getAttackerName() + " won the Risk Game");
												System.out.println("\nThe game is ended");
												System.exit(0);
											}
										}
										addToCommands = true;
									} else {
										System.out.println(
												"\nDefender Country is not a neighbouring country or it is his own Country");
										actions += "\nDefender Country is not a neighbouring country or it is his own Country";
										addToCommands = false;
									}
								} else {
									System.out.println(
											"\nCannot attack ,it is not the turn of player ,or the Country doesn't belong to this Player");
									actions += "\nCannot attack ,it is not the turn of player ,or the Country doesn't belong to this Player";
									addToCommands = false;
								}
							} else {
								if (checkPlayersTurn(inputCommand[1])) {
									if (playerController.validateDefenderCountry(inputCommand[1], inputCommand[2],
											gm.getCountries(), gm.getBoundries(), pl.getListOfPlayers()
													.get(p.getCurrentPlayerTurn()).getOwnedCountriesList())) {

										p.setAttackerName(
												cc.findPlayerNameFromCountry(gm.getCountries(), inputCommand[1]));
										p.setDefenderName(
												cc.findPlayerNameFromCountry(gm.getCountries(), inputCommand[2]));
										if (Integer.parseInt(inputCommand[3]) > 0
												&& Integer.parseInt(inputCommand[3]) <= 3) {
											if (playerController.validateNumDice(inputCommand[1],
													Integer.parseInt(inputCommand[3]),
													pl.getListOfPlayers().get(p.getAttackerName()))) {

												String attacked = playerController.attackPhase(inputCommand[1],
														inputCommand[2], Integer.parseInt(inputCommand[3]), p);
												System.out.println("\n" + attacked);
												actions += "\n" + attacked;
												addToCommands = true;
											} else {
												System.out.println("\nNumber of dice played is invalid");
												actions += "\nNumber of dice played is invalid";
												addToCommands = false;
											}
										} else {
											System.out.println("\nNumber of dice played is invalid");
											actions += "\nNumber of dice played is invalid";
											addToCommands = false;
										}
									} else {
										System.out.println(
												"\nDefender Country is not a neighbouring country or it is his own Country");
										actions += "\nDefender Country is not a neighbouring country or it is his own Country";
										addToCommands = false;
									}

								} else {
									System.out.println(
											"\nCannot attack ,it is not the turn of player ,or the Country doesn't belong to this Player");
									actions += "\nCannot attack ,it is not the turn of player ,or the Country doesn't belong to this Player";
									addToCommands = false;
								}
							}
						} else if (inputCommand.length == 2) {
							if (inputCommand[1].equals("-noattack")) {
								System.out.println("\nAttack noAttack is performed");
								actions = "";
								p.setGameState("FORTIFY");
								addToCommands = true;
							} else {
								System.out.println("\nAttack command format is incorrect");
								actions += "\nAttack command format is incorrect";
								addToCommands = false;
							}
						} else {
							System.out.println("\nAttack command format is incorrect");
							actions += "\nAttack command format is incorrect";
							addToCommands = false;
						}

					} else {
						System.out.println("\nCannot perform attack command");
						actions += "\nCannot perform attack command";
						addToCommands = false;
					}
				} else {
					System.out.println("\nattack command cannot be performed in " + p.getGameState() + " phase");
					actions += "\nattack command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;
			case "defend":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("ATTACK")) {
					if (inputCommandsList.get(inputCommandsList.size() - 1).equals("attack")) {
						if ((inputCommand.length == 2)) {
							if (Integer.parseInt(inputCommand[1]) > 0 && Integer.parseInt(inputCommand[1]) <= 2) {
								if (playerController.validateDefenderNumdice(p.getDefenderCountry(),
										Integer.parseInt(inputCommand[1]),
										pl.getListOfPlayers().get(p.getDefenderName()))) {
									if (playerController.defendPhaseDiceRoll(p.getDefenderCountry(),
											Integer.parseInt(inputCommand[1]), p)) {
										String warStarted = playerController.defendingTheBase(p, pl);
										System.out.println("\n" + warStarted);
										actions += "\n" + warStarted;
										if (warStarted.contains("Won")) {
											boolean checkAllCountriesOwned = playerController.checkGameEnd(pl);
											if (checkAllCountriesOwned) {
												System.out.println("\n" + p.getAttackerName() + " won the Risk Game");
												System.out.println("\nThe game is ended");
												System.exit(0);
											}
										}
										addToCommands = true;
									}
								} else {
									System.out.println("\nNumber of Dice Played is invalid");
									actions += "\nNumber of Dice Played is invalid";
									addToCommands = false;
								}
							} else {
								System.out.println("\nValue of numdice is not valid");
								actions += "\nValue of numdice is not valid";
								addToCommands = false;
							}
						} else {
							System.out.println("\ndefend command format is incorrect");
							actions += "\ndefend command format is incorrect";
							addToCommands = false;
						}

					} else {
						System.out.println("\ndefend command can be performed only after attack command is performed");
						actions += "\ndefend command can be performed only after attack command is performed";
						addToCommands = false;
					}
				} else {
					System.out.println("\ndefend command cannot be performed in " + p.getGameState() + " phase");
					actions += "\ndefend command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;

			case "attackmove":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("ATTACK")) {
					if (inputCommand.length == 2) {

						if ((p.isAllOutPerformed()
								|| inputCommandsList.get(inputCommandsList.size() - 1).equals("defend"))) {
							if (playerController.isvalidAttackMove(Integer.parseInt(inputCommand[1]), p.getDiceRolled(),
									p.getConqueredCountries(), p, pl.getListOfPlayers().get(p.getAttackerName())
											.getOwnedCountriesArmiesList().get(p.getAttackerCountry()))) {
								String armyMoved = playerController.movingArmyToConqueredCountry(
										Integer.parseInt(inputCommand[1]), pl.getListOfPlayers(), p, gm);
								System.out.println("\n" + armyMoved);
								actions += "\n" + armyMoved;
								addToCommands = true;

							} else {
								System.out
										.println("\nNum of army move has to be greater or equal to dice Rolled to win "
												+ "& attacker should have atleast one army left in his own country");
								actions += "\nNum of army move has to be greater or equal to dice Rolled to win "
										+ "& attacker should have atleast one army left in his own country";
								addToCommands = false;
							}

						} else {
							System.out.println("\nCannot perform attackmove command if the defend is not performed or "
									+ "the defender country is not yet conquered");
							result += "\nCannot perform attackmove command if the defend is not performed or "
									+ "the defender country is not yet conquered";
							addToCommands = false;
						}
					} else {
						System.out.println("\ndefend command format is incorrect");
						actions += "\ndefend command format is incorrect";
						addToCommands = false;
					}
				} else {
					System.out.println("\ndefend command cannot be performed in " + p.getGameState() + " phase");
					actions += "\ndefend command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

				addInputCommandList(addToCommands, inputCommand[0]);
				commandLine();
				break;

			case "fortify":
				actions = p.getActionsPerformed();
				if (p.getGameState().equals("FORTIFY")) {
					if ((inputCommand.length == 4) || (inputCommand.length == 2)) {
						if (!inputCommandsList.get(inputCommandsList.size() - 1).equals("fortify")) {
							if (inputCommand.length == 4) {
								if (checkPlayersTurn(inputCommand[1])) {
									result = playerController.fortify(pl.getListOfPlayers(), inputCommand[1],
											inputCommand[2], Integer.parseInt(inputCommand[3]), gm.getCountries(),
											gm.getBoundries());
									System.out.println("\n" + result);
									actions += "\n" + result;
									if (result.contains("success") || (result.contains("does not own"))) {
										addToCommands = true;
										if (p.getConqueredCountries().size() > 0) {
											playerController.addGameCardsToAttacker(
													pl.getListOfPlayers().get(p.getAttackerName()), p, gm);
										}
										actions = "";
										clearPlayerObject();
										setPlayerTurn();
										p.setGameState("REINFORCE");
									} else {
										addToCommands = false;
									}
								} else {
									System.out.println(
											"\nCannot fortify army for the country, it is not the turn of player");
									actions += "\nCannot fortify army for the country, it is not the turn of player";
									addToCommands = false;
								}
							} else if (inputCommand.length == 2) {
								if (inputCommand[1].equals("-none")) {
									System.out.println("\nFortification none completed");
									if (p.getConqueredCountries().size() > 0) {
										playerController.addGameCardsToAttacker(
												pl.getListOfPlayers().get(p.getAttackerName()), p, gm);
									}
									actions = "";
									clearPlayerObject();
									setPlayerTurn();
									p.setGameState("REINFORCE");
									addToCommands = true;
								} else {
									System.out.println("\nfortify command format is incorrect");
									actions += "\nfortify command format is incorrect";
									addToCommands = false;
								}
							} else {
								System.out.println("\nfortify command format is incorrect");
								actions += "\nfortify command format is incorrect";
								addToCommands = false;
							}
						} else {
							System.out.println(
									"\nFortification cannot be performed, only one fortification is allowed per turn");
							actions += "\nFortification cannot be performed, only one fortification is allowed per turn";
							addToCommands = false;
						}

					} else {
						System.out.println("\nfortify command format is incorrect");
						actions += "\nfortify command format is incorrect";
						addToCommands = false;
					}
				} else {
					System.out.println("\nfortify command cannot be performed in " + p.getGameState() + " phase");
					actions += "\nfortify command cannot be performed in " + p.getGameState() + " phase";
					addToCommands = false;
				}

				p.setActionsPerformed(actions);
				p.notifyToObserver();
				pl.notifyToObserver(p);

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
					if (pl.getListOfPlayers().size() > 0) {
						armies = (pl.getListOfPlayers().get(playerName)).getOwnedCountriesArmiesList().get(countryName);
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
			inputCommandsList.add(command);
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
		int totalArmiesCount = cons.NO_PLAYER_ARMIES.get(playersSetup.size());
		for (String str : pl.getListOfPlayers().keySet()) {
			Player p = pl.getListOfPlayers().get(str);
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
	 * @author Sai Krishna
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

	/**
	 * This method is used for adding the cards for the total game
	 * 
	 * @author Sai Krishna
	 */
	private void addGameCards() {
		gm.getTotalCardsList().clear();
		int cardCount = (int) Math.floor((gm.getCountries().size()) / 3.0);
		for (int i = 0; i < cardCount; i++) {
			gm.getTotalCardsList().add("INFANTRY");
		}
		for (int i = 0; i < cardCount; i++) {
			gm.getTotalCardsList().add("CAVALRY");
		}
		for (int i = 0; i < cardCount; i++) {
			gm.getTotalCardsList().add("ARTILLERY");
		}
		if ((cardCount * 3) != gm.getCountries().size()) {
			int remainingCount = (gm.getCountries().size()) - (cardCount * 3);
			for (int i = 0; i < remainingCount; i++) {
				gm.getTotalCardsList().add("ARTILLERY");
			}
		}

	}

	/**
	 * This method is used for setting the player turn
	 * 
	 * @author Sai Krishna
	 */
	private void setPlayerTurn() {
		if ((playersSetup.indexOf(p.getCurrentPlayerTurn())) + 1 < playersSetup.size()) {
			p.setCurrentPlayerTurn(playersSetup.get((playersSetup.indexOf(p.getCurrentPlayerTurn())) + 1));
		} else {
			p.setCurrentPlayerTurn(playersSetup.get(0));
		}
	}

	/**
	 * This method is used for clearing the player objects at the end of player's
	 * turn
	 * 
	 * @author Sai Krishna
	 */
	private void clearPlayerObject() {
		p.setActionsPerformed("");
		p.setAttackerName("");
		p.setDefenderName("");
		p.setAttackerCountry("");
		p.setDefenderCountry("");
		p.setAttackerDice(new ArrayList<Integer>());
		p.setDefenderDice(new ArrayList<Integer>());
		p.setDiceRolled(0);
		p.setCardBonusArmy(0);
		p.setCardReward(0);
		p.setAvailableReinforceArmies(0);
		p.setConqueredCountries(new ArrayList<String>());
		p.setAllOutPerformed(false);
	}
	/**
	 * This Method determines the behaviour of a player and perform its moves
	 * @param strategyName the name of the strategy
	 * @return success if strategy executed successfully
	 * @author Ashish Chaudhary
	 */
	public String executeBehaviour(String strategyName) {

		String result = null;
		switch (strategyName) {

		/*
		 * case AGGRESSIVE: behavior = new AI_Aggressive(this, ref_game); break; case
		 * BENEVOLENT: behavior = new AI_Benevolent(this, ref_game); break; case RANDOM:
		 * behavior = new AI_Random(this, ref_game); break;
		 */
		case "Cheater":
			behaviour.setStrategy(new CheaterStrategy());
			result = behaviour.executeBehaviour(gm, pl, p);
			if(result.equals("Success")) {
				clearPlayerObject();
				setPlayerTurn();
				p.setGameState("REINFORCE");
			}
			executeBehaviour(pl.getListOfPlayers().get(p.getCurrentPlayerTurn()).getStrategy());
			break;
		/*
		 * case "human": behaviour = new StrategyController(); behaviour.setStrategy(new
		 * HumanStrategy()); result = behaviour.executeBehaviour(gm, pl, player); break;
		 */
		case "Human":
			commandLine();
			break;

		default:
			break;
		}
		return null;
	}
}
