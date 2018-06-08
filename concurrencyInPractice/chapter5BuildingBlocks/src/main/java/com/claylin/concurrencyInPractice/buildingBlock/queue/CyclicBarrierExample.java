package com.claylin.concurrencyInPractice.buildingBlock.queue;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class CyclicBarrierExample {
    private final CyclicBarrier barrier;
    private final Worker[] workers;


    public CyclicBarrierExample() {
        int num = Runtime.getRuntime().availableProcessors();
        barrier = new CyclicBarrier(num, () -> {
            System.out.println("go next step");
        });
        workers = new Worker[num];
        for (int i = 0; i < num; i++) {
            workers[i] = new Worker();
        }
    }

    public void start() {
        for (int i = 0; i < workers.length; i++) {
            new Thread(workers[i], String.format("Thread: %s", i)).start();
        }
    }

    class Worker implements Runnable {
        @Override
        public void run() {
            try {
                System.out.println(String.format("%s arrive at step", Thread.currentThread().getName()));
                barrier.await();//关卡点,则被阻塞等待其他线程一起到达指定关卡点,进入下一个阶段
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            } catch (BrokenBarrierException e) {
            }
        }
    }
}
