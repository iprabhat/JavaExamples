package com.demo.streams;

import java.util.List;
import java.util.stream.Stream;

public class StreamExamples {
    
    public static void quickExample(){
        System.out.println("--- Quick Example ---");
        System.out.println("-- SUM of squares of odd number in an integer list--");
        List<Integer> numberList = List.of(1,2,3,4,5);
        Integer sum = numberList.stream().filter(x -> x % 2 == 1).map(x -> x * x).reduce(0, Integer::sum);
        System.out.println("List : " + numberList);
        System.out.println("Sum of squares of odd numbers : " + sum);
        System.out.println();
        System.out.println(" -- Using Stream.of() method -- ");
        System.out.println("Sum of squares of odd numbers {Stream.of()} :" + Stream.of(1,2,3,4,5).filter(x -> x % 2 == 1).map(x -> x * x).reduce(0, Integer::sum));
    }

    public static void main(String[] args) {
        System.out.println("::: STREAM EXAMPLES :::");
        quickExample();
    }
}
