package main.fr.karinedias.query;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.nio.file.Files;
import java.nio.file.attribute.PosixFilePermissions;
import java.util.Scanner;

public class DownloadPDB {

	// todo: Ne fonctionne pas, car si le fichier n'existe pas retourne une erreur
	// ou sinon s'il existe,
	// le remplace par un fichier vide.

	public static void main(String[] args) {

		// www.pdb.org/pdb/download/downloadFile.do?fileFormat=pdb&compression=NO&structureId=6hk2

		URL url;
		URLConnection con;
		DataInputStream dis;
		FileOutputStream fos;
		Scanner sc = new Scanner(System.in);
		byte[] fileData;
		try {
			System.out.println("Which structure would you like to download ?\n");
			String id = sc.nextLine();
			String s = "https://www.pdb.org/pdb/download/downloadFile.do?fileFormat=pdb&compression=NO&structureId="
					+ id; // File Location goes here
			String encoded = java.net.URLEncoder.encode(s, "UTF-8");
			url = new java.net.URL(s);
			con = url.openConnection(); // open the url connection.
			dis = new DataInputStream(con.getInputStream());
			fileData = new byte[con.getContentLength()];
			for (int q = 0; q < fileData.length; q++) {
				fileData[q] = dis.readByte();
			}
			dis.close(); // close the data input stream
			String outputStandard = "/home/karine/src/java/ProjetPDB/doc/";
			File outputFile = new File(outputStandard + id + ".cif");
			Files.setPosixFilePermissions(outputFile.toPath(), PosixFilePermissions.fromString("rwxr-x---"));
			fos = new FileOutputStream(outputFile);
			fos.write(fileData); // write out the file we want to save.
			fos.close(); // close the output stream writer
			System.exit(0);
		} catch (Exception m) {
			System.out.println(m);
		}
	}
}
