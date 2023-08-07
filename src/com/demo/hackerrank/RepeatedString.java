package com.demo.hackerrank;

public class RepeatedString {

	public static long repeatedString(String s, long n) {
		long num = 0;
		char[] arr = s.toCharArray();
		int arrLen = arr.length;
		if(arrLen == 1 && arr[0] == 'a') {
			return n;
		}
		else {
			//find num of a's in string s
			for(char c : arr) {
				if(c == 'a') {
					num += 1;
				}
			}			
			long mul =  n / arrLen;
			long mod = n % arrLen;
			num *= mul;
			
			long l = arrLen * mul;
			for(int i = 0; i < mod; i++) {
				if(arr[i] == 'a') {
					num += 1;
				}
			}			
		}
		return num;
	}
	
	
	public static void main(String[] args) {
		System.out.println(repeatedString("abcac", 10));

	}

}
