package main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class AtomToken {

	/*
	 * Class for splitting tokens from the atoms entries of a pdb file
	 */

	public static void atomTokenizer() {

		/*
		 * Taken from "Aminoacid class"
		 */
		String atomsToParse = "ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  \n"
				+ "ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  \n"
				+ "ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  \n"
				+ "ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  \n"
				+ "ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  \n"
				+ "ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  \n"
				+ "ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  \n"
				+ "ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  \n"
				+ "ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  \n"
				+ "ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  \n"
				+ "ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C";
		// phe71.atomsOfAminoAcid.add("PHE", 'F');
		String delimiter = " "; // à voir
		StringTokenizer stringTokenizer = new StringTokenizer(atomsToParse, delimiter);

		/*
		 * TODO: separate tokens within 11 categories
		 */

		// count the number of atoms ie of lines :
		int nAtoms = countLines(atomsToParse);
		// initialize the String array String[]
		String[] atomEntries = new String[nAtoms];

		//
		// while (stringTokenizer.hasMoreElements()) {
		ArrayList<String[]> tokens = new ArrayList<String[]>(); // TODO: à voir si la datastructure correspond au pb
		// TODO: utiliser l'interface List dans l'initilisation de la var tokens plutot
		// qu'une ArrayList
		for (int i = 0; i < 11; i++) { // first loop for atomEntries
			for (int j = 0; j < atomEntries.length; j++) { // second loop for each info of an atomEntry (11 columns)
				atomEntries[j] = stringTokenizer.nextToken(); // pas sûr...
			}
			tokens.add(i, atomEntries);
			// tokens.add(i, (String) stringTokenizer.nextElement());
		}

		/*
		 * pour afficher mon arraylist d'array... convertir cet objet en arraylist et
		 * afficher cahque entrée de String
		 */

		/*
		 * String[] affichage = new String[nAtoms]; affichage =
		 * tokens.toArray(affichage); int nAffichage = affichage.length;
		 * 
		 * // affichage ? for (int i = 0; i < nAffichage; i++) {
		 * System.out.println(tokens[i]); }
		 */

		/*
		 * for (int i = 0; i < tokens.size(); i++) { for (int j = 0; j < nAtoms; j++) {
		 * System.out.println(tokens.get(i)[atomEntries[j]); } }
		 */

//		for (int i = 0; i < tokens.size(); i++) {
//			for (int j = 0; j < atomEntries.length; j++) {
//				
//				System.out.print(tokens.get(i)[j] + " ");
//			}
//			System.out.println();
//			}
		
		while (stringTokenizer.hasMoreElements()) {
			for (int i = 0; i < nAtoms; i++) {
				System.out.println(stringTokenizer.nextToken());
			}
		}
	}

	// }

	// helper function
	public static int countLines(String input) {

		String[] lines = input.split("\n");
		return lines.length;
	}
	
	public static void test() {

		String atomsToParse = "ATOM   1597  N   PHE B  71      -3.488 -10.470   2.444  1.00 10.48           N  \n"
				+ "ATOM   1598  CA  PHE B  71      -3.362 -10.263   3.894  1.00 13.31           C  \n"
				+ "ATOM   1599  C   PHE B  71      -4.488 -10.953   4.656  1.00 13.47           C  \n"
				+ "ATOM   1600  O   PHE B  71      -4.262 -11.672   5.629  1.00 13.74           O  \n"
				+ "ATOM   1601  CB  PHE B  71      -3.466  -8.734   4.164  1.00 10.18           C  \n"
				+ "ATOM   1602  CG  PHE B  71      -3.307  -8.346   5.625  1.00 11.62           C  \n"
				+ "ATOM   1603  CD1 PHE B  71      -4.382  -8.291   6.479  1.00 14.43           C  \n"
				+ "ATOM   1604  CD2 PHE B  71      -2.042  -8.039   6.090  1.00 16.17           C  \n"
				+ "ATOM   1605  CE1 PHE B  71      -4.224  -7.920   7.820  1.00 13.51           C  \n"
				+ "ATOM   1606  CE2 PHE B  71      -1.861  -7.677   7.422  1.00 16.61           C  \n"
				+ "ATOM   1607  CZ  PHE B  71      -2.948  -7.614   8.272  1.00 13.82           C";
		String delimiter = "\n";
		StringTokenizer allEntries = new StringTokenizer(atomsToParse, delimiter);

		while (allEntries.hasMoreElements()) {
			int n = allEntries.countTokens();
			String[] listeAtomes = new String[n];

			for (int i = 0; i < n; i++) {
				listeAtomes[i] = allEntries.nextToken();
				//System.out.print(listeAtomes[i].toString() + "\n");
			}

			// par exmple entrée n° 5 de l'atome 1602 :
			System.out.print("Entrée n° 5 \n" + listeAtomes[5].toString() + "\n");

			for (int j = 0; j < 11; j++) {
				String[] infosAtomes = new String[11];
				String delimiterAtom = " ";
				StringTokenizer oneEntry = new StringTokenizer(listeAtomes[j], delimiterAtom);
				for (int k = 0; k < 11; k++) {
					infosAtomes[k] = oneEntry.nextToken().trim();
					 //System.out.println("entrée n° " + j + " " + infosAtomes[k].toString());
				}
				 System.out.println("Coordonnées x de l'atome " + j + " = " + infosAtomes[6].toString());
			}

		}
	}

	public static void main(String[] args) {

		test();
	}
}
