package fr.karinedias.math;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import fr.karinedias.model.Chain;
import fr.karinedias.model.Complex;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;

public class NeighborSearch {

	// get all residues within a distance D from a residue
	/**
	 * 
	 * @param complex : complex made of multiple molecules with multiples residues
	 * @param baseRes  : random residue
	 * @param distance  : cutoff distance in Angströms
	 * @return a list of nearest neighbors
	 */
	public static List<Residue> getNeighborsResidues(Complex complex, Residue baseRes, int distance) {

		List<Residue> residuesOfComplex = new ArrayList<>();

		for (Molecule mol : complex.getMolecules()) {
			residuesOfComplex.addAll(mol.getResidues());
		}
		List<Residue> neighbors = new ArrayList<>();
		for (int i = 0; i < residuesOfComplex.size(); i++) {
			// if baseRes is compared with himself, skip distance calculation
			if (residuesOfComplex.get(i).equals(baseRes))
					i++;
			if (ResidueDistanceCalculation.distanceBetweenResidues(baseRes, residuesOfComplex.get(i)) < distance) {
				neighbors.add(residuesOfComplex.get(i));
			}
		}

		return neighbors;
	}
	
	// get all distances from a residue
	/**
	 * 
	 * @param complex : complex made of multiple molecules with multiples residues
	 * @param baseRes  : random residue
	 * @param distance  : cutoff distance in Angströms
	 * @return a list of the smallest distances between the residue and his neighbors
	 */
	public static List<Double> getNeighborsDistance(Complex complex, Residue baseRes, int distance) {

		List<Residue> residuesOfComplex = new ArrayList<>();

		for (Molecule mol : complex.getMolecules()) {
			residuesOfComplex.addAll(mol.getResidues());
		}
		List<Double> neighborsDistances = new ArrayList<>();
		for (int i = 0; i < residuesOfComplex.size(); i++) {
			// if baseRes is compared with himself, skip distance calculation
			if (residuesOfComplex.get(i).equals(baseRes))
					i++;
			double d = ResidueDistanceCalculation.distanceBetweenResidues(baseRes, residuesOfComplex.get(i));
			if (d < distance) {
				neighborsDistances.add(d);
			}
		}
		//sort distances
		neighborsDistances.sort(Comparator.naturalOrder());

		return neighborsDistances;
	}

	/**
	 * 
	 * @param firstMol  : Frist Molecule to search for residues
	 * @param secondMol : Second Molecule to search for residues
	 * @param distance  : cutoff distance in Angströms
	 * @param sorted    : if true returns a sorted list of neighbors residues
	 * @return list of residues found to be neighbors
	 */
	public static List<Residue> getNeighborsFromMolecule(Molecule firstMol, Molecule secondMol, int distance,
			boolean sorted) {

		List<Residue> residuesMol1 = firstMol.getResidues();
		List<Residue> residuesMol2 = secondMol.getResidues();
		List<Residue> neighbors = new ArrayList<>();

		for (int i = 0; i < residuesMol1.size(); i++) {
			for (int j = 0; j < residuesMol2.size(); j++) {
				if (ResidueDistanceCalculation.distanceBetweenResidues(residuesMol1.get(i),
						residuesMol2.get(j)) <= distance) {
					neighbors.add(residuesMol2.get(j));
				}
			}
		}

		for (int i = 0; i < residuesMol2.size(); i++) {
			for (int j = 0; j < residuesMol1.size(); j++) {
				if (ResidueDistanceCalculation.distanceBetweenResidues(residuesMol2.get(i),
						residuesMol1.get(j)) <= distance) {
					neighbors.add(residuesMol1.get(j));
				}
			}
		}
		// remove duplicates from neighbors list
		List<Residue> neighborsWithoutDuplicates = neighbors.stream().distinct().collect(Collectors.toList());
		// sort results by ResidueNumber
		if (sorted) {
			neighborsWithoutDuplicates.sort(Comparator.comparing(Residue::getResidueNumber));
		}
		return neighborsWithoutDuplicates;

	}

	public static List<Residue> getNeighborsFromChains(Molecule cytokine, Molecule receptor, int cutoff,
			boolean sorted) {

		// get chains of cytokine
		List<Character> chainsOfCytokine = new ArrayList<Character>();
		HashMap<Character, List<Residue>> residuesCytokineChains = new HashMap<>();
		List<Residue> neighbors = new ArrayList<>();

		// For each chain of Cytokine, filter residues from this chain and search all
		// possible neighbors from the receptor molecule
		for (Chain chainChar : cytokine.getChain()) {
			System.out.println(chainChar.getChain());
			residuesCytokineChains.put(chainChar.getChain(), cytokine.getResidues().stream()
					.filter(r -> r.getAltChain() == chainChar.getChain()).collect(Collectors.toList()));

			// convert all values of HashMap to list
			List<Residue> cytokineResidues = new ArrayList<Residue>();
			cytokineResidues.addAll(cytokine.getResidues());

			// search neighbors
			for (int i = 0; i < cytokineResidues.size(); i++) { // TODO: old = residuesCytokineChains.keySet().size()
				for (int j = 0; j < receptor.getResidues().size(); j++) {
					if (ResidueDistanceCalculation.distanceBetweenResidues(cytokineResidues.get(i),
							receptor.getResidues().get(j)) <= cutoff) {
						neighbors.add(receptor.getResidues().get(j));
					}
				}
			}
		}

		// get chains of receptor
		List<Character> chainsOfReceptor = new ArrayList<Character>();
		HashMap<Character, List<Residue>> residuesReceptorChains = new HashMap<>();

		// For each chain of Receptor, filter residues from this chain and search all
		// possible neighbors from the cytokine molecule
		for (Chain chainChar : receptor.getChain()) {
			System.out.println(chainChar.getChain());
			residuesReceptorChains.put(chainChar.getChain(), receptor.getResidues().stream()
					.filter(r -> r.getAltChain() == chainChar.getChain()).collect(Collectors.toList()));

			// convert all values of HashMap to list
			List<Residue> receptorResidues = new ArrayList<Residue>();
			receptorResidues.addAll(receptor.getResidues());

			// search neighbors
			for (int i = 0; i < receptorResidues.size(); i++) { // TODO: old = receptorCytokineChains.keySet().size()
				for (int j = 0; j < cytokine.getResidues().size(); j++) {
					if (ResidueDistanceCalculation.distanceBetweenResidues(receptorResidues.get(i),
							cytokine.getResidues().get(j)) <= cutoff) {
						neighbors.add(cytokine.getResidues().get(j));
					}
				}
			}
		}

		// remove duplicates from neighbors list
		List<Residue> neighborsWithoutDuplicates = neighbors.stream().distinct().collect(Collectors.toList());
		// sort results by ResidueNumber
		if (sorted) {
			neighborsWithoutDuplicates.sort(Comparator.comparing(Residue::getResidueNumber));
		}
		return neighborsWithoutDuplicates;

	}

}