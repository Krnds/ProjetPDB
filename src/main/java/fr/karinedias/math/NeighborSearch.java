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
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;

public class NeighborSearch {

	List<Molecule> molecules;

	// get all residues within a distance X from a residue
	/**
	 * 
	 * @param molecule : Polymer containing all the residues to search for
	 * @param baseRes  : TODO: a residue from the molecule or from another ??
	 * @param distance : minimal distance between the residue and
	 * @return a list of nearest neighbors residues within the parameter residue
	 */
	public static List<Residue> getNeighborsResidues(Molecule molecule, Residue baseRes, int distance) {

		List<Residue> residues = molecule.getResidues();
		List<Residue> neighbors = new ArrayList<>();
		for (int i = 0; i < residues.size(); i++) {
			if (ResidueDistanceCalculation.distanceBetweenResidues(baseRes, residues.get(i)) < distance) {
				neighbors.add(residues.get(i));
			}
		}

		return neighbors;
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

	public static List<Residue> getNeighborsFromChains(Molecule cytokine, Molecule receptor, int cutoff, boolean sorted) {
		
		// get chains of cytokine
		List<Character> chainsOfCytokine = new ArrayList<Character>();
		HashMap<Character, List<Residue>> residuesCytokineChains = new HashMap<>();
		List<Residue> neighbors = new ArrayList<>();
		
		
		// For each chain of Cytokine, filter residues from this chain and search all possible neighbors from the receptor molecule
		for (Chain chainChar : cytokine.getChain()) {
			System.out.println(chainChar.getChain());
			residuesCytokineChains.put(chainChar.getChain(),cytokine.getResidues().stream().filter(r -> r.getAltChain() == chainChar.getChain()).collect(Collectors.toList()));
			
			//convert all values of HashMap to list 
			List<Residue> cytokineResidues = new ArrayList<Residue>();
			cytokineResidues.addAll(cytokine.getResidues());
			
			// search neighbors
			for (int i = 0; i < cytokineResidues.size(); i++) { //TODO: old = residuesCytokineChains.keySet().size()
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
		
		// For each chain of Receptor, filter residues from this chain and search all possible neighbors from the cytokine molecule
		for (Chain chainChar : receptor.getChain()) {
			System.out.println(chainChar.getChain());
			residuesReceptorChains.put(chainChar.getChain(),receptor.getResidues().stream().filter(r -> r.getAltChain() == chainChar.getChain()).collect(Collectors.toList()));
			
			//convert all values of HashMap to list 
			List<Residue> receptorResidues = new ArrayList<Residue>();
			receptorResidues.addAll(receptor.getResidues());
			
			// search neighbors
			for (int i = 0; i < receptorResidues.size(); i++) { //TODO: old = receptorCytokineChains.keySet().size()
				for (int j = 0; j < cytokine.getResidues().size(); j++) {
					if (ResidueDistanceCalculation.distanceBetweenResidues(receptorResidues.get(i),
							cytokine.getResidues().get(j)) <= cutoff) {
						neighbors.add(cytokine.getResidues().get(j));
					}
				}
			}
			
			
		}
		
		
		
		// get residues for each chain of receptor
//		for (Chain chainChar : receptor.getChain()) {
//			System.out.println(receptor.getChain());
//			residuesReceptorChains.put(chainChar.getChain(),receptor.getResidues().stream().filter(r -> r.getAltChain() == chainChar.getChain()).collect(Collectors.toList()));
//		}


		
		
	// ANTHONY :	residuesCytokineChains.values().stream().flatMap(liste -> liste.get(index))

		// remove duplicates from neighbors list
		List<Residue> neighborsWithoutDuplicates = neighbors.stream().distinct().collect(Collectors.toList());
		// sort results by ResidueNumber
		if (sorted) {
			neighborsWithoutDuplicates.sort(Comparator.comparing(Residue::getResidueNumber));
		}
		return neighborsWithoutDuplicates;

	}

////FOR TESTING PURPOSES
//	public static void main(String[] args) {
//		List<Residue> res = new ArrayList<>();
//		res.add(new Residue("ALA", 1, 'A', 1, -5.837, 6.837, 37.876));
//		res.add(new Residue("ALA", 2, 'A', 1, 5.037, 6.117, 37.876));
//		res.add(new Residue("TYR", 3, 'A', 1, -5.837, 2.837, 3.876));
//		res.add(new Residue("GLY", 4, 'A', 1, 7.837, -2.837, 1.076));
//		res.add(new Residue("THR", 5, 'A', 1, 12.837, 7.837, 11.076));
//		Molecule mol = new Molecule(0, "Test", "syn", res);
//
//		Residue r = new Residue("ASP", 8, 'B', 2, 45.736, 23.746, 6.837);
//		List<Residue> resFound = new ArrayList<>();
//		resFound = getNeighborsResidues(mol, r, 40);
//
//		System.out.println(res.get(0).toString());
//		System.out.println(res.get(1).toString());
//		double d = ResidueDistanceCalculation.distanceBetweenResidues(r, res.get(2));
//		System.out.println(d);
//		for (Residue residue : resFound) {
//			System.out.println(residue.toString());
//		}
//	}

}