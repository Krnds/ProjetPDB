package main.fr.karinedias.exceptions;

/**
 * Custom Exception thrown when no Atom objects where found while parsing cif files
 * @author Karine Dias
 */

public class AtomNotFoundException extends Exception {

	public AtomNotFoundException(String message) {
		
		super(message);
	}
}
