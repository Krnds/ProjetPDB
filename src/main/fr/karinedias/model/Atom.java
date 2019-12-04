package main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.List;

public class Atom {

	// Add "final" modifier for all fields ?
	private String recordName = "";
	private int serialNumber;
	private char atomName;
	private String alternateLocIndicator;
	// Create an enumeration for amino residues ?
	private String residueName = "ALA";
	private int chainIdentifier = 1;
	private int residueSequenceNumber = 88;
	private List<Double> orthogonalCoordinates = new ArrayList<Double>(); // contains the coordinates for each atom

	public Atom(String recordName, int serialNumber, char atomName, String alternateLocIndicator, String residueName,
			int chainIdentifier, int residueSequenceNumber) {
		this.recordName = recordName;
		this.serialNumber = serialNumber;
		this.atomName = atomName;
		this.alternateLocIndicator = alternateLocIndicator;
		this.residueName = residueName;
		this.chainIdentifier = chainIdentifier;
		this.residueSequenceNumber = residueSequenceNumber;
	}

	public Atom() {
		// TODO Auto-generated constructor stub
	}

	public ArrayList<Double> atomCoordinates(Double x, Double y, Double z) {

		orthogonalCoordinates.add(x);
		orthogonalCoordinates.add(y);
		orthogonalCoordinates.add(x);

		return (ArrayList<Double>) orthogonalCoordinates;
	}

	public String getRecordName() {
		return recordName;
	}

	public void setRecordName(String recordName) {
		this.recordName = recordName;
	}

	public int getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}

	public char getAtomName() {
		return atomName;
	}

	public void setAtomName(char atomName) {
		this.atomName = atomName;
	}

	public String getAlternateLocIndicator() {
		return alternateLocIndicator;
	}

	public void setAlternateLocIndicator(String alternateLocIndicator) {
		this.alternateLocIndicator = alternateLocIndicator;
	}

	public String getResidueName() {
		return residueName;
	}

	public void setResidueName(String residueName) {
		this.residueName = residueName;
	}

	public int getChainIdentifier() {
		return chainIdentifier;
	}

	public void setChainIdentifier(int chainIdentifier) {
		this.chainIdentifier = chainIdentifier;
	}

	public int getResidueSequenceNumber() {
		return residueSequenceNumber;
	}

	public void setResidueSequenceNumber(int residueSequenceNumber) {
		this.residueSequenceNumber = residueSequenceNumber;
	}

	public List<Double> getOrthogonalCoordinates() {
		return orthogonalCoordinates;
	}

	public void setOrthogonalCoordinates(List<Double> orthogonalCoordinates) {
		this.orthogonalCoordinates = orthogonalCoordinates;
	}

	@Override
	public String toString() {
		return "Atom [" + (recordName != null ? "recordName=" + recordName + ", " : "") + "serialNumber=" + serialNumber
				+ ", atomName=" + atomName + ", "
				+ (alternateLocIndicator != null ? "alternateLocIndicator=" + alternateLocIndicator + ", " : "")
				+ (residueName != null ? "residueName=" + residueName + ", " : "") + "chainIdentifier="
				+ chainIdentifier + ", residueSequenceNumber=" + residueSequenceNumber + ", "
				+ (orthogonalCoordinates != null ? "orthogonalCoordinates=" + orthogonalCoordinates : "") + "]";
	}

}
