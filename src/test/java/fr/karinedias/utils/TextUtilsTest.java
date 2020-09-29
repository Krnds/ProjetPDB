package fr.karinedias.utils;

import static fr.karinedias.utils.TextUtils.getStringBetween;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class TextUtilsTest {

	private final static String FROM = "->";
	private final static String TO = "<-";

	@Test
	@DisplayName("Should return an empty string when all the delimiters are missing")
	void getStringBetween_when_delimiters_are_not_here_should_return_empty_string() {

		String s = "123456789";
		String from = FROM;
		String to = TO;
		assertTrue(TextUtils.getStringBetween(s, from, to).equals(""));
		assertTrue(TextUtils.getStringBetween("randomtext", from, to).equals(""));
	}

	@Test
	@DisplayName("Should return an empty string when one of the two delimiters is missing")
	void getStringBetween_when_one_delimiter_is_missing_should_return_empty_string() {

		String s = "123456789<-";
		String from = FROM;
		String to = TO;
		assertTrue(TextUtils.getStringBetween(s, from, to).equals(""));
		assertTrue(TextUtils.getStringBetween("randomtext", from, to).equals(""));
	}

	@Test
	@DisplayName("Should return string between delimiters when delimiters are present")
	void getStringBetween_when_both_delimiters_are_here_should_return_expected_string() {

		String s = "Lorem ipsum -> dolor sit amet <-, consectetur adipiscing elit, sed do eiusmod tempor incididunt";
		String from = FROM;
		String to = TO;
		String expected = " dolor sit amet ";
		String actual = TextUtils.getStringBetween(s, from, to);
		assertEquals(actual, expected);
		assertThat(expected.length() == actual.length());
	}

	@Test
	@DisplayName("Should return string from file between delimiters when they're are present")
	void getStringBetween_when_given_file_should_return_expected_string() throws URISyntaxException, IOException {

		String testData = getStringFromFile("test-data/textutils_test/test.cif");
		String expected = getStringFromFile("test-data/textutils_test/result.cif");

		String actual = getStringBetween(testData, FROM, TO);

		assertEquals(expected, actual);
	}

	private String getStringFromFile(String filepath) throws URISyntaxException, IOException {
		Path path = Paths.get(getClass().getClassLoader().getResource(filepath).toURI());
		Stream<String> lines = Files.lines(path);
		String data = lines.collect(Collectors.joining("\n"));
		lines.close();
		return data;
	}

}
