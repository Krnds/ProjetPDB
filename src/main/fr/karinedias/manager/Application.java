package main.fr.karinedias.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import main.fr.karinedias.exceptions.AtomNotFoundException;
import main.fr.karinedias.exceptions.ResidueNotFoundException;
import main.fr.karinedias.model.Residue;
import main.fr.karinedias.utils.FileReader;
import main.fr.karinedias.utils.TinyParser;

public class Application {

	/**
	 * Test all classes in main
	 */
	public static void main(String[] args) throws IOException {

		// Read a .pdb file :

		String filename = "/home/karine/src/java/ProjetPDB/doc/test.cif";
		FileReader pdb = new FileReader(filename);
		StringBuilder sb = pdb.reader();
		TinyParser tinyparser = new TinyParser(sb);

		TinyParser.AtomParser ap = tinyparser.new AtomParser(sb);
		TinyParser.ResidueParser rp = tinyparser.new ResidueParser(sb);

		List<String> atomlines = ap.parseAtomLines();
		List<String> residuelines = rp.parseResidueLines();

		// Lists of objects
		List<Residue> listOfResidues = new ArrayList<Residue>();
//		List<Atom> listOfAlphaCarbons = new ArrayList<Atom>();
//
//		try {
//			listOfAlphaCarbons.addAll(ap.parseAlphaCarbons(atomlines));
//		} catch (AtomNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println("There were " + listOfAlphaCarbons.size() + " alpha carbons found !");

//		for (String line : residuelines) {
//			try {
//				listOfResidues.add(rp.parseResidues(line));
//			} catch (ResidueNotFoundException | AtomNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
		//Check all residue lines, find residues with regex and their alpha carbon
		try {
			listOfResidues.add(rp.parseResidues(residuelines));
		} catch (ResidueNotFoundException | AtomNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		System.out.println("There were " + listOfResidues.size() + " residues found !");
		System.out.println(listOfResidues.get(11).toString());
	}
}
