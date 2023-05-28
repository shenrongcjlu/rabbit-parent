package com.rabbit.producer.broker;

import com.rabbit.api.Message;

import java.util.List;

/**
 * 说明: 具体发送不同类型的接口
 *
 * @author 沈荣
 * @date 2023/5/28 15:45
 */
public interface RabbitBroker {

    /**
     * 快速消息
     * @param message
     */
    void rapidSend(Message message);

    void confirmSend(Message message);

    void reliantSent(Message message);

    void sendMessages(List<Message> messages);

}
