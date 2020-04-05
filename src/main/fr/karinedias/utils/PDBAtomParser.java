package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.fr.karinedias.model.Atom;

public class PDBAtomParser {

	/*
	 * TODO: create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName Change
	 * the data structure of ArrayList to Iterator objects (Iterator or
	 * listIterator) Use forEach loops instead of for loops ?
	 */

	private StringBuilder content = null;
	public List<Atom> listOfAtoms = new ArrayList<Atom>();

	public PDBAtomParser(StringBuilder contentOfFile) {

		this.setContent(contentOfFile);
	}

	// TODO for performance :
	// use HashMap instead of List ?
	// use indexOf instead of StringTokenizer ?

	/**
	 * This method separates all lines representing atom entries using the line
	 * break character. Each atom is then stored in an arrayList Input : String
	 * representing all ATOMS Output : Transforming the String into ArrayList
	 * 
	 * @param contentAtom a String object containing all lines with the word "ATOM"
	 * @return an ArrayList of String which stores each ATOM entry of the PDBFile
	 */
	public static List<String> getAllAtomEntries(String contentAtom) {

		String atomEntryDelimiter = "\n";
		StringTokenizer allAtomEntries = new StringTokenizer(contentAtom, atomEntryDelimiter);
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

	// TODO: parser for parsing all atom entries (String) in the form of Atom object
	public static void parseAllAtomEntries(String atomData) {

		//trim all whitespaces from string 
		atomData = atomData.replaceAll("\\s+", "");
		System.out.println(atomData);
		
		// pattern only catches : ATOM 1541 O OG
		String pattern = "ATOM(\\d{1,5})+([CNO]{1})+([CNO][A-Z]{0,2})";
		
		//ATOM   3385 O OE1   . GLN A 1 469 ? 50.483 -13.845 -3.263  1.00 37.80 ? 487  GLN A OE1   1
		String pattern2 = "ATOM\\s+(\\d{1,5})\\s+([CNO]{1})\\s+([CNO][A-Z]{0,2})\\s+[.]\\s([A-Z]{3})";
		String pattern3 = "ATOM(\\d{1,5})"
				+ "([CNO]{1})"
				+ "([CNO]{1}[A-Z]{0,2}|[CNO]{1}[A-Z]{1}[0-9]{0,3})" //rewrite to catch C/O/CB, CG1, NH1, NE, 
				+ "[.]([A-Z]{3})"
				+ "([A-Z]{1})"
				+ "([0-9]{1}[0-9]{1,3}).+"
				+ "(-?[0-9]{1,4}\\.[0-9]{3})"
				+ "(-?[0-9]{1,4}\\.[0-9]{3})";

		
		Pattern atomEntry = Pattern.compile(pattern3);
		Matcher m = atomEntry.matcher(atomData);

		System.out.println("Je compte " + m.groupCount() + " groupes");
		
		if (m.find()) {
			int atomSerialNumber1 = Integer.parseInt(m.group(1));
			System.out.println("1. ATOM NUMBER : " + atomSerialNumber1);
			
			char atomName = m.group(2).charAt(0);
			System.out.println("2. ATOM NAME : " + atomName);
			
			String alternateLocationIndicator = m.group(3);
			System.out.println("3. ALTERNATE LOCATION : " + alternateLocationIndicator);
			//TODO : verify if it's a know residue with interface Residue ??
			String residueName = m.group(4);
			System.out.println("4. RESIDUE NAME : " + residueName);
			
			char chainIndentifier = m.group(5).charAt(0);
			System.out.println("5. CHAIN IDENTIFIER : " + chainIndentifier + "\n");
		} else {
			System.out.println("NO MATCH");
		}
		
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

	public static void getListOfAtoms(int resNumber, List<String> allAtomEntries) { // change name

		int nbAtomsEntries = allAtomEntries.size();

		for (int i = 0; i < nbAtomsEntries; i++) {

		}
		getAtomTokens(allAtomEntries);

	}

	// FOR TESTING PURPOSES :
	public static void main(String[] args) {

//		String filenameDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
//		String filenameMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/2b5i.cif";
//		String filenameWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";
//
//		PDBAtomRetriever myPDBFfile = new PDBAtomRetriever(filenameDebian);
//		try {
//
//			StringBuffer myPDBfileAtoms = myPDBFfile.getAtoms();// getAtoms() : 3123 ms VS getAtoms2() : 4850 ms
//			String atomsString = myPDBfileAtoms.toString(); // Conversion of StringBuffer into String
//			List<String> allAtomEntries = getAllAtomEntries(atomsString);
//
//			// System.out.println(allAtomEntries.get(0));
//			System.out.println(getAtomTokens(allAtomEntries).get(0));
//
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
//
//			// test the method getAtomCoordinates :
//			List<String> myatom = getAtomTokens(allAtomEntries);
//			HashMap<String, List<Double>> mycoordinates = new HashMap<String, List<Double>>();
//			mycoordinates = getAtomCoordinates(myatom);
//			System.out.println(mycoordinates.toString());
//			System.exit(0);
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		
		//testing parsing of atoms parseAllAtomEntries(List<String> atoms)
		String test1 = "ATOM  1536 N  N   . SER B 2 44  ? -11.077 -7.959  -17.622 1.00 25.51 ? 44  SER B N   1 \n";
		String test2 = "ATOM   1537 C  CA  . SER B 2 44  ? -12.181 -8.781  -18.189 1.00 26.24 ? 44  SER B CA  1 \n";
		String test3 = "ATOM   1538 C  C   . SER B 2 44  ? -11.823 -10.272 -18.230 1.00 24.54 ? 44  SER B C   1 \n";
		String test4 = "ATOM   3316 C CD2   . LEU A 1 460 ? 43.943 -4.280  -7.943  1.00 23.17 ? 478  LEU A CD2   1\n"; 
		String test5 = "ATOM   3317 N N     . ARG A 1 461 ? 47.184 -4.522  -12.278 1.00 20.43 ? 479  ARG A N     1\n"; 
		String test6 = "ATOM   1541 O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
		String test7 = "ATOM  tzrzfz O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
		String test8 = "";
		String test9 = null;

		parseAllAtomEntries(test1);
		parseAllAtomEntries(test2);
		parseAllAtomEntries(test3);
		parseAllAtomEntries(test4);
		parseAllAtomEntries(test5);
		parseAllAtomEntries(test6);
		parseAllAtomEntries(test7);
		parseAllAtomEntries(test8);
		try {
		parseAllAtomEntries(test9);
		} catch (NullPointerException exc) {
			System.out.println("The String was empty");
		}
	}

	public StringBuilder getContent() {
		return content;
	}

	public void setContent(StringBuilder content) {
		this.content = content;
	}

}
