package test.fr.karinedias.junit;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import junit.framework.Assert;

class FirstTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
	}

	@BeforeEach
	void setUp() throws Exception {
	}

	@AfterEach
	void tearDown() throws Exception {
	}

	@Test
	void testReaderTest() {
		
		String stringToTest = "";
		String resultOftestReaderTest = "";
		Assert.assertEquals(stringToTest, resultOftestReaderTest);
		
	}

	@Test
	void testReaderTest2() {
		fail("Not yet implemented");
	}

	@Test
	void testReaderTest3() {
		fail("Not yet implemented");
	}

	@Test
	void testReader() {
		fail("Not yet implemented");
	}

	@Test
	void testToString() {
		fail("Not yet implemented");
	}

}
