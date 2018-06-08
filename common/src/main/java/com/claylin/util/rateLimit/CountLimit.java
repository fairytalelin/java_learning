package com.claylin.util.rateLimit;

/**
 * 高并发情况下的限流
 * 计数限流：限制一定时间内的请求数
 */
public class CountLimit {
    private static final int LIMIT = 200;// time interval 时间内的请求数
    private static final int TIME_INTERVAL = 1000;// 单位毫秒
    private static int reqCount = 0;
    private static long start = System.currentTimeMillis();

    public static boolean grant() {
        long now = System.currentTimeMillis();
        if (now - start < TIME_INTERVAL) {
            reqCount++;
            if (reqCount > LIMIT) {
                return false;
            }
            return true;
        } else {
            reqCount = 0;
            start = System.currentTimeMillis();
        }
        return true;
    }

    public static void main(String[] args) {
        Runnable worker = () -> {
            if (grant()) {
                // 执行业务代码
            } else {
                System.out.println("已经限流。。。:" + Thread.currentThread().getName());
            }
        };
        for (int i = 1; i <= 20000; i++) {
            new Thread(worker).start();
        }
    }
}
