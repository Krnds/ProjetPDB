package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

import src.main.fr.karinedias.model.AminoAcidCode;

public class AtomToken {

	/*
	 * Class for splitting tokens from the atoms entries of a pdb file
	 */
	/*
	 * 1-4 â€œATOMâ€� character 7-11# Atom serial number right integer 13-16 Atom
	 * name left* character 17 Alternate location indicator character 18-20Â§
	 * Residue name right character 22 Chain identifier character 23-26 Residue
	 * sequence number right integer 27 Code for insertions of residues character
	 * 31-38 X orthogonal Ã… coordinate right real (8.3) 39-46 Y orthogonal Ã…
	 * coordinate right real (8.3) 47-54 Z orthogonal Ã… coordinate right real (8.3)
	 * 55-60 Occupancy right real (6.2) 61-66 Temperature factor right real (6.2)
	 * 73-76 Segment identifierÂ¶ left character 77-78 Element symbol right
	 * character
	 */
	private FileReader file = null;

	/**
	 * Constructor :
	 */

	public AtomToken(FileReader file) {
		this.file = file;
	}

	private int atomSerialNumber;
	private String atomName; // ex : C
	private String alternateLocIndicator; // ex: CA or "."
	private AminoAcidCode residueName;
	private String chainIndentifier; // ex:
	private int residueSequenceNumber; // ex: 71
	private double atomXcoord;
	private double atomYcoord;
	private double atomZcoord;

	// helper function
	public static int countLines(String input) {

		String[] lines = input.split("\n");
		return lines.length;
	}

	public void atomDataParser(String rawStringOfAtoms) { // use PDBAtomRetrierver class
		// first delimit each atom entry with the newline character :
		String atomEntryDelimiter = "\n";
		StringTokenizer allAtomEntries = new StringTokenizer(rawStringOfAtoms, atomEntryDelimiter);

		// loop all atom entries and store each atom into an ArrayList called
		// atomEntries
		while (allAtomEntries.hasMoreElements()) {
			int n = allAtomEntries.countTokens();
			System.out.println(n);
			System.out.println(countLines(rawStringOfAtoms));
			ArrayList<String> atomEntries = new ArrayList<String>(n);

			for (int i = 0; i < n; i++) {
				atomEntries.add(i, allAtomEntries.nextToken());
			}

			// extract all info

			for (int j = 0; j < 11; j++) {
				String[] atomData = new String[11];
				String delimiterAtom = " ";
				StringTokenizer oneEntry = new StringTokenizer(atomEntries.get(j), delimiterAtom);
				for (int k = 0; k < 11; k++) {
					atomData[k] = oneEntry.nextToken().trim();
					// System.out.println("entrÃ©e nÂ° " + j + " " + infosAtomes[k].toString());
				}
				System.out.println("CoordonnÃ©es x de l'atome " + j + " = " + atomData[6].toString());
			}

		}
	}

}
