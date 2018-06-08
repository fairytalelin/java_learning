package com.claylin.javaResource.proxy.jdkProxy;

import sun.misc.ProxyGenerator;
import sun.security.action.GetBooleanAction;

import java.io.FileOutputStream;
import java.nio.file.*;
import java.security.AccessController;

public class Test {
    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", true);
        boolean flag = (Boolean) AccessController.doPrivileged(new GetBooleanAction("sun.misc.ProxyGenerator.saveGeneratedFiles"));
        UserService userService = new UserServiceImpl(11);
        MyInvocationHandler myInvocationHandler = new MyInvocationHandler(userService);
        UserService proxy = (UserService) myInvocationHandler.getProxy();
        proxy.add();



        String path = "/Users/claylin/Documents/java_project/javaResource/$P2roxy4.class";
        byte[] classFile = ProxyGenerator.generateProxyClass("$proxy4", new Class[]{UserService.class});
        Path path1 = Paths.get(path);
        Files.write(path1, classFile, StandardOpenOption.APPEND);
    }
}
