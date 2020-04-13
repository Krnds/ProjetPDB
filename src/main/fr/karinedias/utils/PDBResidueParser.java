package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import src.main.fr.karinedias.model.Atom;
import src.main.fr.karinedias.model.Residue;

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

		// looking for residues entries in pdb file in the form of : 1 1   VAL n 
		Pattern residuesEntries = Pattern.compile("(\\d{1})\\s(\\d{1,3})\\s{1,3}([A-Z]{3})\\sn");
		Matcher residuesLineMatcher = residuesEntries.matcher(pdbFile.toString());

		// Format residues entries to store them into HashMap data set :
		Map<Integer, String> listOfResidues = new HashMap<Integer, String>();

		while (residuesLineMatcher.find()) {
			listOfResidues.put(Integer.parseInt(residuesLineMatcher.group(2)), residuesLineMatcher.group(3));
		}

		return listOfResidues;

	}
	
	//method for isolate from a residue, its list of atoms. Example for residue n° 44 : 
//	ATOM   1536 N  N   . SER B 2 44  ? -11.077 -7.959  -17.622 1.00 25.51 ? 44  SER B N   1 
//	ATOM   1537 C  CA  . SER B 2 44  ? -12.181 -8.781  -18.189 1.00 26.24 ? 44  SER B CA  1 
//	ATOM   1538 C  C   . SER B 2 44  ? -11.823 -10.272 -18.230 1.00 24.54 ? 44  SER B C   1 
//	ATOM   1539 O  O   . SER B 2 44  ? -12.681 -11.060 -18.684 1.00 24.30 ? 44  SER B O   1 
//	ATOM   1540 C  CB  . SER B 2 44  ? -12.578 -8.313  -19.561 1.00 28.03 ? 44  SER B CB  1 
//	ATOM   1541 O  OG  . SER B 2 44  ? -11.528 -8.593  -20.464 1.00 29.63 ? 44  SER B OG  1 

	public static List<Atom> getListOfAtoms(Residue res, StringBuilder pdbfile) {

		List<Atom> atomsOfResidue = new ArrayList<Atom>();
		int nres = res.getResidueNumber(); // number of residue
		
		
		return atomsOfResidue;

	}
	
	private Set<Map<Integer, String>> setFromMap(Map<Integer, String> map) {
		
		//Convert Map to HashMap ?

        Set<Integer> keySet=map.keySet().stream().collect(Collectors.toSet());
        keySet.forEach(key-> System.out.println(key));

        Set<String> valueSet=map.values().stream().collect(Collectors.toSet());
        valueSet.forEach(value-> System.out.println(value));
		
		return Set<Map<Integer,String>> maps;
		
	}
	
	public Residue getResidueObject(Map<Integer, String> residue) {
		
//		Set<Integer, String> setFromMap = set.stream()
//			    .collect(Collectors.toMap(Entry::getKey, Entry::getValue));
//		Residue test = new Residue(residue.get(0), residue., atomsOfResidue)
		
		HashMap<Integer, String> residueHashMap = new HashMap<Integer, String>();
		residue.forEach((k, v) -> residueHashMap.putAll(residueHashMap));
		
		Residue res = new Residue(residueName, residueSequenceNumber,)
		residueHashMap.forEach((k, v) -> r;
		
	}
	
	//TODO: what is this method ??
	public static boolean residueExists(Residue residue) {
		
		return false;
	}

	// for testing purposes
	public static void main(String[] args) {

		FileReader myfile = new FileReader();
		// On Windows OS
//		StringBuilder content = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\3c0p.cif");
//		StringBuilder content2 = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\6hk2.cif");
		StringBuilder debianExample = myfile.reader("/home/karine/src/java/ProjetPDB/doc/6rj4.cif");

		Map<Integer, String> residues = new HashMap<Integer, String>();
		residues = getListOfResidue(debianExample);

		for (int i = 0; i < residues.size(); i++) {
			//Atom atoms = residues.get(i);
		}
		
		Collection<String> s = residues.values();
		System.out.println("--------CLASS RESIDUE PARSER---------\n\nList of residues :\n");
		System.out.println(residues.values().toString());
		System.out.println(residues.keySet().toString());
		System.out.println("\n--------CLASS RESIDUE PARSER---------\n\nList of atoms :\n");
		
		//atoms = getListOfAtoms(s.addAll(c), pdbfile)

	}

}
