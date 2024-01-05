package com.demo.streams;

import java.time.Month;
import java.util.Collections;
import java.util.Comparator;
import java.util.DoubleSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalDouble;
import java.util.Set;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.demo.streams.Person.Gender;

public class StreamExamples {

        public static void quickExample() {
                System.out.println("--- Quick Example ---");
                System.out.println("-- SUM of squares of odd number in an integer list--");
                List<Integer> numberList = List.of(1, 2, 3, 4, 5);
                Integer sum = numberList.stream().filter(x -> x % 2 == 1).map(x -> x * x).reduce(0, Integer::sum);
                System.out.println("List : " + numberList);
                System.out.println("Sum of squares of odd numbers : " + sum);
                System.out.println();
                System.out.println(" -- Using Stream.of() method -- ");
                System.out.println("Sum of squares of odd numbers {Stream.of()} :"
                                + Stream.of(1, 2, 3, 4, 5).filter(x -> x % 2 == 1).map(x -> x * x).reduce(0,
                                                Integer::sum));
        }

        public static void generatePrimeNumbers() {
                System.out.println("--- Prime Number Generation ---");
                System.out.println("Next prime of 19 : " + PrimeNumGenerator.nextPrime(19L));

                System.out.println("--- First 5 Prime Numbers ---");
                Stream.iterate(2L, PrimeNumGenerator::nextPrime)
                                .limit(5)
                                .forEach(System.out::println);

                System.out.println("--- Another way of generating prime numbers ---");
                Stream.iterate(2L, n -> n + 1)
                                .filter(PrimeNumGenerator::isPrime)
                                .limit(5).forEach(System.out::println);

                System.out.println("--- Skipping first n elements from the stream ---");
                Stream.iterate(2L, PrimeNumGenerator::nextPrime)
                                .skip(50).limit(5).forEach(System.out::println);

                System.out.println("--- Using generate method ---");
                Stream.generate(new PrimeNumGenerator()::next).limit(5).forEach(System.out::println);

        }

        public static void applyingForEach() {
                System.out.println("-- Applying ForEach --");
                Person.persons().stream().filter(e -> e.isMale()).forEach(System.out::println);

                List<Person> persons = Person.persons();
                System.out.println("Before increasing the income");
                System.out.println(persons);
                System.out.println("Increasing the income of female employees by 10%");
                persons.stream().filter(Person::isFemale).forEach(e -> e.setIncome(e.getIncome() * 1.10));
                System.out.println(persons);
        }

        public static void mapExample() {
                System.out.println("-- Map Exmaple --");
                String str = "TEST-DATA";
                str.chars()
                                .mapToObj(e -> (char) e)
                                .forEach(System.out::print);

                System.out.println();
                System.out.println("--FlatMap Example--");
                long count = Stream.of("Ken", "Jeff", "Ellen")
                                .map(name -> name.chars())
                                .flatMap(intStream -> intStream.mapToObj(n -> (char) n))
                                .filter(ch -> ch == 'e' || ch == 'E')
                                .count();
                System.out.println("Es count: " + count);

                Stream.of("A", "B")
                                .map(e -> e.chars())
                                .flatMap(e -> e.mapToObj(x -> (char) x))
                                .forEach(System.out::println);

                System.out.println();
                IntStream.of(12, 23, 45)
                                .mapToObj(e -> String.valueOf(e))
                                .forEach(System.out::println);

                System.out.println("(Print Numbers and their squares.)");
                Stream.of(1, 2, 3)
                                .flatMap(x -> Stream.of(x, x * x))
                                .map(x -> x.toString() + ",")
                                .forEach(System.out::print);

        }

