## Streams

### Creating Streams

*Streams from Values*

The `Stream` interface contains the following static methods to create a sequential `Stream` from a single value and multiple values.
1. `<T> Stream<T> of(T t)`
2. `<T> Stream<T> of(T...values)`
3. `<T> Stream<T> ofNullable(T t)`: This method was added to the Stream interface in Java 9. It returns a stream with a single value if the specified value is non-null. Otherwise, it returns an empty stream.

The `Stream.Builder<T>` interface can be used to create stream using builder pattern. The builder() static method of the `Stream` interface returns a stream builder.
The `Stream.Builder<T>` interface contains the following methods:
1. `void accept(T t)`
2. `Stream.Builder<T> add(T t)`
3. `Stream<T> build()`

Example:
```java
    Stream<String> stream = Stream.<String>builder()
                                .add("TEST1")
                                .add("TEST2")
                                .add("TEST3")
                                .add("TEST4")
                                .build();
```

The `IntStream` interfaces contain four static methods that let you create IntStream from values:

1. `IntStream of(int value)`
2. `IntStream of(int... values)`
3. `IntStream range(int start, int end)`
4. `IntStream rangeClosed(int start, int end).`

The `of()` methods let you create a `IntStream` by specifying individual values. 
The range() and rangeClosed() methods produce an `IntStream` that contains ordered integers between the specified start and end. The specified end is exclusive in the `range()` method, whereas it is inclusive in the `rangeClosed()` method.

*Empty Streams*
1. An empty stream is a stream with no elements. The Stream interface contains an empty() static method to create an empty sequential stream.
```java
    // Creates an empty stream of strings
    Stream<String> stream = Stream.empty();
```

2. The IntStream, LongStream, and DoubleStream interfaces also contain an empty() static method to create an empty stream of primitive types. Here is one example:
```java
    // Creates an empty stream of integers
    IntStream numbers = IntStream.empty();
```

*Streams from Functions*

