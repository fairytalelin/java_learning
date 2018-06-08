package com.claylin.javaResource.thread;

import java.util.concurrent.TimeUnit;

public class ThreadSafeStop {

    public static void main(String[] args) throws InterruptedException {
        Runner one = new Runner();

        Thread countThread = new Thread(one, "countThread");
        countThread.start();

        TimeUnit.SECONDS.sleep(1);
        countThread.interrupt();

        Runner two = new Runner();
        countThread = new Thread(one, "countThread1");
        countThread.start();

        TimeUnit.SECONDS.sleep(2);
        two.stopSafely();
    }

    private static class Runner implements Runnable {
        private long i;

        private volatile boolean on = true;

        @Override
        public void run() {
            while (on && !Thread.currentThread().isInterrupted()) {
                i++;
            }
            System.out.printf("start to terminated thread %s, i = %s ", Thread.currentThread().getName(), i);
        }

        public void stopSafely() {
            on = false;
        }
    }
}
