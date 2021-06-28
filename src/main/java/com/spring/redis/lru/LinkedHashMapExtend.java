package com.spring.redis.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @Author: xiuyin.cui@joymo.tech
 * @Date: 2021/6/28 22:26
 * @Description: 直接应用内置的 LinkedHashMap 应用 LRU 缓存
 */
public class LinkedHashMapExtend extends LinkedHashMap {

    private int cacheSize;


    public LinkedHashMapExtend(int cacheSize) {
        super();
        this.cacheSize = cacheSize;
    }

    @Override
    public boolean removeEldestEntry(Map.Entry eldest) {
        //重写移除逻辑
        if (size() > cacheSize) {
            return true;
        }
        return false;
    }

}

