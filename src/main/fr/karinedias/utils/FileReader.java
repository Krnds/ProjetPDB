
package src.main.fr.karinedias.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
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

	// TODO: replace with external method which demands filename
	private final String filePathMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/6hk2.cif";
	private final String filePathDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
	private final String fileWindows = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\2b5i.cif";

	public FileReader() {
		getFileName();
	}

	public String getFileName() {

		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			return filePathMacOS;
		} else if (System.getProperty("os.name").equalsIgnoreCase("Windows 7")) {
			return fileWindows;
		} else {
			return filePathDebian;
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

}
