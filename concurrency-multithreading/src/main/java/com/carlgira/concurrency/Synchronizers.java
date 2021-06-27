package com.carlgira.concurrency;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Synchronizers {

    static CyclicBarrier cyclicBarrier;
    static List<Integer> results = Collections.synchronizedList(new ArrayList<>());

    public static void cyclicBarrier(){

        class Worker implements Runnable {

            List<Integer> list;

            @Override
            public void run() {
                list =  IntStream.generate(() -> (int)(Math.random()*10)).limit(3).boxed().collect(Collectors.toList());
                results.addAll(list);

                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        List<Integer> total = new ArrayList<>();

        int NUM_WORKERS = 3;
        cyclicBarrier = new CyclicBarrier(NUM_WORKERS, () -> {
            results.forEach(System.out::println);
        });

        for(int i=0;i<4;i++){
            new Thread(new Worker()).start();
        }



    }

    public static void main(String[] args) throws InterruptedException {
        cyclicBarrier();

    }

    static class One {
        private int o;

        One(int a){
            this.o = a;
        }
    }

    static class Padlock {
        private Lock lock = new ReentrantLock();

        public void lockUp() {
            if (lock.tryLock()) {
                lock.lock();
                System.out.println("Locked!");
                lock.unlock();
            }
        }
    }

}
