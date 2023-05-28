package com.rabbit.common.serializer.impl;

import com.rabbit.common.serializer.SerializerFactory;
import sun.plugin2.message.Message;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 16:34
 */
public class JacksonSerializerFactory implements SerializerFactory {

    public static final JacksonSerializerFactory INSTANCE = new JacksonSerializerFactory();

    @Override
    public JacksonSerializer create() {
        return JacksonSerializer.createParametricType(Message.class);
    }
}
