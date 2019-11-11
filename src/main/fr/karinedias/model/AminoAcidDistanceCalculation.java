package main.fr.karinedias.model;

import java.util.HashMap;

public class AminoAcidDistanceCalculation {
	
	/**
	 * This class only calculates the distance between 2 residues with the CA method. 
	 * TODO: calculate the distance with the barycenter of C, N, O atoms. 
	 */

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
 * Distance calculation between 2 amino acids : it's the distance between the 2 alpha carbons of each amino acid.
 * TODO: calculate the distance between the C, N and O atoms 
 * - first calculate the barycenter of C, N and O atoms of each residue
 * - second calculate the distance between the 2 barycenters 
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
	
	
	//FOR TESTING PURPOSES
	public static void main(String[] args) {
		
		//ATOM   2785 C  CA  . LEU C 1 48  ? -9.411  16.205  -22.443 1.00 30.64 ? 48  LEU C CA  1 
		AminoAcid residue1 = new AminoAcid("LEU", 48, (List<Atom> residue1ListOfAtoms = new List()));
		//TODO: 
		//ATOM   3062 C  CA  . ASP C 1 85  ? -2.118  3.228   -2.634  1.00 36.83 ? 85  ASP C CA  1 
		AminoAcid residue2 = new AminoAcid("", 48, (List<Atom> residue1ListOfAtoms = new List()));
	}
	
	
	
}
