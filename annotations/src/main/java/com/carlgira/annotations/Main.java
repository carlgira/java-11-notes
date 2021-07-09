package com.carlgira.annotations;
import java.lang.annotation.*;
import java.util.List;

@Documented
@Inherited
@interface SuperAnnotation {
}

@SuperAnnotation
class Three{

}

class Four extends Three{

}


@Target(ElementType.TYPE_USE)
@interface TypeUseAnnotation {
    String value();
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


@SuperAnnotation // ElementType.TYPE
class Where {

    @SuperAnnotation // ElementType.FIELD
    private String name;

    @SuperAnnotation // ElementType.CONSTRUCTOR, ElementTypeTYPE_USE
    public Where(){

    }

    @SuperAnnotation // ElementType.METHOD
    public void method(@SuperAnnotation String param){ // ElementType.PARAMETER

        @SuperAnnotation // ElementType.LOCAL_VARIABLE
        Integer value = 1;

        new @TypeUseAnnotation("asd") Object(); // ElementType.TYPE_USE
    }
}

public class Main  {

    private String name;

    @SafeVarargs
    private void safeargs(List<String>... values){ // private or final method
    }

    public void asd(String... a){ // This ok.

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



interface Dog {
    int play();
}

@FunctionalInterface
interface Webby extends Dog {
    default void rest() {}
    int play();

    String toString();
    boolean equals(Object e);
    // hashcode
}