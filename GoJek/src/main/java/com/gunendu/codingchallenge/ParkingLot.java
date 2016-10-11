package main.java.com.gunendu.codingchallenge;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.logging.Logger;

public class ParkingLot {

	private static final Logger LOGGER = Logger.getLogger(ParkingLot.class
			.getName());
	private static final int STATUS_CODE_FAILURE = 1;

	private Scanner inputScanner;

	public final WorkBook workBook;

	public ParkingLot() {
		workBook = new WorkBook();
		inputScanner = new Scanner(System.in);
	}

	public void setInputScanner(Scanner inputScanner) {
		this.inputScanner = inputScanner;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ParkingLot parkinglot = new ParkingLot();
		if (args.length > 0) {
			try {
				if (!parkinglot.CommandLineParser(args)) {
					LOGGER.severe("Error: Invalid number of arguments");
					System.exit(STATUS_CODE_FAILURE);
				}
			} catch (FileNotFoundException e) {
				LOGGER.severe("Caught FileNotFoundException: Unable to find the specified input file");
				System.exit(STATUS_CODE_FAILURE);
			}
		}
		parkinglot.processWorkBook();
		System.out.println(parkinglot.getResults());
	}

	public boolean CommandLineParser(String[] args)
			throws FileNotFoundException {
		int argsCount = args.length;
		for (String arg : args) {
			if (new File(arg).exists()) {
				setInputScanner(new Scanner(new File(arg)));
				argsCount--;
			}
		}
		return argsCount == 0;
	}

	public void processWorkBook() {
		workBook.readInputFile(inputScanner);
	}
	
	public String getResults() {
		return workBook.getOutput();
	}

}
