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

import src.main.fr.karinedias.exceptions.AtomNotFoundException;
import src.main.fr.karinedias.exceptions.ResidueNotFoundException;
import src.main.fr.karinedias.model.Atom;
import src.main.fr.karinedias.model.Molecule;
import src.main.fr.karinedias.model.Residue;

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

//		private Complex getComplex(String entryID) {
//
//			return new Complex(entryID, List<Molecule> MoleculeParser);
//		}

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
			String[] linesToSearch = getBetweenStrings(lines, "_entity.id",
					"_entity_poly.entity_id").split("\\n");
			
			List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);
			// Search for "polymer" word between those lines

			// Filter molecule lines with chains entities : polymer, non-polymer and water
			List<String> moleculesold = listOfLinesToSearch.stream()
					.filter(Pattern.compile("^\\d\\s(polymer|non-polymer|water)").asPredicate())
					.collect(Collectors.toList());
			List<String> molecules = listOfLinesToSearch.stream()
					.filter(Pattern.compile("polymer").asPredicate())
					.collect(Collectors.toList());
			return molecules;
			}
		

		public List<Molecule> getAllMolecules(List<String> moleculeLines) {
			return moleculeLines.stream().map(str -> parseMolecule(str)).filter(Objects::nonNull)
					.collect(Collectors.toList());
		}
		
		// parse only polymer molecules (non-polymer and water are not made of residues)
		private Molecule parseMolecule(String moleculeLine) {
			//String moleculePatternOld = "(\\d)\\s(polymer|non-polymer|water)\\s+(man|nat|syn)\\s+([^\\']*.+?\\').*";
			String moleculePattern = "(\\d)\\s(polymer)\\s+(man|nat|syn)\\s+([^\\']*.+?\\').*";
			Pattern moleculeEntry = Pattern.compile(moleculePattern);
			Matcher m = moleculeEntry.matcher(moleculeLine);
			if (m.matches()) {
				return new Molecule(Integer.parseInt(m.group(1)), m.group(4), m.group(2));
			} else {
				return null;// TODO: change behaviour -> null object pattern ?
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
		
		private List<Residue> getAllResidues(List<String> residueLines) {
			return residueLines.stream().map(str -> {
				try {
					return parseResidue(str);
				} catch (ResidueNotFoundException | AtomNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}).filter(Objects::nonNull).collect(Collectors.toList());
		}
		
//		return moleculeLines.stream().map(str -> parseMolecule(str)).filter(Objects::nonNull)
//				.collect(Collectors.toList());
//		
		public Residue parseResidue(String residueLine) throws ResidueNotFoundException, AtomNotFoundException {

			// trim all whitespaces from string
			residueLine = residueLine.replaceAll("\\s+", "");

			String residuePattern = "(\\d{1,2})" // group 1 : chain number
					+ "(\\d{1,3})" // group 2 : residueNumber
					+ "([A-Z]{3})" // group 3 : residueName
					+ "[n]"; // not used

			Pattern residueEntry = Pattern.compile(residuePattern);
			Matcher m = residueEntry.matcher(residueLine);

			// create all variables to catch and assign default values
			int chainNumberIdentifier = 0, residueNumber = 0;
			// TODO: modify String with Interface AminoAcid ?
			// TODO: add checker for known amino acids
			String residueName = null;

			if (m.find()) {

				chainNumberIdentifier = Integer.parseInt(m.group(1));
				residueNumber = Integer.parseInt(m.group(2));
				residueName = m.group(3);

			} else {
				// TODO: don't use if/else instead use try/catch and catch below custom
				// exception:
				throw new ResidueNotFoundException("No residues were found while parsing the file");
			}
			// for getting atoms list :
			AtomParser atomParser = new AtomParser(contentOfFile);

			List<String> listOfAtoms = new ArrayList<String>();
			listOfAtoms.addAll(atomParser.parseAtomLines()); // return List<String>

			List<Atom> atoms = new ArrayList<Atom>(listOfAtoms.size());
			// TODO: use stream 
			for (String string : listOfAtoms) { // ????
				atoms.add(atomParser.parseAtom(string));
			}

			return new Residue(residueName, residueNumber, atoms);

		}

		//TODO : use outter class method for not going through all the lines + decrease exec time
		private List<String> parseResidueLines() {

			List<String> residueLines = new ArrayList<String>();
			String[] lines = contentOfFile.toString().split("\\n");
			String pattern = "\\d{1,2}\\s\\d{1,3}\\s+{1,3}[A-Z]{3}\\sn\\s+";
			int nLines = residueLines.size();
			
			for (String s : lines) {
				if (s.matches(pattern)) {
					residueLines.add(s);
				} else {
					if (nLines > 0) {
						break;
					}
				}
			}
			return residueLines;
			
		}
		

		
	}

	public class AtomParser {

		public AtomParser(StringBuilder contentOfFile) {

			Parser.this.contentOfFile = contentOfFile;
		}
		
		// Helper method for searching all atoms used in molecule
//		private void getAtomType () {
//			String atomTypeSymbolLines = getBetweenStrings(contentOfFile.toString(), "_atom_type.symbol", "loop_");
//			
//			String atomTypeSymbolPattern = "([A-Z]|[a-z]{1,2}"; 
//
//			Pattern atomTypePattern = Pattern.compile(atomTypeSymbolPattern);
//			Matcher m = atomTypePattern.matcher(atomTypeSymbolLines);
//			
//			List<String> atomTypeSymbols = atomTypeSymbolLines.ma
//			System.out.println(s);
//			
//		}
		
		private List<Atom> getAllAtoms(List<String> atomLines) {

			return atomLines.stream().map(str -> {
				try {
					return parseAtom(str);
				} catch (AtomNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return null;
			}).filter(Objects::nonNull)
					.collect(Collectors.toList());
		}
		
		public Atom parseAtom(String atomLine) throws AtomNotFoundException {

			// trim all whitespaces from string
			atomLine = atomLine.replaceAll("\\s+", "");
			
			String dataPattern = "ATOM(\\d{1,5})" // group 1 : atom number
					+ "([CNOS]{1})" // group 2 : atom name
					+ "([CNOS]{1}[A-Z]{0,2}|[CNOS]{1}[A-Z]{1}[0-9]{0,3})" // group 3 : atom's alternate location
					+ "[.|A-Z]([A-Z]{3})" // group 4 : residue of the atom
					+ "([A-Z]{1})" // group 5 : chain name identifier (single character)
					+ "([0-9]{1})" // group 6 : chain number identifier (single digit)
					+ "([0-9]{1,3})" // group 7 : number of the residue
					+ "([?]{1})" // group 8 : Code for insertion of residues
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 9 : X orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 10 : Y orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 11 : Z orthogonal coordinate of the atom

			Pattern atomEntry = Pattern.compile(dataPattern);
			Matcher m = atomEntry.matcher(atomLine);

			// create all variables to catch and assign default values
			int atomNumber = 0, chainNumberIdentifier = 0, residueNumber = 0;
			char atomName = '\u0000', chainNameIdentifier = '\u0000', codeInsertionResidue = '\u0000';
			String alternateLocationIndicator = null, residueName = null;
			float xOrthogonalCoordinate = 0.0f, yOrthogonalCoordinate = 0.0f, zOrthogonalCoordinate = 0.0f;

			if (m.find()) {

				atomNumber = Integer.parseInt(m.group(1));
				atomName = m.group(2).charAt(0);
				alternateLocationIndicator = m.group(3);
				// TODO : verify if it's a know residue of the file ??
				residueName = m.group(4);
				chainNameIdentifier = m.group(5).charAt(0);
				chainNumberIdentifier = Integer.parseInt(m.group(6));
				residueNumber = Integer.parseInt(m.group(7));
				codeInsertionResidue = m.group(8).charAt(0);
				xOrthogonalCoordinate = Float.parseFloat(m.group(9));
				yOrthogonalCoordinate = Float.parseFloat(m.group(10));
				zOrthogonalCoordinate = Float.parseFloat(m.group(11));

			} else {
				//TODO: don't use if/else instead use try/catch and catch below custom exception: 
				throw new AtomNotFoundException("No atoms were found while parsing the file");
			}

			Atom atom = new Atom(atomNumber, atomName, alternateLocationIndicator, residueName, chainNameIdentifier,
					chainNumberIdentifier, residueNumber, xOrthogonalCoordinate, yOrthogonalCoordinate,
					zOrthogonalCoordinate);

			return atom;

		}

		//TODO : use outter class method for not going through all the lines + decrease exec time
		public List<String> parseAtomLines() {

			List<String> atomLines = new ArrayList<String>();
			String[] lines = contentOfFile.toString().split("\\n");
			for (String s : lines) {
				if (s.startsWith("ATOM")) {
					atomLines.add(s);
				}
			}

			return atomLines;

		}
	}
	
	
	// TESTING :
	// TODO create method for returning Molecule object + Chain object ? + Residue
	// and Atom objects
	public static void main(String[] args) {
		long startTime = System.nanoTime();
		List<String> listOfFiles = Arrays.asList("1fn3.cif","2b5i.cif","2na8.cif","3bw7.cif","3c0p.cif","3dcu.cif","3l3t.cif","3qt2.cif",
		"3tnw.cif","4NCC.cif","6rj4.cif");
		int index = 0;
		for(String s : listOfFiles)
		    System.out.print((index++)+": [" + s + "]  ");
		System.out.println("\nChoose a molecule from the list : \n");
		Scanner sc = new Scanner(System.in);
		int fileChosen = sc.nextInt();
		String file = "/home/karine/src/java/ProjetPDB/doc/" + listOfFiles.get(fileChosen);

		FileReader filereader = new FileReader(file);
		StringBuilder content = filereader.reader();
		Parser parser = new Parser(content);
		
		//Complex
		ComplexParser complexParser = parser.new ComplexParser(content);
		//Molecules
		MoleculeParser moleculeParser = parser.new MoleculeParser(content);
		List<String> moleculeLines = new ArrayList<String>(moleculeParser.parseMoleculeLines());
		List<Molecule> molecules = new ArrayList<Molecule>(moleculeParser.getAllMolecules(moleculeLines));

		System.out.println(molecules.size() + " molecules were found");
		System.out.println("Which one do you want to display ?");
		int molculeChoice = sc.nextInt();
		System.out.println(molecules.get(molculeChoice - 1).toString());
		
		//Residues 
		ResidueParser residueParser = parser.new ResidueParser(content);
		List<String> residueLines = new ArrayList<String>(residueParser.parseResidueLines());
		List<Residue> residues = new ArrayList<Residue>(residueParser.getAllResidues(residueLines));
		
		System.out.println(residues.size() + " residues were found");
		System.out.println("Which one do you want to display ?");
		int residueChoice = sc.nextInt();
		System.out.println(residues.get(residueChoice - 1).toString());

		//Atoms
		AtomParser atomParser = parser.new AtomParser(content);
	
		List<String> atomLines = new ArrayList<String>(atomParser.parseAtomLines());
		List<Atom> atoms = new ArrayList<Atom>(atomParser.getAllAtoms(atomLines));
		System.out.println(atoms.size() + " atoms were found");
		System.out.println("Which one do you want to display ?");
		int atomChoice = sc.nextInt();
		System.out.println(atoms.get(atomChoice - 1).toString());
		
		long endTime = System.nanoTime();
		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInMilliseconds = TimeUnit.NANOSECONDS.toMillis(durationInNano);
		System.out.println("Elapsed time in milliseconds = " + durationInMilliseconds);

	}
}
