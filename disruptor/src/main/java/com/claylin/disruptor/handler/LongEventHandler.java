package com.claylin.disruptor.handler;

import com.claylin.disruptor.event.LongEvent;
import com.lmax.disruptor.EventHandler;

import java.util.concurrent.CountDownLatch;

public class LongEventHandler implements EventHandler<LongEvent> {
    private CountDownLatch latch;
    private int i = 0;

    public LongEventHandler(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void onEvent(LongEvent event, long sequence, boolean endOfBatch) throws Exception {
        //System.out.println("Event: " + event);
        latch.countDown();
        //System.out.println(++i);
    }
}
