package com.claylin.kafka.config;

import com.claylin.kafka.mq.BasicKafkaProducer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {

    @Bean
    public BasicKafkaProducer basicKafkaProducer() {
        return new BasicKafkaProducer();
    }
}
