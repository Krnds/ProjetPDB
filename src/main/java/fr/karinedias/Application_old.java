package fr.karinedias;

import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import fr.karinedias.math.NeighborSearch;
import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Chain;
import fr.karinedias.model.Complex;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.query.CytokineQuery;
import fr.karinedias.query.Query;
import fr.karinedias.utils.ChainParser;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.MoleculeParser;
import fr.karinedias.utils.ResidueParser;
import fr.karinedias.visualisation.JmolIntegration;
import fr.karinedias.visualisation.JmolIntegration;

public class Application_old {

	private static Scanner SC;

	/**
	 * Test all classes in main
	 * 
	 * @throws URISyntaxException
	 */
	public static void main(String[] args) throws IOException, URISyntaxException {

		long startTime = System.currentTimeMillis();

		// cytokines
		// 3alq, 3dlq, 3g9v, 3og4, 3og6, 3qb7, 3va2, 4nkq, 4nn6, 4wrl
		String structure = "3dcu";
		String structureFile = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/"
				+ structure.toLowerCase() + ".cif";
		int cutoff = 8;

		String testAppli = "/home/karine/src/java/ProjetPDB_old/src/test/resources/test-data/test.cif";

		FileReader pdb = new FileReader(structureFile);
		StringBuilder sb = pdb.reader();
		ResidueParser rwcp = new ResidueParser(sb);

		List<Residue> listOfResidues = rwcp.getAllResidues();

		// parsing molecules
		MoleculeParser mp = new MoleculeParser(sb);
		List<Molecule> listOfMolecules = new ArrayList<>();
		listOfMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));
		System.out.println("Found " + listOfMolecules.size() + " molecule(s) in structure " + structure.toUpperCase());

		// get complex info
		Complex complex = new Complex(structure, listOfMolecules);

		// parsing chains
		ChainParser cp = new ChainParser(sb);
		int numberOfChains = 0;
		for (Molecule mol : listOfMolecules) {
			System.out.println(mol.getDescription());
			mol.setChain(cp.getChains(mol));
			numberOfChains += mol.getChain().size();
		}

		System.out.println("Found " + numberOfChains + " chain(s) :");
		for (Molecule mol : listOfMolecules) {
			System.out.println(mol.getChain().toString());
		}

		// TODO: if > 2 chains, create custom treatment to regroup chains to
		// cytokine/receptor
		if (listOfMolecules.size() > 2)
			;// TODO parse in description 'receptor' and put to mol2
		// TODO: parse 'cytokine' and put to mol1 ?

		// JMOL CHAINS
		// Create structure to fit each molecule number with the list of chains
		HashMap<Integer, List<Character>> jmolInfo = new HashMap<Integer, List<Character>>();

		// For each molecule found, append the list of residues
		for (Molecule currentMolecule : listOfMolecules) {

			List<Residue> residuesOfCurrentMolecule = listOfResidues.stream()
					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
					.collect(Collectors.toList());

			// Set all residues to the correct molecule
			currentMolecule.setResidues(residuesOfCurrentMolecule);
			List<Chain> c = currentMolecule.getChain(); // c.size() = number of chains in each molecule

			List<Character> chainCharacters = c.stream().map(Chain::getChain).collect(Collectors.toList());
			System.out.println(chainCharacters.toString());

			jmolInfo.put(currentMolecule.getId(), chainCharacters);
			System.out.println(jmolInfo.toString());

			for (Character ch : chainCharacters) {
				List<Residue> resOfChain = new ArrayList<>();
				resOfChain.addAll(residuesOfCurrentMolecule.stream().filter(r -> r.getAltChain() == ch)
						.collect(Collectors.toList()));

			}
			// print residues or each molecule
			System.out.println(residuesOfCurrentMolecule.toString());

		}

		System.out.println(
				"Number of residues found in " + structure.toUpperCase() + " = " + listOfResidues.size() + "\n");

		// Get random residues from all molecules and compute distance between them
		Random rand = new Random();
		Residue randomResidue1 = listOfResidues.get(rand.nextInt(listOfResidues.size()));
		System.out.println("Random residue 1 = " + randomResidue1);
		Residue randomResidue2 = listOfResidues.get(rand.nextInt(listOfResidues.size()));
		System.out.println("Random residue 2 = " + randomResidue2);

		double distance = ResidueDistanceCalculation.distanceBetweenResidues(randomResidue1, randomResidue2);
		System.out.println("The distance between the two residues is " + Math.round(distance) + " Angström\n");

//		// Get the names of the alternate chains displayed in Jmol
		Set<Character> chains = new HashSet<Character>(listOfResidues.size());
		listOfResidues.stream().filter(r -> chains.add(r.getAltChain())).collect(Collectors.toList());
		for (Character character : chains) {
			System.out.println("Chains found : " + character);
		}

		// Test get neighbors from chain
		// int cutoff = 8;
		List<Residue> neighborsFromChain = NeighborSearch.getNeighborsFromChains(listOfMolecules.get(0),
				listOfMolecules.get(1), cutoff, true);

		// Get all neighbor residues
		List<Residue> interactions = NeighborSearch.getNeighborsResidues(complex, randomResidue1, cutoff);

		System.out.println("FOUND " + interactions.size() + " INTERACTIONS!");

		// Get all neighbors distances
		List<Double> interactionsDistance = NeighborSearch.getNeighborsDistance(complex, randomResidue1, cutoff);
		for (Double d : interactionsDistance) {
			System.out.println(d);
		}

// attention, il faut prendre en compte le cas où plus de 2 molécules... (or cytokine/récepteur)

		interactions = NeighborSearch.getNeighborsFromMolecule(listOfMolecules.get(0), listOfMolecules.get(1), cutoff,
				false);
		// System.out.println(listOfMolecules.get(0).toString());
		// System.out.println(listOfMolecules.get(1).toString());
		System.out.println(
				"Found " + neighborsFromChain.size() + " residues within a distance of " + cutoff + " Angströms :");

		for (Character character : chains) {
			for (Residue residue2 : neighborsFromChain) {
				if (residue2.getAltChain() == character) {
					// System.out.print(residue2.getResidueName().toLowerCase() +
					// residue2.getAltResidueNumber() + ",");
					System.out.print("atomno=" + residue2.getAtomNumber() + ",");
				}
			}
			System.out.println("\n");
		}

		// link to JmolIntegration
		List<Character> selectChains = new ArrayList<Character>();
		List<String> selectResiduesCommand = new ArrayList<String>();
		// TODO: delete ?
		String selectAtoms = "";
		System.out.println("Jmol info = " + jmolInfo.toString());
		for (Character character : chains) {
			selectChains.add(character);
			for (Residue residue2 : neighborsFromChain) {
				if (residue2.getAltChain() == character) {
					selectResiduesCommand
							.add(residue2.getResidueName().toLowerCase() + residue2.getAltResidueNumber() + ",");
					selectAtoms += "atomno=" + residue2.getAtomNumber() + ",";
				}
			}
		}

		System.out.println(selectChains.toString());
		System.out.println(selectAtoms.toString());
		// selectChains = A,B,C,D --> TODO: avoir la liste des chaines par molécule

//		System.out.println(jmolInfo.get(1));
//		System.out.println(jmolInfo.get(2));

		JmolIntegration testJmol = new JmolIntegration();
		testJmol.setStructure(structureFile, jmolInfo, selectAtoms);

		long endTime = System.currentTimeMillis();
		long elapsedTime = (endTime - startTime) / 1000;
		System.out.println("\n\nElapsed time : " + elapsedTime + " seconds");
	}


}