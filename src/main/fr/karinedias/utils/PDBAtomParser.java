package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.fr.karinedias.model.Atom;

public class PDBAtomParser {

/**
 * From the content of a file save in a StringBuilder, parse Atoms and store them into Atom objects.  
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
	
	
	//TODO: change way of doing, because too long, use stringtokenizer and 
	//@date : 12/04/2020
	public List<String> getAllAtomEntries() {

		String[] lines = content.toString().split("\\n");
		// TODO compare with StringTokenizer instead of using string array
		List<String> atomEntries = new ArrayList<String>();

		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith("ATOM")) { // quid using contains() ?
				atomEntries.add(lines[i]);
			}
		}
		return atomEntries;
	}
		
	
//
//		String atomEntryDelimiter = "\n";
//		StringTokenizer allAtomEntries = new StringTokenizer(content, atomEntryDelimiter);
//		int tokens = allAtomEntries.countTokens(); // number of iterations of atom entries
//		List<String> listOfAtoms = new ArrayList<String>(tokens); // create an ArrayList of n atomic entries
//
//		for (int i = 0; i < tokens; i++) {
//			listOfAtoms.add(allAtomEntries.nextToken());
//		}
//		return listOfAtoms;
		



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
		
		String pattern3 = "ATOM(\\d{1,5})" //group 1 : atom number
				+ "([CNO]{1})" //group 2 : atom name
				+ "([CNO]{1}[A-Z]{0,2}|[CNO]{1}[A-Z]{1}[0-9]{0,3})" //group 3 : atom's alternate location  
				+ "[.]([A-Z]{3})" // group 4 : residue of the atom
				+ "([A-Z]{1})" //group 5 : chain name identifier (single character)
				+ "([0-9]{1})" //group 6 : chain number identifier (single digit)
				+ "([0-9]{1,3})" //group 7 : number of the residue
				+ "([?]{1})" //group 8 : Code for insertion of residues TODO: tester !!!
				+ "(-?[0-9]{1,4}\\.[0-9]{3})" //group 9 : X orthogonal coordinate of the atom
				+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 10 : Y orthogonal coordinate of the atom
				+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 11 : Z rthogonal coordinate of the atom
	

		Pattern atomEntry = Pattern.compile(pattern3);
		Matcher m = atomEntry.matcher(atomData);

		System.out.println("Il y a  " + m.groupCount() + " groupes\n\n");
		
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
			
			char chainNameIndentifier = m.group(5).charAt(0);
			System.out.println("5. CHAIN NAME IDENTIFIER : " + chainNameIndentifier);
			
			int chainNumerIdentifier = Integer.parseInt(m.group(6));
			System.out.println("6. CHAIN NUMBER INDENTIFIER : " + chainNumerIdentifier);
			
			int residueNumer = Integer.parseInt(m.group(7));
			System.out.println("7. REDIDUE NUMBER : " + residueNumer);
			
			char codeInsertionResidue = m.group(8).charAt(0);
			
			System.out.println("8. CODE INSERTION RESIDUE : " + codeInsertionResidue);
			
			float xOrthogonalCoordinate = Float.parseFloat(m.group(9));
			
			System.out.println("9. ORTHONAL COORDINATE OF X AXIS : " + xOrthogonalCoordinate);
			
			float yOrthogonalCoordinate = Float.parseFloat(m.group(10));
			
			System.out.println("10. ORTHONAL COORDINATE OF Y AXIS : " + yOrthogonalCoordinate);
			
			float zOrthogonalCoordinate = Float.parseFloat(m.group(11));
			
			System.out.println("11. ORTHONAL COORDINATE OF Z AXIS : " + zOrthogonalCoordinate);
			
		} else {
			System.out.println("NO MATCH");
		}
		
	}
	
	//Convert the data retrieved by parseAllAtomEntries in the form of an Atom object
	public Atom convertDataToAtom(int atomNumer, char atomName, String altLoc, String resName, char chainName, int chainNumber, int residueNumber, float xCoord, float yCoord, float zCoord) {
		
		Atom atomCreated = new Atom(atomNumer, atomName, altLoc, resName, chainName, chainNumber, residueNumber, xCoord, yCoord, zCoord);
		
		return atomCreated;
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
//		String test1 = "ATOM  1536 N  N   . SER B 2 44  ? -11.077 -7.959  -17.622 1.00 25.51 ? 44  SER B N   1 \n";
//		String test2 = "ATOM   1537 C  CA  . SER B 2 44  ? -12.181 -8.781  -18.189 1.00 26.24 ? 44  SER B CA  1 \n";
//		String test3 = "ATOM   1538 C  C   . SER B 2 44  ? -11.823 -10.272 -18.230 1.00 24.54 ? 44  SER B C   1 \n";
//		String test4 = "ATOM   3316 C CD2   . LEU A 1 460 ? 43.943 -4.280  -7.943  1.00 23.17 ? 478  LEU A CD2   1\n"; 
//		String test5 = "ATOM   3317 N N     . ARG A 1 461 ? 47.184 -4.522  -12.278 1.00 20.43 ? 479  ARG A N     1\n"; 
//		String test6 = "ATOM   1541 O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
//		String test7 = "ATOM  tzrzfz O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
//		String test8 = "";
//		String test9 = null;
//
//		parseAllAtomEntries(test1);
//		parseAllAtomEntries(test2);
//		parseAllAtomEntries(test3);
//		parseAllAtomEntries(test4);
//		parseAllAtomEntries(test5);
//		parseAllAtomEntries(test6);
//		parseAllAtomEntries(test7);
//		parseAllAtomEntries(test8);
//		try {
//		parseAllAtomEntries(test9);
//		} catch (NullPointerException exc) {
//			System.out.println("The String was empty");
//		}
		
		
		//TEST GET ALL ATOM ENTRIES WITH A FILE :
		//TESTING EXECUTION TIME :
		//TODO: change way to do, bc 47 sec. for execution...
		long startTime = System.nanoTime();
		
		FileReader testfile = new FileReader();
		String path = testfile.getFilePath();
		System.out.println("Calling PDBAtomParser...");
		PDBAtomParser fileToParse = new PDBAtomParser(testfile.reader(path));
		System.out.println("Parsing all atoms...");	


		List<String> atoms = new ArrayList<String>();
		atoms.addAll(fileToParse.getAllAtomEntries());

		System.out.println(atoms.get(8));

		
		
		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
		
		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

	public StringBuilder getContent() {
		return content;
	}

	public void setContent(StringBuilder content) {
		this.content = content;
	}

}
