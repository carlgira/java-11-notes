package com.carlgira.classes;

import java.util.Comparator;

/**
 * # Interfaces
 * - interface
 *   - by default all methods are public and abstract
 *   - by default all fields are public static final
 *   - constants
 *   - abstract methods ()
 *   - private methods (only visibles for the interface)
 *   - static methods: (not is not inherited to the class!)
 *   - default method (can cause conflics)
 * - Implement interface
 *   - A class must implement all the abstract methods
 *   - If there is a conflict between two default methods in two interfaces, the class must implement the method.
 *   - Interface can extend other interfaces.
 * - Inteface as type
 *   - IOne ione = new ImplementOne(); => ione instanceof IOne
 * - Describe abstract and not abstract methods
 * - Use common used Interfaces.
 *   - Comparable: Compare one object with the actual one.
 *     - class One implements Comparable<> {} compareTo(T o)
 *   - Comparator:
 *     - class One implements Comparable<> {} compare(T one, T two)
 *   - Cloneable: Does not have any abstract methods
 *     - A type class to be able to check implements clone() as type marker. (if not clone() method of object throws an Exception)
 */

public class Interfaces {
}

interface SampleInterface {

    Integer valueOne = 5;
    Integer valueTwo = 10;

    /**
     * Abstract methods
     */
    Integer sum();
    Integer subtract();

    /**
     * Private method, only visible on other private or default methods
     */
    private Integer sumTemp(Integer os){
        return valueOne + os + 1;
    }

    /**
     * Default methods
     */
    default Integer substractTemp(Integer os){
        return valueTwo + os -1;
    }

    /**
     * Static methods
     */
    static Integer someStatic(){
        return 0;
    }
}

class SampleClass implements SampleInterface {

    @Override
    public Integer sum() {
        return valueOne + valueTwo;
    }

    @Override
    public Integer subtract() {
        return valueOne - valueTwo;
    }

}

/**
 * A conflict can occurs when there is a default method that with the same signature in implemented class
 */
interface IConflictOne {
    void sum();
    default void subtract(){}
}

interface IConflictTwo {
    void sum();
    default void subtract(){}
}

class Conflicts implements IConflictOne, IConflictTwo{

    @Override
    public void sum() {

    }

    @Override
    public void subtract() {
        IConflictOne.super.subtract();
    }
}


/**
 * Some Interfaces
 */

class ComparatorOne implements Comparator<Integer>{

    @Override
    public int compare(Integer o1, Integer o2) {
        return 0;
    }
}

class ComparableOne implements Comparable<Integer>{

    @Override
    public int compareTo(Integer o) {
        return 0;
    }
}
