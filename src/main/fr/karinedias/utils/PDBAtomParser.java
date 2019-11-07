package main.fr.karinedias.utils;

import java.util.StringTokenizer;

public class PDBAtomParser {

	/*
	 * TODO:
	 * create methods for : - atomSerialNumber - alternateLocationIndicator -
	 * coordinateX - coordinateY - coordinateZ - residueName - residueSeqName
	 */
	
	// TODO: to be completed
		public void atomTokenizer(String atomString) {

			String delimiter = "\n";
			StringTokenizer allEntries = new StringTokenizer(atomString, delimiter);

			while (allEntries.hasMoreElements()) {
				int n = allEntries.countTokens();
				String[] listeAtomes = new String[n];

				for (int i = 0; i < n; i++) {
					listeAtomes[i] = allEntries.nextToken();
					// System.out.print(listeAtomes[i].toString() + "\n");
				}

				// par exmple entrée n° 5 de l'atome 1602 :
				System.out.print("Entrée n° 5 \n" + listeAtomes[5].toString() + "\n");

				for (int j = 0; j < 11; j++) {
					String[] infosAtomes = new String[11];
					String delimiterAtom = " ";
					StringTokenizer oneEntry = new StringTokenizer(listeAtomes[j], delimiterAtom);
					for (int k = 0; k < 11; k++) {
						infosAtomes[k] = oneEntry.nextToken().trim();
						// System.out.println("entrée n° " + j + " " + infosAtomes[k].toString());
					}
					System.out.println("Coordonnées x de l'atome " + j + " = " + infosAtomes[6].toString());
				}

			}

		}

}
