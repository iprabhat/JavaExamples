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

### Queues

***Types of Queues***

1. *Simple Queue*: Allows new elements to be inserted at the rear and removed from front.
2. *Priority Queue*: Associate a priority to every element of the queue and remove the element having higghest priority.
3. *Delay Queue*: Associates a delay to every element of the queue and remove the element once the delay has elapsed.
4. *Doubly Ended Queue*: Allows the insertion and removal from both ends (rear and front end).
5. *Blocking Queue*: It blocks the thread from adding into the queue when it is full and blocks the thread from removing when the thread is empty.
6. *Transfer Queue*: A special type of blocking queue where a handoff of an object occurs between two thread (producer and consumer threads).
7. *Blocking Doubly Ended Queue*: A combination of a doubly endded queue and a blocking queue.

***Basic Queue Methods***

1. Adding an element to the queue
    1. `boolean add(E e)`: Add an element to the queue and returns `true` if successful or throws `IllegalStateException`.
    2. `boolean offer(E e)`: Add an element to the queue and returns `true` if successful or returns `false`. It doesn't throws any exception
2. Removing an element from the queue
    1. `E remove()`: Retrives and remove an element from the front of queue and throws exception if the queue is empty.
    2. `E poll()`: Similar to `E remove()` method, but it returns a `null` value if the queue is empty instead of throwing an exception
3. Peeking an the front element of the queue
    1. `E element()`: Retrievs the element from the frontend without removing from the queue and throws an exception if the queue is empty.
    2. `E peek()`: Similar to `E element()` but it returns a null value if the queue is empty instead of throwing an exception.

Implementation of the `Queue<E>` interface.
1. `LinkedList<E>`: Can be used as a FIFO queue or a LIFO queue (Stack)
2. `PriorityQueue<E>`: Assigns a priority to the elements in the queue and retrieves the element based on priority.

***Priority Queues***

1. A priority queue have each of its elements associated with a priority. 
2. `PriorityQueue<E>` is the implementation of an unbounded priority queue. 
3. The elements of the queue may either implement the `Comparable` interface or an instance of `Comparator` is supplied to the queue to determine the order of priority.

***Double Ended Queues***

1. A double ended queue is an extended version of a queue which allows to insert and removed from both ends (front and rear).
2. The `Deque<E>` interface extends the `Queue<E>` interface and declares some additional methods to facilitate the insertion and removal from both ends.

The following methods are supported in a `Deque`
1. Adding an element to the Deque
    1. `void addFirst(E e)` and `void addLast(E e)` to add the element to the front or rear end respectively to the `Deque` and throws an exception when add to a full bounded `Deque`.
    2. `boolean offerFirst(E e)` and `boolean offerLast(E e)` works the same way as `addXxx()` except, it returns false when the element cannot be added to a `Deque` without throwing an exception.
2. Removing an element from a Deque
    1. `E removeFirst()` and `E removeLast()` to retrieve and remove the element from either front or rear end respectively of the `Deque` and throws an exception when the `Deque` is empty.
    2. `E pollFirst()` and `E pollLast()` works the same way as the `removeXxx()` except, it returns `null` if the `Deque` is empty without throwing an exception.
3. Peeking at an element at end of the Deque
    1. `E getFirst()` and `E getLast()` to retrieve without removing the element from either front or rear end respectively from `Deque` and throws an exception if the Deque is empty.
    2. `E peekFirst()` and `E peekLast()` works the same way as `getXxxx()` except, it returns a `null` value if the Deque is empty without throwing an exception. 

Implementations of the `Deque<E>` interface
1. `ArrayDeque<E>`: Backed by an array and it is the preferred implementation of `Deque<E>` as a LIFO queue (or a stack).
2. `LinkedList<E>`: Backed by a linkedlist and it is the preferred implementation of `Deque<E>` as a FIFO queue.

***Blocking Queues***
1. A blocking queue is used to handle two extreme cases 
    - When a new element is attempted to be added to the queue when it is full.    
    - When an element is attempted to be removed from the queue when it is empty.

