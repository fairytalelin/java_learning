package com.claylin.cache.decorators;

import com.claylin.cache.Cache;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.locks.ReadWriteLock;

public class FifoCache implements Cache {
    private static final int DEFAULT_SIZE = 1024;
    private int size;
    private final Cache delegate;
    private Deque<Object> keyDeque;

    public FifoCache(Cache cache) {
        this(DEFAULT_SIZE, cache);
    }

    public FifoCache(int size, Cache cache) {
        this.size = size;
        this.delegate = cache;
        this.keyDeque = new LinkedList<>();
    }

    @Override
    public void putObject(Object key, Object value) {
        cycleKeyList(key);
        delegate.putObject(key, value);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        keyDeque.remove(key);
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        keyDeque.clear();
        delegate.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    private void cycleKeyList(Object key) {
        keyDeque.addLast(key);
        if (keyDeque.size() > size) {
            Object first = keyDeque.removeFirst();
            delegate.removeObject(first);
        }
    }
}
