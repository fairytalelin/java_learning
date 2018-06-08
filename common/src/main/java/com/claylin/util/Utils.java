package com.claylin.util;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.CountDownLatch;

public class Utils {
    private Utils() {
    }

    public static Unsafe getUnsafe() {
        Class clazz = Unsafe.class;
        try {
            Field theUnsafe = clazz.getDeclaredField("theUnsafe");
            theUnsafe.setAccessible(true);
            Unsafe unsafe = (Unsafe) theUnsafe.get(null);
            return unsafe;
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        Unsafe unsafe = getUnsafe();
        System.out.println(unsafe == null);
        Thread thread = new Thread(() -> {
            System.out.println("in thread " + Thread.currentThread().getName());
            latch.countDown();
            unsafe.park(false, 0);
        });
        thread.start();
        latch.await();
        unsafe.unpark(thread);
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().interrupt();
    }
}