2. The following additional methods are part of `BlockingQueue<E>` interface to handle the extreme cases.
    - `put()` and `offer()` methods are used to add an element to the rear end of the queue. The `put()` method blocks indefinitely if the blocking queue is full. The `offer()` method allows to specify the time duration to wait for space to become available. It returns `true` if the specified element is added successfully and `false` if the time period elapsed before the space became available.
    - `take()` and `poll()` methods are used to retrive the element from the front end of the queue. The `take()` method blocks indefinitely if the blockiing queue is empty. The `poll()` method allows to specify the time duration to wait if the blocking queue is empty and it returns `null` if the specified time elapses before an element became available.

3. A `BlockingQueue` is designed to be thread-safe. It is used in a producer/consumer like situations where some threads(Producers) add elements to the queue and some threads(Consumers) remove the elements from the queue.

4. The blocking queue can be bounded or unbounded. It adds another method called `remainingCapacity()` which returns the number elements that can be added to the queue with blocking.

5. The `BlockingQueue<E>` interface and all its implementation classes are in `java.util.concurrent` package. The implementation classes of the `BlockingQueue<E>` are as follows:
    - `ArrayBlockingQueue`: It is a bounded implementation class for `BlockingQueue<E>`. It is backed by an array and it also lets you specify the fairness of the blocking queue in its constructor. By default, it is not fair.
    - `LinkedBlockingQueue`: It is another implementation class for `BlockingQueue<E>`. It can be used as a bounded or unbounded blocking queue and it does not allow specifying a fairness rule for the blocking queue.
    - `PriorityBlockingQueue`: It is an unbounded implementation class for `BlockingQueue<E>`. It works the same way as `PriorityQueue` for ordering the elements in the blocking queue. It adds the blocking feature to `PriorityQueue`.
    - `SynchronousQueue`: It is a special type of implementation of `BlockingQueue<E>` which does not have any capacity. The put operation waits for the take operation to take the element being put. It facilitates a handshake between two threads.
    One thread tries to put an element to the blocking queue and waits for another thread to take the element from the queue. Its `isEmpty()` method always returns true.
    -  `DelayQueue`: It is another unbounded implementation class for `BlockingQueue<E>`. It allows an element to be taken out only if a specified delay has passed for that element. If there are multiple elements in the blocking queue whose specified delay has passed, the element whose delay passed earliest will be placed at the head of the blocking queue.


### Maps

***Implementations of Map Interface***
1. `HashMap<K,V>`: The `HashMap` allows one `null` value as a key and multiple `null` values as the values. The `HashMap` implementation does not guarantee any specific order for the entries.
2. `LinkedHashMap<K, V>` : It stores entries in the Map using a doubly linked list. It preserves the ordering of the entries based on the insertion.
3. `WeakHashMap<K, V>`: It contains weak keys. In this implementation, the keys are garbage collected when there are no references to the key except the `Map`. `WeakHashMap` is used when you want to maintain a cache of key-value pairs and don't mind if the keys are removed from the `Map` by the garbage collector.

***Immutable Maps***
Since Java 9, the `Map<K, V>` interface have 11 overloaded static factory method `of()` to create immutable maps in a compact way.
Example
```java
    // An empty, immutable Map
    Map<Integer, String> emptyMap = Map.of();

    // An immutable Map with one entry
    Map<String, String> mapOneEntry = Map.of("TEST", "123456789");
    
    // An immutable Map with two entries
    Map<Integer, String> mapTwoEntries = Map.of(1, "One", 2, "Two");

    // The first and second parameters are the key-value pair of first entry, third and fourth parameters are the key-value pair of the second entry and so on.
```

To create an immutable map with arbitrary number of entries. Java 9 included a static method names `ofEntries()` in the `Map` interface.
Example
```java
    import java.util.Map;
    import static java.util.Map.entry;
 
    // Use the Map.ofEntries() and Map.entry() methods to create an immutable Map
    Map<Integer, String> numberToWord = Map.ofEntries(entry(1, "One"), 
                                                        entry(2, "Two"), 
                                                        entry(3, "Three"));
```
A `NullPointerException` is thrown if a key or value of an immutable map is `null`.

***Sorted Maps***

