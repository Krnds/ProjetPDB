package fr.karinedias.math;

import fr.karinedias.model.Residue;

public class ResidueDistanceCalculation {

	/**
	 * This class only calculates the distance between 2 residues with the Alpha
	 * Carbons method. TODO: calculate the distance with the barycenter of C, N, O
	 * atoms ?
	 */

	/**
	 * Returns the distance between 2 residues with the coordinates of the alpha
	 * carbons
	 * 
	 * @param residue1
	 * @param residue2
	 * @return atomic distance in Å between the residues
	 */

	public static double distanceBetween2ResiduesAlphaCarbon(Residue residue1, Residue residue2) {

		// creating all variables of the coordinates of each residue :
		double residue1Xcoordinate = residue1.getxCoord();
		double residue1Ycoordinate = residue1.getyCoord();
		double residue1Zcoordinate = residue1.getzCoord();

		double residue2Xcoordinate = residue2.getxCoord();
		double residue2Ycoordinate = residue2.getyCoord();
		double residue2Zcoordinate = residue2.getzCoord();

		double xDistanceBetweenResidues = Math.abs(Math.pow((residue1Xcoordinate - residue2Xcoordinate), 2));
		double yDistanceBetweenResidues = Math.abs(Math.pow((residue1Ycoordinate - residue2Ycoordinate), 2));
		double zDistanceBetweenResidues = Math.abs(Math.pow((residue1Zcoordinate - residue2Zcoordinate), 2));

		double distance = Math.sqrt(xDistanceBetweenResidues + yDistanceBetweenResidues + zDistanceBetweenResidues);
		return distance;
	}

}
