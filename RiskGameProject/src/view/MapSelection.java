package view;

import java.util.Scanner;
import controller.FileReading;

/**
 * This class is used for creating and uploading game map
 */
public class MapSelection {
	
	String input;
	Scanner sc=new Scanner(System.in);
	
	/**
	 * This method is used for displaying the map selection menu
	 */
	public void mapSelectionDisplay()
	{
		System.out.println();
		System.out.println("Map Selection Menu");
		System.out.println("1.File1");
		System.out.println("2.File2");
		System.out.println("3.File3");
		System.out.println("4.Create Map");
		System.out.println("5.Command Line");
		System.out.println("6.Exit");
		input=sc.nextLine();
		switch(input)
		{
			case "1":				
			case "2":				
			case "3":
				FileReading fr=new FileReading();
				try
				{
					fr.input();
				}
				catch(Exception ex)
				{
					
				}				
				break;
			case "4":
				System.exit(0);
				break;
			case "5":
				CommandLine cl=new CommandLine();
				cl.commandLine();
				break;
			case "6":
				System.exit(0);
				break;
			default:
				System.out.println("You have entered wrong input. Please enter correct input");
				mapSelectionDisplay();
				break;				
		}
	}

}
