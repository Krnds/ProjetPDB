package src.main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.List;

public class Molecule {

	private String entryID = "";
	private List<Chain> chains;
	private List<Residue> aminoAcidList = new ArrayList<Residue>(); //to delete -> into Chain object 
	
	/*
	 * Constructor :
	 */
	public Molecule(String entryID, List<Chain> chains, List<Residue> aminoAcidList) {
		this.entryID = entryID;
		this.chains = chains;
		this.aminoAcidList = aminoAcidList;//to delete -> into Chain object
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

	protected List<Chain> getnChains() {
		return chains;
	}

	// add a chain to know chains
	protected void setnChains(Chain chain) {
		this.chains.add(chain);
	}

	protected List<Residue> getAminoAcidList() {
		return aminoAcidList;
	}

	protected void setAminoAcidList(ArrayList<Residue> aminoAcidList) {
		this.aminoAcidList = aminoAcidList;
	}
	
	
	
	
}
