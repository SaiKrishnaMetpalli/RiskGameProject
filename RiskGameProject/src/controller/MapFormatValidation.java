package controller;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * This class contains Map validation information
 *
 */
public class MapFormatValidation {

	/**
	 * Method validates the map file as per given format
	 * 
	 * @param file it is the file which is checked for validation
	 * @return flag which is true if map format is valid otherwise false
	 * @throws FileNotFoundException
	 * @author Ashish
	 */
	public boolean validateFile(File file) throws FileNotFoundException {

		boolean flag = false;
		Scanner validateScanner = new Scanner(file);
		String validateLength;
		while (validateScanner.hasNext()) {
			String validateContinents = validateScanner.nextLine();
			if (validateContinents.equals("[continents]")) { // continents found
				validateLength = validateScanner.nextLine();
				while (!validateLength.equals("")) {
					String[] lineLength = validateLength.split(" ");
					if (lineLength.length == 3) {
						validateLength = validateScanner.nextLine();
						continue;
					} else {
						flag = false;
						return flag;
					}

				}
				while (validateScanner.hasNext()) {

					String validateCountries = validateScanner.nextLine();
					if (validateCountries.equals("[countries]")) { // countries found
						validateLength = validateScanner.nextLine();
						while (!validateLength.equals("")) {
							String[] lineLength = validateLength.split(" ");
							if (lineLength.length == 5) {
								validateLength = validateScanner.nextLine();
								continue;

							} else
								flag = false;
							return flag;
						}
						while (validateScanner.hasNext()) {
							String validateBorders = validateScanner.nextLine();

							if (validateBorders.equals("[borders]")) { // borders found

								flag = true;
								return flag;
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
