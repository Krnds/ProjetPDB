
package src.main.fr.karinedias.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
//for the measure of elapsed time :
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

import src.main.fr.karinedias.query.FetchStructure;

public class FileReader {

	/**
	 * @input : String fileName (must be retrieved from an external class of the CLI
	 *        package)
	 * @return a StringBuilder containing the file content
	 */

	private final FetchStructure structureFile;
	private String filePath;


	public FileReader() {
		//TODO: change bad pratice here
		this.structureFile = new FetchStructure();
		structureFile.getPDBFile();
		this.filePath = structureFile.getPath();
	}


//TODO: create a method for all OS compatible paths

	protected String getFilePath() {
		return filePath;
	}



	/*
	 * Test of reader : prints all the file content
	 */
	// source : https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	public StringBuilder reader(String path) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(path), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (FileNotFoundException exception) {
			System.out.println("The file " + path + " was not found.");
		} catch (IOException exception) {
			System.out.println(exception);
		}
		return contentBuilder;
	}
	

	//TODO: 13/04/2020
	public Set<String> getAtomEntries(StringBuilder content) {
		
		//TODO initial capacity and load factor of hashset ?
		HashSet<String> atomEntries = new HashSet<String>(); //spécifier init capacity & load factor
		
		String delimiter = "\n";
		
	String contentToString = content.toString();
	
	String[] array = new String[contentToString.length()];
	Set<String> set = new HashSet<>(Arrays.asList(array));
	//set.addAll(contentToString.split("\n"));
	

 
    
		
		return set;
		
	}

	// TO DELETE
	public static void main(String[] args) {
		// start time :
		long startTime = System.nanoTime();

		FileReader test = new FileReader();
		System.out.print("The file downloaded can be found at : ");
		System.out.println(test.getFilePath());
		
		System.out.println("Testing the FileReader reader method...\n");
		System.out.println(test.reader(test.getFilePath()));

		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
		
		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

}
