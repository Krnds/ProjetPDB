package fr.karinedias.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import fr.karinedias.Application;

public class FileReader {

	/**
	 * @input : String fileName (must be retrieved from an external class of the CLI
	 *        package)
	 * @return a StringBuilder containing the file content
	 */

	private String filePath;

	public FileReader(String path) {
		filePath = path;
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

		URI fileUri = new File(filePath).toURI();
		try (Stream<String> stream = Files.lines(Paths.get(fileUri), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (FileNotFoundException exception) {
			System.out.println("The file " + filePath + " was not found.");
		} catch (IOException exception) {
			System.out.println(exception);
		}
		return contentBuilder;
	}
	
	
	public StringBuilder readerJar() throws URISyntaxException {
		
	    Path pathToFile = Paths.get(getFilePath());
	    System.out.println(pathToFile.toAbsolutePath());
		//ClassLoader classLoader = this.getClass().getClassLoader();
	    ClassLoader classLoader = pathToFile.getClass().getClassLoader();
        InputStream inputStream = classLoader.getResourceAsStream(getFilePath());

        //InputStream inputStream = Application.class.getResourceAsStream(getFilePath());
        // the stream holding the file content
        if (inputStream == null) {
            throw new IllegalArgumentException("file not found! " + pathToFile);
        }
		
		StringBuilder contentBuilder = new StringBuilder();

		try (Stream<String> stream = Files.lines(Paths.get(inputStream.toString()), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (FileNotFoundException exception) {
			System.out.println("The file " + filePath + " was not found.");
		} catch (IOException exception) {
			System.out.println(exception);
		}
		return contentBuilder;
	}

}
