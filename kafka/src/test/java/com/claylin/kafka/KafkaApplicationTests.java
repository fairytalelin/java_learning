package com.claylin.kafka;

import com.claylin.kafka.mq.BasicKafkaProducer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class KafkaApplicationTests {

    @Autowired
    private BasicKafkaProducer producer;

    @Test
    public void contextLoads() {
    }

}
