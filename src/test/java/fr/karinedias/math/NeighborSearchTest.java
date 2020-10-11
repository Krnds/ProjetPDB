package fr.karinedias.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Complex;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;
import fr.karinedias.utils.FileReader;
import fr.karinedias.utils.MoleculeParser;
import fr.karinedias.utils.ResidueParser;

class NeighborSearchTest {

	@Test
	void getNeighborsResiduesTest() throws URISyntaxException {
		// Given
		List<Residue> expectedResidues = new ArrayList<>();
		expectedResidues.add(new Residue(2, "VAL", 1, 1, 'A', 1, 43.152, 19.932, 2.662));
		expectedResidues.add(new Residue(9, "LEU", 2, 2, 'A', 1, 43.289, 22.947, 0.349));
		expectedResidues.add(new Residue(17, "SER", 3, 3, 'A', 1, 43.993, 22.273, -3.337));
		expectedResidues.add(new Residue(23, "PRO", 4, 4, 'A', 1, 45.820, 24.813, -5.510));
		expectedResidues.add(new Residue(30, "ALA", 5, 5, 'A', 1, 42.457, 25.083, -7.173));
		expectedResidues.add(new Residue(35, "ASP", 6, 6, 'A', 1, 41.279, 27.281, -4.320));
		expectedResidues.add(new Residue(43, "LYS", 7, 7, 'A', 1, 44.711, 28.798, -3.618));
		expectedResidues.add(new Residue(52, "THR", 8, 8, 'A', 1, 45.085, 30.328, -7.077));
		expectedResidues.add(new Residue(59, "ASN", 9, 9, 'A', 1, 41.313, 30.776, -7.245));
		expectedResidues.add(new Residue(67, "VAL", 10, 10, 'A', 1, 41.216, 33.253, -4.385));

		List<Molecule> expectedMolecule =new ArrayList<>(); 
		expectedMolecule.add(new Molecule(1, "Molecule Test1", "polymer", expectedResidues));
		Complex expectedComplex = new Complex("TEST", expectedMolecule);
		Residue residueToCompare = new Residue(1110, "GLU", 6, 6, 'B', 2, 9.439, 48.049, 8.826);
		List<Residue> expected = new ArrayList<>();
		expected = NeighborSearch.getNeighborsResidues(expectedComplex, residueToCompare, 40);

		// When
		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/fakemolecule.cif").toURI());
		FileReader fileTest = new FileReader(path.toString());
		StringBuilder content = fileTest.reader();
		MoleculeParser mp = new MoleculeParser(content);
		// get first molecule and create complex with that molecule
		List<Molecule> actualMolecule = new ArrayList<>();
		actualMolecule.add(mp.getAllMolecules(mp.parseMoleculeLines()).get(0));
		Complex actualComplex = new Complex("TEST", actualMolecule);
		ResidueParser rp = new ResidueParser(content);

		List<Residue> actualResiduesFound = rp.getAllResidues().stream()
				.filter(residue -> residue.getChainNumber() == actualMolecule.get(0).getId()).collect(Collectors.toList());
		// Set all residues to the correct molecule
		actualMolecule.get(0).setResidues(actualResiduesFound);

		List<Residue> actual = new ArrayList<>();
		actual = NeighborSearch.getNeighborsResidues(actualComplex, residueToCompare, 40);

		// Then
		assertEquals(expected.size(), actual.size());
		assertEquals(expectedResidues, actualResiduesFound);

	}

}