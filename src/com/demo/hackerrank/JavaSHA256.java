package com.demo.hackerrank;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class JavaSHA256 {
	public static void main(String[] args) {
		try {
			Scanner scan = new Scanner(System.in);
			MessageDigest md = MessageDigest.getInstance("SHA-256");

			String message = scan.next();
			byte[] messageDigest = md.digest(message.getBytes());
			BigInteger b = new BigInteger(1, messageDigest);
			String hash = b.toString(16);

			while (hash.length() < 64) {
				hash = "0" + hash;
			}
			System.out.println(hash);

			scan.close();

		} catch (Exception e) {
			// TODO: handle exception
		}	
	}
}
