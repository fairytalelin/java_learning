package com.claylin.spring;

import com.claylin.spring.com.claylin.spring.config.AppConfig;
import com.claylin.spring.com.claylin.spring.entity.User;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.nio.ByteBuffer;

public class AppAnnotationContext {
    public static void main(String[] args) {
       /* AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("prod");
        context.register(AppConfig.class);
        context.refresh();
        User user = context.getBean("user", User.class);
        System.out.println("user = " + user);*/

        long uid = 2200806714L;
        int sid = 78125698;

        ByteBuffer byteBuffer = ByteBuffer.allocate(Long.BYTES + Integer.BYTES);
        byteBuffer.putLong(22222L);
        byteBuffer.putInt(1);


        ByteBuffer buffer = ByteBuffer.wrap(byteBuffer.array());


        //byteBuffer.clear();
        byteBuffer.flip();

        System.out.println(byteBuffer.getLong());

        System.out.println(byteBuffer.getInt(8));

        System.out.println(new String(byteBuffer.array()));


        System.out.println(Long.toBinaryString(uid));
    }
}
