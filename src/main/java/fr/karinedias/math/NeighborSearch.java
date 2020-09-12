package fr.karinedias.math;

import java.io.ObjectInputStream.GetField;
import java.util.ArrayList;
import java.util.List;

import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;

public class NeighborSearch {

	List<Molecule> molecules;
	//TODO: create an Object NeighborSearch ?
	
	// get all residues within a distance X from a residue 
	/**
	 * 
	 * @param molecule : Polymer containing all the residues to search for
	 * @param baseRes : TODO: a residue from the molecule or from another ??
	 * @param distance : minimal distance between the residue and 
	 * @return a list of nearest neighbors residues within the parameter residue 
	 */
	public static List<Residue> getNeighborsResidues(Molecule molecule, Residue baseRes, int distance) {

		List<Residue> residues = molecule.getResidues();
		List<Residue> neighbors = new ArrayList<>();
		for (int i = 0; i < residues.size(); i++) {
			if (ResidueDistanceCalculation.distanceBetween2ResiduesAlphaCarbon(baseRes, residues.get(i)) <= distance) {
				neighbors.add(residues.get(i));
			}
		}

		return neighbors;
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
		double d = ResidueDistanceCalculation.distanceBetween2ResiduesAlphaCarbon(r, res.get(2));
		System.out.println(d);
		for (Residue residue : resFound) {
			System.out.println(residue.toString());
		}
	}

}
