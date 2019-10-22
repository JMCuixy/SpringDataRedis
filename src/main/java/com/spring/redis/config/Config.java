package com.spring.redis.config;

import com.spring.redis.service.RedisCacheService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Created by XiuYin.Cui on 2018/1/19.
 */
@Configuration
@EnableCaching
@ComponentScan(basePackageClasses = RedisCacheService.class)
@PropertySource("classpath:redis.properties")
public class Config {

    @Value("${hostName:127.0.0.1}")
    private String hostName;

    @Value("${port:6379}")
    private Integer port;

    @Value("${password:}")
    private String password;

    /*Redis 连接工厂*/
    @Bean(name = "redisConnectionFactory")
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setHostName(hostName);
        jedisConnectionFactory.setPort(port);
        jedisConnectionFactory.setPassword(password);
        jedisConnectionFactory.setDatabase(2);
        jedisConnectionFactory.afterPropertiesSet();
        return jedisConnectionFactory;
    }

    /*RedisTemplate */
    @Bean(name = "redisTemplate")
    public RedisTemplate redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate redisTemplate = new RedisTemplate();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.afterPropertiesSet();
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringRedisTemplate.afterPropertiesSet();
        return stringRedisTemplate;
    }

    /*Redis 缓存管理器*/
    @Bean(name = "cacheManager")
    public CacheManager cacheManager(RedisTemplate redisTemplate) {
        return new RedisCacheManager(redisTemplate);
    }

    /**
     * redis消息监听器容器
     * <p>
     * 可以添加多个监听不同 topic 的 redis监听器，只需要把消息监听器和相应的消息订阅处理器绑定
     *
     * @param connectionFactory
     * @return
     */
    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        //订阅了一个叫 channel 的通道
        container.addMessageListener(channelAdapter(), new PatternTopic("channel"));
        //这个container 可以添加多个 messageListener
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器
     */
    @Bean
    public MessageListenerAdapter channelAdapter() {
        return new MessageListenerAdapter(new ChannelListener());
    }

}
