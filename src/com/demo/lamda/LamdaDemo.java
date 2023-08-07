package com.demo.lamda;

@FunctionalInterface
interface TestAdder {
    int add(int x, int y);

    default void testMethod() {
        System.out.println("Default method executed in Test interface");
    }
}

@FunctionalInterface
interface TestJoiner {
    String concat(String s1, String s2);
}

class ConflictingLambdaExp {
    public void test(TestAdder add) {
        System.out.println(add.add(5, 10));
    }

    public void test(TestJoiner join) {
        System.out.println(join.concat("Hello, ", "World ..."));
    }
}

public class LamdaDemo {
    public static void RunLamdaDemo() {
        System.out.println("Lamda Demo");
        TestAdder t = (x, y) -> x + y;
        System.out.println("Sum : " + t.add(5, 10));
        t.testMethod();
    }

    public static void PassLamdaRef(TestAdder t) {
        System.out.println();
        System.out.println("Lambda test by passing lamda as object");
        System.out.printf("Sum (by passing lamda as parameter): %d\n", t.add(15, 20));
    }

    public static void main(String[] args) {
        RunLamdaDemo();
        PassLamdaRef((final int x, final int y) -> x + y); // Using expression
        PassLamdaRef((final int x, final int y) -> {
            return x + y;
        }); // Using block statement
        System.out.println("Lamda: Target Typing");
        TestAdder sum = (x, y) -> x + y;
        TestJoiner joiner = (x, y) -> x + y;
        TestJoiner joiner2 = (x, y) -> {
            StringBuilder sb1 = new StringBuilder(x);
            StringBuilder sb2 = new StringBuilder(y);
            return sb1.reverse().append(" ").append(sb2.reverse()).toString();
        };

        System.out.println("Sum : " + sum.add(24, 35));
        System.out.println("Joiner : " + joiner.concat("Hello", "Java"));
        System.out.println("Joiner2 : " + joiner2.concat("Hello", "Java"));

        System.out.println("Conflicting Lamda Expression");
        ConflictingLambdaExp obj = new ConflictingLambdaExp();
        // The method test(TestAdder) is ambiguous for the type ConflictingLambdaExp
        //obj.test((x, y) -> x + y); 

        // Fix : 1
        obj.test((int x, int y) -> x + y); // Instantiate a TestAdder instance because parameter types matched with TestAdder
        obj.test((String x, String y) -> x + y); // Instantiate a TestJoiner instance parameter types matched with TestJoiner

        // Fix :2  -- > Type casting the lambda expression to a type of functional interface
        obj.test((TestAdder)(x,y) -> x + y); // Calls the joiner2 version of concat implementation

        //Fix : 3 --> Passing an instance of a functional interface 
        obj.test(joiner2);

    }

}
