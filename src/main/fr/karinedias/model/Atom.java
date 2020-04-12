package src.main.fr.karinedias.model;

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

	protected int getAtomNumber() {
		return atomNumber;
	}

	protected char getAtomName() {
		return atomName;
	}

	protected String getAlternateLocIndicator() {
		return alternateLocIndicator;
	}

	protected String getResidueName() {
		return residueName;
	}

	protected char getChainNameIdentifier() {
		return chainNameIdentifier;
	}

	protected int getChainNumberIdentifier() {
		return chainNumberIdentifier;
	}

	protected int getResidueSequenceNumber() {
		return residueSequenceNumber;
	}

	protected float getxAtomCoordinate() {
		return xAtomCoordinate;
	}

	protected float getyAtomCoordinate() {
		return yAtomCoordinate;
	}

	protected float getzAtomCoordinate() {
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
