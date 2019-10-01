package view;

import java.util.Scanner;

/**
 * This class is used for adding and removing players from the game 
 */
public class PlayerSelection {
	
	String input;
	Scanner sc=new Scanner(System.in);
	
	/**
	 * This method is used for displaying the player selection menu
	 */
	public void playerSelectionDisplay()
	{
		System.out.println();
		System.out.println("Player Selection Menu");
		System.out.println("1.Add Player");
		System.out.println("2.Remove Player");
		System.out.println("3.Command Line");
		System.out.println("4.Exit");
		input=sc.nextLine();
		switch(input)
		{
			case "1":
				
				break;
			case "2":
				
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
				playerSelectionDisplay();
				break;				
		}

	}

}
