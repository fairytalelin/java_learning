package com.claylin.concurrencyInPractice.buildingBlock.queue;

import java.util.concurrent.CountDownLatch;

public class CountDowanLatchExample {
    public long timeTask(int nThreads, final Runnable taks) throws InterruptedException {

        final CountDownLatch startGate = new CountDownLatch(1);
        final CountDownLatch endGate = new CountDownLatch(nThreads);

        for (int i = 0; i < nThreads; i++) {
            Thread thread = new Thread(() -> {
                try {
                    startGate.await();//等待所有线程初始化完毕
                    try {
                        taks.run();
                    } finally {
                        endGate.countDown();//执行完以后 主线程等待所有线程执行完任务
                    }
                } catch (InterruptedException e) {

                }
            });
            thread.start();
        }


        long start = System.nanoTime();
        startGate.countDown();
        endGate.await();
        long end = System.nanoTime();
        return end - start;
    }
}
