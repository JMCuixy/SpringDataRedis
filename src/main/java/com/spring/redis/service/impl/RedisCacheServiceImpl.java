package com.spring.redis.service.impl;

import com.spring.redis.service.RedisCacheService;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisZSetCommands.Range;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by XiuYin.Cui on 2018/1/19.
 */
@Service
public class RedisCacheServiceImpl implements RedisCacheService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, Long timeOut) {
        this.set(key, value, timeOut, TimeUnit.MINUTES);
    }

    public void set(String key, Object value, Long timeOut, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeOut, timeUnit);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    public void deleteKeys(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void listRightPush(String key, Object... value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    public void listRightPushAll(String key, Collection<Object> collection) {
        redisTemplate.opsForList().rightPushAll(key, collection);
    }

    public Long listLeftPush(String key, Object... value) {
        return redisTemplate.opsForList().leftPush(key, value);
    }

    public void listLeftPushAll(String key, Collection<Object> collection) {
        redisTemplate.opsForList().leftPushAll(key, collection);
    }

    public Object listRightPop(String key) {
        Object[] objects = (Object[]) redisTemplate.opsForList().rightPop(key);
        return objects[0];
    }

    public Object listLeftPop(String key) {
        Object[] objects = (Object[]) redisTemplate.opsForList().leftPop(key);
        return objects[0];
    }

    public List listRange(String key, Long start, Long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    public void listSet(String key, Long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    public Object listIndex(String key, Long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    public void setAdd(String key, Object... value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public void setRemove(String key, Object... value) {
        redisTemplate.opsForSet().remove(key, value);
    }

    public Object setPop(String key) {
        return redisTemplate.opsForSet().pop(key);
    }

    public void setMove(String key, String value, String destKey) {
        redisTemplate.opsForSet().move(key, value, destKey);
    }

    public Object setRandomMember(String key) {
        return redisTemplate.opsForSet().randomMember(key);
    }

    public List<Object> setListRandomMember(String key, Long count) {
        return redisTemplate.opsForSet().randomMembers(key, count);
    }

    public Boolean setIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public Set<Object> setMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    public Set<Object> setDifference(String key, String otherKey) {
        return redisTemplate.opsForSet().difference(key, otherKey);
    }

    public Set<Object> setUnion(String key, String otherKey) {
        return redisTemplate.opsForSet().union(key, otherKey);
    }

    public Set<Object> setIsect(String key, String otherKey) {
        return redisTemplate.opsForSet().intersect(key, otherKey);
    }

    public void zsetAdd(String key, Object value, Double score) {
        redisTemplate.opsForZSet().add(key, value, score);
    }

    public void zsetRemove(String key, Object... value) {
        redisTemplate.opsForZSet().remove(key, value);
    }

    public Set<Object> zsetRange(String key, Long start, Long end) {
        return redisTemplate.opsForZSet().range(key, start, end);
    }

    public Set<Object> zsetRangeByScore(String key, Double min, Double max) {
        return redisTemplate.opsForZSet().rangeByScore(key, min, max);
    }

    public Double zsetScore(String key, Object value) {
        return redisTemplate.opsForZSet().score(key, value);
    }

    public Long zsetSize(String key) {
        return redisTemplate.opsForZSet().size(key);
    }

    public Long zsetCount(String key, Double mix, Double max) {
        return redisTemplate.opsForZSet().count(key, mix, max);
    }

    public Set<Object> zsetRangeByLex(String key, Range range) {
        return redisTemplate.opsForZSet().rangeByLex(key, range);
    }

    public void hashPut(String key, Object otherKey, Object otherValue) {
        redisTemplate.opsForHash().put(key, otherKey, otherValue);
    }

    public void hashPutAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public void hashDelete(String key, Object... hashKeys) {
        redisTemplate.opsForHash().delete(key, hashKeys);
    }

    public Map<Object, Object> hashEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    public Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public Boolean hashHasKey(String key, String otherKey) {
        return redisTemplate.opsForHash().hasKey(key, otherKey);
    }

    public List<Object> hashValues(String key) {
        return redisTemplate.opsForHash().values(key);
    }


}
