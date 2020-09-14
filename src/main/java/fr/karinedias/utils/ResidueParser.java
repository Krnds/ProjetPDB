package fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.karinedias.model.Residue;

public class ResidueParser {

	private StringBuilder contentOfFile = null;

	public ResidueParser(StringBuilder contentOfFile) {
		this.contentOfFile = contentOfFile;
	}

	/**
	 * Parse all lines containing the residues
	 * 
	 * @return a list of residues lines
	 */
	public List<Residue> getResidues() {

		List<Residue> listOfResidues = new ArrayList<Residue>();
		String residueLines = ExtractText.extract(contentOfFile.toString(), "_entity_poly_seq.hetero", "#");

		// example of line : 1 516 ASN n
		String pattern = "(\\d{1,2})\\s(\\d{1,3})\\s+{1,3}([A-Z]{3})\\sn\\s+";
		Pattern resPattern = Pattern.compile(pattern);

		String resName = "";
		int resNumber = 0;
		char chain = '\u0000';
		// new
		for (String s : residueLines.split("\\n")) {
			Matcher m = resPattern.matcher(s);
			if (m.find()) {
				chain = m.group(1).charAt(0);
				resNumber = Integer.parseInt(m.group(2));
				resName = m.group(3);
				listOfResidues.add(parseResidue(chain, resName, resNumber));
			}
		}
		return listOfResidues;

	}

	private Residue parseResidue(char chain, String resName, int resNumber) {

		String allAtomLines = ExtractText.extract(contentOfFile.toString(), "_atom_site.pdbx_PDB_model_num", "#");

		List<String> atomLines = new ArrayList<String>();

		for (String string : allAtomLines.split("\\n")) {
			atomLines.add(string);
		}
		// TODO: change default Residue object ?
		Residue res = new Residue(null, -1, 'Z', -1, 0.0, 0.0, 0.0); // create default Residue to avoid NullPointerExc
		for (String string : atomLines) {

			// trim all whitespace from string
			string = string.replaceAll("\\s+", "");
			// ATOM 603 C CA . GLY A 1 98 ? 14.877 -10.126 -6.092 1.00 24.45 ? 116 GLY A CA
			// 1
			String atomToFind = "ATOM(\\d{1,5})" // group 1 : atom number
					+ "([C]{1})" // group 2 : atom name
					+ "(CA[\\.|[A-Z]])" // group 3 : Alpha Carbon
					+ resName + "([A-Z]{1})" // group 4 : chain name identifier (single character)
					+ "([0-9]{1})" // group 5 : chain number identifier (single digit)
					+ resNumber + "([?]{1})" // group 6 : Code for insertion of residues
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 7 : X orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 8 : Y orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 9 : Z orthogonal coordinate of the atom

			Pattern atomEntry = Pattern.compile(atomToFind);
			Matcher m = atomEntry.matcher(string);

			if (m.find()) {
				res.setResidueName(resName);
				res.setResidueNumber(resNumber);
				res.setChain(m.group(4).charAt(0));
				res.setChainNumber(Integer.parseInt(m.group(5)));
				res.setxCoord(Double.parseDouble(m.group(7)));
				res.setyCoord(Double.parseDouble(m.group(8)));
				res.setzCoord(Double.parseDouble(m.group(9)));
			} else {
				continue;
			}
		}
		return res;
	}

}