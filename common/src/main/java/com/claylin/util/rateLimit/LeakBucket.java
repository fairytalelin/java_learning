package com.claylin.util.rateLimit;

/**
 * 高并发漏桶限流法
 */
public class LeakBucket {
    private static final int TPS = 20;
    private static final int BUCKET_SIZE = 100;//桶容量
    private static long start = System.currentTimeMillis();
    private static long count = 0;//当前桶里剩余的未有处理的请求数

    public static boolean grant() {
        long now = System.currentTimeMillis();
        count = Math.max(0, (count - (now - start) * TPS));
        start = System.currentTimeMillis();
        if (BUCKET_SIZE > count) {
            count++;
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        Runnable worker = () -> {
            if (grant()) {
                //执行业务逻辑
                System.out.println("DONE");
            } else {
                System.out.printf("限流: %s\n", Thread.currentThread().getName());
            }
        };
        for (int i = 0; i < 100000; i++) {
            new Thread(worker).start();
        }
    }
}
