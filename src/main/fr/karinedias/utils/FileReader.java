
package src.main.fr.karinedias.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
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
	// file paths to delete...
	private final String filePathMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/6hk2.cif";
	private final String fileWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

	public FileReader() {
		// initialize FetchStructure object here ?
		this.structureFile = new FetchStructure();
		structureFile.getPDBFile();
	}

	public String getFileName() {

		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			return filePathMacOS;
		} else if (System.getProperty("os.name").equalsIgnoreCase("Windows 7")) {
			return fileWindows;
		} else {
			return structureFile.getPathStructure();
		}
	}

	/*
	 * Test of reader : prints all the file content
	 */
	// source : https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	public StringBuilder reader(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (FileNotFoundException exception) {
			System.out.println("The file " + filePath + " was not found.");
		} catch (IOException exception) {
			System.out.println(exception);
		}
		return contentBuilder;
	}

	// TO DELETE
	public static void main(String[] args) {
		// start time :
		long startTime = System.nanoTime();

		FileReader test = new FileReader();
		System.out.println("Testing the FileReader reader method....\n");
		System.out.println(test.reader(test.getFileName()));

		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);
		
		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

}
