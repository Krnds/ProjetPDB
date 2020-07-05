package main.fr.karinedias.math;

import main.fr.karinedias.model.Atom;
import main.fr.karinedias.model.Residue;

public class ResidueDistanceCalculation {
	
	/**
	 * This class only calculates the distance between 2 residues with the Alpha Carbons method. 
	 * TODO: calculate the distance with the barycenter of C, N, O atoms ?
	 */
	
	/**
	 * Returns the distance between 2 residues with the coordinates of the
	 * alpha carbons
	 * @param residue1
	 * @param residue2
	 * @return atomic distance in Å between the residues
	 */
	
	public static double distanceBetween2ResiduesAlphaCarbon (Residue residue1, Residue residue2) {
		
		//creating all variables of the coordinates of each residue :
		double residue1Xcoordinate = residue1.getAlphaCarbon().getxAtomCoordinate();
		double residue1Ycoordinate = residue1.getAlphaCarbon().getyAtomCoordinate();
		double residue1Zcoordinate = residue1.getAlphaCarbon().getzAtomCoordinate();
		
		double residue2Xcoordinate = residue2.getAlphaCarbon().getxAtomCoordinate();
		double residue2Ycoordinate = residue2.getAlphaCarbon().getyAtomCoordinate();
		double residue2Zcoordinate = residue2.getAlphaCarbon().getzAtomCoordinate();
		
		double xDistanceBetweenResidues = Math.abs(Math.pow((residue1Xcoordinate  - residue2Xcoordinate), 2));
		double yDistanceBetweenResidues = Math.abs(Math.pow((residue1Ycoordinate  - residue2Ycoordinate), 2));
		double zDistanceBetweenResidues = Math.abs(Math.pow((residue1Zcoordinate  - residue2Zcoordinate), 2));
		
		double distance = Math.sqrt(xDistanceBetweenResidues + yDistanceBetweenResidues + zDistanceBetweenResidues);
		return distance;
	}
	
	
	//FOR TESTING PURPOSES
	public static void main(String[] args) {

		//Residue 1 :
		//ATOM   919  C  CA  . TRP A  1 121 ? -25.537 5.860   27.678  1.00 25.73  ? 141 TRP A CA  1
		Residue res121 = new Residue("TRP", 121, (new Atom(6, 'C', "CA", "TRP", 'A', 1, 121, 6.8f, 8.9f, 4.88f)));
		Residue res919 = new Residue("ARG", 919, (new Atom(56, 'C', "CA", "ARG", 'B', 2, 919, -5.0f, 22.3f, 1.7f)));
		
		double distance = distanceBetween2ResiduesAlphaCarbon(res121, res919);
		System.out.println("The distance between the two residues is " + Math.round(distance) + " Angström");
	}
	
	
	
}
