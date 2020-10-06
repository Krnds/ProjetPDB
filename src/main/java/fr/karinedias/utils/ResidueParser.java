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

	public List<Residue> getAllResidues() {

		String allAtomLines = TextUtils.getStringBetween(contentOfFile.toString(), "_atom_site.pdbx_PDB_model_num",
				"#");

		List<String> atomLines = new ArrayList<String>();

		for (String string : allAtomLines.split("\\n")) {
			atomLines.add(string);
		}

		List<Residue> residuesFound = new ArrayList<Residue>();

		for (String string : atomLines) {
			Residue res = new Residue(-1, null, -1, -1, 'Z', -1, 0.0, 0.0, 0.0); // create default Residue to avoid
			// NullPointerExc
			// trim all whitespace from string
			string = string.replaceAll("\\s+", "");
			// search for this type of line : ATOM 603 C CA . GLY A 1 98 ? 14.877 -10.126
			// -6.092 1.00 24.45 ? 116 GLY A CA 1

			String atomToFind = "ATOM(\\d{1,5})" // group 1 : atom number
					+ "C" + "CA[\\.|[A-Z]]" + "([A-Z]{3})" + "([A-Z]{1})" // GROUP 2 : resName + GROUP3 : Chaine name
																			// ('A')
					+ "([0-9]{1})" // group 4 : MoleculeNumber
					+ "([0-9]{1,3})" + "[?]{1}" // group 5 : residue number
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 6 : X orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 7 : Y orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 8 : Z orthogonal coordinate of the atom
					+ ".+?([0-9]{1,3})[A-Z]{3}" // group 9 : alternate residue number
					+ "([A-Z])CA.?([0-9]{1})"; // group 10 : alternate chain Name, group 11: alt chain Number

			Pattern atomEntry = Pattern.compile(atomToFind);
			Matcher m = atomEntry.matcher(string);

			if (m.find()) {
				res.setAtomNumber(Integer.parseInt(m.group(1)));
				res.setResidueName(m.group(2));
				res.setResidueNumber(Integer.parseInt(m.group(5)));
				res.setChainNumber(Integer.parseInt(m.group(4)));
				res.setxCoord(Double.parseDouble(m.group(6)));
				res.setyCoord(Double.parseDouble(m.group(7)));
				res.setzCoord(Double.parseDouble(m.group(8)));
				res.setAltResidueNumber(Integer.parseInt(m.group(9)));
				res.setAltChain(m.group(10).charAt(0));
				residuesFound.add(res);

			}

		}

		return residuesFound;
	}

}