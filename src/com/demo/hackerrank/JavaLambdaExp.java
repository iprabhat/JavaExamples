package com.demo.hackerrank;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

interface PerformOperation{
	boolean check(int n);
}

class MyMath {
	public static boolean checker(PerformOperation op, int n) {
		return op.check(n);
	}
	public static PerformOperation isOdd() {
		return n -> {
			return n % 2 == 0 ? false : true;
		};
	}
	
	public static PerformOperation isPrime() {
		return n -> {
			if(n < 2) return false;
			int sq = (int)Math.sqrt(n);
			for (int i = 2; i < sq; i+=2) {
				if(n % i == 0) return false;						
			}
			return true;
		};
	}
	
	public static PerformOperation isPalindrome() {
		return n-> {
			String s = Integer.toString(n);
			String r = new StringBuilder(Integer.toString(n)).reverse().toString();
			if(s.equals(r)) {
				return true;
			}
			return false;
		};
	}
	
}

public class JavaLambdaExp {
	public static void main(String[] args) throws NumberFormatException, IOException {
		MyMath ob = new MyMath();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		PerformOperation op;
		boolean ret = false;
		String ans = null;
		while (T-- > 0) {
			String s = br.readLine().trim();
			StringTokenizer st = new StringTokenizer(s);
			int ch = Integer.parseInt(st.nextToken());
			int num = Integer.parseInt(st.nextToken());
			if (ch == 1) {
				op = ob.isOdd();
				ret = ob.checker(op, num);
				ans = (ret) ? "ODD" : "EVEN";
			} else if (ch == 2) {
				op = ob.isPrime();
				ret = ob.checker(op, num);
				ans = (ret) ? "PRIME" : "COMPOSITE";
			} else if (ch == 3) {
				op = ob.isPalindrome();
				ret = ob.checker(op, num);
				ans = (ret) ? "PALINDROME" : "NOT PALINDROME";

			}
			System.out.println(ans);
		}
	}
}
