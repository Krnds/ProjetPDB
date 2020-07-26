package fr.karinedias.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileReader {

	/**
	 * @input : String fileName (must be retrieved from an external class of the CLI
	 *        package)
	 * @return a StringBuilder containing the file content
	 */

	private String filePath;

	public FileReader(String path) {
		this.filePath = path;
	}


//TODO: create a method for all OS compatible paths

	protected String getFilePath() {
		return filePath;
	}


	/*
	 * Test of reader : prints all the file content
	 */
	// source : https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	public StringBuilder reader() throws URISyntaxException {
		StringBuilder contentBuilder = new StringBuilder();
		URI fileUri = ClassLoader.getSystemResource(filePath).toURI();
		try (Stream<String> stream = Files.lines(Paths.get(fileUri), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (FileNotFoundException exception) {
			System.out.println("The file " + filePath + " was not found.");
		} catch (IOException exception) {
			System.out.println(exception);
		}
		return contentBuilder;
	}

}
