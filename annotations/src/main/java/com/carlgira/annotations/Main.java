package com.carlgira.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.Date;
import java.util.List;


interface Dog {
    default void drink() {}
    void play();
}

@FunctionalInterface
interface Webby extends Dog {
    default void rest() {}
    abstract void play();
    abstract String toString();
}

@interface Ano1 {
    int one();
    int value = 2;
}

interface Ano2 extends Ano1{

}

public class Main  {

    @SafeVarargs
    private void safeargs(List<String>... values){
    }

    @Target(ElementType.TYPE_USE)
    public @interface Friend {
        String value();
        String lastName() default "null";
        int age = 10;

    } class MyFriends {
        void makeFriends() {


            new @Friend("Olivia") Object();

            var friends = List.of(new @Friend("Olivia") Object(),
                    new @Friend("Adeline") String(),
                    new @Friend("Henry") MyFriends());
        }
    }

    public static void main(String[] args) {
        List<Object> stars = List.of(1,2,3);
        stars.add(4);

    }
}
