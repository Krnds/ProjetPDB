package main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

public class PDBAtomRetriver {

	/*
	 * Class that returns all 'ATOM' entries (type String) from a .cif/.pdb file
	 */

	private String pdbFile = null;

	public PDBAtomRetriver(String pdbFile) {
		this.pdbFile = pdbFile;
	}

	protected String getPdbFile() {
		return pdbFile;
	}

	protected void setPdbFile(String pdbFile) {
		this.pdbFile = pdbFile;
	}

	public String getAtoms() throws IOException {

		Reader reader = new FileReader(pdbFile);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		String startWithATOM = "";

		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("ATOM")) {
				startWithATOM += line + "\n";
			}
		}
		bufferedReader.close();
		return startWithATOM;

	}

	public String getAtoms2() throws IOException {

		Reader reader = new FileReader(pdbFile);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String startWithATOM = "";

		for (String line = bufferedReader.readLine(); line != null; line = bufferedReader.readLine()) {
			if (line.startsWith("ATOM")) {
				startWithATOM += line + "\n";

			}

		}
		bufferedReader.close();
		return startWithATOM;

	}


	public static void main(String[] args) {

		PDBAtomRetriver testFile = new PDBAtomRetriver("/Users/dias/eclipse-workspace/ProjetPDB/doc/3c0p.cif");

		/*
		 * Beware, Eclipse limits the output by length, change the setting to see all
		 * the output !
		 */
		try {
			String atoms = testFile.getAtoms();
			System.out.println("First test : \n");
			System.out.println(atoms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			String atoms = testFile.getAtoms2();
			System.out.println("Second test : \n");
			System.out.println(atoms);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			e.getMessage();
		}
	}


}
