package com.claylin.core.utils;

import com.sun.istack.internal.NotNull;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TimeWheel {
    private final int size = 2 << 4;
    private AtomicInteger curIndex = new AtomicInteger(0);
    private final int[] timeWheel;

    private final Map<Integer, Set<Long>> maps = new ConcurrentHashMap<>();

    public TimeWheel() {
        this.timeWheel = new int[size];
        for (int i = 0; i < size; i++) {
        }
    }

    class Entry {
        private final int index;
        private ReentrantLock lock = new ReentrantLock();
        private Set<Long> uids = new HashSet<>();
        private volatile int size = 0;

        Entry(int index) {
            this.index = index;
        }

        public void add(@NotNull Long uid) {
            try {
                lock.lock();
                uids.add(uid);
                size++;
            } finally {
                lock.unlock();
            }
        }

        /*public Long remove(Long uid) {
            try {

            } finally {
                //lock.unlocak
            }
        }*/
    }
}
