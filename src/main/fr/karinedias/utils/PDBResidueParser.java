package main.fr.karinedias.utils;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.fr.karinedias.model.Residue;

public class PDBResidueParser extends PDBAtomParser {

	/**
	 * Class for parsing the PDB file and returning all the residues and their atom
	 * entries
	 * 
	 * @author Karine Dias
	 */

	/**
	 * Parses the pdb file looking for residues in the form of "CHAIN NUMRESIDUE
	 * RESIDUE n"
	 * 
	 * @param pdbFile
	 * @return a Map with the number of the residue and the name TODO: change the
	 *         value of the map to the AminoAcidCode enum
	 */

	public static Map<Integer, String> getListOfResidue(StringBuilder pdbFile) {

		// looking for residues entries in pdb file
		Pattern residuesEntries = Pattern.compile("(\\d{1})\\s(\\d{1,3})\\s{1,3}([A-Z]{3})\\sn");
		Matcher residuesLineMatcher = residuesEntries.matcher(pdbFile.toString());

		// Format residues entries to store them into HashMap data set :
		Map<Integer, String> listOfResidues = new HashMap<Integer, String>();

		while (residuesLineMatcher.find()) {
			listOfResidues.put(Integer.parseInt(residuesLineMatcher.group(2)), residuesLineMatcher.group(3));
		}

		return listOfResidues;

	}

	public static void getListOfAtoms(Residue res) {

		int nres = res.getResidueNumber(); // number of residue

	}

	// for testing purposes
	public static void main(String[] args) {

		FileReader myfile = new FileReader();
		// On Windows OS
		StringBuilder content = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\3c0p.cif");
		StringBuilder content2 = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\6hk2.cif");
		StringBuilder content3 = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif");

		Map<Integer, String> residues = new HashMap<Integer, String>();
		residues = getListOfResidue(content3);
		System.out.println(residues.values().toString());
		System.out.println(residues.keySet().toString());

	}

}
