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

public class CytokineQuery {

	// TODO: QUERY: Structure Keywords CONTAINS PHRASE "CYTOKINE/CYTOKINE RECEPTOR"
	// ??
	private static final String SERVICELOCATION = "https://www.rcsb.org/pdb/rest/search";
	private static final String QUERYVALUE = "CYTOKINE/CYTOKINE RECEPTOR";
	private static final String XML = "<orgPdbQuery><queryType>org.pdb.query.simple.TokenKeywordQuery</queryType>"
			+ "<description>TokenKeywordQuery: struct_keywords.pdbx_keywords.comparator=contains struct_keywords.pdbx_keywords.value="
			+ QUERYVALUE + "</description>"
			+ "<struct_keywords.pdbx_keywords.comparator>contains</struct_keywords.pdbx_keywords.comparator><struct_keywords.pdbx_keywords.value>"
			+ QUERYVALUE + "</struct_keywords.pdbx_keywords.value></orgPdbQuery>";

	private static List<String> cytokinesID = new ArrayList<String>();

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
		for (int i = 0; i <= 10; i++) {
			if ((line = rd.readLine()) != null) {
				cytokinesID.add(line);
			}
		}
		rd.close();

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

	public static List<String> getCytokinesID() {
		return cytokinesID;
	}

	public static void main(String[] args) throws IOException {
		postQuery();
		for (String string : getCytokinesID()) {
			System.out.println(string);
		}

	}
}
