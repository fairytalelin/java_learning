package com.claylin.util.rateLimit;

/**
 * 令牌桶限流方法
 */
public class TokenBucket {
    private static final int SPEED = 2;//
    private static final int BUCKET_SIZE = 1000;
    private static long retainToken = 0;
    private static long start = System.currentTimeMillis();

    public static boolean grant() {
        long now = System.currentTimeMillis();
        retainToken = Math.min(BUCKET_SIZE, retainToken + (now - start) * SPEED);
        start = System.currentTimeMillis();
        if (retainToken > 0) {
            retainToken--;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Runnable worker = () -> {
            if (grant()) {
                System.out.printf("Done: %s\n", Thread.currentThread().getName());
            } else {
                System.out.printf("rate limit, %s\n", Thread.currentThread().getName());
            }
        };

        for (int i = 0; i <= 20000; i++) {
            new Thread(worker).start();
        }
    }
}
