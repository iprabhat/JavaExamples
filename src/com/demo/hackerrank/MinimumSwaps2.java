package com.demo.hackerrank;

public class MinimumSwaps2 {
	// Complete the minimumSwaps function below.
	static int minimumSwaps(int[] arr) {
		int minSwap = 0;
		for (int i = 0; i < arr.length; i++) {
			while(arr[i] != (i+1)) {
				int temp = arr[arr[i] - 1];
				arr[arr[i] - 1] = arr[i];
				arr[i] = temp;
				
				minSwap += 1;
			}
		}
		return minSwap;
	}

	public static void main(String[] args) {
		int [] arr = { 7, 1, 3, 2, 4, 5, 6 };
//		int [] arr = {4, 3, 1, 2}; 
		System.out.println(minimumSwaps(arr));

	}
}
