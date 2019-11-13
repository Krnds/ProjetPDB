package main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Residue {

	HashMap<String, Character> aminoAcidCode = new HashMap<String, Character>();
	private String residueName;
	private int residueNumber = 0;
	private double coordXAlphaCarbon;
	private double coordYAlphaCarbon;
	private double coordZAlphaCarbon;
	private List<Double> coordAlphaCarbon = new ArrayList<Double>(3); // coordinates of the alpha carbon of the residue
	// TODO: créér une liste d'atomes pour chaque acide aminé
	private List<Atom> atomsOfResidue = new ArrayList<Atom>(); //TODO: create a metod for storing all the atoms of an AA

	public ArrayList<Atom> getListOfAtoms() {
		
		//TODO:
		
		return null;
		
	}
	
	protected List<Double> getCoordAlphaCarbon() {
		return coordAlphaCarbon;
	}

	protected void setCoordAlphaCarbon(List<Double> coordAlphaCarbon) {
		this.coordAlphaCarbon = coordAlphaCarbon;
	}

	/*
	 * Constructor of AminoAcid object
	 */
	public Residue(String residueName, int residueSequenceNumber, List<Atom> atomsOfResidue) {
		this.residueName = residueName;
		this.residueNumber = residueSequenceNumber;
		// There's the alpha carbon in atomsOfResidue
		

	}

	public void carbonAlphaRetriver(Residue a) {
		//TODO find in each amino acid entry their alpha carbon
		if (a.atomsOfResidue.contains("CA")) {
			
		}
	}

	// TODO link the residueName to the Hashmpa containing the code of the residue

	private double barycenterOfResidue; // barycentre des atomes C, N et O d'un résidu à faire ensuite

	public Residue(AminoAcidCode code) {
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

	/*
	 * example : aminoacid PHE n° 71 of chain B ATOM 1597 N PHE B 71 -3.488 -10.470
	 * 2.444 1.00 10.48 N ATOM 1598 CA PHE B 71 -3.362 -10.263 3.894 1.00 13.31 C
	 * ATOM 1599 C PHE B 71 -4.488 -10.953 4.656 1.00 13.47 C ATOM 1600 O PHE B 71
	 * -4.262 -11.672 5.629 1.00 13.74 O ATOM 1601 CB PHE B 71 -3.466 -8.734 4.164
	 * 1.00 10.18 C ATOM 1602 CG PHE B 71 -3.307 -8.346 5.625 1.00 11.62 C ATOM 1603
	 * CD1 PHE B 71 -4.382 -8.291 6.479 1.00 14.43 C ATOM 1604 CD2 PHE B 71 -2.042
	 * -8.039 6.090 1.00 16.17 C ATOM 1605 CE1 PHE B 71 -4.224 -7.920 7.820 1.00
	 * 13.51 C ATOM 1606 CE2 PHE B 71 -1.861 -7.677 7.422 1.00 16.61 C ATOM 1607 CZ
	 * PHE B 71 -2.948 -7.614 8.272 1.00 13.82 C
	 */

	public static void main(String[] args) {

		/*
		 * ATOM 166 N N A GLU A 1 23 ? 28.231 -18.450 -6.259 0.50 24.82 ? 23 GLU A N 1
		 * ATOM 167 N N B GLU A 1 23 ? 28.203 -18.435 -6.305 0.50 24.49 ? 23 GLU A N 1
		 * ATOM 168 C CA A GLU A 1 23 ? 28.162 -19.216 -7.538 0.50 25.61 ? 23 GLU A CA 1
		 * ATOM 169 C CA B GLU A 1 23 ? 28.226 -19.126 -7.628 0.50 25.09 ? 23 GLU A CA 1
		 * ATOM 170 C C A GLU A 1 23 ? 27.142 -18.506 -8.432 0.50 23.57 ? 23 GLU A C 1
		 * ATOM 171 C C B GLU A 1 23 ? 27.100 -18.552 -8.492 0.50 23.29 ? 23 GLU A C 1
		 * ATOM 172 O O A GLU A 1 23 ? 27.467 -18.140 -9.587 0.50 23.43 ? 23 GLU A O 1
		 * ATOM 173 O O B GLU A 1 23 ? 27.283 -18.363 -9.713 0.50 22.94 ? 23 GLU A O 1
		 * ATOM 174 C CB A GLU A 1 23 ? 27.729 -20.677 -7.347 0.50 27.45 ? 23 GLU A CB 1
		 * ATOM 175 C CB B GLU A 1 23 ? 28.056 -20.639 -7.500 0.50 26.61 ? 23 GLU A CB 1
		 * ATOM 176 C CG A GLU A 1 23 ? 28.716 -21.587 -6.636 0.50 30.37 ? 23 GLU A CG 1
		 * ATOM 177 C CG B GLU A 1 23 ? 29.229 -21.384 -6.901 0.50 29.28 ? 23 GLU A CG 1
		 * ATOM 178 C CD A GLU A 1 23 ? 28.327 -23.061 -6.598 0.50 32.29 ? 23 GLU A CD 1
		 * ATOM 179 C CD B GLU A 1 23 ? 28.762 -22.531 -6.020 0.50 30.67 ? 23 GLU A CD 1
		 * ATOM 180 O OE1 A GLU A 1 23 ? 28.280 -23.704 -7.679 0.50 34.31 ? 23 GLU A OE1
		 * 1 ATOM 181 O OE1 B GLU A 1 23 ? 28.986 -23.724 -6.375 0.50 32.33 ? 23 GLU A
		 * OE1 1 ATOM 182 O OE2 A GLU A 1 23 ? 28.146 -23.593 -5.481 0.50 33.72 ? 23 GLU
		 * A OE2 1 ATOM 183 O OE2 B GLU A 1 23 ? 28.205 -22.230 -4.953 0.50 30.31 ? 23
		 * GLU A OE2 1
		 */
		Residue phe71 = new Residue(AminoAcidCode.phenylalanine);

		String atomsToParse = "ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  \n"
				+ "ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  \n"
				+ "ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  \n"
				+ "ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  \n"
				+ "ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  \n"
				+ "ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  \n"
				+ "ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  \n"
				+ "ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  \n"
				+ "ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  \n"
				+ "ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  \n"
				+ "ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C";
		// phe71.atomsOfAminoAcid.add("PHE", 'F');
	}


}
