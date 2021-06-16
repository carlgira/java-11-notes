package com.carlgira.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.*;


@FunctionalInterface
interface FunOne {
    Integer sum(Integer one, Integer two);
}

class FunCLas implements FunOne{

    @Override
    public Integer sum(Integer one, Integer two) {
        return null;
    }
}

public class Lambdas {

    public void initLambda(){

        // Anonymous class
        FunOne funOne = new FunOne() {
            @Override
            public Integer sum(Integer one, Integer two) {
                return one + two;
            }
        };
        this.calc(funOne);

        // Lambda
        this.calc((p1, p2) -> p1 + p2);

    }

    public Integer calc(FunOne funOne){
        return funOne.sum(1, 2);
    }


    public int tempComparator(Integer one, Integer two){
        return 1;
    }

    public static int staticTempComparator(Integer one, Integer two){
        return 1;
    }

    public void functionalInterfaces(){

        List<Integer> numbers = Arrays.asList(1, 2 ,5); // 8
        // Types to variables is optional
        int count = 0;
        for(Integer v : numbers){
            count+= v;
        }
        numbers.stream().reduce( (p1, p2) -> p1 + p2);

        // Create an instance of lambda to use for later
        Comparator<Integer> sortText = (s1, s2) -> 1;

        Collections.sort(Arrays.asList(1,2 ,5), sortText);

        // The lambda expresion can have a bigger body using curly braces
        Collections.sort(Arrays.asList(1,2 ,5), (p1, p2) -> {
            System.out.println(1);
            return 1;
        });

        // Shortcut :: symbol. Each method must have the same number and types of the expected interface.
        numbers.forEach(p -> System.out.println(p));
        numbers.forEach(System.out::println);
        numbers.sort(this::tempComparator); // Instance method call
        numbers.sort(Lambdas::staticTempComparator); // Static method

        numbers.stream().map(p -> new Outer(p)); // Creating object
        numbers.stream().map(Outer::new); // Creating object

        // Some common functional interfaces
            // Comparator
            // Comparable
            // Runnable

        // Some common types functional interfaces
            // Function -> IntFunctiom, DoubleFunction,
            // Operator
            // Suplier
            // Consumer
            // Predicates
    }


    /**
     * Function interface has one input one output.
     * And there are helpers method to handle primitives types.
     */
    public void functions(){
        Function<String, Integer> fun1 = p -> Integer.parseInt(p);
        Function<String, Integer> fu5 = Integer::parseInt;
        BiFunction<Integer, Integer, String> fun67 = (p1, p2) -> String.valueOf(p1 + p2) ;

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
        Function<Integer, Integer> fun5 = p -> p + 1;


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

    /**
     * Predicate receives one argument and returns boolean.
     */
    public void predicate(){

        List<Integer> numbers = Arrays.asList(1,2 ,5);
        // Predicate => .negate() .or() .and()

        Predicate<Integer> pred1 = p -> true;
        Predicate<Integer> pred2 = p -> false;


        numbers.removeIf(pred1.negate().or(pred2));
        // Predicate static functions .not .isEqual

        numbers.removeIf(Predicate.isEqual(3));
    }
}
