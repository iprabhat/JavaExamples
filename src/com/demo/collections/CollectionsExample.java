package com.demo.collections;

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

        Student s1 = new Student("Student1", 100, "Address1");
        Student s2 = new Student("Student2", 95, "Address2");
        Student s3 = new Student("Student3", 107, "Address3");
        Student s4 = new Student("Student4", 109, "Address4");
        Student s5 = new Student("Student5", 109, "Address5");

        // Set<Student> s = new HashSet<>();
        // s.addAll(Arrays.asList(s1,s2,s3,s4,s5));

        List<Student> lst = new ArrayList<>(Arrays.asList(s1, s2, s3, s4, s5));
        List<Student> lst2 = new CopyOnWriteArrayList<>(Arrays.asList(s1,s2,s3,s4,s5));

        Collections.sort(lst, new StudentComparator());
        lst.stream().forEach(System.out::println);
        System.out.println();
        lst2.stream().forEach(System.out::println);

        
        // List<String> l = new ArrayList<>(); // Concurrent Modificatione Exception
        List<String> l = new CopyOnWriteArrayList<>(); // No Concurrent Modification Exception
        l.add("Test");
        l.add("Data");

        for(String s : l) {
            System.out.println(s);
            l.add("New Data");
        }
    }

}
