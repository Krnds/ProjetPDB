package main.fr.karinedias.manager;

import java.io.IOException;
import java.util.List;

import main.fr.karinedias.exceptions.AtomNotFoundException;
import main.fr.karinedias.exceptions.ResidueNotFoundException;
import main.fr.karinedias.utils.FileReader;
import main.fr.karinedias.utils.TinyParser;

public class Application {

	/**
	 * Test all classes in main
	 */
	public static void main(String[] args) throws IOException {

		// Read a .pdb file :

		String filename = "/home/karine/src/java/ProjetPDB/doc/2b5i.cif";
		FileReader pdb = new FileReader(filename);
		StringBuilder sb = pdb.reader();
		TinyParser tinyparser = new TinyParser(sb);

		TinyParser.AtomParser ap = tinyparser.new AtomParser(sb);
		TinyParser.ResidueParser rp = tinyparser.new ResidueParser(sb);

		List<String> atomlines = ap.parseAtomLines();
		try {
			ap.parseAlphaCarbons(atomlines);
		} catch (AtomNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		List<String> residuelines = rp.parseResidueLines();

		for (String line : residuelines) {
			try {
				rp.parseResidues(line);
			} catch (ResidueNotFoundException | AtomNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
