package com.carlgira.collections;


import java.util.*;

// Calls that implements Comparable
class SOne implements Comparable<SOne> {

    private Integer value;

    public SOne(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }

    @Override
    public int compareTo(SOne o) {
        return Integer.compare(this.getValue(), o.getValue());
    } // -1, 0 , +1
    // [1, 2] = -1
}

class STwo {
    private Integer value;

    public STwo(Integer value){
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "" + value;
    }
}

public class Main {
    /**
     * List
     * Set
     * Queue: FIFO
     * Stack: LIFO
     * Deque: FIFO, LIFO
     * Map<PERSON, STRING> ("A", MAC), ("GATO", Iphone) (3, Windows), (2, Android)
     */

    public static void list(){
        List<Integer> list1 = new ArrayList<>();
        list1.add(5);
        list1.add(1);
        list1.add(2);

        list1.remove(2);
        list1.set(0, 6);
        list1.clear();

        System.out.println(list1);
    }


    public static void stack(){
        System.out.println("Stack");
        Stack<Integer> stack = new Stack<>();
        stack.add(1);
        stack.add(2);
        System.out.println(stack);

        stack.add(3);
        stack.push(4);

        System.out.println(stack);

        stack.pop();
        System.out.println(stack);

        System.out.println(stack.peek());
    }

    public static void queue(){
        /**
         * Queue => Interface
         *
         * LinkedList
         */

        System.out.println("Queue");
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        queue.add(2);

        queue.offer(3);
        System.out.println(queue);

        queue.remove();
        System.out.println(queue);

        queue.poll();
        System.out.println(queue);

    }

    public static void deque(){
        /**
         * Deque => interface
         *
         * ArrayDeque
         * LinkedList
         */
        System.out.println("Deque");

        Deque<Integer> deque = new LinkedList<>();
        System.out.println("add");

        deque.addLast(2); deque.add(1);
        deque.addFirst(4);

        System.out.println(deque);

        System.out.println("offer");


        deque.offerFirst(7);
        deque.offerLast(9); deque.offer(6);

        System.out.println(deque);

        System.out.println("remove");
        deque.remove();
        System.out.println(deque);

        deque.removeFirst();
        System.out.println(deque);

        deque.removeLast(); // deque.remove();
        System.out.println(deque);

        System.out.println("pop");
        deque.pop();
        System.out.println(deque);

        System.out.println("poll");
        deque.poll(); // deque.pop();
        System.out.println(deque);

        deque.pollFirst();
        System.out.println(deque);

        deque.pollLast();
        System.out.println(deque);

        // add => offer => push
        // poll => remove => pop
    }

    public static void sets(){
        /**
         * Set => Interface
         *
         * HashSet => Orden no importa
         * LinkedHashSet => Mantiene orden que se han agreagado los objetos
         * TreeSet => Ordena los datos del set.
         */
        Set<Integer> set1 = new HashSet<>();
        set1.add(5);
        set1.add(1);
        set1.add(2);
        set1.add(1);
        System.out.println("HashSet " + set1);

        Set<Integer> set2 = new LinkedHashSet<>();
        set2.add(5);
        set2.add(1);
        set2.add(2);

        set2.add(1);
        System.out.println("LinkedHashSet " + set2);

        Set<Integer> set3 = new TreeSet<>();
        set3.add(5);
        set3.add(1);
        set3.add(2);
        set3.add(1);
        System.out.println("TreeSet " + set3);

    }

    public static void maps(){
        /**
         * Map => interface
         *
         * HashMap => No importa el orden
         * LinkedHashMap => Mantiene el orden
         * TreeMap => Ordena datos
         */


        Map<Integer, String> map1 = new HashMap<>();
        map1.put(0, "1");
        map1.put(3, "2");
        map1.put(1, "2");

        System.out.println("HashMap " + map1);

        Map<Integer, String> map2 = new LinkedHashMap<>();
        map2.put(0, "1");

        map2.put(3, "2");
        map2.put(1, "2");
        System.out.println("LinkedHashMap " + map2);

        Map<Integer, String> map3 = new TreeMap<>();
        map3.put(0, "1");
        map3.put(3, "2");

        // map3.put(1, "2");
        // map3.get(1);
        // map3.containsKey(4);

        System.out.println("TreeMap " + map3);
    }

    public static void sets1(){
        Set<SOne> set3 = new TreeSet<>();


        set3.add(new SOne(5));
        set3.add(new SOne(1));
        set3.add(new SOne(2));
        set3.add(new SOne(4));

        System.out.println("TreeSet " + set3);
    }

    public static void sets2(){ // Throws Exception
        Set<STwo> set3 = new TreeSet<>();
        set3.add(new STwo(5));
        set3.add(new STwo(1));
        set3.add(new STwo(2));
        set3.add(new STwo(4));
        System.out.println("TreeSet " + set3);
    }

    public static void sets3(){
        Set<STwo> set3 = new TreeSet<>(new Comparator<>() {
            @Override
            public int compare(STwo o1, STwo o2) {
                return Integer.compare(o1.getValue(), o2.getValue());
            }
        });
        set3.add(new STwo(5));
        set3.add(new STwo(1));
        set3.add(new STwo(2));
        set3.add(new STwo(4));
        System.out.println("TreeSet " + set3);
    }

    public static void arrays(){
        var linux = new String[] { "Linux", "Mac", "Windows" };
        var mac = new String[] { "Mac", "Linux", "Windows" };
        var search = Arrays.binarySearch(linux, "Linux");
        var mismatch1 = Arrays.mismatch(linux, mac);
        var mismatch2 = Arrays.mismatch(mac, mac);
        System.out.println(search + " " + mismatch1 + " " + mismatch2);
    }

    public static void arrays1(){
        /**
         *  Arrays.sort();
         *  Arrays.asList();
         *  Arrays.stream();
         */

        /**
         * List.of();
         * List.copyOf();
         */
    }

    public static void main(String[] args) {
        sets3();
    }
}





