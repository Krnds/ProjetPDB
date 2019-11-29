package main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PDBAtomRetriever {

	/*
	 * Class that returns all 'ATOM' entries (type String) from a .cif/.pdb file
	 * into a String object
	 */

	private String pdbFileContent = null;

	public PDBAtomRetriever(String pdbFileContent) {
		this.pdbFileContent = pdbFileContent;
	}

	public PDBAtomRetriever() {
		// constructor without parameters calling subclass PDBAtomParser
	}

	public String getPdbFile() {
		return pdbFileContent;
	}

	public void setPdbFile(String pdbFile) {
		this.pdbFileContent = pdbFile;
	}

	public StringBuffer getAtoms() throws IOException {

		Reader reader = new FileReader(pdbFileContent);
		BufferedReader bufferedReader = new BufferedReader(reader);
		String line;
		String startWithATOM = "";
		StringBuffer startWithATOM2 = new StringBuffer(100000); // 100.000 is pure assumption

		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("ATOM")) {
				// startWithATOM += line + "\n";
				startWithATOM2.append(line).append("\n"); // testing
			}
		}
		bufferedReader.close();
		return startWithATOM2;

	}

	public String getAtoms2() throws IOException {

		Reader reader = new FileReader(pdbFileContent);
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

}
