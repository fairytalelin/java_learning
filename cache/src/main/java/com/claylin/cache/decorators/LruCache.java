package com.claylin.cache.decorators;

import com.claylin.cache.Cache;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;

public class LruCache implements Cache {

    private static final int DEFAULT_SIZE = 1024;

    private final Cache delegate;

    private Map<Object, Object> keyMap;
    private Object eldestKey;

    public LruCache(Cache cache) {
        this(cache, DEFAULT_SIZE);
    }

    public LruCache(Cache cache, int size) {
        this.delegate = cache;
        setSize(size);
    }

    @Override
    public void putObject(Object key, Object value) {
        delegate.putObject(key, value);
        cycleKeyList(key);
    }

    @Override
    public Object getObject(Object key) {
        return delegate.getObject(key);
    }

    @Override
    public Object removeObject(Object key) {
        return delegate.removeObject(key);
    }

    @Override
    public void clear() {
        delegate.clear();
    }

    @Override
    public int getSize() {
        return delegate.getSize();
    }

    public void setSize(final int size) {
        keyMap = new LinkedHashMap<Object, Object>(size, .75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<Object, Object> eldest) {
                boolean isFull = size() > size;// 当缓存大于size值时候就剔除最近未有使用的

                if (isFull) {
                    eldestKey = eldest.getKey();
                }

                return isFull;
            }
        };
    }

    @Override
    public ReadWriteLock getReadWriteLock() {
        return null;
    }

    private void cycleKeyList(Object key) {
        keyMap.put(key, key);
        if (eldestKey != null) {
            delegate.removeObject(eldestKey);
            eldestKey = null;
        }
    }
}
