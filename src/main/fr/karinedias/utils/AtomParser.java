package src.main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import src.main.fr.karinedias.exceptions.AtomNotFoundException;
import src.main.fr.karinedias.model.Atom;

public class AtomParser {

	private final StringBuilder contentOfFile; //TgetODO: useful ?

	public AtomParser(StringBuilder content) {

		this.contentOfFile = content;

	}

	//FOR TESTING PURPOSES
	public static void main(String[] args) throws AtomNotFoundException {

		//CALLING GETATOMS METHOD :
		
		long startTime = System.nanoTime();
		String fileTestPath = "/home/karine/src/java/ProjetPDB/doc/3qt2.cif";
		//FileReader fileTest = new FileReader();
		FileReader fileTest = new FileReader(fileTestPath);
		StringBuilder content = fileTest.reader();
//		System.out.println(content.toString());
		AtomParser atomsTest = new AtomParser(content);
		//TEST WITH A LIST<STRING> CONTAINING ATOM LINES
		List<String> atomsFound = new ArrayList<String>();
		atomsFound.addAll(atomsTest.parseAtomLines());
		System.out.println("I've found " + atomsFound.size() + " atoms !\n");

		List<Atom> atoms = new ArrayList<Atom>(atomsFound.size());
		//Test getAtoms method :
		for (String string : atomsFound) { //????
			atoms.add(atomsTest.getAtoms(string));
		}
		Atom n88 = atoms.get(88);
		Atom n535 = atoms.get(535);
		Atom n8445 = atoms.get(8445);
		System.out.println(n88.toString() + "\n" 
				+ n535.toString() + "\n" 
				+ n8445.toString());
		
		
		long endTime = System.nanoTime();

		long durationInNano = (endTime - startTime); // Total execution time in nano seconds
		long durationInSeconds = TimeUnit.NANOSECONDS.toSeconds(durationInNano);

		System.out.println("Elapsed time in seconds = " + durationInSeconds);
	}

	// getter
	public StringBuilder getContentOfFile() {
		return contentOfFile;
	}

	public Atom getAtoms(String atomLine) throws AtomNotFoundException {

		// trim all whitespaces from string
		atomLine = atomLine.replaceAll("\\s+", "");
		
		String dataPattern = "ATOM(\\d{1,5})" // group 1 : atom number
				+ "([CNOS]{1})" // group 2 : atom name
				+ "([CNOS]{1}[A-Z]{0,2}|[CNOS]{1}[A-Z]{1}[0-9]{0,3})" // group 3 : atom's alternate location
				+ "[.]([A-Z]{3})" // group 4 : residue of the atom
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


	public List<String> parseAtomLines() {

		List<String> atomLines = new ArrayList<String>();
		String[] lines = getContentOfFile().toString().split("\\n");
		for (String s : lines) {
			if (s.startsWith("ATOM")) {
				atomLines.add(s);
			}
		}

		return atomLines;

	}
}
