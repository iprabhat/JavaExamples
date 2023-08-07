package com.demo;

import java.util.HashMap;
import java.util.Map;

import com.demo.models.Person;
import com.demo.models.SumOfTwo;

public class MainClass {

	public static void main(String[] args) {
		
		int val = 53_563_225 + 1;
		System.out.println("Val : " + val);
		int intMax = Integer.MAX_VALUE;
		System.out.println("Max Int : " + intMax);
		
		byte minByte = Byte.MIN_VALUE;
		byte d = 2;
		
		byte newByteVal = (byte) (d/2); 
		System.out.println("newByteVal : " + newByteVal);
		
		Person person1 = new Person("ABC", "XYZ", "abc@gmail.com");
		Person person2 = new Person("ABC", "WXYZ", "abc@gmail.com");
		
		System.out.println("person1.equals(person1) : "+ person1.equals(person1));		
		System.out.println("person1.equals(person2) : "+ person1.equals(person2));
		
		System.out.println("person1.hashCode(): " + person1.hashCode());
		System.out.println("person2.hashCode(): " + person2.hashCode());
		
		System.out.println("person1.toString(): " + person1.toString());
		System.out.printf("Person2.tostring() : %s", person2.toString());
		System.out.println();
		
		Map<Person, Integer> map = new HashMap<>();
		map.put(person1, 1);
		map.put(person1, 3);
		map.put(person2, 2);
		
		// Inner Class example
		SumOfTwo example1 = new SumOfTwo() {			
			@Override
			public int sum(int x, int y) {
				return x + y;
			}
		};
		System.out.println("Inner Class Example : " + example1.sum(5, 6));
		
		// Lambda Expression Example
		SumOfTwo example2 = (int a, int b) -> a + b;
		System.out.println("Lambda Expression Example : " + example2.sum(5, 6));
		
		//Lambda Expression Example (without parameter type)
		SumOfTwo example3 = (a, b) -> a + b;
		System.out.println("Lambda Expression Example (without parameter type): " + example3.sum(5, 6));
		
		// Method reference
		SumOfTwo example4 = MainClass::addTwoValues;		
		System.out.println("Method Reference Example : " + example4.sum(5, 6));
	}
	
	
	public static int addTwoValues(int a, int b) {
		return a + b;
	}

}
