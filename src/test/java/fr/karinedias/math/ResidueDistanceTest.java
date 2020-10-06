package fr.karinedias.math;

import static fr.karinedias.math.ResidueDistanceCalculation.distanceBetweenResidues;
import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.vecmath.Point3d;

import org.junit.jupiter.api.Test;

import fr.karinedias.model.Residue;

public class ResidueDistanceTest {

	@Test
	void distanceBetweenResidues_when_2given3DPoints_should_return_distance_between_them() {

		Point3d test1 = new Point3d(5, 7, 12);
		Point3d test2 = new Point3d(14, 3, -2);
		double actualDistance = test1.distance(test2);

		double expectedDistance = distanceBetweenResidues(new Residue(340, "test1", 1, 1, 'A', 0, 5, 7, 12),
				new Residue(120, "test2", 2, 2, 'B', 0, 14, 3, -2));

		assertEquals(expectedDistance, actualDistance);

	}

	@Test
	void distanceBetweenResidues_when_null_coordinates_should_return_distance_between_them() {

		Point3d test1 = new Point3d(0, 0, 0);
		Point3d test2 = new Point3d(0, 0, 0);
		double actualDistance = test1.distance(test2);

		double expectedDistance = distanceBetweenResidues(new Residue(340, "test1", 1, 1, 'A', 0, 0, 0, 0),
				new Residue(120, "test2", 2, 2, 'B', 0, 0, 0, 0));

		assertEquals(expectedDistance, actualDistance);
	}

}