package src.main.fr.karinedias.utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser {

	/**
	 * General parser class for parsing : - molécules - chains - residues - atoms
	 */

	@SuppressWarnings("unused")
	private StringBuilder contentOfFile = null;

	/**
	 * Use inner classes for parsing each object needed
	 * 
	 * @author karine
	 *
	 */
	public class MoleculeParser {

		public MoleculeParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}

		private void parseMoleculeLines() {

			String lines = contentOfFile.toString();
			String[] linesToSearch = getBetweenStrings(lines, "_entity.pdbx_number_of_molecules",
					"_entity_poly.entity_id").split("\\n");
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Chains entities are of three types: polymer, non-polymer and water
			List<String> molecules = listOfLinesToSearch.stream().filter(Pattern.compile("^\\d\\s(polymer|non-polymer|water)").asPredicate())
					.collect(Collectors.toList());

			for (int i = 0; i < molecules.size(); i++) {
				System.out.println(molecules.get(i));
			}

		}

		private String getBetweenStrings(String text, String textFrom, String textTo) {

			String result = "";

			// Cut the beginning of the text to not occasionally meet a
			// 'textTo' value in it:
			result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());

			// Cut the excessive ending of the text:
			result = result.substring(0, result.indexOf(textTo));

			return result;
		}

	}

	// TESTING :

	public static void main(String[] args) {
		String fileTestPath = "/home/karine/src/java/ProjetPDB/doc/2b5i.cif";
		FileReaderV2 filereader = new FileReaderV2(fileTestPath);
		StringBuilder content = filereader.reader();
		Parser p = new Parser();
		MoleculeParser test = p.new MoleculeParser(content);
		test.parseMoleculeLines();
	}
}
