package com.carlgira.modules;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ServiceLoader;

public class Main {

    private Integer age;
    private String name;

    public static void main(String[] args) {
        ServiceLoader<Serializable> ones = ServiceLoader.load(Serializable.class);



    }
}

