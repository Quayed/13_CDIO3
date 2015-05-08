package com.cdio3.shared;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
	private static int INITIALS_MAX = 3;
	private static Pattern CPR_PATTERN = Pattern.compile("\\d{6}-\\d{4}");
	
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
		return name.length() > 3;
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
		Matcher m = CPR_PATTERN.matcher(cpr);
		return m.matches();
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
		return init.length() > INITIALS_MAX;
	}
	
	public static boolean isValidPassword(String password){
		return PasswordGenerator.checkPassword(password);
	}
}
