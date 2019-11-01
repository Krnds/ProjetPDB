package main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AminoAcid {

	HashMap<String, Character> aminoAcidCode = new HashMap<String, Character>();
	private int aminoAcidNumber = 0;
	private double coordX;
	private double coordY;
	private double coordZ;

	public AminoAcid(AminoAcidCode code) {
		aminoAcidCode.put("GLY", 'G');
		aminoAcidCode.put("ALA", 'A');
		aminoAcidCode.put("VAL", 'V');
		aminoAcidCode.put("LEU", 'L');
		aminoAcidCode.put("ILE", 'I');
		aminoAcidCode.put("PHE", 'F');
		aminoAcidCode.put("TYR", 'Y');
		aminoAcidCode.put("TRP", 'W');
		aminoAcidCode.put("PRO", 'P');
		aminoAcidCode.put("HIS", 'H');
		aminoAcidCode.put("LYS", 'K');
		aminoAcidCode.put("ARG", 'R');
		aminoAcidCode.put("SER", 'S');
		aminoAcidCode.put("THR", 'T');
		aminoAcidCode.put("GLU", 'E');
		aminoAcidCode.put("GLN", 'Q');
		aminoAcidCode.put("ASP", 'D');
		aminoAcidCode.put("ASN", 'N');
		aminoAcidCode.put("CYS", 'C');
		aminoAcidCode.put("MET", 'M');
	}

	// TODO: créér une liste d'atomes pour chaque acide aminé
	private List<Atom> atomsOfAminoAcid = new ArrayList<Atom>();

/*
 * example : aminoacid PHE n° 71 of chain B
ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  
ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  
ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  
ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  
ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  
ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  
ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  
ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  
ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  
ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  
ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C
 */
	
	public static void main(String[] args) {
		AminoAcid phe71 = new AminoAcid(AminoAcidCode.phenylalanine);
		
		String atomsToParse = "ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  \n" + 
				"ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  \n" + 
				"ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  \n" + 
				"ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  \n" + 
				"ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  \n" + 
				"ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  \n" + 
				"ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  \n" + 
				"ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  \n" + 
				"ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  \n" + 
				"ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  \n" + 
				"ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C";
		//phe71.atomsOfAminoAcid.add("PHE", 'F');
	}
	
	public void atomParser(String input) {
		
		
	}
	

}
