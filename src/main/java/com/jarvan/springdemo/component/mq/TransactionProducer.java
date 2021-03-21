package com.jarvan.springdemo.component.mq;

import org.apache.rocketmq.spring.core.RocketMQTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * 事务消息生产
 *
 * @description:
 * @author: jarvan
 * @create: 2021-03-18
 */
@Component
public class TransactionProducer {
    @Autowired
    private RocketMQTemplate rocketMQTemplate;

    /**
     * 发送消息
     *
     * @param topic
     * @param msg
     */
    public void sendMsg(String topic, String group, String msg) {
        Message message = MessageBuilder.withPayload(msg).build();
        // myTransactionGroup要和@RocketMQTransactionListener(txProducerGroup = "myTransactionGroup")定义的一致
        this.rocketMQTemplate.sendMessageInTransaction(group, topic, message, null);
        System.out.println("发送消息成功");
    }
}
