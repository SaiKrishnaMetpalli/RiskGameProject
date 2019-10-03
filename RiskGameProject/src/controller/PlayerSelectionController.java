package controller;

import java.util.ArrayList;

public class PlayerSelectionController {
	public void playerAddRemove()
	{
		int choice = 0;			
		ArrayList<String> all = new ArrayList<String>();
		while(choice != 5)
		{			
			switch(choice) {
			case 1: // add player name
				System.out.println("Enter the player name");
				//String playerName = sc.nextLine();
				//al1.add(playerName);
				break;
				
			case 2: // remove player name
				System.out.println("Enter the player name");
				//String playerNameRem = sc.nextLine();
				//al1.remove(playerNameRem);
				break;
			case 5: // exit
				System.exit(0);
				break;
			
			default:
				System.out.println("\n==================================");
				System.out.println("\n\t Error! Please Enter Your Choice(1 to 5)");				
				break;
			}
			
		}
	}
}
