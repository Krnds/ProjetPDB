package fr.karinedias.utils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import fr.karinedias.model.Molecule;

public class MoleculeParser {
	/**
	 * contentOfFile (StringBuilder) -> parseMoleculeLines (List <String>)
	 * -> parseMolecule
	 */
	private StringBuilder contentOfFile = null;

	public MoleculeParser(StringBuilder contentOfFile) {
		this.contentOfFile = contentOfFile;
	}

	// search for molecules lines in file
	public List<String> parseMoleculeLines() {

		String[] linesToSearch = TextUtils.getStringBetween(contentOfFile.toString(), "_entity.id",
				"#").split("\\n");
		
		List<String> listOfLinesToSearch = Arrays.asList(linesToSearch);

		// Filter molecule lines with polymer entity
		List<String> molecules = listOfLinesToSearch.stream()
				.filter(Pattern.compile("^\\d\\s(polymer)").asPredicate())
				.collect(Collectors.toList());
		return molecules;
		}
	

	public List<Molecule> getAllMolecules(List<String> moleculeLines) {
		return moleculeLines.stream().map(str -> parseMolecule(str)).filter(Objects::nonNull)
				.collect(Collectors.toList());
	}
	
	// parse only polymer molecules (non-polymer and water are not made of residues)
	private Molecule parseMolecule(String moleculeLine) {

		String moleculePattern = "(\\d)\\s(polymer)\\s+\\w+\\s(.+)\\s+";
		Pattern moleculeEntry = Pattern.compile(moleculePattern);
		Matcher m = moleculeEntry.matcher(moleculeLine);
		if (m.lookingAt()) {
			return new Molecule(Integer.parseInt(m.group(1)), m.group(3).trim(), m.group(2));
		} else {
			// TODO: change behavior -> null object pattern ?
			return null;
		}
	}
	
}