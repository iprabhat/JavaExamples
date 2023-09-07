## Java Collections (Important Points)

#### Sets
1. To create immutable set use `Set.of("abc","xyz")` static varargs function. The `Set.of()` method throws `UnsupportedOperationException` while attempting to modify the set. It also throws `IllegalArgumentException` when duplicate elements are specified and throws `NullPointerException` when a `null` element is passed to the function.
2. `HashSet<E>` is an implementation of Set interface which does not maintain the ordering of the elements.
3. `LinkedHashSet<E>` is an implementation of the Set interface which maintains the ordering with same order as the elements were inserted.
4. Basic set operations. 
    ```java
        // Union of s1 and s2 and the result will be stored in s1
        s1.add(s2)
        // Intersection of s1 and s2 and the result will be stored in s1
        s1.retainAll(s2)
        // Difference in s1 and s2 and the result will be stored in s1
        s1.removeAll(s2)
    ```
5. A `SortedSet<E>` interface can be used to store the elements in natural order using a `Comparator`. The elements in the `SortedSet` are sorted based on either the element's implementation of `Comparable` interface in which case the `compareTo()` method is called to sort the elements or an instance of a `Comparator` is passed to the constructor of the `Set<E>` implementation.
6. `TreeSet<E>` is an implementation of the `SortedSet<E>` interface.
```java
    // Sorted using the compareTo() method of String class as the String class implements Comparable interface
    SortedSet<String> set1 = new TreeSet<>();
    set1.add("abc");
    set1.add("xyz");

    // Sorted using the comparator passed as an argument to the TreeSet
    SortedSet<CustomClass> set2 = new TreeSet<>(Comparator.comparing(CustomClass::getName));    
    set2.add(new CustomClass(1, "Test1"));
    set2.add(new CustomClass(2, "Test2"));
```

#### Queues

Types of Queues

1. *Simple Queue*: Allows new elements to be inserted at the rear and removed from front.
2. *Priority Queue*: Associate a priority to every element of the queue and remove the element having higghest priority.
3. *Delay Queue*: Associates a delay to every element of the queue and remove the element once the delay has elapsed.
4. *Doubly Ended Queue*: Allows the insertion and removal from both ends (rear and front end).
5. *Blocking Queue*: It blocks the thread from adding into the queue when it is full and blocks the thread from removing when the thread is empty.
6. *Transfer Queue*: A special type of blocking queue where a handoff of an object occurs between two thread (producer and consumer threads).
7. *Blocking Doubly Ended Queue*: A combination of a doubly endded queue and a blocking queue.

