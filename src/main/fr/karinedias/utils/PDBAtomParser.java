package main.fr.karinedias.utils;

import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

public class PDBAtomParser {

	/*
	 * TODO: create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName Change
	 * the data structure of ArrayList to Iterator objects (Iterator or
	 * listIterator) Use forEach loops instead of for loops ?
	 */

	public PDBAtomParser() {
	}

	// TODO for performance :
	// use HashMap instead of List ?
	// use indexOf instead of StringTokenizer ?

	/**
	 * This method separates all lines representing atom entries using the line
	 * break character. Each atom is then stored in an arrayList Input : String
	 * representing all ATOMS Output : Transforming the String into ArrayList
	 * 
	 * @param atomString a String object containing all lines with the word "ATOM"
	 * @return an ArrayList of String which stores each ATOM entry of the PDBFile
	 */
	public static ArrayList<String> getAllAtomEntries(String atomString) {

		String atomEntryDelimiter = "\n";
		StringTokenizer allAtomEntries = new StringTokenizer(atomString, atomEntryDelimiter);
		int tokens = allAtomEntries.countTokens(); // number of iterations of atom entries
		ArrayList<String> listOfAtoms = new ArrayList<String>(tokens); // create an ArrayList of n atomic entries

		for (int i = 0; i < tokens; i++) {
			listOfAtoms.add(allAtomEntries.nextToken());
		}
		return listOfAtoms;
	}

	/**
	 * Method for storing each data ATOM entry in a ArrayList
	 * 
	 * @param allAtomEntries
	 * @return an ArrayList of String which stores each ATOM entry of the PDBFile
	 */

	public static List<String> getOneAtomEntry(List<String> allAtomEntries) {

		String atomRecordDelimiter = " ";
		int numberOfAtoms = allAtomEntries.size();
		// ArrayList for the data of each atom
		List<String> listOfAtomData = new ArrayList<String>(numberOfAtoms); // data of an ATOM entry
		for (int i = 0; i < numberOfAtoms; i++) {
			StringTokenizer oneAtomEntry = new StringTokenizer(allAtomEntries.get(i), atomRecordDelimiter);

			for (int j = 0; j < oneAtomEntry.countTokens(); j++) {
				listOfAtomData.add(j, oneAtomEntry.nextToken());
			}
		}

		return listOfAtomData;

	}

	private static void methodToTime() {
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// FOR TESTING PURPOSES :
	public static void main(String[] args) {

		String filenameDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		String filenameMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/2b5i.cif";
		String filenameWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

		PDBAtomRetriever myPDBFfile = new PDBAtomRetriever(filenameWindows);
		try {

			// TESTING EXECUTION TIMES
			Instant start = Instant.now();

			StringBuffer myPDBfileAtoms = myPDBFfile.getAtoms();// getAtoms() : 3123 ms VS getAtoms2() : 4850 ms
			String atomsString = myPDBfileAtoms.toString(); // Conversion of StringBuffer into String
			List<String> listOfAtoms = getAllAtomEntries(atomsString);
			List<String> atomEntry = getOneAtomEntry(listOfAtoms);

			for (int i = 0; i < atomEntry.size(); i++) {
				System.out.print(atomEntry.get(i) + "\n");
			}
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

}
