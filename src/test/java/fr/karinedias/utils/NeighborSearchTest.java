package fr.karinedias.utils;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Molecule;
import fr.karinedias.model.Residue;

class NeighborSearchTest {

	@Test
	void getNeighborsFromMolecule_should_returnX() throws Exception {
		
		// Prepare data
		Path path = Paths.get(getClass().getClassLoader().getResource("test-data/test_moleculeParser.cif").toURI());
		FileReader fileTest = new FileReader(path.toString());
		StringBuilder content = fileTest.reader();
		MoleculeParser mp = new MoleculeParser(content);
		List<Molecule> molecules = new ArrayList<>();
		molecules.addAll(mp.getAllMolecules(mp.parseMoleculeLines()));
		
		// Set all residues to the molecules found
//		for (Molecule currentMolecule : molecules) {
//			List<Residue> listOfResidueOfCurrentMolecule = listOfResiduesWithoutNulls.stream()
//					.filter(residue -> residue.getChainNumber() == currentMolecule.getId())
//					.collect(Collectors.toList());
//			// Set all residues to the correct molecule
//			currentMolecule.setResidues(listOfResidueOfCurrentMolecule);
//		}
//		// 
//		List<Residue> expectedNeighbors = new ArrayList<>();
//		List<Double> expectedDistances = new ArrayList<>();


	}

}
