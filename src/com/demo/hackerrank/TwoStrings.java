package com.demo.hackerrank;

import java.util.HashSet;
import java.util.Iterator;

public class TwoStrings {
	public static String twoStrings(String s1, String s2) {
		HashSet<Character> h1 = new HashSet<>();
		HashSet<Character> h2 = new HashSet<>();
		
		for(char c : s1.toCharArray()) {
			h1.add(c);
		}
		for(char c : s2.toCharArray()) {
			h2.add(c);
		}
		
		for (Iterator itr = h1.iterator(); itr.hasNext();) {
			Character ch = (Character) itr.next();
			if(h2.contains(ch)) {
				return "YES";
			}
		}
		
		return "NO";
	}
	 
	public static void main(String[] args) {
		System.out.println(twoStrings("h1", "world"));

	}

}
