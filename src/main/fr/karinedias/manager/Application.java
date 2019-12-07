package main.fr.karinedias.manager;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import main.fr.karinedias.utils.FileReader;
import main.fr.karinedias.utils.PDBAtomRetriever;
import main.fr.karinedias.utils.PDBResidueParser;

public class Application {

	/**
	 * Test all classes in main
	 */
	public static void main(String[] args) throws IOException {

		// Read a .pdb file :
		FileReader pdb = new FileReader();
		String filename = pdb.getFileName();

		/*
		 * Beware, Eclipse limits the output by length, change the setting to see all
		 * the output !
		 */
		PDBAtomRetriever pdbStringAtoms = new PDBAtomRetriever(filename); // useful ?
		StringBuffer s = pdbStringAtoms.getAtoms();
		String s2 = pdbStringAtoms.getAtoms2();

		System.out.println(s);
		System.out.println("\n\n");
		System.out.println(s2);

		FileReader myfile = new FileReader();
		// On Windows OS
//		StringBuilder content = myfile.reader("C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\3c0p.cif");
		// On Debian (Linux)
		StringBuilder content2 = myfile.reader("/home/karine/src/java/ProjetPDB/doc/6hk2.cif");

		Map<Integer, String> residues, residues2;

		residues = new HashMap<Integer, String>();
		residues2 = new HashMap<Integer, String>();

//		residues = PDBResidueParser.getListOfResidue(content);
		residues2 = PDBResidueParser.getListOfResidue(content2);
		System.out.println(residues2.values().toString());
		System.out.println(residues2.keySet().toString());
		System.out.println("35th residue : " + residues2.get(35));
	}

}
