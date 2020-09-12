package fr.karinedias.model;

public class Residue {

	
	private String residueName = "";
	private int residueNumber = -1;
	private char chain = '\u0000';
	private int chainNumber = -1;
	private double xCoord, yCoord, zCoord;

	/*
	 * Constructor of the ResidueWithCoordinates object
	 */
	public Residue(String residueName, int residueNumber, char chain, int chainNumber, double xCoord, double yCoord, double zCoord) {
		
		this.residueName = residueName;
		this.residueNumber = residueNumber;
		this.chain = chain;
		this.chainNumber = chainNumber;
		this.xCoord = xCoord;
		this.yCoord = yCoord;
		this.zCoord = zCoord;
	}
	
	// Empty constructor 
	public Residue() {
		
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
	 * @return the chain
	 */
	public char getChain() {
		return chain;
	}

	/**
	 * @param chain the chain to set
	 */
	public void setChain(char chain) {
		this.chain = chain;
	}

	/**
	 * @param residueNumber the residueNumber to set
	 */
	public void setResidueNumber(int residueNumber) {
		this.residueNumber = residueNumber;
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
	

	public void setChainNumber(int chainNumber) {
		this.chainNumber = chainNumber;
	}

	public int getChainNumber() {
		return chainNumber;
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
		return "Residue [residueName=" + residueName + ", residueNumber=" + residueNumber + ", chain=" + chain
				+ ", chainNumber=" + chainNumber + ", xCoord=" + xCoord + ", yCoord=" + yCoord + ", zCoord=" + zCoord
				+ "]";
	}





}
