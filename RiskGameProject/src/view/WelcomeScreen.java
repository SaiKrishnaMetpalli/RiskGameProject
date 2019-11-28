package view;

import java.util.Scanner;

/**
 * This class is the first class to take input from user to proceed to commandline
 * @author Sai
 *
 */
public class WelcomeScreen {

	String input; 
	Scanner sc = new Scanner(System.in);

	/**
	 * This method is used for displaying the Welcome Screen
	 * @throws InterruptedException 
	 */
	public void welcomeGameDisplay() throws InterruptedException {
		System.out.println();
		System.out.println("\nWelcome to Risk Game");
		System.out.println("\n1.New Game");
		System.out.println("\n2.Exit");
		System.out.println();
		input = sc.nextLine();
		switch (input) {
		case "1":
			CommandLine cl = new CommandLine();
			cl.commandLine();
			break;
		case "2":
			System.exit(0);
			break;
		default:
			System.out.println("\nYou have entered wrong input. Please enter correct input");
			welcomeGameDisplay();
			break;
		}
	}

}
