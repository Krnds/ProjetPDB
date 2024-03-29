package fr.karinedias.query;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;

public class FetchStructure {

	/**
	 * PROJET-PDB
	 * ETAPE-1 : Isoler toutes les structures de la PDB correspondant à des complexes moléculaires
	 * Cette classe permet de télécharger automatiquement un fichier de structure d'une protéine de la PDB à partir de son identifiant (ID)
	 * TODO:
	 * - pour l'appel de la méthode getRandomID dans le constructeur, utiliser plutôt le Singleton DP ?
	 * - dans ces cas, il faut gérer les exceptions et recommencer les processus
	 * - quand j'ai la liste de tous les complexes moléculaires, il faut que je trouve un moyen d'en choisir un (random ?)
	 * - pouvoir choisir 2 RESIDUS de ce complexe choisi pour calculer les distances...
	 */

	private static final String URL = "https://files.rcsb.org/view/";
	private static String STRUCTUREID;
	private String givenStructureID = null;

	public FetchStructure() {
		try {
		STRUCTUREID = Query.getRandomID(); //use singleton design pattern to only create a single object Query once and for all ?
		} catch (IOException exception) {
			exception.printStackTrace();
		}
	}
	
	//Specify structure ID when calling the constructor :
	public FetchStructure(String id) {
		givenStructureID = id;
	}

	public void getPDBFile() {

		URL url;

		try {

			String finalUrl = URL + STRUCTUREID + ".cif"; //or use getUrl ??
			System.out.println(finalUrl);
			url = new java.net.URL(finalUrl);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			FileWriter pdbPath = new FileWriter(getPDBPathDirectory() + STRUCTUREID + ".cif");
			BufferedWriter writer = new BufferedWriter(pdbPath);

			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}

			reader.close();
			writer.close();
			System.out.println("Successfully Downloaded !");

		//TODO: rewrite exception handling
		} catch (java.util.InputMismatchException | UnsupportedEncodingException e) {
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	/**
	 * Download wanted .cif file with the ID given in argument
	 * @return
	 */
	
	public void getPDBFileWithID(String id) {

		URL url;

		try {

			String finalUrl = URL + id.toLowerCase() + ".cif"; //or use getUrl ??
			System.out.println(finalUrl);
			url = new java.net.URL(finalUrl);

			BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
			FileWriter pdbPath = new FileWriter(getPDBPathDirectory() + id.toLowerCase() + ".cif");
			BufferedWriter writer = new BufferedWriter(pdbPath);

			String line;
			while ((line = reader.readLine()) != null) {
				writer.write(line);
				writer.newLine();
			}

			reader.close();
			writer.close();
			System.out.println("Successfully Downloaded !");

		//TODO: rewrite exception handling
		} catch (java.util.InputMismatchException | UnsupportedEncodingException e) {
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	
	/*
	 * Method for finding the path of the doc directory
	 */

	public String getPDBPathDirectory() {

		return System.getProperty("user.dir") + File.separator + "doc" + File.separator;
	}
	
	public String getPath() {
		
		return System.getProperty("user.dir") + File.separator + "doc" + File.separator + getSTRUCTUREID() + ".cif";
	}


	protected static String getSTRUCTUREID() {
		return STRUCTUREID;
	}

	public String getGivenStructureID() {
		return givenStructureID;
	}

	public void setGivenStructureID(String givenStructureID) {
		this.givenStructureID = givenStructureID;
	}
	
}
