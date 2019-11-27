package test;

import static org.junit.Assert.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import controller.CommonController;
import controller.MapFormatValidation;
import controller.MapSelectionController;
import model.Continents;
import model.Countries;
import model.GameMap;
import model.GameState;
import model.Player;
import model.PlayersList;

public class TestCommonMethods {
	CommonController cc;
	MapSelectionController msc;
	MapFormatValidation mfv;
	HashMap<Integer, Continents> continents;
	HashMap<Integer, Countries> countries;
	HashMap<Integer, ArrayList<Integer>> boundries;
	String fileName;
	GameMap gm;
	PlayersList pl;
	Player p;
	GameState gs;
	boolean flag;
	
	@Before
	public void setUp() throws FileNotFoundException {
		fileName = "risk.map";
		msc = new MapSelectionController();
		gm = new GameMap();
		continents = new HashMap<Integer, Continents>();
		countries = new HashMap<Integer, Countries>();
		boundries = new HashMap<Integer, ArrayList<Integer>>();
		msc.gameMapReading(continents, countries, boundries, fileName);
		gs = new GameState();
		cc = new CommonController();
		
	}
	@Test
	public void TestGetContinentNumberfromName() {
		assertEquals(cc.getContinentNum(continents, "Oceania"),6);
	}
	@Test
	public void TestGetContinentNumberfromNameFail() {
		assertNotEquals(cc.getContinentNum(continents, "Oceania"),4);
	}
	
	@Test
	public void TestGetCountryNumberfromName() {
		assertEquals(cc.getCountryNumberByName(countries, "Japan"),37);
	}
	
	@Test
	public void TestGetCountryNumberfromNameFail() {
		assertNotEquals(cc.getCountryNumberByName(countries, "Japan"),3);
	}
	
	@Test
	public void TestGetCountryNameByNum() {
		assertEquals(cc.getCountryNameByNum(countries, 37),"Japan");
	}

	@Test
	public void TestGetCountryNameByNumFail() {
		assertNotEquals(cc.getCountryNameByNum(countries, 37),"Canada");
	}

}
