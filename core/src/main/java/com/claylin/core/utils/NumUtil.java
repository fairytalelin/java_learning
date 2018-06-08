package com.claylin.core.utils;

public class NumUtil {

    private NumUtil() {
    }

    /**
     * 把cap转换为大于cap的2的幂次方的数
     *
     * @param cap 待转换的数
     * @return
     */
    public int transformExponent(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >> 16;
        return n + 1;
    }
}
