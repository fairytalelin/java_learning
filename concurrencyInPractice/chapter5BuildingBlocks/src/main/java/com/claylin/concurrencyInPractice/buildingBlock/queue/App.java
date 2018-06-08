package com.claylin.concurrencyInPractice.buildingBlock.queue;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class App {
    public static void main(String[] args) {
        BlockingQueue queue = new ArrayBlockingQueue(20);
        Consumer consumer = new Consumer(queue);
        Producer producer = new Producer(queue);
        new Thread(consumer, "consumer1").start();
        new Thread(consumer, "consumer2").start();
        new Thread(consumer, "consumer3").start();

        new Thread(producer, "producer1").start();
    }
}
