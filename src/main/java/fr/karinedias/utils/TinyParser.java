package fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.karinedias.exceptions.AtomNotFoundException;
import fr.karinedias.exceptions.ResidueNotFoundException;
import fr.karinedias.model.Atom;
import fr.karinedias.model.Residue;

public class TinyParser {

	/**
	 * Minimal class for parsing resides and their carbon alpha
	 */

	private StringBuilder contentOfFile = null;

	// default constructor of outer class ?
	public TinyParser(StringBuilder contentOfFile) {

		this.contentOfFile = contentOfFile;
	}

	public class ResidueParser {

		public ResidueParser(StringBuilder contentOfFile) {

			TinyParser.this.contentOfFile = contentOfFile;
		}

		// TODO Search only atoms and match Residue
		public Residue parseResidues(List<String> residueLine) throws ResidueNotFoundException, AtomNotFoundException {

			Residue residueFound = null;

			// for getting alpha carbon of the residue:
			AtomParser atomParser = new AtomParser(contentOfFile);
			List<String> listOfAlphaCarbons = new ArrayList<String>();
			listOfAlphaCarbons.addAll(atomParser.parseAtomLines()); // return List<String>

			List<Atom> carbonsAlpha = new ArrayList<Atom>(listOfAlphaCarbons.size());
			// TODO: test this
			carbonsAlpha.addAll(atomParser.parseAlphaCarbons(listOfAlphaCarbons));

			// Loop for each string representing a residue
			for (String string : residueLine) {

				// trim all whitespaces from string
				string = string.replaceAll("\\s+", "");

				String residuePattern = "(\\d{1,2})" // group 1 : chain number
						+ "(\\d{1,3})" // group 2 : residueNumber
						+ "([A-Z]{3})" // group 3 : residueName
						+ "[n]"; // not used

				Pattern residueEntry = Pattern.compile(residuePattern);
				Matcher m = residueEntry.matcher(string);

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
					continue;
					// TODO: don't use if/else instead use try/catch and catch below custom
					// exception:
					// throw new ResidueNotFoundException("No residues were found while parsing the
					// file");
				}

				// List<Residue> residuesFound = new ArrayList<Residue>();

				for (Atom atom : carbonsAlpha) {
					if (atom.getResidueName().equals(residueName) && atom.getResidueSequenceNumber() == residueNumber) {
						residueFound = new Residue(residueName, residueNumber, atom);

					}
				}

				if (residueFound.getAlphaCarbon().getAtomName() == '\u0000'
						&& residueFound.getAlphaCarbon().getAtomNumber() == 0
						&& residueFound.getAlphaCarbon().getResidueName().equals(null)) {
					System.out.println("Residue not found");
				}
			}
			return residueFound;

		}

		// TODO : use outter class method for not going through all the lines + decrease
		// exec time
		public List<String> parseResidueLines() {

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

			TinyParser.this.contentOfFile = contentOfFile;
		}

		// TODO: create a method
//		List<Atom> getAlphaCarbon(List<String> atomLines) {
//
//			return atomLines.stream().map(str -> {
//				try {
//					return parseAlphaCarbon(str);
//				} catch (AtomNotFoundException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				return null;
//			}).filter(Objects::nonNull).collect(Collectors.toList());
//		}

