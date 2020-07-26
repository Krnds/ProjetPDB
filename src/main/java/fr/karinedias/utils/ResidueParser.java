package main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.fr.karinedias.exceptions.AtomNotFoundException;
import main.fr.karinedias.exceptions.ResidueNotFoundException;
import main.fr.karinedias.model.Atom;
import main.fr.karinedias.model.Residue;

public class ResidueParser {

	/**
	 * @author karine qui va chercher dans tout le fichier .cif tous les résidus
	 *         (AA) donc leur liste avec leur nom (String), leur numéro (int), et la
	 *         liste des atomes correspondants (uniquement sous forme de String
	 *         d'abord puis on verra...)
	 * 
	 *         Cette classe fait le lien entre le fichier .cif et l'objet Residue
	 */

	private StringBuilder contentOfFile; // TODO: useful ?
	// private AtomParser atomParser = new AtomParser(contentOfFile);
	// TODO: change next line!!

	public ResidueParser(StringBuilder content) {

		this.contentOfFile = content;

	}

	// method for parsing atoms TODO: test it
//	public List<Atom> getAtoms() throws AtomNotFoundException {
//		List<String> listOfAtoms = new ArrayList<String>();
//		listOfAtoms.addAll(atomParser.parseAtomLines()); // return List<String>
//
//		List<Atom> atoms = new ArrayList<Atom>(listOfAtoms.size());
//		// Test getAtoms method :
//		for (String string : listOfAtoms) { // ????
//			atoms.add(atomParser.getAtoms(string));
//		}
//
//		return atoms;
//	}

	// getter
	public StringBuilder getContentOfFile() {
		return contentOfFile;
	}

	public Residue getResidues(String residueLine) throws ResidueNotFoundException, AtomNotFoundException {

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
		// Test getAtoms method :
		for (String string : listOfAtoms) { // ????
			atoms.add(atomParser.getAtoms(string));
		}

		//Residue residue = new Residue(residueName, residueNumber, (new Atom(9, , altLoc, resName, chainName, chainNumber, resNumber, xCoord, yCoord, zCoord)));

		return null;

	}

	private List<String>  parseResidueLines() {

		List<String> residueLines = new ArrayList<String>();
		String[] lines = getContentOfFile().toString().split("\\n");
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
