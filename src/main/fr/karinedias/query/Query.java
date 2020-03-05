package main.fr.karinedias.query;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Query {
	/**
	 * PROJET-PDB
	 * ETAPE-1 : Isoler toutes les structures de la PDB correspondant à des complexes moléculaires
	 * Cette classe permet d'effectuer une requête XML via l'interface PDB RESTful Web Service
	 * et de retourner un complexe au hasard.
	 */

	private static final String SERVICELOCATION = "https://www.rcsb.org/pdb/rest/search";
	private static final int NUMBEROFENTITIES = 2;
	private static final String XML = "<orgPdbQuery><queryType>org.pdb.query.simple.NumberOfEntitiesQuery</queryType><description>Molecular Complexes Search : Entity Type=Protein Min Number of Entities=2 Max Number of Entities=" 
	+ NUMBEROFENTITIES+ 
	"</description><entity.type.>p</entity.type.><struct_asym.numEntities.min>"
	+ NUMBEROFENTITIES + 
	"</struct_asym.numEntities.min><struct_asym.numEntities.max>"
	+ NUMBEROFENTITIES +
	"</struct_asym.numEntities.max></orgPdbQuery>";

	public static void main(String[] args) throws IOException {

		Query molecularComplexes = new Query();
		//list of molecular complexes taken from rcsb website : 
		molecularComplexes.postQuery(XML);	
		
		  List<String> pdbIds = new ArrayList<String>();
		  pdbIds.addAll(molecularComplexes.postQuery(XML));
		 
//		  for (String string : pdbIds) { 
//		  System.out.println(string);
//		  }
		  
		  String random = getRandomID((ArrayList<String>) pdbIds); 

		 System.out.println(random);


	}
	
	

	/**
	 * post am XML query (PDB XML query format) to the RESTful RCSB web service
	 * 
	 * @param xmlQuery
	 * @return a list of PDB ids.
	 */
	public List<String> postQuery(String xmlQuery) throws IOException {

		URL webService = new URL(SERVICELOCATION);

		String encodedXML = URLEncoder.encode(xmlQuery, "UTF-8");

		InputStream in = doPOST(webService, encodedXML);

		List<String> pdbIds = new ArrayList<String>();

		BufferedReader rd = new BufferedReader(new InputStreamReader(in));

		String line;
		while ((line = rd.readLine()) != null) {

			pdbIds.add(line);

		}
		rd.close();

		return pdbIds;

	}
	
	/**
	 * method for fetching a random ID from the list of the complexes
	 */
	
	private static String getRandomID(ArrayList<String> listOfID) {
		
		Random random = new Random();
		String randomID = listOfID.get(random.nextInt(listOfID.size()));
		return randomID;
		
	}

	/**
	 * do a POST to a URL and return the response stream for further processing
	 * elsewhere.
	 * 
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream doPOST(URL url, String data) throws IOException {

		// Send data
		URLConnection connection = url.openConnection();

		connection.setDoOutput(true);

		OutputStreamWriter wr = new OutputStreamWriter(connection.getOutputStream());

		wr.write(data);
		wr.flush();

		// Get the response
		return connection.getInputStream();

	}

}