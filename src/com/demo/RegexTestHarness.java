package com.demo;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexTestHarness {

	public static void main(String[] args) {
		//Console console = System.console();
		
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			boolean loopStat = true;
			while(loopStat) {
				System.out.println();
				System.out.println("Enter your regex (:q to quit): ");
				String p = reader.readLine();
				if(!p.equalsIgnoreCase(":q")) {
					Pattern pattern = Pattern.compile(p);
					System.out.print("Enter input string to search: ");
					Matcher matcher = pattern.matcher(reader.readLine());
					boolean found = false;
					while (matcher.find()) {
						System.out.printf("I found the text" +
								" \"%s\" starting at " +
								"index %d and ending at index %d.%n",
								matcher.group(),
								matcher.start(),
								matcher.end());
						found = true;
					}
					if(!found){
						System.out.println("No match found.");
					}
				}
				else {
					System.out.println("Program exits");
					loopStat = false;
				}
			}
			reader.close();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			
		}
	}
}
