package com.demo.hackerrank;

import java.util.BitSet;
import java.util.Scanner;

public class JavaBitSet {
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = scan.nextInt();
		int m = scan.nextInt();
		BitSet b1 = new BitSet(n);
		BitSet b2 = new BitSet(n);
		
		for (int i = 0; i < m; i++) {
			String op = scan.next();			
			if(op.equalsIgnoreCase("AND")) {
				int l = scan.nextInt();
				int r = scan.nextInt();
				if(l == 1 && r == 2) {
					b1.and(b2);
				}
				else if(l == 2 && r == 1) {
					b2.and(b1);
				}
			}
			else if(op.equalsIgnoreCase("OR")) {
				int l = scan.nextInt();
				int r = scan.nextInt();
				if(l == 1 && r == 2) {
					b1.or(b2);
				}
				else if(l == 2 && r == 1) {
					b2.or(b1);
				}
			}
			else if(op.equalsIgnoreCase("XOR")) {
				int l = scan.nextInt();
				int r = scan.nextInt();
				if(l == 1 && r == 2) {
					b1.xor(b2);
				}
				else if(l == 2 && r == 1) {
					b2.xor(b1);
				}
			}
			else if(op.equalsIgnoreCase("FLIP")) {
				int set = scan.nextInt();
				int idx = scan.nextInt();
				if(set == 1) {
					b1.flip(idx);
				}
				else if(set == 2) {
					b2.flip(idx);
				}
			}
			else if(op.equalsIgnoreCase("SET")) {
				int set = scan.nextInt();
				int idx = scan.nextInt();
				if(set == 1) {
					b1.set(idx);
				}
				else if(set == 2) {
					b2.set(idx);
				}				
			}
			System.out.println(b1.cardinality() + " " + b2.cardinality());
		}
	}
}
