package com.demo.hackerrank;

import java.text.NumberFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class HackerRank {

	public static void main(String[] args) {
//		Scanner in = new Scanner(System.in);
//		int t = in.nextInt();
//		for (int i = 0; i < t; i++) {
//			int a = in.nextInt();
//			int b = in.nextInt();
//			int n = in.nextInt();			
//			for (int x = 0; x < n; x++) {
//				int s = a;
//				for (int y = 0; y <= x; y++) {
//					s += Math.pow(2, y) * b;
//				}
//				System.out.print(s + " ");
//			}
//			System.out.println();
//		}
//		in.close();

		//------- Number Formatter-------
//		 NumberFormat nfUS = NumberFormat.getCurrencyInstance(Locale.US);
//		 Locale locIn = new Locale("en", "IN");		 		
//		 NumberFormat nfIN = NumberFormat.getCurrencyInstance(locIn);
//		 NumberFormat nfCH = NumberFormat.getCurrencyInstance(Locale.CHINA);
//		 NumberFormat nfFR = NumberFormat.getCurrencyInstance(Locale.FRANCE);
//		 
//		 System.out.println(nfIN.format(100.25));
//		 String s1 = "Java";
//		 String s2 = "Hello";
//		 System.out.println(s1.compareTo(s2));		 
		//------- Number Formatter-------
		
		// ------ Palindrome ---------
//		String s = "ABCCBA";
//		char[] arr = s.toCharArray();
//		int l = arr.length;
//		String isPalindrome = "Yes";
//		for (int i = 0; i < l; i++) {
//			if((i != (l-i-1)) &&  arr[i] != arr[l-i-1]) {
//				isPalindrome = "No";
//				break;
//			}
//		}
//		System.out.println(isPalindrome);
		//------ Palindrome ---------
		
		// ------- Anagrams -------
		Map<Character, Integer> map1 = new HashMap<>();	
		Map<Character, Integer> map2 = new HashMap<>();
		char[] arr1 = "Hello".toLowerCase().toCharArray();
		char[] arr2 = "hleol".toLowerCase().toCharArray();
		for (int i = 0; i < arr1.length; i++) {
			if(map1.containsKey(arr1[i])) {
				Integer v = map1.get(arr1[i]);
				map1.put(arr1[i], v+1);
			}
			else {
				map1.put(arr1[i], 1);
			}
		}
		System.out.println(map1);
		for (int i = 0; i < arr2.length; i++) {
			if(map2.containsKey(arr2[i])) {
				Integer v = map2.get(arr2[i]);
				map2.put(arr2[i], v+1);
			}
			else {
				map2.put(arr2[i], 1);
			}
		}
		
		System.out.println(map2);
		
		String isAnagram = "Yes";
//		Iterator<Character> itr = map1.keySet().iterator();
//		while(itr.hasNext()) {
//			Character ch = itr.next();
//			if(map2.containsKey(ch)) {
//				if(map1.get(ch) != map2.get(ch)) {
//					isAnagram = "No";
//					break;
//				}				
//			}
//			else {
//				isAnagram = "No";
//				break;
//			}
//		}
		// --- Alternative way ------
		for (int i = 0; i < arr1.length; i++) {
			char c = arr1[i];
			int s1 = 0;
			int s2 = 0;
			for (int j = 0; j < arr1.length; j++) {
				if(c == arr1[j]) {
					s1++;
				}
			}
			for (int j = 0; j < arr2.length; j++) {
				if(c == arr2[j]) {
					s2++;
				}
			}
			if(s1 != s2) {
				isAnagram = "No";
				break;
			}
		}		
		System.out.println("isAnagram : " + isAnagram);
		// --- Alternative way ------
		// ------- Anagrams -------
	}

}
