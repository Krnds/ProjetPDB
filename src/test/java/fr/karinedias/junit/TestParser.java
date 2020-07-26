package src.test.fr.karinedias.junit;

import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TestParser {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		//create file to test
		String fileTestPath = "/home/karine/src/java/ProjetPDB/doc/3qt2.cif";
		//FileReader fileTest = new FileReader();
		FileReader fileTest = new FileReader(fileTestPath);
		StringBuilder content = fileTest.reader();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@Test
	void test() {
		fail("Not yet implemented");
	}

}
