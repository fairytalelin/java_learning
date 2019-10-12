package com.claylin.jvm.learning.chapter8;

/**
 * 局部变量表Slot复用对垃圾收集器的影响之一
 *
 * @author linwucai
 * 2019-10-11 22:03
 **/

public class Example8_1 {
    public static void main(String[] args) {
        {
            byte[] data = new byte[64 * 1024 * 1024];
        }
        int a = 0;
        System.gc();
    }
}
