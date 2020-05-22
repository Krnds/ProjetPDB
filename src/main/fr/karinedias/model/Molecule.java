package src.main.fr.karinedias.model;

import java.util.List;

public class Molecule {

	private int id = 0;
	private String name = "";
	private String type = ""; //TODO: create enumeration for polymer/non-polymer/water (3 types only)
	private int numberOfMolecules = 0;
	private List<Chain> chains; // useful ??

	
	/*
	 * Constructor :
	 */
	public Molecule(int id, String name, String type, int nMolecules) {
		this.id = id;
		this.name = name;
		this.type = type;
		numberOfMolecules = nMolecules;
	}


	/*
	 * Getters for Molecules attributes :
	 */
	
	protected int getId() {
		return id;
	}


	protected String getName() {
		return name;
	}


	protected String getType() {
		return type;
	}


	protected int getNumberOfMolecules() {
		return numberOfMolecules;
	}
	
}
