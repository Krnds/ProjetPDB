package fr.karinedias.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Residue;

public class ResidueParserTest {

	@Test
	public void getAllResidues_should_return_expected_residues() throws Exception {

		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/test_residueParser.cif").toURI());

		FileReader fileTest = new FileReader(path.toString());
		StringBuilder content = fileTest.reader();
		ResidueParser rp = new ResidueParser(content);

		// Given
		List<Residue> expectedResidues = new ArrayList<>();

		expectedResidues.add(new Residue(2, "ALA", 1, 2, 'B', 1, 84.548, -13.576, -50.492));
		expectedResidues.add(new Residue(7, "ASN", 2, 3, 'B', 1, 87.546, -13.117, -48.151));
		expectedResidues.add(new Residue(15, "SER", 3, 4, 'B', 1, 88.574, -15.759, -45.575));
		expectedResidues.add(new Residue(21, "THR", 4, 5, 'B', 1, 87.517, -17.495, -42.375));

		// When
		List<Residue> actualResidues = new ArrayList<>();
		actualResidues.addAll(rp.getAllResidues());

		// Then
		assertEquals(expectedResidues, actualResidues);

	}
}
