package main.fr.karinedias.query;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class DownloadPDB2 {

	public static void main(String[] args) {
		String url = "https://www.pdb.org/pdb/download/downloadFile.do?fileFormat=pdb&compression=NO&structureId=1jtz";

		try {
			downloadUsingNIO(url, "/Users/dias/eclipse-workspace/ProjetPDB/doc/test.cif");

			downloadUsingStream(url, "/Users/dias/eclipse-workspace/ProjetPDB/doc/streamtest.cif");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		String filename = "/Users/dias/eclipse-workspace/ProjetPDB/doc/1jtz.cif";
		try {
			downloadUsingJavaIO(url, filename);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		}

	private static void downloadUsingStream(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		BufferedInputStream bis = new BufferedInputStream(url.openStream());
		FileOutputStream fis = new FileOutputStream(file);
		byte[] buffer = new byte[2048];
		int count = 0;
		while ((count = bis.read(buffer, 0, 1024)) != -1) {
			fis.write(buffer, 0, count);
		}
		fis.close();
		bis.close();
	}

	private static void downloadUsingNIO(String urlStr, String file) throws IOException {
		URL url = new URL(urlStr);
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
		FileOutputStream fos = new FileOutputStream(file);
		fos.getChannel().transferFrom(rbc, 0, Long.MAX_VALUE);
		fos.close();
		rbc.close();
	}

	private static void downloadUsingJavaIO(String urlStr, String file) throws IOException {


		try (BufferedInputStream in = new BufferedInputStream(new URL(urlStr).openStream());
				FileOutputStream fileOutputStream = new FileOutputStream(file)) {
			byte dataBuffer[] = new byte[1024];
			int bytesRead;
			while ((bytesRead = in.read(dataBuffer, 0, 1024)) != -1) {
				fileOutputStream.write(dataBuffer, 0, bytesRead);
			}
		} catch (IOException e) {
			// handle exception
		}
	}

}
