package test.java.com.gunendu.codingchallenge;

import main.java.com.gunendu.codingchallenge.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;

@RunWith(Parameterized.class)
public class ParkingLotTest {
	private ParkingLot parkinglotapp;
	private static final int STATUS_CODE_FAILURE = 1;
	private final String expectedResult;
	private String filepath;

	public ParkingLotTest(String filepath, String expectedResult) {
		this.filepath = filepath;
		this.expectedResult = expectedResult;
	}

	@Parameterized.Parameters
	public static Collection<Object[]> configs() {
		return Arrays
				.asList(new Object[][] {
						{
								"file_inputs.txt",
								"Created a parking lot with 6 slots\n"
										+ "Allocated slot number: 1\n"
										+ "Allocated slot number: 2\n"
										+ "Allocated slot number: 3\n"
										+ "Allocated slot number: 4\n"
										+ "Allocated slot number: 5\n"
										+ "Allocated slot number: 6\n"
										+ "Slot number 4 is free\n"
										+ "Slot number 5 is free\n"
										+ "1 KA-01-HH-1234 White\n"
										+ "2 KA-01-HH-9999 White\n"
										+ "3 KA-01-BB-0001 Black\n"
										+ "6 KA-01-HH-3141 Black\n"
										+ "Allocated slot number: 4\n"
										+ "Allocated slot number: 5\n"
										+ "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333, DL-12-AA-9999\n"
										+ "1, 2, 4, 5\n" 
										+ "6\n"
										+ "Not found" },
						{
								"file_inputs1.txt",
								"Created a parking lot with 6 slots\n"
										+ "Allocated slot number: 1\n"
										+ "Allocated slot number: 2\n"
										+ "Allocated slot number: 3\n"
										+ "Allocated slot number: 4\n"
										+ "Allocated slot number: 5\n"
										+ "Allocated slot number: 6\n"
										+ "Slot number 4 is free\n"
										+ "1 KA-01-HH-1234 White\n"
										+ "2 KA-01-HH-9999 White\n"
										+ "3 KA-01-BB-0001 Black\n"
										+ "5 KA-01-HH-2701 Blue\n"
										+ "6 KA-01-HH-3141 Black\n"
										+ "Allocated slot number: 4\n"
										+ "Sorry, parking lot is full\n"
										+ "KA-01-HH-1234, KA-01-HH-9999, KA-01-P-333\n"
										+ "1, 2, 4\n" 
										+ "6\n" 
										+ "Not found" }

				});
	}

	@Before
	public void initialize() {
		parkinglotapp = new ParkingLot();
	}

	@Test
	public void testApp() {
		try {
			parkinglotapp.setInputScanner(new Scanner(new File(filepath)));
		} catch (FileNotFoundException e) {
			System.err
					.println("Caught FileNotFoundException: Unable to find the specified input test file: "
							+ filepath);
			System.exit(STATUS_CODE_FAILURE);
		}
		parkinglotapp.processWorkBook();
		assertEquals(expectedResult, parkinglotapp.getResults());
	}
}