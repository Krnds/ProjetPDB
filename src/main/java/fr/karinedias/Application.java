package fr.karinedias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import fr.karinedias.math.NeighborSearch;
import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.MoleculeParser;
import fr.karinedias.utils.ResidueParser;

public class Application {

	/**
	 * Test all classes in main
	 * 
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {

		// Read a .pdb file :
		long startTime = System.currentTimeMillis();
		String filename = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/3tnw.cif";
		String filename2 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/2b5i.cif";
		String filename3 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/3l3t.cif";
		//cytokines
		String cyto1 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/cytokins/3og6.cif";
		FileReader pdb = new FileReader(cyto1);
		StringBuilder sb = pdb.reader();
		ResidueParser rwcp = new ResidueParser(sb);
		List<Residue> listOfResidues = rwcp.getResidues();

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));
		System.out.println("Found " + listOfMolecules.size() + " molecule(s)");

		// Remove all null residues (residues not found from cif file)
		List<Residue> listOfResiduesWithoutNulls = listOfResidues.stream()
				.filter(residue -> residue.getResidueName() != null).collect(Collectors.toList());

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {
			List<Residue> listOfResidueOfCurrentMolecule = listOfResiduesWithoutNulls.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());
			// Set all residues to the correct molecule
			currentMolecule.setResidues(listOfResidueOfCurrentMolecule);
		}
		// print all molecules
		for (Molecule molecule : listOfMolecules) {
			System.out.println(molecule.toString());
		}

		List<Residue> listOfNullResidues = listOfResidues.stream().filter(r -> r.getResidueNumber() == -1)
				.collect(Collectors.toList());

		System.out.println("Total number of residues found = " + listOfResidues.size());
		System.out.println("Non-null number of residues found = " + listOfResiduesWithoutNulls.size());
		System.out.println("There's " + listOfNullResidues.size() + " residues that has not been found.");

		// Get random residues from all molecules and compute distance between them
		Random rand = new Random();
		Residue randomResidue1 = listOfResiduesWithoutNulls.get(rand.nextInt(listOfResiduesWithoutNulls.size()));
		System.out.println("Random residue 1 = " + randomResidue1);
		Residue randomResidue2 = listOfResiduesWithoutNulls.get(rand.nextInt(listOfResiduesWithoutNulls.size()));
		System.out.println("Random residue 2 = " + randomResidue2);

		double distance = ResidueDistanceCalculation.distanceBetweenResidues(randomResidue1, randomResidue2);
		System.out.println("The distance between the two residues is " + Math.round(distance) + " Angström\n");

		// Get all neighbor residues
		List<Residue> interactions = new ArrayList<>();
		int cutoff = 10;
		interactions = NeighborSearch.getNeighborsFromMolecule(listOfMolecules.get(0), listOfMolecules.get(1), cutoff, true);

		System.out
				.println("Found " + interactions.size() + " residues within a distance of " + cutoff + " Angströms :");
		for (Residue residue2 : interactions) {
			System.out.println(
					"[Name = " + residue2.getResidueName() + "] [Number = " + residue2.getResidueNumber() + "]");
		}
		
		// Tests on 10 cytokines
		

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("\n\nElapsed time : " + elapsedTime + " seconds");
	}
}