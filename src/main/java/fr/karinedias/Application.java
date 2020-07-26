package fr.karinedias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import fr.karinedias.model.Residue;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.ResidueParser;

public class Application {

	/**
	 * Test all classes in main
	 * @throws URISyntaxException 
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {

		// Read a .pdb file :
		long startTime = System.currentTimeMillis();
		String filename = "data/test.cif";
		FileReader pdb = new FileReader(filename);
		StringBuilder sb = pdb.reader();
		ResidueParser rwcp = new ResidueParser(sb);
		List<Residue> listOfResidues = rwcp.getResidueLines();

		for (Residue res : listOfResidues) {
			System.out.println(res.toString());
		}
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime)/1000;
		System.out.println("Elapsed time : " + elapsedTime + " seconds");
	}
}
