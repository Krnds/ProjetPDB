package main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import main.fr.karinedias.model.AminoAcidCode;

public class PDBResidueParser {

	/**
	 * Class for parsing the PDB file and returning all the residues and their atom
	 * entries
	 * 
	 * @author Karine Dias 
	 */

	//TODO: Ne fonctionne pas, écrire une classe robuste...
	
	/**
	 * Helper method
	 */
	
	public static void findResidues(StringBuilder pdbFile) {
		Pattern lineBeforeResiduesEntries = Pattern.compile("_entity_poly_seq.hetero (.*)"); //TODO: the same line 4 all pdb files ?
		Matcher specificLineMatcher = lineBeforeResiduesEntries.matcher(pdbFile.toString());
		if (specificLineMatcher.find()) {
			System.out.println(specificLineMatcher.group(0));
			System.out.println(specificLineMatcher.group(1));
		}
		
	}

	/**
	 * Parses the file looking for resisdues entries
	 * @param pdbFile
	 * @return
	 */

	public static ArrayList<String> getListOfResidue(StringBuilder pdbFile) {
		//search for line "_entity_poly_seq.hetero"
		
		Map<Integer, AminoAcidCode> residues = new HashMap<Integer, AminoAcidCode>();
		
		//regex for finding residues ex : 1 1   VAL n 
		Pattern p = Pattern.compile("\\d(\\s)+(\\d)+(\\s)+[A-Z]{3}");
	    Matcher m = p.matcher(pdbFile.toString());
	    List<String> allMatches = new ArrayList<String>();
	    while (m.find()) {
	    	allMatches.add(m.group());
	    	
	    }
	    
	    for (int i = 0; i < allMatches.size(); i++) {
			//System.out.println("ENtrée n° " + i + ": " + allMatches.get(i));
	    	allMatches.get(i).trim();
		}
	    
	    //TODO: trim all whitespaces when searching for regex
		
		if (pdbFile.toString().contains("_entity_poly_seq.hetero")) {
			pdbFile.toString().split("\n");
			if (pdbFile.toString().startsWith("\\d")) {
				
			}
			//use Hashmap structure ? conserve l'odre ? méthodes contenues dans l'interface ? load capacity etc. ??
			//regarder ici : https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html
		}
		
		return (ArrayList<String>) allMatches;
		
	}
	
	
	public static void main(String[] args) {
	
		FileReader myfile = new FileReader();
		StringBuilder content = myfile.reader("/home/karine/src/java/ProjetPDB/doc/2b5i.cif");
		 System.out.println(getListOfResidue(content));
		// findResidues(content);
	}
	
	
}
