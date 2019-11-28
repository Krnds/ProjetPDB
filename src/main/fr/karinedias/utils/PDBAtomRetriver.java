package main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.StringTokenizer;

public class PDBAtomRetriver {

	/*
	 * Class that returns all 'ATOM' entries (type String) from a .cif/.pdb file into a String object
	 */

	private String pdbFileContent = null;
	
	
	public PDBAtomRetriver(String pdbFileContent) {
		this.pdbFileContent = pdbFileContent;
	}
	
	public PDBAtomRetriver() {
		//constructor without parameters calling subclass PDBAtomParser
	}

	public String getPdbFile() {
		return pdbFileContent;
	}

	public void setPdbFile(String pdbFile) {
		this.pdbFileContent = pdbFile;
	}

	public String getAtoms() throws IOException {

		Reader reader = new FileReader(pdbFileContent);
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
