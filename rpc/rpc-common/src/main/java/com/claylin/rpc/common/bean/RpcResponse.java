package com.claylin.rpc.common.bean;

import java.io.Serializable;

public class RpcResponse implements Serializable {
    private String context;
    private Exception exception;
    private Object result;

    public String getContext() {
        return context;
    }

    public RpcResponse setContext(String context) {
        this.context = context;
        return this;
    }

    public Exception getException() {
        return exception;
    }

    public RpcResponse setException(Exception exception) {
        this.exception = exception;
        return this;
    }

    public Object getResult() {
        return result;
    }

    public RpcResponse setResult(Object result) {
        this.result = result;
        return this;
    }

    @Override
    public String toString() {
        return "RpcResponse{" +
                "context='" + context + '\'' +
                ", exception=" + exception +
                ", result=" + result +
                '}';
    }
}
