package com.rabbit.api;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 14:59
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Message implements Serializable {

    /**
     * 消息id
     */
    private String messageId;

    private String topic;

    private String routingKey = "";

    private Map<String, Object> attributes = new HashMap<>();

    private int delayMills;

    private String messageType = MessageType.CONFIRM;


}
