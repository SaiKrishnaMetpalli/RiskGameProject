package view;

import java.util.Scanner;

/**
 * This class gives information about game selection
 */
public class GameSelection {
	
	String input;
	Scanner sc=new Scanner(System.in);
	
	/**
	 * This method is used for displaying the game selection menu
	 */
	public void gameSelectionDisplay()
	{
		System.out.println("Game Selection Menu");
		System.out.println("1.Map Selection");
		System.out.println("2.Player Selection");
		System.out.println("3.Command Line");
		System.out.println("4.Exit");
		input=sc.nextLine();
		switch(input)
		{
			case "1":
				MapSelection ms=new MapSelection();
				ms.mapSelectionDisplay();
				break;
			case "2":
				PlayerSelection ps=new PlayerSelection();
				ps.playerSelectionDisplay();
				break;
			case "3":
				CommandLine cl=new CommandLine();
				cl.commandLine();
				break;
			case "4":
				System.exit(0);
				break;
			default:
				System.out.println("You have entered wrong input. Please enter correct input");
				gameSelectionDisplay();
				break;				
		}

	}
	
}
