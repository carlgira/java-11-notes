package com.carlgira.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;

public class Lambdas {

    public int tempComparator(Integer one, Integer two){
        return 1;
    }

    public static int staticTempComparator(Integer one, Integer two){
        return 1;
    }

    public void functionalInterfaces(){

        List<Integer> numbers = Arrays.asList(1,2 ,5);
        // Types to variables is optional
        Collections.sort(Arrays.asList(1,2 ,5), (Integer p1, Integer p2) -> 1);

        // Create an instance of lambda to use for later
        Comparator<String> sortText = (s1, s2) -> 2;
        Collections.sort(Arrays.asList(1,2 ,5), (p1, p2) -> 1);

        // The lambda expresion can have a bigger body using curly braces
        Collections.sort(Arrays.asList(1,2 ,5), (p1, p2) -> { return 1;});

        // Shortcut :: symbol. Each method must have the same number and types of the expected interface.
        numbers.forEach(System.out::println);
        numbers.sort(this::tempComparator); // Instance method call
        numbers.sort(Lambdas::staticTempComparator); // Static method
        numbers.stream().map(Outer::new); // Creating object

        // Some interfaces has some default methods that can be called. The lambda implements the abstract method.

        // Comparator => .thenComparing
        Comparator<Integer> comp1 = (p1, p2) -> 1;
        Comparator<Integer> comp2 = (p1, p2) -> 2;
        Collections.sort(numbers, comp1.thenComparing(comp2).reversed());

        // Predicate => .negate() .or() .and()
        Predicate<Integer> pred1 = p -> true;
        Predicate<Integer> pred2 = p -> false;
        numbers.removeIf(pred1.negate().or(pred2));
        // Predicate static functions .not .isEqual
        numbers.removeIf(Predicate.isEqual(3));
    }

    /**
     * Function interface has one input one output.
     * And there are helpers method to handle primitives types.
     */
    public void functions(){
        Function<String, Integer> fun1 = p -> Integer.parseInt(p);

        IntFunction<String> fun2 = p -> String.valueOf(p); // LongFunction, DoubleFunction
        ToIntFunction<String> fun3 = p -> Integer.parseInt(p); // ToLongFunction, ToDoubleFunction
        DoubleToIntFunction fun4 = p -> (int) p; // DoubleToLongFunction, IntToDoubleFunction, IntToLongFunction

    }

    /**
     * Operator interfaces are special cases of a function that receive and return the same value type.
     */
    public void operator(){
        BinaryOperator<Integer> fun1 = (p1, p2) -> p1 + p2;
        UnaryOperator<Integer> fun2 = p1 -> p1 + 1;

        IntBinaryOperator fun3 = (p1, p2) -> p1 + p2;
        IntUnaryOperator fun4 = p1 -> p1 + 1;
    }

    /**
     * Supplier interface receive no arguments and returns something.
     * And there are helpers method to handle primitives types.
     */
    public void supliers(){
        Supplier<String> fun1 = () -> "Hello";

        BooleanSupplier fun2 = () -> true;
        DoubleSupplier fun3 = () -> 1.0;
    }

    /**
     * Consumer interface accepts a generified argument and returns nothing.
     * And there are helpers method to handle primitives types.
     */
    public void consumer(){
        Consumer<String> fun1 = p -> System.out.println(p);
        Consumer<String> fun2 = System.out::println;

        IntConsumer intConsumer = p -> { // LongConsumer, DoubleCOnsumer
            p += 5;
            System.out.println(p);
        };
    }
}
