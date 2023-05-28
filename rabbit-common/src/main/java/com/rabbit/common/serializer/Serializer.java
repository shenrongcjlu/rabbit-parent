package com.rabbit.common.serializer;

/**
 * 说明: 序列化和反序列化
 *
 * @author 沈荣
 * @date 2023/5/28 16:29
 */
public interface Serializer {

    byte[] serializeRaw(Object data);

    String serialize(Object data);

    <T> T deserialize(String content);

    <T> T deserialize(byte[] content);

}
