package com.demo.hackerrank;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class SherlockAndAnagrams {
	public static int sherlockAndAnagrams(String str) {
		int total = 0;
		
		
		for (int i = 1; i < str.length(); ++i) {
			int[] tmpstr = new int[26];

			for (int j = i; j >= 0; --j) {
				tmpstr[str.charAt(j) - 'a']++;

				for (int k = 0; k < j; ++k) {
					int[] chars = new int[26];
					int x = k;
					int count = 0;
					while (count <= i - j) {
						++chars[str.charAt(x) - 'a'];
						++x;
						++count;
					}
					boolean flag = true;
					for (x = 0; x < 26; ++x) {
						if (tmpstr[x] != chars[x]) {
							flag = false;
							break;
						}
					}
					if (flag)
						++total;
				}
			}
		}
		return total;
	}
	
	public static int sherlockAndAnagrams2(String str) {
		int total = 0;
		char[] arr = str.toCharArray();
		HashMap<String, Integer> m = new HashMap<String, Integer>();
		int l = str.length();
		for (int i = 1; i <= l; i++) {
			for (int j = 0; j <= l - i; j++) {
				char [] subArr = Arrays.copyOfRange(arr, j, j + i);
				Arrays.sort(subArr);
				String s = new String(subArr);
				if(m.containsKey(s)) {
					m.put(s, m.get(s) + 1);
				}
				else {
					m.put(s, 1);
				}
			}
		}
		
		for (Entry<String, Integer> e: m.entrySet()) {
			Integer value = e.getValue();
			for (int i = 0; i < e.getValue(); i++) {
				total += i;
			}
		}		
		return total;
	}

	public static void main(String[] args) {
		String s = "ifailuhkqq"; //
		//System.out.println(sherlockAndAnagrams(s));
		// System.out.println((char)65);
		String data = "abba";
		System.out.println(sherlockAndAnagrams2(data));
		
		Integer [] t = {1,2,3,4,5};
		List<Integer> asList = Arrays.asList(t);
		System.out.println();
		
		Integer[] spam = new Integer[] { 1, 2, 3 };
		List<Integer> list = Arrays.asList(spam);
	}

}
