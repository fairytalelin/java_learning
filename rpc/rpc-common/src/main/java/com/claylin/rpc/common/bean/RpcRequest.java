package com.claylin.rpc.common.bean;

import java.io.Serializable;
import java.util.Arrays;

public class RpcRequest implements Serializable {
    private String context;
    private String interfaceName;
    private String serviceVersion;
    private String methodName;
    private Class<?>[] parameterTypes;
    private Object[] parameters;

    public String getContext() {
        return context;
    }

    public RpcRequest setContext(String context) {
        this.context = context;
        return this;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public RpcRequest setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
        return this;
    }

    public String getServiceVersion() {
        return serviceVersion;
    }

    public RpcRequest setServiceVersion(String serviceVersion) {
        this.serviceVersion = serviceVersion;
        return this;
    }

    public String getMethodName() {
        return methodName;
    }

    public RpcRequest setMethodName(String methodName) {
        this.methodName = methodName;
        return this;
    }

    public Class<?>[] getParameterTypes() {
        return parameterTypes;
    }

    public RpcRequest setParameterTypes(Class<?>[] parameterTypes) {
        this.parameterTypes = parameterTypes;
        return this;
    }

    public Object[] getParameters() {
        return parameters;
    }

    public RpcRequest setParameters(Object[] parameters) {
        this.parameters = parameters;
        return this;
    }

    @Override
    public String toString() {
        return "RpcRequest{" +
                "context='" + context + '\'' +
                ", interfaceName='" + interfaceName + '\'' +
                ", serviceVersion='" + serviceVersion + '\'' +
                ", methodName='" + methodName + '\'' +
                ", parameterTypes=" + Arrays.toString(parameterTypes) +
                ", parameters=" + Arrays.toString(parameters) +
                '}';
    }
}
