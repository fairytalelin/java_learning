package com.claylin.benchmarks;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

public class QpsWorkBeanch {
    public static final int THREAD_NUM = 5;

    public static final int TIME_THRESHOLD = 60;

    private static AtomicInteger totalTime = new AtomicInteger(TIME_THRESHOLD);

    private static AtomicLong totalExecCount = new AtomicLong();

    private static CyclicBarrier barrier = new CyclicBarrier(THREAD_NUM);

    private static CountDownLatch latch = new CountDownLatch(THREAD_NUM);

    private static volatile boolean running = true;

    private static ExecutorService executorService;

    interface Job {
        void execute() throws Exception;
    }
}
