package fr.karinedias.model;

import java.util.List;

public class Molecule {

	private int moleculeID;
	private String type;
	private String description;
	private List<Residue> residues;
	private List<Chain> chains;

	/*
	 * Constructors with Residues:
	 */
	public Molecule(int id, String description, String type, List<Residue> residues) {
		this.moleculeID = id;
		this.description = description;
		this.type = type;
		this.residues = residues;
	}

	/*
	 * Constructors without Residues used in MoleculeParser :
	 */
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

	/**
	 * @return the chain
	 */
	public List<Chain> getChain() {
		return chains;
	}

	/**
	 * @param chain the chain to set
	 */
	public void setChain(List<Chain> chain) {
		this.chains = chain;
	}

	@Override
	public String toString() {
		return "Molecule [moleculeID=" + moleculeID + ", type=" + type + ", description=" + description + ", residues="
				+ residues + "]";
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Molecule)) {
			return false;
		}

		Molecule molecule = (Molecule) o;
		if (molecule.getResidues().size() == 0) {
			return molecule.getDescription().equals(description) && molecule.getId() == moleculeID
					&& molecule.getType().equals(type);
		} else {
			return molecule.getDescription().equals(description) && molecule.getId() == moleculeID
					&& molecule.getType().equals(type) && molecule.getResidues().equals(residues);
		}
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + description.hashCode();
		result = 31 * result + moleculeID;
		result = 31 * result + type.hashCode();
		result = 31 * result + getResidues().hashCode();
		return result;
	}

}