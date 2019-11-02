package main.fr.karinedias.model;

import java.util.HashMap;

public class AminoAcidDistanceCalculation {

	private HashMap<String, Double> molecularWeight = new HashMap<String, Double>();
	//TODO: create an amino acid class with all parameters
	
	public AminoAcidDistanceCalculation() {
		molecularWeight.put("Alanine", 89.1);
		molecularWeight.put("Arginine", 174.2);
		molecularWeight.put("Asparagine", 132.1);
		molecularWeight.put("Aspartate", 133.1);
		molecularWeight.put("Cysteine", 121.2);
		molecularWeight.put("Glutamate", 147.1);
		molecularWeight.put("Glutamine", 146.2);
		molecularWeight.put("Glycine", 75.1);
		molecularWeight.put("Histidine", 155.2);
	}
	
	/*
	 * Example of calculation of the distance between 2 residues of 2 distinct molecules of a PDB file :
	 */

	public double distanceBetween2Residues (AminoAcid residue1, AminoAcid residue2) {
		double distance = Math.sqrt(0.0);
		return distance;
	}
	
	
	
}
