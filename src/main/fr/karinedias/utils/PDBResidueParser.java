package main.fr.karinedias.utils;

public class PDBResidueParser {

	/**
	 * Class for parsing the PDB file and returning all the residues and their atom
	 * entries
	 * 
	 * @author Karine Dias @
	 */

	private final StringBuilder pdbFile = null; // TODO: create a link with the pdb file from FileReader class ?

	

	public void getListOfResidue(StringBuilder pdbFile) {
		//search for line "_entity_poly_seq.hetero"
		if (pdbFile.toString().contains("_entity_poly_seq.hetero")) {
			//use Hashmap structure ? conserve l'odre ? méthodes contenues dans l'interface ? load capacity etc. ??
			//regarder ici : https://docs.oracle.com/javase/7/docs/api/java/util/HashMap.html
		}
		
	}
	
	
	public static void main(String[] args) {
		
	}
}
