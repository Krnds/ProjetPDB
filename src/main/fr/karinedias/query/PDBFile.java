package main.fr.karinedias.query;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PDBFile {

	/*
	 * Class for accessing pdb file and download them as a .cif file
	 */

	/*
	 * Fields
	 */
//Setters and getters ??
	private String url = "https://files.rcsb.org/view/";
	private String id;

	public PDBFile() {
		// TODO Auto-generated constructor stub
	}

	// TODO: control of the ID with regex ?
	public String getID() {

		Scanner sc = new Scanner(System.in);
		System.out.println("Enter the ID of the structure to download :\n");
		String id = sc.nextLine();
//		id = this.id; // correct ??
		sc.close();
		return id;

	}

	public void getPDBFile(String id) {

		URL url;

		try {

			String finalUrl = this.url + id + ".cif"; //or use getUrl ??
			url = new java.net.URL(finalUrl);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			FileWriter pdbPath = new FileWriter(getPathOfPDBFiles() + id.toLowerCase() + ".cif");
			BufferedWriter writer = new BufferedWriter(pdbPath);

			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}

			reader.close();
			writer.close();
			System.out.println("Successfully Downloaded !");

		} catch (java.util.InputMismatchException | UnsupportedEncodingException e) {
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// FOR TESTING PURPOSES
	public static void main(String[] args) {

		
		PDBFile test = new PDBFile();
		//ask for id
		String id = test.getID();
		test.getPDBFile(id);
		
	}

	/*
	 * Method for finding the path of the doc directory
	 */

	public static String getPathOfPDBFiles() {

		return System.getProperty("user.dir") + File.separator + "doc" + File.separator;
	}
}
