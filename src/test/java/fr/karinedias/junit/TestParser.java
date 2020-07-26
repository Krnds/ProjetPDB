package fr.karinedias.junit;

import static org.junit.Assert.fail;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import fr.karinedias.utils.FileReader;

class TestParser {

	@BeforeClass
	static void setUpBeforeClass() throws Exception {
		//create file to test
		String fileTestPath = "/home/karine/src/java/ProjetPDB/doc/3qt2.cif";
		//FileReader fileTest = new FileReader();
		FileReader fileTest = new FileReader(fileTestPath);
		StringBuilder content = fileTest.reader();
	}

	@AfterClass
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented.");
	}

}
