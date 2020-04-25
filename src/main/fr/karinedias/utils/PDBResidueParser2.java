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
	 * TODO: delete
	 */
	
	/**
	 * Lists of Residues & Atoms of the file
	 */

	List<Residue> listOfesidues = new ArrayList<Residue>();
	List<Atom> listOfAtoms = new ArrayList<Atom>();
	String file;
	
	public PDBResidueParser2(StringBuilder file) {
		//TODO ??? how to ? constructor necessary ?? ?
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
		
	}
	
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
