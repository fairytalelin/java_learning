package com.claylin.springboot.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ClaylinProperties {
    @Value("${name}")
    private String name;
    @Value("${age}")
    private int age;

    public String getName() {
        return name;
    }

    public ClaylinProperties setName(String name) {
        this.name = name;
        return this;
    }

    public int getAge() {
        return age;
    }

    public ClaylinProperties setAge(int age) {
        this.age = age;
        return this;
    }

    @Override
    public String toString() {
        return "ClaylinProperties{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
