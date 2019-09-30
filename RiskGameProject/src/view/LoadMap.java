package view;

import controller.FileReading;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import model.Continents;
import model.Countries;
import util.CONSTANTS;

import java.util.Scanner;

public class LoadMap {

	

	public static void main(String args[]) throws FileNotFoundException {

		FileReading fr = new FileReading();
		fr.input();

	}
}
