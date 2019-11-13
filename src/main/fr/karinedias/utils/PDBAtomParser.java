package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PDBAtomParser extends PDBAtomRetriver {

	/*
	 * TODO: create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName
	 */

	public PDBAtomParser() {
		super();
	}

	public static void getListOfResidue() {

		//From getAllAtomEntries, delimit the atoms by newline but regroup them by number of residue

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
			ArrayList<String> allatomsofPDBfile = getAllAtomEntries(myPDBfileAtoms);

//			System.out.println(allatomsofPDBfile.toString());
			int iterations = allatomsofPDBfile.size();
			for (int i = 0; i < iterations; i++) {
				System.out.print(getOneAtomEntry(allatomsofPDBfile).get(i));

			}
			
			for (String string : allatomsofPDBfile) {
				
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
