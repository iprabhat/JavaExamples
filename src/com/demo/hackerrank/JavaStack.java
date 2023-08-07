package com.demo.hackerrank;

import java.util.LinkedList;
import java.util.Scanner;

public class JavaStack {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		LinkedList<Character> lst = new LinkedList<>();
//		lst.offerFirst("Hello");
//		lst.offerFirst("World");
//		lst.offerFirst("TEST");
//		lst.offerFirst("DATA");
		
//		while(!lst.isEmpty()) {
//			System.out.println(lst.pollFirst());
//		}
		
		
		while (sc.hasNext()) {
			String input = sc.next();
			char[] ch = input.toCharArray();
			for(char c : ch) {
				if(c == '[' || c == '{' || c == '(') {
					lst.offerFirst(c);
				}
				else if(c == ']' || c == '}' || c == ')') {
					if(!lst.isEmpty()) {
						Character p = lst.peekFirst();					
						if(c == ']' && p == '[') {
							lst.pollFirst();
						}
						else if(c == '}' && p == '{') {
							lst.pollFirst();
						}
						else if(c == ')' && p == '(') {
							lst.pollFirst();
						}
						else {
							break;
						}
					}
					else {
						lst.offerFirst(c);
					}
				}
			}
			if(lst.isEmpty()) {
				System.out.println("true");
			}
			else {
				System.out.println("false");
			}
			lst.clear();
		}
	}
}
