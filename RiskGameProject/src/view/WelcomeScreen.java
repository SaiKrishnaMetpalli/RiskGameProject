package view;

import java.util.Scanner;

public class WelcomeScreen {
	
	String input; //Used for storing input
	Scanner sc=new Scanner(System.in);
	
	/**
	 * This method is used for displaying the Welcome Screen
	 */
	public void welcomeGameDisplay()
	{
		System.out.println();
		System.out.println("Welcome to Risk Game");
		System.out.println("1.New Game");
		System.out.println("2.Exit");		
		input=sc.nextLine();
		switch(input)
		{
			case "1":
				GameSelection gs=new GameSelection();
				gs.gameSelectionDisplay();
				break;
			case "2":
				System.exit(0);
				break;
			default:
				System.out.println("You have entered wrong input. Please enter correct input");
				welcomeGameDisplay();
				break;				
		}		
	}

}
