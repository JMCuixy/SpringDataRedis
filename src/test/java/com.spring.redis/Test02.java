package com.spring.redis;

import com.spring.redis.config.Config;
import com.spring.redis.service.RedisCacheService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by XiuYin.Cui on 2018/1/23.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = Config.class)
public class Test02 {
    @Autowired
    private RedisCacheService redisCacheService;


    @Test
    public void test02(){
        //存放list
        List<String> list = new LinkedList();
        list.add("123");
        list.add("456");
        list.add("789");
        redisCacheService.set("list",list);
        printList(redisCacheService.get("list"));

        //替换List
        List<String> list2=new LinkedList();
        list2.add("000");
        redisCacheService.set("list",list2);
        printList(redisCacheService.get("list"));

        //删除
        redisCacheService.delete("list");

        //listLeftPush(这样子存入的list数据类型不能用opsForValue().get(key)获取，否则会报WRONGTYPE Operation against a key holding the wrong kind of value)
        redisCacheService.listLeftPush("list","java");
        redisCacheService.listLeftPush("list","python");
        redisCacheService.listLeftPush("list","c++");
        System.out.println(redisCacheService.listLeftPop("list").toString());
    }


    @Test
    public void  test03(){
        //删除
        redisCacheService.delete("list3");
        redisCacheService.delete("list");
    }

    @Test
    public void test04(){
        //listLeftPushALL
        List list3 = new ArrayList();
        list3.add("java");
        list3.add("python");
        list3.add("c++");
        redisCacheService.listLeftPushAll("list3",list3);
        List listRange = redisCacheService.listRange("list3", 3L, 5L);
        printList(listRange);

        //listGetByIndex 输出某个位置的元素，以0下标开始（比如有三个元素 0 1 2 那么 3 4 5 输出也是代表一样的内容，上面类似）
        System.out.println("--------------------");
        Object list31 = redisCacheService.listIndex("list3", 6L);
        System.out.println(String.valueOf(list31));

        //listSet
        System.out.println("--------------------");
        redisCacheService.listSet("list3",0L,"javaScripe");
        List listRange1 = redisCacheService.listRange("list3", 0L, 2L);
        printList(listRange1);

        //listRange
        System.out.println("list3全部内容");
        printList(redisCacheService.listRange("list3",0L,100L));
    }

    //打印List集合
    private void printList(Object object){
        List list = (List) object;
        for (Object o:list){
            System.out.println(String.valueOf(o));
        }
    }
}
