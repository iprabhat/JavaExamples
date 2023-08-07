package com.demo.hackerrank;

import java.lang.reflect.Method;

public class JavaVarargs {
	
	
	public static void main(String[] args) {
		Add ob = new Add();
		ob.add(1,2);
		ob.add(1,2,3);
		ob.add(1,2,3,4);
		ob.add(1,2,3,4,5);
		ob.add(1,2,3,4,5,6);
		
		// Java Reflection
		Class a = Add.class;
		Method[] methods = a.getDeclaredMethods();
		for(Method m : methods) {
			System.out.println(m.getName());
		}
	}
}


class Add{
	public void add(int...arr) {
		int sum = 0;
		String s  = "";
		for (int i = 0; i < arr.length; i++) {
			if(i == arr.length - 1) {
				s += arr[i]+ "=";
			}
			else {
				s += arr[i] + "+";
			}
			sum += arr[i];
		}
		System.out.println(s+sum);
	}	
}