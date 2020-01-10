package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

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
	public static List<String> getAllAtomEntries(String atomString) {

		String atomEntryDelimiter = "\n";
		StringTokenizer allAtomEntries = new StringTokenizer(atomString, atomEntryDelimiter);
		int tokens = allAtomEntries.countTokens(); // number of iterations of atom entries
		List<String> listOfAtoms = new ArrayList<String>(tokens); // create an ArrayList of n atomic entries

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

	public static List<String> getAtomTokens(List<String> allAtomEntries) {

		String atomRecordDelimiter = " ";
		int numberOfAtoms = allAtomEntries.size();
		// ArrayList for the data of each atom
		List<String> listOfAtomData = new ArrayList<String>(numberOfAtoms); // data of an ATOM entry
		for (int i = 0; i < numberOfAtoms; i++) {
			StringTokenizer oneAtomEntry = new StringTokenizer(allAtomEntries.get(i), atomRecordDelimiter);

			int nbTokens = oneAtomEntry.countTokens();
			for (int j = 0; j < nbTokens; j++) {
				listOfAtomData.add(j, oneAtomEntry.nextToken());
				System.out.println(listOfAtomData.get(j));
			}
		}

		return listOfAtomData;

	}

	// does the same thing as getAtomTokens but take a String argument instead of
	// List<String>
	public static List<String> getAtomTokens2(String atomString) {

		String atomRecordDelimiter = " ";
		StringTokenizer data = new StringTokenizer(atomString, atomRecordDelimiter);
		int nbTokens = data.countTokens();
		List<String> tokens = new ArrayList<String>(nbTokens);

		for (int i = 0; i < nbTokens; i++) {
			tokens.add(data.nextToken());
		}

		return tokens;

	}

	// From a list of AtomTokens return a HashMap of coordinates with the number of
	// the atom
	public static HashMap<String, List<Double>> getAtomCoordinates(List<String> atomTokens) {

		Map<String, List<Double>> coordinates = new HashMap<String, List<Double>>();
		Double x = Double.parseDouble(atomTokens.get(10));
		Double y = Double.parseDouble(atomTokens.get(11));
		Double z = Double.parseDouble(atomTokens.get(12));
		List<Double> listCoordinates = Arrays.asList(new Double[] { x, y, z });
		coordinates.put(atomTokens.get(1), listCoordinates);

		return (HashMap<String, List<Double>>) coordinates;

	}

	// TODO WTF ? useful ?
//	ATOM   157  N N   . LEU A 1 25  ? 5.780   -29.926 34.503  1.00 60.26 ? 25  LEU A N   1 
//			ATOM   158  C CA  . LEU A 1 25  ? 6.098   -29.478 35.846  1.00 61.13 ? 25  LEU A CA  1 
//			ATOM   159  C C   . LEU A 1 25  ? 5.569   -30.437 36.914  1.00 61.53 ? 25  LEU A C   1 
//			ATOM   160  O O   . LEU A 1 25  ? 6.310   -30.833 37.822  1.00 61.71 ? 25  LEU A O   1 
//			ATOM   161  C CB  . LEU A 1 25  ? 5.580   -28.051 36.081  1.00 61.10 ? 25  LEU A CB  1 
//			ATOM   162  C CG  . LEU A 1 25  ? 5.989   -27.423 37.422  1.00 61.53 ? 25  LEU A CG  1 
//			ATOM   163  C CD1 . LEU A 1 25  ? 7.431   -26.905 37.383  1.00 61.18 ? 25  LEU A CD1 1 
//			ATOM   164  C CD2 . LEU A 1 25  ? 5.029   -26.312 37.805  1.00 60.62 ? 25  LEU A CD2 1 

	public static void getListOfAtoms(int resNumber, List<String> allAtomEntries) { // change name

		int nbAtomsEntries = allAtomEntries.size();

		for (int i = 0; i < nbAtomsEntries; i++) {

		}
		getAtomTokens(allAtomEntries);

	}

	// FOR TESTING PURPOSES :
	public static void main(String[] args) {

		String filenameDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		String filenameMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/2b5i.cif";
		String filenameWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

		PDBAtomRetriever myPDBFfile = new PDBAtomRetriever(filenameDebian);
		try {

			StringBuffer myPDBfileAtoms = myPDBFfile.getAtoms();// getAtoms() : 3123 ms VS getAtoms2() : 4850 ms
			String atomsString = myPDBfileAtoms.toString(); // Conversion of StringBuffer into String
			List<String> allAtomEntries = getAllAtomEntries(atomsString);

			// System.out.println(allAtomEntries.get(0));
			System.out.println(getAtomTokens(allAtomEntries).get(0));

//			List<String> atomEntry = getAtomTokens(allAtomEntries);
//
//			// print result of oneAtomEntry :
//			for (int i = 0; i < atomEntry.size(); i++) {
//				System.out.println("AtomEntry number " + i + " : " + atomEntry.get(i));
//			}
//
//			// print result of getAtomTokens :
//			List<String> atomTokens = getAtomTokens2(atomsString);
//			int n = atomTokens.size();
//			for (int i = 0; i < n; i++) {
//				System.out.println("AtomToken number " + i + " : " + atomTokens.get(i));
//			}

			// test the method getAtomCoordinates :
//			List<String> myatom = getAtomTokens(allAtomEntries.get(254));
//			HashMap<String, List<Double>> mycoordinates = new HashMap<String, List<Double>>();
//			mycoordinates = getAtomCoordinates(myatom);
//			System.out.println(mycoordinates.toString());
//			System.exit(0);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
