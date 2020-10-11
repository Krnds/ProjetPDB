package fr.karinedias.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Molecule;

public class MoleculeParserTest {

	@Test
	public void getAllMolecules_should_return_expected_molecules() throws Exception {

		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/test_moleculeParser.cif").toURI());

		FileReader fileTest = new FileReader(path.toString());
		StringBuilder content = fileTest.reader();
		MoleculeParser mp = new MoleculeParser(content);
		// Given
		List<Molecule> expectedMolecules = new ArrayList<>();
		expectedMolecules.add(new Molecule(1, "'Molybdenum storage protein subunit beta'", "polymer"));
		expectedMolecules.add(new Molecule(2, "'Molybdenum storage protein subunit alpha'", "polymer"));

		// When
		List<Molecule> actualMolecules = new ArrayList<>();
		actualMolecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));

		// Then
		assertTrue(expectedMolecules.size() == actualMolecules.size());
		assertNotNull(actualMolecules);
		assertEquals(expectedMolecules, actualMolecules);

	}

}