package com.carlgira.classes;

public class Classes {

    /**
     * Modifiers, private, protected, public, <default> package
     */
    private Integer name;
    protected Integer value;
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
        return 1;
    }

    /**
     * Static block (can only see static variables)
     */
    static{
        other = 4;
    }

    /**
     * Instance block (execute always first than constructors), visibility of instance fields.
     */
    {
        name = 6;
    }

    /**
     * Constructors
     */
    Classes(){
        super();
    }

    /**
     * this, and super references
     */
    Classes(Integer name){
        super();
        this.name = name;

        super.equals(0);
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

    }
}