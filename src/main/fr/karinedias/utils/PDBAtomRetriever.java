package src.main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class PDBAtomRetriever {

	/*
	 * Class that returns all 'ATOM' entries (type String) from a .cif/.pdb file
	 * into a StringBuffer (getAtoms2) or String (getAtoms) object
	 */

	private String pdbFileContent;

	public PDBAtomRetriever(String pdbFileContent) {
		this.pdbFileContent = pdbFileContent;
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
		StringBuffer startWithATOM = new StringBuffer();

		while ((line = bufferedReader.readLine()) != null) {
			if (line.contains("ATOM")) {
				startWithATOM.append(line).append("\n");
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
