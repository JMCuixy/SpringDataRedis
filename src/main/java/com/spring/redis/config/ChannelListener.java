package com.spring.redis.config;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;

/**
 * @author : cuixiuyin
 * @date : 2019/10/21
 */
public class ChannelListener implements MessageListener {

    @Override
    public void onMessage(Message message, byte[] pattern) {
        System.out.println("channel is：" + new String(message.getChannel()));
        System.out.println("channel content：" + new String(message.getBody()));
    }
}
