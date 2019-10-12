package com.claylin.unsafe;

import sun.misc.Unsafe;

public class TestUnsafe {

    private Object[] entries;

    public static void main(String[] args) {
        Unsafe unsafe = UnsafeUtil.getUnsafe();
        int scale = unsafe.arrayIndexScale(Entity[].class);
        int arrayBase = unsafe.arrayBaseOffset(Entity[].class);
        System.out.println(arrayBase);
    }

    private static class Entity {
        private int age;
        private String name;

        public int getAge() {
            return age;
        }

        public Entity setAge(int age) {
            this.age = age;
            return this;
        }

        public String getName() {
            return name;
        }

        public Entity setName(String name) {
            this.name = name;
            return this;
        }
    }
}
