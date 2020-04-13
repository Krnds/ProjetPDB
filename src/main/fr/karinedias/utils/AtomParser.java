package src.main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.fr.karinedias.model.Atom;

public class AtomParser {

	private StringBuilder contentOfFile;

	public AtomParser(StringBuilder content) {

		this.contentOfFile = content;

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		long startTime = System.nanoTime();

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

		Atom atom1 = parseAtomData(test1);
		System.out.println(atom1.toString());

		Atom atom2 = parseAtomData(test2);
		System.out.println(atom2.toString());

		Atom atom3 = parseAtomData(test3);
		System.out.println(atom3.toString());

		Atom atom4 = parseAtomData(test4);
		System.out.println(atom4.toString());

		try {
			parseAtomData(test9);
		} catch (NullPointerException exc) {
			System.out.println("The String was empty");
		}

		/////// TEST FOR PARSING ALL LINES SEARCHING FOR ATOM VALUES

		
		BufferedReader br;
		String line;
		StringBuilder sb = new StringBuilder();
		
		try {
			java.io.FileReader testFile = new java.io.FileReader("/home/karine/src/java/ProjetPDB/doc/4URT.cif");
			br = new BufferedReader(testFile);
			line = br.readLine();
			
			while (line != null) {
				sb.append(line);
			}
			
			parseAtomData(sb.toString());
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);

		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

	// getter
	public StringBuilder getContentOfFile() {
		return contentOfFile;
	}

	public static Atom parseAtomData(String atomLine) {

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

		// create all variables to catch
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
			// else create a null object optionnal
			System.out.println("NO MATCH");
		}

		Atom atom = new Atom(atomNumber, atomName, alternateLocationIndicator, residueName, chainNameIdentifier,
				chainNumberIdentifier, residueNumber, xOrthogonalCoordinate, yOrthogonalCoordinate,
				zOrthogonalCoordinate);

		return atom;

	}
	
	//TODO: arg : String or StringBuffer ?
	public List<String> parseAtomLines (String fileContent) {
		
		Scanner sc = new Scanner(fileContent);
		String lineBeforeAtoms = "_atom_site.pdbx_PDB_model_num";
		
		
		while (sc.hasNextLine()) {
			if (sc.hasNext(lineBeforeAtoms)) {
				
			}
		}

		
		
		return null;
		
	}
	
	


}
