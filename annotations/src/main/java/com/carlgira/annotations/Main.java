package com.carlgira.annotations;

import java.lang.annotation.*;
import java.util.List;

@Documented
@Inherited
@interface SuperAnnotation {
}

@Target(ElementType.TYPE_USE)
@interface TypeUseAnnotation {
}


interface Dog {
    int play();
}

@FunctionalInterface
interface Webby extends Dog {
    default void rest() {}
    abstract int play();
    abstract String toString();
}


@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@interface BussinesPolicies {
    BusinessPolicy[] value();
}

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Repeatable(BussinesPolicies.class)
@interface BusinessPolicy {
    String name() default "default policy";
    String[] countries();
    String value();
}

@BussinesPolicies({
        @BusinessPolicy(name = "Returns Policy", countries = "GB", value = "4"),
        @BusinessPolicy(countries = {"GB", "FR"}, value = "ship")
})
class Shop {}


@SuperAnnotation // TYPE
class Where {
    @SuperAnnotation // FIELD
    private String name;

    @SuperAnnotation // CONSTRUCTOR, TYPE_USE
    public Where(){

    }

    @SuperAnnotation // METHOD
    public void method(@SuperAnnotation String param){ // PARAMETER

        @SuperAnnotation // LOCAL_VARIABLE
        Integer value = 1;

        new @TypeUseAnnotation Object(); // TYPE_USE
    }
}

public class Main  {

    private String name;

    @SafeVarargs
    private void safeargs(List<String>... values){ // private or final method
    }

    @Deprecated
    public void superValue(){
    }

    @SuppressWarnings({"deprecated"})
    public void badMehod(){
        superValue();
    }

    @Override
    public String toString() {
        return "Main{" +
                "name='" + name + '\'' +
                '}';
    }

    public static void main(String[] args) {
    }
}
