package com.carlgira.modules;

import java.io.Serializable;
import java.util.ServiceLoader;

public class Main {

    public static void main(String[] args) {
        ServiceLoader<Serializable> ones = ServiceLoader.load(Serializable.class);


    }
}

