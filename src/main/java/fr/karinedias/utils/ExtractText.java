package fr.karinedias.utils;

public class ExtractText {

	protected static String extract(String text, String textFrom, String textTo) {

		String result = "";
		// Cut the beginning of the text to not occasionally meet a
		// 'textTo' value in it:
		result = text.substring(text.indexOf(textFrom) + textFrom.length(), text.length());
		if (result.isEmpty()) {
			return result;
		}
		// Cut the excessive ending of the text:
		result = result.substring(0, result.indexOf(textTo));
		return result;
	}

}