        public static void reduceExample() {
                System.out.println("-- Reduce Operation --");
                Integer sum = Stream.of(1, 2, 3, 4, 5).reduce(0, (a, b) -> {
                        return a + b;
                });
                System.out.println("Sum of Integers: " + sum);

                System.out.println("-- Sum of Incomes --");
                Double sumIncome = Person.persons().stream()
                                .map(Person::getIncome)
                                .reduce(0.0, Double::sum);
                System.out.println("Sum of incomes: " + sumIncome);

                System.out.println("-- Sum of incomes (Using Map Reduce) --");

                // The Double::sum is used only in parallel streams to combine results from
                // multiple threads.
                Double sumIncomeMapReduce = Person.persons().stream()
                                .reduce(0.0, (partial, person) -> partial + person.getIncome(), Double::sum);
                System.out.println("Sum of incomes (Using Map Reduce) :" + sumIncomeMapReduce);

                System.out.println("-- Associative Reduce Operation --");
                Optional<Integer> maxVal = Stream.of(1, 2, 3, 4, 5).reduce(Integer::max);
                if (maxVal.isPresent()) {
                        System.out.println("Maximum : " + maxVal.get());
                } else {
                        System.out.println("Max is not defined");
                }

                Optional<Person> personWithMaxSalary = Person.persons().stream()
                                .reduce((a, b) -> a.getIncome() > b.getIncome() ? a : b);
                if (personWithMaxSalary.isPresent()) {
                        System.out.println("Person with max salary : " + personWithMaxSalary);
                } else {
                        System.out.println("Person with max salary not found.");
                }

                double totalIncome = Person.persons().stream().mapToDouble(Person::getIncome).sum();
                System.out.println("Total Income : " + totalIncome);

                Optional<Person> maxIncome = Person.persons().stream().max(Comparator.comparing(Person::getIncome));
                if (maxIncome.isPresent()) {
                        System.out.println("Highest Earner : " + maxIncome);
                }

                OptionalDouble highestIncome = Person.persons().stream().mapToDouble(Person::getIncome).max();
                System.out.println("Highest Income : " + highestIncome.getAsDouble());

                Function<Person, Double> fn = Person::getIncome;
                System.out.println("First Person's Income : " + fn.apply(Person.persons().get(0)));

                System.out.println("-- Getting the count --");
                long personCount = Person.persons().stream().count();
                System.out.println("Using Stream count method. " + personCount);

                long personCount2 = Person.persons().stream().mapToLong(e -> 1L).sum();
                System.out.println("Count using sum() method : " + personCount2);

                Long personCount3 = Person.persons().stream().map(e -> 1L).reduce(0L, Long::sum);
                System.out.println("Count using map and reduce methods : " + personCount3);

                Long personCount4 = Person.persons().stream().reduce(0L, (partial, obj) -> partial + 1L, Long::sum);
                System.out.println("Count using map and reduce methods with accumulation and combining functions : "
                                + personCount4);
        }

        public static void collectExamples() {
                System.out.println("---- COLLECT EXAMPLES ----");

                Double totalIncome = Person.persons().stream().collect(Collectors.summingDouble(Person::getIncome));
                System.out.println("Total Income : " + totalIncome);
        }

        public static void summaryStatistics() {
                System.out.println("---- Summary Statistics Example ----");
                DoubleSummaryStatistics doubleStats = new DoubleSummaryStatistics();
                doubleStats.accept(100);
                doubleStats.accept(200);
                doubleStats.accept(300);
                System.out.println("Stats : " + doubleStats);

                System.out.println("-- Summary Statistics Used with Streams --");
                DoubleSummaryStatistics incomeStats = Person.persons().stream().mapToDouble(Person::getIncome).collect(
                                DoubleSummaryStatistics::new,
                                DoubleSummaryStatistics::accept, DoubleSummaryStatistics::combine);
                System.out.println("Income Stats : " + incomeStats);

                System.out.println("-- Using specific numeric type --");
                DoubleSummaryStatistics incomeStats2 = Person.persons().stream()
                                .collect(Collectors.summarizingDouble(Person::getIncome));
                System.out.println("Using specific numeric type : " + incomeStats2);

                Double collectAvg = Person.persons().stream()
                                .collect(Collectors.averagingDouble(Person::getIncome));
                System.out.println("Using Specialized Summary Computation (Avg) : " + collectAvg);

        }

        public static void collectMapExample() {
                System.out.println("-- Collect Map Exmaples --");
                Map<Long, String> mapPersonIDToName = Person.persons().stream()
                                .collect(Collectors.toMap(Person::getId, Person::getName));
                System.out.println("Collect Map ID to Name : " + mapPersonIDToName);

                // This will throw an IlligalStateException Exception as the collection have
                // multiple elements
                // having same key (i.e Gender in this case).
                /*
                 * Map<Gender, String> mapPersonGenderToName = Person.persons().stream()
                 * .collect(Collectors.toMap(Person::getGender, Person::getName));
                 * System.out.println("Collect Map Gender to Name : " + mapPersonGenderToName);
                 */

                // Collect Map having multiple keys using Merging function as a third argument.
                Map<Gender, String> mapGenderToName = Person.persons().stream()
                                .collect(Collectors.toMap(Person::getGender, Person::getName,
                                                (oldval, newval) -> String.join(", ", oldval, newval)));
                System.out.println("Collect Map Gender to Name : " + mapGenderToName);

                // Number of people by Gender
                Map<Gender, Long> mapGenderToCount = Person.persons().stream()
                                .collect(Collectors.toMap(Person::getGender, e -> 1L,
                                                (oldval, newval) -> oldval + 1));
                System.out.println("Map Gender to Count : " + mapGenderToCount);

                // Highest earner by Gender
                Map<Gender, Person> highestEarnerByGender = Person.persons().stream()
                                .collect(Collectors.toMap(Person::getGender, p -> p,
                                                (oldval, newval) -> newval.getIncome() > oldval.getIncome() ? newval
                                                                : oldval));
                System.out.println("Highest Earner by Gender : " + highestEarnerByGender);
        }

