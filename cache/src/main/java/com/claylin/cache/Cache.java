package com.claylin.cache;

import java.util.concurrent.locks.ReadWriteLock;

public interface Cache {
    void putObject(Object key, Object value);

    Object getObject(Object key);

    Object removeObject(Object key);

    void clear();

    int getSize();

    ReadWriteLock getReadWriteLock();
}
