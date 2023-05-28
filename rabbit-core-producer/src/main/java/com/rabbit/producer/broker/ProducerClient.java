package com.rabbit.producer.broker;

import com.rabbit.api.Message;
import com.rabbit.api.MessageProducer;
import com.rabbit.api.SendCallback;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:33
 */
public class ProducerClient implements MessageProducer {
    @Override
    public void send(Message message) {

    }

    @Override
    public void send(Message message, SendCallback callback) {

    }

    @Override
    public void send(List<Message> messages) {

    }
}
