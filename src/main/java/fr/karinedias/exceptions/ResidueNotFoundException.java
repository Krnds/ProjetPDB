package main.fr.karinedias.exceptions;

/**
 * Custom Exception thrown when no Residues where found while parsing cif file
 * 
 * @author Karine Dias
 */

@SuppressWarnings("serial")
public class ResidueNotFoundException extends Exception {

	public ResidueNotFoundException(String message) {

		super(message);
	}
}
