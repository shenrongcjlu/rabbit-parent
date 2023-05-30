package com.rabbit.producer.service;

import com.rabbit.producer.constant.BrokerMessageStatus;
import com.rabbit.producer.entity.BrokerMessage;
import com.rabbit.producer.mapper.BrokerMessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/31 0:02
 */
@Service
public class MessageStoreService {

    @Resource
    private BrokerMessageMapper brokerMessageMapper;

    public int insert(BrokerMessage brokerMessage) {
        return brokerMessageMapper.insert(brokerMessage);
    }

    public void success(String messageId) {
        brokerMessageMapper.changeBrokerMessageStatus(messageId, BrokerMessageStatus.SEND_OK.getCode(), new Date());
    }

    public void failure(String messageId) {
        brokerMessageMapper.changeBrokerMessageStatus(messageId, BrokerMessageStatus.SEND_FAIL.getCode(), new Date());
    }
}
