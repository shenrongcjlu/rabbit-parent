package com.rabbit.producer.broker;

import com.rabbit.api.Message;
import com.rabbit.api.MessageType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:48
 */
@Slf4j
@Component
public class RabbitBrokerImpl implements RabbitBroker {

    @Resource
    private RabbitTemplate rabbitTemplate;

    @Override
    public void rapidSend(Message message) {
        message.setMessageType(MessageType.RAPID);
        sendKernel(message);
    }

    @Override
    public void confirmSend(Message message) {

    }

    @Override
    public void reliantSent(Message message) {

    }

    @Override
    public void sendMessages(List<Message> messages) {

    }

    /**
     * 发送消息的核心方法, 使用线程池异步发送
     * @param message
     */
    private void sendKernel(Message message) {
        AsyncBasicQueue.submit(() -> {
            String routingKey = message.getRoutingKey();
            String topic = message.getTopic();
            CorrelationData correlationData = new CorrelationData(String.format("%s#%s", message.getMessageId(), System.currentTimeMillis()));

            rabbitTemplate.convertAndSend("exchange", routingKey, message, correlationData);

            log.info("#RabbitBrokerImpl.sendKernel# send to rabbitmq, messageId: {}", message.getMessageId());
        });
    }
}
