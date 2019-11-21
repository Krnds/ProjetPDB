package main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

import org.junit.platform.commons.util.ToStringBuilder;

import main.fr.karinedias.model.AminoAcidCode;
import main.fr.karinedias.utils.*;

public class AtomToken {

	/*
	 * Class for splitting tokens from the atoms entries of a pdb file
	 */
	/*
	 * 1-4 “ATOM” character 7-11# Atom serial number right integer 13-16 Atom name
	 * left* character 17 Alternate location indicator character 18-20§ Residue name
	 * right character 22 Chain identifier character 23-26 Residue sequence number
	 * right integer 27 Code for insertions of residues character 31-38 X orthogonal
	 * Å coordinate right real (8.3) 39-46 Y orthogonal Å coordinate right real
	 * (8.3) 47-54 Z orthogonal Å coordinate right real (8.3) 55-60 Occupancy right
	 * real (6.2) 61-66 Temperature factor right real (6.2) 73-76 Segment
	 * identifier¶ left character 77-78 Element symbol right character
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

	public void atomDataParser(String rawStringOfAtoms) { //use PDBAtomRetrierver class
		// first delimit each atom entry with the newline character :
		String atomEntryDelimiter = "\n";
		StringTokenizer allAtomEntries = new StringTokenizer(rawStringOfAtoms, atomEntryDelimiter);

		// loop all atom entries and store each atom into an ArrayList called atomEntries
		while (allAtomEntries.hasMoreElements()) {
			int n = allAtomEntries.countTokens();
			System.out.println(n);
			System.out.println(countLines(rawStringOfAtoms));
			ArrayList<String> atomEntries = new ArrayList<String>(n);

			for (int i = 0; i < n; i++) {
				atomEntries.add(i, allAtomEntries.nextToken());
			}

			//extract all info 

			for (int j = 0; j < 11; j++) {
				String[] atomData = new String[11];
				String delimiterAtom = " ";
				StringTokenizer oneEntry = new StringTokenizer(atomEntries.get(j), delimiterAtom);
				for (int k = 0; k < 11; k++) {
					atomData[k] = oneEntry.nextToken().trim();
					// System.out.println("entrée n° " + j + " " + infosAtomes[k].toString());
				}
				System.out.println("Coordonnées x de l'atome " + j + " = " + atomData[6].toString());
			}

		}
	}

//	public static void main(String[] args) {
//
//		String atomsToParse = "ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  \n"
//				+ "ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  \n"
//				+ "ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  \n"
//				+ "ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  \n"
//				+ "ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  \n"
//				+ "ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  \n"
//				+ "ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  \n"
//				+ "ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  \n"
//				+ "ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  \n"
//				+ "ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  \n"
//				+ "ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C  \n";
//		
//		String test = FileReader.reader("").toString();
//
//		atomDataParser(atomsToParse);
//	}
}
