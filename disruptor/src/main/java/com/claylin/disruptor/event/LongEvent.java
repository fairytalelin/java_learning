package com.claylin.disruptor.event;

public class LongEvent {
    private int value;

    public int getValue() {
        return value;
    }

    public LongEvent setValue(int value) {
        this.value = value;
        return this;
    }
}
