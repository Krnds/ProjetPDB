package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.fr.karinedias.exceptions.AtomNotFoundException;
import src.main.fr.karinedias.model.Atom;

public class AtomParser {

	private StringBuilder contentOfFile;

	public AtomParser(StringBuilder content) {

		this.contentOfFile = content;

	}

	public static void main(String[] args) throws AtomNotFoundException {
		// TODO Auto-generated method stub
		

		////////////////// TEST PARSER///////////////
		// testing parsing of atoms parseAllAtomEntries(List<String> atoms)
		String test1 = "ATOM  1536 N  N   . SER B 2 44  ? -11.077 -7.959  -17.622 1.00 25.51 ? 44  SER B N   1 \n";
		String test2 = "ATOM   1537 C  CA  . SER B 2 44  ? -12.181 -8.781  -18.189 1.00 26.24 ? 44  SER B CA  1 \n";
		String test3 = "ATOM   1538 C  C   . SER B 2 44  ? -11.823 -10.272 -18.230 1.00 24.54 ? 44  SER B C   1 \n";
		String test4 = "ATOM   3316 C CD2   . LEU A 1 460 ? 43.943 -4.280  -7.943  1.00 23.17 ? 478  LEU A CD2   1\n";
		String test5 = "ATOM   3317 N N     . ARG A 1 461 ? 47.184 -4.522  -12.278 1.00 20.43 ? 479  ARG A N     1\n";
		String test6 = "ATOM   1541 O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
		String test7 = "ATOM  tzrzfz O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1";
		String test8 = "";
		String test9 = null;

		Atom atom1 = getAtoms(test1);
		System.out.println(atom1.toString());

		Atom atom2 = getAtoms(test2);
		System.out.println(atom2.toString());

		Atom atom3 = getAtoms(test3);
		System.out.println(atom3.toString());

		Atom atom4 = getAtoms(test4);
		System.out.println(atom4.toString());

		try {
			getAtoms(test9);
		} catch (NullPointerException exc) {
			System.out.println("The String was empty");
		}


		System.out.println("Starting the interesting part...\n");
		long startTime = System.nanoTime();
		String fileTestPath = "/home/karine/src/java/ProjetPDB/doc/1XQY.cif";
		FileReader fileTest = new FileReader();
		StringBuilder content = fileTest.reader(fileTestPath);
		AtomParser atomsTest = new AtomParser(content);
		//TEST
		List<String> atomsFound = new ArrayList<String>();
		atomsFound.addAll(atomsTest.parseAtomLines());
		System.out.println("I've found " + atomsFound.size() + " atoms ! Here they are : \n");
		System.out.println(atomsFound.toString());
		
		
		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);

		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

	// getter
	public StringBuilder getContentOfFile() {
		return contentOfFile;
	}

	public static Atom getAtoms(String atomLine) throws AtomNotFoundException {

		// trim all whitespaces from string
		atomLine = atomLine.replaceAll("\\s+", "");
		
		String dataPattern = "ATOM(\\d{1,5})" // group 1 : atom number
				+ "([CNO]{1})" // group 2 : atom name
				+ "([CNO]{1}[A-Z]{0,2}|[CNO]{1}[A-Z]{1}[0-9]{0,3})" // group 3 : atom's alternate location
				+ "[.]([A-Z]{3})" // group 4 : residue of the atom
				+ "([A-Z]{1})" // group 5 : chain name identifier (single character)
				+ "([0-9]{1})" // group 6 : chain number identifier (single digit)
				+ "([0-9]{1,3})" // group 7 : number of the residue
				+ "([?]{1})" // group 8 : Code for insertion of residues
				+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 9 : X orthogonal coordinate of the atom
				+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 10 : Y orthogonal coordinate of the atom
				+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 11 : Z orthogonal coordinate of the atom

		Pattern atomEntry = Pattern.compile(dataPattern);
		Matcher m = atomEntry.matcher(atomLine);

		// create all variables to catch and assign default values
		int atomNumber = 0, chainNumberIdentifier = 0, residueNumber = 0;
		char atomName = '\u0000', chainNameIdentifier = '\u0000', codeInsertionResidue = '\u0000';
		String alternateLocationIndicator = null, residueName = null;
		float xOrthogonalCoordinate = 0.0f, yOrthogonalCoordinate = 0.0f, zOrthogonalCoordinate = 0.0f;

		if (m.find()) {

			atomNumber = Integer.parseInt(m.group(1));
			atomName = m.group(2).charAt(0);
			alternateLocationIndicator = m.group(3);
			// TODO : verify if it's a know residue of the file ??
			residueName = m.group(4);
			chainNameIdentifier = m.group(5).charAt(0);
			chainNumberIdentifier = Integer.parseInt(m.group(6));
			residueNumber = Integer.parseInt(m.group(7));
			codeInsertionResidue = m.group(8).charAt(0);
			xOrthogonalCoordinate = Float.parseFloat(m.group(9));
			yOrthogonalCoordinate = Float.parseFloat(m.group(10));
			zOrthogonalCoordinate = Float.parseFloat(m.group(11));

		} else {
			//TODO: don't use if/else instead use try/catch and catch below custom exception: 
			throw new AtomNotFoundException("No atoms were found while parsing the file");
		}

		Atom atom = new Atom(atomNumber, atomName, alternateLocationIndicator, residueName, chainNameIdentifier,
				chainNumberIdentifier, residueNumber, xOrthogonalCoordinate, yOrthogonalCoordinate,
				zOrthogonalCoordinate);

		return atom;

	}


	public Set<String> parseAtomLines() {

		List<String> atomLines = new ArrayList<String>();
		String[] lines = getContentOfFile().toString().split("\\n");
		for (String s : lines) {
			if (s.startsWith("ATOM")) {
				atomLines.add(s);
			}
		}
		//TODO: compare execution of List<String> VS Set<String>
		Set<String> set = new HashSet<>(Arrays.asList(lines));

		return set;

	}

}
