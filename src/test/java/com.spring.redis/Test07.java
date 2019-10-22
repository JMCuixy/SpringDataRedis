package com.spring.redis;

import com.spring.redis.config.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Redis 发布订阅功能
 *
 * @author : cuixiuyin
 * @date : 2019/10/21
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test07 {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    public void publish() {
        redisTemplate.convertAndSend("channel", "Hello,World");
    }
}
