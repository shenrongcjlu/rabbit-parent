package com.rabbit.producer.broker;

import com.google.common.base.Preconditions;
import com.rabbit.api.Message;
import com.rabbit.api.MessageProducer;
import com.rabbit.api.MessageType;
import com.rabbit.api.SendCallback;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:33
 */
@Component
public class ProducerClient implements MessageProducer {

    @Resource
    private RabbitBroker rabbitBroker;

    @Override
    public void send(Message message) {
        Preconditions.checkNotNull(message.getTopic());
        String messageType = message.getMessageType();
        switch (messageType) {
            case MessageType.RAPID:
                break;
            case MessageType.CONFIRM:
                break;
            case MessageType.RELIANT:
                break;
            default:
                break;
        }
    }

    @Override
    public void send(Message message, SendCallback callback) {

    }

    @Override
    public void send(List<Message> messages) {

    }
}
