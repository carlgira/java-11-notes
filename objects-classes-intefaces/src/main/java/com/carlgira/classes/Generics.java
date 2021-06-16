package com.carlgira.classes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Shoe {}

class Heel extends Shoe { }

class Sandal extends Shoe {}

class DBShoeManager {

    List<Shoe> items = new ArrayList();

    public void add(Shoe shoe){
        this.items.add(shoe);
    }

    public Shoe get(Integer index){
        return this.items.get(index);
    }

    public static void main(String[] args) {
        DBShoeManager shoeManager = new DBShoeManager();
        shoeManager.add(new Heel());
        shoeManager.add(new Sandal());

        Shoe shoe1 = shoeManager.get(0);
    }
}

class Sock {}


class DBSockManager {

    List<Sock> items = new ArrayList();

    public void add(Sock shoe){
        this.items.add(shoe);
    }

    public Sock get(Integer index){
        return this.items.get(index);
    }

    public static void main(String[] args) {
        DBSockManager shoeManager = new DBSockManager();
        shoeManager.add(new Sock());
        shoeManager.add(new Sock());

        Sock sock = shoeManager.get(0);
    }
}

class DBObjectManager {

    List items = new ArrayList();

    public void add(Object object){
        this.items.add(object);
    }

    public Object get(Integer index){
        return this.items.get(index);
    }

    public static void main(String[] args) {
        DBObjectManager storeManagerOne = new DBObjectManager();
        storeManagerOne.add(new Sandal());
        storeManagerOne.add(new Shoe());
        storeManagerOne.add(new Sock());

        Object item = storeManagerOne.get(0);

        if(item instanceof Sock){
            Sock sandal = (Sock) item;
        }

        if(item instanceof Shoe){
            Shoe sandal = (Shoe) item;
        }
    }
}


class DBGenericManager<T> {

    List<T> items = new ArrayList<>();

    public void add(T t){
        this.items.add(t);
    }

    public T get(Integer index){
        return this.items.get(index);
    }

    public static void main(String[] args) {
        DBGenericManager<Shoe> storeManagerOne = new DBGenericManager<>();
        storeManagerOne.add(new Shoe());
        Shoe shoe = storeManagerOne.get(0);

        DBGenericManager<Sandal> storeManagerTwo = new DBGenericManager<>();
        storeManagerTwo.add(new Sandal());
        Sandal sandal = storeManagerTwo.get(0);

        DBGenericManager<Sock> storeManagerThree = new DBGenericManager<>();
        storeManagerThree.add(new Sock());
        Sock sock = storeManagerThree.get(0);
    }
}


public class Generics {
}


class CustomList<T> {

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
        List<?> numbers1 = List.of("");
        List<? extends Number> numbers2 = new ArrayList<>(); // Number, Double, Float
        List<? super Integer> numbers3 = new ArrayList<>(); // Integer, Number, Object
    }
}
