package main.fr.karinedias.manager;

import main.fr.karinedias.model.Atom;

public class AtomicDistanceCalculation extends Atom {

public AtomicDistanceCalculation() {
	super();
	
}

	public AtomicDistanceCalculation(String recordName, int serialNumber, char atomName, String alternateLocIndicator,
		String residueName, int chainIdentifier, int residueSequenceNumber) {
	super(recordName, serialNumber, atomName, alternateLocIndicator, residueName, chainIdentifier, residueSequenceNumber);
	// TODO Auto-generated constructor stub
}



	public Double distanceBetweenAtoms(Atom a, Atom b) {
		
		double xCoord1 =  a.getOrthogonalCoordinates().get(0);
		double yCoord1 = a.getOrthogonalCoordinates().get(1);
		double zCoord1 = a.getOrthogonalCoordinates().get(2);
		
		double xCoord2 = b.getOrthogonalCoordinates().get(0);
		double yCoord2 = b.getOrthogonalCoordinates().get(1);
		double zCoord2 = b.getOrthogonalCoordinates().get(2);
		 
		double xDistanceBetweenAtoms = Math.abs(Math.pow((xCoord1 - xCoord2), 2));
		double yDistanceBetweenAtoms  = Math.abs(Math.pow((yCoord1 - yCoord2), 2));
		double zDistanceBetweenAtoms  = Math.abs(Math.pow((zCoord1 - zCoord2), 2));
		
		double distance = Math.sqrt(xDistanceBetweenAtoms + yDistanceBetweenAtoms + zDistanceBetweenAtoms);
		return distance;
		
	}
	
	public static void main(String[] args) {
		
		//example with :
		//ATOM   651  N  N   . ALA A 1 88  ? 44.337 34.325 22.013  1.00 22.89 ? 88  ALA A N   1
		//ATOM   2711 N  N   . VAL C 1 70  ? 42.988 6.129  20.144  1.00 15.78 ? 70  VAL C N   1
		Atom test1 = new Atom("ATOM", 651, 'N', "N", "ALA", 1, 88);
		Atom test2 = new Atom("ATOM", 2711, 'N', "N", "VAL", 1, 70);
		
		//another example with :
		//ATOM   35   C  CA  . ASP A 1 6   ? 41.279 27.281 -4.320  1.00 16.17 ? 6   ASP A CA  1 
		//ATOM   4363 C  CA  . TYR D 2 145 ? 19.829 40.870 22.615  1.00 14.21 ? 145 TYR D CA  1
		Atom Asp35 = new Atom("ATOM", 35, 'C', "CA", "ASP", 1, 6);
		Atom Tyr4363 = new Atom("ATOM", 4363, 'C', "CA", "TYR", 2, 145);
		
		test1.atomCoordinates(44.337, 34.325, 22.013);
		test2.atomCoordinates(42.988, 6.129, 20.144);
		Asp35.atomCoordinates(41.279, 27.281, -4.320);
		Tyr4363.atomCoordinates(19.829, 40.870, 22.615);
		
		//double distanceTest = ;
		
		AtomicDistanceCalculation testCalcul = new AtomicDistanceCalculation();
		System.out.println("Distance between " + Asp35.toString() + " and " + Tyr4363.toString() );
		System.out.println(testCalcul.distanceBetweenAtoms(test1, test2));
		System.out.println(testCalcul.distanceBetweenAtoms(Asp35, Tyr4363));
	}
}
