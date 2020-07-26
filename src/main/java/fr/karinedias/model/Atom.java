package fr.karinedias.model;

public class Atom {

	// Add "final" modifier for all fields ?

	private int atomNumber;
	private char atomName;
	private String alternateLocIndicator;
	// Create an enumeration for amino residues ?
	private String residueName;
	private char chainNameIdentifier;
	private int chainNumberIdentifier;
	private int residueSequenceNumber;
	// coordinates :
	private float xAtomCoordinate;
	private float yAtomCoordinate;
	private float zAtomCoordinate;

	public Atom(int atomNumber, char atomName, String altLoc, String resName, char chainName, int chainNumber, int resNumber, float xCoord, float yCoord, float zCoord) {

			this.atomNumber = atomNumber;
			this.atomName = atomName;
			this.alternateLocIndicator = altLoc;
			this.residueName = resName;
			this.chainNameIdentifier = chainName;
			this.chainNumberIdentifier = chainNumber;
			this.residueSequenceNumber = resNumber;
			this.xAtomCoordinate = xCoord;
			this.yAtomCoordinate = yCoord;
			this.zAtomCoordinate = zCoord;
	}

	public Atom() {
		// TODO Auto-generated constructor stub
	}

	public int getAtomNumber() {
		return atomNumber;
	}

	public char getAtomName() {
		return atomName;
	}

	public String getAlternateLocIndicator() {
		return alternateLocIndicator;
	}

	public String getResidueName() {
		return residueName;
	}

	public char getChainNameIdentifier() {
		return chainNameIdentifier;
	}

	public int getChainNumberIdentifier() {
		return chainNumberIdentifier;
	}

	public int getResidueSequenceNumber() {
		return residueSequenceNumber;
	}

	public float getxAtomCoordinate() {
		return xAtomCoordinate;
	}

	public float getyAtomCoordinate() {
		return yAtomCoordinate;
	}

	public float getzAtomCoordinate() {
		return zAtomCoordinate;
	}

	@Override
	public String toString() {
		return "Atom [atomNumber=" + atomNumber + ", atomName=" + atomName + ", "
				+ (alternateLocIndicator != null ? "alternateLocIndicator=" + alternateLocIndicator + ", " : "")
				+ (residueName != null ? "residueName=" + residueName + ", " : "") + "chainNameIdentifier="
				+ chainNameIdentifier + ", chainNumberIdentifier=" + chainNumberIdentifier + ", residueSequenceNumber="
				+ residueSequenceNumber + ", xAtomCoordinate=" + xAtomCoordinate + ", yAtomCoordinate="
				+ yAtomCoordinate + ", zAtomCoordinate=" + zAtomCoordinate + "]";
	}



}
