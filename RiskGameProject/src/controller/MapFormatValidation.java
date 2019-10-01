package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 
 * This class contains Map validation information
 *
 */
public class MapFormatValidation {

	/**
	 * Method validates the map file as per given format
	 * 
	 * @param file
	 * @return
	 * @throws FileNotFoundException
	 */
	public boolean validateFile(File file) throws FileNotFoundException {

		boolean flag = false;
		Scanner validateScanner = new Scanner(file);

		while (validateScanner.hasNext()) {
			String validateContinents = validateScanner.nextLine();
			if (validateContinents.equals("[continents]")) { //continents found
				

				while (validateScanner.hasNext()) {

					String validateCountries = validateScanner.nextLine();
					if (validateCountries.equals("[countries]")) { //countries found
						

						while (validateScanner.hasNext()) {
							String validateBorders = validateScanner.nextLine();

							if (validateBorders.equals("[borders]")) { //borders found
								
								flag = true;
							}
						}

					}

				}
			} else
				flag = false;

		}

		validateScanner.close();
		return flag;

	}

}
