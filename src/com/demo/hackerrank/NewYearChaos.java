package com.demo.hackerrank;

import java.util.Iterator;

public class NewYearChaos {

	static void minimumBribes(int[] q) {
		int bribeCount = 0;
		int [] arr = new int[q.length];
		for (int i = 0; i < q.length; i++) {
			arr[i]  = i + 1;
		}
		
		for (int i = 0; i < arr.length; i++) {
			if(arr[i] != q[i]) {
				if(arr[i+1] == q[i]) {
					// swap once
					int temp  = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
					
					bribeCount += 1;
				}
				else if (arr[i+2] == q[i]) {
					// 1st swap
					int temp  = arr[i+2];
					arr[i+2] = arr[i+1];
					arr[i+1] = temp;
					
					// 2nd Swap
					temp  = arr[i+1];
					arr[i+1] = arr[i];
					arr[i] = temp;
					
					bribeCount += 2;
				}
				else {
					System.out.println("Too chaotic");
					return;
				}
			}
		}		
		System.out.println(bribeCount);
	}

	public static void main(String[] args) {
		//int [] arr = {2, 1, 5, 3, 4};
		//int [] arr = {5, 1, 2, 3, 7, 8, 6, 4};
		//			  1, 2, 3, 4, 5, 6, 7, 8
		int [] arr = {1, 2, 5, 3, 7, 8, 6, 4};
		minimumBribes(arr);
	}
}
