package main.fr.karinedias.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import main.fr.karinedias.model.ResidueWithCoordinates;

public class ResidueWithCoordinatesParser {

	private StringBuilder contentOfFile = null;

	public ResidueWithCoordinatesParser(StringBuilder contentOfFile) {
		this.contentOfFile = contentOfFile;
	}

	
/**
 * Parse all lines containing the residues
 * @return a list of residues lines
 */
	private List<String> getResidueLines() {

		String result = "";
		// Cut the beginning of the text to not occasionally meet a
		// 'textTo' value in it:
		result = contentOfFile.toString().substring(contentOfFile.toString().indexOf("entity_poly_seq") + "entity_poly_seq".length(), contentOfFile.toString().length());
		// Cut the excessive ending of the text:
		result = result.substring(0, result.indexOf("#"));
		List<String> residueLines = new ArrayList<String>();
		String[] lines = result.split("\\n");
		//1 516 ASN n 
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
	
	private void getResidues (List<String> resLines) {
		
		for (String string : resLines) {
			string.split("\\n");
			
		}
		
	}

	
	public ResidueWithCoordinates getAlphaCarbon(String resName, int resNumber) {
		
		String result = "";
		// Cut the beginning of the text to not occasionally meet a
		// 'textTo' value in it:
		result = contentOfFile.toString().substring(contentOfFile.toString().indexOf("_atom_site.pdbx_PDB_model_num") + "_atom_site.pdbx_PDB_model_num".length(), contentOfFile.toString().length());
		// Cut the excessive ending of the text:
		result = result.substring(0, result.indexOf("#"));

		List<String> atomLines = new ArrayList<String>();
		
		// Put all lines of atoms into atomLines list
//		atomLines.addAll(Arrays.asList(result.split("\\n"))); 
		
		//Other way
		for (String string : result.split("\\n")) {
			atomLines.add(string);
		}
	
			
		ResidueWithCoordinates res = new ResidueWithCoordinates("", 0, 0.0, 0.0, 0.0);
		//TEST
		for (String string : atomLines) {

			// trim all whitespaces from string
			string = string.replaceAll("\\s+", "");
			//ATOM   603  C CA    . GLY A 1 98  ? 14.877 -10.126 -6.092  1.00 24.45 ? 116  GLY A CA    1 
			String atomToFind = "ATOM(\\d{1,5})" // group 1 : atom number
					+ "([C]{1})" // group 2 : atom name
					+ "(CA\\.)" // group 3 : Alpha Carbon
					+ resName
					+ "([A-Z]{1})" // group 4 : chain name identifier (single character)
					+ "([0-9])" // group 5 : chain number identifier (single digit)
					+ resNumber 
					+ "([?]{1})" // group 6 : Code for insertion of residues
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 7 : X orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})" // group 8 : Y orthogonal coordinate of the atom
					+ "(-?[0-9]{1,4}\\.[0-9]{3})"; // group 9 : Z orthogonal coordinate of the atom

			Pattern atomEntry = Pattern.compile(atomToFind);
			Matcher m = atomEntry.matcher(string);
			
			if (m.find()) {
				res.setResidueName(resName);
				res.setResidueNumber(resNumber);
				res.setxCoord(Float.parseFloat(m.group(7)));
				res.setyCoord(Float.parseFloat(m.group(8)));
				res.setzCoord(Float.parseFloat(m.group(9)));
			} else {
				continue;
			}
		}
			return res;
	}
	
	public static void main(String[] args) {
		String filename = "/home/karine/src/java/ProjetPDB/doc/3bw7.cif";
		FileReader pdb = new FileReader(filename);
		StringBuilder sb = pdb.reader();
		ResidueWithCoordinatesParser rwcp = new ResidueWithCoordinatesParser(sb);
		
		List<String> testresidues = new ArrayList<String>();
		testresidues.addAll(rwcp.getResidueLines());
		
		for (String string : testresidues) {
			System.out.println(string);
		}
		
		// 1 10  ASP n
		//System.out.println(testresidues.get(9).toString());
		//ATOM   603  C CA    . GLY A 1 98  ? 14.877 -10.126 -6.092  1.00 24.45 ? 116  GLY A CA    1 
//		long startTime = System.currentTimeMillis();
//		ResidueWithCoordinates test = rwcp.getAlphaCarbon("SER", 509);
//		
//		System.out.println(test.toString());
//		long endTime = System.currentTimeMillis();
//		long elapsedTime = endTime - startTime;
//		System.out.println("Elapsed time : " + elapsedTime + " millisec");
	}
	
	
	
	/**
	 * From ResidueWithCoordinates : 
	 * - String name
	 * - int number
	 * - double x, y, z coordinates
	 */
	
	




}