1. The `SortedMap` is the interface which allows storing the entries in a sorted order in which the keys are sorted in either a natural sort order or a custom sort order.
The natural sort order is defined by the `Comparable` interface of the keys. If the keys do not implement the `Comparable` interface, then a `Comparator` must be used to sort the entries.
2. A `SortedMap` is to a Map what a `SortedSet` is to a Set.
3. `TreeMap<K, V>` class is the implementation class for the `SortedMap<K, V>` interfacel. It also optionally takes `Comparator` as a constructor argument to use incase the key does not implement `Comparable` interface.

***Navigable Maps***
A navigable map is represented by an instance of the `NavigableMap<K,V>` interface. It extends the `SortedMap<K,V>` interface by adding some useful features like getting the closest match for a key, getting a view of the map in reverse order, etc.


***Concurrent Maps***
A concurreent Map is used when it is used by multiple threads concurrently.
To implement a map to be used by multiple threads, the following approach can be used.
```java
    Map<String,String> map = new HashMap<>();
    String key = "key1";
    String value = "value1";
    // Need to lock the entire map
    synchronized(map) {
        if (!map.containsKey(key)) {
            map.put(key, value); // Add the new key-value
        } 
    }
```
1. In this case, the entire map object need to be locked to put a key-value pair and it is necessary to perform two atomic operations: testing for key existence and putting the key. But, a `ConcurentMap` enables to perform consurrent operations without locking the entire map. 

2. The level of concurrency can be determined when during the creation of a concurrent map using its implementation class. 
3. The level of concurrency is specified as the estimated number of threads that would perform the write operations on the map. 
4. The map will try to adjust those many threads concurrently. 
5. A `ConcurrentMap` does not lock the entire map. Even if it locks the entire map, other threads will still be able to perform read and write operations on it because it uses a fine-grained synchronization mechanism based on a *compare-and-set* primitive.
6. The `ConcurrentHashMap<K,V>` class is an implementation class for the `ConcurrentMap<K,V>` interface. 
7. Both of them are in the java.util.concurrent package.

### Applying Algorithms to Collections
The `Collections` class consists of static methods that implements commonly used algorithms on the collection.

***Sorting a List***
The `Collections` class contains the following static methods to sort the elements of a `List`.
1. `<T extends Comparable<? super T>> void sort(List<T> list)`: It sorts the elements in a `List` in the natural order defined by the `Comparable` interface that is implemented by the elements in the List. Each element in the List must implement the `Comparable` interface and they must be comparable to each other.
2. `<T> void sort(List<T> list, Comparator<? super T> c)`: It lets you pass a `Comparator` to define a custom ordering of the elements.

The `List` interface also provides a `sort` method to sort the elements in the `List` without using the `Collections` class.

***Searhing a List***
The following two static binarySearch() methods in the Collections class can be used to search for a 
specified object in a List.
1. `<T> int binarySearch(List<? extends Comparable<? super T>> list, T key)`
2. `<T> int binarySearch(List<? extends T> list, T key, Comparator<? super T> c)`


### Creating Different Views if a Collection

***Read-Only Views of Collections***

A read-only view (also called unmodifiable view) of a collection is useful when it is required to pass around the collection object without getting it modified.
The following are the methods from `Collections` class to get the read-only view of different types of collections.
1. `<T> Collection<T> unmodifiableCollection(Collection<? extends T> c)`
2. `<T> List<T> unmodifiableList(List<? extends T> list)`
3. `<K,V> Map<K,V> unmodifiableMap(Map<? extends K,? extends V> m)`
4. `<K,V> NavigableMap<K,V> unmodifiableNavigableMap(NavigableMap<K,? extends V> m)`
5. `<T> Set<T> unmodifiableSet(Set<? extends T> s)`
6. `<T> NavigableSet<T> unmodifiableNavigableSet(NavigableSet<T> s) `
7. `<T> SortedSet<T> unmodifiableSortedSet(SortedSet<T> s)`
8. `<K,V> SortedMap<K,V> unmodifiableSortedMap(SortedMap<K,? extends V> m)`

***Synchronized View of a Collection***

