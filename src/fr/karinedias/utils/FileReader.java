package fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader {

	private String filename = "";

	public FileReader(String filename) {
		this.setFilename(filename);
	}

	public StringBuilder readerTest() {
		Scanner sc;
		File file = new File(getFilename());
		StringBuilder fileContent = new StringBuilder("");

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {

				fileContent = fileContent.append(sc.nextLine());
				System.out.println(fileContent);
				sc.close();

			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	public StringBuilder readerTest2 () {
		Path filePath = Paths.get(getFilename());
		Charset charset = Charset.forName("UTF-8");
		StringBuilder sb = new StringBuilder("");
		try {
			List<String> lines = Files.readAllLines(filePath, charset);

			for (String line : lines) {
				sb.append(line);

			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return sb;
	}



	public static StringBuilder readerTest3 (String filePath) throws IOException {

		java.io.FileReader fr = new java.io.FileReader(filePath);

		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();

		String strCurrentLine;

		while ((strCurrentLine = br.readLine()) != null) {

			sb.append(strCurrentLine);

		}
		return sb;

	}
	
	//source : https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	public static StringBuilder reader (String filePath)
	{
	    StringBuilder contentBuilder = new StringBuilder();
	    try (Stream<String> stream = Files.lines( Paths.get(filePath), StandardCharsets.UTF_8))
	    {
	    	stream.forEach(s -> contentBuilder.append(s).append("\n"));
	    }
	    catch (IOException e)
	    {
	        e.printStackTrace();
	    }
	    return contentBuilder;
	}
	
	/*
	 * Getter and Setter fir filename : 
	 */
	
	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	

	// test de la classe FileReader : 
	//

	public static void main(String[] args) throws IOException {

		FileReader haemoglobin = new FileReader("/Users/dias/cnam/projet-cnam/CalculDistancesPDB/doc/1fn3.cif");
		System.out.println(FileReader.reader("/Users/dias/cnam/projet-cnam/CalculDistancesPDB/doc/1fn3.cif"));
		
		StringBuilder fileContent = FileReader.reader(haemoglobin.getFilename());

		//test de ma méthode reader
		//System.out.println(fileContent);

	}
}
