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
	 * static method because it can be called without creating a AminoAcidDistanceCalculation object
	 */

	public static double distanceBetween2Residues (AminoAcid residue1, AminoAcid residue2) {
		
		//creating all variables of the coordinates of each residue :
		double residue1Xcoordinate = residue1.getCoordAlphaCarbon().get(0);
		double residue1Ycoordinate = residue1.getCoordAlphaCarbon().get(1);
		double residue1Zcoordinate = residue1.getCoordAlphaCarbon().get(2);
		
		double residue2Xcoordinate = residue2.getCoordAlphaCarbon().get(0);
		double residue2Ycoordinate = residue2.getCoordAlphaCarbon().get(1);
		double residue2Zcoordinate = residue2.getCoordAlphaCarbon().get(2);
		
		double xDistanceBetweenResidues = Math.abs(Math.pow((residue1Xcoordinate  - residue2Xcoordinate), 2));
		double yDistanceBetweenResidues = Math.abs(Math.pow((residue1Ycoordinate  - residue2Ycoordinate), 2));
		double zDistanceBetweenResidues = Math.abs(Math.pow((residue1Zcoordinate  - residue2Zcoordinate), 2));
		
		double distance = Math.sqrt(xDistanceBetweenResidues + yDistanceBetweenResidues + zDistanceBetweenResidues);
		return distance;
	}
	
	
	
}
