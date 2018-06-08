package com.claylin.spring.com.claylin.spring.config;

import com.claylin.spring.com.claylin.spring.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile("prod")
public class AppConfig {
    @Bean
    public User user() {
        User user = new User();
        user.setName("claylin")
                .setSex("male")
                .setUid(2200806714L);
        return user;
    }
}
