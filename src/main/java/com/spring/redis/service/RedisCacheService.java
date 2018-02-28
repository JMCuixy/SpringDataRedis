package com.spring.redis.service;

import org.springframework.data.redis.connection.RedisZSetCommands;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by XiuYin.Cui on 2018/1/19.
 */
public interface RedisCacheService {

    /**
     * 给Redis缓存赋值（默认不会过期）
     *
     * @param key
     * @param value
     */
    void set(String key, Object value);

    /**
     * 给缓存赋值
     *
     * @param key
     * @param value
     * @param timeOut 存活时间(分钟)
     */
    void set(String key, Object value, Long timeOut);


    /**
     * 给缓存赋值
     *
     * @param key
     * @param value
     * @param timeOut  存活时间
     * @param timeUnit 单位
     */
    void set(String key, Object value, Long timeOut, TimeUnit timeUnit);

    /**
     * 删除key值的所有数据，包括列值、键值对、hash等
     *
     * @param key
     */
    void delete(String key);

    /**
     * 批量删除key
     *
     * @param keys
     */
    void deleteKeys(Collection<String> keys);

    /**
     * 得到缓存对象
     *
     * @param key
     * @return
     */
    Object get(String key);


    /*----------------------------------------*/

    /**
     * 向List缓存对象尾部追加一个元素
     *
     * @param key
     * @param value
     */
    void listRightPush(String key, Object... value);

    /**
     * 向List缓存对象尾部追加集合
     *
     * @param key
     * @param collection
     */
    void listRightPushAll(String key, Collection<Object> collection);

    /**
     * 向List缓存对象头部追加一个元素
     *
     * @param key
     * @param value
     */
    Long listLeftPush(String key, Object... value);

    /**
     * 向List缓存对象头部追加一个集合
     *
     * @param key
     * @param collection
     */
    void listLeftPushAll(String key, Collection<Object> collection);

    /**
     * 集合尾部弹出一个元素
     * 副作用：同时会移除所弹出的元素
     *
     * @param key
     * @return
     */
    Object listRightPop(String key);


    /**
     * 集合头部弹出一个元素
     * 副作用：同时会移除所弹出的元素
     *
     * @param key
     * @return
     */
    Object listLeftPop(String key);

    /**
     * 获得对应缓存对象中的集合
     * 不会从列表中移除任何元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    List listRange(String key, Long start, Long end);

    /**
     * 在列表中index的位置设置value的值
     *
     * @param key
     * @param index
     * @param value
     */
    void listSet(String key, Long index, Object value);

    /**
     * 获取列表中对应索引的值，下标从0开始
     *
     * @param key
     * @param index
     */
    Object listIndex(String key, Long index);


    /*----------------------------------------*/

    /**
     * 向一个Set集合中追加元素
     *
     * @param key
     * @param value
     */
    void setAdd(String key, Object... value);

    /**
     * 向一个Set集合中删除元素
     *
     * @param key
     * @param value
     */
    void setRemove(String key, Object... value);

    /**
     * Set 集合随机返回一个元素并移除
     *
     * @param key
     * @return
     */
    Object setPop(String key);

    /**
     * 把一个Set集合中的value医道目标集合中
     *
     * @param key
     * @param value
     * @param destKey
     */
    void setMove(String key, String value, String destKey);

    /**
     * 集合随机返回一个元素不移除
     *
     * @param key
     * @return
     */
    Object setRandomMember(String key);

    /**
     * 集合随机返回一些元素
     *
     * @param key
     * @param count
     * @return
     */
    List<Object> setListRandomMember(String key, Long count);

    /**
     * 判断这个value是否在这个集合中
     *
     * @param key
     * @param value
     * @return
     */
    Boolean setIsMember(String key, Object value);

    /**
     * 得到Set 集合中的所有元素
     *
     * @param key
     * @return
     */
    Set<Object> setMembers(String key);

    /**
     * otherKey 相对于 key 的差集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<Object> setDifference(String key, String otherKey);

    /**
     * 求两个集合的并集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<Object> setUnion(String key, String otherKey);

    /**
     * 求两个集合的的交集
     *
     * @param key
     * @param otherKey
     * @return
     */
    Set<Object> setIsect(String key, String otherKey);


    /*----------------------------------------*/

    /**
     * 向zset 中添加元素
     *
     * @param key
     * @param value
     * @param score
     */
    void zsetAdd(String key, Object value, Double score);

    /**
     * Zset 中移除元素
     *
     * @param key
     * @param value
     */
    void zsetRemove(String key, Object... value);

    /**
     * 得到Zset中从start 到 end 的元素
     *
     * @param key
     * @param start
     * @param end
     * @return
     */
    Set<Object> zsetRange(String key, Long start, Long end);

    /**
     * 按 Zset 的score得到元素
     *
     * @param key
     * @param min
     * @param max
     * @return
     */
    Set<Object> zsetRangeByScore(String key, Double min, Double max);


    /**
     * 得到Zset集合中某个value的score
     *
     * @param key
     * @param value
     * @return
     */
    Double zsetScore(String key, Object value);

    /**
     * 得到Zset集合的长度
     *
     * @param key
     * @return
     */
    Long zsetSize(String key);

    /**
     * score 在某个范围内的长度
     *
     * @param key
     * @param mix
     * @param max
     * @return
     */
    Long zsetCount(String key, Double mix, Double max);

    /**
     * 按照值来排序的取值,这个排序只有在有相同分数的情况下才能使用，如果有不同的分数则返回值不确定
     *
     * @param key
     * @param range
     * @return
     */
    Set<Object> zsetRangeByLex(String key, RedisZSetCommands.Range range);


    /*----------------------------------------*/

    /**
     * 向hash中存入值
     *
     * @param key
     * @param otherKey
     * @param otherValue
     */
    void hashPut(String key, Object otherKey, Object otherValue);

    /**
     * 向hash中存入map集合
     *
     * @param key
     * @param map
     */
    void hashPutAll(String key, Map<String, Object> map);

    /**
     * 向hash 中删除值
     *
     * @param key
     * @param hashKeys
     */
    void hashDelete(String key, Object... hashKeys);

    /**
     * 得到存放 key 的 Map 集合
     *
     * @param key
     * @return
     */
    Map<Object, Object> hashEntries(String key);

    /**
     * 得到Hash key 的 list 集合
     *
     * @param key
     * @return
     */
    List<Object> hashValues(String key);

    /**
     * 得到hash key 中 Map 的 key 的value
     *
     * @param key
     * @param hashKey
     * @return
     */
    Object hashGet(String key, String hashKey);

    /**
     * 判断hash key 中 Map 的 key 是否存在
     *
     * @param key
     * @param otherKey
     * @return
     */
    Boolean hashHasKey(String key, String otherKey);


}
