package com.yangweiye.springbootdemos.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class FirstListener implements MessageListener {
    @Override
    public void onMessage(Message message, byte[] bytes) {
        System.out.println(new String(message.getBody()));
        System.out.println(new String(bytes));
    }
}
