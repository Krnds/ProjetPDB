package src.main.fr.karinedias.utils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import src.main.fr.karinedias.model.Molecule;

public class Parser {

	/**
	 * General parser class for parsing : - molécules - chains - residues - atoms
	 */

	@SuppressWarnings("unused")
	private StringBuilder contentOfFile = null;
	public Molecule Molecule;
	
	//general method for extracting lines of interest in cif file
	private String getBetweenStrings(String text, String textFrom, String textTo) {

		String result = "";
		// Cut the beginning of the text to not occasionally meet a
		// 'textTo' value in it:
		result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());
		// Cut the excessive ending of the text:
		result = result.substring(0, result.indexOf(textTo));
		return result;
	}

	/**
	 * Use inner classes for parsing each object needed
	 * 
	 * @author Karine Dias
	 *
	 */
	public class MoleculeParser {

		public MoleculeParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}

		private String parseEntryID() {

			String id = null;
			for (String string : contentOfFile.toString().split("\\n")) {
				if (string.startsWith("_entry.id")) {
					Pattern idPattern = Pattern.compile("([A-Z]|[1-9]){4}");
					Matcher m = idPattern.matcher(string);
					if (m.find())
						id = m.group();
				}

			}
			return id;
		}

		private List<Character> parseChainsOfMolecule() {
			
			String lines = contentOfFile.toString();
			String[] linesToSearch = getBetweenStrings(lines, "_struct.entry_id",
					"_atom_site.group_PDB").split("\\n");
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Filter chains of molecule by their name (one capital letter)
			List<Character> chains = listOfLinesToSearch.stream()
					.filter(Pattern.compile("(^[A-Z])\\sN").asPredicate())
					.map(s -> s.charAt(0))
					.collect(Collectors.toList());
			if (chains.isEmpty())
				//TODO: cases of empty chains ?
				//search for chains in atom parser or initialize to null ?
				chains.add('\u0000');//add null character
			return chains;
			
		}

		private void parseMoleculeLines() {

			String lines = contentOfFile.toString();
			String[] linesToSearch = getBetweenStrings(lines, "_entity.pdbx_number_of_molecules",
					"_entity_poly.entity_id").split("\\n");
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Filter molecule lines with chains entities : polymer, non-polymer and water
			List<String> molecules = listOfLinesToSearch.stream()
					.filter(Pattern.compile("^\\d\\s(polymer|non-polymer|water)").asPredicate())
					.collect(Collectors.toList());

			for (int i = 0; i < molecules.size(); i++) {
				System.out.println(molecules.get(i));
			}

		}



	}

	// TESTING :
	//TODO create method for returning Molecule object + Chain object ? + Residue and Atom objects

	public static void main(String[] args) {
		long startTime = System.nanoTime();
		String file1 = "/home/karine/src/java/ProjetPDB/doc/4NCC.cif";
		String file2 = "/home/karine/src/java/ProjetPDB/doc/3dcu.cif";
		String file3 = "/home/karine/src/java/ProjetPDB/doc/3l3t.cif";
		
		FileReader filereader = new FileReader(file3);
		StringBuilder content = filereader.reader();
		Parser p = new Parser();
		MoleculeParser test = p.new MoleculeParser(content);
		test.parseMoleculeLines();

		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(durationInNano);
		System.out.println("Elapsed time in milliseconds = " + durationInMilliseconds);
		System.out.println("ID: " + test.parseEntryID());
		test.parseChainsOfMolecule();
	}
	
	public class ChainParser {
		
		public ChainParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}
	}
	
	public class ResidueParser {
		
		public ResidueParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}
	}
	
	
	public class AtomParser {
		
		public AtomParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}
	}
}
