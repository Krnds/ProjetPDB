package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PDBAtomParser extends PDBAtomRetriver {

	/*
	 * TODO:
	 * create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName
	 */
	
	public PDBAtomParser() {
		super();
	}
	
	public static void coordinatesParser() {
		
		//look for coordinates :
//		Pattern coordinates = Pattern.compile("-?\\d{1-3}.\\d{3}");
//		Matcher matcher = coordinates.matcher(infosAtomes[k].toString()); //?
//		while (matcher.find()) {
//			System.out.println(matcher.toString());
//		}
	}
	
	/**
	 * Static method bc can be called without creating a PDBAtomParser object
	 * @param atomString
	 * 
	 */
		public static void atomTokenizer(String atomString) {

			String allAtomEnrtiesDelimiter = "\n";
			StringTokenizer allAtomEntries = new StringTokenizer(atomString, allAtomEnrtiesDelimiter);
			int n = allAtomEntries.countTokens();
			String[] listOfAtoms = new String[n];
			while (allAtomEntries.hasMoreElements()) {

			
				for (int i = 0; i < n; i++) {
					listOfAtoms[i] = allAtomEntries.nextToken();
					System.out.print(listOfAtoms[i].toString() + "\n");
				}

				// par exmple entrée n° 5 de l'atome 1602 :
				System.out.print("Entrée n° 5 \n" + listOfAtoms[5].toString() + "\n");

				for (int j = 0; j < n; j++) {
					
					String delimiterAtom = " ";
					StringTokenizer oneEntry = new StringTokenizer(listOfAtoms[j], delimiterAtom);
					int dataTokens = oneEntry.countTokens();
					String[] dataOfAtom = new String[n];
					
					for (int k = 0; k < dataTokens; k++) {
						dataOfAtom[k] = oneEntry.nextToken().trim();
						
						System.out.println("entrée n° " + j + " " + dataOfAtom[k].toString());
					}
					//return dataOfAtom;
					System.out.println("Coordonnées x de l'atome " + j + " = " + dataOfAtom[10].toString());
				}
				
			}
			

		}
		
		//FOR TESTING PURPOSES :
		public static void main(String[] args) {
		
			String filename = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
			PDBAtomRetriver myPDBFfile = new PDBAtomRetriver(filename);
			try {
				String myPDBfileAtoms = myPDBFfile.getAtoms();
				//String[] atoms3bw7 = atomTokenizer(myPDBfileAtoms);
				atomTokenizer(myPDBfileAtoms);
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

}
