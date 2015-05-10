package com.cdio3.shared;

import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

public class FieldVerifier {

	// We use this online regex tool to test patterns https://regex101.com/

	/**
	 * These variables define the limits and patterns that our input should
	 * conform to.
	 */

	private static final RegExp NAME_PATTERN = RegExp.compile("^[a-zA-ZæøåÆØÅ -]{2,20}$");
	private static final RegExp INI_PATTERN = RegExp.compile("^[a-zA-ZæøåÆØÅ]{2,3}$");
	private static final RegExp CPR_PATTERN = RegExp.compile("^\\d{10}$");

	private static final int PASSWORD_MIN = 7;
	private static final int PASSWORD_MAX = 8;

	public static boolean isValidName(String name) {
		if (name == null)
			return false;
		return NAME_PATTERN.exec(name) != null;
	}

	public static boolean isValidCPR(String cpr) {
		if (cpr == null)
			return false;
		return CPR_PATTERN.exec(cpr) != null;
	}

	public static boolean isValidInitials(String ini) {
		if (ini == null)
			return false;
		return INI_PATTERN.exec(ini) != null;
	}

	public static boolean isValidPassword(String password) {
		boolean[] check = checkPW(password);

		int passedCategories = 0;
		for (int i = 0; i < 4; i++)
			if (check[i])
				passedCategories++;

		if (passedCategories > 2 && check[4])
			return true;

		return false;
	}

	private static boolean[] checkPW(String pw) {
		boolean[] results = new boolean[5];
		RegExp[] patterns = new RegExp[4];

		// make a pattern for each category
		patterns[0] = RegExp.compile(".*\\d+.*");
		patterns[1] = RegExp.compile(".*[a-z]+.*");
		patterns[2] = RegExp.compile(".*[A-Z]+.*");
		patterns[3] = RegExp.compile(".*[\\+\\-_?=!\\.]+.*");

		// check against patterns and store results of check
		for (int i = 0; i < patterns.length; i++) {
			MatchResult m = patterns[i].exec(pw);
			results[i] = m != null;
		}
		// check length of pw
		results[4] = pw.length() >= PASSWORD_MIN && pw.length() <= PASSWORD_MAX;

		return results;
	}
}
