package com.claylin.springboot.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    private static final String scriptStr = "if not redis.call('get', KEYS[1]) then redis.call('incr',KEYS[1]) redis.call('EXPIRE',KEYS[1],ARGV[1]) return tonumber(redis.call('get', KEYS[1])) else if(tonumber(redis.call('get',KEYS[1])) >= tonumber(ARGV[2])) then return 0 else redis.call('incr',KEYS[1]) return tonumber(redis.call('get', KEYS[1])) end end";

    @Bean
    public DefaultRedisScript<Long> commentRateCheck() {
        DefaultRedisScript<Long> script = new DefaultRedisScript<Long>();
        script.setLocation(new ClassPathResource("test.lua"));
        script.setResultType(Long.class);
        return script;
    }

    @Bean
    public RedisTemplate<String, Long> commentRateCheckTemplate(RedisConnectionFactory redisConnectionFactory, StringRedisSerializer stringRedisSerializer) {
        RedisTemplate<String, Long> redisTemplate = new RedisTemplate<String, Long>();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        return redisTemplate;
    }

    @Bean
    public StringRedisSerializer stringRedisSerializer(){
        return new StringRedisSerializer();
    }
}
