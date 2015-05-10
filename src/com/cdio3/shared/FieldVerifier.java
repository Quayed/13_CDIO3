package com.cdio3.shared;


import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;

/**
 * <p>
 * FieldVerifier validates that the name the user enters is valid.
 * </p>
 * <p>
 * This class is in the <code>shared</code> package because we use it in both
 * the client code and on the server. On the client, we verify that the name is
 * valid before sending an RPC request so the user doesn't have to wait for a
 * network round trip to get feedback. On the server, we verify that the name is
 * correct to ensure that the input is correct regardless of where the RPC
 * originates.
 * </p>
 * <p>
 * When creating a class that is used on both the client and the server, be sure
 * that all code is translatable and does not use native JavaScript. Code that
 * is not translatable (such as code that interacts with a database or the file
 * system) cannot be compiled into client-side JavaScript. Code that uses native
 * JavaScript (such as Widgets) cannot be run on the server.
 * </p>
 */
public class FieldVerifier
{

	
	//The regex tester I use for all my regex: https://regex101.com/ -Magnus
	/**
	 * These variables define the limits and patterns that our input should conform to. 
	 */
	private static final int NAME_MIN = 2;
	private static final int NAME_MAX = 20;
	
	private static final int INI_MIN = 2;
	private static final int INI_MAX = 3;
	
	private static final RegExp CPR_PATTERN = RegExp.compile("^\\d{10}$");
	
	private static final int PASSWORD_MIN = 7;
	private static final int PASSWORD_MAX = 8;
	
	/**
	 * Verifies that the specified name is valid for our service.
	 * 
	 * In this example, we only require that the name is at least four
	 * characters. In your application, you can use more complex checks to ensure
	 * that usernames, passwords, email addresses, URLs, and other fields have the
	 * proper syntax.
	 * 
	 * @param name the name to validate
	 * @return true if valid, false if invalid
	 */
	public static boolean isValidName(String name)
	{
		if (name == null)
			return false;
		return name.length() >= NAME_MIN && name.length() <= NAME_MAX;
	}
	
	/**
	 * Check that the cpr matches the expected pattern, 
	 * a series of 6 numbers, folowed by a "-" folowed by 4 more numbers.
	 * @param cpr The cpr-String
	 * @return True if the pattern matches, false otherwise.
	 */
	public static boolean isValidCPR(String cpr)
	{
		if(cpr == null)
			return false;
		MatchResult m = CPR_PATTERN.exec(cpr);
		return m != null;
	}
	/**
	 * checks that the initials is between 2 and 3 chars long
	 * @param init
	 * @return
	 */
	public static boolean isValidInitials(String init)
	{
		if(init == null)
			return false;
		return init.length() >= INI_MIN && init.length() <= INI_MAX;
	}
	
	public static boolean isValidPassword(String password){
		//TODO: call to checkContainsData()
		boolean[] check = checkPW(password);
		
		int passedCategories = 0;
		for (int i = 0; i < 4; i++)
			if(check[i])
				passedCategories++;
		
		if(passedCategories > 2 && check[4])
			return true;
		
		return false;
	}
	
	private static boolean[] checkPW(String pw)
	{
		boolean[] results = new boolean[5];
		RegExp[] patterns = new RegExp[4];
		
		//make a pattern for each category
		patterns[0] = RegExp.compile(".*\\d+.*");
		patterns[1] = RegExp.compile(".*[a-z]+.*");
		patterns[2] = RegExp.compile(".*[A-Z]+.*");
		patterns[3] = RegExp.compile(".*[\\+\\-_?=!\\.]+.*");
		
		//check against patterns and store results of check
		for (int i = 0; i < patterns.length; i++)
		{
			MatchResult m = patterns[i].exec(pw);
			results[i] = m != null;
		}
		//check length of pw
		results[4] = pw.length() >= PASSWORD_MIN && pw.length() <= PASSWORD_MAX;
		
		return results;
	}
}
