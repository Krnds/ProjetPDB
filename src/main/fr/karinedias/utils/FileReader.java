
package main.fr.karinedias.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream.GetField;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class FileReader {

	/**
	 * @input : String fileName (must be retrieved from an external class of the CLI
	 *        package)
	 * @return a StringBuilder containing the file content
	 */

	//TODO: replace with external method which demands filename
	private final String filePathMacOS = "/Users/dias/eclipse-workspace/ProjetPDB/doc/6hk2.cif";
	private final String filePathDebian = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";


	public String getFileName() {

		if (System.getProperty("os.name").equalsIgnoreCase("Mac OS X")) {
			return filePathMacOS;
		} else {
			return filePathDebian;
		}
	}

	/*
	 * Test of readerTest : prints only the first line
	 */

	public StringBuilder readerTest(String input) {
		Scanner sc;
		File file = new File(input);
		StringBuilder fileContent = new StringBuilder("");

		try {
			sc = new Scanner(file);
			while (sc.hasNextLine()) {

				fileContent = fileContent.append(sc.nextLine());

			}
			System.out.println(fileContent);
			sc.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		return fileContent;
	}

	/*
	 * Test of readerTest2 : prints only the first line
	 */

	public static StringBuilder readerTest2(String input) {
		Path filePath = Paths.get(input);
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

	/*
	 * Test of readerTest3 : prints only the first line
	 */

	public static StringBuilder readerTest3(String input) throws IOException {

		java.io.FileReader fr = new java.io.FileReader(input);

		BufferedReader br = new BufferedReader(fr);
		StringBuilder sb = new StringBuilder();

		String strCurrentLine;

		while ((strCurrentLine = br.readLine()) != null) {

			sb.append(strCurrentLine);

		}
		return sb;

	}

	/*
	 * Test of reader : prints all the file content
	 */
	// source : https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
	public StringBuilder reader(String filePath) {
		StringBuilder contentBuilder = new StringBuilder();
		try (Stream<String> stream = Files.lines(Paths.get(filePath), StandardCharsets.UTF_8)) {
			stream.forEach(s -> contentBuilder.append(s).append("\n"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return contentBuilder;
	}

	/*
	 * Tests in method main for all reader methods
	 */
	public static void main(String[] args) throws IOException {

		// System.out.println(FileReader.reader("/Users/dias/cnam/projet-cnam/CalculDistancesPDB/doc/1fn3.cif"));

	}
}
