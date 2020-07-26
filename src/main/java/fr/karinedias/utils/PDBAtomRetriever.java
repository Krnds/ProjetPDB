package fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.concurrent.TimeUnit;

public class PDBAtomRetriever {

	/**
	 * From a StringBuilder that regroups all the content of a cif file,
	 * PDBAtomRetriver search all ATOM entries and put them into Atom object form
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

	//EXECUTION TIME > getAtoms... delete ?
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

	// FOR TESTING PURPOSES :
	public static void main(String[] args) {

		String filenameDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		String filenameMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/2b5i.cif";
		String filenameWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

		PDBAtomRetriever testFile = new PDBAtomRetriever(filenameDebian);

		
		try {
			// measure execution time of first method getAtoms() : 
			long startTime = System.nanoTime();
			StringBuffer listOfAtoms1 = testFile.getAtoms();
			long endTime = System.nanoTime();
			long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(endTime - startTime);
			
			
			// measure execution time of second method getAtoms() : 
			long startTime2 = System.nanoTime();
			String listOfAtoms2 = testFile.getAtoms2();
			long endTime2 = System.nanoTime();
			long durationInSeconds2 = TimeUnit.NANOSECONDS.toSeconds(endTime2 - startTime2);


			
			
			//print all atoms : 
			System.out.println(listOfAtoms1.toString());
			System.out.println("\n\n");
			System.out.println(listOfAtoms2);
			System.out.println("Execution time of method getAtoms = " + durationInSeconds);
			System.out.println("Execution time of method getAtoms2 = " + durationInSeconds2);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
