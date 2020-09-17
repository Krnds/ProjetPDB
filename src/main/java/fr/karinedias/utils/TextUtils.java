package fr.karinedias.utils;

public class TextUtils {

	public static String getStringBetween(String text, String from, String to) {
		String result = "";
		int indexFromBeginning = text.indexOf(from);
		if (indexFromBeginning < 0) {
			return "";
		}
		int indexFromEnd = indexFromBeginning + from.length();
		result = text.substring(indexFromEnd, text.length());
		int indexTo = result.indexOf(to);
		if (indexTo < 0) {
			return "";
		}
		result = result.substring(0, indexTo);
		return result;
	}
}
