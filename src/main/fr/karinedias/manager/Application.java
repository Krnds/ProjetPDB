package main.fr.karinedias.manager;

import java.io.IOException;
import java.util.List;

import main.fr.karinedias.model.ResidueWithCoordinates;
import main.fr.karinedias.utils.FileReader;
import main.fr.karinedias.utils.ResidueWithCoordinatesParser;

public class Application {

	/**
	 * Test all classes in main
	 */
	public static void main(String[] args) throws IOException {

		// Read a .pdb file :
		long startTime = System.currentTimeMillis();
		String filename = "/home/karine/src/java/ProjetPDB/doc/test.cif";
		FileReader pdb = new FileReader(filename);
		StringBuilder sb = pdb.reader();
		ResidueWithCoordinatesParser rwcp = new ResidueWithCoordinatesParser(sb);
		List<ResidueWithCoordinates> listOfResidues = rwcp.getResidueLines();

		for (ResidueWithCoordinates res : listOfResidues) {
			System.out.println(res.toString());
		}
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime)/1000;
		System.out.println("Elapsed time : " + elapsedTime + " seconds");
	}
}
