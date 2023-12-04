package com.demo.collections;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.WeakHashMap;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.DelayQueue;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

import javax.swing.RowFilter.Entry;

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
        if (this.getId() == o.getId()) {
            return 0;
        } else if (this.getId() > o.getId()) {
            return 1;
        } else {
            return -1;
        }
    }
}

class PersonComparator implements Comparator<Person> {

    @Override
    public int compare(Person o1, Person o2) {
        return o1.getName().compareTo(o2.getName());
    }
}

class BQProducer extends Thread {
    private final BlockingQueue<String> queue;
    private final String name;
    private int nextNumber = 1;
    private final Random random = new Random();

    public BQProducer(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                String str = name + "-" + nextNumber;
                System.out.println(
                        name + " is trying to add: " + str + ". Remaining capacity: " + this.queue.remainingCapacity());
                this.queue.put(str);
                nextNumber++;
                System.out.println(name + " added " + str);

                // Sleep between 1 and 5 seconds
                int sleepTime = (random.nextInt(5) + 1) * 1000;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class BQConsumer extends Thread {
    private final BlockingQueue<String> queue;
    private final String name;
    private final Random random = new Random();

    public BQConsumer(BlockingQueue<String> queue, String name) {
        this.queue = queue;
        this.name = name;
    }

    @Override
    public void run() {
        while (true) {
            try {
                System.out.println(
                        name + " is trying to take an element. Remaining Capacity: " + this.queue.remainingCapacity());
                String str = this.queue.take();
                System.out.println(name + " took " + str);

                // Sleep between 1 and 5 seconds
                int sleepTime = (random.nextInt(5) + 1) * 1000;
                Thread.sleep(sleepTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
    }
}

class DelayedJob implements Delayed {
    private final Instant scheduledTime;
    private String jobName;

    public DelayedJob(Instant scheduledTime, String jobName) {
        this.scheduledTime = scheduledTime;
        this.jobName = jobName;
    }

    @Override
    public int compareTo(Delayed o) {
        long currentJobDelay = this.getDelay(TimeUnit.MILLISECONDS);
        long jobDelay = o.getDelay(TimeUnit.MILLISECONDS);

        int diff = 0;
        if (currentJobDelay > jobDelay) {
            diff = 1;
        } else if (currentJobDelay < jobDelay) {
            diff = -1;
        }
        return diff;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        Duration delay = Duration.between(Instant.now(), scheduledTime);
        return delay.toMillis();
    }

    @Override
    public String toString() {
        return "DelayedJob [scheduledTime=" + scheduledTime + ", jobName=" + jobName + "]";
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

        // Comparator takes preference even though the class implements Comparable
        // interface
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

        // ListIterator allows to iterate the elements in both directions(Forward and
        // Backward)
        // Whereas, Iterator only allows to iterate in forward direction
        ListIterator<String> listIterator = lstNames.listIterator();
        while (listIterator.hasNext()) {
            System.out.println(listIterator.next());
        }

        // LinkedList performs better with adding and removing elements any arbritrary
        // position because ArrayList have to do an array copy to keep the elements in
        // sequence.
        // ArrayList performs better with accessing the element at any arbritrary
        // position since the elements are accessed using an index
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

    public static void queueExamples() {
        System.out.println("--- Queue Exampes ---");

        System.out.println("-- Simple Queue --");
        Queue<Person> q1 = new LinkedList<>();
        q1.add(new Person(4, "TEST4"));
        q1.add(new Person(2, "TEST2"));
        q1.add(new Person(3, "TEST3"));
        q1.add(new Person(1, "TEST1"));

        System.out.println("Q! Elements");
        while (q1.peek() != null) {
            System.out.println(q1.poll());
        }
        System.out.println("-------------------------");
        System.out.println("-- Priority Queue --");
        Queue<Person> priorityQueue = new PriorityQueue<>();
        priorityQueue.add(new Person(4, "ABC"));
        priorityQueue.add(new Person(2, "XYZ"));
        priorityQueue.add(new Person(3, "PQR"));
        priorityQueue.add(new Person(1, "DEF"));

        System.out.println("Priority Queue Default Order");
        System.out.println(priorityQueue);

        System.out.println("Priority Queue Elements");
        while (priorityQueue.peek() != null) {
            System.out.println(priorityQueue.poll());
        }

        System.out.println("-- Priority Queue with Comparator");
        Queue<Person> pq = new PriorityQueue<>(new PersonComparator());
        pq.add(new Person(4, "test1"));
        pq.add(new Person(2, "test2"));
        pq.add(new Person(3, "test3"));
        pq.add(new Person(1, "test4"));

        System.out.println("Without poll()" + pq);

        System.out.println("-- Priority Queue Elements (Using Comparator)");
        while (pq.peek() != null) {
            System.out.println(pq.poll());
        }
    }

    public static void dequeExamples() {
        System.out.println("---- Deque Examples -----");

        System.out.println("-- Simple Deque --");
        Deque<String> dq = new LinkedList<>();
        dq.offerFirst("TEST1");
        dq.offerLast("TEST2");
        dq.offerFirst("TEST3");
        dq.addFirst("TEST4");

        System.out.println("Deque: " + dq);

        System.out.println("-- Deque as Stack --");
        Deque<String> stack = new ArrayDeque<>();
        stack.push("elem1");
        stack.push("elem2");
        System.out.println("Stack : " + stack);
        if (!stack.isEmpty()) {
            System.out.println("POP : " + stack.pop());
        }
        System.out.println("Stack: " + stack);
        stack.push("elem3");
        stack.push("elem4");
        System.out.println("Stack: " + stack);
        System.out.println("Elements from stack");
        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }

    public static void blockingQueueExample() {
        System.out.println("-- Blocking Queue (Producer/Consumer) example --");
        int capacity = 5;
        boolean fair = true;

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(capacity, fair);
        // Create one producer and two consumers
        new BQProducer(queue, "Producer1").start();
        new BQConsumer(queue, "Consumer1").start();
        new BQConsumer(queue, "Consumer2").start();
    }

    public static void delayedQueueExample() throws InterruptedException {
        System.out.println("-- Delayed Queue Example --");

        BlockingQueue<DelayedJob> queue = new DelayQueue<>();
        Instant now = Instant.now();

        queue.put(new DelayedJob(now.plusSeconds(3), "FIRST JOBN"));
        queue.put(new DelayedJob(now.plusSeconds(7), "SECOND JOBN"));
        queue.put(new DelayedJob(now.plusSeconds(6), "THIRD JOBN"));
        queue.put(new DelayedJob(now.plusSeconds(9), "FOURTH JOBN"));
        queue.put(new DelayedJob(now.plusSeconds(10), "FIFTH JOBN"));

        while (queue.size() > 0) {
            System.out.println("Waiting to take a job from the queue ......");
            DelayedJob job = queue.take();
            System.out.println("Took Job : " + job);
        }
    }

    public static void mapExample() {
        System.out.println("-- Map Example --");
        Map<String, String> m = new HashMap<>();
        m.put("1", "Hello");
        m.put("2", "World");
        System.out.println("Map : " + m);

        m.entrySet().forEach(item -> {
            System.out.println("Key: " + item.getKey() + ", Value: " + item.getValue());
        });

        System.out.println("\nImmutable Maps");
        Map<String, String> im = Map.of("One", "1", "Two", "2");
        System.out.println(im);
        Map<String, String> im2 = Map.ofEntries(Map.entry("123", "TEST"),
                Map.entry("456", "Data"),
                Map.entry("789", "Demo Map"));
        System.out.println(im2);

    }

    public static void collectionViews() {
        System.out.println("-- Different Views of Collections ---");

        System.out.println(("Set View from a Map"));
        Map<String, Boolean> map = new HashMap<>();
        System.out.println("Map: " + map);
        Set<String> set = Collections.newSetFromMap(map);
        System.out.println("Set : " + set);

        System.out.println();
        System.out.println("--- Read-Only view of the collections ---");
        System.out.println("--- Unmodifiable Map ---");
        map.put("Key1", true);
        map.put("Key2", true);
        map.put("Key3", true);

        Map<String, Boolean> unmodifiableMap = Collections.unmodifiableMap(map);
        System.out.println("Unmodifiable Map: " + unmodifiableMap);
        //unmodifiableMap.put("Key4", true); // Throws java.lang.UnsupportedOperationException
        System.out.println("Unmodifiable Map (After Modification): " + unmodifiableMap);

        System.out.println();
        System.out.println("--- Unmodifiable List ---");
        List<String> lst = Arrays.asList("test1", "test2", "test3");
        
        List<String> unmodifiableList = Collections.unmodifiableList(lst);
        System.out.println("Unmodifiable List : " + lst);
        // unmodifiableList.add("TEST4"); // Throws java.lang.UnsupportedOperationException
        System.out.println("Unmodifiable List : (After Modification)" + unmodifiableList);


    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("----------- Collection Examples ------------");
        // setExamples();
        // sortedSetExamples();
        // navigableSetExamples();
        // listExamples();
        // queueExamples();
        // dequeExamples();
        // blockingQueueExample();
        // delayedQueueExample();

        // mapExample();

        collectionViews();

    }

}