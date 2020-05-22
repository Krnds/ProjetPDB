package src.main.fr.karinedias.model;

public class Complex {

	private String entryID = "";
	private Molecule molecules;
	
	
	public Complex(String entryID, Molecule molecules) {
		this.entryID = entryID;
		this.molecules = molecules;
	}


	protected Molecule getMolecules() {
		return molecules;
	}


	protected void setMolecules(Molecule molecules) {
		this.molecules = molecules;
	}


	protected String getEntryID() {
		return entryID;
	}
	
	public static void main(String[] args) {
		
	}

	
}
