package fr.karinedias.model;

public enum AminoAcidCode {
	
	   glycine("GLY", 'G'),
	   alanine("ALA", 'A'),
	   valine("VAL", 'V'),
	   leucine("LEU",'L'),
	   isoleucine("ILE",'I'),
	   phenylalanine("PHE",'F'),
	   serine("SER",'S'),
	   threonine("THR",'T'),
	   tyrosine("TYR",'Y'),
	   cysteine("CYS",'C'),
	   methionine("MET",'M'),
	   proline("PRO",'P'),
	   lysine("LYS",'K'),
	   histidine("HIS",'H'),
	   tryptophan("TRP",'W'),
	   arginine("ARG",'R'),
	   asparagine("ASN",'N'),
	   glutamine("GLN",'Q'),
	   Aspartic("ASP",'D'),
	   glutamic("GLU",'E');
	
	private String code;
	private char shortCode;
	
	/*
	 * Constructor :
	 */
	AminoAcidCode (String threeLettersCode, char oneLetterCode) {
		this.code = threeLettersCode;
		this.shortCode = oneLetterCode;
		
	}
}