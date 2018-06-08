package com.claylin.javaResource.thread;

import java.util.concurrent.TimeUnit;

public class InterruptedThread implements Runnable {
    @Override
    public void run() {
        while (true) {
            System.out.println(Thread.currentThread().getName() + " is running");
        }
    }

    public static void main(String[] args) throws Exception{
        Thread interupptedThread = new Thread(new InterruptedThread());
        interupptedThread.start();

        TimeUnit.SECONDS.sleep(2);

        interupptedThread.interrupt();
        System.out.println("InterruptedThread interrupted is " + interupptedThread.isInterrupted());

        TimeUnit.SECONDS.sleep(2);
    }
}
