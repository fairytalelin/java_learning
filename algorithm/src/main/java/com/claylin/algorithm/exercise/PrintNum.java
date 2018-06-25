package com.claylin.algorithm.exercise;

import java.util.concurrent.atomic.AtomicInteger;

public class PrintNum {

    public static void main(String[] args) throws Exception {

        AtomicInteger i = new AtomicInteger(1);

        Object lock = new Object();

        Thread oddThread = new Thread(() -> {
            try {
                synchronized (lock) {
                    while (i.get() < 100) {
                        if (i.get() % 2 != 0) {
                            System.out.println(i.getAndIncrement());
                            lock.notify();
                        } else {
                            lock.wait();
                        }
                    }
                }
            } catch (Exception e) {

            }
        });


        Thread evenThread = new Thread(() -> {
            try {
                synchronized (lock) {
                    while (i.get() <= 100) {
                        if (i.get() % 2 == 0) {
                            System.out.println(i.getAndIncrement());
                            lock.notify();
                        } else {
                            lock.wait();
                        }
                    }
                }
            } catch (Exception e) {

            }
        });


        oddThread.start();
        evenThread.start();

        oddThread.join();
        evenThread.join();
    }
}
