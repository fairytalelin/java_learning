package com.claylin.concurrencyInPractice.buildingBlock.queue.servlet.impl;

import com.claylin.concurrencyInPractice.buildingBlock.queue.servlet.Computable;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        return null;
    }
}
