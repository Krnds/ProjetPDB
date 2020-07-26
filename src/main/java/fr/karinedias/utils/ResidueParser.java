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
 * @return a list of residues lines
 */
	public List<Residue> getResidueLines() {

		List<Residue> listOfResidues = new ArrayList<Residue>(); //using empty constructor for creating an empty object
		String result = "";
		result = contentOfFile.toString().substring(contentOfFile.toString().indexOf("entity_poly_seq") + "entity_poly_seq".length(), contentOfFile.toString().length());
		// Cut the excessive ending of the text:
		result = result.substring(0, result.indexOf("#"));

		//example of line : 1 516 ASN n 
		String pattern = "(\\d{1,2})\\s(\\d{1,3})\\s+{1,3}([A-Z]{3})\\sn\\s+";
		Pattern resPattern = Pattern.compile(pattern);

		String resName = "";
		int resNumber = 0;
		char chain = '\u0000';
		//new 
		for (String s : result.split("\\n")) {
			Matcher m = resPattern.matcher(s);
			if (m.find()) {
				chain = m.group(1).charAt(0);
				resNumber = Integer.parseInt(m.group(2));
				resName = m.group(3);
				listOfResidues.add(getResidue(chain, resName, resNumber));
			}
		}
		return listOfResidues;

	}


	
	private Residue getResidue(char chain, String resName, int resNumber) {
		
		String result = "";
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
	
			
		Residue res = new Residue("", 0, 'Z', 0.0, 0.0, 0.0);
		//TEST
		for (String string : atomLines) {

			// trim all whitespaces from string
			string = string.replaceAll("\\s+", "");
			//ATOM   603  C CA    . GLY A 1 98  ? 14.877 -10.126 -6.092  1.00 24.45 ? 116  GLY A CA    1 
			String atomToFind = "ATOM(\\d{1,5})" // group 1 : atom number
					+ "([C]{1})" // group 2 : atom name
					+ "(CA[\\.|[A-Z]])" // group 3 : Alpha Carbon
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
				res.setChain(m.group(5).charAt(0));
				res.setxCoord(Double.parseDouble(m.group(7)));
				res.setyCoord(Double.parseDouble(m.group(8)));
				res.setzCoord(Double.parseDouble(m.group(9)));
			} else {
				continue;
			}
		}
			return res;
	}
	
//	public static void main(String[] args) {
//		
//		long startTime = System.currentTimeMillis();
//		
//		String filename1 = "/home/karine/src/java/ProjetPDB/doc/5hqx.cif";
//		String filename = "/home/karine/src/java/ProjetPDB/doc/6rj4.cif";
//		FileReader pdb = new FileReader(filename);
//		StringBuilder sb = pdb.reader();
//		ResidueWithCoordinatesParser rwcp = new ResidueWithCoordinatesParser(sb);
//		
//		List<ResidueWithCoordinates> list = rwcp.getResidueLines();
//
//		for (ResidueWithCoordinates res : list) {
//			System.out.println(res.toString());
//		}
//		
//		long endTime = System.currentTimeMillis();
//		long elapsedTime = (endTime - startTime)/1000;
//		System.out.println("Elapsed time : " + elapsedTime + " seconds");
//
//	}
//	
//	
//	
//	/**
//	 * From ResidueWithCoordinates : 
//	 * - String name
//	 * - int number
//	 * - double x, y, z coordinates
//	 */
//	
//	
//



}
