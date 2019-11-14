package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class PDBAtomParser extends PDBAtomRetriver {

	/*
	 * TODO: create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName
	 * Change the data structure of ArrayList to Iterator objects (Iterator or listIterator)
	 * Use forEach loops instead of for loops ?
	 */

	public PDBAtomParser() {
		super();
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

		String filenameDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		String filenameMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/2b5i.cif";
		
		PDBAtomRetriver myPDBFfile = new PDBAtomRetriver(filenameMacOS);
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
