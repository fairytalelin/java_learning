package com.claylin.disruptor;

import com.claylin.disruptor.event.LongEvent;
import com.claylin.disruptor.event.LongEventFactory;
import com.claylin.disruptor.handler.LongEventHandler;
import com.claylin.disruptor.producer.LongEventProducer;
import com.lmax.disruptor.*;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.nio.ByteBuffer;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class LongEventMain {
    public static void main(String[] args) throws InterruptedException {
        int count = 100000;
        int threadNum = 60;

        CountDownLatch latch = new CountDownLatch(count * threadNum);
        Executor executor = Executors.newCachedThreadPool();

        LongEventFactory factory = new LongEventFactory();

        int bufferSize = 2 << 20;

        Disruptor<LongEvent> disruptor = new Disruptor(factory, bufferSize, executor,
                ProducerType.MULTI, new YieldingWaitStrategy());


        disruptor.handleEventsWith(new LongEventHandler(latch));
        disruptor.start();

        RingBuffer<LongEvent> ringBuffer = disruptor.getRingBuffer();

        LongEventProducer producer = new LongEventProducer(ringBuffer);


        long start = System.currentTimeMillis();
        for (int l = 0; l < threadNum; l++) {

            new Thread(() -> {
                for (int i = 0; i < count; i++) {
                    producer.onData(i);
                }
            }, "thread-" + l).start();

        }

        /*for (int i = 0; i < threadNum * count; i++) {
            producer.onData(i);
        }*/

        latch.await();

        long end = System.currentTimeMillis();

        System.out.println("tps: " + (count * threadNum) * 1000 / (end - start));
        System.exit(0);
    }
}
