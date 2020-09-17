package fr.karinedias.math;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
	 * @param firstMol : Frist Molecule to search for residues
	 * @param secondMol : Second Molecule to search for residues
	 * @param distance : cutoff distance in Angströms
	 * @param sorted : if true returns a sorted list of neighbors residues
	 * @return list of residues found to be neighbors
	 */
	public static List<Residue> getNeighborsFromMolecule(Molecule firstMol, Molecule secondMol, int distance,
			boolean sorted) {

		List<Residue> residuesMol1 = firstMol.getResidues();
		List<Residue> residuesMol2 = secondMol.getResidues();
		List<Residue> neighbors = new ArrayList<>();
		int it = 0;
		for (int i = 0; i < residuesMol2.size(); i++) {
			for (int j = 0; j < residuesMol1.size(); j++) {
				if (ResidueDistanceCalculation.distanceBetweenResidues(residuesMol2.get(i),
						residuesMol1.get(j)) < distance) {
					neighbors.add(residuesMol1.get(j));
				}
				it++;
			}
		}
		System.out.println("Iterations = "  +it);
		// remove duplicates from neighbors list
		List<Residue> neighborsWithoutDuplicates = neighbors.stream().distinct().collect(Collectors.toList());
		// sort results by ResidueNumber
		if (sorted) {
			neighborsWithoutDuplicates.sort(Comparator.comparing(Residue::getResidueNumber));
		}
		return neighborsWithoutDuplicates;
	}

	// TODO: essayer d'implementer en Java l'algo KNN
	public static void kNearestNeighbors(Molecule cytokine, Molecule receptor) {

		Set<Residue> referencePoints = new HashSet<Residue>(cytokine.getResidues());
		Set<Residue> queryPoints = new HashSet<Residue>(receptor.getResidues());

		int k = -1;
		// Set<Float> distances
	}

//FOR TESTING PURPOSES
	public static void main(String[] args) {
		List<Residue> res = new ArrayList<>();
		res.add(new Residue("ALA", 1, 'A', 1, -5.837, 6.837, 37.876));
		res.add(new Residue("ALA", 2, 'A', 1, 5.037, 6.117, 37.876));
		res.add(new Residue("TYR", 3, 'A', 1, -5.837, 2.837, 3.876));
		res.add(new Residue("GLY", 4, 'A', 1, 7.837, -2.837, 1.076));
		res.add(new Residue("THR", 5, 'A', 1, 12.837, 7.837, 11.076));
		Molecule mol = new Molecule(0, "Test", "syn", res);

		Residue r = new Residue("ASP", 8, 'B', 2, 45.736, 23.746, 6.837);
		List<Residue> resFound = new ArrayList<>();
		resFound = getNeighborsResidues(mol, r, 40);

		System.out.println(res.get(0).toString());
		System.out.println(res.get(1).toString());
		double d = ResidueDistanceCalculation.distanceBetweenResidues(r, res.get(2));
		System.out.println(d);
		for (Residue residue : resFound) {
			System.out.println(residue.toString());
		}
	}

}