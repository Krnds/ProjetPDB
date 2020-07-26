package fr.karinedias.junit;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import fr.karinedias.utils.FileReader;

public class FileReaderTest {

	@Test
	public void reader_givenFilename_shouldReturnCorrectStringBuilder() throws Exception {
		// Given
		String fileName = "test-data/test.cif";
		String atomLinePresent = "ATOM   11   C CA  . ILE A 1 10  ? 49.784  48.711  98.248  1.00 107.24 ? 8   ILE A CA  1";
		String atomLineAbsent = "ATOM   99   N CA  . ILE A 1 10  ? 49.784  48.711  98.248  1.00 107.24 ? 8   ILE A CA  1";
		FileReader fileReader = new FileReader(fileName);

		// When
		StringBuilder stringBuilder = fileReader.reader();

		// Then
		assertFalse(stringBuilder.length() == 0);
		assertTrue(isLinePresent(stringBuilder, atomLinePresent));
		assertFalse(isLinePresent(stringBuilder, atomLineAbsent));
	}

	private boolean isLinePresent(StringBuilder stringBuilder, String line) {
		return stringBuilder.indexOf(line) > -1;
	}
	
}
