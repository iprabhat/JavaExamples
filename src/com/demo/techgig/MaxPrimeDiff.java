package com.demo.techgig;

import java.util.Scanner;

public class MaxPrimeDiff {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		while(n-- > 0) {
			int l = scan.nextInt();
			int r = scan.nextInt();
			int prime1 = 0;
			int prime2 = 0;
			for (int i = l; i <= r; i++) {
				boolean isprime = true;				
				if(i == 1) isprime = false;
				else if(i == 2) isprime = true;
				else {
					for (int j = 2; j <= Math.sqrt(i); j++) {
						 if(i % j == 0) {
							 isprime = false;
							 break;
						 }
					}
				}
				
				if(isprime) {
					if(prime1 == 0) prime1 = i;
					else {
						prime2 = i;	
					}
				}
			}
			
			if(prime1 == 0) {
				System.out.println(-1);
			}
			else if(prime2 == 0) {
				System.out.println(0);
			}
			else {
				System.out.println(Math.abs(prime2 - prime1));
			}
		}
		scan.close();
	}

}
