package fr.karinedias.model;

public class Chain {

	private int moleculeNumber;
	private Character chain;

	public Chain(char chain, int moleculeNumber) {
		this.chain = chain;
		this.moleculeNumber = moleculeNumber;
	}

	/**
	 * @return the moleculeNumber
	 */
	public int getMoleculeNumber() {
		return moleculeNumber;
	}

	/**
	 * @param moleculeNumber the moleculeNumber to set
	 */
	public void setMoleculeNumber(int moleculeNumber) {
		this.moleculeNumber = moleculeNumber;
	}

	/**
	 * @return the chain
	 */
	public Character getChain() {
		return chain;
	}

	/**
	 * @param chain the chain to set
	 */
	public void setChain(Character chain) {
		this.chain = chain;
	}

	@Override
	public String toString() {
		return "Chain [" + chain + "] from molecule n°" + moleculeNumber;
	}

}
