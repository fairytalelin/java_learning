package com.claylin.javaResource.threadLocal;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadId {
    private static final AtomicInteger nextId = new AtomicInteger();

    private static final ThreadLocal<Integer> threadId = new ThreadLocal<Integer>() {
        @Override
        protected Integer initialValue() {
            return nextId.getAndIncrement();
        }
    };

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            System.out.println(threadId.get());
        });

        Thread thread2 = new Thread(() -> {
            System.out.println(threadId.get());
        });


        Thread thread3 = new Thread(() -> {
            System.out.println(threadId.get());
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();
    }
}
