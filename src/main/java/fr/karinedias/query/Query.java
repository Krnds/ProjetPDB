package fr.karinedias.query;

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

	private static final String SERVICELOCATION = "https://www.rcsb.org/pdb/rest/search";
	private static final int NUMBEROFENTITIES = 2;
	private static final String XML = "<orgPdbQuery><queryType>org.pdb.query.simple.NumberOfEntitiesQuery</queryType><description>Molecular Complexes Search : Entity Type=Protein Min Number of Entities=2 Max Number of Entities="
			+ NUMBEROFENTITIES + "</description><entity.type.>p</entity.type.><struct_asym.numEntities.min>"
			+ NUMBEROFENTITIES + "</struct_asym.numEntities.min><struct_asym.numEntities.max>" + NUMBEROFENTITIES
			+ "</struct_asym.numEntities.max></orgPdbQuery>";
	private static List<String> molecularComplexes = new ArrayList<String>();

	/**
	 * post a specific query with the XML value for fetching all entities of
	 * molecular complexes
	 * 
	 * @param xmlQuery
	 * @return a list of PDB ids.
	 */
	private static void postQuery() throws IOException {

		URL webService = new URL(SERVICELOCATION);

		String encodedXML = URLEncoder.encode(XML, "UTF-8");

		InputStream in = doPOST(webService, encodedXML);

		BufferedReader rd = new BufferedReader(new InputStreamReader(in));

		String line;
		while ((line = rd.readLine()) != null) {
			molecularComplexes.add(line);

		}
		rd.close();

	}

	/**
	 * method for fetching a random ID from the list of the complexes
	 * 
	 * @throws IOException
	 */

	final static String getRandomID() throws IOException {

		// store all molecular comlexes into arraylist :
		postQuery();
		Random random = new Random();
		String randomID = molecularComplexes.get(random.nextInt(molecularComplexes.size()));
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