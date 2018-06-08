package com.claylin.disruptor.producer;

import com.claylin.disruptor.event.LongEvent;
import com.lmax.disruptor.EventTranslatorOneArg;
import com.lmax.disruptor.RingBuffer;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class LongEventProducer {
    private final int BATCH = 100;
    private final List<Integer> batch = new ArrayList<>();
    private final RingBuffer<LongEvent> ringBuffer;
    private final EventTranslators eventTranslators = new EventTranslators();

    public LongEventProducer(RingBuffer<LongEvent> ringBuffer) {
        this.ringBuffer = ringBuffer;
    }

    /*public void onData(ByteBuffer buffer) {
        long sequence = ringBuffer.next(); // grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // get the entry in the Disruptor for the sequence
            event.setValue(buffer.getLong(0));
        } finally {
            ringBuffer.publish(sequence);
        }
    }*/

    public void onData(int i) {
        long sequence = ringBuffer.next(); // grab the next sequence
        try {
            LongEvent event = ringBuffer.get(sequence); // get the entry in the Disruptor for the sequence
            event.setValue(i);
        } finally {
            ringBuffer.publish(sequence);
        }
    }

    public void onDataBatch(int i) {
        batch.add(i);
        if (batch.size() >= BATCH) {
            Integer[] buffers = batch.toArray(new Integer[]{});
            ringBuffer.publishEvents(eventTranslators, buffers);
            batch.clear();
        }
    }

    private class EventTranslators implements EventTranslatorOneArg<LongEvent, Integer> {
        @Override
        public void translateTo(LongEvent event, long sequence, Integer arg0) {
            event.setValue(arg0);
        }
    }
}
