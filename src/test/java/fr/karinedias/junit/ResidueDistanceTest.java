package fr.karinedias.junit;

import fr.karinedias.math.ResidueDistanceCalculation;
import fr.karinedias.model.Residue;

import javax.vecmath.Point3d;

import org.junit.Assert;
import org.junit.Test;

/**
 * Beware, the javax is from the Java SE edition (13.0.1 used here) The external
 * .jar librairy was externally downloaded "apt-get install libvecmath-java" If
 * Maven will be used, put the vecmath dependancy in : <dependency>
 * <groupId>javax.vecmath</groupId> <artifactId>vecmath</artifactId>
 * <version>1.5.2</version> </dependency>
 * 
 * @see https://stackoverflow.com/questions/4381291/import-javax-vecmath
 * @author Karine Dias
 *
 */

//TODO: faire une class permettant de tester si le résultat de la classe ResidueDistanceCalculation donne les mêmes résultats qu'avec la librairie javafx 3dpoints
public class ResidueDistanceTest {

	@Test
	public void testResidueDistanceCalculation() {

		// Given
		Point3d test1 = new Point3d(5, 7, 12);
		Point3d test2 = new Point3d(14, 3, -2);
		double expectedDistance = test1.distance(test2);
		
		// When
		double actualDistance = ResidueDistanceCalculation.distanceBetween2ResiduesAlphaCarbon
				(new Residue("test1", 1, 'A', 0, 5, 7, 12), new Residue("test2", 2, 'B', 0, 14, 3, -2));
	
		// Then
		Assert.assertEquals(expectedDistance, actualDistance, 0);

	}

}
