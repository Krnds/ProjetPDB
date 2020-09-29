package fr.karinedias.utils;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.junit.jupiter.api.Test;

public class FileReaderTest {

	@Test
	void reader_given_file_should_return_correct_string_builder() throws Exception {
		// Given
		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/test.cif").toURI());
		String atomLinePresent = "ATOM   11   C CA  . ILE A 1 10  ? 49.784  48.711  98.248  1.00 107.24 ? 8   ILE A CA  1";
		String atomLineAbsent = "ATOM   99   N CA  . ILE A 1 10  ? 49.784  48.711  98.248  1.00 107.24 ? 8   ILE A CA  1";
		FileReader fileReader = new FileReader(path.toString());

		// When
		StringBuilder stringBuilder = fileReader.reader();

		// Then
		assertFalse(stringBuilder.length() == 0);
		assertTrue(isLinePresent(stringBuilder, atomLinePresent));
		assertFalse(isLinePresent(stringBuilder, atomLineAbsent));
	}

	@Test
	void reader_given_unknown_file_should_return_NullPointerException() throws URISyntaxException {

		String filePath = "test-data/unknown.cif";
		@SuppressWarnings("unused")
		Path path;
		assertThrows(NullPointerException.class, () -> {
			new FileReader(Paths.get(getClass().getClassLoader().getResource(filePath).toURI()).toString());
		});
	}

	private boolean isLinePresent(StringBuilder stringBuilder, String line) {
		return stringBuilder.indexOf(line) > -1;
	}

}
