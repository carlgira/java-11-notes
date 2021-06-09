package com.carlgira.classes;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Generics {
}

class CustomList<T>{

    private List<T> values = new ArrayList<>();

    public void add(T value){
        values.add(value);
    }

    public T get(int i){
        return this.values.get(i);
    }
}

class CustomMap<T, V> {

    private Map<T, V> maps = new HashMap<>();

    public V get(T index){
        return maps.get(index);
    }

    public void set(T index, V value){
        this.maps.put(index, value);
    }
}

class CustomMethod {

    public <T> void set(){
        List<T> list = new ArrayList<>();
        T v = list.get(0);
    }

    public <T, V> void set(T index, V value){
        Map<T, V> maps = new HashMap<>();
        V v = maps.get(index);
    }
}


class CustomExtendOne<T extends Number>{
    public static void main(String[] args) {
        CustomExtendOne<Double> d1 = new CustomExtendOne<>();
        CustomExtendOne<Integer> d2 = new CustomExtendOne<>();
    }
}

class CustomExtendTwo<T extends Number & Comparable>{
    public static void main(String[] args) {
        CustomExtendTwo<Double> d1 = new CustomExtendTwo<>();
        CustomExtendTwo<Integer> d2 = new CustomExtendTwo<>();
    }
}

class WildCard{

    public static void main(String[] args) {
        List<?> numbers1 = new ArrayList<>();
        List<? extends Number> numbers2 = new ArrayList<>();
        List<? super Integer> numbers3 = new ArrayList<>();
    }
}