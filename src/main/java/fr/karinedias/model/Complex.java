package fr.karinedias.model;

import java.util.List;

public class Complex {

	private String entryID = "";
	private List<Molecule> molecules;

	public Complex(String entryID, List<Molecule> molecules) {
		this.entryID = entryID;
		this.molecules = molecules;
	}

	protected List<Molecule> getMolecules() {
		return molecules;
	}

	protected void setMolecules(List<Molecule> molecules) {
		this.molecules = molecules;
	}

	protected String getEntryID() {
		return entryID;
	}

	public static void main(String[] args) {

	}

}
