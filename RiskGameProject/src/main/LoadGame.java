package main;

import java.io.FileNotFoundException;

import controller.FileReading;
/**
 * This class contains the Main class 
 *
 */
public class LoadGame {

	public static void main(String argsp[]) throws FileNotFoundException
	{
		FileReading fileReading = new FileReading();
		fileReading.input();
	}
}
