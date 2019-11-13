package main.fr.karinedias.model;

import java.util.ArrayList;

public class Molecule {

	private String entryID = "";
	private int nChains = 0;
	private ArrayList<Residue> aminoAcidList = new ArrayList<Residue>();
	
	/*
	 * Constructor :
	 */
	public Molecule(String entryID, int nChains, ArrayList<Residue> aminoAcidList) {
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

	protected ArrayList<Residue> getAminoAcidList() {
		return aminoAcidList;
	}

	protected void setAminoAcidList(ArrayList<Residue> aminoAcidList) {
		this.aminoAcidList = aminoAcidList;
	}
	
	
	
	
}
