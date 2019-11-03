package main.fr.karinedias.utils;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AtomReader extends FileReader {

	private StringBuilder pdbfile;

	public static void atomReader(String pdbFile) {

//		Pattern atomPattern = Pattern.compile("ATOM");
//		Matcher atomLines = atomPattern.matcher(pdbFile);
//		while (atomLines.find()) {
//		    System.out.println(atomLines.toString());
//		}
		
//		try {
//	                 
//	            for(int i = 0; i < pdbFile.length(); i++){
//	                StringBuilder lines = FileReader.reader(pdbFile);
//	                if(lines.{
//	                    System.out.println(lines);      
//	                }
//	        }
//
//	    } catch (Exception e) {
//
//	        e.printStackTrace();
//	    }
	}

	public static void atomWriterToFile() {
		/*
		 * TODO: write automatically a file with all the "ATOM" entries to be parsed
		 * later
		 */
	}
	
	/*
	 * TEST
	 */
	public static void main(String[] args) throws IOException{
		StringBuilder sb = FileReader.reader("/home/karine/src/java/ProjetPDB/doc/6hk2.cif");
		atomReader(sb);
	}
}
