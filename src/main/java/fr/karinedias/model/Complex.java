package fr.karinedias.model;

import java.util.List;

public class Complex {

	private String entryID = "";
	private List<Molecule> molecules;

	public Complex(String entryID, List<Molecule> molecules) {
		this.entryID = entryID;
		this.molecules = molecules;
	}

	/**
	 * @return the entryID
	 */
	public String getEntryID() {
		return entryID;
	}

	/**
	 * @param entryID the entryID to set
	 */
	public void setEntryID(String entryID) {
		this.entryID = entryID;
	}

	/**
	 * @return the molecules
	 */
	public List<Molecule> getMolecules() {
		return molecules;
	}

	/**
	 * @param molecules the molecules to set
	 */
	public void setMolecules(List<Molecule> molecules) {
		this.molecules = molecules;
	}

	@Override
	public String toString() {
		return "Complex [entryID=" + entryID + ", molecules=" + molecules + "]";
	}



}
