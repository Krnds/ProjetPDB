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
	public void moleculeParserTest() throws Exception {

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
		// assertions for molecule id
		assertEquals(expectedMolecules.get(0).getId(), actualMolecules.get(0).getId());
		assertEquals(expectedMolecules.get(1).getId(), actualMolecules.get(1).getId());
		// assertions for molecule description
		assertEquals(expectedMolecules.get(0).getDescription(), actualMolecules.get(0).getDescription());
		assertEquals(expectedMolecules.get(1).getDescription(), actualMolecules.get(1).getDescription());
		// assertions for molecule type
		assertEquals(expectedMolecules.get(0).getType(), actualMolecules.get(0).getType());
		assertEquals(expectedMolecules.get(1).getType(), actualMolecules.get(1).getType());

		assertEquals(expectedMolecules, actualMolecules);

	}

}