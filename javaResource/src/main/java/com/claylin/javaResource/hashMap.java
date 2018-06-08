package com.claylin.javaResource;

import org.junit.jupiter.api.Test;

import java.util.Objects;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class hashMap {
    static class Student {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public Student setAge(int age) {
            this.age = age;
            return this;
        }

        public String getName() {
            return name;
        }

        public Student setName(String name) {
            this.name = name;
            return this;
        }

        @Override
        public String toString() {
            return "Student{" +
                    "age=" + age +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    @Test
    public void test() {
        int capacity = 22;
        int n = capacity;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        System.out.println(n >>> 1);
    }

    @Test
    public void test2() {
        System.out.println(-15 & 15);
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>());
    }
}
