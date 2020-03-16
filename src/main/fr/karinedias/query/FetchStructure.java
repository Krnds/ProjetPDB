package src.main.fr.karinedias.query;

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
	 * - ne fonctionne pas si la structure est invalide
	 * - dans ces cas, il faut gérer les exceptions et recommencer les processus
	 * - quand j'ai la liste de tous les complexes moléculaires, il faut que je trouve un moyen d'en choisir un (random ?)
	 * et d'ensuite choisir 2 RESIDUS de ce complexe choisi pour calculer les distances...
	 */

	private static final String URL = "https://files.rcsb.org/view/";
	private static String STRUCTUREID;

	public FetchStructure() throws IOException {
		STRUCTUREID = Query.getRandomID(); //use singleton design pattern ?
	}


	public void getPDBFile(String id) {

		URL url;

		try {

			String finalUrl = URL + id + ".cif"; //or use getUrl ??
			System.out.println(finalUrl);
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
	public static void main(String[] args) throws IOException {

		
		FetchStructure test = new FetchStructure();
		//ask for id
		String id = FetchStructure.STRUCTUREID;
		test.getPDBFile(id);
		String pathOfCif = FetchStructure.getPathStructure();
		System.out.println(pathOfCif);
		
	}

	
	/*
	 * Method for finding the path of the doc directory
	 */

	public String getPathOfPDBFiles() {

		return System.getProperty("user.dir") + File.separator + "doc" + File.separator;
	}
	
	public static String getPathStructure() {
		
		return System.getProperty("user.dir") + File.separator + "doc" + File.separator + getSTRUCTUREID() + ".cif";
	}


	protected static String getSTRUCTUREID() {
		return STRUCTUREID;
	}
	
}
