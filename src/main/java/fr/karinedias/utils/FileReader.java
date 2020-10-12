package fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
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


	protected String getFilePath() {
		return filePath;
	}

	/*
	 * Method for reading a file in IDE
	 */
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

	
	// Method for reading a file in jar
	public StringBuilder readerJar() throws IOException {


		try (InputStream inputStream = getClass().getResourceAsStream(getFilePath());
				BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
			String contents = reader.lines().collect(Collectors.joining(System.lineSeparator()));

			StringBuilder sb = new StringBuilder(contents);

			return sb;

		}
	}
}
