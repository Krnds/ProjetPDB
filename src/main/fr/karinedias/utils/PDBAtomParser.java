package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;

import main.fr.karinedias.model.Residue;

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

	public static List<String> getAtomTokens(String atomString) {

		String atomRecordDelimiter = " ";
		StringTokenizer data = new StringTokenizer(atomString, atomRecordDelimiter);
		List<String> tokens = new ArrayList<>();
		int nbTokens = data.countTokens();

		for (int j = 0; j < nbTokens; j++) {
			tokens.add(data.nextToken());
		}

		return tokens;

	}

	public static HashMap<String, List<Double>> getAtomcoordinates(List<String> atomTokens) {

		Map<String, List<Double>> coordinates = new HashMap<String, List<Double>>();
		Double x = Double.parseDouble(atomTokens.get(10));
		Double y = Double.parseDouble(atomTokens.get(11));
		Double z = Double.parseDouble(atomTokens.get(12));
		Double[] list
		coordinates.put(atomTokens.get(1), );
		return null;

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

	// WTF ? useful ?
	public static boolean getListOfAtomsofResidue(Residue res, List<String> allAtomEntries) { // change name

		int residueNumber = res.getResidueNumber();
		boolean found = false;
		for (int i = 0; i < allAtomEntries.size(); i++) {
			if (allAtomEntries.contains(Integer.toString(residueNumber))) {
				found = true;

			}
		}
		return found;
	}

	public static void getInfoAtoms(List<String> atomEntry) {

		// Pattern alternateLocation = Pattern.compile(regex)

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

			StringBuffer myPDBfileAtoms = myPDBFfile.getAtoms();// getAtoms() : 3123 ms VS getAtoms2() : 4850 ms
			String atomsString = myPDBfileAtoms.toString(); // Conversion of StringBuffer into String
			List<String> allAtomEntries = getAllAtomEntries(atomsString);
			List<String> atomEntry = getOneAtomEntry(allAtomEntries);

//			for (int i = 0; i < allAtomEntries.size(); i++) {
//				System.out.print(allAtomEntries.get(i));
//				System.out.println();
//			}

			System.out.print("Atom n°56 coordinates : ");
			List<Double> coordinates = new ArrayList<Double>(3);
			// coordinates.add(allAtomEntries.get(55)).get(10));
			coordinates.add(0, Double.parseDouble(getAtomTokens(allAtomEntries.get(55)).get(10)));
			coordinates.add(1, Double.parseDouble(getAtomTokens(allAtomEntries.get(55)).get(11)));
			coordinates.add(2, Double.parseDouble(getAtomTokens(allAtomEntries.get(55)).get(12)));
			System.out.println(coordinates.toString());
			System.exit(0);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
