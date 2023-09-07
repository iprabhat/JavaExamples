package com.demo.lamda;

import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.ToIntFunction;

public class MethodReference {

    public static void main(String[] args) {
        System.out.println("Method Reference Examples ");

        ToIntFunction<String> lengthFunction = s -> s.length();
        String strdata = "TEST DATA";
        System.out.println("Length of String is : " + lengthFunction.applyAsInt(strdata));
        System.out.println();


        System.out.println("Lambda Expression using Method Reference");
        ToIntFunction<String> lengthFunction2 = String::length;
        System.out.println("Length of String (Method Reference) : " + lengthFunction2.applyAsInt(strdata));

        
        System.out.println("Static Method Reference");
        Function<Integer, String> func1 = x -> Integer.toBinaryString(x);
        System.out.println(func1.apply(50));
        Function<Integer, String> func2 = Integer::toBinaryString;
        System.out.println("(Using method reference)" + func2.apply(50));


        System.out.println("\nBiFunction Example");
        BiFunction<Integer, Integer, Integer> func3 = Integer::sum;
        System.out.println(func3.apply(25, 54));


        Consumer<String> func4 = System.out::println;
        func4.accept("Hello Consumer Functional Interface .....");

        // Bound Receiver (Instance Method Receiver) for static method
        Supplier<String> func5 = MethodReference::getData;
        System.out.println(func5.get());

        MethodReference ref = new MethodReference();
        Supplier<String> func6 = ref::getInstanceData;
        System.out.println("Supplier with instance method reference : " + func6.get());
        

        // Bound Receiver (Instance Method Receiver)
        Supplier<Integer> func7 = "Supplier Example for instance method"::length;
        System.out.println("Supplier with instance method: " + func7.get());


        // Constructor Reference
        Function<String, String> func8 = String::new;
        System.out.println("Constructor Reference example: " + func8.apply("Hello Constructor Reference"));

    }

    public String getInstanceData(){
        return "This is instance data .....";
    }

    public static String getData() {
        return "Hello Supplier Functional Interface (static method).....";
    }

}