Most of the collections from `Collections` framework are not thead safe. In a multi-threaded environment, a synchronized collection is required and to get a synchronized view of a collection, the `Collections` class provides the following static methods (One method for each collection type to return the same type of synchronized version of the collection).

1. `<T> Collection<T> synchronizedCollection(Collection<T> c)`
2. `<T> List<T> synchronizedList(List<T> list)`
3. `<K,V> Map<K,V> synchronizedMap(Map<K,V> m)`
4. `<K,V> NavigableMap<K,V> synchronizedNavigableMap(NavigableMap<K,V> m)`
5. `<T> NavigableSet<T> synchronizedNavigableSet(NavigableSet<T> s)`
6. `<T> Set<T> synchronizedSet(Set<T> s)`
7. `<T> SortedSet<T> synchronizedSortedSet(SortedSet<T> s)`
8. `<K,V> SortedMap<K,V> synchronizedSortedMap (SortedMap<K,V> m)`

*Important Point:* All reads and writes through the synchronized view will be thread-safe, except when you are iterating over elements of the collection using an iterator. You must synchronize the entire collection during the time you get the iterator and use it.
*Example*
```java
    Set set = ...; // unsynchronized set 
    // Get a synchronized view of the Set, s 
    Set synset = Collections.synchronizedSet(set);
    // We need to iterate over elements of synset. Must get a lock on synset first (not on set).
    synchronized(synset) {
        Iterator iterator = synset.iterator();
        // use iterator while holding the lock 
        while (iterator.hasNext()) {
            Object obj = iterator.next();
            // Do something with obj here 
        }
    }
```
Same logic need to be followed while iterating over the key, value, or entry views of a synchronized `Map`. That is, you must get a lock on the synchronized view of the `Map` while iterating over any of its views.

***Checked Collections***

The `Collections` class provides static methods to create checked collection in which it will throw `ClassCastException` when a piece of code attempt to add a value that violates the rule at the runtime.

1. `<E> Collection<E> checkedCollection(Collection<E> c, Class<E> type)`
2. `<E> List<E> checkedList(List<E> list, Class<E> type)`
3. `<K,V> Map<K,V> checkedMap(Map<K,V> m, Class<K> keyType, Class<V> valueType)`
4. `<K,V> NavigableMap<K,V> checkedNavigableMap(NavigableMap<K,V> m, Class<K> keyType, Class<V> valueType)`
5. `<E> NavigableSet<E> checkedNavigableSet(NavigableSet<E> s, Class<E> type)`
6. `<E> Queue<E> checkedQueue(Queue<E> queue, Class<E> type)`
7. `<E> Set<E> checkedSet(Set<E> s, Class<E> type)`
8. `<K,V> SortedMap<K,V> checkedSortedMap(SortedMap<K,V> m, Class<K> keyType, Class<V> valueType)`
9. `<E> SortedSet<E> checkedSortedSet(SortedSet<E> s, Class<E> type)`

***Creating Empty Collection***

The `Collections` class provides static methods for creating immutable empty collections which are required when it need to pass an empty collection to a method. The following static methods (partial list) return an empty immutatble collection.

1. `<T> List<T> emptyList()`
2. `<K,V> Map<K,V> emptyMap()`
3. `<T> Set<T> emptySet()`
4. `<T> Iterator<T> emptyIterator()`
5. `<T> ListIterator<T> emptyListIterator()`

***Creating Singleton Collections***

A singleton collection is a collection which have only one element. The `Collections` class provides three static methods to create an immutable collection with one specified element. The following methods are used to create singleton collection.

1. `<T> Set<T> singleton(T o)`
2. `<T> List<T> singletonList(T o)`
3. `<K,V> Map<K,V> singletonMap(K key, V value)`

To create a singleton set we can use the following code snippets.
```java
    Set<String> singletonSet = Collections.singleton("One Element");
```
Java 9 added an overloaded static `of()` method to the `List`, `Set`, and `Map` interfaces. The method creates an empty immutable singleton list, set, and map, respectively. The previous statement can be rewritten in Java 9 as follows:

```java
    Set<String> singletonSet = Set.of("Lonely");
```

