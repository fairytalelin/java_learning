package com.claylin.javaResource.proxy.cglibProxy;

import com.claylin.javaResource.proxy.jdkProxy.UserServiceImpl;
import net.sf.cglib.proxy.*;

import java.lang.reflect.Method;

public class CglibProxyTest {
    public static void main(String[] args) throws Exception {
        //testFixedValue();
        //testInvocationHandler();
        //testMethodInterceptor();
        testCallbackFilter();
    }

    public static void testFixedValue() throws Exception {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new FixedValue() {
            @Override
            public Object loadObject() throws Exception {
                return "cglib proxy";
            }
        });
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        System.out.println(userService.getClass());
    }

    public static void testInvocationHandler() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                if (method.getDeclaringClass() != Object.class && method.getReturnType() == String.class) {
                    return "sdfds";
                } else {
                    throw new RuntimeException("do not know what to do");
                }
            }
        });

        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.add();
        /*String str = userService.test();
        System.out.println(str);*/
    }

    public static void testMethodInterceptor() {
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallback(new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return method.invoke(obj, args);
            }
        });
        UserServiceImpl userService = (UserServiceImpl) enhancer.create();
        userService.test();
    }

    public static void testCallbackFilter() {
        CallbackFilter filter = new UserServiceCallbackFilter();

        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(UserServiceImpl.class);
        enhancer.setCallbackFilter(filter);

        Callback noOpCallBack = NoOp.INSTANCE;
        Callback methodIntercepotrCallback = new MethodInterceptor() {
            @Override
            public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
                return proxy.invokeSuper(obj, args);
            }
        };

        enhancer.setCallbacks(new Callback[]{noOpCallBack, methodIntercepotrCallback});

        UserServiceImpl userService = (UserServiceImpl) enhancer.create();

        userService.test();
        userService.add();
    }
}
