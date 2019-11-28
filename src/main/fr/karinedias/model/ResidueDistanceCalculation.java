package main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ResidueDistanceCalculation {
	
	/**
	 * This class only calculates the distance between 2 residues with the CA method. 
	 * TODO: calculate the distance with the barycenter of C, N, O atoms. 
	 */

	private HashMap<String, Double> molecularWeight = new HashMap<String, Double>();
	//TODO: create an amino acid class with all parameters
	
	public ResidueDistanceCalculation() {
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

	
	/**
	 * Calculate the distance between 2 residues with the barycenter of atoms CNO.
	 * @param residue1
	 * @param residue2
	 */
	public static double distanceBetween2Residues (Residue residue1, Residue residue2) {
		
		//x, y, z coordinates of each atom (C,N,O) of residue1 : 
		List<Double> res1coordC = new ArrayList<Double>(3);
		//adding all coordinates from the C atom :
		residue1.getCoordCarbonAtom().addAll(res1coordC);
		//Same with the Nitrogen atom : 
		List<Double> res1coordN = new ArrayList<Double>(3);
		residue1.getCoordNitrogenAtom().addAll(res1coordN);
		//Same with the Oxygen atom :
		List<Double> res1coordO = new ArrayList<Double>(3);
		residue1.getCoordOxygenAtom().addAll(res1coordO);
		
		// calculate the barycenter of the residue :
		double sumXcoordRes1 = res1coordC.get(0) + res1coordN.get(0) + res1coordO.get(0);
		double sumYcoordRes1 = res1coordC.get(1) + res1coordN.get(1) + res1coordO.get(1);
		double sumZcoordRes1 = res1coordC.get(2) + res1coordN.get(2) + res1coordO.get(2);
		
		double barycenterRes1 = (sumXcoordRes1 + sumYcoordRes1 + sumZcoordRes1) / 9;
		
		/*
		 * /x, y, z coordinates of each atom (C,N,O) of residue2 : 
		 */
		List<Double> res2coordC = new ArrayList<Double>(3);
		//adding all coordinates from the C atom :
		residue2.getCoordCarbonAtom().addAll(res2coordC);
		//Same with the Nitrogen atom : 
		List<Double> res2coordN = new ArrayList<Double>(3);
		residue2.getCoordNitrogenAtom().addAll(res2coordN);
		//Same with the Oxygen atom :
		List<Double> res2coordO = new ArrayList<Double>(3);
		residue2.getCoordOxygenAtom().addAll(res2coordO);
		
		// calculate the barycenter of the residue1 :
		double sumXcoordRes2 = res2coordC.get(0) + res2coordN.get(0) + res2coordO.get(0);
		double sumYcoordRes2 = res2coordC.get(1) + res2coordN.get(1) + res2coordO.get(1);
		double sumZcoordRes2 = res2coordC.get(2) + res2coordN.get(2) + res2coordO.get(2);
		
		double barycenterRes2 = (sumXcoordRes2 + sumYcoordRes2 + sumZcoordRes2) / 9;
		

		/*
		 * Calculate the final distance between the 2 residues :
		 */
		double barycenter = Math.abs(barycenterRes2 - barycenterRes1);
		return barycenter;
	}
	
	
	public static double distanceBetween2ResiduesAlphaCarbon (Residue residue1, Residue residue2) {
		
		//creating all variables of the coordinates of each residue :
		double residue1Xcoordinate = residue1.getCoordXAlphaCarbon();
		double residue1Ycoordinate = residue1.getCoordYAlphaCarbon();
		double residue1Zcoordinate = residue1.getCoordZAlphaCarbon();
		
		double residue2Xcoordinate = residue2.getCoordXAlphaCarbon();
		double residue2Ycoordinate = residue2.getCoordYAlphaCarbon();
		double residue2Zcoordinate = residue2.getCoordZAlphaCarbon();
		
		double xDistanceBetweenResidues = Math.abs(Math.pow((residue1Xcoordinate  - residue2Xcoordinate), 2));
		double yDistanceBetweenResidues = Math.abs(Math.pow((residue1Ycoordinate  - residue2Ycoordinate), 2));
		double zDistanceBetweenResidues = Math.abs(Math.pow((residue1Zcoordinate  - residue2Zcoordinate), 2));
		
		double distance = Math.sqrt(xDistanceBetweenResidues + yDistanceBetweenResidues + zDistanceBetweenResidues);
		return distance;
	}
	
	
	//FOR TESTING PURPOSES
	public static void main(String[] args) {

	}
	
	
	
}
