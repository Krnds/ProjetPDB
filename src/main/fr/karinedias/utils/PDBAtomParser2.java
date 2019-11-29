package main.fr.karinedias.utils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class PDBAtomParser2 extends PDBResidueParser {

	private final Map<Integer, String> residues = new HashMap<Integer, String>();

	public static void main(String[] args) {

		String filenameWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

		PDBAtomRetriever myPDBFfile = new PDBAtomRetriever(filenameWindows);
		try {

			// TESTING EXECUTION TIMES
			Instant start = Instant.now();
			StringBuffer myPDBfileAtoms = myPDBFfile.getAtoms();
			System.out.println(myPDBfileAtoms);
			// Measure execution time for this method
			methodToTime();
			Instant finish = Instant.now();

			long timeElapsed = Duration.between(start, finish).toMillis(); // in millis
			System.out.println("Execution time in milliseconds : " + timeElapsed);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private static void methodToTime() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
