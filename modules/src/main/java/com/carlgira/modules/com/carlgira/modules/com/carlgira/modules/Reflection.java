package com.carlgira.modules.com.carlgira.modules.com.carlgira.modules;

import com.carlgira.modules.Main;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class Reflection {

    public static void reflection(Main main) throws IllegalAccessException {
        for(Field field : Main.class.getDeclaredFields()){
            System.out.println(field.getName() + " ----  " + field.get(main));
        }

        //Method method = Main.class.getDeclaredMethod("asdas");
        //method.invoke();
    }

    public static void main(String[] args) throws IllegalAccessException {
        reflection(new Main());
    }
}
