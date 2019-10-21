package com.spring.redis;

import com.spring.redis.config.Config;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Redis 的事务和管道命令
 *
 * @author : cuixiuyin
 * @date : 2019/10/20
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test06 {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private RedisTemplate redisTemplate;

    /*管道命令*/
    @Test
    public void pipeline() {
        // 1.executePipelined 重写 入参 RedisCallback 的doInRedis方法
        List<Object> resultList = redisTemplate.executePipelined(new RedisCallback<String>() {
            @Override
            public String doInRedis(RedisConnection redisConnection) throws DataAccessException {
                // 2.redisConnection 给本次管道内添加 要一次性执行的多条命令
                // 2.1 一个set操作
                redisConnection.set("hello".getBytes(), "world".getBytes());
                // 2.2一个批量mset操作
                Map<byte[], byte[]> tuple = new HashMap();
                tuple.put("m_hello_1".getBytes(), "m_world_1".getBytes());
                tuple.put("m_hello_2".getBytes(), "m_world_2".getBytes());
                tuple.put("m_hello_3".getBytes(), "m_world_3".getBytes());
                redisConnection.mSet(tuple);
                // 2.3一个get操作
                redisConnection.get("m_hello_1".getBytes());
                // 3 这里一定要返回null，最终pipeline的执行结果，才会返回给最外层
                return null;
            }
        });
        // 4. 最后对redis pipeline管道操作返回结果进行判断和业务补偿
        for (Object str : resultList) {
            System.out.println(str);
        }
    }
}
