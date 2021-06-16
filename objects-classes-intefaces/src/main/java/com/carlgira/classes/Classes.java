package com.carlgira.classes;

import java.lang.Math;
import java.util.List;

import static java.lang.Math.cos;

class OneTwo {
    protected Integer one;

}

class OneThree extends OneTwo{
    public static void main(String[] args) {
        double a = cos(1);
        List<Integer> values = List.of(1,2,3);
        values.parallelStream().reduce(0, (n, m) -> n+m);
        values.stream().parallel().reduce(0, (n, m) -> n+m);
    }
}


public class Classes  {

    /**
     * Modifiers, private, protected, public, <default> package
     */
    private Integer name;
    protected Integer value; // private for sub-class and classes on same package
    public Integer lastName;
    Integer age;

    /**
     * Instance field
     */
    private Integer number;

    /**
     * Static field
     */
    private static Integer other;

    /**
     * Method with params, return type and Exception
     */
    public Integer method(String param, Integer one) throws Exception{
        this.name = 2;
        Classes.other = 2;
        return 1;
    }

    public static Integer methodStatic(String param, Integer one) throws Exception{

        return 1;
    }

    /**
     * Static block (can only see static variables)
     */
    static {
        other = 4;
    }

    /**
     * Instance block (execute always first than constructors), visibility of instance fields.
     */
    {
        name = 6;
    }

    /**
     * this, and super references
     */
    Classes(Integer name){
        super();

        this.name = name;

        super.equals(0);

        other = 2;
    }

    Classes(){
        this(2);

    }




    public static void main(String[] args) {
        Classes one = new Classes(1);

        Classes two = new Classes(2);

        //Classes.methodStatic();
    }

}

// Encapsulation
// Immutability

class POne {

    private final Integer qa = 2;

    /**
     * Stack => referencias objectos, varibales privmitas
     * Heap => Memory, objectos, clean => JDK
     */


    public void sum(){

    }

    public void subtract(){

    }
}

class PChild extends POne {

    @Override
    public void sum() {
        super.sum();
    }
}

/**
 * final keyword
    * final class: Class can not have sub-classes
    * final field: Field can only be initialized once
    * final method: Can not be override on sub-class
 */
final class IClass {
    private final Integer value = 5;
    private final Integer value2;

    IClass(){
        value2 = 1;
    }

    final void sum(){
        OneFour oneFour = new OneFour();
        Integer o = oneFour.one;
    }
}

