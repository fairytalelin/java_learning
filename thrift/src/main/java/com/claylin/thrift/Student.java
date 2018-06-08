package com.claylin.thrift;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.codec.ThriftStruct;

import java.util.Arrays;
import java.util.Objects;

import static com.google.common.base.MoreObjects.toStringHelper;

@ThriftStruct("Student")
public final class Student
{
    public Student() {
    }

    private String name;

    @ThriftField(value=1, name="name", requiredness=Requiredness.NONE)
    public String getName() { return name; }

    @ThriftField
    public void setName(final String name) { this.name = name; }

    private int age;

    @ThriftField(value=2, name="age", requiredness=Requiredness.NONE)
    public int getAge() { return age; }

    @ThriftField
    public void setAge(final int age) { this.age = age; }

    private long id;

    @ThriftField(value=3, name="id", requiredness=Requiredness.NONE)
    public long getId() { return id; }

    @ThriftField
    public void setId(final long id) { this.id = id; }

    @Override
    public String toString()
    {
        return toStringHelper(this)
            .add("name", name)
            .add("age", age)
            .add("id", id)
            .toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Student other = (Student)o;

        return
            Objects.equals(name, other.name) &&
            Objects.equals(age, other.age) &&
            Objects.equals(id, other.id);
    }

    @Override
    public int hashCode() {
        return Arrays.deepHashCode(new Object[] {
            name,
            age,
            id
        });
    }
}
