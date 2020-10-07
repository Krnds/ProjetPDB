package fr.karinedias.model;

public class Residue {

	private int atomNumber;
	private String residueName;
	private int residueNumber;
	private int altResidueNumber;
	private char altChain;
	private int chainNumber;
	private double xCoord, yCoord, zCoord;

	/*
	 * Constructor of the ResidueWithCoordinates object
	 */
	public Residue(int atomNumber, String residueName, int residueNumber, int altResidueNumber, char altChain,
			int chainNumber, double xCoord, double yCoord, double zCoord) {

		this.atomNumber = atomNumber;
		this.residueName = residueName;
		this.residueNumber = residueNumber;
		this.altResidueNumber = altResidueNumber;
		this.altChain = altChain;
		this.chainNumber = chainNumber;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}

	// Empty constructor
	public Residue() {

	}

	/**
	 * @return the atomNumber
	 */
	public int getAtomNumber() {
		return atomNumber;
	}

	/**
	 * @param atomNumber the atomNumber to set
	 */
	public void setAtomNumber(int atomNumber) {
		this.atomNumber = atomNumber;
	}

	/**
	 * @return the residueName
	 */
	public String getResidueName() {
		return residueName;
	}

	/**
	 * @param residueName the residueName to set
	 */
	public void setResidueName(String residueName) {
		this.residueName = residueName;
	}

	/**
	 * @return the residueNumber
	 */
	public int getResidueNumber() {
		return residueNumber;
	}

	/**
	 * @param residueNumber the residueNumber to set
	 */
	public void setResidueNumber(int residueNumber) {
		this.residueNumber = residueNumber;
	}

	/**
	 * @return the altChain
	 */
	public char getAltChain() {
		return altChain;
	}

	/**
	 * @param altChain the altChain to set
	 */
	public void setAltChain(char altChain) {
		this.altChain = altChain;
	}

	/**
	 * @return the chainNumber
	 */
	public int getChainNumber() {
		return chainNumber;
	}

	/**
	 * @param chainNumber the chainNumber to set
	 */
	public void setChainNumber(int chainNumber) {
		this.chainNumber = chainNumber;
	}

	/**
	 * @return the xCoord
	 */
	public double getxCoord() {
		return xCoord;
	}

	/**
	 * @param xCoord the xCoord to set
	 */
	public void setxCoord(double xCoord) {
		this.xCoord = xCoord;
	}

	/**
	 * @return the yCoord
	 */
	public double getyCoord() {
		return yCoord;
	}

	/**
	 * @param yCoord the yCoord to set
	 */
	public void setyCoord(double yCoord) {
		this.yCoord = yCoord;
	}

	/**
	 * @return the zCoord
	 */
	public double getzCoord() {
		return zCoord;
	}

	/**
	 * @param zCoord the zCoord to set
	 */
	public void setzCoord(double zCoord) {
		this.zCoord = zCoord;
	}

	@Override
	public String toString() {
		return "Residue [residueName=" + residueName + ", residueNumber=" + residueNumber + ", altResidueNumber="
				+ altResidueNumber + ", altChain=" + altChain + ", chainNumber=" + chainNumber + ", xCoord=" + xCoord
				+ ", yCoord=" + yCoord + ", zCoord=" + zCoord + "]";
	}

	@Override
	public boolean equals(Object o) {

		if (o == this)
			return true;
		if (!(o instanceof Residue)) {
			return false;
		}

		Residue residue = (Residue) o;

		return residue.getAtomNumber()==atomNumber && residue.getResidueName().equals(residueName) && residue.getChainNumber() == chainNumber
				&& residue.getAltChain() == altChain && residue.getChainNumber() == chainNumber
				&& residue.getxCoord() == xCoord && residue.getyCoord() == yCoord && residue.getzCoord() == zCoord;
	}

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + residueName.hashCode();
		result = 31 * result + residueNumber;
		result = 31 * result + altChain;
		result = 31 * result + chainNumber;

		result = 31 * result + hashDouble(xCoord);
		result = 31 * result + hashDouble(yCoord);
		result = 31 * result + hashDouble(zCoord);
		return result;
	}

	// Source : effective Java
	/**
	 * To transform a double into int for hashCode calculation
	 * 
	 * @param value (double)
	 * @return an int
	 */
	private int hashDouble(double val) {
		long longBits = Double.doubleToLongBits(val);
		return (int) (longBits ^ (longBits >>> 32));
	}

	public int getAltResidueNumber() {
		return altResidueNumber;
	}

	public void setAltResidueNumber(int altResidueNumber) {
		this.altResidueNumber = altResidueNumber;
	}

}
