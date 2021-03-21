package com.jarvan.springdemo.component.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 普通消息生产
 *
 * @description:
 * @author: jarvan
 * @create: 2021-03-18
 */
@Slf4j
@Component
public class DefaultPerducer {

    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     *
     * @param topic
     * @param group
     * @param msg
     */
    public void sendMsg(String topic, String group, String msg) {
        Message message = MessageBuilder.withPayload(msg).build();
        this.rocketMQTemplate.syncSend(topic + ":" + group, message);
    }

    /**
     * 发送延时消息
     *
     * @param topic
     * @param group
     * @param msg
     */
    public void sendDelayMsg(String topic, String group, String msg, int delay) {
        Message message = MessageBuilder.withPayload(msg).build();
        this.rocketMQTemplate.syncSend(topic + ":" + group, message, 1000, delay);
    }
}
