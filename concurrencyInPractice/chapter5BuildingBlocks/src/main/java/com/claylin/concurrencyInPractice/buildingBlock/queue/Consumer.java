package com.claylin.concurrencyInPractice.buildingBlock.queue;

import java.util.concurrent.BlockingQueue;

public class Consumer implements Runnable {
    private final BlockingQueue queue;

    public Consumer(BlockingQueue queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        while (true) {
            try {
                Object o = queue.take();
                consume(o);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();//恢复中断状态,避免掩盖中断
            }
        }
    }

    private void consume(Object o) {
        System.out.println(String.format("Thread: %s, consume: %s", Thread.currentThread().getName(), o.toString()));
    }
}
