package com.jarvan.springdemo.component.mq;

import lombok.extern.slf4j.Slf4j;
import org.apache.rocketmq.spring.annotation.RocketMQTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionListener;
import org.apache.rocketmq.spring.core.RocketMQLocalTransactionState;
import org.apache.rocketmq.spring.support.RocketMQHeaders;
import org.springframework.messaging.Message;

/**
 * 监听事务消息发送结果
 *
 * @description:
 * @author: jarvan
 * @create: 2021-03-18
 */
@Slf4j
@RocketMQTransactionListener(txProducerGroup = "tx-consumer")
public class TransactionListenerImpl implements RocketMQLocalTransactionListener {
    /**
     * 执行业务逻辑
     *
     * @param message
     * @param o
     * @return
     */
    @Override
    public RocketMQLocalTransactionState executeLocalTransaction(Message message, Object o) {
        String transId = (String) message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);

        try {
            //执行事务

            // 返回事务状态给生产者
            return RocketMQLocalTransactionState.COMMIT;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return RocketMQLocalTransactionState.ROLLBACK;
    }

    /**
     * 回查
     *
     * @param message
     * @return
     */
    @Override
    public RocketMQLocalTransactionState checkLocalTransaction(Message message) {
//        String transId = (String)message.getHeaders().get(RocketMQHeaders.TRANSACTION_ID);
        return null;
    }
}
