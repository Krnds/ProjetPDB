package fr.karinedias;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import fr.karinedias.math.NeighborSearch;
import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Chain;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.utils.ChainParser;
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
		// cytokines
		String structure = "3og4";
		String cyto1 = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/cytokins/" + structure.toLowerCase()
				+ ".cif";

		String testAppli = "/home/karine/src/java/ProjetPDB_old/src/test/resources/test-data/test.cif";

		FileReader pdb = new FileReader(cyto1);
		StringBuilder sb = pdb.reader();
		ResidueParser rwcp = new ResidueParser(sb);
		// List<Residue> listOfResidues = rwcp.getResidues();
		List<Residue> listOfResidues = rwcp.parseAllResidues();

		//System.out.println("RESIDUE TEST :" + listOfResidues.get(105).toString());

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));
		System.out.print("Found " + listOfMolecules.size() + " molecule(s)");
		System.out.println(listOfMolecules.toString());

		// parsing chains
		ChainParser cp = new ChainParser(sb);
		int numberOfChains = 0;
		for (Molecule mol : listOfMolecules) {
			mol.setChain(cp.getChains(mol));
			numberOfChains += mol.getChain().size();
		}

		System.out.println("Found " + numberOfChains + " chain(s)");
		for (Molecule mol : listOfMolecules) {
			System.out.println(mol.getChain().toString());
		}

		// TODO: DELETE ?
		// Remove all null residues (residues not found from cif file)
		List<Residue> listOfResiduesWithoutNulls = listOfResidues.stream()
				.filter(residue -> residue.getResidueName() != null).collect(Collectors.toList());

		// JMOL CHAINS

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {

			List<Residue> listOfResidueOfCurrentMolecule = listOfResiduesWithoutNulls.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());

			// Set all residues to the correct molecule
			currentMolecule.setResidues(listOfResidueOfCurrentMolecule);
			List<Chain> c = currentMolecule.getChain();
			List<Character> charac = c.stream().map(Chain::getChain).collect(Collectors.toList());
			System.out.println(charac.toString());
			for (Character ch : charac) {
				List<Residue> resOfChain = new ArrayList<>();
				resOfChain.addAll(listOfResidueOfCurrentMolecule.stream().filter(r -> r.getAltChain() == ch)
						.collect(Collectors.toList()));
				System.out.println(resOfChain.toString());
			}

//				System.out.println(c.toString());
//				// residues by chains for Jmol ???
//				listOfResidueOfCurrentChain = listOfResiduesWithoutNulls.stream()
//						.filter(residue -> residue.getAltChain() == c.getChain())
//						.collect(Collectors.toMap(residue, c.getChain()));

			// DECOMMENT
//			System.out.println("Number of res of mol " + currentMolecule.getDescription() + " is = "
//					+ currentMolecule.getResidues().size());

		}
		// print all molecules
//		for (Molecule molecule : listOfMolecules) {
//			System.out.println(molecule.getResidues().toString());
//		}

//		System.out.println("PRINT ALL RESIDUES BY JMOL CHAIN");
//		for (Residue residue2 : listOfResidueOfCurrentChain) {
//			System.out.println(residue2.toString());
//		}

		List<Residue> listOfNullResidues = listOfResidues.stream().filter(r -> r.getResidueNumber() == -1)
				.collect(Collectors.toList());

		System.out.println("Total number of residues found = " + listOfResidues.size());
		System.out.println("Number of residues with coordinates found = " + listOfResiduesWithoutNulls.size());
		System.out.println("There's " + listOfNullResidues.size() + " residues whitout coordinates.");

		// Get random residues from all molecules and compute distance between them
		Random rand = new Random();
		Residue randomResidue1 = listOfResiduesWithoutNulls.get(rand.nextInt(listOfResiduesWithoutNulls.size()));
		// System.out.println("Random residue 1 = " + randomResidue1);
		Residue randomResidue2 = listOfResiduesWithoutNulls.get(rand.nextInt(listOfResiduesWithoutNulls.size()));
		// System.out.println("Random residue 2 = " + randomResidue2);

		double distance = ResidueDistanceCalculation.distanceBetweenResidues(randomResidue1, randomResidue2);
		// System.out.println("The distance between the two residues is " +
		// Math.round(distance) + " Angström\n");

		// Get the names of the alternate chains displayed in Jmol
		Set<Character> jmolChains = new HashSet<Character>(listOfResiduesWithoutNulls.size());
		listOfResiduesWithoutNulls.stream().filter(r -> jmolChains.add(r.getAltChain())).collect(Collectors.toList());
		for (Character character : jmolChains) {
			System.out.println("Names of chains for JMOL representation : " + character);
		}

		// Test get neighbors from chain
		int cutoff = 10;
		List<Residue> test = NeighborSearch.getNeighborsFromChains(listOfMolecules.get(0), listOfMolecules.get(1), cutoff,
				true);

		// Get all neighbor residues
		List<Residue> interactions = new ArrayList<>();

		interactions = NeighborSearch.getNeighborsFromMolecule(listOfMolecules.get(0), listOfMolecules.get(1), cutoff,
				false);
		System.out.println(listOfMolecules.get(0).toString());
		System.out.println(listOfMolecules.get(1).toString());
		System.out.println("Found " + test.size() + " residues within a distance of " + cutoff + " Angströms :");

		for (Character character : jmolChains) {
			for (Residue residue2 : test) {
				if (residue2.getAltChain() == 'B') {
					System.out.print(residue2.getResidueName().toLowerCase() + residue2.getResidueNumber() + ",");
				}
			}
		}
//		}
//		for (Residue residue2 : interactions) {
//			if (residue2.getAltChain() == 'C') {
//				System.out.print(residue2.getResidueName().toLowerCase() + residue2.getResidueNumber() + ",");
//			}
//		}
//		for (Residue residue2 : interactions) {
//			if (residue2.getAltChain() == 'D') {
//				System.out.print(residue2.getResidueName().toLowerCase() + residue2.getResidueNumber() + ",");
//			}
//		}
//		for (Residue residue2 : test) {
//			if (residue2.getAltChain() == 'E') {
//				System.out.print(residue2.getResidueName().toLowerCase() + residue2.getResidueNumber() + ",");
//			}
//		}
//		for (Residue residue2 : interactions) {
//			if (residue2.getAltChain() == 'F') {
//				System.out.print(residue2.getResidueName().toLowerCase() + residue2.getResidueNumber() + ",");
//			}
//		}


		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("\n\nElapsed time : " + elapsedTime + " seconds");
	}
}