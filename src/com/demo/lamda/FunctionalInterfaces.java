package com.demo.lamda;

import java.util.function.Function;

// Generic Functional Interface Example
@FunctionalInterface
interface Mapper<T> {
    int map(T source);

    static <U> int[] mapToInt(U[] arr, Mapper<? super U> mapper) {
        int[] mappedValues = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            mappedValues[i] = mapper.map(arr[i]);
        }
        return mappedValues;
    }
}

@FunctionalInterface
interface Adder {
    int add(int x, int y);
}

interface TestMarker {
}

public class FunctionalInterfaces {
    public static void main(String[] args) {
        System.out.println(".....Generic Functional Interface Examples ...");

        String[] names = { "Java", "Go", "Rust" };
        int[] lenNames = Mapper.mapToInt(names, (String name) -> name.length());
        printArray(names, lenNames);

        Integer[] nums = { 4, 7, 9 };
        int[] numCubes = Mapper.mapToInt(nums, (Integer n) -> n * n * n);
        printArray(nums, numCubes);

        System.out.println(".....Intersection Type and Lambda Expression.....");

        // Compile Time Error as TestMarker does not have the matching abstract method
        // TestMarker marker =  (x , y) -> x + y;

        // This will work, as the intersection type (TestMarker & Adder) has one matching abstract method from Added interface.
        TestMarker marker = (TestMarker & Adder) (x , y) -> x + y;
        System.out.println();

        // Using built in Function interface
        System.out.println(".....Using built in Function interface.....");
        Function<Integer, Integer> square = (x) -> x * x;
        System.out.println(square.apply(8));

        // Using compose() and andThen() function from Function interface
        Function<Long, Long> squareValue = x -> x * x;
        Function<Long, Long> addOne = x -> x + 1;

        // Compose functions from two functions
        Function<Long, Long> squareAddOne = squareValue.andThen(addOne);
        Function<Long, Long> addOneSquare = squareValue.compose(addOne);

        Function<Long, Long> identity = Function.<Long>identity();

        Long n = 8L;
        System.out.println("Number : " + n);
        System.out.println("Square then add one : " + squareAddOne.apply(n));
        System.out.println("Add one then square : " + addOneSquare.apply(n));
        System.out.println("Identity : " + identity.apply(n));
        System.out.println();
        
    }
    

    public static void printArray(Object[] from, int[] to) {
        for (int i = 0; i < from.length; i++) {
            System.out.println(from[i] + " Mapped to " + to[i]);
        }
    }

}
