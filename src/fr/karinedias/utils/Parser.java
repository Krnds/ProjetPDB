package fr.karinedias.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {

	/*
	 * TESTING WITH THE CIF FILE :
	 * /Users/dias/cnam/projet-cnam/CalculDistancesPDB/doc/1fn3.cif
	 */

	static StringBuilder sb = new StringBuilder();

	public boolean searchAtomsEntries(StringBuilder input) {

		Pattern atomPattern = Pattern.compile("^ATOM\\s");
		Matcher matcher = atomPattern.matcher(input);
		while (matcher.find()) {
			return true;
		}
		return false;

	}
	
	/*
	 * Method for searching the coordinates for each atom within the molecule
	 * TODO: use interface "spliterator" for parallel processing of stream of elements for any collection
	 */
	
	public static ArrayList<Double> atomicCoordinatesParser(StringBuilder atomsInput) {
		
		ArrayList<Double> atomicCoordinates = new ArrayList<Double>();
		Pattern atomicCoordinatesPattern = Pattern.compile("\\s\\d{1,4}(.)\\d\\d\\d");
		Matcher matcher = atomicCoordinatesPattern.matcher(atomsInput);
		
		while (matcher.find()) {
			System.out.println("groupe: " + matcher.group());
		}
		String coordinatesDelimiter = " ";
		atomsInput.toString().split("\\d\\d.\\d\\d\\d\\s{3}");
		
		return atomicCoordinates;
		
	}


	public static void main(String[] args) {

		//sb = FileReader.reader("/Users/dias/cnam/projet-cnam/CalculDistancesPDB/doc/1fn3.cif");
		
		//print all content of the cif file 
		//System.out.println(sb.toString());
		
		Parser atomParser = new Parser();
		//System.out.println(atomParser.searchAtomsEntries(sb));
		
		//TEST avec le fichier txt des atomes : 
		String atomFile = "/home/karine/src/java/ProjetPDB/doc/atoms.txt";
		StringBuilder sb2 = new StringBuilder();
		sb2 = FileReader.reader(atomFile);
		System.out.println(sb2.toString());
		System.out.println(atomParser.searchAtomsEntries(sb2));
		//System.out.println(atomParser.searchAtomsEntries(sb));
		
		System.out.println(atomicCoordinatesParser(sb2));
	}
}
