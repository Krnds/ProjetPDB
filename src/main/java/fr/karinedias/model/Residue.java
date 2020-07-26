package fr.karinedias.model;

import java.util.HashMap;

public class Residue {

	HashMap<String, Character> aminoAcidCode = new HashMap<String, Character>();
	
	private String residueName = "";
	private int residueNumber = 0;
	private Atom alphaCarbon;

	/*
	 * Constructor of AminoAcid object
	 */
	public Residue(String residueName, int residueSequenceNumber, Atom alphaCarbon) {
		this.residueName = residueName;
		this.residueNumber = residueSequenceNumber;
		this.alphaCarbon = alphaCarbon;

	}

	/**
	 * @return the aminoAcidCode
	 */
	public HashMap<String, Character> getAminoAcidCode() {
		return aminoAcidCode;
	}

	/**
	 * @param aminoAcidCode the aminoAcidCode to set
	 */
	public void setAminoAcidCode(HashMap<String, Character> aminoAcidCode) {
		this.aminoAcidCode = aminoAcidCode;
	}

	/**
	 * @return the residueName
	 */
	public String getResidueName() {
		return residueName;
	}

	/**
	 * @param residueName the residueName to set
	 */
	public void setResidueName(String residueName) {
		this.residueName = residueName;
	}

	/**
	 * @return the residueNumber
	 */
	public int getResidueNumber() {
		return residueNumber;
	}

	/**
	 * @param residueNumber the residueNumber to set
	 */
	public void setResidueNumber(int residueNumber) {
		this.residueNumber = residueNumber;
	}

	/**
	 * @return the alphaCarbon
	 */
	public Atom getAlphaCarbon() {
		return alphaCarbon;
	}

	/**
	 * @param alphaCarbon the alphaCarbon to set
	 */
	public void setAlphaCarbon(Atom alphaCarbon) {
		this.alphaCarbon = alphaCarbon;
	}

}
