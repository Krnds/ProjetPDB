package fr.karinedias.model;

import java.util.ArrayList;

public class Molecule {

	private String entryID = "";
	private int nChains = 0;
	private ArrayList<AminoAcid> aminoAcidList = new ArrayList<AminoAcid>();
	
	/*
	 * Constructor :
	 */
	public Molecule(String entryID, int nChains, ArrayList<AminoAcid> aminoAcidList) {
		this.entryID = entryID;
		this.nChains = nChains;
		this.aminoAcidList = aminoAcidList;
	}

	/*
	 * Getters and setters from the Molecule object fields :
	 */
	protected String getEntryID() {
		return entryID;
	}

	protected void setEntryID(String entryID) {
		this.entryID = entryID;
	}

	protected int getnChains() {
		return nChains;
	}

	protected void setnChains(int nChains) {
		this.nChains = nChains;
	}

	protected ArrayList<AminoAcid> getAminoAcidList() {
		return aminoAcidList;
	}

	protected void setAminoAcidList(ArrayList<AminoAcid> aminoAcidList) {
		this.aminoAcidList = aminoAcidList;
	}
	
	
	
	
}
