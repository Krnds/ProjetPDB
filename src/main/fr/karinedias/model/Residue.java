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

	// Coordinates of C, N and O atoms of the residue :
	private List<Double> coordCarbonAtom = new ArrayList<Double>(3);
	private List<Double> coordNitrogenAtom = new ArrayList<Double>(3);
	private List<Double> coordOxygenAtom = new ArrayList<Double>(3);

	/*
	 * Constructor of AminoAcid object
	 */
	public Residue(String residueName, int residueSequenceNumber, List<Atom> atomsOfResidue) {
		this.residueName = residueName;
		this.residueNumber = residueSequenceNumber;
		// There's the alpha carbon in atomsOfResidue

	}

	// TODO link the residueName to the Hashmpa containing the code of the residue

	public String getResidueName() {
		return residueName;
	}

	public void setResidueName(String residueName) {
		this.residueName = residueName;
	}

	public int getResidueNumber() {
		return residueNumber;
	}

	public void setResidueNumber(int residueNumber) {
		this.residueNumber = residueNumber;
	}

	protected List<Double> getCoordCarbonAtom() {
		return coordCarbonAtom;
	}

	protected void setCoordCarbonAtom(List<Double> coordCarbonAtom) {
		this.coordCarbonAtom = coordCarbonAtom;
	}

	protected List<Double> getCoordNitrogenAtom() {
		return coordNitrogenAtom;
	}

	protected void setCoordNitrogenAtom(List<Double> coordNitrogenAtom) {
		this.coordNitrogenAtom = coordNitrogenAtom;
	}

	protected List<Double> getCoordOxygenAtom() {
		return coordOxygenAtom;
	}

	protected void setCoordOxygenAtom(List<Double> coordOxygenAtom) {
		this.coordOxygenAtom = coordOxygenAtom;
	}

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

	protected double getCoordXAlphaCarbon() {
		return coordXAlphaCarbon;
	}

	protected void setCoordXAlphaCarbon(double coordXAlphaCarbon) {
		this.coordXAlphaCarbon = coordXAlphaCarbon;
	}

	protected double getCoordYAlphaCarbon() {
		return coordYAlphaCarbon;
	}

	protected void setCoordYAlphaCarbon(double coordYAlphaCarbon) {
		this.coordYAlphaCarbon = coordYAlphaCarbon;
	}

	protected double getCoordZAlphaCarbon() {
		return coordZAlphaCarbon;
	}

	protected void setCoordZAlphaCarbon(double coordZAlphaCarbon) {
		this.coordZAlphaCarbon = coordZAlphaCarbon;
	}

}
