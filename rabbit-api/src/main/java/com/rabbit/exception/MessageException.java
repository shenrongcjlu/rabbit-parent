package com.rabbit.exception;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:13
 */
public class MessageException extends RuntimeException {

    public MessageException() {
        super();
    }

    public MessageException(String message) {
        super(message);
    }

    public MessageException(String message, Exception cause) {
        super(message, cause);
    }

}
