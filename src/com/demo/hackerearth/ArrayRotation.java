package com.demo.hackerearth;

import java.util.Scanner;

public class ArrayRotation {

	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();
		while (n-- > 0) {
			int l = s.nextInt();
			int r = s.nextInt();

			int[] arr = new int[l];
			for (int i = 0; i < l; i++) {
				arr[i] = s.nextInt();
			}
			// [1,2,3,4,5]
			// rotate
			int m = r % l;

			for (int i = 0; i < arr.length; i++) {
				System.out.print( arr[(i + (l-m)) % l ] + " ");
			}

			System.out.println();
		}
	}

}
