package com.rabbit.api;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:06
 */
public final class MessageType {

    /**
     * 迅速消息，不需要保证可靠性
     */
    public final static String RAPID = "0";

    /**
     * 确认消息： 不需要保障可靠性，但是会做消息confirm确认
     */
    public final static String CONFIRM = "1";

    /**
     * 可靠性消息，一定要保存100%可靠性投递
     */
    public final static String RELIANT = "2";


}
