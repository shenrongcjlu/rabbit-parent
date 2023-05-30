package com.rabbit.producer.broker;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.rabbit.api.Message;
import com.rabbit.api.MessageType;
import com.rabbit.common.convert.GenericMessageConverter;
import com.rabbit.common.convert.RabbitMessageConverter;
import com.rabbit.common.serializer.Serializer;
import com.rabbit.common.serializer.SerializerFactory;
import com.rabbit.common.serializer.impl.JacksonSerializer;
import com.rabbit.common.serializer.impl.JacksonSerializerFactory;
import com.rabbit.producer.service.MessageStoreService;
import com.rabbitmq.client.ConnectionFactory;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 说明: 池化rabbitTemplate封装
 *
 * @author 沈荣
 * @date 2023/5/28 16:07
 */
@Slf4j
@Component
public class RabbitTemplateContainer implements RabbitTemplate.ConfirmCallback {

    private Map<String, RabbitTemplate> rabbitMap = Maps.newConcurrentMap();

    private Splitter splitter = Splitter.on("#");

    private SerializerFactory serializerFactory = JacksonSerializerFactory.INSTANCE;

    @Resource
    private ConnectionFactory connectionFactory;
    @Resource
    private MessageStoreService messageStoreService;

    public RabbitTemplate getTemplate(Message message) {
        Preconditions.checkNotNull(message);
        String topic = message.getTopic();

        RabbitTemplate rabbitTemplate = rabbitMap.get(topic);
        if (rabbitTemplate != null) {
            return rabbitTemplate;
        }
        log.info("#RabbitTemplateContainer.getTemplate# topic: {} is not exist, createTemplate", topic);

        rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setExchange(topic);
        rabbitTemplate.setRetryTemplate(new RetryTemplate());
        rabbitTemplate.setRoutingKey(message.getRoutingKey());

        // 设置序列化方式
        Serializer serializer = serializerFactory.create();
        GenericMessageConverter genericMessageConverter = new GenericMessageConverter(serializer);
        RabbitMessageConverter rabbitMessageConverter = new RabbitMessageConverter(genericMessageConverter);
        rabbitTemplate.setMessageConverter(rabbitMessageConverter);

        // 只要不是异步消息，就注册回调
        String messageType = message.getMessageType();
        if (!StringUtils.equals(messageType, MessageType.RAPID)) {
            rabbitTemplate.setConfirmCallback(this);
        }
        rabbitMap.put(topic, rabbitTemplate);
        return rabbitTemplate;
    }

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        List<String> strings = splitter.splitToList(correlationData.getId());
        String messageId = strings.get(0);
        long sendTime = Long.parseLong(strings.get(1));

        if (ack) {
            messageStoreService.success(messageId);
            log.info("send message is ok, messageId: {}, time: {}", messageId, sendTime);
        } else {
            messageStoreService.failure(messageId);
            log.info("send message is failed, messageId: {}, time: {}", messageId, sendTime);
        }
    }
}
