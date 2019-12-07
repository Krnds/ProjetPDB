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

public class XMLQuery {

	public static final String SERVICELOCATION = "https://www.rcsb.org/pdb/rest/search";

	public static void main(String[] args) {

		String xml = "<orgPdbQuery><queryType>org.pdb.query.simple.NumberOfEntitiesQuery</queryType><description>Number of Entities Search : Entity Type=Protein Min Number of Entities=2 Max Number of Entities=2</description><entity.type.>p</entity.type.><struct_asym.numEntities.min>2</struct_asym.numEntities.min><struct_asym.numEntities.max>2</struct_asym.numEntities.max></orgPdbQuery>";

		XMLQuery t = new XMLQuery();

		try {
			List<String> pdbIds = t.postQuery(xml);

			for (String string : pdbIds) {
				System.out.println(string);

			}
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	/**
	 * post am XML query (PDB XML query format) to the RESTful RCSB web service
	 * 
	 * @param xml
	 * @return a list of PDB ids.
	 */
	public List<String> postQuery(String xml) throws IOException {

		URL u = new URL(SERVICELOCATION);

		String encodedXML = URLEncoder.encode(xml, "UTF-8");

		InputStream in = doPOST(u, encodedXML);

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
	 * do a POST to a URL and return the response stream for further processing
	 * elsewhere.
	 * 
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	public static InputStream doPOST(URL url, String data)

			throws IOException {

		// Send data

		URLConnection conn = url.openConnection();

		conn.setDoOutput(true);

		OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());

		wr.write(data);
		wr.flush();

		// Get the response
		return conn.getInputStream();

	}

}