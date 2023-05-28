package com.rabbit.producer.autoconfigure;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * 说明:
 *
 * @author 沈荣
 * @date 2023/5/28 15:32
 */
@Configuration
@ComponentScan({"com.rabbit.producer.*"})
public class RabbitProducerAutoConfiguration {
}
