package com.spring.redis;

import com.spring.redis.config.Config;
import com.spring.redis.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Iterator;
import java.util.Set;

/**
 * Created by XiuYin.Cui on 2018/1/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test03 {

    @Autowired
    private RedisCacheService redisCacheService;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Test
    public void test03(){
        redisCacheService.setAdd("set","java");
        redisCacheService.setAdd("set","python");
        redisCacheService.setAdd("set","c++");
        //setMembers
        printSet(redisCacheService.setMembers("set"));
        System.out.println();
        //setIsMember
        System.out.println(redisCacheService.setIsMember("set","java"));
        System.out.println("------");
        //setRandomMember
        Object set = redisCacheService.setRandomMember("set");
        System.out.println(String.valueOf(set));
        System.out.println("---");
        //setPop
        Object set1 = redisCacheService.setPop("set");
        System.out.println(String.valueOf(set1));
        System.out.println("---");
        printSet(redisCacheService.setMembers("set"));
        //setRemove
        redisCacheService.setRemove("set","java");
        System.out.println();
        printSet(redisCacheService.setMembers("set"));
    }

    @Test
    public void test04(){
        printSet(redisCacheService.setMembers("set"));
        System.out.println();
        System.out.println("--------");

        redisCacheService.setAdd("set1","java","python","c++");
        printSet(redisCacheService.setMembers("set1"));
        //setDifference 差集
        System.out.println();
        System.out.println("--------");
        printSet(redisCacheService.setDifference("set1", "set"));
        //setUnion 并集
        System.out.println();
        System.out.println("--------");
        printSet(redisCacheService.setUnion("set", "set1"));
        //setIsect 交集
        System.out.println();
        System.out.println("--------");
        printSet(redisCacheService.setIsect("set", "set1"));
    }


    public void printSet(Set set){
        Iterator iterator = set.iterator();
        while (iterator.hasNext()){
            Object next = iterator.next();
            System.out.print(String.valueOf(next) + " ");
        }
    }

}
