package com.spring.redis;

import com.spring.redis.config.Config;
import com.spring.redis.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by XiuYin.Cui on 2018/1/31.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test04 {

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void test04(){
        redisCacheService.zsetAdd("zset","a",1.0);
        redisCacheService.zsetAdd("zset","b",1.0);
        redisCacheService.zsetAdd("zset","c",2.0);
        redisCacheService.zsetAdd("zset","d",3.0);
    }

    @Test
    public void test10(){
        redisCacheService.zsetRemove("zset",3,"c","b","d","k","c++");
    }

    @Test
    public void test05(){
        Long zset = redisCacheService.zsetSize("zset");
        System.out.println(zset);
    }

    @Test
    public void test06(){
        Long zset = redisCacheService.zsetCount("zset", 0.5, 1.5);
        System.out.println(zset);
    }

    @Test
    public void test07(){
        Set<Object> zset = redisCacheService.zsetRange("zset", 0L, 1L);
        printSet(zset);
    }

    //TODO 测试没通过
    @Test
    public void test08(){
        Range range = new Range();
        range.gt("b");
        range.lt("d");
        System.out.println(range.getMin().getValue());
        System.out.println(range.getMax().getValue());
        Set<Object> zset = redisCacheService.zsetRangeByLex("zset", range);
        printSet(zset);
    }

    @Test
    public void test09(){
        Set<Object> zset = redisCacheService.zsetRangeByScore("zset", 0.5, 2.5);
        printSet(zset);
    }

    @Test
    public void test11(){
        Double aDouble = redisCacheService.zsetScore("zset", "b");
        System.out.println(aDouble);
    }



    public void printSet(Set set){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.print(String.valueOf(next) + " ");
        }
    }
}
