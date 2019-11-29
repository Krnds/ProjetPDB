package main.fr.karinedias.manager;

import java.awt.geom.Point2D;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;

import main.fr.karinedias.utils.AtomToken;
import main.fr.karinedias.utils.FileReader;
import main.fr.karinedias.utils.PDBAtomRetriever;

public class Application {

	public static void main(String[] args) {
//TODO: test all classes from here :
		// Read a .pdb file :
		FileReader pdb = new FileReader();
		String file = pdb.getFileName();
		StringBuilder pdbSB = pdb.reader(file);
		
		// Test retrieving all ATOMS in form of a String :
		
		/*
		 * Beware, Eclipse limits the output by length, change the setting to see all
		 * the output !
		 */
		//System.out.println(pdbSB.toString());
		PDBAtomRetriever pdbStringAtoms = new PDBAtomRetriever(pdbSB.toString());
		
		try {
			System.out.println("getAtoms method test :");
			System.out.println(pdbStringAtoms.getAtoms());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Another test with the getAtoms2 method :
		try {
			System.out.println("getAtoms2 method test :");
			System.out.println(pdbStringAtoms.getAtoms2());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/// AtomToken atomsPdb = new AtomToken(file) //changer
	}
	
}
