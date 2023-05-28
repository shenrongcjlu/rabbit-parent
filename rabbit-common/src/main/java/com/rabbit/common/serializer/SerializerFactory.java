package com.rabbit.common.serializer;

import com.rabbit.common.serializer.impl.JacksonSerializer;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 16:29
 */
public interface SerializerFactory {

    JacksonSerializer create();

}
