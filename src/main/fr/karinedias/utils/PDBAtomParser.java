package main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

public class PDBAtomParser {
	
	/*
	 * Class for parsing all 'ATOM' entries of a .cif/.pdb file and all their corresponding info
	 * TODO: create appropriate object for parsing the file ie instances variables and non-static methods
	 */
	
	

	public static String rawStringOfAtoms(String pdbFile) throws IOException {

		Reader reader = new FileReader(pdbFile);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		String startWithATOM = "";

		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("ATOM")) {
				startWithATOM += line + "\n";
			}
		}

		return startWithATOM;

	}
	
	

	public static String rawStringOfAtoms2(String pdbFile) throws IOException {

		Reader reader = new FileReader(pdbFile);
		BufferedReader br = new BufferedReader(reader);
		String startWithATOM = "";

		for (String line = br.readLine(); line != null; line = br.readLine()) {
			if (line.startsWith("ATOM")) {
				startWithATOM += line + "\n";

			}

		}
		return startWithATOM;

	}

	//TODO: to be completed
	public void atomTokenizer(String atomString) {
		
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
		
		String testFile = "/Users/dias/eclipse-workspace/ProjetPDB/doc/3c0p.cif";
		/*
		 * Beware, Eclipse limits the output by length, change the setting to see all the output !
		 */
		try {
			String atoms = rawStringOfAtoms(testFile);
			System.out.println("First test : \n");
			System.out.println(atoms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			String atoms = rawStringOfAtoms2(testFile);
			System.out.println("Second test : \n");
			System.out.println(atoms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
	}
	
	
	/*create methods for :
	 * - atomSerialNumber
	 * - alternateLocationIndicator
	 * - coordinateX
	 * - coordinateY
	 * - coordinateZ
	 * - residueName
	 * - residueSeqName
	 */

}
