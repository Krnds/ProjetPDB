package main.fr.karinedias.model;

import java.util.ArrayList;
import java.util.List;

public class Atom {

	//Add "final" modifier for all fields ?
	private String recordName =  "ATOM";
	private int serialNumber = 651;
	private char atomName = 'N';
	private String alternateLocIndicator = "N";
	//Create an enumeration for amino residues ?
	private String residueName = "ALA";
	private int chainIdentifier = 1;
	private int residueSequenceNumber = 88;
	private List<Double> orthogonalCoordinates = new ArrayList<Double>(); //contains the coordinates for each atom
	
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
	
	public static void main(String[] args) {
		Atom myAtom = new Atom("ATOM", 651, 'N', "N", "ALA", 1, 88);
		myAtom.atomCoordinates(44.337, 34.325, 22.013);
	}


	protected String getRecordName() {
		return recordName;
	}


	protected void setRecordName(String recordName) {
		this.recordName = recordName;
	}


	protected int getSerialNumber() {
		return serialNumber;
	}


	protected void setSerialNumber(int serialNumber) {
		this.serialNumber = serialNumber;
	}


	protected char getAtomName() {
		return atomName;
	}


	protected void setAtomName(char atomName) {
		this.atomName = atomName;
	}


	protected String getAlternateLocIndicator() {
		return alternateLocIndicator;
	}


	protected void setAlternateLocIndicator(String alternateLocIndicator) {
		this.alternateLocIndicator = alternateLocIndicator;
	}


	protected String getResidueName() {
		return residueName;
	}


	protected void setResidueName(String residueName) {
		this.residueName = residueName;
	}


	protected int getChainIdentifier() {
		return chainIdentifier;
	}


	protected void setChainIdentifier(int chainIdentifier) {
		this.chainIdentifier = chainIdentifier;
	}


	protected int getResidueSequenceNumber() {
		return residueSequenceNumber;
	}


	protected void setResidueSequenceNumber(int residueSequenceNumber) {
		this.residueSequenceNumber = residueSequenceNumber;
	}


	public List<Double> getOrthogonalCoordinates() {
		return orthogonalCoordinates;
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
