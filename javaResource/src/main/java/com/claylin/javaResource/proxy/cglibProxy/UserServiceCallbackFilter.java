package com.claylin.javaResource.proxy.cglibProxy;

import net.sf.cglib.proxy.CallbackFilter;

import java.lang.reflect.Method;

public class UserServiceCallbackFilter implements CallbackFilter {
    private static final int NO_OP = 0;
    private static final int METHOD_INTERCEPTOR = 1;

    @Override
    public int accept(Method method) {
        int callBackIndex;
        String name = method.getName();
        if (name.equals("add")) {
            callBackIndex = 0;
        } else {
            callBackIndex = 1;
        }
        return callBackIndex;
    }
}
