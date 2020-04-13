package src.main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

import src.main.fr.karinedias.model.Atom;
import src.main.fr.karinedias.model.Residue;

public class PDBResidueParser2 {
	
	/**
	 * Parsing residues from molecular structure files (.cif) and returning them in a form of Residue objects
	 * @param args
	 */
	
	/**
	 * Lists of Residues & Atoms of the file
	 */

	List<Residue> listOfesidues = new ArrayList<Residue>();
	List<Atom> listOfAtoms = new ArrayList<Atom>();
	String file;
	
	public PDBResidueParser2(StringBuilder file) {
		//TODO ??? how to ? constructor necessary ??
	}
	
	//method taken from PDBAtomRetriver for finding all ATOM entries :
	/**TODO: 02/02/20 : 
	 * Change the argument by none or StringBuilder ?? bc FileReader takes the path of the cif file and
	 * return a StringBuilder object.
	 * 
	 */
	public void getAtoms() throws IOException {

		Reader reader = new FileReader(file);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		StringBuffer startWithATOM = new StringBuffer();
		//use another object for storing ATOM enties :
		int nEntries = startWithATOM.length();
		String[] atomsUnformatted = new String[nEntries];

		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("ATOM")) {
				startWithATOM.append(line).append("\n");
			}
		}
		
		atomsUnformatted = startWithATOM.toString().split("\\r?\\n", -1);
		System.out.println(atomsUnformatted.toString());
		bufferedReader.close();
		
//		for (int i = 0; i < nEntries; i++) {
//			
//			
//			//atomsUnformatted.addAll(startWithATOM.toString().split("\\r?\\n", -1)); //regex for matching newlines character on UNIX and Windows systems and removing empty values with the -1
//		}
		

	}
	
//	public List<Residue> getListOfResidues(StringBuilder contentOfFile) {
//		
//		// looking for residues entries in pdb file in the form of : 1 1   VAL n 
//		Pattern residuesEntries = Pattern.compile("(\\d{1})\\s(\\d{1,3})\\s{1,3}([A-Z]{3})\\sn");
//		Matcher residuesLineMatcher = residuesEntries.matcher(contentOfFile.toString());
//
//		//TODO: looking for atoms related to each residue :
//		//search for all atoms with the entity_id equals to the residue number :
//		Pattern atomsWithCorrectEntityID =  Pa
//		
//		// Format residues entries to store them into HashMap data set :
//		
//		Map<Integer, String> residuesFound = new HashMap<Integer, String>();
//		listOfesidues.addAll((Collection<? extends Residue>) residuesFound);
//
//		while (residuesLineMatcher.find()) {
//			residuesFound.put(Integer.parseInt(residuesLineMatcher.group(2)), residuesLineMatcher.group(3));
//		}
//		
//		listOfesidues.add(e)
//	}
	
	
	//FOR TESTING PURPORSES :
	public static void main(String[] args) {
		
		src.main.fr.karinedias.utils.FileReader testfile = new src.main.fr.karinedias.utils.FileReader();
				
		PDBResidueParser2 test = new PDBResidueParser2(testfile.reader("/home/karine/src/java/ProjetPDB/doc/3bw7.cif"));
		String s = test.toString();
		
		try {
			test.getAtoms();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
