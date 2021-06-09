package com.carlgira.classes;

import java.util.Objects;

public class Inheritance {
}

/**
 * All classes by default extend from Object
 * Methods from object, equals(), clone(), hashCode(), toString()
 */
class SampleObjectOne {}
class SampleObjectTwo extends Object{

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(this);
    }

    @Override
    public String toString() {
        return super.toString();
    }
}


class Parent {

    private Integer one;
    protected Integer two;

    {
        System.out.println("Parent block Instance ");
    }
    static {
        System.out.println("Parent static Instance ");
    }
    Parent(Integer one){
        this.one = one;
    }

    public int sum(int o, int u){
        return o + u;
    }

    public int subtract(int o, int u){
        return o + u;
    }
}

class ChildTwo extends Parent {

    ChildTwo(Integer one) {
        super(one);
    }
}


class ChildOne extends Parent {

    {
        System.out.println("ChildOne block Instance ");
    }
    static {
        System.out.println("ChildOne static Instance ");
    }
    ChildOne(Integer one) {
        /**
         * super keyword to call parent-class constructor, methods and fields.
         */
        super(one);
        int v = super.sum(1, 2);
        this.two = 6;
    }

    /**
     * Overriding parent method
     */
    @Override
    public int subtract(int o, int u) {
        return super.subtract(o, u) - 1;
    }

    public static void main(String[] args) {
        // Polymorphism
        Parent parent = new Parent(0);
        Parent child1 = new ChildOne(1);
        //Parent child2 = new ChildTwo(1);

        System.out.println(parent instanceof Parent);
        System.out.println(child1 instanceof Parent);
        System.out.println(child1 instanceof ChildOne);
    }
}

/**
 * Abstract classes
 */
abstract class ICustomAbstract {
    abstract int sum();
}

class CustomAbstract extends ICustomAbstract{

    public int subtract(int o, int u){
        return o + u;
    }

    @Override
    int sum() {
        return 0;
    }
}
