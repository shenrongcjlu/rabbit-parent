package com.rabbit.api;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:16
 */
public interface MessageProducer {

    /**
     * 消息发送
     * @param message
     */
    void send(Message message);

    /**
     * 消息发送，带回调
     * @param message
     * @param callback
     */
    void send(Message message, SendCallback callback);

    /**
     * 消息批量发送
     * @param messages
     */
    void send(List<Message> messages);


}
