package fr.karinedias.junit;

import fr.karinedias.model.Residue;

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
public class TestDistance3DPoints {

	public double testResidueDistanceCalculation(Residue res1, Residue res2) {

		// TODO: how to have access of getters of residues ?

		return 0;

	}

	public static void main(String[] args) {
		// Point3d residue1 = new Point3d(5, 7, 3);

	}
}
