package com.demo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import com.demo.models.Student;

class StudentComparator implements Comparator<Student> {

    @Override
    public int compare(Student s1, Student s2) {
        if (s1.getRollno() == s2.getRollno()) {
            return s1.getName().compareTo(s2.getName());
        } else if (s1.getRollno() > s2.getRollno()) {
            return 1;
        } else {
            return -1;
        }
    }
}

public class CollectionsExample {

    public static void main(String[] args) {

        Student s1 = new Student("Bruce", 100, "NYC");
        Student s2 = new Student("Tony", 95, "Manhattan");
        Student s3 = new Student("Angela", 107, "NJ");
        Student s4 = new Student("Michael", 109, "Berlin");
        Student s5 = new Student("Ingrid", 109, "Malmo");

        // Set<Student> s = new HashSet<>();
        // s.addAll(Arrays.asList(s1,s2,s3,s4,s5));

        List<Student> lst = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));
        List<Student> lst2 = new CopyOnWriteArrayList<>(Arrays.asList(s1,s2,s3,s4,s5));

        Collections.sort(lst, new StudentComparator());
        lst.stream().forEach(System.out::println);
        System.out.println();
        lst2.stream().forEach(System.out::println);

        // Concurrent Modificatione exception
        List<String> l = new ArrayList<>();
        l.add("Test");
        l.add("Data");

        for(String s : l) {
            System.out.println(s);
            l.add("New Data");
        }
    }

}
