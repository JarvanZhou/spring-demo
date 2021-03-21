package com.jarvan.springdemo.component.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.spring.annotation.RocketMQMessageListener;
import org.apache.rocketmq.spring.core.RocketMQListener;
import org.apache.rocketmq.spring.core.RocketMQPushConsumerLifecycleListener;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

/**
 * 消费
 *
 * @description:
 * @author: jarvan
 * @create: 2021-03-18
 */
@Slf4j
@Component
@RocketMQMessageListener(topic = "spring-tx-my-topic",
        consumerGroup = "tx-consumer",
        selectorExpression = "tag")
public class TransactionConsumer implements RocketMQListener<String>, RocketMQPushConsumerLifecycleListener {
    @Override
    public void onMessage(String s) {
        try {
            //执行事务

        } catch (Exception exception) {
            exception.printStackTrace();
            //失败，发送邮件或加入死信队列
        }
    }

    @Override
    public void prepareStart(DefaultMQPushConsumer consumer) {
        consumer.registerMessageListener((MessageListenerConcurrently) (exts, context) -> {
            try {
                if (!CollectionUtils.isEmpty(exts)) {
                    exts.forEach(ext -> {
                        Integer con = ext.getReconsumeTimes();
                        if (con > 3) {
                            //
                        }
                        //
                    });
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            } catch (Exception e) {
                e.printStackTrace();
                log.error("消费消息失败，message：{}", e.getMessage());
                return ConsumeConcurrentlyStatus.RECONSUME_LATER;
            }
        });
    }
}
