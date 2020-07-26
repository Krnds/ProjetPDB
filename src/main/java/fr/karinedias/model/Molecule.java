package fr.karinedias.model;

import java.util.List;

public class Molecule {

	private int moleculeID = 0;
	private String type = ""; //TODO: create enumeration for polymer/non-polymer/water (3 types only)
	private String description = "";
	private List<Chain> chains; // useful ??

	
	public Molecule(int moleculeID, String type) {
		this.moleculeID = moleculeID;
		this.type = type;
	}

	/*
	 * Constructor :
	 */
	public Molecule(int id, String description, String type) {
		this.moleculeID = id;
		this.description = description;
		this.type = type;
	}


	/*
	 * Getters for Molecules attributes :
	 */
	
	protected int getId() {
		return moleculeID;
	}


	protected String getDescription() {
		return description;
	}


	protected String getType() {
		return type;
	}

	@Override
	public String toString() {
		return "Molecule [moleculeID=" + moleculeID + ", " + (type != null ? "type=" + type + ", " : "")
				+ (description != null ? "description=" + description : "") + "]";
	}


	
}