		public List<Atom> parseAlphaCarbons(List<String> atomLines) throws AtomNotFoundException {

//			Optional<SomeObject> result =
//				    someObjects.stream().filter(obj -> some_condition_met).findFirst();
//			boolean result = someObjects.stream().anyMatch(obj -> some_condition_met);

			List<Atom> atomsFound = new ArrayList<Atom>();

			for (int i = 0; i < atomLines.size(); i++) {

				// trim all whitespaces from string
				String currentLine = atomLines.get(i).replaceAll("\\s+", "");

				// ATOM 919 C CA . TRP A 1 121 ? -25.537 5.860 27.678 1.00 25.73 ? 141 TRP A CA
				// 1
				String dataPattern = "ATOM(\\d{1,5})" // group 1 : atom number
						+ "([C]{1})" // group 2 : atom name
						+ "(CA)" // group 3 : Alpha Carbon
						+ "[.|A-Z]([A-Z]{3})" // group 4 : residue of the atom
						+ "([A-Z]{1})" // group 5 : chain name identifier (single character)
						+ "([0-9]{1})" // group 6 : chain number identifier (single digit)
						+ "([0-9]{1,3})" // group 7 : number of the residue
						+ "([?]{1})" // group 8 : Code for insertion of residues
						+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 9 : X orthogonal coordinate of the atom
						+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 10 : Y orthogonal coordinate of the atom
						+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 11 : Z orthogonal coordinate of the atom

				Pattern atomEntry = Pattern.compile(dataPattern);
				Matcher m = atomEntry.matcher(currentLine);

				// create all variables to catch and assign default values
				int atomNumber = 0, chainNumberIdentifier = 0, residueNumber = 0;
				char atomName = '\u0000', chainNameIdentifier = '\u0000', codeInsertionResidue = '\u0000';
				String alternateLocationIndicator = null, residueName = null;
				float xOrthogonalCoordinate = 0.0f, yOrthogonalCoordinate = 0.0f, zOrthogonalCoordinate = 0.0f;

				if (m.find()) {

					atomNumber = Integer.parseInt(m.group(1));
					atomName = m.group(2).charAt(0);
					alternateLocationIndicator = m.group(3);
					residueName = m.group(4);
					chainNameIdentifier = m.group(5).charAt(0);
					chainNumberIdentifier = Integer.parseInt(m.group(6));
					residueNumber = Integer.parseInt(m.group(7));
					codeInsertionResidue = m.group(8).charAt(0);
					xOrthogonalCoordinate = Float.parseFloat(m.group(9));
					yOrthogonalCoordinate = Float.parseFloat(m.group(10));
					zOrthogonalCoordinate = Float.parseFloat(m.group(11));

					Atom currentAtom = new Atom(atomNumber, atomName, alternateLocationIndicator, residueName,
							chainNameIdentifier, chainNumberIdentifier, residueNumber, xOrthogonalCoordinate,
							yOrthogonalCoordinate, zOrthogonalCoordinate);

					atomsFound.add(currentAtom);

				} else {

					continue;
				}

			}
			if (atomsFound.isEmpty()) {
				throw new AtomNotFoundException("No atoms were found while parsing the file");
			}

			return atomsFound;
		}

		// TODO : use outter class method for not going through all the lines + decrease
		// exec time
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

	// TEST
//	public static void main(String[] args) {
//
//		String Windowsfile = "C:\\Users\\Karine\\eclipse-workspace\\ProjetPDB\\doc\\3l3t.cif";
//		String file = "/home/karine/src/java/ProjetPDB/doc/3l3t.cif";
//		
//		FileReader filereader = new FileReader(file);
//		StringBuilder content = filereader.reader();
//		TinyParser tinyParser = new TinyParser(content);
//
//		// instances for ResidueParser & AtomParser :
//		AtomParser ap = tinyParser.new AtomParser(content);
//		List<String> atomLines = ap.parseAtomLines();
//		try {
//			List<Atom> atomsFound = ap.parseAlphaCarbons(atomLines);
//		} catch (AtomNotFoundException e) {
//			e.printStackTrace();
//		}
//
//		ResidueParser rp = tinyParser.new ResidueParser(content);
//		// Find all lines with residues
//		List<String> lines = rp.parseResidueLines();
//		List<Residue> res = new ArrayList<Residue>();
//		for (String string : lines) {
//			try {
//				res.addAll(rp.parseResidues(string));
//			} catch (ResidueNotFoundException | AtomNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
//		
//		System.out.println(res.size());
//		res.get(6).toString();
//
//	}
}
