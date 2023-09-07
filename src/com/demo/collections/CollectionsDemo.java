package com.demo.collections;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

class Person implements Comparable<Person> {
    private int id;
    private String name;

    public Person(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Person other = (Person) obj;
        if (id != other.id)
            return false;
        if (name == null) {
            if (other.name != null)
                return false;
        } else if (!name.equals(other.name))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + "]";
    }

    @Override
    public int compareTo(Person o) {
        if(this.getId() == o.getId()) {
            return 0;
        }
        else if(this.getId() > o.getId()) {
            return 1;
        }
        else{
            return -1;
        }        
    }
}

public class CollectionsDemo {

    public static void setExamples() {
        System.out.println("--- Set Examples ---");
        Set<String> names = new HashSet<>();
        names.add("Test1");
        names.add("Test2");
        names.add("Test3");

        System.out.println("Set : " + names);

        // Using static of() method in Java 9 to create immutable Set
        Set<String> names2 = Set.of("Test1", "Test2", "Test3");
        System.out.println("Immutable Set (using of() method): ");
        names2.forEach(System.out::println);

        // Set operations (Union, intersection and minus)
        Set<String> s1 = Set.of("apple", "samsung", "motorola", "nokia", "nothing");
        Set<String> s2 = Set.of("xiaomi", "nokia", "huwaei", "sony", "apple");

        System.out.println("s1 : " + s1);
        System.out.println("s2 : " + s2);

        System.out.println("--- Union of sets s1 and s2");
        Set<String> s1unions2 = new HashSet<>(s1);
        s1unions2.addAll(s2);
        System.out.println(s1unions2);

        System.out.println("--- Intersection of sets s1 and s2 ---");
        Set<String> s1Intersections2 = new HashSet<>(s1);
        s1Intersections2.retainAll(s2);
        System.out.println(s1Intersections2);

        System.out.println("--- Difference between sets s1 and s2 ---");
        Set<String> s1DifferenceS2 = new HashSet<>(s1);
        s1DifferenceS2.removeAll(s2);

        Set<String> s2DifferenceS1 = new HashSet<>(s2);
        s2DifferenceS1.removeAll(s1);

        System.out.println("s1 difference s2 : " + s1DifferenceS2);
        System.out.println("s2 difference s1 : " + s2DifferenceS1);

    }

    public static void sortedSetExamples() {
        System.out.println("\n--- Sorted Set Example ---");
        SortedSet<String> sortedSet = new TreeSet<>();
        sortedSet.add("java");
        sortedSet.add("rust");
        sortedSet.add("go");
        sortedSet.add("matlab");
        sortedSet.add("scala");
        System.out.println("Sorted Set (Strings) : " + sortedSet);

        System.out.println("Sorted set of user defined class");
        // Comparator object should be provided in the TreeSet constructor to allow the
        // TreeSet to sort the elements
        // Sorted based on name. Can also be used for other data members

        // Comparator takes preference even though the class implements Comparable interface
        SortedSet<Person> sortedSetPerson = new TreeSet<>(Comparator.comparing(Person::getName));         
        sortedSetPerson.add(new Person(1, "Person1"));
        sortedSetPerson.add(new Person(2, "Person2"));
        sortedSetPerson.add(new Person(3, "Person3"));
        sortedSetPerson.add(new Person(3, "Person4"));
        System.out.println("Sorted set (Person) with comparator: " + sortedSetPerson);

        // This uses the compareTo method implementated by the Persor class
        SortedSet<Person> sortedSetPerson2 = new TreeSet<>(); 
        sortedSetPerson2.add(new Person(1, "test1"));
        sortedSetPerson2.add(new Person(2, "test2"));
        System.out.println("Sorted Set (Person) using comparable interface: " + sortedSetPerson2);


    }

    public static void navigableSetExamples() {
        System.out.println("--- Navigable Set Examples ---");
        NavigableSet<Integer> ns = new TreeSet<>();
        ns.add(100);
        ns.add(101);
        ns.add(109);
        ns.add(120);
        ns.add(107);
        System.out.println("Navigable Set: " + ns);

        NavigableSet<Integer> reverseNs = ns.descendingSet();
        System.out.println("Reverse Navigable Set: " + reverseNs);

        System.out.println("floor(109): " + ns.floor(109)); // Returns the highest element less than equal to specified
                                                            // value
        System.out.println("lower(109): " + ns.lower(109)); // Returns the highest element less than the specified value

        System.out.println("ceiling(109): " + ns.ceiling(109)); // Returns the lowest element greater than equal to
                                                                // specified value
        System.out.println("higher(109): " + ns.higher(109)); // Returns the lowest element greater than the specified
                                                              // value

        System.out.println("pollfirst: " + ns.pollFirst());
        System.out.println("Navigable Set: " + ns);

        System.out.println("polllast: " + ns.pollLast());
        System.out.println("Navigable Set: " + ns);

    }

    public static void listExamples() {
        System.out.println("--- List Examples ---");

        List<String> lstNames = new ArrayList<>();
        lstNames.add("Test1");
        lstNames.add("Test1");
        lstNames.add("Test3");
        lstNames.add("Test4");

        // ListIterator allows to iterate the elements in both directions(Forward and Backward) 
        // Whereas, Iterator only allows to iterate in forward direction
        ListIterator<String> listIterator = lstNames.listIterator(); 
        while(listIterator.hasNext()){
            System.out.println(listIterator.next());
        }

        // LinkedList performs better with adding and removing elements any arbritrary position because ArrayList have to do an array copy to keep the elements in sequence.
        // ArrayList performs better with accessing the element at any arbritrary position since the elements are accessed using an index
        List<String> lstNames2 = new LinkedList<>();
        lstNames2.add("ABC");
        lstNames2.add("XYZ");
        System.out.println("Linked list : " + lstNames2);

        // Creating immutable list using List.of() method
        List<String> lstDemo = List.of("test1", "test2", "test3");
        System.out.println("Immutable List using of() function: " + lstDemo);
        // This will throw an UnsupportedOperationException
        // lstDemo.add("New data"); 
        
        
    }

    public static void queueExamples(){
        System.out.println("--- Queue Exampes ---");
    }

    public static void main(String[] args) {
        System.out.println("----------- Collection Examples ------------");
        // setExamples();
        // sortedSetExamples();
        // navigableSetExamples();
        // listExamples();

        queueExamples();
    }

}