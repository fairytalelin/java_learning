package com.claylin.concurrencyInPractice.buildingBlock.queue;

import java.util.concurrent.BlockingQueue;

public class Producer implements Runnable {
    private final BlockingQueue queue;

    public Producer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {

                Object o = produce();
                queue.put(o);
                System.out.println(String.format("Thread: %s, produce: %s", Thread.currentThread().getName(), o.toString()));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private Object produce() {
        return new Object();
    }

}
