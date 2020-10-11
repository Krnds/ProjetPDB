package fr.karinedias.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Chain;

public class ChainParserTest {

	@Test
	public void moleculeParserTest() throws Exception {

		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/test_chainParser.cif").toURI());

		FileReader fileTest = new FileReader(path.toString());
		StringBuilder content = fileTest.reader();
		ChainParser cp = new ChainParser(content);
		// Given
		List<Chain> expectedChains = new ArrayList<>();
		expectedChains.add(new Chain('B', 1));
		expectedChains.add(new Chain('D', 1));
		expectedChains.add(new Chain('F', 1));
		expectedChains.add(new Chain('A', 2));
		expectedChains.add(new Chain('C', 2));
		expectedChains.add(new Chain('E', 2));

		// When
		List<Chain> actualMolecules = new ArrayList<>();
//		actualMolecules.addAll(cp.getAllMolecules(cp.getChains(mol)));
//
//		// Then
//		assertTrue(expectedMolecules.size() == actualMolecules.size());
//		assertNotNull(actualMolecules);
//		assertEquals(expectedMolecules, actualMolecules);

	}
}
