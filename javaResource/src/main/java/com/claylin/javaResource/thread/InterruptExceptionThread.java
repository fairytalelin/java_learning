package com.claylin.javaResource.thread;

import java.util.concurrent.TimeUnit;

public class InterruptExceptionThread implements Runnable {
    @Override
    public void run() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread interruptedThread = new Thread(new InterruptExceptionThread());
        interruptedThread.start();

        TimeUnit.SECONDS.sleep(2);

        interruptedThread.interrupt();

        System.out.println("InterruptedExceptionThread interrupted is " + interruptedThread.isInterrupted());
        TimeUnit.SECONDS.sleep(2);
    }
}
