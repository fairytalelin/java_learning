package com.claylin.concurrencyInPractice.buildingBlock.queue.servlet;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
