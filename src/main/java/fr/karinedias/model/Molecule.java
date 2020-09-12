package fr.karinedias.model;

import java.util.List;

public class Molecule {

	private int moleculeID = 0;
	private String type = ""; // TODO: create enumeration for polymer/non-polymer/water (3 types only)
	private String description = "";
	private List<Residue> residues;

	public Molecule(int moleculeID, String type) {
		this.moleculeID = moleculeID;
		this.type = type;
	}

	/*
	 * Constructor :
	 */
	public Molecule(int id, String description, String type, List<Residue> residues) {
		this.moleculeID = id;
		this.description = description;
		this.type = type;
		this.residues = residues;
	}

	public Molecule(int id, String description, String type) {
		this.moleculeID = id;
		this.description = description;
		this.type = type;
	}

	public List<Residue> getResidues() {
		return residues;
	}

	public void setResidues(List<Residue> residues) {
		this.residues = residues;
	}

	public int getId() {
		return moleculeID;
	}

	public String getType() {
		return type;
	}

	public String getDescription() {
		return description;
	}

	@Override
	public String toString() {
		return "Molecule [moleculeID=" + moleculeID + ", type=" + type + ", description=" + description + ", residues="
				+ residues + "]";
	}

}
