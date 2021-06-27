package com.carlgira.concurrency;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

public class ExecutorClass {

    public static void createExecutors() {

        Runnable r = () -> System.out.println(1);

        ExecutorService pool0 = Executors.newFixedThreadPool(3);
        pool0.execute(r);

        ExecutorService pool2 = Executors.newWorkStealingPool(2);
        pool2.execute(r);

        ExecutorService pool3 = Executors.newSingleThreadExecutor();
        pool3.execute(r);

        ExecutorService pool4 = Executors.newCachedThreadPool();
        pool4.execute(r);

        ScheduledExecutorService pool1 = Executors.newScheduledThreadPool(3);
        pool1.scheduleAtFixedRate(r, 1000, 1000, TimeUnit.MILLISECONDS);

        ScheduledExecutorService pool5 = Executors.newSingleThreadScheduledExecutor();
        pool5.schedule(r, 1000, TimeUnit.MILLISECONDS);

        ExecutorService pool6 = Executors.unconfigurableExecutorService(Executors.newCachedThreadPool());
        pool6.execute(r);


    }

    public static void executeOne() throws InterruptedException {

        ExecutorService pool0 = Executors.newFixedThreadPool(3);

        AtomicInteger atomicInteger = new AtomicInteger(0);
        for(int i=0;i<10;i++){
            pool0.execute(() -> {
                try {
                    Thread.sleep(1000);
                    System.out.println(" Thread " + atomicInteger.addAndGet(1) + " " +  Thread.currentThread().getId());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
        }

        pool0.shutdown();

        if(!pool0.awaitTermination(2000, TimeUnit.MILLISECONDS)){
            pool0.shutdownNow();
        }
    }

    public static void executeAndSubmit() throws ExecutionException, InterruptedException {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        ExecutorService pool0 = Executors.newFixedThreadPool(3);

        pool0.execute(r);
        System.out.println("After Execution !!! pool0.execute(r)");

        AtomicInteger atomicInteger = new AtomicInteger(0);
        Callable<String> callable = new Callable<>() {
            @Override
            public String call() throws Exception {
                Thread.sleep(3000);
                return "Hello World! " + atomicInteger.addAndGet(1);
            }
        };

        Future<String> future = pool0.submit(callable);
        System.out.println(future.get());

        pool0.shutdown();
        System.out.println("Method Finish");
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        //executeOne();
        executeAndSubmit();
    }


}
