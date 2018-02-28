package com.spring.redis;

import com.spring.redis.config.Config;
import com.spring.redis.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.concurrent.TimeUnit;


/**
 * Created by XiuYin.Cui on 2018/1/22.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test01 {

    @Autowired
    private RedisCacheService redisCacheService;

    //String 测试
    @Test
    public void test01(){
        redisCacheService.set("key","value");
        System.out.println(String.valueOf(redisCacheService.get("key")));//value
        //
        redisCacheService.set("key","value2");
        System.out.println(String.valueOf(redisCacheService.get("key")));//value2
        //
        redisCacheService.delete("key");
        System.out.println(String.valueOf(redisCacheService.get("key")));//null
        //
        redisCacheService.set("key","value3",10L, TimeUnit.SECONDS);
        System.out.println(String.valueOf(redisCacheService.get("key")));//value3
        try {
            Thread.sleep(11000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(redisCacheService.get("key"));//null
        //

    }

}
