package com.demo.hackerrank;

import java.util.HashMap;

public class HashTablesRansomNote {
	
	static void checkMagazine(String[] magazine, String[] note) {
		HashMap<String, Integer> h = new HashMap<String, Integer>();
		for (String s : magazine) {
			if(h.containsKey(s)) {
				Integer i = h.get(s);
				h.put(s, i+1);
			}
			else {
				h.put(s, 1);
			}
		}
		
		for (String s : note) {
			if(h.containsKey(s)) {
				Integer i = h.get(s);
				if(i > 0) {
					h.put(s, i - 1);
				}
				else {
					System.out.println("No");
					return;
				}
			}
			else {
				System.out.println("No");
				return;
			}
		}
		
		System.out.println("Yes");

    }
	public static void main(String[] args) {
		String [] magazine = {"ive", "got", "a", "lovely", "bunch","Some", "of", "coconuts"};
		String [] note = {"ive", "got", "some", "coconuts"};
		
		checkMagazine(magazine, note);
	}

}
