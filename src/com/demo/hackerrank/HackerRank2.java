package com.demo.hackerrank;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.regex.Pattern;

public class HackerRank2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub		
		//------ String Tokens -------
//		String str = "                        ".trim();
//		if(!str.trim().equalsIgnoreCase("")){
//            String[] arrStr = str.trim().split("[ !,?._'@]+");
//            System.out.println(arrStr.length);
//            for (int i = 0; i < arrStr.length; i++) {
//                System.out.println(arrStr[i]);
//            }
//        }
//        else{
//            System.out.println(0);
//        }
		//------ String Tokens -------
		
		// ---- Pattern Syntax Checker ----
//		String pattern = "batcatpat(nat";
//		try {
//			Pattern.compile(pattern);
//			System.out.println("Valid");
//		}
//		catch (Exception e) {
//			System.out.println("Invalid");
//		}
		// ---- Pattern Syntax Checker ----
		
		
		// ----- Java Regex -----
		//String ipPattern = "^[0-255]\\.[0-255]\\.[0-255]\\.[0-255]$";
//		String ipPattern = "( 25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]? )\\.( 25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]? )\\.( 25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]? )\\.( 25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]? )";
//		String ipAddress= "192.168.1.1";
//		Pattern pattern = Pattern.compile(ipPattern);
//		System.out.println(pattern.matcher(ipAddress).matches());		
		// ----- Java Regex -----
		
		// ----- Java Regex 2 - Duplicate Words -----
		
		// ----- Java Regex 2 - Duplicate Words -----
		
		
		// ----- Java Regex - Valid username ------
		// Desc : Starts with alphabet, length from 8 to 30, contains alphanumeric and underscore(_)
//		String usernamePattern = "^[a-zA-Z][a-zA-Z_0-9]{7,29}$";
//		String [] usernames = {"Julia","Samantha","Samantha_21","1Samantha","Samantha?10_2A",
//				"JuliaZ007","Julia@007","_Julia007"};
//		Pattern pattern = Pattern.compile(usernamePattern);
//		for(String s : usernames) {
//			System.out.println(s + " : " + pattern.matcher(s).matches());
//		}
		// ----- Java Regex - Valid username ------
		
		// ----- Java Primality Test -----
//		BigInteger bigInt = new BigInteger("31");
//		bigInt.isProbablePrime(1);
		// ----- Java Primality Test -----
		
		
		// ----- Java BigDecimal -----
//		String [] s = {"-100","50","0","56.6","90","0.12",".12","02.34","000.000"};
//		Arrays.sort(s, 0 ,s.length, new Comparator<Object>() {
//			@Override
//			public int compare(Object o1, Object o2) {
//				// TODO Auto-generated method stub
//				BigDecimal b1 = new BigDecimal((String)o1);
//				BigDecimal b2 = new BigDecimal((String)o2);
//				return b2.compareTo(b1);
//			}
//			
//		});
//		
//		for(String str : s) {
//			System.out.println(str);
//		}
		
		// ----- Java BigDecimal -----
		
		// ----- Java BigInteger -----
//		Scanner scanner = new Scanner(System.in);
//		String s1 = scanner.next();
//		String s2 = scanner.next();
//		BigInteger b1 = new BigInteger(s1);
//		BigInteger b2 = new BigInteger(s2);
//		System.out.println(b1.add(b2));
//		System.out.println(b1.multiply(b2));
//		scanner.close();
		// ----- Java BigInteger -----
		
		
		// ----- Java Subarray -----
//		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
//		int[] arr = new int[n];
//		for(int i=0; i<n; i++) {
//			arr[i] = scan.nextInt();
//		}
//		
//		int numNegative = 0;
//		for (int i = 0; i < arr.length; i++) {			
//			for (int j = i; j < arr.length; j++) {
//				int sum = 0;
//				for (int k = i; k <= j; k++) {
//					sum += arr[k];
//				}
//				if(sum < 0) {
//					numNegative++;
//				}
//			}
//		}
//		System.out.println(numNegative);
//		scan.close();
		// ----- Java Subarray -----
		
		// ----- Java Arraylist -----
//		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
//		List<List<Integer>> lst = new ArrayList<List<Integer>>(); 
//		// ----- Java Arraylist -----
//		for (int i = 0; i < n; i++) {
//			int x = scan.nextInt();
//			lst.add(i, new ArrayList<Integer>());
//			for (int j = 0; j < x; j++) {
//				lst.get(i).add(scan.nextInt());
//			}
//		}
//		
//		int q = scan.nextInt();
//		int[][] arr = new int[q][2];
//		for (int i = 0; i < q; i++) {
//			arr[i][0] = scan.nextInt();
//			arr[i][1] = scan.nextInt();
//		}
//		for (int j = 0; j < q; j++) {
//			if(arr[j][0] - 1 >= 0 && arr[j][0] - 1 < n) {
//				List<Integer> l = lst.get(arr[j][0] - 1);
//				if(arr[j][1] - 1 >= 0 && arr[j][1] - 1 < lst.get(arr[j][0] - 1).size()) {
//					System.out.println(lst.get(arr[j][0] - 1).get(arr[j][1] - 1));
//				}
//				else {
//					System.out.println("ERROR!");
//				}
//			}
//			else {
//				System.out.println("ERROR!");
//			}
//		}
		// ----- Java Arraylist -----
		
		// ----- Java List -----
//		Scanner scan = new Scanner(System.in);
//		int n = scan.nextInt();
//		List<Integer> lst = new ArrayList<Integer>(); 
//		for (int i = 0; i < n; i++) {
//			lst.add(scan.nextInt());
//		}
//		
//		int q = scan.nextInt();
//		for (int j = 0; j < q; j++) {
//			String s = scan.next();
//			if(s.equalsIgnoreCase("Insert")) {
//				int idx = scan.nextInt();
//				int data = scan.nextInt();
//				lst.add(idx, data);
//			}
//			else if (s.equalsIgnoreCase("Delete")) {
//				int idx = scan.nextInt();
//				lst.remove(idx);
//			}
//		}
//		
//		for(int i : lst) {
//			System.out.print(i + " ");
//		}
		// ----- Java List -----
				
		// ----- Java Hashset -----
//		Scanner s = new Scanner(System.in);
//        int t = s.nextInt();
//        String [] pair_left = new String[t];
//        String [] pair_right = new String[t];
//        
//        for (int i = 0; i < t; i++) {
//            pair_left[i] = s.next();
//            pair_right[i] = s.next();
//        }
//        
//        HashSet<String> hs = new HashSet<>();
//        for (int i = 0; i < t; i++) {
//			hs.add(pair_left[i] + "," + pair_right[i]);
//			System.out.println(hs.size());
//		}        
		// ----- Java Hashset -----
		
		// ----- Java Generics -----
//		Printer ptr = new Printer();
//		Integer[] intArr = {1,2,3,4,5};
//		String[] strArr = {"Hello", "World"};
//		ptr.printArray(intArr);
//		ptr.printArray(strArr);
		// ----- Java Generics -----
		
		
	}
}

class Printer<T>{	
	 public void printArray(T[] arr){
	       for (int i = 0; i < arr.length; i++) {
			System.out.println(arr[i]);
		}
	 }	
}
class Student{
	private int id;
	private String fname;
	private double cgpa;
	public Student(int id, String fname, double cgpa) {
		super();
		this.id = id;
		this.fname = fname;
		this.cgpa = cgpa;
	}
	public int getId() {
		return id;
	}
	public String getFname() {
		return fname;
	}
	public double getCgpa() {
		return cgpa;
	}
}