        public static void joiningStringsCollector() {
                System.out.println("-- Joining Strings using Collectors --");
                String joiningNames = Person.persons().stream()
                                .map(Person::getName)
                                .collect(Collectors.joining());
                System.out.println("Joining Names : " + joiningNames);

                String joiningNamesWithDelimiter = Person.persons().stream()
                                .map(Person::getName)
                                .collect(Collectors.joining(","));
                System.out.println("Joining names with delimiter : " + joiningNamesWithDelimiter);

                String joiningNameswithDelimiterAndPrefixAndSuffix = Person.persons().stream()
                                .map(Person::getName)
                                .collect(Collectors.joining(",", "Hello ... ", ". Welcome to Stream examples!"));
                System.out.println("Joining Names with Delimiter And Prefix And Suffix : "
                                + joiningNameswithDelimiterAndPrefixAndSuffix);
        }

        public static void groupingDataCollector() {
                System.out.println("-- Grouping Data Using Collector --");

                Map<Gender, List<Person>> groupByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender));
                System.out.println("Group by Gender : " + groupByGender);

                Map<Gender, Long> countByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender, Collectors.counting()));
                System.out.println("Count elements by Gender : " + countByGender);

                Map<Gender, String> groupPersonNameByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender,
                                                Collectors.mapping(Person::getName, Collectors.joining(", "))));
                System.out.println("Comma Separated Person Name by Gender : " + groupPersonNameByGender);

                Map<Gender, List<String>> listPersonByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender,
                                                Collectors.mapping(Person::getName, Collectors.toList())));
                System.out.println("List of Person Name by Gender : " + listPersonByGender);

                Map<Gender, Map<Month, String>> groupByGenderAndDOB = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender, Collectors.groupingBy(
                                                e -> e.getDob().getMonth(),
                                                Collectors.mapping(Person::getName, Collectors.joining(" ,")))));
                System.out.println("Group Person Name by Gender and then Month of Birth : " + groupByGenderAndDOB);

                Map<Gender, Double> totalIncomeByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender,
                                                Collectors.summingDouble(Person::getIncome)));
                System.out.println("Total Income by Gender : " + totalIncomeByGender);

                Map<Gender, Double> avgIncomeByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender,
                                                Collectors.averagingDouble(p -> p.getIncome())));
                System.out.println("Average Income By Gender : " + avgIncomeByGender);

                Map<Gender, DoubleSummaryStatistics> summarizeByGender = Person.persons().stream()
                                .collect(Collectors.groupingBy(Person::getGender,
                                                Collectors.summarizingDouble(Person::getIncome)));
                System.out.println("Summarize by Gender : " + summarizeByGender);

        }

        public static void partitioningData() {
                System.out.println("-- Partitioning Data --");
                Map<Boolean, List<Person>> partitionByIncomeGreaterThan5000 = Person.persons().stream()
                                .collect(Collectors.partitioningBy(e -> e.getIncome() > 5000));
                System.out.println("Partition Income greater than 5000 : \n" + partitionByIncomeGreaterThan5000);

                Map<Boolean, List<Person>> partitionByGender = Person.persons().stream()
                                .collect(Collectors.partitioningBy(p -> p.getGender() == Gender.MALE));
                System.out.println("Partition By Gender : \n" + partitionByGender);

                Map<Boolean, Long> partitionByGenderWithCount = Person.persons().stream()
                                .collect(Collectors.partitioningBy(Person::isMale, Collectors.counting()));
                System.out.println("Partition by gender with count : " + partitionByGenderWithCount);

                Map<Boolean, String> partitionByGenderWithNames = Person.persons().stream()
                                .collect(Collectors.partitioningBy(Person::isMale,
                                                Collectors.mapping(Person::getName, Collectors.joining(" ,"))));
                System.out.println("Person Names Partitioned by Gender : \n" + partitionByGenderWithNames);
        }

        public static void adaptingCollectorsResults() {
                System.out.println("-- Adapting the Collector Results --");
                List<Person> unmodfiableList = Person.persons().stream()
                                .collect(Collectors.collectingAndThen(Collectors.toList(),
                                                result -> Collections.unmodifiableList(result)));
                System.out.println("Getting unmodifiable list of person names : " + unmodfiableList);

                Map<Month, String> personNamesGroupedByDOBMonth = Person.persons().stream()
                                .collect(Collectors.groupingBy(e -> e.getDob().getMonth(),
                                                Collectors.mapping(Person::getName, Collectors.joining(","))));
                System.out.println("Person names grouped by DOB Month : " + personNamesGroupedByDOBMonth);

                Map<Month, String> groupByDOBMonthWithEmptyMonths = Person.persons().stream()
                                .collect(Collectors.collectingAndThen(Collectors.groupingBy(e -> e.getDob().getMonth(), 
                                                        Collectors.mapping(Person::getName, Collectors.joining(","))),
                                                result -> {
                                                        for (Month m : Month.values()) {
                                                                result.putIfAbsent(m, "None");
                                                        }
                                                        return Collections.unmodifiableMap(new TreeMap<>(result));
                                                        // return new TreeMap<>(result);
                                                }));
                System.out.println("Group by DOB Months with empty Months : ");
                groupByDOBMonthWithEmptyMonths.entrySet().stream().forEach(System.out::println);

                Map<Gender, List<Person>> filteringExample = Person.persons().stream()
                        .collect(Collectors.groupingBy(Person::getGender, Collectors.filtering(e -> e.getIncome() > 8000, Collectors.toList())));
                System.out.println("Filtering by income in a group by Gender : " + filteringExample);
                
                System.out.println("-- FlatMapping Example --");
                // Represent the gender and the list of spoken languages
                List<Entry<String, Set<String>>> lst = List.of(
                        Map.entry("Male", Set.of("English", "French")),
                        Map.entry("Male", Set.of("Spanish", "Wu")),
                        Map.entry("Female", Set.of("English", "French")),
                        Map.entry("Male", Set.of("Wu", "Lao")),
                        Map.entry("Female", Set.of("English", "German")),
                        Map.entry("Male", Set.of("English"))
                );
                Map<String, Set<String>> langByGender = lst.stream().collect(Collectors.groupingBy(Entry::getKey, 
                        Collectors.flatMapping(e -> e.getValue().stream(), Collectors.toSet())));
                System.out.println("Set of languages by Gender : " + langByGender);

        }

        public static void findingAndMatchingInStreams(){
                System.out.println("-- Finding and Matching in Streams --");
                
                boolean allMalePerson = Person.persons().stream().allMatch(Person::isMale);
                System.out.println("Is all persons are males : " + allMalePerson);

                boolean anyPersonBornIn1970 = Person.persons().stream().anyMatch(e -> e.getDob().getYear() == 1970);
                System.out.println("Any person born was in 1970 : " + anyPersonBornIn1970);

                boolean anyPersonBornIn1955 = Person.persons().stream().anyMatch(e -> e.getDob().getYear() == 1955);
                System.out.println("Any person born in 1955 : " + anyPersonBornIn1955);

                Optional<Person> findAnyMale = Person.persons().stream().filter(Person::isMale).findAny();
                if (findAnyMale.isPresent()){
                        System.out.println("There is atleast a Male in the collection : " + findAnyMale.get());
                } else{
                        System.out.println("There are no males present");
                }
                
                Optional<Person> firstMale = Person.persons().stream().filter(Person::isMale).findFirst();
                if(firstMale.isPresent()){
                        System.out.println("First Male : " + firstMale.get());
                } else{
                        System.out.println("No male present");
                }


        }

        public static void parallelStream(){
                System.out.println("-- Parallel Stream Examples --");

                String personNames = Person.persons().stream().map(Person::getName).collect(Collectors.joining(","));
                System.out.println("Person Names using Sequential stream : " + personNames);

                String personNamesParallel = Person.persons().parallelStream().map(Person::getName).collect(Collectors.joining(","));
                System.out.println("Person Names using Parallel Stream : " + personNamesParallel);

                String malePersonNames = Person.persons().stream().filter(Person::isMale).parallel().map(Person::getName).collect(Collectors.joining(","));
                System.out.println("Male person names : " + malePersonNames);
        }

        public static void main(String[] args) {
                System.out.println("::: STREAM EXAMPLES :::");
                // String s = "hello, world";
                // Pattern.compile(",").splitAsStream(s).map(e ->
                // e.trim()).forEach(System.out::println);

                // quickExample();
                // generatePrimeNumbers();
                // applyingForEach();
                // mapExample();
                // reduceExample();
                // collectExamples();
                // summaryStatistics();
                // collectMapExample();
                // joiningStringsCollector();
                // groupingDataCollector();
                // partitioningData();
                // adaptingCollectorsResults();
                // findingAndMatchingInStreams();

                parallelStream();

        }
}
