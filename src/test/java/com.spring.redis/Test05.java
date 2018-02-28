package com.spring.redis;

import com.spring.redis.config.Config;
import com.spring.redis.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;

/**
 * Created by XiuYin.Cui on 2018/2/1.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test05 {

    @Autowired
    private RedisCacheService redisCacheService;

    @Test
    public void test05() {
        Map<String, Object> map = new HashMap(5);
        map.put("mapKey1", "java");
        map.put("mapKey2", "c++");
        map.put("mapKey3", "python");
        redisCacheService.hashPutAll("hashKey", map);
    }

    @Test
    public void test06() {
        redisCacheService.hashDelete("hashKey", "mapKey1");
    }

    @Test
    public void test07() {
        Map<Object, Object> hashKey = redisCacheService.hashEntries("hashKey");
        printMap(hashKey);
    }

    @Test
    public void test08() {
        List<Object> hashKey = redisCacheService.hashValues("hashKey");
        for (int i = 0; i < hashKey.size(); i++) {
            System.out.println(hashKey.get(i));
        }
    }

    @Test
    public void test09(){
        Boolean aBoolean = redisCacheService.hashHasKey("hashKey", "mapKey1");
        System.out.println(aBoolean);
    }

    @Test
    public void test10(){
        Object o = redisCacheService.hashGet("hashKey", "mapKey1");
        System.out.println(String.valueOf(o));
    }


    public void printMap(Map<Object, Object> map) {
        Set<Map.Entry<Object, Object>> entries = map.entrySet();
        Iterator<Map.Entry<Object, Object>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<Object, Object> next = iterator.next();
            System.out.println("key:" + next.getKey() + ",value:" + next.getValue());
        }
    }
}
