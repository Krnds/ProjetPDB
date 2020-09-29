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

		// List<Character> chainsFound = new ArrayList<>();
		List<Chain> chainsOfMolecule = new ArrayList<>();
//		Chain chain = new Chain('\u0000', -1); // default chain object
		for (String string : chainLines) {

			// trim all whitespace from string
			string = string.replaceAll("\\s+", "");

			// TODO mettre dans le regex le nom de la strucure, mais lier avec l'objet
			// Structure ?

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

//	public static void main(String[] args) throws URISyntaxException {
//		String test1 = " _struct_ref_seq.pdbx_auth_seq_align_end \n"
//				+ "	1 1 3QB7 A 4 ? 132 ? D4HNR6 25 ? 153 ? 1  129 \n"
//				+ "	2 1 3QB7 B 4 ? 132 ? D4HNR6 25 ? 153 ? 1  129 \n"
//				+ "	3 2 3QB7 C 4 ? 203 ? P31785 55 ? 254 ? 33 232 \n"
//				+ "	4 2 3QB7 D 4 ? 203 ? P31785 55 ? 254 ? 33 232 \n" + "	# ";
//		String file = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/6rj4.cif";
//		FileReader pdb = new FileReader(file);
//		StringBuilder sb = pdb.reader();
//		ChainParser cp = new ChainParser(sb);
//		Chain test = cp.getChains();
//		System.out.println(test);
//
//	}
}
