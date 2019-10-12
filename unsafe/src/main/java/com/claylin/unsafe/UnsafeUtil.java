package com.claylin.unsafe;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeUtil {
    private static final Unsafe unsafe;

    static {
        try {
            Field field = Unsafe.class.getDeclaredField("theUnsafe");
            field.setAccessible(true);
            unsafe = (Unsafe) field.get(null);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }

    public static Unsafe getUnsafe() {
        return unsafe;
    }
}
