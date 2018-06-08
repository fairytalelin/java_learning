package com.claylin.thrift;

import com.facebook.swift.codec.ThriftField;
import com.facebook.swift.codec.ThriftField.Requiredness;
import com.facebook.swift.service.ThriftMethod;
import com.facebook.swift.service.ThriftService;
import com.google.common.util.concurrent.ListenableFuture;

@ThriftService("StudentService")
public interface StudentService
{
    @ThriftService("StudentService")
    public interface Async
    {
        @ThriftMethod(value = "getById")
        ListenableFuture<Student> getById(
                @ThriftField(value = 1, name = "id", requiredness = Requiredness.NONE) final long id
        );
    }
    @ThriftMethod(value = "getById")
    Student getById(
            @ThriftField(value = 1, name = "id", requiredness = Requiredness.NONE) final long id
    ) throws org.apache.thrift.TException;
}