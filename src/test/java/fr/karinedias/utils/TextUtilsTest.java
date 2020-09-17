package fr.karinedias.utils;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class TextUtilsTest {

	@Test
	void getStringBetween_should_return_empty_string() {

		String s = "123456789";
		String from = "->";
		String to = "<-";
		assertTrue(TextUtils.getStringBetween(s, from, to).equals(""));
		assertTrue(TextUtils.getStringBetween("randomtext", from, to).equals(""));
	}

	@Test
	static void getStringBetween_should_return_expected() {

		String s = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.";
		String from = "sed";
		String to = "ut";
		String expected = " do eiusmod tempor incididunt ";
		String actual = TextUtils.getStringBetween(s, from, to);
		assertEquals(actual, expected);
		assertThat(expected.length() == actual.length());
	}

}
