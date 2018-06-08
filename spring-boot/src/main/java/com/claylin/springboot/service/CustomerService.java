package com.claylin.springboot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.RedisScript;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerService {

    private static final int TTL = 200;//10 second
    private static final int HOUR_TTL = 60 * 60;// 1小时
    private static final int MAX_POST_COMMENT = 4;// TTL时间内允许发表的最大评论数
    private static final int HOUR_MAX_POST_COMMENT = 500;// 单人一小时发送最大评论数
    private static final int MAX_DUOKAI_NUM = 5; // 最大多开数

    private static final String scriptStr = "if not redis.call('get', KEYS[1]) then redis.call('incr',KEYS[1]) redis.call('EXPIRE',KEYS[1],ARGV[1]) return tonumber(redis.call('get', KEYS[1])) else if(tonumber(redis.call('get',KEYS[1])) >= tonumber(ARGV[2])) then return 0 else redis.call('incr',KEYS[1]) return tonumber(redis.call('get', KEYS[1])) end end";

    @Autowired
    private RedisScript<Long> redisScript;

    @Autowired
    private RedisTemplate<String, Long> redisTemplate;


    public void test() {
        List<String> list = new ArrayList<String>();
        list.add("test1");
        String key = list.get(0);
        Long commentNum = redisTemplate.execute(redisScript, list, String.valueOf(TTL), String.valueOf(MAX_POST_COMMENT));
        System.out.println(commentNum);
/*
        Object ret = redisTemplate.getConnectionFactory().getConnection().eval(scriptStr.getBytes(), ReturnType.INTEGER, 1,
                key.getBytes(), String.valueOf(TTL).getBytes(), String.valueOf(MAX_POST_COMMENT).getBytes());

        System.out.println(ret);*/
    }
}
