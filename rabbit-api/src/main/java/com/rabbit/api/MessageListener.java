package com.rabbit.api;

import java.util.List;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:17
 */
public interface MessageListener {

    /**
     * 消息接收
     * @param message
     */
    void onMessage(Message message);

}
