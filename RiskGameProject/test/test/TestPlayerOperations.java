package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;


import controller.PlayerSelectionController;

public class TestPlayerOperations {
	PlayerSelectionController psc;
    ArrayList<String> listofPlayers;
    String playerName;
    
    @Before
    public void setUp() {
    	listofPlayers = new ArrayList<String>(Arrays.asList("Sakib","Sai","Harman","hsgdf"));
    	psc = new PlayerSelectionController();   
    	playerName = "";
    }
 
	@Test
	public void addPlayerTest() {
		String result = psc.addPlayer(listofPlayers, "gagan");
		assertEquals(result, "Success");
		String re=psc.addPlayer(listofPlayers, "gagan");
		assertEquals(re,"Failure");
	}
	@Test
	public void removePlayerTest() {
		String result = psc.removePlayer(listofPlayers, "hsgdf");
		assertEquals(result, "Success");
		String re = psc.removePlayer(listofPlayers,"hsgdf");
		assertEquals(re, "Failure");
	}
}
