package com.rabbit.api;

/**
 * 说明: 回调函数处理
 *
 * @author 沈荣
 * @date 2023/5/28 15:17
 */
public interface SendCallback {

    void onSuccess();

    void onFailure();

}
