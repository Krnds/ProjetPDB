package fr.karinedias.utils;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Molecule;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.MoleculeParser;

public class MoleculeParserTest {

	@Test
	public void moleculeParserTest() throws Exception {

//		1 polymer     man 'Molybdenum storage protein subunit beta'  28247.582 3   ? ? ? ? 
//				2 polymer     man 'Molybdenum storage protein subunit alpha' 29245.582 3   ? ? ? ? 
//				3 non-polymer syn "ADENOSINE-5'-DIPHOSPHATE"                 427.201   3   ? ? ? ? 
//				4 non-polymer syn 'PHOSPHATE ION'                            94.971    6   ? ? ? ? 
//				5 non-polymer syn 'SODIUM ION'                               22.990    1   ? ? ? ? 
//				6 non-polymer syn "ADENOSINE-5'-TRIPHOSPHATE"                507.181   3   ? ? ? ? 
//				7 non-polymer syn 'MAGNESIUM ION'                            24.305    4   ? ? ? ? 
//				8 water       nat water                                      18.015    798 ? ? ? ? 

		// create file to test
		String fileTestPath = "/home/karine/src/java/ProjetPDB_old/src/main/resources/data/6rj4.cif";
		// FileReader fileTest = new FileReader();
		FileReader fileTest = new FileReader(fileTestPath);
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
		// assertEquals(expectedMolecules, actualMolecules);
		// assertTrue(Iterables.elementsEqual(argumentComponents, returnedComponents));
//		Assert.assertThat(actualMolecules, 
//			       IsIterableContainingInOrder.contains(expectedMolecules.toArray()));
		Object[] expectedArray = expectedMolecules.toArray();
		Object[] actualArray = actualMolecules.toArray();
		assertArrayEquals(expectedArray, actualArray);

	}

}
