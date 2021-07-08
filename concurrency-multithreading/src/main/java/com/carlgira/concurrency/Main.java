package com.carlgira.concurrency;

import java.util.*;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Main {

    static class Lateral implements Runnable{
        @Override
        public void run() {
        }
    }

    static class Lateral1 implements Callable<String>{
        @Override
        public String call() throws Exception {
            return null;
        }
    }

    public void runnable(){


        new Thread(new Lateral()).start();

        Thread t = new Thread(new Lateral());
        t.start();

        new Thread(new Runnable() {
            @Override
            public void run() {

            }
        }).start();


        new Thread(() -> System.out.println(1));

    }

    public void states(){

        Thread t = new Thread(new Lateral());
        t.start();

        t.isAlive();
        t.isAlive();
        t.isDaemon();
        t.isInterrupted();
        Thread.State state = t.getState(); // ENUM

    }

    // Syncronized

    static class Test{

        synchronized void one(){
            synchronized(this){

            }
        }

        static synchronized  void two(){
            synchronized (Test.class){

            }
        }

        void three(){

            // t1
            synchronized (this){

            }

            // t2
            synchronized (this){

            }

            synchronized (Test.class){

            }

            Date d = new Date();
            synchronized (d){

            }

            List<String> list = Collections.synchronizedList(List.of("1", "2", "3")); // Update operations
            synchronized (list){ // For read operations still needs the syncronized keyword.
                for(String s : list){

                }
            }
        }
    }

    private Lock lock1 = new ReentrantLock(true);

    public void alternagive(){
        lock1.lock();

        lock1.unlock();

    }

    public void alternativeLock() throws InterruptedException {
        if (lock1.tryLock()) {
            lock1.lock();
            try{

            }
            catch (Exception e){
            }
            finally {
                lock1.unlock();
            }
        }
    }

    public static class Athlete {
        int stroke = 0;
        public synchronized void swimming() {
            stroke++;
        }
        private int getStroke() {
            synchronized(this) { return stroke; }
        }

    }


    public static void wait1() throws InterruptedException {
        Object object = new Object();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    try {
                        object.wait();
                        System.out.println("Runnable " + Thread.currentThread().getName());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        };

        Thread t1 = new Thread(r);
        //t1.setDaemon(true);
        t1.start();

        Thread t2 = new Thread(r);
        //t1.setDaemon(true);
        t2.start();

        Thread.sleep(1000);
        synchronized (object){
            System.out.println("Notify " + Thread.currentThread().getName());
            object.notify();
        }

    }

    public static void wait2() throws InterruptedException {
        Object object = new Object();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                synchronized (object){
                    try {
                        //object.wait(2000);
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("Runnable " + Thread.currentThread().getName()+ " " + Thread.currentThread().getState());

            }
        };

        Thread t1 = new Thread(r);
        t1.start();

        Thread t2 = new Thread(r);
        t2.start();

        Thread.sleep(1000);
        synchronized (object){
            System.out.println("Notify " + Thread.currentThread().getName());
            System.out.println(" t1 " + t1.getState() );
            System.out.println(" t2 " + t1.getState() );
            object.notifyAll(); // It's necessary to wake up all threads
        }

    }


    public static void join() throws InterruptedException {
        AtomicInteger atomicInteger = new AtomicInteger(0);
        Runnable r = () -> {
            try {
                while(atomicInteger.get() < 5){
                    Thread.sleep(1000);
                    System.out.println("Thread times " + atomicInteger.addAndGet(1));
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        };

        Thread t = new Thread(r);
        t.start();

        t.join();

        System.out.println("Main thread finish");
    }

    public synchronized static void singleMethod(){
        // Vey long time
    }

    public static void lockingProblems(){
        // Starvation
        Thread t1 = new Thread(() -> singleMethod());
        t1.start();

        Thread t2 = new Thread(() -> singleMethod());
        t2.start();

        // LiveLock
        Lock lock1 = new ReentrantLock(true);
        Lock lock2 = new ReentrantLock(true);

        Thread t3 = new Thread(() -> {

           while(true){
               try {

                   lock1.tryLock(50, TimeUnit.MILLISECONDS);
                   System.out.println("Got lock1 ");

                   if(lock2.tryLock()){
                       System.out.println("Got lock2 ");
                   }

               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        });

        Thread t4 = new Thread(() -> {
            while(true){
                try {

                    lock2.tryLock(50, TimeUnit.MILLISECONDS);
                    System.out.println("Got lock2 ");

                    if(lock1.tryLock()){
                        System.out.println("Got lock1 ");
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });



        // Dealock

        Object a = new Object();
        Object b = new Object();

        Thread t6 = new Thread(() -> {
            synchronized (a){
                synchronized (b){

                }
            }
        });

        Thread t7 = new Thread(() -> {
            synchronized (b){
                synchronized (a){

                }
            }
        });

        t6.start();
        t7.start();

    }

    volatile static int a = 0;

    public static void volatile_method(){
        Thread t1 = new Thread(() -> {
            while (a < 1){
            }
        });

        Thread t2 = new Thread(() -> {
            a = 2;
        });

        t1.start();
        t2.start();

    }


    public static void copyOnWrite(){

        List<String> l3 = Collections.synchronizedList(List.of("", ""));
        ConcurrentLinkedQueue<String>  l4 = new ConcurrentLinkedQueue<>();
        // BlockingDeque<String> queue = new LinkedBlockedQueue<>();


        List<String> l1 = new CopyOnWriteArrayList<>();
        Set<String> l2  = new CopyOnWriteArraySet<>();

        Thread t1 = new Thread(() -> {
            l1.get(0);
        } );
    }

    public static void main(String[] args) throws InterruptedException {

        ExecutorService s = Executors.newFixedThreadPool(10);
        Athlete a = new Athlete();
        for(int i=0; i<10000; i++) {
            s.execute(() -> a.swimming());
        }
        s.shutdown();
        System.out.print(a.getStroke());
    }



}
