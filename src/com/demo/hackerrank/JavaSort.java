package com.demo.hackerrank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class JavaSort {

	public static void main(String[] args) {
		// ----- Java Sort -----
		List<Double> lst = Arrays.asList(45.0, 23.0, 57.0, 32.0);
		lst.sort(new Comparator<Double>() {
			@Override
			public int compare(Double o1, Double o2) {
				return o1.compareTo(o2);
			}
		});
		System.out.println(lst);
		List<Student> lstStudent = new ArrayList<Student>();
		lstStudent.add(new Student(33, "Rumpa", 3.68));
		lstStudent.add(new Student(85, "Ashis", 3.85));
		lstStudent.add(new Student(56, "Samiha", 3.75));
		lstStudent.add(new Student(19, "Samara", 3.75));
		lstStudent.add(new Student(22, "Fahim", 3.76));
		lstStudent.sort(new Comparator<Student>() {
			@Override
			public int compare(Student o1, Student o2) {
				if (o1.getCgpa() == o2.getCgpa()) {
					if (o1.getFname().compareToIgnoreCase(o2.getFname()) == 0) {
						if (o1.getId() < o2.getId()) {
							return 1;
						} else if (o1.getId() > o2.getId()) {
							return -1;
						} else {
							return 0;
						}
					} else {
						return o1.getFname().compareTo(o2.getFname());
					}
				} else if (o1.getCgpa() < o2.getCgpa()) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		lstStudent.stream().forEach(x -> System.out.println(x.getFname()));
		// ----- Java Sort -----

	}
}
