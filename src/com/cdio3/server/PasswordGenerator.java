package com.cdio3.server;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordGenerator
{
	private static Random rand = new Random();
	private static final String[] ALPHABET = {"0123456789", 
										      "abcdefghijklmnopqrstuvwxyz", 
										      "ABCDEFGHIJKLMNOPQRSTUVWXYZ", 
										      ".+-_?=!"};
	private static final int PASSWORD_MIN = 6;
	
	/**
	 * Generates a random password, matching the needed criteria, and with length 8
	 * @return The randomly generated password.
	 */
	public static String generatePassword()
	{	
		return generatePasswordRekursive(rand.nextInt(ALPHABET.length), PASSWORD_MIN + 2, "", 2);
	}
	
	/**
	 * Checks if the given password matches the criteria
	 * @param pass The password to check
	 * @return true if the password matches 3 of 4 categories, and has a length >= 6, false otherwise.
	 */
	public static boolean checkPassword(String pass)
	{
		
		//TODO: call to checkContainsData()
		boolean[] check = checkPW(pass);
		
		int passedCategories = 0;
		for (int i = 0; i < 4; i++)
			if(check[i])
				passedCategories++;
		
		if(passedCategories > 2 && check[4])
			return true;
		
		return false;
	}
	
	/**
	 * checks if the password matches the requirements, returns a boolean array
	 *  of length = 5 where:
	 * 0 = [0-9]
	 * 1 = [a-z]
	 * 2 = [A-Z]
	 * 3 = [\\+\\-_?=]
	 * 4 = length >= 6
	 * @param pw The password to check
	 * @return An array containing true for all passed test, and false for all failed ones.
	 */
	private static boolean[] checkPW(String pw)
	{
		boolean[] results = new boolean[5];
		Pattern[] patterns = new Pattern[4];
		
		//make a pattern for each category
		patterns[0] = Pattern.compile(".*\\d+.*");
		patterns[1] = Pattern.compile(".*[a-z]+.*");
		patterns[2] = Pattern.compile(".*[A-Z]+.*");
		patterns[3] = Pattern.compile(".*[\\+\\-_?=!\\.]+.*");
		
		//check against patterns and store results of check
		for (int i = 0; i < patterns.length; i++)
		{
			Matcher m = patterns[i].matcher(pw);
			results[i] = m.matches();
		}
		//check length of pw
		results[4] = pw.length() >= PASSWORD_MIN;
		
		return results;
	}
	
	/**
	 * 
	 * @param cat a number marking what category to pull from
	 * @param max the max length of the password
	 * @param pass the password anything added here will be appended to the start of the pass
	 * @param catCount number of categories that we still need to fill, minus the current one.
	 * @return A string containing a random password.
	 */
	private static String generatePasswordRekursive(int cat, int max, String pass, int catCount)
	{
		if(catCount > 0 || pass.length() < max)
		{
			int limit = max - pass.length() - (catCount);
			int random = rand.nextInt(limit + 1);
			if(random > 0)
			{
				for (int i = 0; i < random; i++)
					pass += ALPHABET[cat].charAt(rand.nextInt(ALPHABET[cat].length()));
				cat = (cat + 1) % 4;
				if(catCount != 0)
					catCount--;
			}
			pass = generatePasswordRekursive(cat, max, pass, catCount);
		}
		return pass;
	}
	
}
