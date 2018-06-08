package com.claylin.kafka.mq;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.springframework.beans.factory.InitializingBean;

import java.util.Properties;

public class BasicKafkaProducer implements InitializingBean {
    private Producer<String, String> kafkaProducer;

    @Override
    public void afterPropertiesSet() throws Exception {
        Properties props = new Properties();
        props.put("boostrap.servers", "10.211.55.4:9092");
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<>(props);
    }

    public void send() {

    }
}
