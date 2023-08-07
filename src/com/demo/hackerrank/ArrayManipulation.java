package com.demo.hackerrank;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class ArrayManipulation {
	  // Complete the arrayManipulation function below.
    static long arrayManipulation(int n, int[][] queries) {
        long max = Long.MIN_VALUE;
        long [] arr = new long[n];
        for (int i = 0; i < queries.length; i++) {
			arr[queries[i][0] - 1] += queries[i][2];
			if(queries[i][1] < n) {
				arr[queries[i][1]] -= queries[i][2];
			}
		}
        
        long sum = 0;
        for (int i = 0; i < n; i++) {
			sum += arr[i];
			if(sum > max) {
				max = sum;
			}
		}
        
        return max;
    }
   
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new FileReader("input04_ArrayManipulation.txt"));
		String[] nm = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nm[0]);

        int m = Integer.parseInt(nm[1]);

        int[][] queries = new int[m][3];

        for (int i = 0; i < m; i++) {
            String[] queriesRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");

            for (int j = 0; j < 3; j++) {
                int queriesItem = Integer.parseInt(queriesRowItems[j]);
                queries[i][j] = queriesItem;
            }
        }

        long result = arrayManipulation(n, queries);
        System.out.println(result);
	}
   
}
