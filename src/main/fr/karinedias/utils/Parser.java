package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import src.main.fr.karinedias.model.Molecule;

public class Parser {

	/**
	 * General parser class for parsing : - molecules - chains - residues - atoms
	 */

	// TODO: use outter constructor for all inner classes ? Like this :
//	public class NestedClassExtension extends NestedClass {
//
//	    public NestedClassExtension(MainClass mainClass, String nestedValue) {
//	        mainClass.super(nestedValue);
//	    }
//	}

	@SuppressWarnings("unused")
	private StringBuilder contentOfFile = null;

	// default constructor of outer class ?
	public Parser(StringBuilder contentOfFile) {

		this.contentOfFile = contentOfFile;
	}

	// general method for extracting lines of interest in cif file
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
	 * Parsing each object data with inner classes
	 */
	public class ComplexParser {

		public ComplexParser(StringBuilder contentOfFile) {

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

		// private Complex getComplex(String entryID) {

		// return new Complex(entryID, molecules);

	}

	public class MoleculeParser {

		/*
		 * Inner class for parsing : - the number ID of the molecule - the type of the
		 * molecule (polymer/non-polymer or water) - the description
		 */

		public MoleculeParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}

		// search for molecules lines in file
		private List<String> parseMoleculeLines() {

			String lines = contentOfFile.toString();
			String[] linesToSearch = getBetweenStrings(lines, "_entity.pdbx_number_of_molecules",
					"_entity_poly.entity_id").split("\\n");
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Filter molecule lines with chains entities : polymer, non-polymer and water
			List<String> molecules = listOfLinesToSearch.stream()
					.filter(Pattern.compile("^\\d\\s(polymer|non-polymer|water)").asPredicate())
					.collect(Collectors.toList());
			return molecules;

		}

		private List<Molecule> getAllMolecules(List<String> moleculeLines) {
			return moleculeLines.stream().map(str -> parseMolecule(str)).filter(Objects::nonNull)
					.collect(Collectors.toList());
		}

		private Molecule parseMolecule(String moleculeLine) {
			String moleculePattern = "(\\d)\\s(polymer|non-polymer|water)\\s+(man|nat|syn)\\s+([^\\']*.+?\\').*";
			Pattern moleculeEntry = Pattern.compile(moleculePattern);
			Matcher m = moleculeEntry.matcher(moleculeLine);
			if (m.matches()) {
				return new Molecule(Integer.parseInt(m.group(1)), m.group(4), m.group(2));
			} else {
				return new Molecule(0, null, null); // TODO: change behaviour -> null object pattern ?
			}
		}
	}

	public class ChainParser {

		public ChainParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}

		// TODO: modify to return a Molecule object ?
		private List<Character> parseChainsOfMolecule() {

			String lines = contentOfFile.toString();
			String[] linesToSearch = getBetweenStrings(lines, "_struct.entry_id", "_atom_site.group_PDB").split("\\n");
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Filter chains of molecule by their name (one capital letter)
			List<Character> chains = listOfLinesToSearch.stream().filter(Pattern.compile("(^[A-Z])\\sN").asPredicate())
					.map(s -> s.charAt(0)).collect(Collectors.toList());
			if (chains.isEmpty())
				// TODO: cases of empty chains ?
				// search for chains in atom parser or initialize to null ?
				chains.add('\u0000');// add null character
			return chains;

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

	
	
	// TESTING :
	// TODO create method for returning Molecule object + Chain object ? + Residue
	// and Atom objects
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		String file1 = "/home/karine/src/java/ProjetPDB/doc/4NCC.cif";
		String file2 = "/home/karine/src/java/ProjetPDB/doc/3dcu.cif";
		String file3 = "/home/karine/src/java/ProjetPDB/doc/3l3t.cif";

		FileReader filereader = new FileReader(file2);
		StringBuilder content = filereader.reader();
		Parser parser = new Parser(content);
		ComplexParser complexParser = parser.new ComplexParser(content);

		MoleculeParser moleculeParser = parser.new MoleculeParser(content);
		List<String> moleculeLines = new ArrayList<String>(moleculeParser.parseMoleculeLines());
		List<Molecule> molecules = new ArrayList<Molecule>(moleculeParser.getAllMolecules(moleculeLines));

		System.out.println(molecules.size() + " molecules were found");
		Scanner sc = new Scanner(System.in);
		System.out.println("Which one do you want to display ?");
		int choice = sc.nextInt();
		System.out.println(molecules.get(choice - 1).toString());

		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(durationInNano);
		System.out.println("Elapsed time in milliseconds = " + durationInMilliseconds);

	}
}
