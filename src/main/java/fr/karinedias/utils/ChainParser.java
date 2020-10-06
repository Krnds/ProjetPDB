package fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import fr.karinedias.model.Chain;
import fr.karinedias.model.Molecule;

public class ChainParser {

	private StringBuilder contentOfFile = null;

	public ChainParser(StringBuilder contentOfFile) {
		this.contentOfFile = contentOfFile;
	}

	public List<Chain> getChains(Molecule mol) {

		String allChainLines = TextUtils.getStringBetween(contentOfFile.toString(),
				"_struct_ref_seq.pdbx_auth_seq_align_end", "#");
		List<String> chainLines = new ArrayList<String>();
		for (String string : allChainLines.split("\\n")) {
			chainLines.add(string.trim());
		}

		List<Chain> chainsOfMolecule = new ArrayList<>();

		for (String string : chainLines) {

			// trim all whitespace from string
			string = string.replaceAll("\\s+", "");

			// 1 1 3QB7 A 4 ? 132 ? D4HNR6 25 ? 153 ? 1 129
			String chainToFind = "\\d{1,2}(\\d)([A-Z0-9]{4})" // group 1 : molecule Number, group 2 : Structure ID
					+ "([A-Z])"; // group 3 : chain Name (character)

			Pattern atomEntry = Pattern.compile(chainToFind);
			Matcher m = atomEntry.matcher(string);

			if (m.find()) {
				if (Integer.parseInt(m.group(1)) == mol.getId())
				chainsOfMolecule.add(new Chain(m.group(3).charAt(0), Integer.parseInt(m.group(1))));
			}
		}
		return chainsOfMolecule;
	}

}
