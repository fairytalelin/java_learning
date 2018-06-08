package com.claylin.spring.com.claylin.spring.entity;

public class User {
    private long uid;
    private String name;
    private String sex;

    public long getUid() {
        return uid;
    }

    public User setUid(long uid) {
        this.uid = uid;
        return this;
    }

    public String getName() {
        return name;
    }

    public User setName(String name) {
        this.name = name;
        return this;
    }

    public String getSex() {
        return sex;
    }

    public User setSex(String sex) {
        this.sex = sex;
        return this;
    }

    @Override
    public String toString() {
        return "User{" +
                "uid=" + uid +
                ", name='" + name + '\'' +
                ", sex='" + sex + '\'' +
                '}';
    }
}
