package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDBAtomParser extends PDBAtomRetriver {

	/*
	 * TODO: create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName
	 */

	public PDBAtomParser() {
		super();
	}

	public static void coordinatesParser() {

		// look for coordinates :
//		Pattern coordinates = Pattern.compile("-?\\d{1-3}.\\d{3}");
//		Matcher matcher = coordinates.matcher(infosAtomes[k].toString()); //?
//		while (matcher.find()) {
//			System.out.println(matcher.toString());
//		}
	}

	/**
	 * This method separates all lines representing atom entries using the line
	 * break character. Each atom is then stored in an arrayList
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

	public static ArrayList<String> getOneAtomEntry(ArrayList<String> allAtomEntries) {

		String atomRecordDelimiter = " ";
		int nAtoms = allAtomEntries.size(); // 3795
		// ArrayList contenant les données de chaque atome : listOfAtomRecord

		ArrayList<String> listOfAtomData = new ArrayList<String>(); // données d'une entrée ATOM
		for (int i = 0; i < nAtoms; i++) {
			// listOfAtomRecord.addAll(allAtomEntries);
			StringTokenizer oneAtomEntry = new StringTokenizer(allAtomEntries.get(i), atomRecordDelimiter);

			for (int j = 0; j < oneAtomEntry.countTokens(); j++) {
				// System.out.print(oneAtomEntry.nextElement());//CECI FONCTIONNE !!!
				listOfAtomData.add(j, oneAtomEntry.nextToken());
//			int tokens = oneAtomEntry.countTokens();
//			listOfAtomData.add(oneAtomEntry.nextToken());
			}
		}

		return listOfAtomData;

	}

	// FOR TESTING PURPOSES :
	public static void main(String[] args) {

		String filename = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		PDBAtomRetriver myPDBFfile = new PDBAtomRetriver(filename);
		try {
			String myPDBfileAtoms = myPDBFfile.getAtoms();

			int iterations = getAllAtomEntries(myPDBfileAtoms).size();
			for (int i = 0; i < iterations; i++) {
				System.out.println(getOneAtomEntry(getAllAtomEntries(myPDBfileAtoms)).get(i));
				// System.out.println(getAllAtomEntries(myPDBfileAtoms).get(i));
				// does not work
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
