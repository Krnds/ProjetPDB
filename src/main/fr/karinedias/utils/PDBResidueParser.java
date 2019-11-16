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

	private final StringBuilder pdbFile = null; // TODO: create a link with the pdb file from FileReader class ?

	

	public static ArrayList<String> getListOfResidue(StringBuilder pdbFile) {
		//search for line "_entity_poly_seq.hetero"
		
		Map<Integer, AminoAcidCode> residues = new HashMap<Integer, AminoAcidCode>();
		
		//regex for finding residues ex : 1 1   VAL n 
		Pattern p = Pattern.compile("\\d(\\s)+(\\d)+(\\s)+[A-Z]{3}");
	    Matcher m = p.matcher(pdbFile.toString());
	    List<String> allMatches = new ArrayList<String>();
	    while (m.find()) {
	    	allMatches.add(m.group().trim());
	    }
	    
	    System.out.println(allMatches.get(6).trim());
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
		StringBuilder test = new StringBuilder("loop_\n" + 
				"_entity_poly_seq.entity_id \n" + 
				"_entity_poly_seq.num \n" + 
				"_entity_poly_seq.mon_id \n" + 
				"_entity_poly_seq.hetero \n" + 
				"1 1   VAL n \n" + 
				"1 2   LEU n \n" + 
				"1 3   SER n \n" + 
				"1 4   PRO n \n" + 
				"1 5   ALA n \n" + 
				"1 6   ASP n \n" + 
				"1 7   LYS n \n" + 
				"1 8   THR n \n" + 
				"1 9   ASN n \n" + 
				"1 10  VAL n \n" + 
				"1 11  LYS n \n" + 
				"1 12  ALA n \n" + 
				"1 13  ALA n \n" + 
				"1 14  TRP n \n" + 
				"1 15  GLY n \n" + 
				"1 16  LYS n \n" + 
				"1 17  VAL n \n" + 
				"1 18  GLY n \n" + 
				"1 19  ALA n \n" + 
				"1 20  HIS n \n" + 
				"1 21  ALA n \n" + 
				"1 22  GLY n \n" + 
				"1 23  GLU n \n" + 
				"1 24  TYR n \n" + 
				"1 25  GLY n \n" + 
				"1 26  ALA n \n" + 
				"1 27  GLU n \n" + 
				"1 28  ALA n \n" + 
				"1 29  LEU n \n" + 
				"1 30  GLU n \n" + 
				"1 31  ARG n \n" + 
				"1 32  MET n \n" + 
				"1 33  PHE n \n" + 
				"1 34  LEU n \n" + 
				"1 35  SER n \n" + 
				"1 36  PHE n \n" + 
				"1 37  PRO n \n" + 
				"1 38  THR n \n" + 
				"1 39  THR n \n" + 
				"1 40  LYS n \n" + 
				"1 41  THR n \n" + 
				"1 42  TYR n \n" + 
				"1 43  PHE n \n" + 
				"1 44  PRO n \n" + 
				"1 45  HIS n \n" + 
				"1 46  PHE n \n" + 
				"1 47  ASP n \n" + 
				"1 48  LEU n \n" + 
				"1 49  SER n \n" + 
				"1 50  HIS n \n" + 
				"1 51  GLY n \n" + 
				"1 52  SER n \n" + 
				"1 53  ALA n \n" + 
				"1 54  GLN n \n" + 
				"1 55  VAL n \n" + 
				"1 56  LYS n \n" + 
				"1 57  GLY n \n" + 
				"1 58  HIS n \n" + 
				"1 59  GLY n \n" + 
				"1 60  LYS n \n" + 
				"1 61  LYS n \n" + 
				"1 62  VAL n \n" + 
				"1 63  ALA n \n" + 
				"1 64  ASP n \n" + 
				"1 65  ALA n \n" + 
				"1 66  LEU n \n" + 
				"1 67  THR n \n" + 
				"1 68  ASN n \n" + 
				"1 69  ALA n \n" + 
				"1 70  VAL n \n" + 
				"1 71  ALA n \n" + 
				"1 72  HIS n \n" + 
				"1 73  VAL n \n" + 
				"1 74  ASP n \n" + 
				"1 75  ASP n \n" + 
				"1 76  MET n \n" + 
				"1 77  PRO n \n" + 
				"1 78  ASN n \n" + 
				"1 79  ALA n \n" + 
				"1 80  LEU n \n" + 
				"1 81  SER n \n" + 
				"1 82  ALA n \n" + 
				"1 83  LEU n \n" + 
				"1 84  SER n \n" + 
				"1 85  ASP n \n" + 
				"1 86  LEU n \n" + 
				"1 87  HIS n \n" + 
				"1 88  ALA n \n" + 
				"1 89  HIS n \n" + 
				"1 90  LYS n \n" + 
				"1 91  LEU n \n" + 
				"1 92  ARG n \n" + 
				"1 93  VAL n \n" + 
				"1 94  ASP n \n" + 
				"1 95  PRO n \n" + 
				"1 96  VAL n \n" + 
				"1 97  ASN n \n" + 
				"1 98  PHE n \n" + 
				"1 99  LYS n \n" + 
				"1 100 LEU n ");
	
		 System.out.println(getListOfResidue(test));
	}
	
	
}
